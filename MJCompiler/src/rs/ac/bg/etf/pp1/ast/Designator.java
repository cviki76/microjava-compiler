// generated with ast extension for cup
// version 0.8
// 15/8/2023 0:5:28


package rs.ac.bg.etf.pp1.ast;

public class Designator implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private String name;
    private DesignatorAddOnList DesignatorAddOnList;

    public Designator (String name, DesignatorAddOnList DesignatorAddOnList) {
        this.name=name;
        this.DesignatorAddOnList=DesignatorAddOnList;
        if(DesignatorAddOnList!=null) DesignatorAddOnList.setParent(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public DesignatorAddOnList getDesignatorAddOnList() {
        return DesignatorAddOnList;
    }

    public void setDesignatorAddOnList(DesignatorAddOnList DesignatorAddOnList) {
        this.DesignatorAddOnList=DesignatorAddOnList;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorAddOnList!=null) DesignatorAddOnList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorAddOnList!=null) DesignatorAddOnList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorAddOnList!=null) DesignatorAddOnList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Designator(\n");

        buffer.append(" "+tab+name);
        buffer.append("\n");

        if(DesignatorAddOnList!=null)
            buffer.append(DesignatorAddOnList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Designator]");
        return buffer.toString();
    }
}
