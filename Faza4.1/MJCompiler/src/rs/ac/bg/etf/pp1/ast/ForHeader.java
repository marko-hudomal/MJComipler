// generated with ast extension for cup
// version 0.8
// 5/1/2019 5:50:32


package rs.ac.bg.etf.pp1.ast;

public class ForHeader implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private ForStatement1 ForStatement1;
    private ForCondition ForCondition;
    private ForStatement2 ForStatement2;

    public ForHeader (ForStatement1 ForStatement1, ForCondition ForCondition, ForStatement2 ForStatement2) {
        this.ForStatement1=ForStatement1;
        if(ForStatement1!=null) ForStatement1.setParent(this);
        this.ForCondition=ForCondition;
        if(ForCondition!=null) ForCondition.setParent(this);
        this.ForStatement2=ForStatement2;
        if(ForStatement2!=null) ForStatement2.setParent(this);
    }

    public ForStatement1 getForStatement1() {
        return ForStatement1;
    }

    public void setForStatement1(ForStatement1 ForStatement1) {
        this.ForStatement1=ForStatement1;
    }

    public ForCondition getForCondition() {
        return ForCondition;
    }

    public void setForCondition(ForCondition ForCondition) {
        this.ForCondition=ForCondition;
    }

    public ForStatement2 getForStatement2() {
        return ForStatement2;
    }

    public void setForStatement2(ForStatement2 ForStatement2) {
        this.ForStatement2=ForStatement2;
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
        if(ForStatement1!=null) ForStatement1.accept(visitor);
        if(ForCondition!=null) ForCondition.accept(visitor);
        if(ForStatement2!=null) ForStatement2.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForStatement1!=null) ForStatement1.traverseTopDown(visitor);
        if(ForCondition!=null) ForCondition.traverseTopDown(visitor);
        if(ForStatement2!=null) ForStatement2.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForStatement1!=null) ForStatement1.traverseBottomUp(visitor);
        if(ForCondition!=null) ForCondition.traverseBottomUp(visitor);
        if(ForStatement2!=null) ForStatement2.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForHeader(\n");

        if(ForStatement1!=null)
            buffer.append(ForStatement1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForCondition!=null)
            buffer.append(ForCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForStatement2!=null)
            buffer.append(ForStatement2.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForHeader]");
        return buffer.toString();
    }
}
