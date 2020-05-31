package com.example.eclipsejdt.gettingstarted;

import java.io.IOException;

public class EclipseAstDemo {

	public static void main(String[] args) throws IOException {
		FileOperationUtils.operateOnJavaFiles(EclipseAstUtils::doAstOnJavaSurceCode);
	}
}
