/**
 * Created by keirsteadn on 5/19/17.
 */
import java.util.ArrayList;

public abstract class Algorithm {
    private ArrayList<Atom> system;
    public Algorithm(ArrayList<Atom> system) {
        this.system = system;
    }
    public ArrayList<Atom> getSystem() {
        return system;
    }
    public abstract void setUpSystem();
    public abstract void run();
}