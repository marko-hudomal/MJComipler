// generated with ast extension for cup
// version 0.8
// 1/1/2019 20:40:49


package rs.ac.bg.etf.pp1.ast;

public class ErrorFormParam extends FormalParamDecl {

    public ErrorFormParam () {
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
        buffer.append("ErrorFormParam(\n");

        buffer.append(tab);
        buffer.append(") [ErrorFormParam]");
        return buffer.toString();
    }
}