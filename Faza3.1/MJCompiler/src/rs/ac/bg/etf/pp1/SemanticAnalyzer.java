package rs.ac.bg.etf.pp1;

import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;

 

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;
 



public class SemanticAnalyzer extends VisitorAdaptor  {
	

 
	boolean errorDetected = false;
	Type currentVarType = null;
	int enumValues;
	Obj currentMethod=null;
	Obj currentClass=null;
	
	int memberLevel;
	Obj memberObj;
	Struct memberType;
	
	boolean returnFound=false;
	boolean mainFound=false;
	boolean inForLoop=false;
	
	Logger log = Logger.getLogger(getClass());
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	public boolean passed(){
    	return !errorDetected;
    }
	//=================================================================================
	//Conditions
	public void visit(ForHeader forHeader) {
		inForLoop=true;
		Struct struct = forHeader.getConditionOrNull().struct;
		if (struct.getKind()!=Struct.Bool) {
			report_error("Greska na liniji " +  forHeader.getLine()+ " : uslov izraza nije bool. ", null);
		}
		//log.info(">>>>>>>>>>>>>>>>forHeader line: "+forHeader.getLine());
	}
	public void visit (ForStatement forStatement) {
		inForLoop=false;
		//log.info("forStatement line: "+forStatement.getLine());
	}
	public void visit(ConditionNotNull condition) {
		condition.struct=condition.getCondition().struct;
	}
	public void visit(ConditionNull condition) {
		condition.struct=TabExtra.boolType;
	}
	public void visit(BreakStatement breakStatement) {
		if (inForLoop) {
			
		}else {
			report_error("Greska na liniji " +  breakStatement.getLine()+ " : break nije u for petlji! ", null);
		}
	}
	public void visit(ContinueStatement continueStatement) {
		if (inForLoop) {
			
		}else {
			report_error("Greska na liniji " +  continueStatement.getLine()+ " : continue nije u for petlji! ", null);
		}
	}
	//CONDITIONS MAIN
	public void visit(IfConditionNode ifCondition) {
		 ifCondition.struct=ifCondition.getCondition().struct;
	}
	public void visit(ConditionNode condition) {
		 condition.struct=condition.getCondTermList().struct;
	}
	//Terms
	public void visit(ConditionTerms condTerms) {
		Struct left = condTerms.getCondTermList().struct;
    	Struct right = condTerms.getCondTerm().struct;
    	if(right == TabExtra.boolType && left == TabExtra.boolType){
    		condTerms.struct = TabExtra.boolType;
    	}else{
			//report_error("Greska na liniji "+ condTerms.getLine()+" : nekompatibilni tipovi u relaciji OR. ", null);
			condTerms.struct = Tab.noType;
    	}
	}
	public void visit(SingleConditionTerm term) {
		 term.struct=term.getCondTerm().struct;
	}
	
