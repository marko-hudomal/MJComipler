package rs.ac.bg.etf.pp1;

import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
 

import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.util.MyDump;
import rs.ac.bg.etf.pp1.util.MyPrint;
 
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;
import rs.etf.pp1.symboltable.structure.HashTableDataStructure;
import rs.etf.pp1.symboltable.structure.SymbolDataStructure;
 



public class SemanticAnalyzer extends VisitorAdaptor  {
	
	int nVars;
 
	boolean errorDetected = false;
	Type currentVarType = null;
	int enumValues;
	Obj currentMethod=null;
	Obj currentClass=null;
	
	Obj classMethodCall=null;
	
	int memberLevel;
	Obj memberObj;
	Struct memberType;
	
	//Function params
	HashTableDataStructure currentActParams=new HashTableDataStructure();
	
	//Obj currentNewClass=null;
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
		Struct struct = forHeader.getForCondition().getConditionOrNull().struct;
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
		 if (condition.struct!=TabExtra.boolType) {
				report_error("Greska na liniji "+ condition.getLine()+" : uslov nije boolean. ", null);
		 }
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
			if (left.getKind()==Struct.Class || left.getKind()==Struct.Array || left==Tab.nullType || right==Tab.nullType) {
				
				 boolean validRelation=false;
				 if (expr.getRelop() instanceof RelopEqualEqual || expr.getRelop() instanceof RelopNotEqual) {
					 validRelation=true;
				 }
				 boolean oneNull=false;
				 if ((left.getKind()==Struct.Class && right==Tab.nullType)||(right.getKind()==Struct.Class && left==Tab.nullType)||(left.getKind()==Struct.Array && right==Tab.nullType)||(right.getKind()==Struct.Array && left==Tab.nullType)||(right==Tab.nullType && left==Tab.nullType))
					 oneNull=true;
				 
				if (((left.getKind()==Struct.Class && right.getKind()==Struct.Class)||(left.getKind()==Struct.Array && right.getKind()==Struct.Array)||(oneNull)) && (validRelation))
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
			else {
				report_info("Dodela vrednosti za " + assignment.getDesignator().obj.getName(), assignment);
//				if (assignment.getDesignator().obj.getType().getKind()==Struct.Class) {
//					currentNewClass=assignment.getDesignator().obj;
//				}
			}
			
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
			report_info("Pronadjen poziv promenljive " + MyPrint.printObjNode(designator.getDesignator().obj) + " na liniji " + designator.getLine(), null);
		}
	}
	public void visit(DesignatorStatementActPars designatorBrace) {
		Obj func = designatorBrace.getDesignator().obj;
    	if(Obj.Meth == func.getKind()){
			report_info("Pronadjen poziv funkcije "  +MyPrint.printObjNode(func)+   " sa "+designatorBrace.getActPars().struct.getMembersTable().numSymbols()+"/"+func.getLevel()+" argumenta na liniji " + designatorBrace.getLine(), null);
			
			HashTableDataStructure myArgs=new HashTableDataStructure();
			Collection<Obj> collection = func.getLocalSymbols();
			Iterator<Obj> i  = collection.iterator();
			
			int k=0;
			while (i.hasNext() && k<func.getLevel())
			{
			    Obj obj = i.next();
			   	//report_info((k+1)+". >>>>>>>>>>[POTREBAN] PARAMETAR FUNKCIJE tip["+obj.getType().getKind()+"] '"+func.getName()+"' : "+obj.getName(),null);

			   	if (k<func.getLevel()) {
			   		myArgs.insertKey(obj);
			   	}
			   	
			   	k++;
			}
			
			if (SemanticAnalyzer.equalTypeHash(myArgs, designatorBrace.getActPars().struct.getMembersTable())) {
			   	//report_info("YES >>>>>>>>>>DOBAR POZIV FUNKCIJE",null);
				
			}else {
				report_error("Greska na liniji " + designatorBrace.getLine()+" : funkcija " + func.getName() + " nema korektne argumente!", null);
			}
			
    	}else{
			report_error("Greska na liniji " + designatorBrace.getLine()+" : ime " + func.getName() + " nije funkcija!", null);
 
    	}
    	
		currentActParams=new HashTableDataStructure();

	}
	public void visit(DesignatorBraceActPars designatorBrace){
		Obj func = designatorBrace.getDesignator().obj;
    	if(Obj.Meth == func.getKind()){
			report_info("Pronadjen poziv funkcije " +MyPrint.printObjNode(func)+  " sa "+designatorBrace.getActPars().struct.getMembersTable().numSymbols()+"/"+func.getLevel()+" argumenta na liniji " + designatorBrace.getLine(), null);
			designatorBrace.struct = func.getType();
			
			HashTableDataStructure myArgs=new HashTableDataStructure();
			Collection<Obj> collection = func.getLocalSymbols();
			Iterator<Obj> i  = collection.iterator();
			
			int k=0;
			while (i.hasNext() && k<func.getLevel())
			{
			    Obj obj = i.next();
			   	//report_info((k+1)+". >>>>>>>>>>[POTREBAN] PARAMETAR FUNKCIJE tip["+obj.getType().getKind()+"] '"+func.getName()+"' : "+obj.getName(),null);

			   	if (k<func.getLevel()) {
			   		myArgs.insertKey(obj);
			   	}
			   	
			   	k++;
			}
			
			if (SemanticAnalyzer.equalTypeHash(myArgs, designatorBrace.getActPars().struct.getMembersTable())) {
			   	//report_info("YES >>>>>>>>>>DOBAR POZIV FUNKCIJE",null);
			}else {
				report_error("Greska na liniji " + designatorBrace.getLine()+" : funkcija " + func.getName() + " nema korektne argumente!", null);
			}
			

    	}else{
			report_error("Greska na liniji " + designatorBrace.getLine()+" : ime " + func.getName() + " nije funkcija!", null);
			designatorBrace.struct = Tab.noType;
    	}
    	
		currentActParams=new HashTableDataStructure();

	}
	public void visit(ActParsNode list) {
		list.struct=list.getActParsList().struct;
	}
	public void visit(NoActAprsNode noList) {
		noList.struct=new Struct(Struct.None);
		SymbolDataStructure table = noList.struct.getMembersTable();  	 
		if (classMethodCall!=null) {
		   	//report_info(" >>>>>>>>>>[EMPTY] PARAMETAR FUNKCIJE tip "+" ",noList);
		   	table.insertKey(classMethodCall);
		   	classMethodCall=null;
		}
	}
	public void visit(ActualParametres paramList) {
		paramList.struct=paramList.getActParsList().struct;
		SymbolDataStructure table =  paramList.struct.getMembersTable();
		
		
		int val = table.numSymbols();
		table.insertKey(new Obj(Obj.Var, "arg_"+val, paramList.getExpr().struct));
	   	//report_info((val+1)+". >>>>>>>>>>[PRONADJEN] PARAMETAR FUNKCIJE tip["+paramList.getExpr().struct.getKind()+"]",paramList);

	}
	public void visit(SingleActualParam singleParam) {
		singleParam.struct=new Struct(Struct.None);
		SymbolDataStructure table = singleParam.struct.getMembersTable();
		
    	 
		if (classMethodCall!=null) {
		   	//report_info(" >>>>>>>>>>[FIRST] PARAMETAR FUNKCIJE tip["+singleParam.getExpr().struct.getKind()+"]",singleParam);
		   	table.insertKey(classMethodCall);
		   	classMethodCall=null;
		}
		int val = table.numSymbols();
		table.insertKey(new Obj(Obj.Var, "arg_"+val, singleParam.getExpr().struct));
		
	   	

	}
	
	public void visit(Idents dotIdent) {
		Obj left = dotIdent.getIdentExprList().obj;
		if (left.getType().getKind()==Struct.Enum || left.getType().getKind()==Struct.Class || left.getType().getKind()==Struct.Interface) {
			
			if (dotIdent.getParent()!=null && (dotIdent.getParent().getParent().getClass()==DesignatorBraceActPars.class || dotIdent.getParent().getParent().getClass()==DesignatorStatementActPars.class)) {
				//System.err.println("poziv funkcije klase: "+left.getName());
				classMethodCall=left;
			}
			
			
			
			Collection<Obj> collection;
			if (left.getType().getKind()==Struct.Class || left.getType().getKind()==Struct.Interface) {
				 collection = left.getType().getMembers();
			}else {
				 collection = left.getLocalSymbols();
			}

			Iterator<Obj> i  = collection.iterator();
		   	//report_info(">>>>>>>>TEST NUM | "+left.getName()+"."+dotIdent.getI2()+"| lvl: "+left.getLevel()+" | size: "+collection.size(),null);

			Obj found=Tab.noObj;
			while (i.hasNext())
			{
			    Obj obj = i.next();
			   	//report_info(">>>>>>>>Moguce .dot prom/funkc | "+obj.getName(),null);
			   	if (obj.getName().equals(dotIdent.getI2())) {
			   		found=obj;
			   		report_info("Izabrano polje : ."+found.getName()+" | "+MyPrint.printObjNode(obj)+" |", null);
			   		break;
			   	}
			}
			if (found==Tab.noObj) {
				report_error("Greska na liniji " +  dotIdent.getParent().getLine()+ " : polje "+ dotIdent.getI2()+" nije deklarisano! (u okviru:"+left.getName()+")", null);
			}
			dotIdent.obj=found;
			
		}else {
			report_error("Greska na liniji " +  dotIdent.getParent().getLine()+ " : podrzani samo Enum i Class/Interface tipovi. ", null);
			dotIdent.obj=Tab.noObj;
		}
		
		memberLevel++;
	}
	public void visit(Exprs elem) {
		Obj left=elem.getIdentExprListArray().obj;
		if (left.getType().getKind()==Struct.Array) {
	 
			if (elem.getExpr().struct.getKind()==Struct.Int) {
								
				elem.obj=new Obj(Obj.Elem, "$elem", left.getType().getElemType());
				//System.err.println("napravljen $elem: "+elem.obj.getType().getKind());
			}else {
				if (left!=Tab.noObj) 
					report_error("Greska na liniji " +  elem.getParent().getLine()+ " : indeks(izraz) nije int! ", null);
				elem.obj=Tab.noObj;
			}
			
		}else {
			if (left!=Tab.noObj) 
				report_error("Greska na liniji " +  elem.getParent().getLine()+ " : polje "+ left.getName()+" nije niz! ", null);
			elem.obj=Tab.noObj; 
		}
		memberLevel++;
	}
	public void visit(IdentExprListArray array) {
		array.obj=array.getIdentExprList().obj;
	}
	public void visit(SingleIdentExpr base) {
		Obj obj = Tab.find(base.getBaseName());
		if(obj == Tab.noObj){
			report_error("Greska na liniji " + base.getLine()+ " : ime "+base.getBaseName()+" nije deklarisano! ", null);
		} 	

		base.obj=obj;
		memberLevel=0;
	}
	public void visit(DesignatorNode designatorNode){
  	 	//report_info("Rad sa promenljivom/funkc | "+designatorNode.getBaseName(),null);
//		Obj obj = Tab.find(designatorNode.getDesignatorIdent().getBaseName());
//		if(obj == Tab.noObj){
//			report_error("Greska na liniji " + designatorNode.getLine()+ " : ime "+designatorNode.getDesignatorIdent().getBaseName()+" nije deklarisano! ", null);
//		} 		
//		 
		Obj obj = designatorNode.getIdentExprList().obj;
		if(obj.getKind()==Obj.Type || obj.getKind()==Obj.Prog){
			report_error("Greska na liniji " + designatorNode.getLine()+ " : ime promenljive se poklapa sa tipom ili imenom programa ", null);
		}
		designatorNode.obj = obj;
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
			report_error("Greska na liniji " + newEmpty.getLine()+ " : tip nije klasa. ", null);
			newEmpty.struct=Tab.noType;
		}
		
		
	}
	public void visit (NewArray newArray) {
		Struct struct  = newArray.getExpr().struct;
		if (struct.getKind()==Struct.Int) {
			newArray.struct = new Struct(Struct.Array,newArray.getNewArrayType().struct);
		}else {
			report_error("Greska na liniji " + newArray.getLine()+ " : velicina niza mora biti int. ", null);
			newArray.struct=Tab.noType;
		}
		
	}
	public void visit(NewArrayType arrType) {
		arrType.struct=arrType.getType().struct;
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
    	nVars = Tab.currentScope.getnVars();
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
    public void visit(InterfaceIdent interIdent) {
    	Struct classStruct = new Struct(Struct.Interface);
    	interIdent.obj=Tab.insert(Obj.Type,  interIdent.getI1(), classStruct);
   	 	currentClass= interIdent.obj;
    	Tab.openScope();
    }
    public void visit(InterfaceDeclarationNode interDecl) {
    	Tab.chainLocalSymbols(interDecl.getInterfaceIdent().obj);
    	Tab.closeScope();
    	currentClass=null;
    	
    	report_info("Deklarisan novi interfejs: " + interDecl.getInterfaceIdent().getI1(), interDecl);

    }
    public void visit(ClassDeclIdent classIdent) {
    	Struct classStruct = new Struct(Struct.Class);
    	classIdent.obj=Tab.insert(Obj.Type, classIdent.getClassIdent(), classStruct);
   	 	currentClass=classIdent.obj;
    	Tab.openScope();
   	 	
   	 	//insert 'this' field
   	 	//Tab.insert(Obj.Fld, "this", classIdent.obj.getType());
   	 	
    }
    
    public void visit(ClassDeclarationWithoutMethods classDecl) {
    	Tab.chainLocalSymbols(classDecl.getClassDeclIdent().obj);
    	Tab.closeScope();
    	currentClass=null;

    	report_info("Deklarisana nova klasa: " + classDecl.getClassDeclIdent().getClassIdent(), classDecl);

    }
    public void visit(ClassDeclarationWithMethods classDecl) {
    	Tab.chainLocalSymbols(classDecl.getClassDeclIdent().obj);
    	Tab.closeScope();
    	currentClass=null;
    	
    	report_info("Deklarisana nova klasa: " + classDecl.getClassDeclIdent().getClassIdent(), classDecl);
    }
    public void visit(Extension ext) {
    	if (ext.getType().struct.getKind()==Struct.Class){
    		currentClass.getType().setElementType(ext.getType().struct);
    	}else {
			report_error("Greska na liniji " + ext.getLine()+ " : jedino se iz klase moze izvesti", null);
    	}
    	
    }
    public void visit(TypeListNode impl) {

    	if (impl.getType().struct.getKind()==Struct.Interface){
    		Collection<Struct> collection = currentClass.getType().getImplementedInterfaces();
    		Iterator<Struct> i  = collection.iterator();
    		while (i.hasNext())
    		{
    		    Struct struct = i.next();
    		   	//System.err.println(">>>>>>>>>>[IMPL] : "+struct.getKind());
    		    if (struct==impl.getType().struct) {
    	    		report_error("Greska na liniji " + impl.getLine()+ " : interfejs implementiran vise puta", null);
    	    		return;
    		    }
    		   	//k++;
    		}
    		//System.err.println("b>>>>>>>>>>[IMPL] : "+collection.size());
    		currentClass.getType().addImplementedInterface(impl.getType().struct);
    	}else {
    		report_error("Greska na liniji " + impl.getLine()+ " : jedino se interfejs moze implementirati", null);
    	}
    	
    }
    public void visit(SinglType impl) {
    	if (impl.getType().struct.getKind()==Struct.Interface){
    		Collection<Struct> collection = currentClass.getType().getImplementedInterfaces();
    		Iterator<Struct> i  = collection.iterator();
    		while (i.hasNext())
    		{
    		    Struct struct = i.next();
    		   	//System.err.println(">>>>>>>>>>[IMPL] : "+struct.getKind());
    		    if (struct==impl.getType().struct) {
    	    		report_error("Greska na liniji " + impl.getLine()+ " : interfejs implementiran vise puta", null);
    	    		return;
    		    }
    		   	//k++;
    		}
		   	//System.err.println("a>>>>>>>>>>[IMPL] : "+collection.size());
    		currentClass.getType().addImplementedInterface(impl.getType().struct);
    	}else {
    		report_error("Greska na liniji " + impl.getLine()+ " : jedino se interfejs moze implementirati", null);
    	}    }
    
    public void visit(InterfaceMethodIdent mStart) {
    	SymbolDataStructure table=currentClass.getType().getMembersTable();
    	Obj meth=Tab.noObj;
    	
    	if (table.searchKey(mStart.getI2()) == null) {
			//System.err.println(currentClass.getName()+"->inserted class fld: "+vnb.getVarName());
			report_info("Deklarisana metoda "+ mStart.getI2()+" u okviru interfejsa "+currentClass.getName()+"", mStart);
			meth=Tab.insert(Obj.Meth, mStart.getI2(), mStart.getReturnTypes().struct);
			table.insertKey(meth);
			currentMethod=meth;
		}else
		{
			currentMethod=meth;
			report_error("Greska na liniji " + mStart.getLine()+ " : polje "+ mStart.getI2()+" vec deklarisano! ", null);
		}
    	Tab.openScope();
    }
    public void visit(InterfaceMethodDeclarationWithParams mEnd) {
    	Tab.chainLocalSymbols(currentMethod);
    	Tab.closeScope();
    	currentMethod=null;
    }
    public void visit(MethodTypeName mTypeName){
    	if (mTypeName.getParent().getParent().getClass()==ClassMethodDeclarations.class) {
    		SymbolDataStructure table=currentClass.getType().getMembersTable();
    		Obj meth=Tab.noObj;
    		if (table.searchKey(mTypeName.getMethodName()) == null) {
    			//System.err.println(currentClass.getName()+"->inserted class fld: "+vnb.getVarName());
    			report_info("Deklarisana metoda "+ mTypeName.getMethodName()+" u okviru klase "+currentClass.getName()+"", mTypeName);
    			meth=Tab.insert(Obj.Meth, mTypeName.getMethodName(), mTypeName.getReturnTypes().struct);
    			
    			table.insertKey(meth);
    			currentMethod=meth;
    			mTypeName.obj=currentMethod;
    			Tab.openScope();
    			Tab.insert(Obj.Var, "this", currentClass.getType());
    		 
    		}else
    		{
    			currentMethod=meth;
    			mTypeName.obj=currentMethod;
    			Tab.openScope();
    			report_error("Greska na liniji " + mTypeName.getLine()+ " : polje "+mTypeName.getMethodName()+" vec deklarisano! ", null);
    		}
    	}else {
    		//Provera da li vec postoji
        	Obj obj = Tab.currentScope().findSymbol(mTypeName.getMethodName());
        	if(obj != null){
    			report_error("Greska na liniji " + mTypeName.getLine()+ " : ime funkcije : "+mTypeName.getMethodName()+" vec deklarisano! ", null);
    		}else {
    	    	//Ako ne postoji
    			report_info("Definisana funkcija "+mTypeName.getMethodName(),mTypeName);
    		}
    		currentMethod=Tab.insert(Obj.Meth, mTypeName.getMethodName(),mTypeName.getReturnTypes().struct);				
    		mTypeName.obj=currentMethod;
    		Tab.openScope();
    	}
    	//mTypeName.obj=currentMethod;
		//Tab.openScope();
		//Tab.insert(Obj.Fld, "this", currentClass.getType());
   }
   public void visit(MethodLBRACE lbrace) {
	   SyntaxNode parent = lbrace.getParent();
	   
	   Tab.chainLocalSymbols(currentMethod);
	   //System.err.println("chained local symbols for "+currentMethod.getName()+"\n");
	   
   }
   public void visit(MethodDecl mDecl){
	   //if (currentMethod==null) return;
	   
	   //report_info("Method >> scope end | " + mDecl.getMethodTypeName().getMethodName()+"| level=["+currentMethod.getLevel()+"]",null);
	   if(!returnFound && currentMethod.getType() != Tab.noType){
			report_error("Greska na liniji " + mDecl.getLine() + ": funkcija "+MyPrint.printObjNode(currentMethod)+" " + currentMethod.getName() + " nema return iskaz!", null);
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
		    	enumConst.setAdr(enumVal.getN1());
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
    	
    	//For Class
    	if (vnb.getParent().getClass()==ClassVariables.class || vnb.getParent().getClass()==SingleClassVariable.class)
    	{
    		SymbolDataStructure table=currentClass.getType().getMembersTable();
    		if (table.searchKey(vnb.getVarName()) == null) {
    			//System.err.println(currentClass.getName()+"->inserted class fld: "+vnb.getVarName());
    			report_info("Deklarisano single polje "+ vnb.getVarName(), vnb);
    			Obj var=Tab.insert(Obj.Fld, vnb.getVarName(), currentVarType.struct);
    			table.insertKey(var);
    			currentClass.getType().setMembers(currentClass.getType().getMembersTable());
    		}else
    		{
    			report_error("Greska na liniji " + vnb.getLine()+ " : polje "+vnb.getVarName()+" vec deklarisano! ", null);
    		}
    	}else {
       	
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
    	
    	
    	
    	
    }
    public void visit(VariableBracket vb) {
    	//For Class
    	if (vb.getParent().getClass()==ClassVariables.class || vb.getParent().getClass()==SingleClassVariable.class)
    	{
    		SymbolDataStructure table=currentClass.getType().getMembersTable();
    		if (table.searchKey(vb.getVarName()) == null) {
    			//System.err.println(currentClass.getName()+"->inserted class fld: "+vnb.getVarName());
    			report_info("Deklarisanp array polje "+ vb.getVarName(), vb);
    			Struct arrayStruct = new Struct(Struct.Array,currentVarType.struct);
    			Obj var=Tab.insert(Obj.Fld, vb.getVarName(), arrayStruct);
    			table.insertKey(var);
    			currentClass.getType().setMembers(currentClass.getType().getMembersTable());
    		}else
    		{
    			report_error("Greska na liniji " + vb.getLine()+ " : polje "+vb.getVarName()+" vec deklarisano! ", null);
    		}
    	}
    	else{
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
    }
    //Constant
    public void visit(IdentEqualConstantNode c) {
    	//Provera da li vec postoji
    	Obj obj = Tab.currentScope().findSymbol(c.getConstName());
    	if(obj != null){
			report_error("Greska na liniji " + c.getLine()+ " : ime "+c.getConstName()+" vec deklarisano! ", null);
		}else {
			if (currentVarType.struct!=c.getConstant().obj.getType()) {
				System.err.println("type left:"+currentVarType.struct.getKind());
				System.err.println("type right:"+c.getConstant().obj.getType().getKind());
				report_error("Greska na liniji " + c.getLine()+ " : "+c.getConstName()+" nije dobrog tipa! ", null);
			}else {
				report_info("Definisana konstanta ("+c.getConstant().obj.getAdr()+")"+ c.getConstName(), c);
		    	Obj var=Tab.insert(Obj.Con, c.getConstName(), currentVarType.struct);
		    	var.setAdr(c.getConstant().obj.getAdr());
			}
	    	
	    	
		}
    } 
    public void visit(NumberNode number) {
    	number.obj= new Obj(Obj.Con, "$con", Tab.intType);
    	number.obj.setAdr(number.getNumber());
    	 
    }
	public void visit(CharNode chr) {
		chr.obj= new Obj(Obj.Con, "$con", Tab.charType);
    	chr.obj.setAdr(chr.getChr());
	}
	public void visit(BooleanNode b) {
		b.obj= new Obj(Obj.Con, "$con", TabExtra.boolType);
		b.obj.setAdr(b.getBo());
    	
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
    public void visit(IfStatementThen stat) {
		stat.obj=stat.getStatement().obj;
 
	}
	public void visit(IfStatementElse stat) {
		stat.obj=stat.getStatement().obj;
 
	}
    public static boolean equalTypeHash(SymbolDataStructure h1, SymbolDataStructure h2) {
		if (h1 == h2) 
			return true;
		if (h1!=null && h1.numSymbols()==0 && h2==null)
			return true;
		if (h2!=null && h2.numSymbols()==0 && h1==null)
			return true;
		if (h1==null && h2==null)
			return true;
		
		if (h1.numSymbols()==0 && h2.numSymbols()==0)
			return true;
		
		
		if (h1.numSymbols() == h2.numSymbols()) {
			Collection<Obj> h1Obj = h1.symbols(), h2Obj = h2.symbols();
			Iterator<Obj> itH1 = h1Obj.iterator(), itH2 = h2Obj.iterator();
			
			while (itH1.hasNext() && itH2.hasNext()) {
				Obj obj1 = itH1.next();
				Obj obj2 = itH2.next();
				if (obj1.getType().getKind()==Struct.Array && obj2.getType()==Tab.nullType)
					return true;
				if (obj1.getType().getKind()==Struct.Array && (obj1.getType().getElemType()!=obj2.getType().getElemType() && obj1.getType().getElemType()!=Tab.noType))
					return false;
				if (!(obj1.getType().getKind() == obj2.getType().getKind()))
					return false;
//				if (!obj1.getType().compatibleWith(obj2.getType())) {
//					return false;
//				}
			}
			return true;
		}
		else 
			return false;
	}
    public static HashTableDataStructure CollectionToHash(Collection<Obj> collection) {
    	HashTableDataStructure table = new HashTableDataStructure();
		Iterator<Obj> i  = collection.iterator();
		
		//int k=0;
		while (i.hasNext())
		{
		    Obj obj = i.next();
		   	System.err.println(">>>>>>>>>>[INTO HASH] : "+obj.getName());
		    table.insertKey(new Obj(Obj.Var,obj.getName(),obj.getType()));
		   	//k++;
		}
		System.err.println(">>>>>>>>>>[size HASH] : "+table.numSymbols());
    	return table;
    	
    }
    public static boolean DoesImplementAll(Struct classStruct) {
     
		 System.err.println(">>>>>>>>>>[NUM I] : "+classStruct.getImplementedInterfaces().size());
		 System.err.println(">>>>>>>>>>[TYPE C] : "+classStruct.getElemType());

    	SymbolDataStructure real=classStruct.getMembersTable();
    	Collection<Struct> interfaces=classStruct.getImplementedInterfaces();
    	for(int i=0;i<interfaces.size();i++) {
    		Iterator<Struct> iterator = interfaces.iterator();
    		while(iterator.hasNext()) {
    			 Struct struct = iterator.next();
    			 if (has(real,struct) ) {
    				 System.err.println(">>>>>>>>>>[INTERFACE MATCH] : "+struct.getKind());
    			 }else {
 					 System.err.println(">>>>>>>>>>[INTERFACE not MATCH] : "+struct.getKind());

    				 return false;
    			 }
    		}
    	}
    	if (classStruct.getElemType()==null) return true;
   	
    	Collection<Obj> klase=classStruct.getElemType().getMembers();
    	Iterator<Obj> iterator = klase.iterator();
		while(iterator.hasNext()) {
			 Obj obj = iterator.next();
			 if (obj.getKind()!=Obj.Meth) continue;
			 if (has(real,obj.getType())) {
					System.err.println(">>>>>>>>>>[CLASS MATCH] : "+obj.getType().getKind());
			 }else {
					System.err.println(">>>>>>>>>>[CLASS NOT MATCH] : "+obj.getType().getKind());
				 return false;
			 }
		}
    	return true;
    }
    public static boolean has(SymbolDataStructure hash, Struct target) {
    	Collection<Obj> collection=hash.symbols();

    	Iterator<Obj> iterator = collection.iterator();
		while(iterator.hasNext()) {
			 Struct struct = iterator.next().getType();
			 if (target==(struct)) {
				 return true;
			 }
		}
		return false;
    }
}

class TabExtra{
	public static final Struct boolType = new Struct(Struct.Bool),
							   enumType = new Struct(Struct.Enum);
	
	public static void init() {
		Tab.currentScope().addToLocals(new Obj(Obj.Type, "bool", boolType));
		Tab.currentScope().addToLocals(new Obj(Obj.Type, "enum", enumType));
	}
	
	public static void tsdump() {
		Tab.dump(new MyDump());
	}
	
}
