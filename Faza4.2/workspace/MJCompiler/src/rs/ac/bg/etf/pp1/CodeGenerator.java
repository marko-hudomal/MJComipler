package rs.ac.bg.etf.pp1;

import java.util.Stack;

 

import rs.ac.bg.etf.pp1.CounterVisitor.*;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class CodeGenerator extends VisitorAdaptor {
	
	private int mainPc;
	private Obj currentObject=null;
	
	public int getMainPc(){
		return mainPc;
	}
	
	 
	
	public void visit(SingleIdentExpr ident) {
		//currentObject=ident.obj;
	}
	
	public void visit(ReadStatement rd) {
		Obj des = rd.getDesignator().obj;
		int kind = des.getType().getKind();
		if (kind==Struct.Char) {
			Code.put(Code.bread);
		}else {
			Code.put(Code.read);
		}

		Code.store(rd.getDesignator().obj);
	}
	public void visit(PrintStatement printStmt){
		if(printStmt.getExpr().struct == Tab.intType || printStmt.getExpr().struct == TabExtra.boolType){
			Code.loadConst(1);
			Code.put(Code.print);
		}else{
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
	}
	public void visit(PrintWithNumConstStatement printStmt){

		int num = printStmt.getN2();
 
		if(printStmt.getExpr().struct == Tab.intType || printStmt.getExpr().struct == TabExtra.boolType){
			Code.loadConst(num);
			Code.put(Code.print);
		}else{
			Code.loadConst(num);
			Code.put(Code.bprint);
		}
	}
	public void visit(ConstantNumber cnst){
		Obj con = Tab.insert(Obj.Con, "$", cnst.struct);
		con.setLevel(0);
		con.setAdr(cnst.getN1());
		
		Code.load(con);
	}
	public void visit(ConstantCharacter cnst){
		Obj con = Tab.insert(Obj.Con, "$", cnst.struct);
		con.setLevel(0);
		con.setAdr(cnst.getC1());
		
		Code.load(con);
	}
	public void visit(ConstantBoolean cnst){
		Obj con = Tab.insert(Obj.Con, "$", cnst.struct);
		con.setLevel(0);
		con.setAdr(cnst.getB1());
		
		Code.load(con);
	}
	
	//METHOD START
	public void visit(MethodTypeName methodTypeName){
		//System.err.println(methodTypeName.getMethodName());
		
		
		if("main".equalsIgnoreCase(methodTypeName.getMethodName())){
			mainPc = Code.pc;
		}	
		
		methodTypeName.obj.setAdr(Code.pc);
		// Collect arguments and local variables
		SyntaxNode methodNode = methodTypeName.getParent();
	
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		
		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);
		
		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());
		
	}
	//METHOD END
	public void visit(MethodDecl mDecl){
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	public void visit(NewType newType) {
		Struct struct = newType.getType().struct;
		
		Code.put(Code.new_);
		Code.put2(struct.getNumberOfFields()*4); 
		//System.err.println(struct.getNumberOfFields());
	}
	public void visit(Idents dotIdent) {
		Obj left = dotIdent.getIdentExprList().obj;
		if (left.getType().getKind()==Struct.Class) {
			Code.load(left);
		}
		currentObject=left;
		//System.err.println("idents "+currentObject.getName());
	}
	public void visit(NewArray newArray) {
 
		Code.put(Code.newarray);
		if (newArray.getNewArrayType().struct.getKind()==Struct.Char)
			Code.put(0);
		else
			Code.put(1);
	}
	public void visit(NewArrayType newArrayType) {
		newArrayType.struct=newArrayType.getType().struct;
		
	}
	public void visit(DesignatorStatementAssignop assignment) {		
		Code.store(assignment.getDesignator().obj);
	}
	
	public void visit(DesignatorNode designator){
		SyntaxNode parent = designator.getParent();
		
		if(DesignatorStatementAssignop.class != parent.getClass() && DesignatorStatementPlusPlus.class!=parent.getClass() && DesignatorStatementMinusMinus.class!=parent.getClass() && ReadStatement.class!=parent.getClass() && DesignatorStatementActPars.class != parent.getClass() && DesignatorBraceActPars.class != parent.getClass()){
			if (currentObject!=null && designator.obj.getLevel()==1) {
				//Code.load(currentObject);
				//System.err.println("ovde ima "+currentObject.getName());
			}
			
			Code.load(designator.obj);
		}
	}
	public void visit(DesignatorStatementPlusPlus des) {
		if (des.getDesignator().obj.getKind()==Obj.Elem) {
			Code.put(Code.dup2);
		}
		if (des.getDesignator().obj.getKind()==Obj.Fld) {
			Code.put(Code.dup);
		}
		Code.load(des.getDesignator().obj);
		
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(des.getDesignator().obj);
 
	}
	public void visit(DesignatorStatementMinusMinus des) {
		if (des.getDesignator().obj.getKind()==Obj.Elem) {
			Code.put(Code.dup2);
		}
		Code.load(des.getDesignator().obj);
		
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(des.getDesignator().obj);
		
	}
	public void visit(Exprs arrayElem) {
		//Adr i expr su stavljeni na stek
		
	}
	public void visit(IdentExprListArray array) {
		Code.load(array.obj);
	}
	public void visit(DesignatorBraceActPars funcCall){
		if (funcCall.getDesignator().obj.getName()!="chr" && funcCall.getDesignator().obj.getName()!="ord")
		{
			if (funcCall.getDesignator().obj.getName()=="len") {
				Code.put(Code.arraylength);
			}else {
				Obj functionObj = funcCall.getDesignator().obj;
				int offset = functionObj.getAdr() - Code.pc;
				Code.put(Code.call);
				
				Code.put2(offset);
			}
			
		}
		
	}
	
	public void visit(DesignatorStatementActPars procCall){
		if (procCall.getDesignator().obj.getName()!="chr" && procCall.getDesignator().obj.getName()!="ord")
		{
			if (procCall.getDesignator().obj.getName()=="len") {
				Code.put(Code.arraylength);
			}else {
				Obj functionObj = procCall.getDesignator().obj;
				int offset = functionObj.getAdr() - Code.pc;
				Code.put(Code.call);
				Code.put2(offset);
				
				if(procCall.getDesignator().obj.getType() != Tab.noType){
					Code.put(Code.pop);
				}
			}
		
		}
	}
	public void visit(ReturnStatementExpr returnExpr){
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(ReturnStatementVoid returnNoExpr){
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	public void visit(ExpressionNode addExpr){

		if (addExpr.getAddop() instanceof AddopPlus) {
			Code.put(Code.add);
		}else
		if (addExpr.getAddop() instanceof AddopMinus) {
			Code.put(Code.sub);
		}
		

	}
	public void visit(TermNode mulExpr) {
		if (mulExpr.getMulop() instanceof MulopMul) {
			Code.put(Code.mul);
		}else
		if (mulExpr.getMulop() instanceof MulopDiv) {
			Code.put(Code.div);
		}else 
		if (mulExpr.getMulop() instanceof MulopPerc) {
			Code.put(Code.rem);
		}
		 
		
	}
	public void visit(SingleExpressionMinus minusExpr) {
		Code.put(Code.neg);
	}
	
	Stack<Integer> adr1=new Stack<>(),adr2=new Stack<>();
	
	public void visit(IfConditionNode condition)
	{
		Code.loadConst(1);
		Code.putFalseJump(Code.eq, 0);
		adr1.push(Code.pc-2);
		
	}
	public void visit(IfStatementThen statementThen) {
		if (statementThen.getParent().getClass()==IfElseStatement.class) {
			Code.putJump(0);
			adr2.push(Code.pc-2);
		}
		
		Code.fixup(adr1.pop());
	}
	public void visit(IfStatementElse statementElse) {
		Code.fixup(adr2.pop());
	}
	public void visit(CondFactExpressionRelopExpression condRel) {		
		int jump_code=Code.inverse[Code.eq];;		
		if (condRel.getRelop().getClass()==RelopEqualEqual.class)
		{
			jump_code=Code.inverse[Code.eq];
		}else
		if (condRel.getRelop().getClass()==RelopNotEqual.class)
		{
			jump_code=Code.inverse[Code.ne];
		}else
		if (condRel.getRelop().getClass()==RelopGreater.class)
		{
			jump_code=Code.inverse[Code.gt];
		}else
		if (condRel.getRelop().getClass()==RelopGreaterEqual.class)
		{
			jump_code=Code.inverse[Code.ge];
		}else
		if (condRel.getRelop().getClass()==RelopLess.class)
		{
			jump_code=Code.inverse[Code.lt];
		}else	
		if (condRel.getRelop().getClass()==RelopLessEqual.class)
		{
			jump_code=Code.inverse[Code.le];
		}
		
		Code.putFalseJump(jump_code, Code.pc+7);
		Code.loadConst(0);
		Code.putJump(Code.pc+4);
		Code.loadConst(1);
		
	}
	public void visit(ConditionFacts condAnd) {
		Code.loadConst(1);
		Code.putFalseJump(Code.inverse[Code.ne], Code.pc+11);
		Code.loadConst(1);
		Code.putFalseJump(Code.inverse[Code.ne], Code.pc+8);
		Code.loadConst(1);
		Code.putJump (Code.pc+5);
		Code.put(Code.pop);
		Code.loadConst(0);
	}
	public void visit(ConditionTerms condOr) {
		Code.loadConst(1);
		Code.putFalseJump(Code.inverse[Code.ne], Code.pc+8);
		Code.put(Code.pop);
		Code.loadConst(1);
		Code.putJump(Code.pc+8);
		Code.loadConst(1);
		Code.putFalseJump(Code.inverse[Code.eq],Code.pc-5);
		Code.loadConst(0);		
	}
	
	Stack<Integer> top=new Stack<>(),top2=new Stack<>();
	int for1,temp,for2;
	
	public void visit(ForStatement1 stat1) {
		for1=Code.pc;
		//System.err.println("1");
	}
	public void visit(ForCondition cond) {
		Code.loadConst(1);
		//Izlaz iz petlje
		Code.putFalseJump(Code.inverse[Code.ne], 0);
		top.push(Code.pc - 2);
		//Nastavak iteracije, preskace se stat2
		Code.putJump(0);
		for2=Code.pc;
		top2.push(Code.pc);
		//System.err.println("2");
	}
	public void visit(ForStatement2 stat2) {
		Code.putJump(for1);
		Code.fixup(for2-2);
		//System.err.println("3");
	}
	public void visit(ForStatement forStatement) {
		Code.putJump(top2.pop());
		Code.fixup(top.pop());
		//Code.fixup(temp);
		//System.err.println("4");
	}
	public void visit(ContinueStatement cont) {
		Code.putJump(top2.peek());
	}
	public void visit(BreakStatement brk) {
		Code.loadConst(0);
		Code.loadConst(1);
		Code.putJump(top.peek()-1);
		
	}
	public  void visit(ConditionNull none) {
		Code.loadConst(1);
	}
}
