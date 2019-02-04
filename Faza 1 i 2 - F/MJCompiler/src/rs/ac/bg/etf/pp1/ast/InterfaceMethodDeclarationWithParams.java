// generated with ast extension for cup
// version 0.8
// 29/0/2019 22:39:44


package rs.ac.bg.etf.pp1.ast;

public class InterfaceMethodDeclarationWithParams extends InterfaceMethodDecl {

    private ReturnTypes ReturnTypes;
    private String I2;
    private FormPars FormPars;

    public InterfaceMethodDeclarationWithParams (ReturnTypes ReturnTypes, String I2, FormPars FormPars) {
        this.ReturnTypes=ReturnTypes;
        if(ReturnTypes!=null) ReturnTypes.setParent(this);
        this.I2=I2;
        this.FormPars=FormPars;
        if(FormPars!=null) FormPars.setParent(this);
    }

    public ReturnTypes getReturnTypes() {
        return ReturnTypes;
    }

    public void setReturnTypes(ReturnTypes ReturnTypes) {
        this.ReturnTypes=ReturnTypes;
    }

    public String getI2() {
        return I2;
    }

    public void setI2(String I2) {
        this.I2=I2;
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
        if(ReturnTypes!=null) ReturnTypes.accept(visitor);
        if(FormPars!=null) FormPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ReturnTypes!=null) ReturnTypes.traverseTopDown(visitor);
        if(FormPars!=null) FormPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ReturnTypes!=null) ReturnTypes.traverseBottomUp(visitor);
        if(FormPars!=null) FormPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("InterfaceMethodDeclarationWithParams(\n");

        if(ReturnTypes!=null)
            buffer.append(ReturnTypes.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+I2);
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
