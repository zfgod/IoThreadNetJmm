package com.example.common.study.io;

import java.io.*;

/**
 * author: zf
 * Date: 2017/1/12  13:15
 * Description:  没太弄明白。。。
 */
public class DataInputStreamTest {
	private static final int LEN = 5;

	public static void main(String[] args) {
		// 测试DataOutputStream，将数据写入到输出流中。
//		testDataOutputStream() ;
		// 测试DataInputStream，从上面的输出流结果中读取数据。
		testDataInputStream() ;
	}

	/**
	 * DataOutputStream的API测试函数
	 */
	private static void testDataOutputStream() {

		try {
			File file = new File("e:\\io\\b.txt");
			DataOutputStream out =
					new DataOutputStream(
							new FileOutputStream(file));

			out.writeBoolean(true);
			out.writeByte((byte)0x41);
			out.writeChar((char)0x4243);
			out.writeShort((short)0x4445);
			out.writeInt(0x12345678);
			out.writeLong(0x0FEDCBA987654321L);

			out.writeUTF("abcdefghijklmnopqrstuvwxyz严12");

			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * DataInputStream的API测试函数
	 */
	private static void testDataInputStream() {

		try {
			File file = new File("e:\\io\\b.txt");
			DataInputStream in =
					new DataInputStream(
							new FileInputStream(file));

			System.out.printf("byteToHexString(0x8F):0x%s\n", byteToHexString((byte)0x8F));
			System.out.printf("charToHexString(0x8FCF):0x%s\n", charToHexString((char)0x8FCF));

			System.out.printf("readBoolean():%s\n", in.readBoolean());
			System.out.printf("readByte():0x%s\n", byteToHexString(in.readByte()));
			System.out.printf("readChar():0x%s\n", charToHexString(in.readChar()));
			System.out.printf("readShort():0x%s\n", shortToHexString(in.readShort()));
			System.out.printf("readInt():0x%s\n", Integer.toHexString(in.readInt()));
			System.out.printf("readLong():0x%s\n", Long.toHexString(in.readLong()));
			System.out.printf("readUTF():%s\n", in.readUTF());

			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 打印byte对应的16进制的字符串
	private static String byteToHexString(byte val) {
		return Integer.toHexString(val & 0xff);
	}

	// 打印char对应的16进制的字符串
	private static String charToHexString(char val) {
		return Integer.toHexString(val);
	}

	// 打印short对应的16进制的字符串
	private static String shortToHexString(short val) {
		return Integer.toHexString(val & 0xffff);
	}
	/*public static void main(String[] args) {
		try(
				DataInputStream inputStream =
						new DataInputStream(new FileInputStream("e:\\io\\b.txt"))
				) {
			int available = inputStream.available();
			System.out.println(available);
			int readUnsignedShort = inputStream.readUnsignedShort();
			String s = null;
			while ((available)>0){
			    s = inputStream.readUTF();
				System.out.println(s);
				available = inputStream.available();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
