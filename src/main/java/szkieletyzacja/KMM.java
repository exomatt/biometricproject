package szkieletyzacja;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KMM {

    private List<Integer> deleteList = new ArrayList<Integer>(Arrays.asList(
            3, 5, 7, 12, 13, 14, 15, 20,
            21, 22, 23, 28, 29, 30, 31, 48,
            52, 53, 54, 55, 56, 60, 61, 62,
            63, 65, 67, 69, 71, 77, 79, 80,
            81, 83, 84, 85, 86, 87, 88, 89,
            91, 92, 93, 94, 95, 97, 99, 101,
            103, 109, 111, 112, 113, 115, 116, 117,
            118, 119, 120, 121, 123, 124, 125, 126,
            127, 131, 133, 135, 141, 143, 149, 151,
            157, 159, 181, 183, 189, 191, 192, 193,
            195, 197, 199, 205, 207, 208, 209, 211,
            212, 213, 214, 215, 216, 217, 219, 220,
            221, 222, 223, 224, 225, 227, 229, 231,
            237, 239, 240, 241, 243, 244, 245, 246,
            247, 248, 249, 251, 252, 253, 254, 255));

    public BufferedImage kmmAlgorithm(BufferedImage image) {
        int table[][] = new int[image.getWidth()][image.getHeight()];
        // łapiemy początkowe czarne
        for (int w = 0; w < table.length; w++) {
            for (int h = 0; h < table[0].length; h++) {
                Color color = new Color(image.getRGB(w, h));
                if (color.getRed() == 0)
                    table[w][h] = 1;
                else table[w][h] = 0;
            }
        }
        // łapiemy dwójki
        for (int w = 1; w < table.length - 1
                ; w++) {
            for (int h = 1; h < table[0].length - 1; h++) {
                if (table[w][h] == 1) {
                    if (table[w - 1][h - 1] == 0 ||
                            table[w - 1][h] == 0 || table[w - 1][h + 1] == 0 ||
                            table[w][h - 1] == 0 || table[w][h + 1] == 0 || table[w + 1][h - 1] == 0 || table[w + 1][h] == 0 || table[w + 1][h + 1] == 0)
                        table[w][h] = 2;
                }
            }
        }

        // łapiemy w rogu czyli 3
        for (int w = 1; w < table.length - 1; w++) {
            for (int h = 1; h < table[0].length - 1; h++) {
                if (table[w][h] > 0) {
                    if ((table[w - 1][h] > 0 && table[w + 1][h] > 0 && table[w][h - 1] > 0 && table[w][h + 1] > 0)
                            && (table[w - 1][h - 1] == 0 || table[w - 1][h + 1] == 0 || table[w + 1][h - 1] == 0 || table[w + 1][h + 1] == 0))
                        table[w][h] = 3;
                }
            }
        }

        // łapiemy czwórki
        for (int h = 0; h < table[0].length; h++) {
            for (int w = 0; w < table.length; w++) {
                System.out.print(table[w][h]);
            }
            System.out.println("");
        }
        return null;
    }

    public static BufferedImage copyImage(BufferedImage source) {
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }
}
