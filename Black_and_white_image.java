/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_and_white_image;

//import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Black_and_white_image {

    public static void main(String[] args) throws IOException {
        File s = null;
        BufferedImage binary = null;
        try {
            BufferedImage img = ImageIO.read(new File("C:\\Users\\frede\\Videos\\SDU Robot Diplom\\Semester projekt 1\\Rainbow.PNG"));

            binary = new BufferedImage(img.getWidth(), img.getHeight(),
                    BufferedImage.TYPE_BYTE_BINARY);

            Graphics2D g = binary.createGraphics();
            g.drawImage(img, 0, 0, null);

            HashSet<Integer> colors = new HashSet<>();
            int color = 0;
            for (int y = 0; y < binary.getHeight(); y++) {
                for (int x = 0; x < binary.getWidth(); x++) {
                    color = binary.getRGB(x, y);
                    

                    
                    if (color == -1) {
                        binary.setRGB(x, y, -1);
                    } else {
                        binary.setRGB(x, y, -16777216);
                    }

                    System.out.println(color);
                    colors.add(color);

                }
            }

            System.out.println(colors.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            s = new File("C:\\Users\\frede\\Videos\\SDU Robot Diplom\\Semester projekt 1\\new picture.jpg");
            ImageIO.write(binary, "jpg", s);
            System.out.println("File writing completed ");
        } catch (IOException e) {
            System.out.println("Error: " + e);

        }
       /* BufferedImage binary2 = null;
        try {
            BufferedImage image = ImageIO.read(new File("C:\\Users\\frede\\Videos\\SDU Robot Diplom\\Semester projekt 1\\new picture.jpg"));
//            binary2 = new BufferedImage(image.getWidth(), image.getHeight(), image.getRGB(0, 0));
        } catch (IOException e) {
            System.out.println(" " + e.getMessage());
        } */
        File input = new File("C:\\Users\\frede\\Videos\\SDU Robot Diplom\\Semester projekt 1\\new picture.jpg");
        BufferedImage image = ImageIO.read(input);
        BufferedImage resized = resize(image, 12, 24);
        File output = new File("C:\\Users\\frede\\Videos\\SDU Robot Diplom\\Semester projekt 1\\new picture_output.jpg");
        ImageIO.write(resized, "jpg", output);
         File outputtest = new File("C:\\Users\\frede\\Videos\\SDU Robot Diplom\\Semester projekt 1\\new picture_output_test.jpg");
       Picture p1 = new Picture(output); 
       p1.getRGB(0, 0);
       System.out.println(p1.getImage());
       
       p1.show();
       int[][] compute = compute(outputtest);
    }

    /*
        Image image;
         image = ImageIO.read(new File("C:\\Users\\frede\\Videos\\SDU Robot Diplom\\Semester projekt 1\\new picture.jpg"));
            BufferedImage buffered = (BufferedImage) image;
            createResizedCopy(buffered,300, 600, true);
        }
    
    public static BufferedImage createResizedCopy(Image OriginalImage, int scaledWidth, int scaledHeight, boolean preserveAlpha)
    {
        System.out.println("resizing...");
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaledBI = new BufferedImage(600, 318, 1);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        Image img = null;
        g.drawImage(img, 0, 0, scaledWidth, scaledHeight, null); 
        g.dispose();
        return scaledBI;
    }
     */
    
    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    public static int[][] convertToArray(BufferedImage image) {

        if (image == null || image.getWidth() == 0 || image.getHeight() == 0) {
            return null;
        }
        // This returns bytes of data starting from the top left of the bitmap
        // image and goes down.
        // Top to bottom. Left to right.
        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();

        final int width = image.getWidth();
        final int height = image.getHeight();

        int[][] result = new int[height][width];

        boolean done = false;
        boolean alreadyWentToNextByte = false;
        int byteIndex = 0;
        int row = 0;
        int col = 0;
        int numBits = 0;
        byte currentByte = pixels[byteIndex];
        while (!done) {
            alreadyWentToNextByte = false;

            result[row][col] = (currentByte & 0x80) >> 7;
            currentByte = (byte) (((int) currentByte) << 1);
            numBits++;

            if ((row == height - 1) && (col == width - 1)) {
                done = true;
            } else {
                col++;

                if (numBits == 8) {
                    currentByte = pixels[++byteIndex];
                    numBits = 0;
                    alreadyWentToNextByte = true;
                }

                if (col == width) {
                    row++;
                    col = 0;

                    if (!alreadyWentToNextByte) {
                        currentByte = pixels[++byteIndex];
                        numBits = 0;
                    }
                }
            }
        }

        return result;
    }

    public static int[][] compute(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            Raster raster = image.getData();
            int w = raster.getWidth(), h = raster.getHeight();
        
            int pixels[][] = new int[w][h];
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    
                    pixels[x][y] = raster.getSample(x, y, 0);
                }
            }
            String[] position = {"X", "Y"};
            try (
                    PrintStream output = new PrintStream(new File("C:\\Users\\frede\\Videos\\SDU Robot Diplom\\output.txt"));) {
                 for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                   // output.print(pixels[x][y] + ", ");
                   if (pixels[x][y] < 125){
                       output.println("x " + x + " y " + y);
                   }
                }
                output.println("");
                 }
                 
                    
               /* for (int i = 0; i < position.length; i++) {
                    String sc = "";
                    for (int j = 0; j < pixels[i].length; j++) {
                        sc += pixels[i][j] + " ";
                    }
                    output.println("Placering  " + position[i] + " værdi af pixels " + sc);
                }
                */output.close();

            } catch (FileNotFoundException e) {

                e.printStackTrace();
            }
            return pixels;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
