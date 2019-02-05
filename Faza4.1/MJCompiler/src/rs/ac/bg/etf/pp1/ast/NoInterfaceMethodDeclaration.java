// generated with ast extension for cup
// version 0.8
// 5/1/2019 5:50:32


package rs.ac.bg.etf.pp1.ast;

public class NoInterfaceMethodDeclaration extends InterfaceMethodDeclList {

    public NoInterfaceMethodDeclaration () {
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
        buffer.append("NoInterfaceMethodDeclaration(\n");

        buffer.append(tab);
        buffer.append(") [NoInterfaceMethodDeclaration]");
        return buffer.toString();
    }
}
