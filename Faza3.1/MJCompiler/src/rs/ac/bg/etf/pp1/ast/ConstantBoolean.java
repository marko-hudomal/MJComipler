// generated with ast extension for cup
// version 0.8
// 1/1/2019 7:50:31


package rs.ac.bg.etf.pp1.ast;

public class ConstantBoolean extends Factor {

    public ConstantBoolean () {
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
        buffer.append("ConstantBoolean(\n");

        buffer.append(tab);
        buffer.append(") [ConstantBoolean]");
        return buffer.toString();
    }
}
