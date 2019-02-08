// generated with ast extension for cup
// version 0.8
// 6/1/2019 3:51:16


package rs.ac.bg.etf.pp1.ast;

public class ClassVarDeclNode extends ClassVarDecl {

    private ClassVarDeclType ClassVarDeclType;
    private ClassVarList ClassVarList;

    public ClassVarDeclNode (ClassVarDeclType ClassVarDeclType, ClassVarList ClassVarList) {
        this.ClassVarDeclType=ClassVarDeclType;
        if(ClassVarDeclType!=null) ClassVarDeclType.setParent(this);
        this.ClassVarList=ClassVarList;
        if(ClassVarList!=null) ClassVarList.setParent(this);
    }

    public ClassVarDeclType getClassVarDeclType() {
        return ClassVarDeclType;
    }

    public void setClassVarDeclType(ClassVarDeclType ClassVarDeclType) {
        this.ClassVarDeclType=ClassVarDeclType;
    }

    public ClassVarList getClassVarList() {
        return ClassVarList;
    }

    public void setClassVarList(ClassVarList ClassVarList) {
        this.ClassVarList=ClassVarList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassVarDeclType!=null) ClassVarDeclType.accept(visitor);
        if(ClassVarList!=null) ClassVarList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassVarDeclType!=null) ClassVarDeclType.traverseTopDown(visitor);
        if(ClassVarList!=null) ClassVarList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassVarDeclType!=null) ClassVarDeclType.traverseBottomUp(visitor);
        if(ClassVarList!=null) ClassVarList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassVarDeclNode(\n");

        if(ClassVarDeclType!=null)
            buffer.append(ClassVarDeclType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassVarList!=null)
            buffer.append(ClassVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassVarDeclNode]");
        return buffer.toString();
    }
}
