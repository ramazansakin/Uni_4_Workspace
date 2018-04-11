/**
 * GITLExpressions class
 * Has a GITLExpressionBase interface and a list of inner classes representing
 * the expressions in the GITL language.
 *
 * @author Orhan Aksoy - 09104302
 */

import java.util.*;

public class GITLExpressions {

    /** The maximum integer value above which there's overflow */
    protected static final int MAXIMUM_INTEGER = 2147483647;
    /**
     * Defines the evaluate, handleJump and checkMatch method interfaces
     *
     */
    public interface GITLExpressionBase {
        public void evaluate(GITLContext ctx) throws GITLException;
        public void handleJump(GITLContext ctx)  throws GITLException;
        public boolean checkMatch(LinkedList<String> tokens);
        

    }
    /**
     * Base expression class that provides the checkIfSeekingLabel function
     */
    public static class GITLExpression implements GITLExpressionBase {
        /**
         * Returns false by default. Each expression implements this function
         * and returns true if the tokens matches it.
         * @param tokens The token list of the current line
         * @return Match status.
         */
        public boolean checkMatch(LinkedList<String> tokens) {
            return false;
        }
        /**
         * Evaluates the current line. Each expression implements this function
         * and does its own function during interpretation.
         * @param ctx The context in which to interpret the line
         * @throws GITLException
         */
        public void evaluate(GITLContext ctx) throws GITLException {
        }
        /**
         * This function is called by the parser when a jump occurs to this
         * expression. No expressions other than label and function definition
         * handles it.
         */
        public void handleJump(GITLContext ctx)   throws GITLException{
        }

    }

    /**
     * GITLComment expression represents the comment lines that
     * begin with "//"
     */
    public static class GITLComment extends GITLExpression {

        /**
         * Creates a new GITLComment object using the parser
         * @param interpreter The interpreter using this expression
         */
        public GITLComment(GITLInterpreter interpreter) {

        }

        /**
         * Checks to see if the input tokens match a comment expression
         * @param tokens The code line
         * @return true if the input code line is a comment
         */
        public boolean checkMatch(LinkedList<String> tokens) {
            return tokens.getFirst().equals("//");
        }
        /**
         * Does nothing.
         * @param ctx  The context in which this expression will be evaluated.
         */
        public void evaluate(GITLContext ctx) throws GITLException {
       }

    }

    /**
     * GITLLabel expression represents the label lines that
     * the interpreter can jump to
     */

    public static class GITLLabel  extends GITLExpression {

        /**
         * Creates a new GITLLabel object using the parser
         * @param interpreter The interpreter using this expression
         */
        public GITLLabel(GITLInterpreter interpreter) {

        }
        /**
         * Checks to see if the input tokens match a label expression
         * @param tokens The code line
         * @return true if the input code line is a label
         */
        public boolean checkMatch(LinkedList<String> tokens) {
            return (tokens.size() == 2) && tokens.getFirst().equals("label") ;
        }
        /**
         * This function is called by the parser when a jump occurs to this
         * expression
         */
        public void handleJump(GITLContext ctx) throws GITLException {

            ctx.addLabel(ctx.getParams().get(1));
            // we're not running, but looking for a label at the moment,
            // So check if this is the correct label. If so, make a jump call to the
            // current location, resetting this state.

            if ( ctx.getJumpLabel().equals(ctx.getParams().get(1)) ) {
                    ctx.jumpLabel(ctx.getJumpLabel());
                }
            }


        /**
         * Stores the label in the context. During a conditional expression
         * evaluation, the list of labels are searched to jump to to this
         * location if there is a match.
         *
         * @param ctx  The context in which this expression will be evaluated.
         */
        public void evaluate(GITLContext ctx)  throws GITLException {
            ctx.addLabel(ctx.getParams().get(1));
       }

    }

    /**
     * GITLVariable expression represents the variable declaration
     * lines.
     */
    public static class GITLVariable  extends GITLExpression {

        /**
         * Creates a new GITLVariable object using the parser
         * @param interpreter The interpreter using this expression
         */
        public GITLVariable(GITLInterpreter interpreter) {

        }
        /**
         * Checks to see if the input tokens match a variable declaration
         * expression
         * @param tokens The code line
         * @return true if the input code line is a variable declaration
         */
        public boolean checkMatch(LinkedList<String> tokens) {
            return (tokens.size() == 2) && tokens.getFirst().equals("int") ;
        }
         /**
         * Creates a new variable in the current context and sets it value
         * to zero
         *
         * @param ctx  The context in which this expression will be evaluated.
         */
        public void evaluate(GITLContext ctx)  throws GITLException {

              ctx.addVariable(ctx.getParams().get(1));
        }

    }
    /**
     * GITLConditional expression represents the "if" statement.
     */
    public static class GITLConditional  extends GITLExpression {