	public void visit(ConditionTermNode condTermNode) {
		 condTermNode.struct=condTermNode.getCondFactList().struct;
	}
	//Facts
	public void visit(ConditionFacts condFacts) {
		Struct left = condFacts.getCondFactList().struct;
    	Struct right = condFacts.getCondFact().struct;
    	if(right == TabExtra.boolType && left == TabExtra.boolType){
    		condFacts.struct = TabExtra.boolType;
    	}else{
			//report_error("Greska na liniji "+ condFacts.getLine()+" : nekompatibilni tipovi u relaciji AND. ", null);
			condFacts.struct = Tab.noType;
    	}
	}
	public void visit(SingleConditionFact fact) {
		 fact.struct=fact.getCondFact().struct;
	}
	//Expr
	public void visit(CondFactExpression singleExpr) {
		singleExpr.struct=singleExpr.getExpr().struct;
	}
	public void visit(CondFactExpressionRelopExpression expr) {
		Struct left = expr.getExpr().struct;
    	Struct right = expr.getExpr1().struct;
		if (left.compatibleWith(right)) {
			if (left.getKind()==Struct.Class || left.getKind()==Struct.Array) {
				
				 boolean validRelation=false;
				 if (expr.getRelop() instanceof RelopEqualEqual || expr.getRelop() instanceof RelopNotEqual) {
					 validRelation=true;
				 }
				 
				if (((left.getKind()==Struct.Class && right.getKind()==Struct.Class)||(left.getKind()==Struct.Array && right.getKind()==Struct.Array)) && (validRelation))
				{
					//report_info(">>>>>>>>>>>>>>>>CLASS/ARRAY: Expr1 relOp Expr2 ",expr);
					expr.struct=TabExtra.boolType;
				}else {
					expr.struct=Tab.noType;
					report_error("Greska na liniji "+ expr.getLine()+" : nekompatibilna relacija za klasu ili niz. ", null);

				}
			}else {
				//report_info(">>>>>>>>>>>>>>>>Expr1 relOp Expr2 ", expr);
				expr.struct=TabExtra.boolType;
			}
			
		}else {
			expr.struct=Tab.noType;
			
			report_error("Greska na liniji "+ expr.getLine()+" : nekompatibilni tipovi u relaciji. ", null);

		}
		 
	}
 
	//=====================================================================================
	//Statement
	public void visit(ReadStatement readStatement) {
		Obj des = readStatement.getDesignator().obj;
		int kind = des.getType().getKind();
		if ((des.getKind()==Obj.Var || des.getKind()==Obj.Fld || des.getKind()==Obj.Elem) && (kind==Struct.Int || kind==Struct.Char || kind==Struct.Bool)) 
		{
			
		}else {
			report_error("Greska na liniji " +  readStatement.getLine()+ " : Nekompatibilan tip u read funkciji. ", null);
		}
	}
	public void visit(PrintStatement printStatement) {
		int kind = printStatement.getExpr().struct.getKind();
		if ((kind==Struct.Int || kind==Struct.Char || kind==Struct.Bool)) 
		{
			
		}else {
			report_error("Greska na liniji " +  printStatement.getLine()+ " : Nekompatibilan tip izraza u print funkciji. ", null);
		}
	}
	public void visit(PrintWithNumConstStatement printStatement) {
		int kind = printStatement.getExpr().struct.getKind();
		if ((kind==Struct.Int || kind==Struct.Char || kind==Struct.Bool)) 
		{
			
		}else {
			report_error("Greska na liniji " +  printStatement.getLine()+ " : Nekompatibilan tip izraza u print funkciji. ", null);
		}
	}
	
	
	
