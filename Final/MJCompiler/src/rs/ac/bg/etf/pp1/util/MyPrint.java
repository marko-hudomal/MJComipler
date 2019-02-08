package rs.ac.bg.etf.pp1.util;

import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class MyPrint {
 
	
	public static String printObjNode(Obj objToVisit) {
		StringBuilder output = new StringBuilder();
		output.append("[");
		switch (objToVisit.getKind()) {
		case Obj.Con:  output.append("Con "); break;
		case Obj.Var:  output.append("Var "); break;
		case Obj.Type: output.append("Type "); break;
		case Obj.Meth: output.append("Meth "); break;
		case Obj.Fld:  output.append("Fld "); break;
		case Obj.Prog: output.append("Prog "); break;
		}
		
		output.append(objToVisit.getName());
		output.append(": ");		
		output.append(visitStructNode(objToVisit.getType()));
		output.append(", ");
		output.append(objToVisit.getAdr());
		output.append(", ");
		output.append(objToVisit.getLevel() + " ");
 

		output.append("]");
		return output.toString();
	}
	public static String visitStructNode(Struct structToVisit) {
		StringBuilder output = new StringBuilder();
		switch (structToVisit.getKind()) {
		case Struct.None:
			output.append("notype");
			break;
		case Struct.Int:
			output.append("int");
			break;
		case Struct.Char:
			output.append("char");
			break;
		case Struct.Bool:
			output.append("bool");
			break;
		case Struct.Enum:
			output.append("enum");
			break;	
		case Struct.Array:
			output.append("Arr of ");
			
			switch (structToVisit.getElemType().getKind()) {
			case Struct.None:
				output.append("notype");
				break;
			case Struct.Int:
				output.append("int");
				break;
			case Struct.Char:
				output.append("char");
				break;
			case Struct.Class:
				output.append("Class");
				break;
			}
			break;
		case Struct.Class:
			output.append("Class [");
			output.append("]");
			break;
		}
		return output.toString();
	}
}
