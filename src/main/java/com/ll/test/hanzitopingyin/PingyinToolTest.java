package com.ll.test.hanzitopingyin;

import com.ll.test.hanzitopingyin.PinyinTool.Type;

public class PingyinToolTest {
	public static void main(String[] args) throws Exception{
		PinyinTool tool = new PinyinTool();
		System.out.println("刘亚壮的运行测试结果为====" + tool.toPinYin("刘亚壮", "", Type.LOWERCASE));
	}

}
