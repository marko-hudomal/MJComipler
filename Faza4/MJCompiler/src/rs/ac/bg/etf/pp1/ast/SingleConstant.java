// generated with ast extension for cup
// version 0.8
// 4/1/2019 5:0:20


package rs.ac.bg.etf.pp1.ast;

public class SingleConstant extends ConstList {

    private IdentEqualConstant IdentEqualConstant;

    public SingleConstant (IdentEqualConstant IdentEqualConstant) {
        this.IdentEqualConstant=IdentEqualConstant;
        if(IdentEqualConstant!=null) IdentEqualConstant.setParent(this);
    }

    public IdentEqualConstant getIdentEqualConstant() {
        return IdentEqualConstant;
    }

    public void setIdentEqualConstant(IdentEqualConstant IdentEqualConstant) {
        this.IdentEqualConstant=IdentEqualConstant;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IdentEqualConstant!=null) IdentEqualConstant.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IdentEqualConstant!=null) IdentEqualConstant.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IdentEqualConstant!=null) IdentEqualConstant.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleConstant(\n");

        if(IdentEqualConstant!=null)
            buffer.append(IdentEqualConstant.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleConstant]");
        return buffer.toString();
    }
}
