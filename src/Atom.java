/**
 * Created by njk on 5/14/17.
 */
import java.util.Random;
import java.awt.Color;
import acm.graphics.*;

public class Atom extends GCompound {
    private static double[][] data = Utils.loadElementData();
    private static String[] symbols = Utils.loadElementSymbols();
    private static double radiusFactor = 12.0;
    private static Random random = new Random();

    private int atomNum;
    private double mass, radius;
    private GLabel symbol;
    private GOval body;
    private double[] a, v;

    public Atom(int atomNum, GPoint loc) {
        super();
        this.atomNum = atomNum;
        this.mass = data[atomNum-1][0];
        this.radius = data[atomNum-1][1];
        this.body = new GOval(radius * radiusFactor * 2, radius * radiusFactor * 2);
        this.symbol = new GLabel(symbols[atomNum-1]);
        this.a = new double[]{0,0};
        this.v = new double[]{0,0};

        random.setSeed(this.atomNum);
        body.setFilled(true);
        body.setFillColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));
        add(body);
        add(symbol);
        setLocation(loc);
        setVisible(true);
    }
    public int getAtomNum() {
        return atomNum;
    }
    public void move(long dt) {
        double dx = v[0]*dt + 0.5*a[0]*dt*dt;
        double dy = v[1]*dt + 0.5*a[1]*dt*dt;
        move(dx*(1e8), dy*(1e8));
    }
    public void update(double fx, double fy, long dt) {
        a[0] += fx / mass;
        a[1] += fy / mass;
        v[0] += a[0] * dt;
        v[1] += a[1] * dt;
    }
}