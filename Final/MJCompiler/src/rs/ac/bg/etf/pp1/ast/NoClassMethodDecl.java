// generated with ast extension for cup
// version 0.8
// 6/1/2019 3:51:16


package rs.ac.bg.etf.pp1.ast;

public class NoClassMethodDecl extends ClassMethodDeclList {

    public NoClassMethodDecl () {
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
        buffer.append("NoClassMethodDecl(\n");

        buffer.append(tab);
        buffer.append(") [NoClassMethodDecl]");
        return buffer.toString();
    }
}
