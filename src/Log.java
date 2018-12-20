import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * This class represents the mathematical operation of logarithm,
 * extends BinaryExpression class, and implements Expression
 * interface.
 */
public class Log extends BinaryExpression implements Expression {

    /**
     * Log class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param e1 the logarithm base
     * @param e2 the logarithm reuslt
     */
    public Log(Expression e1, Expression e2) {
          super(e1, e2);
    }

    /**
     * Log class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param val1 the logarithm base
     * @param val2 the logarithm reuslt
     */
    public Log(double val1, double val2) {
          super(val1, val2);
    }

    /**
     * Log class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param var1 the logarithm base
     * @param var2 the logarithm reuslt
     */
    public Log(String var1, String var2) {
          super(var1, var2);
    }

    /**
     * Log class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param val the logarithm base
     * @param var the logarithm reuslt
     */
    public Log(double val, String var) {
          super(val, var);
    }

    /**
     * Log class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param var the logarithm base
     * @param val the logarithm reuslt
     */
    public Log(String var, double val) {
          super(var, val);
    }

    /**
     * Log class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param e the logarithm base expression
     * @param val the logarithm reuslt
     */
    public Log(Expression e, double val) {
          super(e, val);
    }

    /**
     * Log class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param e the logarithm base expression
     * @param var the logarithm reuslt
     */
    public Log(Expression e, String var) {
          super(e, var);
    }

    /**
     * Log class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param var the logarithm base variable
     * @param e the logarithm reuslt expression
     */
    public Log(String var, Expression e) {
          super(var, e);
    }

    /**
     * Log class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param val the logarithm base value
     * @param e the logarithm reuslt expression
     */
    public Log(double val, Expression e) {
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
            double base = this.getLeftExpression().evaluate(assignment);
            double result = this.getRightExpression().evaluate(assignment);
            return logCalculation(base, result);
        } catch (Exception e) {
            throw new Exception("Error. At least one variable assignment is missing, or math error");
        }
    }

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return result the result after evaluating the division expression
     * @throws Exception if expression can not be evaluated
     */
    @Override
    public double evaluate() throws Exception {
        try {
            double base = this.getLeftExpression().evaluate();
            double result = this.getRightExpression().evaluate();
            return logCalculation(base, result);
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
        Expression l = this.getLeftExpression();
        Expression r = this.getRightExpression();

        return "log(" + l.toString() + ", " + r.toString() + ")";
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
        Expression base = this.getLeftExpression().assign(var, expression);
        Expression logResult = this.getRightExpression().assign(var, expression);
        return new Log(base, logResult);
    }

    /**
     * This is an helper method, which helps to calculate log(a, b),
     * where a is the logarithm base, and b is the required result.
     * This function uses the Math.log function, which calculates e based
     * logarithms.
     *
     * @param base the logarithm base
     * @param num the logarithm result
     * @return result the wanted exponent value
     * @throws Exception if log calculation was stopped due to math error
     */
    public double logCalculation(double base, double num) throws Exception {
        double result = Math.log(num) / Math.log(base);
        return result;
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
        Expression base = this.getLeftExpression();
        Expression logResult = this.getRightExpression();
        Expression result = new Div(logResult.differentiate(var), new Mult(logResult, new Log("e", base)));

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
            Expression logResult = this.getRightExpression().simplify();

            if (base.isEqual(logResult)) {
                return new Num(1);
            }

            if (base.getVariables().isEmpty()) {
                return new Log(base.simplify(), logResult);
            }

            if (logResult.getVariables().isEmpty()) {
                return new Log(base, logResult.simplify());
            }

            return new Log(base.simplify(), logResult.simplify());

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
        return new Log(this.getLeftExpression(), this.getRightExpression());
    }
}