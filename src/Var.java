import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * This class represents a variable
 * The mathematical constants E and PI are
 * treated like variables.
 */
public class Var implements Expression {

    //class member
    private String val;

    /**
     * Var class constructor.
     *
     * @param s the variable in string representation
     */
    public Var(String s) {
        this.val = s;
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
        if (assignment.containsKey(this.val)) {
            return assignment.get(this.val);
        }
        throw new Exception("Error in Var. A variable with no value assigen was found");
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
        if (this.val == "e") {
            return Math.E;
        }
        if (this.val == "Pi") {
            return Math.PI;
        }
        throw new Exception("At least one variable was not assigned propely");
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
        List<String> vars = new ArrayList<String>();
        vars.add(this.val);
        return vars;
    }

   /**
     * Returns a nice string representation of the expression.
     *
     * @return str the String representation of the expression
     */
    @Override
    public String toString() {
        String str = this.val;
        return str;
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
        if (this.val.equals(var)) {
            return expression;
      }
        return this;
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
        if (var == this.val) {
            return new Num(1);
        }
        //in case the variables does not match
        return new Num(0);
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
        return new Var(this.val);
    }

    /**
    * A convenience method which uses toString(),
    * in order to compare between two strings.
    *
    * @return boolean true if both expressions.toString() are equal, false otherwise
    * @param other the expression to compare with
    */
    public boolean isEqual(Expression other) {
        if (this.toString().equals(other.toString())) {
            return true;
        }
        return false;
    }
}