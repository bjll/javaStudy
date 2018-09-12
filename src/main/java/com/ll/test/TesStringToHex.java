package com.ll.test;

import java.io.ByteArrayOutputStream;

/**
 * 汉子转换为16 进制
 * @author Chris
 *
 */
public class TesStringToHex {
	/**
	  * @16进制数字与字符串互相转换
	  */

	 private static String hexString = "0123456789ABCDEF"; //此处不可随意改动

	   /* 将字符串编码成16进制数字 */
	    public static String encode(String str) {
	    // 根据默认编码获取字节数组
	    byte[] bytes = str.getBytes();
	    StringBuilder sb = new StringBuilder(bytes.length * 2);
	    // 将字节数组中每个字节拆解成2位16进制整数
	    for (int i = 0; i < bytes.length; i++) {
	     sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
	     sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
	    }
	    return sb.toString();
	 }
	 /*将16进制数字解码成字符串*/ 
	 public static String decode(String bytes) {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream(
	      bytes.length() / 2);
	    // 将每2位16进制整数组装成一个字节
	    for (int i = 0; i < bytes.length(); i += 2)
	     baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
	       .indexOf(bytes.charAt(i + 1))));
	    return new String(baos.toByteArray());
	 }

	 /*输出最终值*/
	    public static void main(String[] args) {
	   // System.out.println(encode("手机"));                    //测试将字符串转化为16进制
	   System.out.println(decode("E6898BE69CBA"));   //反向测试将16进制转化为字符串

	 }
}
