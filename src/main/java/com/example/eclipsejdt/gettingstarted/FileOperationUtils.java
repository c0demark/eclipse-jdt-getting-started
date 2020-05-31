package com.example.eclipsejdt.gettingstarted;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class FileOperationUtils {

	public static String getJavaSourceFilesLocation() {
		return FileSystems.getDefault().getPath("code").toAbsolutePath().normalize().toString();
	}
	
	public static Stream<Path> walkJavaSourceFilesPath() throws IOException {
		return Files.walk(Paths.get(getJavaSourceFilesLocation()));
	}
	
	public static Stream<Path> getJavaSourceFiles() throws IOException {
		return walkJavaSourceFilesPath().filter(filePath -> Files.isRegularFile(filePath));
	}
	
	public static void operateOnJavaFiles(Consumer<? super Path> operation) throws IOException {
		getJavaSourceFiles().forEach(operation);
	}

	public static String getFileConetent(Path filePath) throws IOException {
		File file = filePath.toFile();
		StringBuilder stringBuilder = new StringBuilder();
		try(BufferedReader br  = new BufferedReader(new FileReader(file))){
			String strLine;
			while((strLine = br.readLine()) != null){
				stringBuilder.append(strLine);
			}
		}		
		return stringBuilder.toString();
	}
}
