import java.util.Map;
import java.util.TreeMap;

/**
* This class is being used to test our code.
*/
public class ExpressionsTest {

    /**
     * This main methods runs the requested instructions.
     *
     * @param args no command line arguments for this program
     * @throws Exception if an exception was caught in the middle of the proccess
     */
    public static void main(String[] args) throws Exception {

        try {
            Expression e = new Plus(new Plus(new Mult(2, "x"), new Sin(new Mult(4, "y"))), new Pow("e", "x"));
            System.out.println(e);
            Map<String, Double> assignment = new TreeMap<String, Double>();
            assignment.put("x", new Double(2));
            assignment.put("y", new Double(0.25));
            assignment.put("e", new Double(2.71));
            System.out.println(e.evaluate(assignment));
            System.out.println(e.differentiate("x"));
            System.out.println(e.differentiate("x").evaluate(assignment));
            System.out.println(e.differentiate("x").simplify());

        } catch (Exception e) {
            ;
        }
    }
}