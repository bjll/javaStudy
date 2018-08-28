package com.ll.test.objectqueen;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;


public class UserProducer extends Thread {
	
	LinkedBlockingQueue<List<User>>  linkedUserList;
	
	List<User> userList=new ArrayList<User>();
	
	List<User> tempList=new ArrayList<User>() ;
	public UserProducer(){
		
	}
    public UserProducer(LinkedBlockingQueue<List<User>> linkedUserList){
		this.linkedUserList=linkedUserList;
	}
	@Override
	public void run() {
		for (int i = 0; i < 100001; i++) {
			userList.add(new User("test"+i, "xxx"+i, 10+i));
			if(userList.size()%5000==0){
				try {
					tempList.clear();
					tempList.addAll(userList);
					//Thread.currentThread().sleep(1000);
					linkedUserList.put(tempList);
					userList.clear();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				
			}
			if(i>=100000){
				try {
					tempList.clear();
					tempList.addAll(userList);
					//Thread.currentThread().sleep(1000);
					linkedUserList.put(tempList);
					userList.clear();
				} catch (Exception e) {
					
				}
			}
		}
		
	}
}
