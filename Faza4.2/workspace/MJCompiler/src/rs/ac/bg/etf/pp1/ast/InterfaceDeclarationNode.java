// generated with ast extension for cup
// version 0.8
// 6/1/2019 3:51:16


package rs.ac.bg.etf.pp1.ast;

public class InterfaceDeclarationNode extends InterfaceDecl {

    private InterfaceIdent InterfaceIdent;
    private InterfaceMethodDeclList InterfaceMethodDeclList;

    public InterfaceDeclarationNode (InterfaceIdent InterfaceIdent, InterfaceMethodDeclList InterfaceMethodDeclList) {
        this.InterfaceIdent=InterfaceIdent;
        if(InterfaceIdent!=null) InterfaceIdent.setParent(this);
        this.InterfaceMethodDeclList=InterfaceMethodDeclList;
        if(InterfaceMethodDeclList!=null) InterfaceMethodDeclList.setParent(this);
    }

    public InterfaceIdent getInterfaceIdent() {
        return InterfaceIdent;
    }

    public void setInterfaceIdent(InterfaceIdent InterfaceIdent) {
        this.InterfaceIdent=InterfaceIdent;
    }

    public InterfaceMethodDeclList getInterfaceMethodDeclList() {
        return InterfaceMethodDeclList;
    }

    public void setInterfaceMethodDeclList(InterfaceMethodDeclList InterfaceMethodDeclList) {
        this.InterfaceMethodDeclList=InterfaceMethodDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(InterfaceIdent!=null) InterfaceIdent.accept(visitor);
        if(InterfaceMethodDeclList!=null) InterfaceMethodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(InterfaceIdent!=null) InterfaceIdent.traverseTopDown(visitor);
        if(InterfaceMethodDeclList!=null) InterfaceMethodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(InterfaceIdent!=null) InterfaceIdent.traverseBottomUp(visitor);
        if(InterfaceMethodDeclList!=null) InterfaceMethodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("InterfaceDeclarationNode(\n");

        if(InterfaceIdent!=null)
            buffer.append(InterfaceIdent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(InterfaceMethodDeclList!=null)
            buffer.append(InterfaceMethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [InterfaceDeclarationNode]");
        return buffer.toString();
    }
}
