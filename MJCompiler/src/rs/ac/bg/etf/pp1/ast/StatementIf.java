// generated with ast extension for cup
// version 0.8
// 15/8/2023 0:5:28


package rs.ac.bg.etf.pp1.ast;

public class StatementIf extends Statement {

    private IfBegin IfBegin;
    private IfCondition IfCondition;
    private Statement Statement;
    private IfEnd IfEnd;

    public StatementIf (IfBegin IfBegin, IfCondition IfCondition, Statement Statement, IfEnd IfEnd) {
        this.IfBegin=IfBegin;
        if(IfBegin!=null) IfBegin.setParent(this);
        this.IfCondition=IfCondition;
        if(IfCondition!=null) IfCondition.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.IfEnd=IfEnd;
        if(IfEnd!=null) IfEnd.setParent(this);
    }

    public IfBegin getIfBegin() {
        return IfBegin;
    }

    public void setIfBegin(IfBegin IfBegin) {
        this.IfBegin=IfBegin;
    }

    public IfCondition getIfCondition() {
        return IfCondition;
    }

    public void setIfCondition(IfCondition IfCondition) {
        this.IfCondition=IfCondition;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public IfEnd getIfEnd() {
        return IfEnd;
    }

    public void setIfEnd(IfEnd IfEnd) {
        this.IfEnd=IfEnd;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfBegin!=null) IfBegin.accept(visitor);
        if(IfCondition!=null) IfCondition.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(IfEnd!=null) IfEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfBegin!=null) IfBegin.traverseTopDown(visitor);
        if(IfCondition!=null) IfCondition.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(IfEnd!=null) IfEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfBegin!=null) IfBegin.traverseBottomUp(visitor);
        if(IfCondition!=null) IfCondition.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(IfEnd!=null) IfEnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementIf(\n");

        if(IfBegin!=null)
            buffer.append(IfBegin.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IfCondition!=null)
            buffer.append(IfCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IfEnd!=null)
            buffer.append(IfEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementIf]");
        return buffer.toString();
    }
}
