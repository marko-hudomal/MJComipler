// generated with ast extension for cup
// version 0.8
// 4/1/2019 5:0:20


package rs.ac.bg.etf.pp1.ast;

public class NoVarDeclaration extends VarDeclList {

    public NoVarDeclaration () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NoVarDeclaration(\n");

        buffer.append(tab);
        buffer.append(") [NoVarDeclaration]");
        return buffer.toString();
    }
}
