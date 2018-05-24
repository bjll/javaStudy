package com.ll.test;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//java 批量导入图片到excel
public class ExportImageToExcel {
  public static void main(String[] args) {
	   FileOutputStream fileOut = null;
	   BufferedImage bufferImg = null;
	   // 先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
	   try {
	   ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
	   bufferImg = ImageIO.read(new File("D:/deskPicture/timg.jpg"));//读取文件
	   ImageIO.write(bufferImg, "jpg", byteArrayOut);
	   HSSFWorkbook wb = new HSSFWorkbook();
	   HSSFSheet sheet1 = wb.createSheet("test picture");
	  // 画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
	  HSSFPatriarch patriarch = sheet1.createDrawingPatriarch();
	   for (int i = 0; i < 3; i++) {
	   // anchor主要用于设置图片的属性
	   HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 250, (short) 1, 1+i*10, (short) 5, 8+i*10);
	   // 插入图片
	   patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
	   }
	   HSSFSheet sheet2 = wb.createSheet("test picture2");
	   //第二个sheet页
	   HSSFPatriarch patriarch2 = sheet2.createDrawingPatriarch();
	   for (int i = 0; i <3 ; i++) {
	   // anchor主要用于设置图片的属性
	   HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 250, (short) 1, 1+i*10, (short) 5, 8+i*10);
	   // 插入图片
	   patriarch2.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
	   }
	   fileOut = new FileOutputStream("D://Excel.xls");
	   // 写入excel文件
	   wb.write(fileOut);
	   System.out.println("----Excle文件已生成------");
	   } catch (Exception e) {
	   e.printStackTrace();
	   } finally {
	   if (fileOut != null) {
	   try {
	   fileOut.close();
	   } catch (IOException e) {
	   e.printStackTrace();
	  }
	   }
}
	/*File file=  FileSystemView.getFileSystemView() .getHomeDirectory();//获取用户的桌面路径
	System.err.println(file);*/
}
 /* 62 // 关于HSSFClientAnchor(dx1,dy1,dx2,dy2,col1,row1,col2,row2)的参数，有必要在这里说明一下：
  63 // dx1：起始单元格的x偏移量，
  64 // dy1：起始单元格的y偏移量，
  65 // dx2：终止单元格的x偏移量，
  66 // dy2：终止单元格的y偏移量，
  67 // col1：起始单元格列序号，从0开始计算；
  68 // row1：起始单元格行序号，从0开始计算，
  69 // col2：终止单元格列序号，从0开始计算；
  70 // row2：终止单元格行序号，从0开始计算，
  71 //添加多个图片时:多个pic应该share同一个DrawingPatriarch在同一个sheet里面。
  	/**
	 * 将图片进行缩放
	 * @param data
	 * @param nw
	 * @param nh
	 * @return
	 */
    public   byte[] ChangeImgSize(byte[] data, int nw, int nh){     
        byte[] newdata = null;     
        try{      
             BufferedImage bis = ImageIO.read(new ByteArrayInputStream(data));     
                int w = bis.getWidth();     
                int h = bis.getHeight();     
                double sx = (double) nw / w;     
                double sy = (double) nh / h;     
                AffineTransform transform = new AffineTransform();     
                transform.setToScale(sx, sy);     
                AffineTransformOp ato = new AffineTransformOp(transform, null);     
                //原始颜色     
                BufferedImage bid = new BufferedImage(nw, nh, BufferedImage.TYPE_3BYTE_BGR);     
                ato.filter(bis, bid);     
                //转换成byte字节     
                ByteArrayOutputStream baos = new ByteArrayOutputStream();     
                ImageIO.write(bid, "png", baos);     
                newdata = baos.toByteArray();     
        }catch(IOException e){      
             e.printStackTrace();      
        }      
        return newdata;     
    }
}