	//Expressions
	public void visit(DesignatorStatementAssignop assignment) {
		//Obj obj = Tab.find(assignment.getDesignator().obj.getName());
		
		//Compatible
//		if (!assignment.getExpr().struct.compatibleWith(assignment.getDesignator().obj.getType())) {
//    		report_error("Greska na liniji " + assignment.getLine() + " : " + "nekompatibilni tipovi u dodeli vrednosti! ", null);
//
//		}else {
//			report_info("Dodela vrednosti za " + assignment.getDesignator().obj.getName(), assignment);
//		}
		
		Obj des=assignment.getDesignator().obj;
		int desKind=assignment.getDesignator().obj.getType().getKind();
		if ((des.getKind()==Obj.Var || des.getKind()==Obj.Fld || des.getKind()==Obj.Elem)) {
			
			//AssignableTo
			if(!assignment.getExpr().struct.assignableTo(assignment.getDesignator().obj.getType()))
	    		report_error("Greska na liniji " + assignment.getLine() + " : " + "nekompatibilni tipovi u dodeli vrednosti! ", null);
			else
				report_info("Dodela vrednosti za " + assignment.getDesignator().obj.getName(), assignment);

			
		}else {
			
			report_error("Greska na liniji " + assignment.getLine() + " : " + "nekompatibilni tip kome se dodeljuje vrednost! ", null);
			
			
		}
		
	
	}
	
	
	//++/--
	public void visit(DesignatorStatementPlusPlus pp) {
		Obj des=pp.getDesignator().obj;
		if ((des.getKind()==Obj.Var || des.getKind()==Obj.Fld || des.getKind()==Obj.Elem) && (des.getType().getKind()==Struct.Int)) {
			
		}else {
			report_error("Greska na liniji " + pp.getLine() + " : " + "nekompatibilni tip na kome se vrsi inkrementiranje(++)! ", null);

		}
	}
	public void visit(DesignatorStatementMinusMinus mm) {
		Obj des=mm.getDesignator().obj;
		if ((des.getKind()==Obj.Var || des.getKind()==Obj.Fld || des.getKind()==Obj.Elem) && (des.getType().getKind()==Struct.Int)) {
			
		}else {
			report_error("Greska na liniji " + mm.getLine() + " : " + "nekompatibilni tip na kome se vrsi dekrementiranje(--)! ", null);

		}
	}
	public void visit(SingleExpressionMinus expr) {
		Struct struct = expr.getTerm().struct;
		if (struct.getKind()!=Struct.Int) {
			expr.struct=Tab.noType;
			report_error("Greska na liniji " + expr.getLine() + " : " + " ako je prefiks '-', tip mora biti int! ", null);
		}else {
			expr.struct=expr.getTerm().struct;
		}
	}
	//+,*
	//ADD
	public void visit(ExpressionNode expr) {
		Struct right = expr.getTerm().struct;
    	Struct left = expr.getExpr().struct;
    	if(left.equals(right) && left == Tab.intType){
    		expr.struct = left;
    	}else{
			report_error("Greska na liniji "+ expr.getTerm().getLine()+" : nekompatibilni tipovi u izrazu za sabiranje.", null);
			expr.struct = Tab.noType;
    	}
	}
	public void visit(SingleExpression expr) {
		expr.struct=expr.getTerm().struct;
	}
	//MUL
	public void visit (TermFactor t) {
		t.struct=t.getFactor().struct;
	}
	public void visit (TermNode n) {
		Struct right = n.getFactor().struct;
    	Struct left = n.getTerm().struct;
    	if(left.equals(right) && left == Tab.intType){
    		n.struct = left;
    	}else{
			report_error("Greska na liniji "+ n.getLine()+" : nekompatibilni tipovi u izrazu za mnozenje", null);
			n.struct = Tab.noType;
    	}
	}
	//Last ones
	public void visit(DesignatorEmpty designator) {
		designator.struct=designator.getDesignator().obj.getType();
		if (designator.getDesignator().obj!=Tab.noObj) {
			report_info("Pronadjen poziv promenljive " + designator.getDesignator().obj.getName() + " na liniji " + designator.getLine(), null);
		}
	}
	public void visit(DesignatorBraceActPars designatorBrace){
		Obj func = designatorBrace.getDesignator().obj;
    	if(Obj.Meth == func.getKind()){
			report_info("Pronadjen poziv funkcije " + func.getName() + " na liniji " + designatorBrace.getLine(), null);
			designatorBrace.struct = func.getType();
    	}else{
			report_error("Greska na liniji " + designatorBrace.getLine()+" : ime " + func.getName() + " nije funkcija!", null);
			designatorBrace.struct = Tab.noType;
    	}
	}
	public void visit(DesignatorIdent designatorIdent){
	   	 //report_info("Procitana promenljiva/funkc : "+designatorIdent.getBaseName(),null);
			Obj obj = Tab.find(designatorIdent.getBaseName());
			if(obj == Tab.noObj){
				report_error("Greska na liniji " + designatorIdent.getLine()+ " : ime "+designatorIdent.getBaseName()+" nije deklarisano! ", null);
			} 		
			 
			memberObj=obj;
			memberType=obj.getType();
			memberLevel=0;
	   }
	public void visit(Idents dotIdent) {
		Collection<Obj> collection = memberObj.getLocalSymbols();
		Iterator<Obj> i  = collection.iterator();
		memberObj=Tab.noObj;
		memberType=Tab.noType;
		while (i.hasNext())
		{
		    Obj obj = i.next();
		   	//report_info(">>>>>>>>Moguce .dot prom/funkc | "+obj.getName(),null);
		   	if (obj.getName().equals(dotIdent.getI2())) {
		   		memberObj=obj;
		   		memberType=obj.getType();
		   		report_info("Izabrano polje : ."+obj.getName(), null);
		   	}
		}
		if (memberType==Tab.noType) {
			report_error("Greska na liniji " +  dotIdent.getParent().getLine()+ " : polje "+ dotIdent.getI2()+" nije deklarisano! ", null);

		}
		memberLevel++;
	}
	public void visit(Exprs elem) {
		if (memberType.getKind()==Struct.Array) {
			memberType=memberObj.getType().getElemType();
			memberObj= new Obj(Obj.Elem, "polje", memberType);
			if (elem.getExpr().struct.getKind()==Struct.Int) {
				
			}else {
				if (memberObj!=Tab.noObj) 
					report_error("Greska na liniji " +  elem.getParent().getLine()+ " : indeks(izraz) nije int! ", null);
				memberType=Tab.noType;
				memberObj=Tab.noObj;
			}
			
		}else {
			if (memberObj!=Tab.noObj) 
				report_error("Greska na liniji " +  elem.getParent().getLine()+ " : polje "+ memberObj.getName()+" nije niz! ", null);
			memberType=Tab.noType;
			memberObj=Tab.noObj;
		}
	}
	public void visit(DesignatorNode designatorNode){
  	 	//report_info("Rad sa promenljivom/funkc | "+designatorNode.getBaseName(),null);
//		Obj obj = Tab.find(designatorNode.getDesignatorIdent().getBaseName());
//		if(obj == Tab.noObj){
//			report_error("Greska na liniji " + designatorNode.getLine()+ " : ime "+designatorNode.getDesignatorIdent().getBaseName()+" nije deklarisano! ", null);
//		} 		
//		 
		designatorNode.obj = memberObj;
  }
	public void visit (ConstantNumber number) {
		number.struct=Tab.intType;
	}
	public void visit (ConstantCharacter ch) {
		ch.struct=Tab.charType;
	}
	public void visit (ConstantBoolean b) {
		b.struct=TabExtra.boolType;
	}
	public void visit (NewType newEmpty) {
		Struct struct = newEmpty.getType().struct;
		if (struct.getKind()==Struct.Class) {
			newEmpty.struct=newEmpty.getType().struct;
		}else {
			report_error("Greska na liniji " + newEmpty.getLine()+ " : nije klasa. ", null);
			newEmpty.struct=Tab.noType;
		}
		
		
	}
	public void visit (NewArray newArray) {
		Struct struct  = newArray.getExpr().struct;
		if (struct.getKind()==Struct.Int) {
			newArray.struct = new Struct(Struct.Array,newArray.getType().struct);
		}else {
			report_error("Greska na liniji " + newArray.getLine()+ " : velicina niza mora biti int. ", null);
			newArray.struct=Tab.noType;
		}
		
	}
	public void visit (ExprParenNode expr) {
		expr.struct=expr.getExpr().struct;
	}
	//-------------------------------------------------------------------------------------
	//SCOPES: Program and Functions
	public void visit(ProgName progName){
    	 //report_info("ProgName >> test | "+progName.getProgName(),null);
    	 progName.obj=Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
    	 Tab.openScope();
    }
    
