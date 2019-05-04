package szkieletyzacja;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static szkieletyzacja.KMM.copyImage;

public class ACM {
    public BufferedImage crossingNumber(BufferedImage image) {
        BufferedImage copyImage = copyImage(image);
        short tabMinucje[][] = new short[image.getWidth()][image.getHeight()];
        for (int w = 1; w < image.getWidth() - 1; w++) {
            for (int h = 1; h < image.getHeight() - 1; h++) {
                Color color = new Color(image.getRGB(w, h));
                if (color.getRed() == 0) {
                    int cn = countCN(image, w, h);
                    if (cn == 1) { //zakończenie
                        tabMinucje[w][h] = 1;
                    } else if (cn == 3) { //rozgałęzienie
                        tabMinucje[w][h] = 3;
                    } else if (cn == 4) { //skrzyżowanie
                        tabMinucje[w][h] = 4;
                    }

                }
            }
        }
        short[][] minutiaeFiltration = Filtration.minutiaeFiltration(tabMinucje);

        for (int w = 1; w < minutiaeFiltration.length; w++) {
            for (int h = 1; h < minutiaeFiltration[0].length; h++) {
                if (minutiaeFiltration[w][h] == 1) { //zakończenie
                    Color circleColor = new Color(0, 255, 0);
                    copyImage = drawCircle(copyImage, w, h, circleColor);
                } else if (minutiaeFiltration[w][h] == 3) { //rozgałęzienie
                    Color circleColor = new Color(255, 0, 0);
                    copyImage = drawCircle(copyImage, w, h, circleColor);
                } else if (minutiaeFiltration[w][h] == 4) { //skrzyżowanie
                    Color circleColor = new Color(0, 0, 255);
                    copyImage = drawCircle(copyImage, w, h, circleColor);
                }
            }
        }

        return copyImage;
    }

    private BufferedImage drawCircle(BufferedImage image, int w, int h, Color circleColor) {
        Graphics graphics = image.getGraphics();
        graphics.setColor(circleColor);
        graphics.drawOval(w - 3, h - 3, 6, 6);
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
        return image;
    }

    private int countCN(BufferedImage image, int w, int h) {
        int tempTable[] = new int[9];

        if (new Color(image.getRGB(w + 1, h)).getRed() == 0) {
            tempTable[0] = 1;
            tempTable[8] = 1;
        } else {
            tempTable[0] = 0;
            tempTable[8] = 0;
        }
        if (new Color(image.getRGB(w + 1, h + 1)).getRed() == 0) {
            tempTable[1] = 1;
        } else
            tempTable[1] = 0;
        if (new Color(image.getRGB(w, h + 1)).getRed() == 0) {
            tempTable[2] = 1;
        } else
            tempTable[2] = 0;
        if (new Color(image.getRGB(w - 1, h + 1)).getRed() == 0) {
            tempTable[3] = 1;
        } else
            tempTable[3] = 0;
        if (new Color(image.getRGB(w - 1, h)).getRed() == 0) {
            tempTable[4] = 1;
        } else
            tempTable[4] = 0;
        if (new Color(image.getRGB(w - 1, h - 1)).getRed() == 0) {
            tempTable[5] = 1;
        } else
            tempTable[5] = 0;
        if (new Color(image.getRGB(w, h - 1)).getRed() == 0) {
            tempTable[6] = 1;
        } else
            tempTable[6] = 0;
        if (new Color(image.getRGB(w + 1, h - 1)).getRed() == 0) {
            tempTable[7] = 1;
        } else
            tempTable[7] = 0;

        int tempSum = 0;

        for (int i = 0; i < tempTable.length - 1; i++) {
            tempSum += Math.abs(tempTable[i] - tempTable[i + 1]);
        }


        return tempSum / 2;
    }
}
