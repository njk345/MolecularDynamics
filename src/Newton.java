/**
 * Created by njk on 6/1/17.
 */
import java.util.ArrayList;
import acm.graphics.GPoint;

public class Newton extends Algorithm {
    private static final double DT = 0.001; //in picoseconds
    public Newton(ArrayList<Atom> system) {
        super(system);
    }
    public void setUpSystem() {

    }
    public double getTime() {
        return 0; //havent gotten around to this one yet
    }
    public void run() {
        ArrayList<Atom> system = getSystem();
        double[][] f = new double[system.size()][2];
        while (true) {
            for (int i = 0; i < system.size(); i++) {
                double[] lJForce = netForces(system.get(i), system);
                f[i][0] = lJForce[0];
                f[i][1] = lJForce[1];
            }
            for (int i = 0; i < system.size(); i++) {
                system.get(i).newtonUpdate(f[i][0], f[i][1], DT);
            }
            try {
                Thread.sleep(10);
            } catch(Exception e) {}
        }
    }
}
