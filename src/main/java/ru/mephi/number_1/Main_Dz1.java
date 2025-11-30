package ru.mephi.number_1;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main_Dz1 {

    public static void main(String[] args) throws IOException {

        List<List<Integer>> exs = List.of(
                List.of(100, 100, 400, 80, 700, 200, 650, 500, 200, 450),
                List.of(150, 150, 500, 150, 700, 400, 500, 700, 150, 700, 300, 400),
                List.of(50, 500, 950, 500, 900, 520, 100, 520),
                List.of(100, 100, 900, 150, 700, 400, 500, 900, 450, 600, 400, 300),
                List.of(100, 100, 900, 100, 800, 300, 900, 500, 100, 500, 200, 300),
                List.of(10, 10, 1010, 10, 1010, 800, 600, 1010, 10, 800),
                List.of(100, 100, 900, 130, 880, 160, 120, 140),
                List.of(962, 512,
                        778, 599,
                        852, 759,
                        665, 722,
                        645, 921,
                        512, 782,
                        385, 902,
                        365, 714,
                        156, 771,
                        265, 592,
                        112, 512,
                        284, 438,
                        172, 265,
                        359, 302,
                        379, 103,
                        512, 242,
                        639, 122,
                        677, 285,
                        868, 253,
                        788, 422
                )
        );

        File dir = new File("src/main/resources/result/dz1");
        dir.mkdirs();
        for (int i = 0; i < exs.size(); i++) {
            List<Point> pts = new ArrayList<>();
            for (int j = 0; j < exs.get(i).size(); j += 2) {
                pts.add(new Point(exs.get(i).get(j), exs.get(i).get(j + 1)));
            }

            BufferedImage res = Solver_1.write(pts, Solver_1.solve(pts));
            File out = new File(dir.getAbsolutePath() + "/" + i + ".png");
            ImageIO.write(res, "png", out);
        }
    }
}