    public void visit(Program program){
    	//report_info("Program >> test | " + program.getProgName().getProgName(),null);
    	Tab.chainLocalSymbols(program.getProgName().obj);
    	Tab.closeScope();
    }
    public void visit(EnumIdent enumIdent) {
    	enumIdent.obj=Tab.insert(Obj.Type, enumIdent.getEnumIdent(), TabExtra.enumType);
    	report_info("Deklarisan novi enum tip: " + enumIdent.getEnumIdent(), enumIdent);
    	Tab.openScope();
   	 	enumValues=0;
    }
    public void visit(EnumDeclarationNode enumDecl) {
    	Tab.chainLocalSymbols(enumDecl.getEnumIdent().obj);
    	Tab.closeScope();
    }
    public void visit(ClassDeclIdent classIdent) {
    	Struct classStruct = new Struct(Struct.Class);
    	classIdent.obj=Tab.insert(Obj.Type, classIdent.getClassIdent(), classStruct);
   	 	currentClass=classIdent.obj;
    	Tab.openScope();
   	 	
   	 	//insert 'this' field
   	 	Tab.insert(Obj.Fld, "this", classIdent.obj.getType());
   	 	
    }
    public void visit(ClassDeclarationWithoutMethods classDecl) {
    	Tab.chainLocalSymbols(classDecl.getClassDeclIdent().obj);
    	Tab.closeScope();
    	currentClass=null;
    }
    public void visit(ClassDeclarationWithMethods classDecl) {
    	Tab.chainLocalSymbols(classDecl.getClassDeclIdent().obj);
    	Tab.closeScope();
    	currentClass=null;
    }
    public void visit(MethodTypeName mTypeName){
	    report_info("Definisana funkcija "+mTypeName.getMethodName(),mTypeName);
		currentMethod=Tab.insert(Obj.Meth, mTypeName.getMethodName(),mTypeName.getReturnTypes().struct);
		Tab.openScope();
   }
   
