// generated with ast extension for cup
// version 0.8
// 6/1/2019 3:51:16


package rs.ac.bg.etf.pp1.ast;

public class Exprs extends IdentExprList {

    private IdentExprListArray IdentExprListArray;
    private Expr Expr;

    public Exprs (IdentExprListArray IdentExprListArray, Expr Expr) {
        this.IdentExprListArray=IdentExprListArray;
        if(IdentExprListArray!=null) IdentExprListArray.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public IdentExprListArray getIdentExprListArray() {
        return IdentExprListArray;
    }

    public void setIdentExprListArray(IdentExprListArray IdentExprListArray) {
        this.IdentExprListArray=IdentExprListArray;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IdentExprListArray!=null) IdentExprListArray.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IdentExprListArray!=null) IdentExprListArray.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IdentExprListArray!=null) IdentExprListArray.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Exprs(\n");

        if(IdentExprListArray!=null)
            buffer.append(IdentExprListArray.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Exprs]");
        return buffer.toString();
    }
}
