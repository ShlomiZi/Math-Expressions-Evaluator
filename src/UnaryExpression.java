import java.util.List;
import java.util.ArrayList;

/**
 * UnaryExpression abstract main class.
 * This class is intented to be extended.
 */
public abstract class UnaryExpression extends BaseExpression {

    //class members
    private Expression exp;

    /**
     * UnaryExpression construcror..
     *
     * @param e an Expression
     */
    public UnaryExpression(Expression e) {
        this.exp = e;
    }

     /**
     * UnaryExpression construcror..
     *
     * @param d a numeric value
     */
    public UnaryExpression(double d) {
        this.exp = new Num(d);
    }

     /**
     * UnaryExpression construcror..
     *
     * @param var a string represents a Variable, or a constant(Math.E, for example)
     */
    public UnaryExpression(String var) {
        this.exp = new Var(var);
    }

    /**
     * This method retunrs the expression inside the Unary instance.
     *
     * @return this.exp the expression
     */
    public Expression getExpression() {
        return this.exp;
    }

    /**
     * Returns a list of the variables in the expression.
     *
     * @return list the variables in the expression
     */
    public List<String> getVariables() {
        List<String> exp1List = this.exp.getVariables();
        List<String> newList = new ArrayList<String>();
        newList.addAll(exp1List);
        return newList;
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

    /**
    * This methods retunrs true if an expression
    * commutative(or symmetric), and false otherwish.
    *
    * @return boolean true
    */
    public boolean isCommutative() {
        return true;
    }
}