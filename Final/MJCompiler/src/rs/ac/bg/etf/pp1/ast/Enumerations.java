// generated with ast extension for cup
// version 0.8
// 6/1/2019 3:51:16


package rs.ac.bg.etf.pp1.ast;

public class Enumerations extends EnumList {

    private EnumList EnumList;
    private Enumeration Enumeration;

    public Enumerations (EnumList EnumList, Enumeration Enumeration) {
        this.EnumList=EnumList;
        if(EnumList!=null) EnumList.setParent(this);
        this.Enumeration=Enumeration;
        if(Enumeration!=null) Enumeration.setParent(this);
    }

    public EnumList getEnumList() {
        return EnumList;
    }

    public void setEnumList(EnumList EnumList) {
        this.EnumList=EnumList;
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
        if(EnumList!=null) EnumList.accept(visitor);
        if(Enumeration!=null) Enumeration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EnumList!=null) EnumList.traverseTopDown(visitor);
        if(Enumeration!=null) Enumeration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EnumList!=null) EnumList.traverseBottomUp(visitor);
        if(Enumeration!=null) Enumeration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Enumerations(\n");

        if(EnumList!=null)
            buffer.append(EnumList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Enumeration!=null)
            buffer.append(Enumeration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Enumerations]");
        return buffer.toString();
    }
}
