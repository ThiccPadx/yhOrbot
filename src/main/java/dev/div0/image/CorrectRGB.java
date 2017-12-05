package dev.div0.image;

import java.awt.image.BufferedImage;

public class CorrectRGB {
	public static void fixBadJPEG(BufferedImage img)
    {
        int[] ary = new int[img.getWidth() * img.getHeight()];
        img.getRGB(0, 0, img.getWidth(), img.getHeight(), ary, 0, img.getWidth());
        for (int i = ary.length - 1; i >= 0; i--)
        {
            int y = ary[i] >> 16 & 0xFF; // Y
            int b = (ary[i] >> 8 & 0xFF) - 128; // Pb
            int r = (ary[i] & 0xFF) - 128; // Pr

            int g = (y << 8) + -88 * b + -183 * r >> 8; //
            b = (y << 8) + 454 * b >> 8;
            r = (y << 8) + 359 * r >> 8;

            if (r > 255)
                r = 255;
            else if (r < 0) r = 0;
            if (g > 255)
                g = 255;
            else if (g < 0) g = 0;
            if (b > 255)
                b = 255;
            else if (b < 0) b = 0;

            ary[i] = 0xFF000000 | (r << 8 | g) << 8 | b;
        }
        img.setRGB(0, 0, img.getWidth(), img.getHeight(), ary, 0, img.getWidth());
    }
}
