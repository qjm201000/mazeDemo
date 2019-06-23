import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public class Image {
    //图片四周的空白去除,指定x轴的位置
    private final Integer START_X_ZERO_LENGTH = 13;
    private final Integer END_X_ZERO_LENGTH = 12;
    private final Integer START_Y_ZERO_LENGTH = 5;
    private final Integer END_Y_ZERO_LENGTH = 5;


    /**
     * 读取一张图片的RGB值
     *
     * @Return 返回像素值集合
     */
    public int [][] getImagePixel(String image){

        int[] rgb = new int[3];
        File file = new File(image);
        System.out.println(file.getAbsolutePath());
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int width = bi.getWidth() - START_X_ZERO_LENGTH - END_X_ZERO_LENGTH;
        int height = bi.getHeight() - START_Y_ZERO_LENGTH - END_Y_ZERO_LENGTH;
//        System.out.println("width=" + width + ",height=" + height + ".");
        //迷宫像素集合
        int [][] paths = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pixel = bi.getRGB(i + START_X_ZERO_LENGTH, j + START_Y_ZERO_LENGTH); // 下面三行代码将一个数字转换为RGB数字
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                if(rgb[0] == 0 && rgb[1] == 0 && rgb[2] == 0){
                    //黑色表示1
                    paths[i][j] = ColorNumber.BLACK_NUMBER.getCode();
                }else{
                    //白色表示0
                    paths[i][j] = ColorNumber.WHITE_NULBER.getCode();
                }
            }
        }
        return paths;
    }

    /**
     * 生成最新图片
     *
     * @param newPaths 新的像素集合
     * @param generateFileUrl 生成图片的地址
     * @return
     * @throws AWTException
     */
    public void generateImage(MazeNode[][] newPaths, String generateFileUrl) throws IOException {
        BufferedImage bi = new BufferedImage(newPaths.length,newPaths[0].length, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                if(newPaths[i][j].getVal() == ColorNumber.BLACK_NUMBER.getCode()){
                    bi.setRGB(i, j, ColorNumber.BLACK_NUMBER.getColor().getRGB());
                }else if(newPaths[i][j].getVal() == ColorNumber.WHITE_NULBER.getCode()){
                    bi.setRGB(i, j, ColorNumber.WHITE_NULBER.getColor().getRGB());
                }else{
                    bi.setRGB(i, j, ColorNumber.RED_NULBER.getColor().getRGB());
                }
            }
        }
        Iterator<ImageWriter> it = ImageIO.getImageWritersByFormatName("jpg");
        ImageWriter writer = it.next();
        File f = new File(generateFileUrl);
        ImageOutputStream ios = ImageIO.createImageOutputStream(f);
        writer.setOutput(ios);
        writer.write(bi);
        bi.flush();
        ios.flush();
        ios.close();
    }
}