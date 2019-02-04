// generated with ast extension for cup
// version 0.8
// 29/0/2019 22:39:45


package rs.ac.bg.etf.pp1.ast;

public class NewEmpty extends Factor {

    private Type Type;
    private NewNode NewNode;

    public NewEmpty (Type Type, NewNode NewNode) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.NewNode=NewNode;
        if(NewNode!=null) NewNode.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public NewNode getNewNode() {
        return NewNode;
    }

    public void setNewNode(NewNode NewNode) {
        this.NewNode=NewNode;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(NewNode!=null) NewNode.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(NewNode!=null) NewNode.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(NewNode!=null) NewNode.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NewEmpty(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NewNode!=null)
            buffer.append(NewNode.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NewEmpty]");
        return buffer.toString();
    }
}
