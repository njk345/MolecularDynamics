/**
 * Created by keirsteadn on 5/19/17.
 */
import java.util.ArrayList;
import acm.graphics.*;
import java.util.Arrays;

public class VelocityVerlet extends Algorithm {
    private static final double DT = 0.001;
    private static double time = 0;

    public VelocityVerlet(ArrayList<Atom> system) {
        super(system);
    }
    public void setUpSystem() {
        int w = 10, h = 10, d = 2;
//        for (int i = 0; i < h; i++) {
//            for (int j = 0; j < w; j++) {
//                getSystem().add(new Atom(1, new double[]{20 - (w-1)/2*d + j*d, 10 - (h-1)/2*d + i*d}));
//            }
//        }
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                getSystem().add(new Atom(1, new double[]{20 - d*((w-1)/2 - j), 10 - d*Math.sqrt(3)*((h-1)/4 - i)}));
            }
        }
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                getSystem().add(new Atom(1, new double[]{20 - d*((w-1)/2 - j + 0.5), 10 - d*Math.sqrt(3)*((h-1)/4 - i + 0.5)}));
            }
        }
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
            time += DT;
            Main.timeMeter.setText("Time Elapsed: " + String.format("%10.3f",time) + " ps");
            try {
                Thread.sleep(10);
            } catch(Exception e) {}
        }
    }
    public double getTime() {
        return time;
    }
}