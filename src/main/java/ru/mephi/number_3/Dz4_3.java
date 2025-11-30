package ru.mephi.number_3;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Dz4_3 {
    public static BufferedImage getImage(BufferedImage img, int levels, int byerSz) {
        double[][] errMatr = getErrorMatr(getByer(byerSz), levels);
        int[] table = getVlookupTable(levels);

        BufferedImage res = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                Color c = new Color(img.getRGB(x, y));
                double gray = 0.299f * c.getRed() + 0.587f * c.getGreen() + 0.114f * c.getBlue();

                double err = errMatr[x % errMatr.length][y % errMatr.length];
                int resCol = table[fix(gray + err)];

                res.setRGB(x, y, new Color(resCol, resCol, resCol).getRGB());
            }
        }
        return res;
    }

    private static int fix(double x) {
        return (int) min(255, max(0, Math.round(x)));
    }

    private static int[][] getByer(int n) {
        int[][] d1 = {{0, 2}, {3, 1}};
        for (int sz = 2; sz < n; sz *= 2) {

            int[][] d2 = new int[sz * 2][sz * 2];

            for (int y = 0; y < sz; y++) {
                for (int x = 0; x < sz; x++) {
                    int v = d1[y][x] * 4;
                    d2[y][x] = v;
                    d2[y][x + sz] = v + 2;
                    d2[y + sz][x] = v + 3;
                    d2[y + sz][x + sz] = v + 1;
                }
            }
            d1 = d2;
        }
        return d1;
    }

    private static double[][] getErrorMatr(int[][] byer, int levels) {
        double[][] res = new double[byer.length][byer.length];
        double step = 255.0 / (levels - 1);

        for (int x = 0; x < byer.length; x++) {
            for (int y = 0; y < byer.length; y++) {
                double t = (byer[x][y] + 0.5f) / (byer.length * byer.length);
                res[x][y] = (t - 0.5f) * step;
            }
        }

        return res;
    }

    private static int[] getVlookupTable(int n) {
        int[] res = new int[256];

        double step = 255.0 / ((1 << n) - 1);
        for (int v = 0; v < 256; v++) {
            int idx = (int) Math.round(v / step);
            idx = max(0, min((1 << n) - 1, idx));

            int out = (int) Math.round(idx * step);
            res[v] = max(0, min(255, out));
        }
        return res;
    }
}