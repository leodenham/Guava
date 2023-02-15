package GuavaLang;


import java.util.ArrayList;

public class Lexer {

    

    public static boolean genericIs(char[] arrIn, char input){
        for (char current : arrIn){
            if (input == current){
                return true;
            }
        }
        return false;
    }

    public static boolean isNumeric(char in){
        final char[] numeric = {'0','1','2','3','4','5','6','7','8','9','.'};
        return genericIs(numeric, in);
    }

    public static boolean isBinaryOperator(char in){
        final char[] operator = {'+','-','/','*'};
        return genericIs(operator, in);
    }

    public static boolean isSkippable(char in){
        final char[] skippable = {'\n', '\t', ' '};
        return genericIs(skippable, in);
    }

    public static boolean isKeyword(String in){
        final String[] keyword = {"var"};
        for (String current : keyword){
            if (in.equals(current)){
                return true;
            }
        }
        return false;
    }

    public static TokenType getKeywordTokenType(String str){
        return TokenType.Var; // Needs to be changed when more keywords are added
    }


    public static ArrayList<Token> tokenise(String input){
        ArrayList<Token> tokens = new ArrayList<Token>();
        int position = 0;
        char currentChar;
        while (position < input.length()){
            currentChar = input.charAt(position);
            if (isSkippable(currentChar)){
                position++;
            } else if (isBinaryOperator(currentChar)){
                tokens.add(new Token(Character.toString(currentChar), TokenType.BinaryOp));
                position++;
            } else if (currentChar == '('){
                tokens.add(new Token("(", TokenType.OpenParen));
                position++;
            } else if (currentChar == ')'){
                tokens.add(new Token(")", TokenType.CloseParen));
                position++;
            } else if (currentChar == '='){
                tokens.add(new Token("=", TokenType.Equals));
                position++;
            } else if (currentChar == ':'){
                tokens.add(new Token(":", TokenType.EndOfLine));
                position++;
            } else if (isNumeric(currentChar)){
                StringBuilder num = new StringBuilder();
                while (isNumeric(currentChar)){
                    num.append(currentChar);
                    position++;
                    if (position == input.length()){
                        break;
                    }
                    currentChar = input.charAt(position);
                }
                tokens.add(new Token(num.toString(), TokenType.Number));
            } else if (currentChar == '"'){
                StringBuilder string = new StringBuilder();
                while (currentChar != '"'){
                    string.append(currentChar);
                    position++;
                    if (position == input.length()){
                        break;
                    }
                    currentChar = input.charAt(position);
                }
                tokens.add(new Token(string.toString(), TokenType.StringLiteral));
            } else {
                StringBuilder str = new StringBuilder();
                while (!isSkippable(currentChar)){
                    str.append(currentChar);
                    position++;
                    if (position == input.length()){
                        break;
                    }
                    currentChar = input.charAt(position);
                }
                if (isKeyword(str.toString())){
                    tokens.add(new Token(str.toString(), getKeywordTokenType(str.toString())));
                } else {
                    tokens.add(new Token(str.toString(), TokenType.Variable));
                }
            }

            
        }
        tokens.add(new Token("", TokenType.EndOfFile)); 
        return tokens;
    }
}




