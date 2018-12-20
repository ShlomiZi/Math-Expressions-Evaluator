import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * This class represents the mathematical operation of multiplication,
 * extends BinaryExpression class, and implements Expression
 * interface.
 */
public class Mult extends BinaryExpression implements Expression {

    /**
     * Mult class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param e1 the left expression
     * @param e2 the right expression
     */
    public Mult(Expression e1, Expression e2) {
          super(e1, e2);
    }

    /**
     * Mult class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param val1 the left number in the expression
     * @param val2 the right number in the expression
     */
    public Mult(double val1, double val2) {
          super(val1, val2);
    }

    /**
     * Mult class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param var1 the left variable in the expression
     * @param var2 the right variable in the expression
     */
    public Mult(String var1, String var2) {
          super(var1, var2);
    }

    /**
     * Mult class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param val the number in the left expression
     * @param var the variable in the right expression
     */
    public Mult(double val, String var) {
          super(val, var);
    }

    /**
     * Mult class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param var the variable in the left expression
     * @param val the number in the right expression
     */
    public Mult(String var, double val) {
          super(var, val);
    }

    /**
     * Mult class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param e the left expression
     * @param val the number in the right expression
     */
    public Mult(Expression e, double val) {
        super(e, val);
    }

    /**
     * Mult class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param e the left expression
     * @param var the variable in the right expression
     */
    public Mult(Expression e, String var) {
        super(e, var);
    }

    /**
     * Mult class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param val the number in the right expression
     * @param e the right exression
     */
    public Mult(double val, Expression e) {
        super(val, e);
    }

    /**
     * Mult class construcror.
     * Using constructor of BinaryExpression class.
     *
     * @param var the variable in the left expression
     * @param e the right exression
     */
    public Mult(String var, Expression e) {
        super(var, e);
    }

    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result. If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment a dictionary contains variables and their values
     * @return result the result after evaluating the multiplication expression
     *         using a given mapping of variables and their values
     * @throws Exception if one of the variables was not assigned
     *         or a math caclulation occured
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            double left = this.getLeftExpression().evaluate(assignment);
            double right = this.getRightExpression().evaluate(assignment);
            double result = left * right;
            return result;
        } catch (Exception e) {
            throw new Exception("Error. At least one variable assignment is missing, or math error");
        }
    }

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return result the result after evaluating the multiplication expression
     * @throws Exception if expression can not be evaluated
     */
    @Override
    public double evaluate() throws Exception {
        try {
            double result = this.getLeftExpression().evaluate() * this.getRightExpression().evaluate();
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
        String s = "(" + this.getLeftExpression().toString() + " * " + this.getRightExpression().toString() + ")";
        return s;
    }

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var the variable to be replaced
     * @param expression to expression to be replaced with var
     * @return assigned the NEW expression after assignment
     */
    @Override
    public Expression assign(String var, Expression expression) {
        Expression left = this.getLeftExpression().assign(var, expression);
        Expression right = this.getRightExpression().assign(var, expression);
        return new Mult(left, right);
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

        Expression e1 = new Mult(this.getLeftExpression(), this.getRightExpression().differentiate(var));
        Expression e2 =  new Mult(this.getRightExpression(), this.getLeftExpression().differentiate(var));
        return new Plus(e1, e2);
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

            if (left.isEqual(new Num(0)) || right.isEqual(new Num(0))) {
                return new Num(0);
            }

            if (left.isEqual(new Num(1))) {
                return right;
            }

            if (right.isEqual(new Num(1))) {
                return left;
            }

            return new Mult(left.simplify(), right.simplify());
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
        return new Mult(this.getRightExpression(), this.getLeftExpression());
    }

    /**
    * A convenience method which uses toString(),
    * in order to compare between two strings.
    *
    * @return boolean true if both expressions.toString() are equal, false otherwise
    * @param other the other expression to compare with
    */
    public boolean isEqual(Expression other) {
        if (this.toString().equals(other.toString())) {
            return true;
        }
        if (other.isCommutative()) {
            Expression reversed = other.reversedExp();
            if (this.toString().equals(reversed.toString())) {
                return true;
            }
            //Expression thisSimplified = this.simplify();
            //Expression otherSimplified = other.simplify();
            //if (thisSimplified.isEqual(otherSimplified)) {
                //return true;
            //}
        }
        return false;
    }
}