   public void visit(MethodDecl mDecl){
	   //report_info("Method >> scope end | " + mDecl.getMethodTypeName().getMethodName()+"| level=["+currentMethod.getLevel()+"]",null);
	   if(!returnFound && currentMethod.getType() != Tab.noType){
			report_error("Semanticka greska na liniji " + mDecl.getLine() + ": funkcija " + currentMethod.getName() + " nema return iskaz!", null);
   		}
	   //Samo za main provera---------------
	   if (mDecl.getMethodTypeName().getMethodName().equals("main")) {
			mainFound=true;
			if (mDecl.getMethodTypeName().getReturnTypes().struct==Tab.noType && currentMethod.getLevel()==0) {
				report_info("main funkcija je korektna. ", mDecl);
			}else {
				report_error("Greska : main nije korektan.", mDecl);				
			}
	   }
	   //------------------------------------
	   Tab.chainLocalSymbols(currentMethod);
	   Tab.closeScope();
	   currentMethod=null;
	   returnFound=false;
   }
   //---------------------------------------------------------------------------------
   //Return checks
   public void visit(ReturnStatementExpr ret) {
    	returnFound=true;
    	Struct currMethType = currentMethod.getType();
    	if(!currMethType.compatibleWith(ret.getExpr().struct)){
			report_error("Greska" + " : " + "tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije " + currentMethod.getName(), null);
    	}
    }
    public void visit(ReturnStatementVoid ret2) {
    	returnFound=true;
    	Struct currMethType = currentMethod.getType();
    	if(currMethType!=Tab.noType){
			report_error("Greska" + " : " + "funkcija nema void povratni tip,  " + currentMethod.getName(), null);
    	}
    }
    //----------------------------------------------------------------------------
    //VarDecl start list
    public void visit(VarDeclType v) {
    	//report_info("new var list>>>>>>>>>>>>>>>>>START "+v.getType().struct.getKind(), null);
    	currentVarType=v.getType();
    }   
    public void visit(VarDeclNode v) {
    	//report_info("new var list>>>>>>>>>>>>>>>>>END ", null);
    	currentVarType=null;
    }
    //ConstDecl start list
    public void visit(ConstDeclType v) {
    	//report_info("new const list>>>>>>>>>>>>>>>>>START "+v.getType().struct, v);
    	currentVarType=v.getType();
    }   
    public void visit(ConstDeclNode v) {
    	//report_info("new const list>>>>>>>>>>>>>>>>>START "+v.getConstDeclType().getType(), v);
    	currentVarType=null;
    }   
    
