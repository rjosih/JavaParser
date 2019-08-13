package root;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class MainParser {
	final static String FILE_NAME = "input_5";
	final static String OUTPUT_FILE_NAME = FILE_NAME + "(1)";
	public static int counter = 0;

	public static void main(String... args) throws IOException {
		MainParser binary = new MainParser();
		byte[] bytes = binary.readFile(FILE_NAME);

		String output = ParseData.parseByteArray(bytes);
		
		long start = System.currentTimeMillis();
		
		writeToNewFile(output, OUTPUT_FILE_NAME);
//		System.out.println(output);
		// Get elapsed time in milliseconds
		long elapsedTimeMillis = System.currentTimeMillis()-start;
//		// Get elapsed time in seconds
//		float elapsedTimeSec = elapsedTimeMillis/1000F;
		System.out.println("The parsing took: " + elapsedTimeMillis + "ns");
	}

	byte[] readFile(String fileName) throws IOException {
		Path path = Paths.get(fileName);
		return Files.readAllBytes(path);
	}

	static void writeToNewFile(String output, String fileName) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		writer.write(output);
		writer.close();
	}
}