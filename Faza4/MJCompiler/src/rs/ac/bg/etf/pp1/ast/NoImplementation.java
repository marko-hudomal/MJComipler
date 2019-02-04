// generated with ast extension for cup
// version 0.8
// 4/1/2019 5:0:20


package rs.ac.bg.etf.pp1.ast;

public class NoImplementation extends Impl {

    public NoImplementation () {
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
        buffer.append("NoImplementation(\n");

        buffer.append(tab);
        buffer.append(") [NoImplementation]");
        return buffer.toString();
    }
}
