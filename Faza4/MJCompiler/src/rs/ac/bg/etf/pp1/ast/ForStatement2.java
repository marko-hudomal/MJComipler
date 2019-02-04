// generated with ast extension for cup
// version 0.8
// 4/1/2019 5:0:20


package rs.ac.bg.etf.pp1.ast;

public class ForStatement2 implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private DesignatorStatementOrNull DesignatorStatementOrNull;

    public ForStatement2 (DesignatorStatementOrNull DesignatorStatementOrNull) {
        this.DesignatorStatementOrNull=DesignatorStatementOrNull;
        if(DesignatorStatementOrNull!=null) DesignatorStatementOrNull.setParent(this);
    }

    public DesignatorStatementOrNull getDesignatorStatementOrNull() {
        return DesignatorStatementOrNull;
    }

    public void setDesignatorStatementOrNull(DesignatorStatementOrNull DesignatorStatementOrNull) {
        this.DesignatorStatementOrNull=DesignatorStatementOrNull;
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
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorStatementOrNull!=null) DesignatorStatementOrNull.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorStatementOrNull!=null) DesignatorStatementOrNull.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForStatement2(\n");

        if(DesignatorStatementOrNull!=null)
            buffer.append(DesignatorStatementOrNull.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForStatement2]");
        return buffer.toString();
    }
}
