// generated with ast extension for cup
// version 0.8
// 15/8/2023 0:5:28


package rs.ac.bg.etf.pp1.ast;

public class ConstValueBool extends ConstValue {

    private Boolean b;

    public ConstValueBool (Boolean b) {
        this.b=b;
    }

    public Boolean getB() {
        return b;
    }

    public void setB(Boolean b) {
        this.b=b;
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
        buffer.append("ConstValueBool(\n");

        buffer.append(" "+tab+b);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstValueBool]");
        return buffer.toString();
    }
}
