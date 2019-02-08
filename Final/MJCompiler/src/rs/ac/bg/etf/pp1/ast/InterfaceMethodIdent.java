// generated with ast extension for cup
// version 0.8
// 6/1/2019 3:51:16


package rs.ac.bg.etf.pp1.ast;

public class InterfaceMethodIdent implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private ReturnTypes ReturnTypes;
    private String I2;

    public InterfaceMethodIdent (ReturnTypes ReturnTypes, String I2) {
        this.ReturnTypes=ReturnTypes;
        if(ReturnTypes!=null) ReturnTypes.setParent(this);
        this.I2=I2;
    }

    public ReturnTypes getReturnTypes() {
        return ReturnTypes;
    }

    public void setReturnTypes(ReturnTypes ReturnTypes) {
        this.ReturnTypes=ReturnTypes;
    }

    public String getI2() {
        return I2;
    }

    public void setI2(String I2) {
        this.I2=I2;
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
        if(ReturnTypes!=null) ReturnTypes.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ReturnTypes!=null) ReturnTypes.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ReturnTypes!=null) ReturnTypes.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("InterfaceMethodIdent(\n");

        if(ReturnTypes!=null)
            buffer.append(ReturnTypes.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+I2);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [InterfaceMethodIdent]");
        return buffer.toString();
    }
}
