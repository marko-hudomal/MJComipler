// generated with ast extension for cup
// version 0.8
// 5/1/2019 5:50:32


package rs.ac.bg.etf.pp1.ast;

public class SingleIdentExpr extends IdentExprList {

    private String baseName;

    public SingleIdentExpr (String baseName) {
        this.baseName=baseName;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName=baseName;
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
        buffer.append("SingleIdentExpr(\n");

        buffer.append(" "+tab+baseName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleIdentExpr]");
        return buffer.toString();
    }
}
