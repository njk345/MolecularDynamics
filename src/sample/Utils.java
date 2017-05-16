package sample;
import java.io.*;
import javafx.scene.paint.Color;
/**
 * Created by njk on 5/15/17.
 */
public class Utils {
    public static double[][] loadElementData() {
        double[][] data = new double[95][2];
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/ElementData.txt"));
            br.readLine(); //throw away title line
            for (int i = 0; i < 95; i++) {
                data[i][0] = Double.parseDouble(br.readLine().trim());
            }
            br.readLine();
            for (int i = 0; i < 95; i++) {
                data[i][1] = Double.parseDouble(br.readLine().trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
