import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataReader {

	ArrayList<String> values = new ArrayList<>();
	
	public DataReader(String filename){ // reads CSV files
		String csvFile = filename;
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				String[] data = line.split(csvSplitBy);
				for (int i = 0; i < data.length; i ++){
					values.add(data[i]);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public ArrayList<String> getData(){
		return values;
	}
}
