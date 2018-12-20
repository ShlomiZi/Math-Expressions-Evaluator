import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/**
 * BinaryExpression abstract main class.
 * This class is intented to be extended.
 */
public abstract class BinaryExpression extends BaseExpression {

    //class members
    private Expression leftExp;
    private Expression rightExp;

    /**
     * Class constructor for inherited classes.
     *
     * @param left the left expression
     * @param right the right expression
     */
    public BinaryExpression(Expression left, Expression right) {
        leftExp = left;
        rightExp = right;
    }

    /**
     * Class constructor for inherited classes.
     *
     * @param var the variable in the left expression
     * @param val the number in the right expression
     */
    public BinaryExpression(String var, double val) {
        leftExp = new Var(var);
        rightExp = new Num(val);
    }

    /**
     * Class constructor for inherited classes.
     *
     * @param val1 the number in the left expression
     * @param val2 the number in the right expression
     */
    public BinaryExpression(double val1, double val2) {
        leftExp = new Num(val1);
        rightExp = new Num(val2);
    }

    /**
     * Class constructor for inherited classes.
     *
     * @param var1 the variable in the left expression
     * @param var2 the variable in the right expression
     */
    public BinaryExpression(String var1, String var2) {
        leftExp = new Var(var1);
        rightExp = new Var(var2);
    }

    /**
     * Class constructor for inherited classes.
     *
     * @param val the number in the left expression
     * @param var the variable in the right expression
     */
    public BinaryExpression(double val, String var) {
        leftExp = new Num(val);
        rightExp = new Var(var);
    }

    /**
     * Class constructor for inherited classes.
     *
     * @param e the left expression
     * @param val the number in the right expression
     */
    public BinaryExpression(Expression e, double val) {
        leftExp = e;
        rightExp = new Num(val);
    }

    /**
     * Class constructor for inherited classes.
     *
     * @param e the left expression
     * @param var the variable in the right expression
     */
    public BinaryExpression(Expression e, String var) {
        leftExp = e;
        rightExp = new Var(var);
    }

    /**
     * Class constructor for inherited classes.
     *
     * @param val the left number
     * @param e the right expression
     */
    public BinaryExpression(double val, Expression e) {
        leftExp = new Num(val);
        rightExp = e;
    }

    /**
     * Class constructor for inherited classes.
     *
     * @param var the left variable
     * @param e the right expression
     */
    public BinaryExpression(String var, Expression e) {
        leftExp = new Var(var);
        rightExp = e;
    }

    /**
     * Return the left expression.
     *
     * @return this.leftExp the right expression
     */
    public Expression getLeftExpression() {
        return this.leftExp;
    }

    /**
     * Return the right expression.
     *
     * @return this.rightExp the right expression
     */
    public Expression getRightExpression() {
        return this.rightExp;
    }

    /**
     * Return a list of the variables in the expression.
     *
     * @return newList the list of existing variables(could be empty)
     */
    public List<String> getVariables() {
         List<String> exp1List = this.leftExp.getVariables();
         List<String> exp2List = this.rightExp.getVariables();
         //creating a new set
         Set<String> hs = new HashSet<String>();
         List<String> newList = new ArrayList<String>();
         //adding the lists items to the set. in order to avoid duplications.
         hs.addAll(exp1List);
         hs.addAll(exp2List);
         //adding items from set to the new list, this time with no duplications
         newList.addAll(hs);
         return newList;
    }

    /**
    * This methods retunrs true if an expression
    * commutative(or symmetric), and false otherwish.
    *
    * @return boolean true if expression is commutative, false otherwise.
    */
    public boolean isCommutative() {
        return false;
    }
}