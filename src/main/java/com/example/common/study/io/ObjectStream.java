package com.example.common.study.io;

import com.example.model.Users;

import java.io.*;

/**
 * author: zf
 * Date: 2017/1/12  14:46
 * Description:
 */
public class ObjectStream {
	public static void main(String[] args) {
		Users u = new Users("a","a","a",1);
		String file ="E:\\io\\test.data";
		useObjectStreamWrite(u, file);
		useObjectStreamRead(file);
	}

	private static void useObjectStreamRead(String file) {
		try(
			ObjectInputStream inputStream
				= new ObjectInputStream(new FileInputStream(file));
			){
			Users o = (Users) inputStream.readObject();
			System.out.println(o.toString());
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private static void useObjectStreamWrite(Users u, String file) {
		try(
		  ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))
				){
			outputStream.writeObject(u);
			outputStream.flush();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
