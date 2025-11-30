package ru.mephi.number_1;

import ru.mephi.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public final class Solver_1 {
    public static List<Point> solve(List<Point> pts__) {
        List<Point> pts = new ArrayList<>(pts__);

        pts.sort((p1, p2) -> {
            if (p1.x == p2.x) return p1.y - p2.y;
            return p1.x - p2.x;
        });

        List<Point> low = new ArrayList<>();
        for (Point p : pts) {
            while (low.size() >= 2 && multi(low.get(low.size() - 2), low.get(low.size() - 1), p) <= 0) {
                low.remove(low.size() - 1);
            }
            low.add(p);
        }

        List<Point> high = new ArrayList<>();
        for (int i = pts.size() - 1; i >= 0; i--) {
            while (high.size() >= 2 && multi(high.get(high.size() - 2), high.get(high.size() - 1), pts.get(i)) <= 0) {
                high.remove(high.size() - 1);
            }
            high.add(pts.get(i));
        }

        low.remove(low.size() - 1);
        high.remove(high.size() - 1);
        low.addAll(high);

        return low;
    }

    private static double multi(Point a, Point b, Point c) {
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
    }

    public static BufferedImage write(List<Point> p1, List<Point> p2) {
        BufferedImage res = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_RGB);
        Graphics gr = res.createGraphics();
        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, 1024, 1024);

        gr.setColor(Color.BLACK);
        for (int i = 0; i < p1.size(); i++) {
            Point p01 = p1.get(i);
            Point p02 = p1.get((i + 1) % p1.size());
            Utils.line(gr, p01.x, p01.y, p02.x, p02.y, 5);
        }

        gr.setColor(Color.RED);
        for (int i = 0; i < p2.size(); i++) {
            Point p01 = p2.get(i);
            Point p02 = p2.get((i + 1) % p2.size());
            Utils.line(gr, p01.x, p01.y, p02.x, p02.y, 2);
        }

        gr.dispose();
        return res;
    }
}