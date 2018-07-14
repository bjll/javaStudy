package com.ll.test.queue;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.LinkedBlockingQueue;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class Consumer extends Thread {
	private LinkedBlockingQueue<String> b;
	private BufferedWriter bWriter;
	public Consumer(LinkedBlockingQueue<String> b) {
		this.b = b;
	}
    
	public void run() {
		try {
			int count=1;
	        bWriter = new BufferedWriter( new OutputStreamWriter( new FileOutputStream("E:\\test.txt"),"utf-8"));
			String resultStr="";
			String tempStr="";
			int  x=0;
	        while (true) {
	        	//System.err.println("---Consumer--"+b.poll());
	        	tempStr=b.poll();
	        	System.err.println("-----Consumer---"+tempStr);
	        	if(count==108001){
	        		break;
	        	}
	        	if(tempStr!=null&&!"".equals(tempStr)){
	        		resultStr+=tempStr+"\n";
	        		if(count%4000==0){
		        		bWriter.write(resultStr);
		        		System.err.println("第"+x+"次提交");
		        		x++;
		        		resultStr="";
		        	}
	        		count++;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(bWriter!=null){
				try {
					bWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
