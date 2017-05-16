package sample;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import java.util.Random;

/**
 * Created by njk on 5/14/17.
 */
public class Atom extends Circle {
    private static double[][] data = Utils.loadElementData();
    private static double radiusFactor = 15.0;
    private static Random random = new Random();

    private Color color;
    private int atomNum;
    private double x, y;

    public Atom(int atomNum, double x, double y) {
        super(x, y, 1.0);
        setRadius(data[atomNum-1][1] * radiusFactor);
        this.atomNum = atomNum;
        this.x = x;
        this.y = y;
        random.setSeed(this.atomNum);
        setFill(Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble()));
    }
}