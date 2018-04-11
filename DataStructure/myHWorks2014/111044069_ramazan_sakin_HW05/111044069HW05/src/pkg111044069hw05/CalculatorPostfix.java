
package pkg111044069hw05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author ramazan
 */
public class CalculatorPostfix {
    
    /** A list of operators. */
  /** The operators */
  private static final String[] OPERATORS = {"+","-","/","*","(",")","=",
      "sin","cos","tan","cot","exp","log","sqrt","ceil","floor", "^" ,
      "input", "var", "print", "loop", "++", "--", "prints" };
  
  private  LinkedList<variable> variables;
  private Stack<LinkedList<variable>>  stackVarList;
  private  File file;
  private  FileReader fileReader;
  private  BufferedReader bufferedReader;

    public CalculatorPostfix(String filePath) throws FileNotFoundException {
        this.stackVarList = new Stack<>();
        variables = new LinkedList<>();
        file = new File(filePath);
        fileReader = new FileReader(file);
        bufferedReader = new BufferedReader(fileReader);
    }
  
  
  // Data Field
  /** The operand stack. */
  private Stack < String > operandStack;

  // Methods
  /** Evaluates the current operation.
      This function pops the two operands off the operand
      stack and applies the operator.
      @param op A character representing the operator
      @return The result of applying the operator
      @throws EmptyStackException if pop is attempted on
              an empty stack
   */
  private String evalOp(String opr) throws ConverterInfixToPostfix.SyntaxErrorException, IOException {
    // Pop the two operands off the stack.
    String rhs = operandStack.pop();
    String lhs;
    Double result = null;
    int index, index2 = 0;
    double tempD;
    fileReader = new FileReader(file);
    bufferedReader = new BufferedReader(fileReader);
    
    // Evaluate the operator.
    switch (opr) {
        
      case "+":
        lhs = operandStack.pop();
        if( (index = searchVar(rhs)) != -1 && (index2 = searchVar(lhs)) == -1)
            result = variables.get(index).getValue()  + Double.parseDouble(lhs);
        else if( (index = searchVar(rhs)) == -1 && (index2 = searchVar(lhs)) != -1 )
            result = Double.parseDouble(rhs) + variables.get(index2).getValue();
        else if( searchVar(rhs) == -1 && searchVar(lhs) == -1)
            result = Double.parseDouble(lhs) + Double.parseDouble(rhs);
        else if((index = searchVar(rhs)) != -1 && (index2 = searchVar(lhs)) != -1)
            result = variables.get(index2).getValue() + variables.get(index).getValue();
        break;
      case "^":
        lhs = operandStack.pop();
        if( (index = searchVar(rhs)) != -1 && (index2 = searchVar(lhs)) == -1) 
            result = Math.pow( Double.parseDouble(lhs), variables.get(index).getValue() );
        else if( (index = searchVar(rhs)) == -1 && (index2 = searchVar(lhs)) != -1 )
            result = Math.pow(variables.get(index2).getValue(), Double.parseDouble(rhs));
        else if( searchVar(rhs) == -1 && searchVar(lhs) == -1)
            result = Math.pow(Double.parseDouble(lhs), Double.parseDouble(rhs)) ;
        else if((index = searchVar(rhs)) != -1 && (index2 = searchVar(lhs)) != -1)
            result = Math.pow(variables.get(index2).getValue(), variables.get(index).getValue());
        break;
      case "-":
        lhs = operandStack.pop();
        if( (index = searchVar(rhs)) != -1 && (index2 = searchVar(lhs)) == -1) 
            result = Double.parseDouble(lhs) - variables.get(index).getValue() ;
        else if( (index = searchVar(rhs)) == -1 && (index2 = searchVar(lhs)) != -1 )
            result = variables.get(index2).getValue() - Double.parseDouble(rhs);
        else if( searchVar(rhs) == -1 && searchVar(lhs) == -1)
            result = Double.parseDouble(lhs) - Double.parseDouble(rhs) ;
        else if((index = searchVar(rhs)) != -1 && (index2 = searchVar(lhs)) != -1)
            result = variables.get(index2).getValue() - variables.get(index).getValue();
        break;
      case "/":
        lhs = operandStack.pop();  
        if( (index = searchVar(rhs)) != -1 && (index2 = searchVar(lhs)) == -1) 
            result = Double.parseDouble(lhs) / variables.get(index).getValue() ;
        else if( (index = searchVar(rhs)) == -1 && (index2 = searchVar(lhs)) != -1 )
            result = variables.get(index2).getValue() / Double.parseDouble(rhs);
        else if( searchVar(rhs) == -1 && searchVar(lhs) == -1)
            result = Double.parseDouble(lhs) / Double.parseDouble(rhs);
        else if((index = searchVar(rhs)) != -1 && (index2 = searchVar(lhs)) != -1)
            result = variables.get(index2).getValue() / variables.get(index).getValue();
        break;
      case "*":
        lhs = operandStack.pop();
        if( (index = searchVar(rhs)) != -1 && (index2 = searchVar(lhs)) == -1) 
            result = variables.get(index).getValue() * Double.parseDouble(lhs);
        else if( (index = searchVar(rhs)) == -1 && (index2 = searchVar(lhs)) != -1 )
            result = Double.parseDouble(rhs) * variables.get(index2).getValue();
        else if( searchVar(rhs) == -1 && searchVar(lhs) == -1)
            result = Double.parseDouble(lhs) * Double.parseDouble(rhs);
        else if((index = searchVar(rhs)) != -1 && (index2 = searchVar(lhs)) != -1)
            result = variables.get(index2).getValue() * variables.get(index).getValue();
        break;
      case "=":
        lhs = operandStack.pop();
        if( Character.isDigit(rhs.charAt(0)) || rhs.charAt(0) == '-' ){
            variables.get(searchVar(lhs)).setValue(Double.parseDouble(rhs));
            result = Double.parseDouble(rhs);
        }else{
            variables.get(searchVar(lhs)).setValue(variables.get(searchVar(rhs)).getValue());
            result = variables.get(searchVar(rhs)).getValue();
        }
        
      break;
      case "sin":
          if( isNumeric(rhs) || Character.isDigit(rhs.charAt(0)) )
            result = Math.sin(Math.toRadians(Double.parseDouble(rhs)));
          else if( ( index = searchVar(rhs)) != -1 ){
              if( !variables.get(index).getStatus() ){
                  throw new ConverterInfixToPostfix.SyntaxErrorException(
                          "Line :" + ConverterInfixToPostfix.numberOfLine+"  Syntax Error : Unitilliazed variable -> " + rhs);
              }
              result = Math.sin(Math.toRadians(variables.get(index).getValue()));
          
          }else {
              throw new ConverterInfixToPostfix.SyntaxErrorException(
                      "Line :" + ConverterInfixToPostfix.numberOfLine+"  Syntax Error : Undecleared variable -> " + rhs);
          }
      break;    
      case "cos":
          if( isNumeric(rhs) || Character.isDigit(rhs.charAt(0)) )
            result = Math.cos(Math.toRadians(Double.parseDouble(rhs)));
          else if( ( index = searchVar(rhs)) != -1 ){
           if( !variables.get(index).getStatus() ){
                  throw new ConverterInfixToPostfix.SyntaxErrorException(
                    "Line :" + ConverterInfixToPostfix.numberOfLine+"  Syntax Error : Unitilliazed variable -> " + rhs);
        
           }   
            result = Math.cos(Math.toRadians(variables.get(index).getValue()));
          }else {
              throw new ConverterInfixToPostfix.SyntaxErrorException(
                      "Line :" + ConverterInfixToPostfix.numberOfLine+"  Syntax Error : Undecleared variable -> " + rhs);
          }
      break;
      case "log":
          if( isNumeric(rhs) || Character.isDigit(rhs.charAt(0)) )
            result = Math.log10(Double.parseDouble(rhs));
          else if( ( index = searchVar(rhs)) != -1 ){
            if( !variables.get(index).getStatus() ){
                  throw new ConverterInfixToPostfix.SyntaxErrorException(
                          "Line :" + ConverterInfixToPostfix.numberOfLine+"  Syntax Error : Unitilliazed variable -> " + rhs);
              }
            result = Math.log10(variables.get(index).getValue());
          }else {
              throw new ConverterInfixToPostfix.SyntaxErrorException(
                      "Line :" + ConverterInfixToPostfix.numberOfLine+"  Syntax Error : Undecleared variable -> " + rhs);
          }
      break;
      case "exp":
          if( isNumeric(rhs) || Character.isDigit(rhs.charAt(0)) )
            result = Math.exp(Double.parseDouble(rhs));
          else if( ( index = searchVar(rhs)) != -1 ){
            if( !variables.get(index).getStatus() ){
                  throw new ConverterInfixToPostfix.SyntaxErrorException(
                          "Line :" + ConverterInfixToPostfix.numberOfLine+"  Syntax Error : Unitilliazed variable -> " + rhs);
              }
            result = Math.exp(variables.get(index).getValue());
          }else {
              throw new ConverterInfixToPostfix.SyntaxErrorException(
                      "Line :" + ConverterInfixToPostfix.numberOfLine+"  Syntax Error : Undecleared variable -> " + rhs);
          }
      break;
      case "sqrt":
          if( isNumeric(rhs) || Character.isDigit(rhs.charAt(0)) )
            result = Math.sqrt(Double.parseDouble(rhs));
          else if( ( index = searchVar(rhs)) != -1 ){
            if( !variables.get(index).getStatus() ){
                  throw new ConverterInfixToPostfix.SyntaxErrorException(
                          "Line :" + ConverterInfixToPostfix.numberOfLine+"  Syntax Error : Unitilliazed variable -> " + rhs);
            }  
            result = Math.sqrt(variables.get(index).getValue());
          }else {
              throw new ConverterInfixToPostfix.SyntaxErrorException(
                      "Line :" + ConverterInfixToPostfix.numberOfLine+"  Syntax Error : Undecleared variable -> " + rhs);
          }
      break;
      case "cot":
          if( isNumeric(rhs) || Character.isDigit(rhs.charAt(0)) )
            result = 1/Math.tan(Math.toRadians(Double.parseDouble(rhs)));
          else if( ( index = searchVar(rhs)) != -1 ){
            if( !variables.get(index).getStatus() ){
                  throw new ConverterInfixToPostfix.SyntaxErrorException(
                  "Line :" + ConverterInfixToPostfix.numberOfLine+"  Syntax Error : Unitilliazed variable -> " + rhs);
            
              }
            result = 1/Math.tan(Math.toRadians(variables.get(index).getValue()));
          }else {
              throw new ConverterInfixToPostfix.SyntaxErrorException(
                      "Line :" + ConverterInfixToPostfix.numberOfLine+" Syntax Error : Undecleared variable -> " + rhs);
          }
      break;
      case "tan":
          if( isNumeric(rhs) || Character.isDigit(rhs.charAt(0)) )
            result = Math.tan(Math.toRadians(Double.parseDouble(rhs)));
          else if( ( index = searchVar(rhs)) != -1 ){
            if( !variables.get(index).getStatus() ){
                throw new ConverterInfixToPostfix.SyntaxErrorException(
                       "Line :" + ConverterInfixToPostfix.numberOfLine+" Syntax Error : Unitilliazed variable -> " + rhs);
              }
            result = Math.tan(Math.toRadians(variables.get(index).getValue()));
          }else {
              throw new ConverterInfixToPostfix.SyntaxErrorException(
                      "Line :" + ConverterInfixToPostfix.numberOfLine+" Syntax Error : Undecleared variable -> " + rhs);
          }
      break;
      case "ceil":
          if( isNumeric(rhs) || Character.isDigit(rhs.charAt(0)) )
            result = Math.ceil(Double.parseDouble(rhs));
          else if( ( index = searchVar(rhs)) != -1 ){
              if( !variables.get(index).getStatus() ){
                  throw new ConverterInfixToPostfix.SyntaxErrorException(
                      "Line :" + ConverterInfixToPostfix.numberOfLine+ " Syntax Error : Unitilliazed variable -> " + rhs);
              }
              result = Math.ceil(variables.get(index).getValue());
          }else {
              throw new ConverterInfixToPostfix.SyntaxErrorException(
                      "Line :" + ConverterInfixToPostfix.numberOfLine+" Syntax Error : Undecleared variable -> " + rhs);
          }
      break;
      case "floor":
          if( isNumeric(rhs) || Character.isDigit(rhs.charAt(0)) )
            result = Math.floor(Double.parseDouble(rhs));
          else if( (index = searchVar(rhs)) != -1 ){
            if( !variables.get(index).getStatus() ){
                  throw new ConverterInfixToPostfix.SyntaxErrorException(
                          "Line :" + ConverterInfixToPostfix.numberOfLine+" Syntax Error : Unitilliazed variable -> " + rhs);
              }
            result = Math.floor(variables.get(index).getValue());
          }else {
              throw new ConverterInfixToPostfix.SyntaxErrorException(
                        "Line :" + ConverterInfixToPostfix.numberOfLine+" Syntax Error : Undecleared variable -> " + rhs);
          }
      break;
      case "var":
          if( isNumeric(rhs) || Character.isDigit(rhs.charAt(0)) )
            System.out.println("Line :" + ConverterInfixToPostfix.numberOfLine+" Syntax Error : variable name can not be a number -> " + rhs);
          else if( (searchVar(rhs)) == -1 ){
              variable newVar = new variable(rhs);
              variables.addLast(newVar);
              return newVar.getName();
          }else{
              throw new ConverterInfixToPostfix.SyntaxErrorException(
                      "Line :" + ConverterInfixToPostfix.numberOfLine+" Syntax Error : Redecleared variable -> " + rhs);
          } 
      break;
      case "print":
          if( (index = searchVar(rhs)) != -1 ){
              if( variables.get(index).getStatus() ){
                System.out.println(variables.get(index).getValue());
                result = variables.get(index).getValue();
              }else
                  throw new ConverterInfixToPostfix.SyntaxErrorException( 
                          "Line :" + ConverterInfixToPostfix.numberOfLine+" Syntax Error : Unitialized variable -> " + rhs);
          }else if( isNumeric(rhs) || Character.isDigit(rhs.charAt(0)) || Character.isDigit(rhs.charAt(1)) ){
              System.out.println(rhs);
              result = Double.parseDouble(rhs);
          }else{
              throw new ConverterInfixToPostfix.SyntaxErrorException(
                      "Line :" + ConverterInfixToPostfix.numberOfLine+"  Syntax Error : Undecleared variable -> " + rhs);
          }  
      break;
      case "prints":
          if( (index = searchVar(rhs)) != -1 ){
              if( variables.get(index).getStatus() ){
                System.out.print(variables.get(index).getValue());
                result = variables.get(index).getValue();
              }else
                  throw new ConverterInfixToPostfix.SyntaxErrorException( 
                          "Line :" + ConverterInfixToPostfix.numberOfLine+" Syntax Error : Unitialized variable -> " + rhs);
          }else if( isNumeric(rhs) || Character.isDigit(rhs.charAt(0)) || Character.isDigit(rhs.charAt(1)) ){
              System.out.print(rhs);
              result = Double.parseDouble(rhs);
          }else{
              throw new ConverterInfixToPostfix.SyntaxErrorException(
                      "Line :" + ConverterInfixToPostfix.numberOfLine+"  Syntax Error : Undecleared variable -> " + rhs);
          }  
      break;
      case "input":
          System.out.println("Please enter a number for assigned number: "+ rhs);
          Scanner scan = new Scanner(System.in);
          if( (index = searchVar(rhs)) != -1 ){
              tempD = scan.nextDouble();
              variables.get(index).setValue(tempD);
          result = variables.get(index).getValue();
          }else{
              throw new ConverterInfixToPostfix.SyntaxErrorException(
                      "Line :" + ConverterInfixToPostfix.numberOfLine+"Syntax Error : Undecleared variable -> " + rhs);
          }
      break;
      case "++":
          if( isNumeric(rhs) || Character.isDigit(rhs.charAt(0)) )
            result = Double.parseDouble(rhs) + 1.0;
          else if( (index = searchVar(rhs)) != -1 ){
            if( !variables.get(index).getStatus() ){
                  throw new ConverterInfixToPostfix.SyntaxErrorException(
                          "Line :" + ConverterInfixToPostfix.numberOfLine+" Syntax Error : Unitilliazed variable -> " + rhs);
              }
            variables.get(index).setValue(variables.get(index).getValue()+1.0);
            result = variables.get(index).getValue();
          }else {
              throw new ConverterInfixToPostfix.SyntaxErrorException(
                        "Line :" + ConverterInfixToPostfix.numberOfLine+" Syntax Error : Undecleared variable -> " + rhs);
          }
      break;
          case "--":
          if( isNumeric(rhs) || Character.isDigit(rhs.charAt(0)) )
            result = Double.parseDouble(rhs) - 1.0;
          else if( (index = searchVar(rhs)) != -1 ){
            if( !variables.get(index).getStatus() ){
                  throw new ConverterInfixToPostfix.SyntaxErrorException(
                          "Line :" + ConverterInfixToPostfix.numberOfLine+" Syntax Error : Unitilliazed variable -> " + rhs);
              }
            variables.get(index).setValue(variables.get(index).getValue()-1.0);
            result = variables.get(index).getValue();
          }else {
              throw new ConverterInfixToPostfix.SyntaxErrorException(
                        "Line :" + ConverterInfixToPostfix.numberOfLine+" Syntax Error : Undecleared variable -> " + rhs);
          }
      break;
      case "loop":
          
          String[] lines=new String[20];
          String oneLine;
          int loopNumbers[]  = new int[20];
          int loop=0, count=0;
          int startIndex[] = new int[20];
          int loopControl=1, i;
          int beginC=0;
          int endC=0;
          
          for( i=0; i<ConverterInfixToPostfix.numberOfSkipLine ; ++i )
              oneLine = bufferedReader.readLine();
          
          while( ( oneLine = bufferedReader.readLine() ) != null ){
              ++ConverterInfixToPostfix.numberOfLine;
              if( oneLine.contains("loop") )
                  ++loopControl;
              else if( oneLine.contains("end") ){
                  --loopControl;
                  ++endC;
              }
              if( oneLine.contains("begin") )
                  ++beginC;
              
              lines[count] = ConverterInfixToPostfix.convert(oneLine);
              ++count;
              if( loopControl == 0 )
                  break;
          }
          
          result = (double)count;
          
          if( loopControl != 0 ){
              throw new ConverterInfixToPostfix.SyntaxErrorException(
                      "end statement is missing for a loop!!!");
          }
          
          if( beginC != endC ){
              throw new ConverterInfixToPostfix.SyntaxErrorException(
                      "begin Statement is missing for a loop!!!");
          }
          
          stackVarList.push(variables);
          
          if( isNumeric(rhs) )
              loopNumbers[loop] = Integer.parseInt(rhs);
          else if( (index = searchVar(rhs)) != -1 ){
              loopNumbers[loop] = 
                      (int)variables.get(index).getValue();
          }else{
              throw new ConverterInfixToPostfix.SyntaxErrorException(
                        "Line :" + ConverterInfixToPostfix.numberOfLine+
                      " Syntax Error : Undecleared variable -> " + rhs);        
          }
          
          startIndex[0]=0;
          count=startIndex[loop];
          while( loopNumbers[0] >= 1 ){
             
              if( lines[count].contains("loop") ){
                  ++loop;
                  String[] val = lines[count].split(" ");
                  if( isNumeric(val[0]) )
                    loopNumbers[loop] = Integer.parseInt(val[0]);
                  else if( (index = searchVar(val[0])) != -1 )
                    loopNumbers[loop] = (int)(variables.get(searchVar(val[0])).getValue()-1);
                  else{
                    throw new ConverterInfixToPostfix.SyntaxErrorException(
                        "Line :" + ConverterInfixToPostfix.numberOfLine+
                      " Syntax Error : Undecleared variable -> " + rhs);        
                  }
                  
                  startIndex[loop] = count+1;  // to pass the loop statement
                  stackVarList.push(variables);
              }else if( lines[count].contains("end") ){
                  if( loopNumbers[loop] == 0 ){
                      --loop;
                      variables = new LinkedList<>();
                      variables.addAll(stackVarList.pop());
                  }else{
                    --loopNumbers[loop];
                    count=startIndex[loop];
                  }
              }else if( lines[count].contains("begin") ){
                  // Skip the line!
              }else {
                  if( lines[count].compareTo("") != 0 && !lines[count].contains("//")  )
                  eval(lines[count]);
              }
              
              ++count;
          }
      break;
      case "":
          // Skip the empty line for loops
      break;
      default:
          throw new ConverterInfixToPostfix.SyntaxErrorException(
                  "Line "+ ConverterInfixToPostfix.numberOfLine+": Unnown operator Evception");
    }
    
    return result.toString();
  }
  
