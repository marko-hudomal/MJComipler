// generated with ast extension for cup
// version 0.8
// 7/0/2019 4:14:13


package rs.ac.bg.etf.pp1.ast;

public class NoSingleMinus extends MinusOrNull {

    public NoSingleMinus () {
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
        buffer.append("NoSingleMinus(\n");

        buffer.append(tab);
        buffer.append(") [NoSingleMinus]");
        return buffer.toString();
    }
}