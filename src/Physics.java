/**
 * Created by njk on 5/22/17.
 */

import java.util.HashMap;

public class Physics {
    private static HashMap<Integer, double[]> ljParams = Utils.loadLJParams();

    public static double[] lennardJones(Atom a, Atom b) {
        //gives the x and y components of the FORCE from the Lennard-Jones Potential
        //force units are kCal / A
        double[] r1 = a.getR(), r2 = b.getR();
        double dx = r1[0] - r2[0];
        double dy = r1[1] - r2[1];
        double r = Math.sqrt(dx * dx + dy * dy);
        //r in Angstroms

        int key = Integer.parseInt("" + a.getAtomNum() + b.getAtomNum());
        double[] params = ljParams.get(key);

        double dv = 12 * params[1] / Math.pow(r, 13) - 6 * params[0] / Math.pow(r, 7);
        double drdx = dx / r;
        double drdy = dy / r;

        return new double[]{dv * drdx, dv * drdy};
    }
}
