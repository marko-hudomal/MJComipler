// generated with ast extension for cup
// version 0.8
// 5/1/2019 5:50:32


package rs.ac.bg.etf.pp1.ast;

public class BooleanNode extends Constant {

    private Integer bo;

    public BooleanNode (Integer bo) {
        this.bo=bo;
    }

    public Integer getBo() {
        return bo;
    }

    public void setBo(Integer bo) {
        this.bo=bo;
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
        buffer.append("BooleanNode(\n");

        buffer.append(" "+tab+bo);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BooleanNode]");
        return buffer.toString();
    }
}
