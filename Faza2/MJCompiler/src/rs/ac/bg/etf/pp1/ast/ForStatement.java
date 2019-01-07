// generated with ast extension for cup
// version 0.8
// 7/0/2019 4:14:13


package rs.ac.bg.etf.pp1.ast;

public class ForStatement extends Statement {

    private DesignatorStatementOrNull DesignatorStatementOrNull;
    private ConditionOrNull ConditionOrNull;
    private DesignatorStatementOrNull DesignatorStatementOrNull1;
    private Statement Statement;

    public ForStatement (DesignatorStatementOrNull DesignatorStatementOrNull, ConditionOrNull ConditionOrNull, DesignatorStatementOrNull DesignatorStatementOrNull1, Statement Statement) {
        this.DesignatorStatementOrNull=DesignatorStatementOrNull;
        if(DesignatorStatementOrNull!=null) DesignatorStatementOrNull.setParent(this);
        this.ConditionOrNull=ConditionOrNull;
        if(ConditionOrNull!=null) ConditionOrNull.setParent(this);
        this.DesignatorStatementOrNull1=DesignatorStatementOrNull1;
        if(DesignatorStatementOrNull1!=null) DesignatorStatementOrNull1.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public DesignatorStatementOrNull getDesignatorStatementOrNull() {
        return DesignatorStatementOrNull;
    }

    public void setDesignatorStatementOrNull(DesignatorStatementOrNull DesignatorStatementOrNull) {
        this.DesignatorStatementOrNull=DesignatorStatementOrNull;
    }

    public ConditionOrNull getConditionOrNull() {
        return ConditionOrNull;
    }

    public void setConditionOrNull(ConditionOrNull ConditionOrNull) {
        this.ConditionOrNull=ConditionOrNull;
    }

    public DesignatorStatementOrNull getDesignatorStatementOrNull1() {
        return DesignatorStatementOrNull1;
    }

    public void setDesignatorStatementOrNull1(DesignatorStatementOrNull DesignatorStatementOrNull1) {
        this.DesignatorStatementOrNull1=DesignatorStatementOrNull1;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorStatementOrNull!=null) DesignatorStatementOrNull.accept(visitor);
        if(ConditionOrNull!=null) ConditionOrNull.accept(visitor);
        if(DesignatorStatementOrNull1!=null) DesignatorStatementOrNull1.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorStatementOrNull!=null) DesignatorStatementOrNull.traverseTopDown(visitor);
        if(ConditionOrNull!=null) ConditionOrNull.traverseTopDown(visitor);
        if(DesignatorStatementOrNull1!=null) DesignatorStatementOrNull1.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorStatementOrNull!=null) DesignatorStatementOrNull.traverseBottomUp(visitor);
        if(ConditionOrNull!=null) ConditionOrNull.traverseBottomUp(visitor);
        if(DesignatorStatementOrNull1!=null) DesignatorStatementOrNull1.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForStatement(\n");

        if(DesignatorStatementOrNull!=null)
            buffer.append(DesignatorStatementOrNull.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditionOrNull!=null)
            buffer.append(ConditionOrNull.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorStatementOrNull1!=null)
            buffer.append(DesignatorStatementOrNull1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForStatement]");
        return buffer.toString();
    }
}
