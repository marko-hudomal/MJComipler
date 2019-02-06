// generated with ast extension for cup
// version 0.8
// 6/1/2019 3:51:16


package rs.ac.bg.etf.pp1.ast;

public class SingleEnumeration extends EnumList {

    private Enumeration Enumeration;

    public SingleEnumeration (Enumeration Enumeration) {
        this.Enumeration=Enumeration;
        if(Enumeration!=null) Enumeration.setParent(this);
    }

    public Enumeration getEnumeration() {
        return Enumeration;
    }

    public void setEnumeration(Enumeration Enumeration) {
        this.Enumeration=Enumeration;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Enumeration!=null) Enumeration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Enumeration!=null) Enumeration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Enumeration!=null) Enumeration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleEnumeration(\n");

        if(Enumeration!=null)
            buffer.append(Enumeration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleEnumeration]");
        return buffer.toString();
    }
}
