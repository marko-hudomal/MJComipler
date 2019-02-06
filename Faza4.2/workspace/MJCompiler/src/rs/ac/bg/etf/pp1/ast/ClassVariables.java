// generated with ast extension for cup
// version 0.8
// 6/1/2019 3:51:16


package rs.ac.bg.etf.pp1.ast;

public class ClassVariables extends ClassVarList {

    private ClassVarList ClassVarList;
    private Variable Variable;

    public ClassVariables (ClassVarList ClassVarList, Variable Variable) {
        this.ClassVarList=ClassVarList;
        if(ClassVarList!=null) ClassVarList.setParent(this);
        this.Variable=Variable;
        if(Variable!=null) Variable.setParent(this);
    }

    public ClassVarList getClassVarList() {
        return ClassVarList;
    }

    public void setClassVarList(ClassVarList ClassVarList) {
        this.ClassVarList=ClassVarList;
    }

    public Variable getVariable() {
        return Variable;
    }

    public void setVariable(Variable Variable) {
        this.Variable=Variable;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassVarList!=null) ClassVarList.accept(visitor);
        if(Variable!=null) Variable.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassVarList!=null) ClassVarList.traverseTopDown(visitor);
        if(Variable!=null) Variable.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassVarList!=null) ClassVarList.traverseBottomUp(visitor);
        if(Variable!=null) Variable.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassVariables(\n");

        if(ClassVarList!=null)
            buffer.append(ClassVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Variable!=null)
            buffer.append(Variable.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassVariables]");
        return buffer.toString();
    }
}
