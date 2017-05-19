/**
 * Created by njk on 5/16/17.
 */
import acm.program.GraphicsProgram;
import acm.graphics.*;
import java.awt.*;
import java.util.ArrayList;

public class Main extends GraphicsProgram {
    private static int sw, sh;
    private static ArrayList<Atom> system;
    private static Algorithm algo;
    @Override
    public void init() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        sw = (int)screen.getWidth();
        sh = (int)screen.getHeight();
        setSize(sw, sh);
        pause(50); //give container time to resize

        //fill up system with some configuration of atoms
        for (int i = 1; i < 20; i++) {
            system.add(new Atom(i, new GPoint(50*i, 50)));
        }
        for (Atom a : system) {
            add(a); //add whole system to screen
        }
        algo = new Verlet(system);
        waitForClick();
    }
    @Override
    public void run() {
        algo.run();
    }
    public static void main(String[] args) {
        new Main().start();
    }
}
