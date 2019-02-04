// generated with ast extension for cup
// version 0.8
// 4/1/2019 5:0:20


package rs.ac.bg.etf.pp1.ast;

public class ForCondition implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private ConditionOrNull ConditionOrNull;

    public ForCondition (ConditionOrNull ConditionOrNull) {
        this.ConditionOrNull=ConditionOrNull;
        if(ConditionOrNull!=null) ConditionOrNull.setParent(this);
    }

    public ConditionOrNull getConditionOrNull() {
        return ConditionOrNull;
    }

    public void setConditionOrNull(ConditionOrNull ConditionOrNull) {
        this.ConditionOrNull=ConditionOrNull;
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
        if(ConditionOrNull!=null) ConditionOrNull.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConditionOrNull!=null) ConditionOrNull.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConditionOrNull!=null) ConditionOrNull.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForCondition(\n");

        if(ConditionOrNull!=null)
            buffer.append(ConditionOrNull.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForCondition]");
        return buffer.toString();
    }
}
