// generated with ast extension for cup
// version 0.8
// 1/1/2019 7:50:30


package rs.ac.bg.etf.pp1.ast;

public class EnumerationVal extends Enumeration {

    private String identName;
    private Integer N1;

    public EnumerationVal (String identName, Integer N1) {
        this.identName=identName;
        this.N1=N1;
    }

    public String getIdentName() {
        return identName;
    }

    public void setIdentName(String identName) {
        this.identName=identName;
    }

    public Integer getN1() {
        return N1;
    }

    public void setN1(Integer N1) {
        this.N1=N1;
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
        buffer.append("EnumerationVal(\n");

        buffer.append(" "+tab+identName);
        buffer.append("\n");

        buffer.append(" "+tab+N1);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnumerationVal]");
        return buffer.toString();
    }
}
