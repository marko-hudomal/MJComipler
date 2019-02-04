// generated with ast extension for cup
// version 0.8
// 1/1/2019 7:50:31


package rs.ac.bg.etf.pp1.ast;

public class ConstantCharacter extends Factor {

    private Character C1;

    public ConstantCharacter (Character C1) {
        this.C1=C1;
    }

    public Character getC1() {
        return C1;
    }

    public void setC1(Character C1) {
        this.C1=C1;
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
        buffer.append("ConstantCharacter(\n");

        buffer.append(" "+tab+C1);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstantCharacter]");
        return buffer.toString();
    }
}
