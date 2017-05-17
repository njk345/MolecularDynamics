import java.io.*;
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
    public static String[] loadElementSymbols() {
        String[] symbols = new String[95];
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/ElementData.txt"));
            for (int i = 0; i < 193; i++) {
                br.readLine(); //skip first 193 lines
            }
            for (int i = 0; i < 95; i++) {
                symbols[i] = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return symbols;
    }
}
