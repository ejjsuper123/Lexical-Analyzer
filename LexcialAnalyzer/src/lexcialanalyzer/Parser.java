/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lexcialanalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author ejazike
 */
public class Parser {
    
    public static LexicalAnalyzer12 la;
    public static LexicalAnalyzer12.Token token;
    
    public static void main(String args[]) throws FileNotFoundException{
         String filePath;

        System.out.println("Enter File Path: ");
        Scanner input = new Scanner(System.in);
        filePath = input.nextLine();

        File file = new File(filePath);
        Scanner scan = new Scanner(file);
        String source = " ";
        while (scan.hasNext()) {
            source += scan.nextLine() + "\n";
        }
        la = new LexicalAnalyzer12(source);
        token = la.getToken();
      
        
        if(!program()){
       //failed to parse program.
       return;
        }
    }
    public static boolean program(){
        if(!la.match(token,LexicalAnalyzer12.Grammar.PROGRAM)){
            System.out.println("Missing keyword program at line: " + token.lineNum + ": " + token.FirstCharPostion);
            return false; 
        }
        token = la.getToken();
        if(!la.match(token,LexicalAnalyzer12.Grammar.IDENTIFIER)){
            return false;
        }
        token = la.getToken();
        if(!la.match(token,LexicalAnalyzer12.Grammar.COLON)){
            return false;
        }
        token = la.getToken();
        if(!body()){
            return false;
        }
        if(!la.match(token,LexicalAnalyzer12.Grammar.END)){
            return false;
        }
        token = la.getToken();
        return true;
    }
    public static boolean body(){
        declarations();
        
        if(!statements()){
            return false;
        }
        return true;
    }
    public static boolean declarations(){
        while(declaration()){
            //empty on purpose
        }
        return true;
    }
    public static boolean declaration(){
        if(!la.match(token,LexicalAnalyzer12.Grammar.BOOL)&&!la.match(token,LexicalAnalyzer12.Grammar.INT)){
            return false;
        }
        token = la.getToken();
        if(!la.match(token,LexicalAnalyzer12.Grammar.IDENTIFIER)){
            return false;
        }
        token = la.getToken();
        if(!la.match(token,LexicalAnalyzer12.Grammar.SEMICOLON)){
            return false;
        }
        token = la.getToken();
        return true;
    }
    public static boolean statements(){
        if(!statement()){
            return false;
        }
        while(la.match(token,LexicalAnalyzer12.Grammar.SEMICOLON)){
            token = la.getToken();
            if(!statement()){
                return false;
            }
        }
        return true;
    }
    public static boolean statement(){
        if(assignmentStatement()){
            return true;
        }
        if(conditionalStatement()){
            return true;
        }
        if(iterativeStatement()){
            return true;
        }
        if(printStatment()){
            return true;
        }
        return false;
    }
    public static boolean assignmentStatement(){
        if(!la.match(token,LexicalAnalyzer12.Grammar.IDENTIFIER)){
            return false;
        }
        token = la.getToken();
        if(!la.match(token,LexicalAnalyzer12.Grammar.ASSIGN)){
            return false;
        }
        token = la.getToken();
        if(!expression()){
            return false;
        }
        return true;
    }
    public static boolean conditionalStatement(){
        if(!la.match(token,LexicalAnalyzer12.Grammar.IF)){
            return false;
        }
        token = la.getToken();
        if(!expression()){
            return false;
        }
        if(!la.match(token,LexicalAnalyzer12.Grammar.THEN)){
            return false;
        }
        token = la.getToken();
        if(!body()){
            return false;
        }
        if(la.match(token,LexicalAnalyzer12.Grammar.ELSE)){
            token = la.getToken();
            if(!body()){
                return false;
            }
        }
        if(!la.match(token,LexicalAnalyzer12.Grammar.FI)){
            return false;
        }
        token = la.getToken();
        return true;
    }
    public static boolean iterativeStatement(){
        if(!la.match(token,LexicalAnalyzer12.Grammar.WHILE)){
            return false;
        }
        token = la.getToken();
        if(!expression()){
            return false;
        }
        if(!la.match(token,LexicalAnalyzer12.Grammar.DO)){
            return false;
        }
        token = la.getToken();
        if(!body()){
            return false;
        }
        if(!la.match(token,LexicalAnalyzer12.Grammar.OD)){
            return false;
        }
        token = la.getToken();
        return true;
    }
    public static boolean printStatment(){
        if(!la.match(token,LexicalAnalyzer12.Grammar.PRINT)){
            return false;
        }
        token = la.getToken();
        if(!expression()){
            return false;
        }
        return true;
    }
    public static boolean expression(){
        if(!simpleExpression()){
            return false;
        }
        if(relationalOperator()){
            if(!simpleExpression()){
                return false;
            }
        }
        return true;
    }
    public static boolean relationalOperator(){
        if(la.match(token,LexicalAnalyzer12.Grammar.LT)||
           la.match(token,LexicalAnalyzer12.Grammar.LT_EQUAL)||
           la.match(token,LexicalAnalyzer12.Grammar.EQUAL)||
           la.match(token,LexicalAnalyzer12.Grammar.NOT_EQUAL)||
           la.match(token,LexicalAnalyzer12.Grammar.GT)||
           la.match(token,LexicalAnalyzer12.Grammar.GT_EQUAL)){
           token= la.getToken();
           return true;   
        }
        return false;
    }
    public static boolean simpleExpression(){
        if(!term()){
            return false;
        }
        if(additiveOperator()){
            if(!term()){
                return false;
            }
        }
        return true;
    }
    public static boolean additiveOperator(){
        if(la.match(token,LexicalAnalyzer12.Grammar.ADD)||
           la.match(token,LexicalAnalyzer12.Grammar.SUBTRACT)||
           la.match(token,LexicalAnalyzer12.Grammar.OR)){
           token = la.getToken();
           return true;
        }
       return false;
    }
    
    public static boolean term(){
        if(!factor()){
            return false;
        }
        if(multiplicativeOperator()){
            if(!factor()){
                return false;
            }
        }
        return true;
    }
    public static boolean multiplicativeOperator(){
        if(la.match(token,LexicalAnalyzer12.Grammar.MULTIPLY)||
           la.match(token,LexicalAnalyzer12.Grammar.DIVIDE)||
           la.match(token,LexicalAnalyzer12.Grammar.AND)){
           token = la.getToken();
           return true;
        }
        return false;   
    }
    public static boolean factor(){
        unaryOperator();
        
        if(literal()){
            return true;
        }
        if(la.match(token,LexicalAnalyzer12.Grammar.IDENTIFIER)){
            token = la.getToken();
            return true;
        }
        if(!la.match(token,LexicalAnalyzer12.Grammar.OPEN_PARA)){
            return false;
        }
        token = la.getToken();
        if(!expression()){
            return false;
        }
        if(la.match(token,LexicalAnalyzer12.Grammar.CLOSE_PARA)){
            return false;
        }
        token = la.getToken();
        return true;
    }
    public static boolean unaryOperator(){
        if(la.match(token,LexicalAnalyzer12.Grammar.SUBTRACT)||la.match(token,LexicalAnalyzer12.Grammar.NOT)){
            la.getNext();
            return true;
        }
        return false;
    }
    public static boolean literal(){
        if(la.match(token,LexicalAnalyzer12.Grammar.TRUE)||la.match(token,LexicalAnalyzer12.Grammar.FALSE)||la.match(token,LexicalAnalyzer12.Grammar.NUM)){
            token = la.getToken();
            return true;
        }
        return false;
    }

}
