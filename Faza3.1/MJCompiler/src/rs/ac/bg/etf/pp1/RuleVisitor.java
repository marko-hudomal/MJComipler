package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.ast.*;

public class RuleVisitor extends VisitorAdaptor{

	int printCallCount = 0;
	int varDeclCount = 0;
	
	Logger log = Logger.getLogger(getClass());

//	public void visit(VariableNoBracket var){
//		log.info(var.getI1() + "; "+ var.getLine());
//	}
//	public void visit(IdentEqualConstantNode iec){
//
//		log.info(iec.getI1() + ":"+iec.getConstant().getClass().getName());
//		
//		if (iec.getConstant() instanceof NumberNode) {
//			NumberNode nn = (NumberNode) iec.getConstant();
//			log.info("Ovo je broj: " + nn.getN1());
//		}
//	}
//	public void visit(Program program){
//		log.info(program.getClass().getName());
//	}
//     

}
