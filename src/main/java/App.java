import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

//Bitmap Class
public class App {
    public static void imageReading(Path path, File newPath) throws IOException {
        BufferedImage image = ImageIO.read(new File(path.toUri()));

        //To read width and height of the original image
        int w = image.getWidth(), h = image.getHeight();
        BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        for (int row = 1; row < w; row++) {
            for (int col = 1; col < h; col++) {
                //Get RGB Value
                int val = image.getRGB(row, col);
                //Convert to three separate channels
                int r = (0x00ff0000 & val) >> 16;
                int g = (0x0000ff00 & val) >> 8;
                int b = (0x000000ff & val);
                int m = (r + g + b);

                if (m >= 383) {
                    // for light color it set white
                    bufferedImage.setRGB(row, col, Color.WHITE.getRGB());
                } else {
                    // for dark color it will set black
                    bufferedImage.setRGB(row, col, 0);
                }
            }
        }
        ImageIO.write(bufferedImage, "bmp", newPath);
    }

    public static void RotateImage(Path path, File newPath) throws IOException {
        BufferedImage image = ImageIO.read(new File(path.toUri()));
        int w = image.getHeight();
        int h = image.getHeight();
        BufferedImage bufferedImage = new BufferedImage(h, w, BufferedImage.TYPE_INT_RGB);

        for (int row = 0; row < w; row++) {

            for (int col = 0; col < h; col++) {
                int val = image.getRGB(row, col);
                bufferedImage.setRGB(col, w - row - 1, val);

            }
        }
        ImageIO.write(bufferedImage, "bmp", newPath);
    }

    public static void main(String[] args) throws IOException {
        Path path;
        File newPath = new File("src/main/resources/output/natureBW.bmp");

        //User selection
        Scanner sc = new Scanner(System.in);
        int selection;
        String imgName = "nature";
        boolean exit = true;
        System.out.println("Welcome! ");
        System.out.println("Which picture do you like to be converted? \n Type any number between 1 and 5");
        System.out.println("1)Eagle");
        System.out.println("2)Flower");
        System.out.println("3)Kids");
        System.out.println("4)Nature");
        System.out.println("5)Night");
        selection = sc.nextInt();
        switch (selection) {
            case 1:
                imgName = "eagle";
                break;
            case 2:
                imgName = "flower";
                break;
            case 3:
                imgName = "Kids";
                break;
            case 4:
                imgName = "nature";
                break;
            case 5:
                imgName = "night";
                break;
        }
        path = Paths.get("src/main/resources/input/" + imgName + ".bmp");
        while (exit) {
            System.out.println("Welcome! ");
            System.out.println("What method do you want to apply? \n Type any number between 1 and 2 \n 3. is for Exit");
            System.out.println("1)Black And White");
            System.out.println("2)Rotate");
            System.out.println("3)Exit");
            selection = sc.nextInt();
            switch (selection) {
                case 1:
                    newPath = new File("src/main/resources/output/" + imgName + "BW.bmp");
                    imageReading(path, newPath);
                    break;

                case 2:
                    newPath = new File("src/main/resources/output/" + imgName + "Rotate.bmp");
                    RotateImage(path, newPath);
                    break;
                case 3:
                    exit = false;
                    break;
            }
        }
        System.out.print("Path=  " + newPath);
    }
}
