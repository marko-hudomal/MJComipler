// generated with ast extension for cup
// version 0.8
// 6/1/2019 3:51:16


package rs.ac.bg.etf.pp1.ast;

public class NewArray extends Factor {

    private NewArrayType NewArrayType;
    private Expr Expr;

    public NewArray (NewArrayType NewArrayType, Expr Expr) {
        this.NewArrayType=NewArrayType;
        if(NewArrayType!=null) NewArrayType.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public NewArrayType getNewArrayType() {
        return NewArrayType;
    }

    public void setNewArrayType(NewArrayType NewArrayType) {
        this.NewArrayType=NewArrayType;
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
        if(NewArrayType!=null) NewArrayType.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(NewArrayType!=null) NewArrayType.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(NewArrayType!=null) NewArrayType.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NewArray(\n");

        if(NewArrayType!=null)
            buffer.append(NewArrayType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NewArray]");
        return buffer.toString();
    }
}
