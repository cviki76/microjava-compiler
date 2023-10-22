// generated with ast extension for cup
// version 0.8
// 15/8/2023 0:5:28


package rs.ac.bg.etf.pp1.ast;

public class StatementForEach extends Statement {

    private ForEachBegin ForEachBegin;
    private Statement Statement;
    private ForEachEnd ForEachEnd;

    public StatementForEach (ForEachBegin ForEachBegin, Statement Statement, ForEachEnd ForEachEnd) {
        this.ForEachBegin=ForEachBegin;
        if(ForEachBegin!=null) ForEachBegin.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.ForEachEnd=ForEachEnd;
        if(ForEachEnd!=null) ForEachEnd.setParent(this);
    }

    public ForEachBegin getForEachBegin() {
        return ForEachBegin;
    }

    public void setForEachBegin(ForEachBegin ForEachBegin) {
        this.ForEachBegin=ForEachBegin;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public ForEachEnd getForEachEnd() {
        return ForEachEnd;
    }

    public void setForEachEnd(ForEachEnd ForEachEnd) {
        this.ForEachEnd=ForEachEnd;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ForEachBegin!=null) ForEachBegin.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(ForEachEnd!=null) ForEachEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForEachBegin!=null) ForEachBegin.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(ForEachEnd!=null) ForEachEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForEachBegin!=null) ForEachBegin.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(ForEachEnd!=null) ForEachEnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementForEach(\n");

        if(ForEachBegin!=null)
            buffer.append(ForEachBegin.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForEachEnd!=null)
            buffer.append(ForEachEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementForEach]");
        return buffer.toString();
    }
}
