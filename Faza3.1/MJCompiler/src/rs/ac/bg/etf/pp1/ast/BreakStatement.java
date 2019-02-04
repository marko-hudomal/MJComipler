// generated with ast extension for cup
// version 0.8
// 1/1/2019 7:50:30


package rs.ac.bg.etf.pp1.ast;

public class BreakStatement extends Statement {

    private Semi Semi;

    public BreakStatement (Semi Semi) {
        this.Semi=Semi;
        if(Semi!=null) Semi.setParent(this);
    }

    public Semi getSemi() {
        return Semi;
    }

    public void setSemi(Semi Semi) {
        this.Semi=Semi;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Semi!=null) Semi.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Semi!=null) Semi.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Semi!=null) Semi.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("BreakStatement(\n");

        if(Semi!=null)
            buffer.append(Semi.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BreakStatement]");
        return buffer.toString();
    }
}
