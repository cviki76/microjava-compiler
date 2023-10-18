// generated with ast extension for cup
// version 0.8
// 15/8/2023 0:5:28


package rs.ac.bg.etf.pp1.ast;

public class DeclarationList extends DeclList {

    private DeclList DeclList;
    private OneDecl OneDecl;

    public DeclarationList (DeclList DeclList, OneDecl OneDecl) {
        this.DeclList=DeclList;
        if(DeclList!=null) DeclList.setParent(this);
        this.OneDecl=OneDecl;
        if(OneDecl!=null) OneDecl.setParent(this);
    }

    public DeclList getDeclList() {
        return DeclList;
    }

    public void setDeclList(DeclList DeclList) {
        this.DeclList=DeclList;
    }

    public OneDecl getOneDecl() {
        return OneDecl;
    }

    public void setOneDecl(OneDecl OneDecl) {
        this.OneDecl=OneDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DeclList!=null) DeclList.accept(visitor);
        if(OneDecl!=null) OneDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DeclList!=null) DeclList.traverseTopDown(visitor);
        if(OneDecl!=null) OneDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DeclList!=null) DeclList.traverseBottomUp(visitor);
        if(OneDecl!=null) OneDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DeclarationList(\n");

        if(DeclList!=null)
            buffer.append(DeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OneDecl!=null)
            buffer.append(OneDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DeclarationList]");
        return buffer.toString();
    }
}
