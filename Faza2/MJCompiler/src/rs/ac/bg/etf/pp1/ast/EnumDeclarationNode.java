// generated with ast extension for cup
// version 0.8
// 7/0/2019 4:14:13


package rs.ac.bg.etf.pp1.ast;

public class EnumDeclarationNode extends EnumDecl {

    private String I1;
    private EnumList EnumList;

    public EnumDeclarationNode (String I1, EnumList EnumList) {
        this.I1=I1;
        this.EnumList=EnumList;
        if(EnumList!=null) EnumList.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public EnumList getEnumList() {
        return EnumList;
    }

    public void setEnumList(EnumList EnumList) {
        this.EnumList=EnumList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(EnumList!=null) EnumList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EnumList!=null) EnumList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EnumList!=null) EnumList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EnumDeclarationNode(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(EnumList!=null)
            buffer.append(EnumList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnumDeclarationNode]");
        return buffer.toString();
    }
}
