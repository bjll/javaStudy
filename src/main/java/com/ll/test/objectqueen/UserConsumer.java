package com.ll.test.objectqueen;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class UserConsumer extends Thread {
	LinkedBlockingQueue<List<User>>  linkedUserList;
	List<User> listUser=new ArrayList<User>();
	private boolean isRunning=true;//是否退出线程的标志
	public UserConsumer() {
		
	}
    public UserConsumer(LinkedBlockingQueue<List<User>>  linkedUserList) {
		this.linkedUserList=linkedUserList;
	}
    
    @Override
    public void run() {
    	 while(isRunning){
    		 try {
				//listUser=linkedUserList.poll(1, TimeUnit.SECONDS);
				if(listUser!=null){
					System.err.println(linkedUserList.poll(1, TimeUnit.SECONDS).size());
	    		}else{
	    			isRunning=false;
	    		}
			} catch (InterruptedException e) {
				  e.printStackTrace();
				  break;
			}catch (Exception e) {
				System.err.println("线程结束");
				break;
			}
    		 
    	 }
    }
}
