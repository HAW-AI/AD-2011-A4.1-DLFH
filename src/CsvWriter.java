import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class CsvWriter {
	
	private final String ELEMENT_DELIMITER;
	
//constructors
	
	private CsvWriter(List<List<String>> data, String filePath, String delimiter) {
		ELEMENT_DELIMITER =  delimiter;
		write(data, filePath);
	}
	
	/**
	 * writes the fileData into the file "filePath", using the delimiter
	 * 
	 * @param List<List<String>> fileData:	the given list to print in a file
	 * @param String filePath:				the file to write into
	 * @param String delimiter:				separates an appointments attributes in the file
	 * 
	 * @return CsvWriter; writes a data-list into a file
	 */
	public static CsvWriter write(List<List<String>> fileData, String filePath, String delimiter){
		return new CsvWriter(fileData,filePath,delimiter);
	}
	
//writing
	
	private void write(List<List<String>> data, String filePath) {
		 try {
			FileWriter writer = new FileWriter(filePath);
			Iterator<List<String>> dataIter = data.listIterator();
			while(dataIter.hasNext()){
				Iterator<String> lineIter = dataIter.next().listIterator();
				while(lineIter.hasNext()){
					writer.append(lineIter.next());
					if(lineIter.hasNext()){
						writer.append(ELEMENT_DELIMITER);
					}
				}
				if(dataIter.hasNext()){
					writer.append("\n");
				}
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
