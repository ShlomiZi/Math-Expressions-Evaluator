import java.util.Map;
import java.util.List;

/**
 * This Expression interface repersents
 * mathematical expression.
 */
public interface Expression {

    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result. If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment a dictionary contains variables and their values
     * @return finalResult the result after evaluating the expression
     *         using a given mapping of variables and their values
     * @throws Exception if one of the variables was not assigned
     *         or a math caclulation occured
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return result the result after evaluating the expression
     * @throws Exception if expression can not be evaluated
     */
    double evaluate() throws Exception;

    /**
     * Returns a list of the variables in the expression.
     *
     * @return list the variables in the expression
     */
    List<String> getVariables();

    /**
     * Returns a nice string representation of the expression.
     *
     * @return str the String representation of the expression
     */
    String toString();

     /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var the variable to assign to
     * @param expression the expression to repalce the variable
     * @return assigned the NEW expression after assignment
     */
   Expression assign(String var, Expression expression);

   /**
     * Returns the expression tree resulting from differentiating
     * the current expression relative to variable `var`.
     *
     * @param var the variable for differentiating
     * @return e the expression after differentiating
     */
   Expression differentiate(String var);

   /**
     * Returnes a simplified version of the current expression.
     * In case of an unexpected error, the original expression
     * will be returned.
     *
     * @return result the simplified expression, or the original expression
     *         in case of an error
     */
   Expression simplify();

   /**
    * This methods retunrs true if an expression
    * commutative(or symmetric), and false otherwish.
    *
    * @return boolean true if expression is commutative, false otherwise.
    */
   boolean isCommutative();

   /**
    * A convenience method which uses toString(),
    * in order to compare between two strings.
    *
    * @param other the expression to compare with
    * @return boolean true if both expressions.toString() are equal, false otherwish
    */
   boolean isEqual(Expression other);

   /**
    * This function returen the reversed expression of
    * given expression.
    *
    * @return expression the reversed Expression
    */
   Expression reversedExp();
}