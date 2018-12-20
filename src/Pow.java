import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * This class represents the mathematical operation of power,
 * extends BinaryExpression class, and implements Expression
 * interface.
 */
public class Pow extends BinaryExpression implements Expression {

    /**
     * Pow class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param e1 the left expression
     * @param e2 the right expression
     */
    public Pow(Expression e1, Expression e2) {
          super(e1, e2);
    }

    /**
     * Pow class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param val1 the base value
     * @param val2 the exponent value
     */
    public Pow(double val1, double val2) {
        super(val1, val2);
    }

    /**
     * Pow class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param val the base value
     * @param var the exponent variable
     */
    public Pow(double val, String var) {
        super(val, var);
    }

    /**
     * Pow class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param var the base variable
     * @param val the exponent variable
     */
    public Pow(String var, double val) {
        super(var, val);
    }

    /**
     * Pow class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param var base variable
     * @param var2 the exponenet variable
     */
    public Pow(String var, String var2) {
        super(var, var2);
    }

    /**
     * Pow class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param var base variable
     * @param e the expression in the exponent
     */
    public Pow(String var, Expression e) {
        super(var, e);
    }

    /**
     * Pow class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param val base numeric value
     * @param e the exponenet expression
     */
    public Pow(double val, Expression e) {
        super(val, e);
    }

    /**
     * Pow class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param e the expression in the base
     * @param var the exponenet variable
     */
    public Pow(Expression e, String var) {
        super(e, var);
    }

    /**
     * Pow class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param e the expression in the base
     * @param val the exponent numeric value
     */
    public Pow(Expression e, double val) {
        super(e, val);
    }

    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result. If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment a dictionary contains variables and their values
     * @return result the result after evaluating the power expression
     *         using a given mapping of variables and their values
     * @throws Exception if one of the variables was not assigned
     *         or a math caclulation occured
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            double base = this.getLeftExpression().evaluate(assignment);
            double exponent = this.getRightExpression().evaluate(assignment);
            double result = Math.pow(base, exponent);
            return result;
        } catch (Exception e) {
            throw new Exception("Error. At least one variable assignment is missing, or math error");
        }
    }

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return result the result after evaluating the power expression
     * @throws Exception if expression can not be evaluated
     */
    @Override
    public double evaluate() throws Exception {
        try {
            double base = this.getLeftExpression().evaluate();
            double exponent = this.getRightExpression().evaluate();
            double result = Math.pow(base, exponent);
            return result;
        } catch (Exception e) {
            throw new Exception("Cannot evaluate. Missing assignements, or math calculation error");
        }
    }

     /**
     * Returns a list of the variables in the expression.
     * This function uses the getVariables() function of class
     * BinaryExpression.
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
     * @return s the String representation of the expression
     */
    @Override
    public String toString() {
        String s =  "(" + this.getLeftExpression().toString() + "^" + this.getRightExpression().toString() + ")";
        return s;
    }

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var the variable to be replaced
     * @param expression the expression to replace the variable
     * @return assigned the NEW expression after assignment
     */
    @Override
    public Expression assign(String var, Expression expression) {
        Expression base = this.getLeftExpression().assign(var, expression);
        Expression exponent = this.getRightExpression().assign(var, expression);
        return new Pow(base, exponent);
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
        List<String> l1 = this.getLeftExpression().getVariables();
        List<String> l2 = this.getRightExpression().getVariables();

        //verify the variables exists in the expressions
        if (!l1.contains(var) && !l2.contains(var)) {
            return new Num(0);
        }

        Expression power = this;
        Expression fDerivative = this.getLeftExpression().differentiate(var);
        Expression gDerivative = this.getRightExpression().differentiate(var);

        Expression leftExp = new Mult(fDerivative, new Div(this.getRightExpression(), this.getLeftExpression()));
        Expression rightExp = new Mult(gDerivative, new Log(new Num(Math.E), getLeftExpression()));

        Expression result = new Mult(power, new Plus(leftExp, rightExp));
        return result;
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
        try {
            //checking stepes
            //If there are no variables in the expression, eavluate it
            if (this.getVariables().isEmpty()) {
                return new Num(this.evaluate());
            }

            Expression base = this.getLeftExpression().simplify();
            Expression exponent = this.getRightExpression().simplify();

            if (base.getVariables().isEmpty()) {
                return new Pow(base.simplify(), exponent);
            }
            if (exponent.getVariables().isEmpty()) {
                return new Pow(base, exponent.simplify());
            }

            base = base.simplify();
            exponent = exponent.simplify();

        } catch (Exception e) {
            System.out.println("Error while trying to simplify the expression");
            System.out.println(e.getMessage());
        }
        return this;
     }

     /**
    * This function returen the reversed expression of
    * given expression.
    *
    * @return expression the reversed Expression
    */
     @Override
    public Expression reversedExp() {
        return new Pow(this.getLeftExpression(), this.getRightExpression());
    }
}