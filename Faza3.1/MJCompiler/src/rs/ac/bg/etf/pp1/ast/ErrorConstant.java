// generated with ast extension for cup
// version 0.8
// 1/1/2019 7:50:30


package rs.ac.bg.etf.pp1.ast;

public class ErrorConstant extends IdentEqualConstant {

    public ErrorConstant () {
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
        buffer.append("ErrorConstant(\n");

        buffer.append(tab);
        buffer.append(") [ErrorConstant]");
        return buffer.toString();
    }
}
