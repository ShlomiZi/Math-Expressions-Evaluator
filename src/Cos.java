import java.util.Map;
import java.util.List;
import java.util.ArrayList;


/**
 * This class represents the mathematical Cosinus function,
 * extends UnaryExpression class, and implements Expression
 * interface.
 */
public class Cos extends UnaryExpression implements Expression {

    /**
     * Cosinus Class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param e an Expression
     */
    public Cos(Expression e) {
          super(e);
    }

    /**
     * Cosinus class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param var a String represents a variable
     */
    public Cos(String var) {
        super(var);
    }

    /**
     * Cosinus class construcror.
     * Using constructor of BinaryExpression class.
     * All calculations are being done on radians
     * (angles are converted to radians).
     *
     * @param val a double represents a number
     */
    public Cos(double val) {
        super(val);
    }

    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result. If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment a dictionary contains variables and their values
     * @return finalResult the result after evaluating the Cosinus expression
     *         using a given mapping of variables and their values
     * @throws Exception if one of the variables was not assigned
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            double result = this.getExpression().evaluate(assignment);
            //calculating result, converting degrees into radians
            double finalResult = Math.cos(Math.toRadians(result));
            return finalResult;
        } catch (Exception e) {
            throw new Exception("Error. At least one variable assignment is missing, or math error");
        }
    }

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return result the result after evaluating the Cosinus expression
     * @throws Exception if expression can not be evaluated
     */
    @Override
    public double evaluate() throws Exception {
        try {
            //calculating result, converting degrees into radians
            double result =  Math.cos(Math.toRadians(this.getExpression().evaluate()));
            return result;
        } catch (Exception e) {
            throw new Exception("Cannot evaluate. Missing assignements, or math calculation error");
        }
    }

    /**
     * Returns a list of the variables in the expression.
     * This function uses the getVariables() function of class
     * UnaryExpression.
     *
     * @return list the variables in the expression
     */
    @Override
    public List<String> getVariables() {
        List<String> list = new ArrayList<String>();
        list = super.getVariables();
        return list;
    }

    /**
     * Returns a nice string representation of the expression.
     *
     * @return str the String representation of the expression
     */
    @Override
    public String toString() {
        Expression e = getExpression();
        String str = "Cos(" + e.toString() + ")";
        return str;
    }

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var the variable to assign to
     * @param expression the expression to repalce the variable
     * @return assigned the NEW expression after assignment
     */
    @Override
    public Expression assign(String var, Expression expression) {
        Expression assigned = new Cos(this.getExpression().assign(var, expression));
        return assigned;
    }

    /**
     * Returns the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     *
     * @param var the variable for differentiating
     * @return e the expression after differentiating
     */
    @Override
    public Expression differentiate(String var) {
        List<String> list = this.getExpression().getVariables();
        if (!list.contains(var)) {
            return new Num(0);
        }
        // the new differentiated expression
        Expression e = new Neg(new Mult(new Sin(this.getExpression()), this.getExpression().differentiate(var)));
        return e;
    }

    /**
     * Returnes a simplified version of the current expression.
     * In case of an unexpected error, the original expression
     * will be returned.
     *
     * @return result the simplified expression, or the original expression
     *         in case of an error
     */
    @Override
    public Expression simplify() {
        Expression result = this.getExpression();
        try {
            //at first, check if there are variables in the expression
            List<String> vars = result.getVariables();
            if (vars.isEmpty()) {
                //in case no variables were found, return the evaluated expression
                return new Num(this.evaluate());
            }
            //result is not the simplified expression
            result = new Cos(this.getExpression().simplify());
        } catch (Exception e) {
            System.out.println("Error while trying to simplify the expression");
            System.out.println(e.getMessage());
        }
        //in case simplification was not successful
        return result;
    }

    /**
    * This function returen the reversed expression of
    * given expression.
    *
    * @return expression the reversed Expression
    */
    @Override
    public Expression reversedExp() {
        return new Cos(this.getExpression());
    }
}