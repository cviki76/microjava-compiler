// generated with ast extension for cup
// version 0.8
// 15/8/2023 0:5:28


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatementFindAndReplace extends DesignatorStatement {

    private Designator Designator;
    private Designator Designator1;
    private FindAndReplaceBegin FindAndReplaceBegin;
    private Expr Expr;
    private Designator Designator2;
    private Expr Expr3;

    public DesignatorStatementFindAndReplace (Designator Designator, Designator Designator1, FindAndReplaceBegin FindAndReplaceBegin, Expr Expr, Designator Designator2, Expr Expr3) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.Designator1=Designator1;
        if(Designator1!=null) Designator1.setParent(this);
        this.FindAndReplaceBegin=FindAndReplaceBegin;
        if(FindAndReplaceBegin!=null) FindAndReplaceBegin.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.Designator2=Designator2;
        if(Designator2!=null) Designator2.setParent(this);
        this.Expr3=Expr3;
        if(Expr3!=null) Expr3.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public Designator getDesignator1() {
        return Designator1;
    }

    public void setDesignator1(Designator Designator1) {
        this.Designator1=Designator1;
    }

    public FindAndReplaceBegin getFindAndReplaceBegin() {
        return FindAndReplaceBegin;
    }

    public void setFindAndReplaceBegin(FindAndReplaceBegin FindAndReplaceBegin) {
        this.FindAndReplaceBegin=FindAndReplaceBegin;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public Designator getDesignator2() {
        return Designator2;
    }

    public void setDesignator2(Designator Designator2) {
        this.Designator2=Designator2;
    }

    public Expr getExpr3() {
        return Expr3;
    }

    public void setExpr3(Expr Expr3) {
        this.Expr3=Expr3;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(Designator1!=null) Designator1.accept(visitor);
        if(FindAndReplaceBegin!=null) FindAndReplaceBegin.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(Designator2!=null) Designator2.accept(visitor);
        if(Expr3!=null) Expr3.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(Designator1!=null) Designator1.traverseTopDown(visitor);
        if(FindAndReplaceBegin!=null) FindAndReplaceBegin.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(Designator2!=null) Designator2.traverseTopDown(visitor);
        if(Expr3!=null) Expr3.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(Designator1!=null) Designator1.traverseBottomUp(visitor);
        if(FindAndReplaceBegin!=null) FindAndReplaceBegin.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(Designator2!=null) Designator2.traverseBottomUp(visitor);
        if(Expr3!=null) Expr3.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatementFindAndReplace(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Designator1!=null)
            buffer.append(Designator1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FindAndReplaceBegin!=null)
            buffer.append(FindAndReplaceBegin.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Designator2!=null)
            buffer.append(Designator2.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr3!=null)
            buffer.append(Expr3.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatementFindAndReplace]");
        return buffer.toString();
    }
}