  public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if( str.charAt(i) != '-' ){
                if (Character.isDigit(str.charAt(i)) == false) {
                    return false;
                }
            }
        }
        return true;
   }
 
  
  public int searchVar( String target ) {
        for(int i=0; i< variables.size(); ++i){
            if( target.compareTo(variables.get(i).getName()) == 0  )
                return i;
        }
        // if the variable was not declared
        return -1;
    }

  /** Determines whether a character is an operator.
      @param op The character to be tested
      @return true if the character is an operator
   */
  private int isOperator(String op) {
      for( int i=0; i < OPERATORS.length ; ++i ){
          if( op.compareTo(OPERATORS[i])  == 0)
              return i;
      }
      return -1;
  }
  
  /** Evaluates a postfix expression.
      @param expression The expression to be evaluated
      @return The value of the expression
      @throws SyntaxErrorException if a syntax error is detected
   */
  public Double eval(String expression) throws ConverterInfixToPostfix.SyntaxErrorException, IOException {
    // Create an empty stack.
    operandStack = new Stack<>();
    
    // Process each token.
    StringTokenizer tokens = new StringTokenizer(expression);
    try {
      while (tokens.hasMoreTokens()) {
        String nextToken = tokens.nextToken();
        // Does it start with a digit?
        if (Character.isDigit(nextToken.charAt(0))) {
          // Push value onto operand stack.
          operandStack.push( nextToken );
        } // Is it an operator?
        else if (isOperator(nextToken ) > -1) {
          // Evaluate the operator
            String result = evalOp(nextToken);
            if( nextToken.compareTo("loop")==0 )
                return Double.parseDouble(result);
            // Push result onto the operand stack.
            operandStack.push( result );
        }
        else if( Character.isJavaIdentifierStart(nextToken.charAt(0)) ){
            operandStack.push( nextToken );
      }else {
          // Invalid character.
          throw new ConverterInfixToPostfix.SyntaxErrorException(
              "Invalid character was found : " + nextToken);
        }
      } // End while.
      
      Double answer = null;
      // No more tokens - pop result from operand stack.
      if( isNumeric(operandStack.peek())  )
        answer = Double.parseDouble(operandStack.pop());
      else{
         return 0.0;
      }
      // Operand stack should be empty.
      if (operandStack.empty()) {
        return answer;
      }
      else {
        // Indicate syntax error.
        throw new ConverterInfixToPostfix.SyntaxErrorException(
            "Syntax Error: Stack should be empty");
      }
    }
    catch (EmptyStackException ex) {
      // Pop was attempted on an empty stack.
      throw new ConverterInfixToPostfix.SyntaxErrorException(
          "Syntax Error: The stack is empty");
    }
  }
    
}
