package ru.mephi;

import java.awt.*;
import java.util.Set;

import static java.lang.Math.abs;

public class Utils {

    public static void line(Graphics g, int x0, int y0, int x1, int y1) {
        int dx = x1 - x0;
        int dy = y1 - y0;
        int sz = Math.max(Math.abs(dx), Math.abs(dy));
        if (sz == 0) {
            g.fillRect(x0, y0, 1, 1);
            return;
        }

        for (int k = 0; k <= sz; k++) {
            int x = fix(x0 * sz + dx * k, sz);
            int y = fix(y0 * sz + dy * k, sz);

            g.fillRect(x, y, 1, 1);
        }
    }

    private static int fix(int x, int n) {
        int r = (Math.abs(x) + n / 2) / n;
        return x < 0 ? -r : r;
    }


    public static void line(Graphics g, int x0, int y0, int x1, int y1, int w) {
        for (int dx = -(w - 1) / 2; dx <= w / 2; dx++) {
            for (int dy = -(w - 1) / 2; dy <= w / 2; dy++) {
                line(g, x0 + dx, y0 + dy, x1 + dx, y1 + dy);
            }
        }
    }
}
