/**
 * Created by njk on 5/16/17.
 */
import acm.program.GraphicsProgram;
import java.awt.*;
import java.util.ArrayList;

public class Main extends GraphicsProgram {
    public static int sw, sh;
    private static ArrayList<Atom> system;
    private static Algorithm algo;
    @Override
    public void init() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        sw = (int)screen.getWidth();
        sh = (int)screen.getHeight();
        setSize(sw, sh);
        pause(50); //give container time to resize

        system = new ArrayList<>();
        algo = new Verlet(system);
        algo.setUpSystem(); //let the algorithm populate the system in its own way

        for (Atom a : system) {
            add(a); //add whole system to screen
        }
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
