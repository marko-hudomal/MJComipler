// generated with ast extension for cup
// version 0.8
// 4/1/2019 5:0:20


package rs.ac.bg.etf.pp1.ast;

public class MethodTypeName implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private ReturnTypes ReturnTypes;
    private String methodName;

    public MethodTypeName (ReturnTypes ReturnTypes, String methodName) {
        this.ReturnTypes=ReturnTypes;
        if(ReturnTypes!=null) ReturnTypes.setParent(this);
        this.methodName=methodName;
    }

    public ReturnTypes getReturnTypes() {
        return ReturnTypes;
    }

    public void setReturnTypes(ReturnTypes ReturnTypes) {
        this.ReturnTypes=ReturnTypes;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName=methodName;
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
        buffer.append("MethodTypeName(\n");

        if(ReturnTypes!=null)
            buffer.append(ReturnTypes.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+methodName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodTypeName]");
        return buffer.toString();
    }
}
