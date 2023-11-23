/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassFiles;

import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Process {

    int a, r, g, b, t, hi, wi, i, j, c;
    int length, ilen, h, w, size = 0, ext;
    int pixels[], red[], green[], blue[], alpha[], buf[], ipixels[], ia[], ir[], ig[], ib[], data[];
    Image img;
    ImageIcon ico;
    ColorModel cm;
    String key, pdkey, extension;
    PasswordDialog pd;
    BufferedImage image;
    Runtime runtime = Runtime.getRuntime();

    public Process(ImageIcon ico) {

        h = ico.getIconHeight();
        w = ico.getIconWidth();
        img = ico.getImage();

        pixels = new int[w * h];
        alpha = new int[w * h];
        red = new int[w * h];
        green = new int[w * h];
        blue = new int[w * h];

        try {
            PixelGrabber pg = new PixelGrabber(img, 0, 0, w, h, true);

            if (pg.grabPixels() == true) {
                cm = pg.getColorModel();

                size = 0;
                pixels = (int[]) pg.getPixels();
                for (j = 0; j < h; j++) {
                    for (i = 0; i < w; i++) {
                        alpha[size] = ((pixels[j * w + i] >> 24) & 0xff);
                        red[size] = ((pixels[j * w + i] >> 16) & 0xff);
                        green[size] = ((pixels[j * w + i] >> 8) & 0xff);
                        blue[size] = ((pixels[j * w + i]) & 0xff);
                        size++;

                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Process Error::" + e.getMessage());
        }
        System.out.println("Total Memory : " + runtime.totalMemory() / 1024 + "KB");

    }

    public boolean setTextBuffer(int arr[]) {
        buf = arr;

        if (buf.length > red.length) {
            JOptionPane.showMessageDialog(new JFrame(), "Cannot Encrypt\n No Enough Space To Store Data ", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            JFrame frame = new JFrame();
            frame.setIconImage(new ImageIcon("images/key.gif").getImage());
            pd = new PasswordDialog(frame);
            pd.setVisible(true);
            key = pd.getPassword();
            

            System.out.println("  key : " + key);

            for (i = 0; i < 8; i++) {
                buf[i + 8] = key.charAt(i);
            }

            for (i = 0; i < buf.length; i++) {
                t = buf[i];
                r = t % 8;
                t = t / 8;
                g = t % 7;
                b = t / 7;

                red[i] = r + (red[i] - red[i] % 8);
                green[i] = g + (green[i] - green[i] % 8);
                blue[i] = b + (blue[i] - blue[i] % 8);

            }
            return true;
        }

    }

    public ImageIcon mergeData() {
        size = 0;

        for (j = 0; j < h; j++) {
            for (i = 0; i < w; i++) {
                a = (alpha[size] << 24) & 0xff000000;
                r = (red[size] << 16) & 0x00ff0000;
                g = (green[size] << 8) & 0x0000ff00;
                b = (blue[size]) & 0x000000ff;
                pixels[j * w + i] = a | r | g | b;
                size++;

            }

        }
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        image.setRGB(0, 0, w, h, pixels, 0, w);
        ImageIcon icon = new ImageIcon(image);
        return icon;
    }

    public int[] getBuffer() {

        char str;
        String key = new String();
        int len;
        int ch;
        int x[] = new int[3];

        for (i = 0; i < 3; i++) {
            r = red[i] - (red[i] - red[i] % 8);
            g = green[i] - (green[i] - green[i] % 8);
            b = blue[i] - (blue[i] - blue[i] % 8);
            x[i] = (((b * 7) + g) * 8) + r;
        }
        len = x[0] * 10000 + x[1] * 100 + x[2];

        System.out.println("len : " + len);
        int chbuf[] = new int[len - 16];

        for (i = 8; i < 16; i++) {
            r = red[i] - (red[i] - red[i] % 8);
            g = green[i] - (green[i] - green[i] % 8);
            b = blue[i] - (blue[i] - blue[i] % 8);
            ch = (((b * 7) + g) * 8) + r;
            str = (char) ch;
            key = key + str;
        }

        pd = new PasswordDialog(new JFrame());
        pd.show();
        pdkey = pd.getPassword();
        pdkey = pdkey.substring(0, 8);

        System.out.println("  key : " + key);
        System.out.println("pdkey : " + pdkey);

        if (pdkey.equalsIgnoreCase(key)) {
            for (i = 16; i < len; i++) {

                r = red[i] - (red[i] - red[i] % 8);
                g = green[i] - (green[i] - green[i] % 8);
                b = blue[i] - (blue[i] - blue[i] % 8);

                ch = (((b * 7) + g) * 8) + r;
                chbuf[i - 16] = ch;
            }
            return chbuf;
        } else {

            JOptionPane.showMessageDialog(new JFrame(), "Invalid Password\n Try Again", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

    public int[] getpixels() {

        return pixels;

    }

    public int[][] getARGB() {

        int[][] ARGB = new int[4][w * h];

        ARGB[0] = alpha;
        ARGB[1] = red;
        ARGB[2] = green;
        ARGB[3] = blue;

        return ARGB;

    }

    public void saveImage(ImageIcon ico, String path, String ext, String type) {
        try {
            File file = new File(path);
            String ex = new Utils().getExtension(file);

            if (ex == null) {
                file = new File(path + "." + ext);
            }

            System.out.println(type);

            Image img = ico.getImage();

            if (type.equalsIgnoreCase("png") || type.equalsIgnoreCase("gif")) {
                image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

            } else {
                image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            }

            if (type.equalsIgnoreCase("gif")) {
                type = "jpg";
            }

            image.setRGB(0, 0, w, h, pixels, 0, w);
            ImageIO.write(image, type, file);

           
            runtime.gc();
            long l = runtime.freeMemory();
            System.out.println("Free Memory " + l / 1024 + "KB");
        } catch (IOException io) {
            System.out.println("Save ERROR:" + io.getMessage());
        }

    }

}

