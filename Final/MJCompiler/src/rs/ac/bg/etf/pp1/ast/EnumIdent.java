// generated with ast extension for cup
// version 0.8
// 6/1/2019 3:51:16


package rs.ac.bg.etf.pp1.ast;

public class EnumIdent implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private String enumIdent;

    public EnumIdent (String enumIdent) {
        this.enumIdent=enumIdent;
    }

    public String getEnumIdent() {
        return enumIdent;
    }

    public void setEnumIdent(String enumIdent) {
        this.enumIdent=enumIdent;
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
        buffer.append("EnumIdent(\n");

        buffer.append(" "+tab+enumIdent);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnumIdent]");
        return buffer.toString();
    }
}
