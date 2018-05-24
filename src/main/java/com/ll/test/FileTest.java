package com.ll.test;

import java.io.File;
import java.io.IOException;

public class FileTest {
	//文件删除操作
	public static void main(String[] args) throws IOException {
		File  file=new  File("D://test");
		//每次执行前要先删除以前有的图防止时间过长产生多过多的图片
		if(!file.exists()){//如果文件夹不存在则创建一个新的文件夹
			file.mkdir();
		}
		if (file.isDirectory()) {
			String[] files = file.list();//得到目录下的文件
			int fileLenth=files.length;//判断是否存在文件
			if(fileLenth!=0){
				File tempFile=null;
				for (int i = 0; i < fileLenth; i++) {
					tempFile=new  File(file,files[i]);
					 if (tempFile.isFile() && tempFile.exists()) {
						 tempFile.delete();
					 }
					
				}
			}
		}
	}
}
