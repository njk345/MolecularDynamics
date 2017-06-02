/**
 * Created by keirsteadn on 5/19/17.
 */
import java.util.ArrayList;

public abstract class Algorithm {
    private ArrayList<Atom> system;
    public Algorithm(ArrayList<Atom> system) {
        this.system = system;
    }
    public ArrayList<Atom> getSystem() {
        return system;
    }
    public abstract void setUpSystem();
    public abstract void run();
    public double[] netForces(Atom a, ArrayList<Atom> system) {
        double fx = 0, fy = 0;
        for (Atom i : system) {
            if (i.equals(a)) continue;
            double[] f = Physics.lennardJones(a, i);
            fx += f[0];
            fy += f[1];
        }
        return new double[]{fx, fy};
    }
}