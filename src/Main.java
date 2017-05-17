/**
 * Created by njk on 5/16/17.
 */
import acm.program.GraphicsProgram;
import acm.graphics.*;
import java.awt.*;

public class Main extends GraphicsProgram {
    private static int sw, sh;
    @Override
    public void init() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        sw = (int)screen.getWidth();
        sh = (int)screen.getHeight();
        setSize(sw, sh);
        pause(50); //give container time to resize

        for (int i = 1; i < 20; i++) {
            add(new Atom(i, new GPoint(50*i, 50*i)));
        }
        add(new Atom(95, new GPoint(200, 50)));
    }
    @Override
    public void run() {

    }
    public static void main(String[] args) {
        new Main().start();
    }
}
