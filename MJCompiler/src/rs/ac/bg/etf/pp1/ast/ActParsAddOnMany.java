// generated with ast extension for cup
// version 0.8
// 15/8/2023 0:5:28


package rs.ac.bg.etf.pp1.ast;

public class ActParsAddOnMany extends ActParsAddOn {

    private ActParsAddOn ActParsAddOn;
    private Expr Expr;

    public ActParsAddOnMany (ActParsAddOn ActParsAddOn, Expr Expr) {
        this.ActParsAddOn=ActParsAddOn;
        if(ActParsAddOn!=null) ActParsAddOn.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public ActParsAddOn getActParsAddOn() {
        return ActParsAddOn;
    }

    public void setActParsAddOn(ActParsAddOn ActParsAddOn) {
        this.ActParsAddOn=ActParsAddOn;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ActParsAddOn!=null) ActParsAddOn.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActParsAddOn!=null) ActParsAddOn.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActParsAddOn!=null) ActParsAddOn.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActParsAddOnMany(\n");

        if(ActParsAddOn!=null)
            buffer.append(ActParsAddOn.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActParsAddOnMany]");
        return buffer.toString();
    }
}
