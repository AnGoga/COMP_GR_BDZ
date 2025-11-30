package ru.mephi.number_2;

import ru.mephi.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main_Dz2 {
    private static Point calc(Point p0, Point p1, Point kas0, Point kas1, double t) {
        Double[] px = new Double[] {
                2*t*t*t - 3*t*t + 1,
                t*t*t - 2*t*t + t,
                -2*t*t*t + 3*t*t,
                t*t*t - t*t
        };

        return new Point(
                (int) Math.round(px[0] * p0.x + px[1] * kas0.x + px[2] * p1.x + px[3] * kas1.x),
                (int) Math.round(px[0] * p0.y + px[1] * kas0.y + px[2] * p1.y + px[3] * kas1.y)
        );
    }

    public static BufferedImage solve(List<Point> ps, List<Point> kas) {
        BufferedImage res = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_RGB);
        Graphics gr = res.createGraphics();
        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, 1024, 1024);

        gr.setColor(Color.BLUE);
        for (int i = 0; i < ps.size() - 1; i++) {

            Point prev = calc(ps.get(i), ps.get(i + 1), kas.get(i), kas.get(i + 1), 0);
            for (int j = 1; j <= 10_000; j++) {
                double t = (double) j / 10_000;
                Point cur = calc(ps.get(i), ps.get(i + 1), kas.get(i), kas.get(i + 1), t);
                Utils.line(gr, prev.x, prev.y, cur.x, cur.y, 2);
                prev = cur;
            }
        }

        gr.setColor(Color.green);
        for (int i = 0; i < ps.size(); i++) {
            Point p = ps.get(i);
            Point k = kas.get(i);
            Utils.line(gr, p.x, p.y, (int) (p.x + k.x * 0.15), (int) (p.y + k.y * 0.15), 2);
        }

        gr.setColor(Color.RED);
        for (Point p : ps) {
            gr.fillOval(p.x - 2, p.y - 2, 5, 5);
        }

        gr.dispose();
        return res;
    }

    public static void main(String[] args) throws IOException {
        List<List<Integer>> ps = List.of(
                List.of(100, 500, 300, 100, 600, 400),
                List.of(100, 500, 300, 100, 600, 400),
                List.of(100, 800, 500, 200, 900, 800),
                List.of(200, 200, 800, 200, 800, 800, 200, 800),
                List.of(100, 512, 400, 200, 700, 500, 900, 100),
                List.of(150, 900, 300, 300, 600, 700, 850, 150, 950, 600),
                List.of(100, 500, 250, 150, 500, 400, 750, 100, 900, 500),
                List.of(200, 800, 400, 200, 600, 600, 800, 300),
                List.of(100, 100, 200, 900, 500, 100, 800, 900, 900, 100),
                List.of(512, 100, 200, 400, 300, 800, 700, 800, 800, 400, 512, 100),
                List.of(100, 512, 150, 300, 200, 150, 280, 100, 370, 150,
                        450, 250, 512, 400, 580, 512, 650, 600, 720, 512,
                        780, 400, 830, 300, 870, 200, 900, 150, 920, 200,
                        900, 350, 850, 500, 780, 650, 700, 780, 600, 850,
                        500, 900, 400, 880, 300, 800, 200, 700, 120, 600
                )
        );

        List<List<Integer>> kas = List.of(
                List.of(400, -300, 300, 200, 200, -400),
                List.of(-400, 300, -300, -200, -200, 400),
                List.of(600, -400, 400, 500, 300, -300),
                List.of(500, 0, 0, 500, -500, 0, 0, -500),
                List.of(400, -200, 300, 400, 300, -300, 200, 400),
                List.of(300, -500, 400, 300, 300, -400, 400, 300, 200, -300),
                List.of(300, -200, 200, 100, 250, 300, 200, -100, 150, 200),
                List.of(400, -400, 300, 500, 300, -400, 200, 300),
                List.of(300, 500, 400, -600, 400, 600, 400, -600, 300, 500),
                List.of(0, 500, -400, 300, 400, 200, 400, -200, -400, -300, 0, -500),
                List.of(200, -300, 200, -200, 250, -150, 300, 100, 300, 200,
                        250, 300, 200, 250, 200, 200, 150, 150, 100, -100,
                        50, -200, 100, -250, 150, -200, 200, 100, 150, 250,
                        -100, 300, -200, 250, -250, 150, -200, 100, -150, 50,
                        -100, 100, -150, 150, -200, 200, -250, 150, -200, -100
                )
        );

        File dir = new File("src/main/resources/result/dz2");
        dir.mkdirs();

        for (int i = 0; i < ps.size(); i++) {
            File out = new File(dir.getAbsolutePath() + "/" + i + ".png");
            ImageIO.write(solve(fix(ps.get(i)), fix(kas.get(i))), "png", out);
        }
    }

    private static List<Point> fix(List<Integer> ps) {
        List<Point> pts = new ArrayList<>();
        for (int i = 0; i < ps.size(); i += 2) {
            pts.add(new Point(ps.get(i), ps.get(i + 1)));
        }
        return pts;
    }
}
