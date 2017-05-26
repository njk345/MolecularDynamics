/**
 * Created by keirsteadn on 5/19/17.
 */
import java.util.ArrayList;
import acm.graphics.*;
import java.util.Arrays;

public class Verlet extends Algorithm {
    private static long dt = 10;
    public Verlet(ArrayList<Atom> system) {
        super(system);
    }
    public void setUpSystem() {
        getSystem().add(new Atom(1, new GPoint(Main.sw/2 - 100, Main.sh/2)));
        getSystem().add(new Atom(1, new GPoint(Main.sw/2 + 100, Main.sh/2)));
        getSystem().add(new Atom(1, new GPoint(Main.sw/2, Main.sh/2 - 100)));
        getSystem().add(new Atom(1, new GPoint(Main.sw/2, Main.sh/2 + 100)));

    }
    public void run() {
        ArrayList<Atom> system = getSystem();
        while (true) {
            for (Atom a : system) {
                double[] f = netForces(a, system);
                a.update(f[0], f[1], dt);
                a.move(dt);
            }
            try {
                Thread.sleep(dt);
            } catch(Exception e) {}
        }
    }
    private static double[] netForces(Atom a, ArrayList<Atom> system) {
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