import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class dbload {

	//Call Database to begin operations
	public static void main(String[] args) {
		if (args.length == 3) {
			
			System.out.println("Argument one = " + args[0]);
	        System.out.println("Argument two = " + args[1]);
	        System.out.println("Argument three = " + args[2]);
	        
	        int bufferSize = Integer.parseInt(args[1]);
	        
	        List<List<String>> records = new ArrayList<>();
	        try (BufferedReader br = new BufferedReader(new FileReader("task3-sample.csv"))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] values = line.split(",");
	                System.out.println(values[1]);
	                records.add(Arrays.asList(values));
	            }
	            
	        } catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	        
	        char c ='c';
	        String test = "tesc";
	        String s = "";
	        for (int i = 0; i < test.length(); i++) {
	        	s += String.format("%8s", Integer.toBinaryString((int) test.charAt(i))).replace(' ', '0');
	        	System.out.println(s);
	        }
	        
//	        byte[] data = "4".getBytes(StandardCharsets.UTF_8);
	        System.out.println(String.format("%32s", Integer.toBinaryString(255)).replace(' ', '0'));
	        System.out.println((int) c);
	        System.out.println(Character.BYTES);
	        
//	        Path path = Paths.get("doc.txt");
//	        byte[] bytes = "Hellow".getBytes(StandardCharsets.UTF_8);
//	 
//	        try {
//	        	System.out.println(bytes[0]);
//	            Files.write(path, bytes);    // Java 7+ only
//	            System.out.println("Successfully written data to the file");
//	        }
//	        catch (IOException e) {
//	            e.printStackTrace();
//	        }
	        
//	        File file = new File("heap." + args[1]);
//	        try (FileWriter fos = new FileWriter(file)) {
//	            fos.write(s);
//	            System.out.println("Successfully written data to the file");
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
	        
		} else {
			System.err.println("Please provide the following command: java dbload -p [pagesize] [datafile]");
			System.exit(0);
		}
	}
}