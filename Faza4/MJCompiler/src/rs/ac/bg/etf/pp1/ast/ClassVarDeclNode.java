// generated with ast extension for cup
// version 0.8
// 4/1/2019 5:0:20


package rs.ac.bg.etf.pp1.ast;

public class ClassVarDeclNode extends ClassVarDecl {

    private ClassVarDeclType ClassVarDeclType;
    private VarList VarList;

    public ClassVarDeclNode (ClassVarDeclType ClassVarDeclType, VarList VarList) {
        this.ClassVarDeclType=ClassVarDeclType;
        if(ClassVarDeclType!=null) ClassVarDeclType.setParent(this);
        this.VarList=VarList;
        if(VarList!=null) VarList.setParent(this);
    }

    public ClassVarDeclType getClassVarDeclType() {
        return ClassVarDeclType;
    }

    public void setClassVarDeclType(ClassVarDeclType ClassVarDeclType) {
        this.ClassVarDeclType=ClassVarDeclType;
    }

    public VarList getVarList() {
        return VarList;
    }

    public void setVarList(VarList VarList) {
        this.VarList=VarList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassVarDeclType!=null) ClassVarDeclType.accept(visitor);
        if(VarList!=null) VarList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassVarDeclType!=null) ClassVarDeclType.traverseTopDown(visitor);
        if(VarList!=null) VarList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassVarDeclType!=null) ClassVarDeclType.traverseBottomUp(visitor);
        if(VarList!=null) VarList.traverseBottomUp(visitor);
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

        if(VarList!=null)
            buffer.append(VarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassVarDeclNode]");
        return buffer.toString();
    }
}
