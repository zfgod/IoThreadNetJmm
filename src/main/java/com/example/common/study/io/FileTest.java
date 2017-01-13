package com.example.common.study.io;

import java.io.File;

/**
 * author: zf
 * Date: 2017/1/11  18:17
 * Description:file 使用
 */
public class FileTest {
	public static void main(String[] args) {
		File file1 = new File("e://io/a.txt");
		File file2 = new File("e://io/b.txt");
		if(file1.exists() && file1.isFile()){
			file1.renameTo(file2);//重命名,原文件移除,可以使用在文件删除中,直接备份到别的目录并重命名,不直接删除文件
		}

		File dir = new File("e://io");
		File dirs = new File("e://io//testDir");
		if(!dirs.exists()){
			dirs.mkdirs();
		}
		if(dir.exists() && dir.isDirectory()){
			File[] files = dir.listFiles();//查看目录下的文件及目录
			for (File file : files) {
				String type = file.isDirectory() ? "目录-": "文件-" ;
				System.out.println(type+file.getName());
			}
			String[] list = dir.list();//目录下文件和目录的名称
			for (String s : list) {
				System.out.println(s);
			}
		}

	}
}
