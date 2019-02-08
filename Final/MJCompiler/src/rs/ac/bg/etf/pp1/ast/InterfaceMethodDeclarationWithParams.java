// generated with ast extension for cup
// version 0.8
// 6/1/2019 3:51:16


package rs.ac.bg.etf.pp1.ast;

public class InterfaceMethodDeclarationWithParams extends InterfaceMethodDecl {

    private InterfaceMethodIdent InterfaceMethodIdent;
    private FormPars FormPars;

    public InterfaceMethodDeclarationWithParams (InterfaceMethodIdent InterfaceMethodIdent, FormPars FormPars) {
        this.InterfaceMethodIdent=InterfaceMethodIdent;
        if(InterfaceMethodIdent!=null) InterfaceMethodIdent.setParent(this);
        this.FormPars=FormPars;
        if(FormPars!=null) FormPars.setParent(this);
    }

    public InterfaceMethodIdent getInterfaceMethodIdent() {
        return InterfaceMethodIdent;
    }

    public void setInterfaceMethodIdent(InterfaceMethodIdent InterfaceMethodIdent) {
        this.InterfaceMethodIdent=InterfaceMethodIdent;
    }

    public FormPars getFormPars() {
        return FormPars;
    }

    public void setFormPars(FormPars FormPars) {
        this.FormPars=FormPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(InterfaceMethodIdent!=null) InterfaceMethodIdent.accept(visitor);
        if(FormPars!=null) FormPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(InterfaceMethodIdent!=null) InterfaceMethodIdent.traverseTopDown(visitor);
        if(FormPars!=null) FormPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(InterfaceMethodIdent!=null) InterfaceMethodIdent.traverseBottomUp(visitor);
        if(FormPars!=null) FormPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("InterfaceMethodDeclarationWithParams(\n");

        if(InterfaceMethodIdent!=null)
            buffer.append(InterfaceMethodIdent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormPars!=null)
            buffer.append(FormPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [InterfaceMethodDeclarationWithParams]");
        return buffer.toString();
    }
}
