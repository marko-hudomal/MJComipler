// generated with ast extension for cup
// version 0.8
// 1/1/2019 7:50:31


package rs.ac.bg.etf.pp1.ast;

public class AddopMul extends Mulop {

    public AddopMul () {
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
        buffer.append("AddopMul(\n");

        buffer.append(tab);
        buffer.append(") [AddopMul]");
        return buffer.toString();
    }
}