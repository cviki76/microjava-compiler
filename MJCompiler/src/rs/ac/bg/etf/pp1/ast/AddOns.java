// generated with ast extension for cup
// version 0.8
// 15/8/2023 0:5:28


package rs.ac.bg.etf.pp1.ast;

public class AddOns extends DesignatorAddOnList {

    private DesignatorAddOnList DesignatorAddOnList;
    private DesignatorAddOn DesignatorAddOn;

    public AddOns (DesignatorAddOnList DesignatorAddOnList, DesignatorAddOn DesignatorAddOn) {
        this.DesignatorAddOnList=DesignatorAddOnList;
        if(DesignatorAddOnList!=null) DesignatorAddOnList.setParent(this);
        this.DesignatorAddOn=DesignatorAddOn;
        if(DesignatorAddOn!=null) DesignatorAddOn.setParent(this);
    }

    public DesignatorAddOnList getDesignatorAddOnList() {
        return DesignatorAddOnList;
    }

    public void setDesignatorAddOnList(DesignatorAddOnList DesignatorAddOnList) {
        this.DesignatorAddOnList=DesignatorAddOnList;
    }

    public DesignatorAddOn getDesignatorAddOn() {
        return DesignatorAddOn;
    }

    public void setDesignatorAddOn(DesignatorAddOn DesignatorAddOn) {
        this.DesignatorAddOn=DesignatorAddOn;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorAddOnList!=null) DesignatorAddOnList.accept(visitor);
        if(DesignatorAddOn!=null) DesignatorAddOn.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorAddOnList!=null) DesignatorAddOnList.traverseTopDown(visitor);
        if(DesignatorAddOn!=null) DesignatorAddOn.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorAddOnList!=null) DesignatorAddOnList.traverseBottomUp(visitor);
        if(DesignatorAddOn!=null) DesignatorAddOn.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AddOns(\n");

        if(DesignatorAddOnList!=null)
            buffer.append(DesignatorAddOnList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorAddOn!=null)
            buffer.append(DesignatorAddOn.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AddOns]");
        return buffer.toString();
    }
}
