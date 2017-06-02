/**
 * Created by keirsteadn on 5/19/17.
 */
import java.util.ArrayList;
import acm.graphics.*;
import java.util.Arrays;

public class VelocityVerlet extends Algorithm {
    private static final double DT = 0.001;

    public VelocityVerlet(ArrayList<Atom> system) {
        super(system);
    }
    public void setUpSystem() {
        //getSystem().add(new Atom(1, new GPoint(Main.sw/2 - 25, Main.sh/2)));
        //getSystem().add(new Atom(1, new GPoint(Main.sw/2 + 25, Main.sh/2)));
        getSystem().add(new Atom(1, new double[]{24, 10}));
        getSystem().add(new Atom(1, new double[]{26, 10}));
        getSystem().add(new Atom(1, new double[]{25, 10 + Math.sqrt(3)}));
        //getSystem().add(new Atom(1, new double[]{25, 12}));
        //getSystem().add(new Atom(1, new GPoint(Main.sw/2, Main.sh/2)));
    }
    public void run() {
        ArrayList<Atom> system = getSystem();
        double[][] f = new double[system.size()][2];
        //do a quick initial force calc round to init accelerations
        for (int i = 0; i < system.size(); i++) {
            double[] lJForce = netForces(system.get(i), system);
            system.get(i).setLastA(lJForce);
        }
        while (true) {
            for (int i = 0; i < system.size(); i++) {
                system.get(i).velocityVerletUpdate1(DT);
                double[] newLJForce = netForces(system.get(i), system);
                system.get(i).velocityVerletUpdate2(newLJForce[0], newLJForce[1], DT);
            }
            try {
                Thread.sleep(10);
            } catch(Exception e) {}
        }
    }
}