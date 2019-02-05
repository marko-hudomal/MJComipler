// generated with ast extension for cup
// version 0.8
// 5/1/2019 5:50:32


package rs.ac.bg.etf.pp1.ast;

public class IfElseStatement extends Statement {

    private IfCondition IfCondition;
    private IfStatementThen IfStatementThen;
    private IfStatementElse IfStatementElse;

    public IfElseStatement (IfCondition IfCondition, IfStatementThen IfStatementThen, IfStatementElse IfStatementElse) {
        this.IfCondition=IfCondition;
        if(IfCondition!=null) IfCondition.setParent(this);
        this.IfStatementThen=IfStatementThen;
        if(IfStatementThen!=null) IfStatementThen.setParent(this);
        this.IfStatementElse=IfStatementElse;
        if(IfStatementElse!=null) IfStatementElse.setParent(this);
    }

    public IfCondition getIfCondition() {
        return IfCondition;
    }

    public void setIfCondition(IfCondition IfCondition) {
        this.IfCondition=IfCondition;
    }

    public IfStatementThen getIfStatementThen() {
        return IfStatementThen;
    }

    public void setIfStatementThen(IfStatementThen IfStatementThen) {
        this.IfStatementThen=IfStatementThen;
    }

    public IfStatementElse getIfStatementElse() {
        return IfStatementElse;
    }

    public void setIfStatementElse(IfStatementElse IfStatementElse) {
        this.IfStatementElse=IfStatementElse;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfCondition!=null) IfCondition.accept(visitor);
        if(IfStatementThen!=null) IfStatementThen.accept(visitor);
        if(IfStatementElse!=null) IfStatementElse.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfCondition!=null) IfCondition.traverseTopDown(visitor);
        if(IfStatementThen!=null) IfStatementThen.traverseTopDown(visitor);
        if(IfStatementElse!=null) IfStatementElse.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfCondition!=null) IfCondition.traverseBottomUp(visitor);
        if(IfStatementThen!=null) IfStatementThen.traverseBottomUp(visitor);
        if(IfStatementElse!=null) IfStatementElse.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfElseStatement(\n");

        if(IfCondition!=null)
            buffer.append(IfCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IfStatementThen!=null)
            buffer.append(IfStatementThen.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IfStatementElse!=null)
            buffer.append(IfStatementElse.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfElseStatement]");
        return buffer.toString();
    }
}
