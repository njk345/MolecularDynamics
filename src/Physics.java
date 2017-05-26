/**
 * Created by njk on 5/22/17.
 */
import java.util.HashMap;

public class Physics {
    private static HashMap<Integer, double[]> ljParams = Utils.loadLJParams();
    public static double[] lennardJones(Atom a, Atom b) {
        double dx = a.getX() - b.getX();
        double dy = a.getY() - b.getY();
        double r = Math.sqrt(dx*dx + dy*dy);

        int key = Integer.parseInt("" + a.getAtomNum() + b.getAtomNum());
        double[] params = ljParams.get(key);

        double dv = -12*params[1]/Math.pow(r, 13) + 6*params[0]/Math.pow(r, 7);
        double drdx = dx / r;
        double drdy = dy / r;

        return new double[]{dv*drdx, dv*drdy};
    }
}