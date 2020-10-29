package psn.cyf.util;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;


public class Draw {
    public static void main(String[] args) throws IOException {

        System.out.println(new HashMap<String,String>().toString().length());
        int [] startPoint={10,10};//起始点
        int stepSize=10;//步长
        int [] nums=initArr();//初始化要打印的数组

        System.out.println(new Double(Math.sqrt(11)).intValue());
    }

    /**
     * @param startPoint 初始点的位置
     * @param stepSize
     * @param arr
     * @param size
     */
    private static void draw(int [] startPoint,int stepSize,final int [] arr,int[] size){
        BufferedImage bi = new BufferedImage(800,600,BufferedImage.TYPE_INT_RGB);//INT精确度达到一定,RGB三原色
        //得到它的绘制环境(这张图片的笔)
        Graphics2D g2 = (Graphics2D) bi.getGraphics();
        g2.fillRect(0,0,size[0],size[1]);//填充一个矩形 左上角坐标(0,0)填充整张图片
        //设置背景色
        g2.setColor(Color.WHITE);
        g2.fillRect(0,0,size[0],size[1]);//背景色填充整张图片

        g2.drawString("1",3,50); //向图片上写字符串
    }
    private int [] getRowAndCol(int [] arr){
        int len=arr.length;
       int col= new Double(Math.sqrt(len)).intValue();
       if(col*col<len){

       }
        return null;
    }
    private static int [] initArr(){
        int [] nums=new int[16];//要打印的数组 
        for (int i=0;i<nums.length;i++){
            nums[i]=i;
        }
        return nums;
    }

    /*

     Random rand = new Random();
        //得到图片缓冲区
        BufferedImage bi = new BufferedImage

                (150,70,BufferedImage.TYPE_INT_RGB);//INT精确度达到一定,RGB三原色，高度70,宽度150

//得到它的绘制环境(这张图片的笔)
        Graphics2D g2 = (Graphics2D) bi.getGraphics();

        g2.fillRect(0,0,150,70);//填充一个矩形 左上角坐标(0,0),宽70,高150;填充整张图片
//设置颜色
        g2.setColor(Color.WHITE);
        g2.fillRect(0,0,150,70);//填充整张图片(其实就是设置背景颜色)

//        g2.setColor(Color.RED);

//        g2.drawRect(0,0,150-1,70-1); //画边框
        g2.setFont(new Font("宋体",Font.BOLD,18)); //设置字体:字体、字号、大小
        g2.setColor(Color.BLUE);//设置背景颜色
        //g2.drawOval(55,15,20,20);
        g2.drawString("1",3,50); //向图片上写字符串

        ImageIO.write(bi,"JPEG",new FileOutputStream("F:/b.jpg"));//保存图片 JPEG表示保存格式

     */
}
