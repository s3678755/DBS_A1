import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class dbquery {
	
	private final static int TO_MILISECOND = 10^6;

	public static void main(String[] args) {
		if (args.length == 2) {
			
			System.out.println("Argument one = " + args[0]);
	        System.out.println("Argument two = " + args[1]);
	        
	        int totalMatch = 0;
	        
	        long startTime = System.nanoTime();
			
			try {
				
				FileInputStream fileIs = new FileInputStream("~\\DBS_A1\\DBS_Java\\heap." + args[1]);			
				DataInputStream is = new DataInputStream(fileIs);
				
				int remainingBytes = is.available();
				int id;
				String dateTime = "";
				int year;
				String month;
				int mdate;
				String day;
				int time;
				int sensorID;
				String sensorName;
				int hourlyCounts;
				
				boolean isChecking = false;
													
				while(remainingBytes > 0) {
					
//					Add a lock to check if the next information is dateTime or end of page
					if(!isChecking) {
						dateTime = is.readUTF();
					}
					
					id = is.readInt();
					year = is.readInt();
					month = is.readUTF();
					mdate = is.readInt();
					day = is.readUTF();
					time = is.readInt();
					sensorID = is.readInt();
					sensorName = is.readUTF();
					hourlyCounts = is.readInt();
					
					
					String query = sensorID + dateTime  ;
					
					if (query.contains(args[0])) {
						totalMatch++;
						System.out.println("UUID: " + id + " | " + "DateTime: " + dateTime + " | " + "Year: " + year + " | " + "Month: " + month + " | " + "MDate: " + mdate + " | " + "Day: " + day + " | " + "Time: " + time + " | " + "Sensor_ID: " + sensorID + " | " + "Sensor_Name: " + sensorName + " | " + "Hourly Counts: " + hourlyCounts);
					}
					
					remainingBytes = is.available();
					
					if (remainingBytes > 0) {
						
						String checkIfEndOfPage = is.readUTF();
						isChecking = true;
						
						if(checkIfEndOfPage.indexOf('$') != -1) {
							remainingBytes += checkIfEndOfPage.length();
							isChecking = false;
						} else {
							dateTime = checkIfEndOfPage;
						}
					}
									
				}
				
				is.close();
			
			} catch (FileNotFoundException e1) {
				System.err.println("Cannot find file: heap." + args[1]);
			} catch (EOFException e1) {
				e1.printStackTrace();
				System.err.println(e1);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			long endTime = System.nanoTime();
			long duration = (endTime - startTime) / TO_MILISECOND;
			
			System.out.println("Found total " + totalMatch + " record(s)");
			System.out.println("Time taken in miliseconds: " + duration);
			
		} else {
			System.err.println("Please provide the following command: java dbquery \"[SensorID_DateTime]\" [pagesize]");
			System.exit(0);
		}
	}

}
