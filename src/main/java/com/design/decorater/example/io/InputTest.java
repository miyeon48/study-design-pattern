package com.design.decorater.example.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputTest {
	public static void main(String[] args) throws IOException {
		int c;
		InputStream in = null;
		
		try {
			in = 
				new LowerCaseInputStream( 
					new BufferedInputStream(
						new FileInputStream(InputTest.class.getResource("").getPath() + "test.txt")
						)
					);

			while((c = in.read()) >= 0) {
				System.out.print((char)c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) { in.close(); }
		}
		
	}

}
