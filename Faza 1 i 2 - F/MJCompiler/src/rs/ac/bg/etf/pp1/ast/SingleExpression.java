// generated with ast extension for cup
// version 0.8
// 29/0/2019 22:39:45


package rs.ac.bg.etf.pp1.ast;

public class SingleExpression extends Expr {

    private MinusOrNull MinusOrNull;
    private Term Term;

    public SingleExpression (MinusOrNull MinusOrNull, Term Term) {
        this.MinusOrNull=MinusOrNull;
        if(MinusOrNull!=null) MinusOrNull.setParent(this);
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
    }

    public MinusOrNull getMinusOrNull() {
        return MinusOrNull;
    }

    public void setMinusOrNull(MinusOrNull MinusOrNull) {
        this.MinusOrNull=MinusOrNull;
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MinusOrNull!=null) MinusOrNull.accept(visitor);
        if(Term!=null) Term.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MinusOrNull!=null) MinusOrNull.traverseTopDown(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MinusOrNull!=null) MinusOrNull.traverseBottomUp(visitor);
        if(Term!=null) Term.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleExpression(\n");

        if(MinusOrNull!=null)
            buffer.append(MinusOrNull.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleExpression]");
        return buffer.toString();
    }
}
