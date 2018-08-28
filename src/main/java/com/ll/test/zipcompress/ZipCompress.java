package com.ll.test.zipcompress;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 把文件(文件夹)压缩为zip 文件
 * 
 * @author lilin
 *
 */
public class ZipCompress {

	public static void main(String[] args) {
		business();
	}
	/**
	 * 执行压缩的方法
	 * 
	 * @param out
	 * @param bos
	 * @param sourceFile
	 * @param base
	 * @throws Exception
	 */
	public static void compress(ZipOutputStream out, BufferedOutputStream bos, File sourceFile, String base)
			throws Exception {
		// 如果路径为目录（文件夹）
		if (sourceFile.isDirectory()) {
			// 取出文件夹中的文件（或子文件夹）
			File[] flist = sourceFile.listFiles();

			if (flist.length == 0)// 如果文件夹为空，则只需在目的地zip文件中写入一个目录进入点
			{
				System.out.println(base + "/");
				out.putNextEntry(new ZipEntry(base + "/"));
			} else// 如果文件夹不为空，则递归调用compress，文件夹中的每一个文件（或文件夹）进行压缩
			{
				for (int i = 0; i < flist.length; i++) {
					compress(out, bos, flist[i], base + "/" + flist[i].getName());
				}
			}
		} else// 如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入zip文件中
		{
			out.putNextEntry(new ZipEntry(base));
			FileInputStream fos = new FileInputStream(sourceFile);
			BufferedInputStream bis = new BufferedInputStream(fos);
			int tag;
			// 将源文件写入到zip文件中
			while ((tag = bis.read()) != -1) {
				bos.write(tag);
			}
			bis.close();
			fos.close();
		}
	}

	/**
	 * 压缩前的准备方法
	 * 
	 * @param sourceFileName
	 *            要压缩的源文件路径
	 * @param zipFileName
	 *            压缩文件的目的地
	 */
	public static void zip(String sourceFileName, String zipFileName) {
		ZipOutputStream out = null;
		BufferedOutputStream bos = null;
		try {
			// 创建zip输出流
			out = new ZipOutputStream(new FileOutputStream(zipFileName));

			// 创建缓冲输出流
			bos = new BufferedOutputStream(out);
           
			File sourceFile = new File(sourceFileName);
			// 调用函数
			compress(out, bos, sourceFile, sourceFile.getName());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/*
	 * 这是业务逻辑的处理方法
	 */
	public  static  void  business(){
		try {
			System.err.println("开始执行压缩");
			String filePath="D:/Source/JAVA/java/大数据/elasticsearch/01_快速入门篇";
			File file=new  File(filePath);
			for (File file2:file.listFiles()) {
				zip(file2.getAbsolutePath(),file2.getName());
			}
			System.err.println("结束压缩");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
