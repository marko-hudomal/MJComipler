// generated with ast extension for cup
// version 0.8
// 5/1/2019 5:50:32


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclarationWithMethods extends ClassDecl {

    private ClassDeclIdent ClassDeclIdent;
    private Ext Ext;
    private Impl Impl;
    private ClassVarDeclList ClassVarDeclList;
    private MethodDeclList MethodDeclList;

    public ClassDeclarationWithMethods (ClassDeclIdent ClassDeclIdent, Ext Ext, Impl Impl, ClassVarDeclList ClassVarDeclList, MethodDeclList MethodDeclList) {
        this.ClassDeclIdent=ClassDeclIdent;
        if(ClassDeclIdent!=null) ClassDeclIdent.setParent(this);
        this.Ext=Ext;
        if(Ext!=null) Ext.setParent(this);
        this.Impl=Impl;
        if(Impl!=null) Impl.setParent(this);
        this.ClassVarDeclList=ClassVarDeclList;
        if(ClassVarDeclList!=null) ClassVarDeclList.setParent(this);
        this.MethodDeclList=MethodDeclList;
        if(MethodDeclList!=null) MethodDeclList.setParent(this);
    }

    public ClassDeclIdent getClassDeclIdent() {
        return ClassDeclIdent;
    }

    public void setClassDeclIdent(ClassDeclIdent ClassDeclIdent) {
        this.ClassDeclIdent=ClassDeclIdent;
    }

    public Ext getExt() {
        return Ext;
    }

    public void setExt(Ext Ext) {
        this.Ext=Ext;
    }

    public Impl getImpl() {
        return Impl;
    }

    public void setImpl(Impl Impl) {
        this.Impl=Impl;
    }

    public ClassVarDeclList getClassVarDeclList() {
        return ClassVarDeclList;
    }

    public void setClassVarDeclList(ClassVarDeclList ClassVarDeclList) {
        this.ClassVarDeclList=ClassVarDeclList;
    }

    public MethodDeclList getMethodDeclList() {
        return MethodDeclList;
    }

    public void setMethodDeclList(MethodDeclList MethodDeclList) {
        this.MethodDeclList=MethodDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassDeclIdent!=null) ClassDeclIdent.accept(visitor);
        if(Ext!=null) Ext.accept(visitor);
        if(Impl!=null) Impl.accept(visitor);
        if(ClassVarDeclList!=null) ClassVarDeclList.accept(visitor);
        if(MethodDeclList!=null) MethodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassDeclIdent!=null) ClassDeclIdent.traverseTopDown(visitor);
        if(Ext!=null) Ext.traverseTopDown(visitor);
        if(Impl!=null) Impl.traverseTopDown(visitor);
        if(ClassVarDeclList!=null) ClassVarDeclList.traverseTopDown(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassDeclIdent!=null) ClassDeclIdent.traverseBottomUp(visitor);
        if(Ext!=null) Ext.traverseBottomUp(visitor);
        if(Impl!=null) Impl.traverseBottomUp(visitor);
        if(ClassVarDeclList!=null) ClassVarDeclList.traverseBottomUp(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDeclarationWithMethods(\n");

        if(ClassDeclIdent!=null)
            buffer.append(ClassDeclIdent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Ext!=null)
            buffer.append(Ext.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Impl!=null)
            buffer.append(Impl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassVarDeclList!=null)
            buffer.append(ClassVarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclList!=null)
            buffer.append(MethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDeclarationWithMethods]");
        return buffer.toString();
    }
}
