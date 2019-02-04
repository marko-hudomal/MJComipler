// generated with ast extension for cup
// version 0.8
// 29/0/2019 22:39:45


package rs.ac.bg.etf.pp1.ast;

public class DesignatorNode extends Designator {

    private String I1;
    private IdentExprList IdentExprList;

    public DesignatorNode (String I1, IdentExprList IdentExprList) {
        this.I1=I1;
        this.IdentExprList=IdentExprList;
        if(IdentExprList!=null) IdentExprList.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public IdentExprList getIdentExprList() {
        return IdentExprList;
    }

    public void setIdentExprList(IdentExprList IdentExprList) {
        this.IdentExprList=IdentExprList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IdentExprList!=null) IdentExprList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IdentExprList!=null) IdentExprList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IdentExprList!=null) IdentExprList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorNode(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(IdentExprList!=null)
            buffer.append(IdentExprList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorNode]");
        return buffer.toString();
    }
}
