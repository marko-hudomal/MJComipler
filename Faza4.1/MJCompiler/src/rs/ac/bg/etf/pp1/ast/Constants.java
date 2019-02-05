// generated with ast extension for cup
// version 0.8
// 5/1/2019 5:50:32


package rs.ac.bg.etf.pp1.ast;

public class Constants extends ConstList {

    private ConstList ConstList;
    private IdentEqualConstant IdentEqualConstant;

    public Constants (ConstList ConstList, IdentEqualConstant IdentEqualConstant) {
        this.ConstList=ConstList;
        if(ConstList!=null) ConstList.setParent(this);
        this.IdentEqualConstant=IdentEqualConstant;
        if(IdentEqualConstant!=null) IdentEqualConstant.setParent(this);
    }

    public ConstList getConstList() {
        return ConstList;
    }

    public void setConstList(ConstList ConstList) {
        this.ConstList=ConstList;
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
        if(ConstList!=null) ConstList.accept(visitor);
        if(IdentEqualConstant!=null) IdentEqualConstant.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstList!=null) ConstList.traverseTopDown(visitor);
        if(IdentEqualConstant!=null) IdentEqualConstant.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstList!=null) ConstList.traverseBottomUp(visitor);
        if(IdentEqualConstant!=null) IdentEqualConstant.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Constants(\n");

        if(ConstList!=null)
            buffer.append(ConstList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IdentEqualConstant!=null)
            buffer.append(IdentEqualConstant.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Constants]");
        return buffer.toString();
    }
}
