import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class dbload {
	
	private final static int INTEGER_BYTE_SIZE = 4;
	private final static int BYTE_SIZE = 1024;
	private final static int TO_MILISECOND = 10^6;
	
//	For testing purpose
//	private final static String DATA_SAMPLE = "./DBS_Sample.csv";
	
	
	public static void main(String[] args) {
		
		if (args.length == 3 && args[0].equals("-p")) {
			
			System.out.println("Argument one = " + args[0]);
	        System.out.println("Argument two = " + args[1]);
	        System.out.println("Argument three = " + args[2]);
	        
	        int pageSize = Integer.parseInt(args[1]);
	        
	        if ( (pageSize % BYTE_SIZE) != 0) {
	        	System.err.println("Size input must be a multiple of 1KB");
	        	System.exit(0);
	        }
	        
	        int remainingSize = Integer.parseInt(args[1]);
	        int pageCount = 0;
	        int numberOfRecord = 0;
	        int totalRecord = 0;
	        
	        long startTime = System.nanoTime();
	        System.out.println("Generating heap file ...");
	        
	        try (BufferedReader br = new BufferedReader(new FileReader(args[2]))) {
	            String line;
	            FileOutputStream fileOs = new FileOutputStream("heap." + pageSize);
	            DataOutputStream os = new DataOutputStream(fileOs);
	            
	            while ((line = br.readLine()) != null) {
	            	
//	            	create a page (pageCount++)
	            	if (pageCount == 0) {pageCount++;}
	            	
	            	String[] values = line.split(",");
	                
//	            	pageSize - data bytes to control record size in a page. If the whole record fit in the current page, the add, else create new page
	                int sizeOfRecord = (INTEGER_BYTE_SIZE * 6 + values[1].length() + values[5].length() + values[3].length() + values[8].length());
	                
	                if (remainingSize > sizeOfRecord) {
	                	
	                	os.writeUTF(values[1]);
	                	os.writeInt(Integer.parseInt(values[0]));
			            os.writeInt(Integer.parseInt(values[2]));
			            os.writeUTF(values[3]);
			            os.writeInt(Integer.parseInt(values[4]));
			            os.writeUTF(values[5]);
			            os.writeInt(Integer.parseInt(values[6]));
			            os.writeInt(Integer.parseInt(values[7]));
			            os.writeUTF(values[8]);
			            os.writeInt(Integer.parseInt(values[9]));
			            
			            remainingSize -= sizeOfRecord;
			            numberOfRecord++;
			            totalRecord++;
			            
	                } else {
	                	// Add recordCount each page, create new page by adding delimiter to separate the pages ($)
	                	os.writeUTF(numberOfRecord + "$");
	                	
	                	// Reset counter
	                	pageCount++;
	                	numberOfRecord = 0;
	                	remainingSize = Integer.parseInt(args[1]);
	                	
	                	os.writeUTF(values[1]);
	                	os.writeInt(Integer.parseInt(values[0]));
			            os.writeInt(Integer.parseInt(values[2]));
			            os.writeUTF(values[3]);
			            os.writeInt(Integer.parseInt(values[4]));
			            os.writeUTF(values[5]);
			            os.writeInt(Integer.parseInt(values[6]));
			            os.writeInt(Integer.parseInt(values[7]));
			            os.writeUTF(values[8]);
			            os.writeInt(Integer.parseInt(values[9]));
			            
			            remainingSize -= sizeOfRecord;
			            numberOfRecord++;
			            totalRecord++;
	                	
	                }
		            
	            }
	            
	            os.close();
	            
	        } catch (FileNotFoundException e1) {
	        	System.err.println("Cannot find file: " + args[2]);
				e1.printStackTrace();
			} catch (IOException e1) {
				System.err.println("IOException");
				e1.printStackTrace();
			}
	        
	        long endTime = System.nanoTime();
	        long duration = (endTime - startTime) / TO_MILISECOND;
	        
	        System.out.println("Successfully generated: " + "heap." + pageSize);
	        System.out.println("Total of Records loaded: " + totalRecord);
	        System.out.println("Number of Pages used: " + pageCount);
	        System.out.println("Time taken in miliseconds: " + duration);
	        
	        
		} else {
			System.err.println("Please provide the following command: java dbload -p [pagesize] [datafile]");
			System.exit(0);
		}
	}
}