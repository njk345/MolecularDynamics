/**
 * Created by njk on 5/14/17.
 */
import java.util.Random;
import java.awt.Color;
import acm.graphics.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Atom extends GCompound {
    private static double[][] data = Utils.loadElementData();
    private static String[] symbols = Utils.loadElementSymbols();
    private static final double SCALE_FACTOR = Main.sw / 50.0; //pixels per "angstrom"
    private static final double MASS_FACTOR = 1.66E-27 / 4184 / 10E20 * 6.022E23 * 10E24;
    private static Random random = new Random();

    private int atomNum;
    private double mass, radius; //mass in kCal * ps^2 / A^2
    private GLabel symbol;
    private GOval body;
    private double[] v, r;
    private double[] lastA; //only used in VelocityVerlet

    public Atom(int atomNum, GPoint loc) {
        super();
        this.atomNum = atomNum;
        this.mass = data[atomNum-1][0] * MASS_FACTOR;
        this.radius = data[atomNum-1][1];
        this.body = new GOval(radius * SCALE_FACTOR * 2, radius *SCALE_FACTOR * 2);
        this.symbol = new GLabel(symbols[atomNum-1]);
        this.v = new double[]{0,0};
        this.r = new double[]{1/SCALE_FACTOR * loc.getX(), 1/SCALE_FACTOR * loc.getY()};
        this.lastA = new double[]{0,0};

        random.setSeed(this.atomNum);
        body.setFilled(true);
        body.setFillColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));
        add(body);
        add(symbol);
        setLocation(loc);
        setVisible(true);
    }
    public Atom(int atomNum, double[] r) {
        super();
        this.atomNum = atomNum;
        this.mass = data[atomNum-1][0] * MASS_FACTOR;
        this.radius = data[atomNum-1][1];
        this.body = new GOval(radius * SCALE_FACTOR * 2, radius *SCALE_FACTOR * 2);
        this.symbol = new GLabel(symbols[atomNum-1]);
        this.v = new double[]{0,0};
        this.r = r;
        this.lastA = new double[]{0,0};

        random.setSeed(this.atomNum);
        body.setFilled(true);
        body.setFillColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));
        add(body);
        add(symbol);
        setLocation(r[0] * SCALE_FACTOR, r[1] * SCALE_FACTOR);
        setVisible(true);
    }
    public double[] getV() {
        return v;
    }
    public double[] getR() {
        return r;
    }
    public void setLastA(double[] initForce) {
        lastA[0] = initForce[0] / mass;
        lastA[1] = initForce[1] / mass;
    }
    public int getAtomNum() {
        return atomNum;
    }
    public void newtonUpdate(double fx, double fy, double dt) { //dt in picoseconds
        System.out.println("FX = " + fx + " || FY = " + fy);

        double ax = fx / mass, ay = fy / mass;

        System.out.println("AX = " + ax + " || AY = " + ay);

        double drx = v[0] * dt + 0.5*ax*dt*dt, dry = v[1] * dt + 0.5*ay*dt*dt;
        double pxDx = drx * SCALE_FACTOR, pxDy = dry * SCALE_FACTOR;
        r[0] += drx;
        r[1] += dry;
        move(pxDx, pxDy);

        v[0] += ax * dt;
        v[1] += ay * dt;
    }
    public void velocityVerletUpdate1(double dt) {
        double drx = v[0] * dt + 0.5*lastA[0]*dt*dt, dry = v[1] * dt + 0.5*lastA[1]*dt*dt;
        double pxDx = drx * SCALE_FACTOR, pxDy = dry * SCALE_FACTOR;
        r[0] += drx;
        r[1] += dry;
        move(pxDx, pxDy);
    }
    public void velocityVerletUpdate2(double fx, double fy, double dt) {
        double newAx = fx / mass, newAy = fy / mass;
        v[0] += 0.5*(lastA[0] + newAx)*dt;
        v[1] += 0.5*(lastA[1] + newAy)*dt;
        lastA[0] = newAx;
        lastA[1] = newAy;
    }
}