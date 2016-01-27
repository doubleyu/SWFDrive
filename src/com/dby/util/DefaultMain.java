package com.dby.util;

public class DefaultMain {
	public static void main(String[] argv) {
		int exitCode = -1;
		MainDriver md = new MainDriver();
		try {
			

			exitCode = md.run(argv);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		System.exit(exitCode);
	}
}