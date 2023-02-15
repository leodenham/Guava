package GuavaLang;


import java.util.ArrayList;

enum NodeType{
    Program,
    IntegerLiteral,
    FloatLiteral,
    Identifier,
    BinaryExpression
}


abstract class Statement {
    NodeType type;
    
    public String toString(){
        return "{"+type.toString()+"}";
    }
}

class Program extends Statement {
    NodeType type = NodeType.Program;
    ArrayList<Statement> contents;

    public Program(){
        this.contents = new ArrayList<Statement>();
    }

    void addContents(Statement val){
        this.contents.add(val);
    }

    public String toString(){
        String retval = "{type: Program,\n body=[";
        for (Statement stmt : contents){
            retval+=stmt.toString() + ",\n";
        }
        return retval + "]}";
    }

}

class Expression extends Statement{}

class IntegerLiteral extends Expression {
    NodeType type = NodeType.IntegerLiteral;
    int value;

    void setValue(int val){
        this.value=val;
    }

    public String toString(){
        return "{type: IntegerLiteral, value=" + Integer.toString(this.value) + "}";
    }
}

class FloatLiteral extends Expression {
    NodeType type = NodeType.FloatLiteral;
    float value;
    void setValue(float val){
        this.value=val;
    }
    public String toString(){
        return "{type: FloatLiteral, value=" + Float.toString(this.value) + "}";
    }
}

class Identifier extends Expression {
    NodeType type = NodeType.Identifier;
    String symbol;
    void setValue(String val){
        this.symbol=val;
    }

    public String toString(){
        return "{type: Identifier, value=" + this.symbol + "}";
    }
}

class BinaryExpression extends Expression {
    NodeType type = NodeType.BinaryExpression;
    Expression left;
    Expression right;
    String operator;
    void setLeft(Expression left){
        this.left = left;
    }
    void setRight(Expression right){
        this.right = right;
    }
    void setOperator(String operator){
        this.operator = operator;
    }

    public String toString(){
        return "{type: BinaryExpression, left=" + this.left.toString() + "\noperator=" + operator + "\nright=" + this.right.toString() + "}";
    }

}
