import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * This class represents the mathematical operation of division,
 * extends BinaryExpression class, and implements Expression
 * interface.
 */
public class Div extends BinaryExpression implements Expression {

    /**
     * Div class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param e1 the left expression
     * @param e2 the right expression
     */
    public Div(Expression e1, Expression e2) {
          super(e1, e2);
    }

    /**
     * Div class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param val1 the left number in the expression
     * @param val2 the right number in the expression
     */
    public Div(double val1, double val2) {
          super(val1, val2);
    }

    /**
     * Div class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param var1 the left variable in the expression
     * @param var2 the right variable in the expression
     */
    public Div(String var1, String var2) {
          super(var1, var2);
    }

    /**
     * Div class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param val the number in the left expression
     * @param var the variable in the right expression
     */
    public Div(double val, String var) {
          super(val, var);
    }

    /**
     * Div class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param var the variable in the left expression
     * @param val the number in the right expression
     */
    public Div(String var, double val) {
          super(var, val);
    }

    /**
     * Div class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param e the left expression
     * @param val the number in the right expression
     */
    public Div(Expression e, double val) {
          super(e, val);
    }

    /**
     * Div class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param e the left expression
     * @param var the variable in the right expression
     */
    public Div(Expression e, String var) {
          super(e, var);
    }

    /**
     * Div class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param var the variable in the left expression
     * @param e the right expression
     */
    public Div(String var, Expression e) {
          super(var, e);
    }

    /**
     * Div class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param val the number in the left expression
     * @param e the  right expression
     */
    public Div(double val, Expression e) {
          super(val, e);
    }

    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result. If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment a dictionary contains variables and their values
     * @return result the result after evaluating the division expression
     *         using a given mapping of variables and their values
     * @throws Exception if one of the variables was not assigned
     *         or a math caclulation occured
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            double left = this.getLeftExpression().evaluate(assignment);
            double right = this.getRightExpression().evaluate(assignment);
            double result = (left / right);
            return result;
        } catch (Exception e) {
            throw new Exception("Error. At least one variable assignment is missing, or math error");
        }
    }

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return result the result after evaluating the division expression
     * * @throws Exception if expression can not be evaluated
     */
    @Override
    public double evaluate() throws Exception {
        try {
            double result = (this.getLeftExpression().evaluate() / this.getRightExpression().evaluate());
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
     * @return str the String representation of the expression
     */
    @Override
    public String toString() {
        String str = "(" + this.getLeftExpression().toString() + " / " + this.getRightExpression().toString() + ")";
        return str;
    }

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var the variable to assign to
     * @param expression the expression to replace the variable with
     * @return assigned the NEW expression after assignment
     */
    @Override
    public Expression assign(String var, Expression expression) {
        Expression left = this.getLeftExpression().assign(var, expression);
        Expression right = this.getRightExpression().assign(var, expression);
        return new Div(left, right);
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

        //verify the variavles exists in the expressions
        if (!l1.contains(var) && !l2.contains(var)) {
            return new Num(0);
        }
        //differentiating steps
        Expression left = new Mult(this.getRightExpression(), this.getLeftExpression().differentiate(var)); ;
        Expression right =  new Mult(this.getLeftExpression(), this.getRightExpression().differentiate(var));
        Expression denominator = new Pow(this.getRightExpression(), new Num(2));
        return new Div(new Minus(left, right), denominator);
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

            Expression numerator = this.getLeftExpression().simplify();
            Expression denominator = this.getRightExpression().simplify();

            if (numerator.isEqual(denominator)) {
                return new Num(1);
            }

            if (denominator.isEqual(new Num(1))) {
                return numerator.simplify();
            }

            return new Div(numerator.simplify(), denominator.simplify());
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
        return new Div(this.getLeftExpression(), this.getRightExpression());
    }
}