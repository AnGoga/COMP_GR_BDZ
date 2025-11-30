package ru.mephi.number_3;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main_Dz3 {

    public static void main(String[] args) throws IOException {

        for (File file : new File("src/main/resources/images").listFiles()) {
            for (int levels = 8; levels >= 2; levels--) {
                for (int byerSz = 2; byerSz <= 32; byerSz *= 2) {
                    BufferedImage res = Dz4_3.getImage(ImageIO.read(file), levels, byerSz);
                    File dir = new File("src/main/resources/result/dz3/levels_" + levels + "/byerSz_" + byerSz);
                    dir.mkdirs();
                    File out = new File(dir.getAbsolutePath() + "/" + file.getName());
                    ImageIO.write(res, "png", out);
                }
            }
        }
    }
}