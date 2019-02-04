// generated with ast extension for cup
// version 0.8
// 29/0/2019 22:39:45


package rs.ac.bg.etf.pp1.ast;

public class ReturnStatement extends Statement {

    private ExprOrNull ExprOrNull;

    public ReturnStatement (ExprOrNull ExprOrNull) {
        this.ExprOrNull=ExprOrNull;
        if(ExprOrNull!=null) ExprOrNull.setParent(this);
    }

    public ExprOrNull getExprOrNull() {
        return ExprOrNull;
    }

    public void setExprOrNull(ExprOrNull ExprOrNull) {
        this.ExprOrNull=ExprOrNull;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExprOrNull!=null) ExprOrNull.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprOrNull!=null) ExprOrNull.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprOrNull!=null) ExprOrNull.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ReturnStatement(\n");

        if(ExprOrNull!=null)
            buffer.append(ExprOrNull.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ReturnStatement]");
        return buffer.toString();
    }
}
