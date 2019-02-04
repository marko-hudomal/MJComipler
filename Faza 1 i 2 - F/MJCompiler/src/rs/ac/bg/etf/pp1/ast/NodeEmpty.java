// generated with ast extension for cup
// version 0.8
// 29/0/2019 22:39:45


package rs.ac.bg.etf.pp1.ast;

public class NodeEmpty extends NewNode {

    public NodeEmpty () {
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
        buffer.append("NodeEmpty(\n");

        buffer.append(tab);
        buffer.append(") [NodeEmpty]");
        return buffer.toString();
    }
}
