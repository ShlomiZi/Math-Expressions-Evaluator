import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the mathematical operation of adding,
 * extends BinaryExpression class, and implements Expression
 * interface.
 */
public class Plus extends BinaryExpression implements Expression  {

    /**
     * Plus class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param e1 the left expression
     * @param e2 the right expression
     */
    public Plus(Expression e1, Expression e2) {
          super(e1, e2);
    }

    /**
     * Plus class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param var the left variable in the expression
     * @param val the right number in the expression
     */
    public Plus(String var, double val) {
        super(var, val);
    }

    /**
     * Plus class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param val1 the left number in the expression
     * @param val2 the right number in the expression
     */
    public Plus(double val1, double val2) {
        super(val1, val2);
    }

    /**
     * Plus class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param val the left number in the expression
     * @param var the right variable in the expression
     */
    public Plus(double val, String var) {
        super(val, var);
    }

    /**
     * Plus class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param var the left variable in the expression
     * @param var2 the right variable in the expression
     */
    public Plus(String var, String var2) {
        super(var, var2);
    }

    /**
     * Plus class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param var the left variable in the expression
     * @param e the right expression
     */
    public Plus(String var, Expression e) {
        super(var, e);
    }

    /**
     * Plus class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param val the left value in the expression
     * @param e the right expression
     */
    public Plus(double val, Expression e) {
        super(val, e);
    }

    /**
     * Plus class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param val the left value in the expression
     * @param e the right expression
     */
    public Plus(Expression e, double val) {
        super(e, val);
    }

    /**
     * Plus class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param e the left expression
     * @param var the right variable in the expression
     */
    public Plus(Expression e, String var) {
        super(e, var);
    }

    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result. If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment a dictionary contains variables and their values
     * @return result the result after evaluating the addition expression
     *         using a given mapping of variables and their values
     * @throws Exception if one of the variables was not assigned
     *         or a math caclulation occured
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            double left = this.getLeftExpression().evaluate(assignment);
            double right = this.getRightExpression().evaluate(assignment);
            double result = left + right;
            return result;
        } catch (Exception e) {
            throw new Exception("Error. At least one variable assignment is missing, or math error");
        }
    }

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return result the result after evaluating the addition expression
     * @throws Exception if expression can not be evaluated
     */
    @Override
    public double evaluate() throws Exception {
        try {
            return this.getLeftExpression().evaluate() + this.getRightExpression().evaluate();
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
        String s = "(" + this.getLeftExpression().toString() + " + " + this.getRightExpression().toString() + ")";
        return s;
    }

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var the variable to assign to
     * @param expression the expression to replace to variable
     * @return assigned the NEW expression after assignment
     */
    @Override
    public Expression assign(String var, Expression expression) {
        Expression left = this.getLeftExpression().assign(var, expression);
        Expression right = this.getRightExpression().assign(var, expression);
        return new Plus(left, right);
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

        //verify the variables exist in the expressions
        if (!l1.contains(var) && !l2.contains(var)) {
            return new Num(0);
        }
        Expression diff1 = this.getLeftExpression().differentiate(var);
        Expression diff2 = this.getRightExpression().differentiate(var);
        return new Plus(diff1, diff2);
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

            if (right.isEqual(new Num(0))) {
                return left.simplify();
            }

            if (left.isEqual(new Num(0))) {
                return right.simplify();
            }

            if (left.getVariables().isEmpty()) {
                if (left.evaluate() == 0) {
                    return right.simplify();
                }
                return new Plus(left.evaluate(), right.simplify());
            }

            if (right.getVariables().isEmpty()) {
                if (right.evaluate() == 0) {
                    return left.simplify();
                }
                return new Plus(left.simplify(), right.evaluate());
            }

            return new Plus(left.simplify(), right.simplify());
        } catch (Exception e) {
            System.out.println("Error while trying to simplify the expression");
            System.out.println(e.getMessage());
        }
        return this;
     }

     @Override
     public boolean isCommutative() {
        return true;
    }

    /**
    * This function returen the reversed expression of
    * given expression.
    *
    * @return expression the reversed Expression
    */
     @Override
     public Expression reversedExp() {
        return new Plus(this.getRightExpression(), this.getLeftExpression());
    }

    /**
    * A convenience method which uses toString(),
    * in order to compare between two strings.
    *
    * @param other the expression to compare with
    * @return boolean true if both expressions.toString() are equal, false otherwise
    */
     @Override
     public boolean isEqual(Expression other) {
        if (this.toString().equals(other.toString())) {
            return true;
        }

        if (other.isCommutative()) {
             Expression reversed = other.reversedExp();
            if (this.toString().equals(reversed.toString())) {
                return true;
            }
        }
        return false;
     }
}