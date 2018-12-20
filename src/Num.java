import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * This class represents a number.
 */
public class Num implements Expression {

    //class member
    private double value;

    /**
     * Num class constructor.
     *
     * @param val the value of the number
     */
    public Num(double val) {
        this.value = val;
    }

    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result.  If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment a dictionary contains variables and their values
     * @return result the result after evaluating the number itsled,
     *         using a given mapping of variables and their values
     * @throws Exception if one of the variables was not assigned
     *         or a math caclulation occured
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            return this.value;
        } catch (Exception e) {
            throw new Exception("Error at evaluating Num with variables");
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
            return this.value;
        } catch (Exception e) {
            throw new Exception("Error at evaluating Num");
        }
    }

    /**
     * Returns a list of the variables in the expression.
     * Since we are in the Num class, no variable actualy
     * exists, therfore an empty list will be returned.
     *
     * @return list the variables in the expression
     */
    @Override
    public List<String> getVariables() {
        List<String> list = new ArrayList<String>();
        return list;
    }

    /**
     * Returns a nice string representation of the expression.
     * If values of Pi or E were found, their mathematical sign
     * will be returned.
     *
     * @return s the String representation of the expression
     */
    @Override
    public String toString() {
        String s =  Double.toString(this.value);
        if (this.value == Math.E) {
            s = "e";
        }
        if (this.value == Math.PI) {
            s = "Pi";
        }
        return s;
    }

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     * Since we are dealing with numbers only in this class,
     * no actual assignment wiil occur, and the returned Expression
     * will be the number itslef.
     *
     * @param var the variable to be replaced
     * @param expression the expression to replace the variable
     * @return this the current number
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }

    /**
     * Returns the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     *
     * @param var the variable for differentiating
     * @return result the differentiating result
     */
    @Override
    public Expression differentiate(String var) {
        Expression result = new Num(0);
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
        return this;
    }

    /**
    * This methods retunrs true if an expression
    * commutative(or symmetric), and false otherwish.
    *
    * @return boolean true
    */
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
        return new Num(this.value);
    }

    /**
    * A convenience method which uses toString(),
    * in order to compare between two strings.
    *
    * @param other the expression to compare with
    * @return boolean true if both expressions.toString() are equal, false otherwise
    */
    public boolean isEqual(Expression other) {
        if (this.toString().equals(other.toString())) {
            return true;
        }
        return false;
    }
}