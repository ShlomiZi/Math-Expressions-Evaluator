import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * This class represents the mathematical operation of subtraction,
 * extends BinaryExpression class, and implements Expression
 * interface.
 */
public class Minus extends BinaryExpression implements Expression {

    /**
     * Minus class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param e1 the left expression
     * @param e2 the right expression
     */
    public Minus(Expression e1, Expression e2) {
          super(e1, e2);
    }

    /**
     * Minus class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param var the variable in the left expression
     * @param val the number in the right expression
     */
    public Minus(String var, double val) {
        super(var, val);
    }

    /**
     * Minus class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param val1 the left number in the expression
     * @param val2 the right number in the expression
     */
    public Minus(double val1, double val2) {
        super(val1, val2);
    }

    /**
     * Minus class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param val the number in the left expression
     * @param var the variable in the right expression
     */
    public Minus(double val, String var) {
        super(val, var);
    }

    /**
     * Minus class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param var the left variable in the expression
     * @param var2 the right variable in the expression
     */
    public Minus(String var, String var2) {
        super(var, var2);
    }

    /**
     * Minus class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param var the left variable in the expression
     * @param e the right expression
     */
    public Minus(String var, Expression e) {
        super(var, e);
    }

    /**
     * Minus class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param val the left value in the expression
     * @param e the right expression
     */
    public Minus(double val, Expression e) {
        super(val, e);
    }

    /**
     * Minus class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param val the left value in the expression
     * @param e the right expression
     */
    public Minus(Expression e, double val) {
        super(e, val);
    }

    /**
     * Minus class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param e the left expression
     * @param var the right variable in the expression
     */
    public Minus(Expression e, String var) {
        super(e, var);
    }

    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result. If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment a dictionary contains variables and their values
     * @return result the result after evaluating the substraction expression
     *         using a given mapping of variables and their values
     * @throws Exception if one of the variables was not assigned
     *         or a math caclulation occured
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            double left = this.getLeftExpression().evaluate(assignment);
            double right = this.getRightExpression().evaluate(assignment);
            double result = left - right;
            return result;
        } catch (Exception e) {
            throw new Exception("Error. At least one variable assignment is missing, or math error");
        }
    }

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return result the result after evaluating the substraction expression
     * @throws Exception if expression can not be evaluated
     */
    @Override
    public double evaluate() throws Exception {
        try {
            double result = this.getLeftExpression().evaluate() - this.getRightExpression().evaluate();
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
        Expression l = this.getLeftExpression();
        Expression r = this.getRightExpression();
        String s = "(" + l.toString() + " - " + r.toString() + ")";
        return s;
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
        return new Minus(left, right);
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
        Expression diff1 = this.getLeftExpression().differentiate(var);
        Expression diff2 = this.getRightExpression().differentiate(var);
        return new Minus(diff1, diff2);
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
            Expression left = this.getLeftExpression().simplify();
            Expression right = this.getRightExpression().simplify();

            if (left.isEqual(right)) {
                return new Num(0);
            }
            if (left.getVariables().isEmpty()) {
                if (left.evaluate() == 0) {
                    return new Neg(right.simplify());
                }
            }
            if (right.getVariables().isEmpty()) {
                if (right.evaluate() == 0) {
                    return left.simplify();
                }
            }
            if (left.isEqual(new Num(0))) {
                return new Neg(right.simplify());
            }
            if (right.isEqual(new Num(0))) {
                return left.simplify();
            }
            return new Minus(left.simplify(), right.simplify());
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
        return new Minus(this.getLeftExpression(), this.getRightExpression());
    }
}