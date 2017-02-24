package com.example.common.algorithm;

/**
 * author: zf
 * Date: 2017/2/24  13:18
 * Description:
 */
public class TimeSpaceCount {
	public static void main(String[] args) {

		int f = f(7);
		System.out.println(f);
	}
	static int f(int n){
       //O(n!) n 算法复杂度n的阶乘
		if (n == 0) return 1;
		else return n*f(n-1);
	}
}