        /**
         * Creates a new GITLConditional object using the parser
         * @param interpreter The interpreter using this expression
         */
        public GITLConditional(GITLInterpreter interpreter) {

        }

        /**
         * Checks to see if the input tokens match a conditional expression
         * @param tokens The code line
         * @return true if the input code line is a conditional expression
         */
        public boolean checkMatch(LinkedList<String> tokens) {
            return (tokens.size() == 3) && tokens.getFirst().equals("if");
        }
         /**
         * If the first parameter is nonzero, jumps to the label defined by
         * the second parameter
         *
         * @param ctx The context in which this expression will be evaluated.
         */
        public void evaluate(GITLContext ctx)  throws GITLException {

            String varName = ctx.getParams().get(1);
            String labelName = ctx.getParams().get(2);

            int var;

            // Check if the condition parameter is a constant
            try {
                // if it is a constant and > 0, jump to it
                var = Integer.parseInt(varName);
                if (var > 0 ) {
                    ctx.jumpLabel(labelName);
                }
                return;
            } catch(NumberFormatException nFE) {
               // it is a variable.
            }

            // it is a variable, not a constant.
            if ( ctx.getVariableValue(varName) > 0 ) {
                ctx.jumpLabel(labelName);
            }

        }

    }

    /**
     * GITLCall expression is used to call a function (print or scan).
     */
    public static class GITLCall  extends GITLExpression {

        /**
         * Creates a new GITLCall object using the parser
         * @param interpreter The interpreter using this expression
         */
        public GITLCall(GITLInterpreter interpreter) {

        }

        /**
         * Checks to see if the input tokens match a call expression
         * @param tokens The code line
         * @return true if the input code line is a function call
         */
        public boolean checkMatch(LinkedList<String> tokens) {
            return (tokens.size() == 4) && tokens.getFirst().equals("call") ;
        }
        /**
         * If the first parameter is 'print', prints out the contents of the first
         * parameter. If the first parameter is 'scan', it gets input from the
         * user and puts the result in the second parameter. Otherwise, makes
         * a custom function call.
         *
         * @param ctx The context in which this expression will be evaluated.
         */
        public void evaluate(GITLContext ctx)  throws GITLException {

             if (ctx.getParams().get(1).equals("print")) {
                 
                 // Process 'print' call.

                 // Check if the  value is a variable or a constant

                 try {
                     System.out.println(Integer.parseInt(ctx.getParams().get(2)));
                     return;
                 } catch(NumberFormatException nFE) {
                     // it is a variable.
                 }
                 System.out.println(">> " + ctx.getVariableValue(ctx.getParams().get(2)));

            } else if (ctx.getParams().get(1).equals("scan")) {

                 // Process 'scan' call.

                String ret = ctx.getParams().get(3);
                ctx.getVariableValue(ret);

                // If the variable did not exist, the line above would have thrown an exception.

                int val;

                try {
                    Scanner in = new Scanner(System.in);
                    val = in.nextInt();
                    in.close();
                } catch (InputMismatchException im) {
                    throw new GITLException("Input value invalid.");
                }
                ctx.setVariableValue(ret, val);
            } else if (ctx.getParams().get(1).equals("time")) {

                 // Process 'time' call.

                String ret = ctx.getParams().get(3);

                // Make sure that the return variable exists. If it doesn't
                // the following line throws exception.

                ctx.getVariableValue(ret);


                Long refTime = System.currentTimeMillis();

//                System.out.println("currentTimeMillis value: " + refTime);

                ctx.setVariableValue(ret, (int)(refTime - ctx.getLastTime()));
                ctx.setLastTime(refTime);

            } else {

                // Make a custom function call.

                String returnVariable = ctx.getParams().get(3);

                int inputVal;
                try {
                    inputVal = Integer.parseInt(ctx.getParams().get(2));
                } catch(NumberFormatException nFE) {
                    // it is a variable.
                    inputVal = ctx.getVariableValue(ctx.getParams().get(2));
                }
                ctx.callFunction(ctx.getParams().get(1), inputVal, returnVariable); 
           }

        }

    }

        /**
     * GITLReturn expression is used to return from function.
     */
    public static class GITLReturn  extends GITLExpression {

        /**
         * Creates a new GITLReturn object using the parser
         * @param interpreter The interpreter using this expression
         */
        public GITLReturn(GITLInterpreter interpreter) {

        }