    //Class VarDecl list
    public void visit(ClassVarDeclType v) {
    	//report_info("new class var list>>>>>>>>>>>>>>>>>START "+v.getType().struct.getKind(), null);
    	currentVarType=v.getType();
    }    
    public void visit(ClassVarDeclNode v) {
    	//report_info("new class var list>>>>>>>>>>>>>>>>>END ", null);
    	currentVarType=null;
    }
    //Enum
    public void visit (EnumerationNoVal enumNoVal)
    {
    	//Provera da li vec postoji
    	Obj obj = Tab.currentScope().findSymbol(enumNoVal.getIdentName());
    	if(obj != null){
			report_error("Greska na liniji " + enumNoVal.getLine()+ " : ime "+enumNoVal.getIdentName()+" vec deklarisano! ", null);
		}else {
	    	//Ako ne postoji
	    	report_info("Definisana enum konstanta "+ enumNoVal.getIdentName()+" sa vrednoscu "+enumValues, enumNoVal);
	    	Obj enumConst=Tab.insert(Obj.Con, enumNoVal.getIdentName(), Tab.intType);
	    	enumConst.setAdr(enumValues);
	    	enumValues++;
		} 
    }
    public void visit (EnumerationVal enumVal)
    {
    	//Provera da li vec postoji
    	Obj obj = Tab.currentScope().findSymbol(enumVal.getIdentName());
    	if(obj != null){
			report_error("Greska na liniji " + enumVal.getLine()+ " : ime "+enumVal.getIdentName()+" vec deklarisano! ", null);
		}else {
	    	//Ako ne postoji
			if (enumValues>enumVal.getN1()) {
				report_error("Greska na liniji " + enumVal.getLine()+ " : "+enumVal.getIdentName()+" ima neodgovarajucu vrednost! ", null);
			}else {
				report_info("Definisana enum konstanta "+ enumVal.getIdentName()+" sa vrednoscu "+enumVal.getN1(), enumVal);
		    	Obj enumConst=Tab.insert(Obj.Con, enumVal.getIdentName(), Tab.intType);
		    	enumConst.setAdr(enumValues);
		    	enumValues=enumVal.getN1()+1;
			}
	    	
		} 
    }
    //FormalParams
    public void visit(FormalParamDeclarationNoBrackets fnb) {
    	//Provera da li vec postoji
    	Obj obj = Tab.currentScope().findSymbol(fnb.getParamName());
    	if(obj != null){ 
			report_error("Greska na liniji " + fnb.getLine()+ " : ime "+fnb.getParamName()+" vec deklarisano! ", null);
		}else {
	    	//Ako ne postoji
	    	report_info("Deklarisan single argument "+ fnb.getParamName(), fnb);
	    	Obj var=Tab.insert(Obj.Var, fnb.getParamName(), fnb.getType().struct);
	    	currentMethod.setLevel(currentMethod.getLevel()+1);
		}
    }
    public void visit(FormalParamDeclarationBrackets fb) {
    	//Provera da li vec postoji
    	Obj obj = Tab.currentScope().findSymbol(fb.getParamName());
    	if(obj != null){
			report_error("Greska na liniji " + fb.getLine()+ " : ime "+fb.getParamName()+" vec deklarisano! ", null);
		}else {
	    	report_info("Deklarisan array argument "+ fb.getParamName(), fb);
	    	Struct arrayStruct = new Struct(Struct.Array, fb.getType().struct);
	    	Obj var=Tab.insert(Obj.Var, fb.getParamName(), arrayStruct);
	    	currentMethod.setLevel(currentMethod.getLevel()+1);
		}
    }
    //Variable Local/Global
    public void visit(VariableNoBracket vnb) {
    	//Provera da li vec postoji
    	Obj obj = Tab.currentScope().findSymbol(vnb.getVarName());
    	if(obj != null){
			report_error("Greska na liniji " + vnb.getLine()+ " : ime "+vnb.getVarName()+" vec deklarisano! ", null);
		}else {
	    	//Ako ne postoji
	    	report_info("Deklarisana single promenljiva "+ vnb.getVarName(), vnb);
	    	Obj var=Tab.insert(Obj.Var, vnb.getVarName(), currentVarType.struct);
		}
    	
    }
    public void visit(VariableBracket vb) {
    	//Provera da li vec postoji
    	Obj obj = Tab.currentScope().findSymbol(vb.getVarName());
    	if(obj != null){
			report_error("Greska na liniji " + vb.getLine()+ " : ime "+vb.getVarName()+" vec deklarisano! ", null);
		}else {
	    	report_info("Deklarisana array promenljiva "+ vb.getVarName(), vb);
	    	Struct arrayStruct = new Struct(Struct.Array,currentVarType.struct);
	    	Obj var=Tab.insert(Obj.Var, vb.getVarName(), arrayStruct);
		}
    }
    //Constant
    public void visit(IdentEqualConstantNode c) {
    	//Provera da li vec postoji
    	Obj obj = Tab.currentScope().findSymbol(c.getConstName());
    	if(obj != null){
			report_error("Greska na liniji " + c.getLine()+ " : ime "+c.getConstName()+" vec deklarisano! ", null);
		}else {
			if (currentVarType.struct!=c.getConstant().struct) {
				report_error("Greska na liniji " + c.getLine()+ " : "+c.getConstName()+" nije dobrog tipa! ", null);
			}else {
				report_info("Definisana konstanta ["+c.getConstant().struct.getKind()+"]"+ c.getConstName(), c);
		    	Obj var=Tab.insert(Obj.Con, c.getConstName(), currentVarType.struct);
		    	//var.setAdr(trenutnaVrednost);
			}
	    	
	    	
		}
    } 
    public void visit(NumberNode number) {
    	number.struct=Tab.intType;
    	 
    }
	public void visit(CharNode chr) {
		chr.struct=Tab.charType;
	}
	public void visit(BooleanNode b) {
		b.struct=TabExtra.boolType;
	}
    //----------------------------------------------------------------------------
    
    public void visit(TypeNode type){
    	Obj typeNode = Tab.find(type.getTypeName());
    	if(typeNode == Tab.noObj){
    		report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola! ", null);
    		type.struct = Tab.noType;
    	}else{
    		if(Obj.Type == typeNode.getKind()){
				if (typeNode.getType().getKind()==Struct.Enum) {
					type.struct = Tab.intType;
				}else {
					type.struct = typeNode.getType();
				}
    			
    		}else{
    			report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
    			type.struct = Tab.noType;
    		}
    	}
    }
    public void visit(NoVoidType noVoidType) {
    	//report_info("NoVoidType detektovan ", noVoidType);
    	noVoidType.struct=noVoidType.getType().struct;
    }
    public void visit(VoidType voidType) {
    	//report_info("VoidType detektovan ", voidType);
    	voidType.struct=Tab.noType;
    	
    }
}

class TabExtra{
	public static final Struct boolType = new Struct(Struct.Bool),
							   enumType = new Struct(Struct.Enum);
	
	public static void init() {
		Tab.currentScope().addToLocals(new Obj(Obj.Type, "bool", boolType));
		Tab.currentScope().addToLocals(new Obj(Obj.Type, "enum", enumType));
	}
}