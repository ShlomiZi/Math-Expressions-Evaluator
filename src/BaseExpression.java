import java.util.Map;
import java.util.List;
import java.util.TreeMap;

/**
* This class represents the basic structure of a mathematical
* expression and its must-have methods, for our implemention.
*/
public abstract class BaseExpression {

    /**
     * This function will be used to return a list
     * contains all the variables in a specific expression.
     *
     * @return list a list contains the variables in the expression
     */
    public abstract List<String> getVariables();

    /**
     * This function will be used to evaluate an expression
     * using a given variables-values Map.
     *
     * @param assignment a Map of variables and the values
     * @return this.evaluate(assignment) the expression value
     * @throws Exception if at least one variables was not assigned
     */
    public abstract double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * This function will be used be used to evaluate
     * an expression withput a given variables-values Map.
     *
     * @return this.evaluate(assignment) the expression value
     * @throws Exception if the expression can not be evaluated
     */
    public double evaluate() throws Exception {
        Map<String, Double> assignment = new TreeMap<String, Double>();
        return this.evaluate(assignment);
    }

    /**
    * A convenience method which uses toString(),
    * in order to compare between two strings.
    *
    * @param other the expression to compare with
    * @return boolean true if both expressions.toString() are equal, false otherwish
    */
    public boolean isEqual(Expression other) {
        if (this.toString().equals(other.toString())) {
            return true;
        }
        return false;
    }
}