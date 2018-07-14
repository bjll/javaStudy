package com.ll.test.queue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 生产者的类
 * 
 * @author Chris
 *
 */
public class Producer extends Thread {
	private LinkedBlockingQueue<String> b;// 定义一个队列

	public Producer() {
	}
	
	public Producer(LinkedBlockingQueue<String> b) {
		this.b = b;
	}
    //这里是执行生产的过程
	@Override
	public void run() {
		try {
			File file=new java.io.File("E:\\hbasetest\\test3.txt");
			//FileInputStream  fileInputStream=new  FileInputStream(file);
			FileReader m=new FileReader(file);
			BufferedReader reader=new BufferedReader(m);//读取文件
			int count=1;//作为每一行的ID插入
			String line="";
			while (true) {
				line=reader.readLine();
				System.err.println("Producer---"+line);
				if(line==null||"".equals(line)){
					break;
				}
				//执行操作
				line=count+"\t"+line;
				b.offer(line);
				line="";
				count++;
				
			}
			System.err.println("---Producer----"+count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
