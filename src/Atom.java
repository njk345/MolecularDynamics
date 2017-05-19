import java.util.Random;
import java.awt.Color;
import acm.graphics.*;

/**
 * Created by njk on 5/14/17.
 */
public class Atom extends GCompound {
    private static double[][] data = Utils.loadElementData();
    private static String[] symbols = Utils.loadElementSymbols();
    private static double radiusFactor = 15.0;
    private static Random random = new Random();

    private int atomNum;
    private double mass, radius;
    private GLabel symbol;
    private GOval body;
    private GPoint loc;

    public Atom(int atomNum, GPoint loc) {
        super();
        this.atomNum = atomNum;
        this.mass = data[atomNum-1][0];
        this.radius = data[atomNum-1][1];
        this.loc = loc;
        this.body = new GOval(radius * radiusFactor * 2, radius * radiusFactor * 2);
        this.symbol = new GLabel(symbols[atomNum-1]);

        random.setSeed(this.atomNum);
        body.setFilled(true);
        body.setFillColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));
        add(body);
        add(symbol);
        setLocation(loc);
        setVisible(true);
    }
}