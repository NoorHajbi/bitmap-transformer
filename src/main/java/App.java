/*
 * This Java source file was generated by the Gradle 'init' task.
 */
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    public static void imageReading(Path path, File newPath){
        try {

            BufferedImage image = ImageIO.read(new File(path.toUri()));

            //To read width and height of the original image
            int w = image.getWidth(),  h = image.getHeight();

            BufferedImage bufferedImage = new BufferedImage(w,h, image.TYPE_INT_RGB);


            for(int row =1; row < w;row++){
                for( int col =1; col< h; col++){
                    //Get RGB Value
                    int val = image.getRGB(row, col);
                    //Convert to three separate channels
                    int r = (0x00ff0000 & val) >> 16;
                    int g = (0x0000ff00 & val) >> 8;
                    int b = (0x000000ff & val);
                    int m=(r+g+b);

                    if(m>=383)
                    {
                        // for light color it set white
                        bufferedImage.setRGB(row, col, Color.WHITE.getRGB());
                    }
                    else{
                        // for dark color it will set black
                        bufferedImage.setRGB(row, col, 0);
                    }
                }
            }
            ImageIO.write(bufferedImage, "jpg", newPath);
        }catch(Exception ex){
            System.out.println("Error :" + ex);
        }
    }
    public static void imageWrite(BufferedImage result,String targetName) {

        File output = new File("./app/src/main/resources/"+targetName+".bmp");
        try {
            ImageIO.write(result, "bmp", output);
        } catch (IOException e) {
            System.out.println("ERROR!");
            System.out.println(e);
        }
    }
    public String getGreeting() {
        return "Hello world.";
    }


    public static void main(String[] args) {
        Path path = Paths.get("src/main/resources/nature.bmp");
        File newPath = new File("src/main/resources/natureR.bmp");
        imageReading(path, newPath );
        System.out.print("PAth  "+newPath);
    }
}
