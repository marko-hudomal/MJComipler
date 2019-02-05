// generated with ast extension for cup
// version 0.8
// 5/1/2019 5:50:32


package rs.ac.bg.etf.pp1.ast;

public class EnumerationNoVal extends Enumeration {

    private String identName;

    public EnumerationNoVal (String identName) {
        this.identName=identName;
    }

    public String getIdentName() {
        return identName;
    }

    public void setIdentName(String identName) {
        this.identName=identName;
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
        buffer.append("EnumerationNoVal(\n");

        buffer.append(" "+tab+identName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnumerationNoVal]");
        return buffer.toString();
    }
}
