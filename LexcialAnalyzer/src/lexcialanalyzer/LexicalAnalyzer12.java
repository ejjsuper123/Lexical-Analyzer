/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lexcialanalyzer;

/**
 *
 * @author ejazike
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
///Users/ejazike/Documents/COSC 455/examples/ab.txt
public class LexicalAnalyzer12 {
    
Map<String, Grammar> keywords = new HashMap<>();

    int lineNum;
    int FirstCharPostion;
    int position;
    char chr;
    String s;
// A class created with all of our Terminals for our grammar
    
    enum Grammar {
        COLON,
        PROGRAM,
        END,
        BOOL,
        INT,
        SEMICOLON,
        IF,
        THEN,
        ELSE,
        FI,
        WHILE,
        DO,
        OD,
        PRINT,
        LT,
        LT_EQUAL,
        EQUAL,
        NOT_EQUAL,
        GT_EQUAL,
        GT,
        ADD,
        SUBTRACT,
        OR,
        MULTIPLY,
        DIVIDE,
        AND,
        OPEN_PARA,
        CLOSE_PARA,
        NOT,
        FALSE,
        TRUE,
        UNDERSCORE,
        ASSIGN,
       IDENTIFIER,
        NUM,
    }
    LexicalAnalyzer12(String generatedTokenFile) {

        this.lineNum = 1;
        this.FirstCharPostion = 0;
        this.position = 0;
        this.s = generatedTokenFile;
        this.chr = this.s.charAt(0);
        this.keywords.put("program", Grammar.PROGRAM);
        this.keywords.put("end", Grammar.END);
        this.keywords.put("bool", Grammar.BOOL);
        this.keywords.put("int", Grammar.INT);
        this.keywords.put("if", Grammar.IF);
        this.keywords.put("then", Grammar.THEN);
        this.keywords.put("else", Grammar.ELSE);
        this.keywords.put("fi", Grammar.FI);
        this.keywords.put("while", Grammar.WHILE);
        this.keywords.put("do", Grammar.DO);
        this.keywords.put("od", Grammar.OD);
        this.keywords.put("print", Grammar.PRINT);
        this.keywords.put("or", Grammar.OR);
        this.keywords.put("and", Grammar.AND);
        this.keywords.put("not", Grammar.NOT);
        this.keywords.put("false", Grammar.FALSE);
        this.keywords.put("true", Grammar.TRUE);
        this.keywords.put(":=", Grammar.ASSIGN);
    }

    // Creating a HashMap to map the values associated with each keyword
    

    // The information included with each token (kind, value and position of lexeme)
    static class Token {

        // Variables
        Grammar Grammar;
        String value;
        int lineNum;
        int FirstCharPostion;

        // Token object
        Token(Grammar token, String value, int lineNum, int FirstCharPostion) {
            this.Grammar = token;
            this.value = value;
            this.lineNum = lineNum;
            this.FirstCharPostion = FirstCharPostion;
        }

        @Override
        
        public String toString() {

            String tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+ "  " + "{" + this.Grammar + "}" +" \t";
           
            // Checks kind of lexeme, identifier, integer, or itself
            switch (this.Grammar) {
                case NUM:
                    tokenString += value;
                    break;
                case IDENTIFIER:
                    tokenString += value;
                    break;
                case COLON:
                    tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+ " "+ ":";
                    break;
                case PROGRAM:
                    tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+ " "+ "program";
                    break;
                case END:
                    tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+ " "+ "end";
                    break;   
                case BOOL:
                    tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+ " "+ "bool";
                    break;
                case INT:
                    tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+ " "+ "int";
                    break;
                case SEMICOLON:
                    tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+ " "+ ";";
                    break;
                case ASSIGN:
                    tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+ " "+ ":=";
                    break;
                case IF:
                    tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+ " "+ "if";
                    break;
                case THEN:
                    tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+ " "+ "then";
                    break;
                case ELSE:
                    tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+ " "+ "else";
                    break;
                case FI:
                    tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+ " "+ "fi";
                    break;
                case WHILE:
                    tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+ " "+ "while";
                    break;
                case DO:
                    tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+ " "+ "do";
                    break;
                case OD:
                    tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+ " "+ "od";
                    break;
                case PRINT:
                    tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+ " "+ "print";
                    break;
                
                case LT:
                    tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+ " "+ "<";
                    break;   
                case LT_EQUAL:
                    tokenString ="Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+ " " + "<=";
                    break;
                case EQUAL:
                    tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+" " +" =";
                    break;
                case NOT_EQUAL:
                    tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+" " +" !=";
                    break;
                case GT_EQUAL:
                    tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+" " +" >=";
                    break;
                case GT:
                    tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+" " +" >";
                    break;
                case ADD:
                    tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+" " +" +";
                    break;
                case  SUBTRACT:
                    tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+" " +" -";
                    break;
                case OR:
                    tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+" " +" or";
                    break;
                case MULTIPLY:
                    tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+" " +" *";
                    break;
                case DIVIDE:
                    tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+" " +" /";
                    break;
                case OPEN_PARA:
                    tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+" " +" (";
                    break;
                case CLOSE_PARA:
                    tokenString = "Line Number: " +"{"+ this.lineNum + "}" + " " + "Postion of First Charater: " + "{" +this.FirstCharPostion +"}"+" " +" )";
                    break;
            }

            return tokenString;
        }
    }
    char getNext() {

        this.FirstCharPostion++;
        this.position++;

        this.chr = this.s.charAt(this.position);

        if (this.chr == '\n') {
            this.lineNum++;
            this.FirstCharPostion = 0;
        }

        return this.chr;
    }
  ///Users/ejazike/Documents/COSC 455/ab.txt  
    boolean match(Token t,Grammar g){
        
        t = getToken();
        return t.Grammar == g;
           
     }

    // Determines if Token is an identifier or an integer
    Token Value(int lineNum, int FirstCharPostion) {

        // Initializing variables
        boolean num = true;
        String generatedToken = "";

        // While the current character is alphabetic, a digit, or "_"
        while (Character.isAlphabetic(this.chr) || Character.isDigit(this.chr) || this.chr == '_') {

            // Adds the character to generatedToken in order to build the full generatedToken
            generatedToken += this.chr;

            if (!Character.isDigit(this.chr)) {

                num = false;
            }

            getNext();
        }

        if (this.keywords.containsKey(generatedToken)) {

            // Creates a new token if generatedToken contains an established Keyword
            return new Token(this.keywords.get(generatedToken), "", lineNum, FirstCharPostion);
        }

        return new Token(Grammar.IDENTIFIER, generatedToken, lineNum, FirstCharPostion);
    }
// Returns a symbol Token
    Token symbol(char symbol, Grammar match, Grammar no_match, int lineNum, int FirstCharPostion) {

        if (getNext() == symbol) {
            getNext();
            return new Token(match, "", lineNum, FirstCharPostion);
        }

        // Returns error message for an unrecognized character
        if (no_match == Grammar.END) {
            System.out.println("Line Number" + " " + lineNum + "First Charater Postion " +  " "+ FirstCharPostion+ "unrecognized character: "+ (int) this.chr+ this.chr);
        }

        return new Token(no_match, "", lineNum, FirstCharPostion);
    }    

    // Get method for Tokens
    Token getToken() {

        // Variables
        int lineNum, FirstCharPostion;

        // Ignores white spaces
        while (Character.isWhitespace(this.chr)) {
            getNext();
        }

        // Sets the line number and the position of the first character
        lineNum = this.lineNum;
        FirstCharPostion = this.FirstCharPostion;

       //this checks for the expected terminals that we expect to see throughout our code
        switch (this.chr) {

            case ':':
                if (getNext() != '=') {
                    getNext();
                    return new Token(Grammar.COLON, "", lineNum, FirstCharPostion);
                }

                else {
                    getNext();
                    return new Token(Grammar.ASSIGN, "", lineNum, FirstCharPostion);
                }

            case ';':
                getNext();
                return new Token(Grammar.SEMICOLON, "", lineNum, FirstCharPostion);

            case '<':

                return symbol('=', Grammar.LT_EQUAL, Grammar.LT, lineNum, FirstCharPostion);

            case '=':
                return symbol('=', Grammar.EQUAL, Grammar.ASSIGN, lineNum, FirstCharPostion);

            case '!':
                if (getNext() == '=') {
                    return symbol('=', Grammar.NOT_EQUAL, Grammar.NOT, lineNum, FirstCharPostion);
                }

            case '>':
                return symbol('=', Grammar.GT_EQUAL, Grammar.GT, lineNum, FirstCharPostion);

            case '+':
                getNext();
                return new Token(Grammar.ADD, "", lineNum, FirstCharPostion);

            case '-':
                getNext();
                return new Token(Grammar.SUBTRACT, "", lineNum, FirstCharPostion);

            case '*':
                getNext();
                return new Token(Grammar.MULTIPLY, "", lineNum, FirstCharPostion);

            case '/':
                return comments(lineNum, FirstCharPostion);

            case '(':
                getNext();
                return new Token(Grammar.OPEN_PARA, "", lineNum, FirstCharPostion);

            case ')':
                getNext();
                return new Token(Grammar.CLOSE_PARA, "", lineNum, FirstCharPostion);

            case '_':
                getNext();
                return new Token(Grammar.UNDERSCORE, "", lineNum, FirstCharPostion);
                // the smallest character for a char
            case '\u0000':
                return new Token(Grammar.END, "", this.lineNum, this.FirstCharPostion);

            default:
                return Value(lineNum, FirstCharPostion);
        }
    }

    

    // Method to get the getNext character in a line
    

    // Determines if "/" symbol is for mathematical division or belonging to a
    // comment
    Token comments(int line, int pos) {

        // Mathematical division
        if (getNext() != '/') {
            return new Token(Grammar.DIVIDE, "", line, pos);
        }

        getNext();

            // Identifies empty space
            if (this.chr == '\n') {
                getNext();
                return getToken();
            } else {
                getNext();
            }
            return null;
        }

    
    
    // Prints out each Token
    void printTokens() {

        // variable
        Token token;

        // While the current Token is not END, continues to print Tokens
        while ((token = getToken()).Grammar != Grammar.END) {

            System.out.println(token);
        }

        System.out.println(token);
    }

   
    public static void main(String[] args) throws FileNotFoundException {

        String filePath;

        System.out.println("Enter File Path: ");
        Scanner input = new Scanner(System.in);
        filePath = input.nextLine();

        File file = new File(filePath);
        Scanner scan = new Scanner(file);
        String generatedTokenFile = " ";
        while (scan.hasNext()) {
            generatedTokenFile += scan.nextLine() + "\n";
        }

        // Runs LexicalAnalyzer12 in main
        LexicalAnalyzer12 la = new LexicalAnalyzer12(generatedTokenFile);
        
        
       // System.out.println(la.Value(1, 2));
       la.printTokens();


///Users/ejazike/Documents/COSC 455/examples/ab.txt
      
                        
                                        
        
        
    }
}