        /**
         * Checks to see if the input tokens match a return expression
         * @param tokens The code line
         * @return true if the input code line is a return expression
         */
        public boolean checkMatch(LinkedList<String> tokens) {
            return (tokens.size() == 2) && tokens.getFirst().equals("return") ;
        }
        /**
         * Returns the first parameter to the caller.
         *
         * @param ctx The context in which this expression will be evaluated.
         */
        public void evaluate(GITLContext ctx)  throws GITLException {

            if (ctx.getActiveFunction() == null ) {
                throw new GITLException("Mismatched end statement");
            }

            int returnValue = -1;

            // Check if the return value is a variable or a constant
            try {
                // IT is a constant. Return it.
                returnValue = Integer.parseInt(ctx.getParams().get(1));
            } catch(NumberFormatException nFE) {
               // it is a variable. Retrieve its value from the context.
                returnValue = ctx.getVariableValue(ctx.getParams().get(1));
            }

            ctx.endCurrentFunction(returnValue);

        }

    }

    /**
     * GITLAssignment expression represents the lines that assign values to
     * variables. This class also handles arithmetic calculations.
     */
    public static class GITLAssignment  extends GITLExpression {

        private static final String OPERATORS = "+-*/()";
        private static final int[] PRECEDENCE = {1, 1, 2, 2, -1, -1};
        /**
         * Creates a new GITLAssignment object using the parser
          * @param interpreter The interpreter using this expression
        */
        public GITLAssignment(GITLInterpreter interpreter) {

        }

        /**
         * Checks to see if the input tokens match an assignment expression
         * @param tokens The code line
         * @return true if the input code line is an assignment expression
         */
        public boolean checkMatch(LinkedList<String> tokens) {
            return (tokens.size() > 1) && tokens.get(1).equals("=")  ;
        }
        /**
         * Converts the right side of teh assignment operator into postfix
         * form and evaluates it. During evaluation, retrieves variable values
         * from the context when necessary.
         *
         * @param ctx The context in which this command is executing
         * @throws GITLException
         */
        public void evaluate(GITLContext ctx)  throws GITLException {

            String keyword = ctx.getParams().remove(1);
            String varName = ctx.getParams().removeFirst();

             int res = evaluatePostfix(toPostfix(ctx.getParams()), ctx);

             ctx.setVariableValue(varName, res);

       }

        /**
         * returns true if the input parameter is an operator
         *
         */
        private static boolean isOperator(String op) {
            final String operators = "+-*/()";
            return operators.contains(op);
        }

        /**
         * returns the precedence of the operator
         * @param op the operator
         * @return its precedence.
         */
        private static int precedence (char op) {
            return PRECEDENCE[OPERATORS.indexOf(op)];
        }

        /**
         * Evaluates the postfix expression using the current context.
         * @param postfix The postfix expression
         * @param ctx The current context
         * @return The evaluated value of the expression.
         */
        private static int evaluatePostfix(LinkedList<String> postfix, GITLContext ctx)  throws GITLException{
            Stack<Integer> evalStack = new Stack<Integer>();

            int result = 0;
            for (String s : postfix) {
                if ( GITLAssignment.isOperator(s) ) {
                    if (evalStack.size() < 2) {
                        throw new GITLException("Invalid expression");
                    } else {
                        int rightOp = evalStack.pop();
                        int leftOp = evalStack.pop();
                        if (s.equals("+")) {
                            if ( (rightOp > MAXIMUM_INTEGER - leftOp) || (leftOp > MAXIMUM_INTEGER - rightOp) ) {
                                throw new GITLException("Integer Overflow");
                            }
                            result = leftOp + rightOp;
                        } else if (s.equals("-")) {
                            if ( (leftOp < -MAXIMUM_INTEGER + rightOp)) {
                                throw new GITLException("Integer Overflow");
                            }
                            result = leftOp - rightOp;
                        } else if (s.equals("*")) {
                            if ( (rightOp > MAXIMUM_INTEGER / leftOp) || (leftOp > MAXIMUM_INTEGER / rightOp) ) {
                                throw new GITLException("Integer Overflow");
                            }
                            result = leftOp * rightOp;
                        } else if (s.equals("/")) {
                            result = (int) (leftOp / rightOp);
                        }
                        evalStack.push(result);
                    }
                } else {
                    int value;
                    try {
                        evalStack.push(Integer.parseInt(s));
                        continue;
                    } catch(NumberFormatException nFE) {
                        // it is a variable.
                    }
                    evalStack.push(ctx.getVariableValue(s));
               }

            }
            return evalStack.pop();
        }

