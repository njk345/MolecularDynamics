/**
 * Created by njk on 5/16/17.
 */
import acm.program.GraphicsProgram;
import acm.graphics.*;
import java.awt.*;
import java.util.Iterator;

public class Main extends GraphicsProgram {
    private static int sw, sh;
    @Override
    public void init() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        sw = (int)screen.getWidth();
        sh = (int)screen.getHeight();
        setSize(sw, sh);
        pause(50); //give container time to resize

        for (int i = 1; i < 30; i++) {
            add(new Atom(i, new GPoint(50*i, 50)));
        }
        //add(new Atom(95, new GPoint(200, 50)));
    }
    @Override
    public void run() {
        while (true) {
            Iterator<GObject> iter = iterator();
            while (iter.hasNext()) {
                iter.next().move(0.01, 0.01);
            }
            pause(0.1);
        }
    }
    public static void main(String[] args) {
        new Main().start();
    }
}
