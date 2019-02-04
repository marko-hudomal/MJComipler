// generated with ast extension for cup
// version 0.8
// 1/1/2019 20:40:49


package rs.ac.bg.etf.pp1.ast;

public class ForHeader implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private DesignatorStatementOrNull DesignatorStatementOrNull;
    private ConditionOrNull ConditionOrNull;
    private DesignatorStatementOrNull DesignatorStatementOrNull1;

    public ForHeader (DesignatorStatementOrNull DesignatorStatementOrNull, ConditionOrNull ConditionOrNull, DesignatorStatementOrNull DesignatorStatementOrNull1) {
        this.DesignatorStatementOrNull=DesignatorStatementOrNull;
        if(DesignatorStatementOrNull!=null) DesignatorStatementOrNull.setParent(this);
        this.ConditionOrNull=ConditionOrNull;
        if(ConditionOrNull!=null) ConditionOrNull.setParent(this);
        this.DesignatorStatementOrNull1=DesignatorStatementOrNull1;
        if(DesignatorStatementOrNull1!=null) DesignatorStatementOrNull1.setParent(this);
    }

    public DesignatorStatementOrNull getDesignatorStatementOrNull() {
        return DesignatorStatementOrNull;
    }

    public void setDesignatorStatementOrNull(DesignatorStatementOrNull DesignatorStatementOrNull) {
        this.DesignatorStatementOrNull=DesignatorStatementOrNull;
    }

    public ConditionOrNull getConditionOrNull() {
        return ConditionOrNull;
    }

    public void setConditionOrNull(ConditionOrNull ConditionOrNull) {
        this.ConditionOrNull=ConditionOrNull;
    }

    public DesignatorStatementOrNull getDesignatorStatementOrNull1() {
        return DesignatorStatementOrNull1;
    }

    public void setDesignatorStatementOrNull1(DesignatorStatementOrNull DesignatorStatementOrNull1) {
        this.DesignatorStatementOrNull1=DesignatorStatementOrNull1;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorStatementOrNull!=null) DesignatorStatementOrNull.accept(visitor);
        if(ConditionOrNull!=null) ConditionOrNull.accept(visitor);
        if(DesignatorStatementOrNull1!=null) DesignatorStatementOrNull1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorStatementOrNull!=null) DesignatorStatementOrNull.traverseTopDown(visitor);
        if(ConditionOrNull!=null) ConditionOrNull.traverseTopDown(visitor);
        if(DesignatorStatementOrNull1!=null) DesignatorStatementOrNull1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorStatementOrNull!=null) DesignatorStatementOrNull.traverseBottomUp(visitor);
        if(ConditionOrNull!=null) ConditionOrNull.traverseBottomUp(visitor);
        if(DesignatorStatementOrNull1!=null) DesignatorStatementOrNull1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForHeader(\n");

        if(DesignatorStatementOrNull!=null)
            buffer.append(DesignatorStatementOrNull.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditionOrNull!=null)
            buffer.append(ConditionOrNull.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorStatementOrNull1!=null)
            buffer.append(DesignatorStatementOrNull1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForHeader]");
        return buffer.toString();
    }
}
