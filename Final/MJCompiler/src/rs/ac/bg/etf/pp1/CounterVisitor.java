package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;

public class CounterVisitor extends VisitorAdaptor {
	protected int count;
	
	public int getCount(){
		return count;
	}
	
	
	
	public static class FormParamCounter extends CounterVisitor{
		
		public void visit(FormalParamDeclarationBrackets param){
			count++;
			//System.err.println("->>>>>>>>"+param.getParamName());
		}
		public void visit(FormalParamDeclarationNoBrackets param) {
			count++;
			//System.err.println("->>>>>>>>"+param.getParamName());
		}
	}
	
	public static class VarCounter extends CounterVisitor{
		
		public void visit(VariableBracket varDecl){
			count++;
		}
		
		public void visit(VariableNoBracket varDecl){
			count++;
		}
		
	}
}
