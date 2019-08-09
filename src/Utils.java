/**
 * Created by njk on 5/15/17.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

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

    public static HashMap<Integer, double[]> loadLJParams() {
        HashMap<Integer, double[]> params = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/ElementData.txt"));
            for (int i = 0; i < 289; i++) {
                br.readLine(); //skip first 289 lines
            }
            for (int i = 0; i < 25; i++) {
                String[] line = br.readLine().split(" ");
                int key = Integer.parseInt(line[0] + line[1]);
                double[] vals = new double[]{Double.parseDouble(line[2]), Double.parseDouble(line[3])};
                params.put(key, vals);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }
}
