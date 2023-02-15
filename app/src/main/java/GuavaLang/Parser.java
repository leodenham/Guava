package GuavaLang;


import java.util.ArrayList;

public class Parser {
    
    private ArrayList<Token> tokens;
    private Token popToken(){
        return this.tokens.remove(0);
    }

    boolean isInteger(String num){
        for (char cur : num.toCharArray()){
            if (cur == '.'){
                return false;
            }
        }
        return true;
    }

    private boolean endOfFile(){
        return (this.tokens.get(0).type == TokenType.EndOfFile);
    }


    public Program toSyntaxTree(ArrayList<Token> tokens){
        this.tokens = tokens;
        Program program = new Program();
        while(!endOfFile()){
            // Token currentToken = popToken();
            program.addContents(parseStatement());
        }

        return program;
    }

    Statement parseStatement(){
        return parseExpression();
    }

    Expression parseExpression(){
        return parseCurrentExpression();
    }

    Expression parseCurrentExpression(){
        Token currentToken = this.tokens.get(0);
        if (currentToken.type == TokenType.Variable){
            Identifier id = new Identifier();
            id.setValue(popToken().value);
            return id;
        } else if (currentToken.type == TokenType.Number){
            if (isInteger(currentToken.value)){
                IntegerLiteral retval = new IntegerLiteral();
                retval.setValue(Integer.parseInt(popToken().value));
                return retval;
            } else {
                FloatLiteral retval = new FloatLiteral();
                retval.setValue(Float.parseFloat(popToken().value));
                return retval;
            }
        } else {
            System.out.println("ERROR unexpected character");
            return null;
        }

    }
}
