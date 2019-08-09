/**
 * Created by njk on 5/16/17.
 */

import acm.program.GraphicsProgram;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main extends GraphicsProgram {
    public static int sw, sh;
    public static JLabel timeMeter;
    private static ArrayList<Atom> system;
    private static Algorithm algo;

    public static void main(String[] args) {
        new Main().start();
    }

    @Override
    public void init() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        sw = (int) screen.getWidth();
        sh = (int) screen.getHeight();
        setSize(sw, sh);
        pause(50); //give container time to resize

        timeMeter = new JLabel("Time Elapsed: 0 ps");

        system = new ArrayList<>();
        algo = new VelocityVerlet(system);
        algo.setUpSystem(); //let the algorithm populate the system in its own way

        for (Atom a : system) {
            add(a); //add whole system to screen
        }
        add(timeMeter, SOUTH);
        waitForClick();
    }

    @Override
    public void run() {
        algo.run();
    }
}
