// generated with ast extension for cup
// version 0.8
// 15/8/2023 0:5:28


package rs.ac.bg.etf.pp1.ast;

public class OneIdent extends IdentList {

    private IdentOptions IdentOptions;

    public OneIdent (IdentOptions IdentOptions) {
        this.IdentOptions=IdentOptions;
        if(IdentOptions!=null) IdentOptions.setParent(this);
    }

    public IdentOptions getIdentOptions() {
        return IdentOptions;
    }

    public void setIdentOptions(IdentOptions IdentOptions) {
        this.IdentOptions=IdentOptions;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IdentOptions!=null) IdentOptions.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IdentOptions!=null) IdentOptions.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IdentOptions!=null) IdentOptions.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OneIdent(\n");

        if(IdentOptions!=null)
            buffer.append(IdentOptions.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OneIdent]");
        return buffer.toString();
    }
}