        /**
         * COnverts an input infix expression into its postfix form.
         * @param infix infix expression
         * @return postfix expression
         */
        private static LinkedList<String> toPostfix(LinkedList<String> infix) {
            LinkedList<String> postFix = new LinkedList<String>();
            Stack<String> convStack = new Stack<String>();

            for (String s : infix) {
                if ( isOperator (s)) {
                    if (! s.equals("(")) {
                        while ( !convStack.isEmpty() && (precedence(s.charAt(0)) <= precedence(convStack.lastElement().charAt(0)))) {
                            String op = convStack.pop();

                            if (op.equals("(")) {
                                break;
                            }
                            postFix.add(op);
                        }
                    }
                    if (!s.equals(")"))
                        convStack.push(s);
                } else {
                    postFix.add(s);
                }
            }
            while(!convStack.isEmpty()) {
                postFix.add(convStack.pop());
            }
            return postFix;
        }



    }

        /**
     * GITLFunctionDefinition expression is used to define a function.
     */
    public static class GITLFunctionDefinition  extends GITLExpression {

        /**
         * Creates a new GITLReturn object using the parser
          * @param interpreter The interpreter using this expression
         */
        public GITLFunctionDefinition(GITLInterpreter interpreter) {

        }

        /**
         * Checks to see if the input tokens match a conditional expression
         * @param tokens The code line
         * @return true if the input code line is a conditional expression
         */
        public boolean checkMatch(LinkedList<String> tokens) {
            return (tokens.size() >= 4) && 
                    tokens.getFirst().equals("int") &&
                    tokens.get(2).equals("int");
        }

        /**
         * This function is called by the parser when a jump occurs to this
         * expression
         */
        public void handleJump(GITLContext ctx) throws GITLException {

            // Somebody called a function or jumped to a label, so,
            // we're not running.

            // THe line contains "int <functionName> int <param>
            // so, get them.
            ctx.getParams().removeFirst(); // int
            String functionName = ctx.getParams().removeFirst(); // functionname
            ctx.getParams().removeFirst(); // int
            String paramName = ctx.getParams().removeFirst();

            // Add the function definition to the context.
            ctx.addFunction(functionName, paramName);

            // Check if this is the correct function. If so, make a jump call to the
            // current location, resetting this state.
            if ( ctx.getJumpFunction().equals(functionName) ) {

                // We're being called!
                // Restore the interpreter state into RUN 
                ctx.jumpFunction(functionName);

            }
        }

        /**
         * This method can only be called when the interpreter encounters a
         * function definition line when it is in RUN state, not JUMP state.
         * So, report error.
         *
         * @param ctx The context in which this expression will be evaluated.
         * @throws GITLException("Encountered function definition in RUN state.")
         */
        public void evaluate(GITLContext ctx)  throws GITLException {
            throw new GITLException("Encountered function definition in RUN state.");
        }

    }
        /**
     * GITLFunctionTermination expression is used to end a function block.
     */
    public static class GITLFunctionTerm  extends GITLExpression {
        /**
         * Creates a new GITLFunctionTerm object using the parser
          * @param interpreter The interpreter using this expression
         */
        public GITLFunctionTerm(GITLInterpreter interpreter) {

        }
        /**
         * Checks to see if the input token matches 'end'
         * @param tokens The code line
         * @return true if the input code line is a function termination
         */
        public boolean checkMatch(LinkedList<String> tokens) {
            return tokens.getFirst().equals("end") ;
        }
        /**
         * Terminates the function block.
         *
         * @param ctx The context in which this expression will be evaluated.
         * @throws GITLException("Duplicate function definition")
         */
        public void evaluate(GITLContext ctx)  throws GITLException {
            if (ctx.getActiveFunction() == null ) {
                throw new GITLException("Mismatched end statement");
            }
            
            // The return value is 0 by default

            ctx.endCurrentFunction(0);

        }


    }


    /**
     * GITLTimeTick expression is used to measure time differences
     * in milliseconds.
     */
    public static class GITLTimeTick  extends GITLExpression {
        /**
         * Creates a new GITLFunctionTerm object using the parser
          * @param interpreter The interpreter using this expression
         */
        public GITLTimeTick(GITLInterpreter interpreter) {

        }
        /**
         * Checks to see if the input token matches 'end'
         * @param tokens The code line
         * @return true if the input code line is a function termination
         */
        public boolean checkMatch(LinkedList<String> tokens) {
            return tokens.getFirst().equals("end") ;
        }
        /**
         * Terminates the function block.
         *
         * @param ctx The context in which this expression will be evaluated.
         * @throws GITLException("Duplicate function definition")
         */
        public void evaluate(GITLContext ctx)  throws GITLException {
            if (ctx.getActiveFunction() == null ) {
                throw new GITLException("Mismatched end statement");
            }

            // The return value is 0 by default

            ctx.endCurrentFunction(0);

        }


    }
}
