// generated with ast extension for cup
// version 0.8
// 15/8/2023 0:5:28


package rs.ac.bg.etf.pp1.ast;

public class FormParMatrix extends FormPar {

    private String par;

    public FormParMatrix (String par) {
        this.par=par;
    }

    public String getPar() {
        return par;
    }

    public void setPar(String par) {
        this.par=par;
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
        buffer.append("FormParMatrix(\n");

        buffer.append(" "+tab+par);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormParMatrix]");
        return buffer.toString();
    }
}
