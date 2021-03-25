import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class dbquery {

	public static void main(String[] args) {
		if (args.length == 2) {
			
			System.out.println("Argument one = " + args[0]);
	        System.out.println("Argument two = " + args[1]);
			
			try {
				
				FileInputStream fileIs = new FileInputStream("heap." + args[1]);
				ObjectInputStream is = new ObjectInputStream(fileIs);
				int x = is.readInt();
				String y = is.readUTF();
				System.out.println(x);
				System.out.println(y);
				is.close();
				
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			System.err.println("Please provide the following command: java dbquery [text] [pagesize] ");
			System.exit(0);
		}
	}

}
