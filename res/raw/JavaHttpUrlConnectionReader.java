package urconnection;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * A complete Java class that shows how to open a URL, then read data (text) from that URL,
 * HttpURLConnection class (in combination with an InputStreamReader and BufferedReader).
 *
 * @author alvin alexander, devdaily.com.
 *
 */
public class JavaHttpUrlConnectionReader {
	
//	public static void main(String[] args) throws Exception {
//		String file = "resultado.txt";
//		String [] url = {"http://www.hearthpwn.com/cards?display=1", "http://www.hearthpwn.com/cards?display=1&page=2",
//				"http://www.hearthpwn.com/cards?display=1&page=3","http://www.hearthpwn.com/cards?display=1&page=4",
//				"http://www.hearthpwn.com/cards?display=1&page=5","http://www.hearthpwn.com/cards?display=1&page=6"};
//		for(int i=0; i<url.length;i++)
//			new JavaHttpUrlConnectionReader(url[i], file);
//			
//	}
	
	public JavaHttpUrlConnectionReader(String myUrl, String file) {
		
		try {
			
			//String myUrl = "http://www.hearthpwn.com/cards?display=1";
			// if your url can contain weird characters you will want to 
			// encode it here, something like this:
			// myUrl = URLEncoder.encode(myUrl, "UTF-8");

			String results = doHttpUrlConnectionAction(myUrl);
			//System.out.println(results);
      
			if (SaveFile(file, results, false) == true) 
				System.out.println("output guardado");

		} catch (Exception e) {
			// deal with the exception in your "controller"
		}
	}

	/**
   * Returns the output from the given URL.
   * 
   * I tried to hide some of the ugliness of the exception-handling
   * in this method, and just return a high level Exception from here.
   * Modify this behavior as desired.
   * 
   * @param desiredUrl
   * @return
   * @throws Exception
   */
 
	public boolean SaveFile(String FilePath, String FileContent, boolean CleanFileContent) {
		
		FileWriter file;
		BufferedWriter writer;
       
    	 try {
    		 
    		 file = new FileWriter(FilePath, !CleanFileContent);
    		 writer = new BufferedWriter(file);
    		 writer.write(FileContent, 0, FileContent.length());
           
    		 writer.close();
    		 file.close();
   
    		 return true;
    		 
    	 } catch (IOException ex) {
    		 ex.printStackTrace();
    		 return false;
    	 }
	}
			
	private String doHttpUrlConnectionAction(String desiredUrl) throws Exception {
		
		URL url = null;
		BufferedReader reader = null;
		StringBuilder stringBuilder;

		try {
			
			// create the HttpURLConnection
			url = new URL(desiredUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      
			// just want to do an HTTP GET here
			connection.setRequestMethod("GET");
      
			// uncomment this if you want to write output to this url
			//connection.setDoOutput(true);
			
			// give it 15 seconds to respond
			connection.setReadTimeout(15*1000);
			connection.connect();

			// read the output from the server
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			stringBuilder = new StringBuilder();
	
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				stringBuilder.append(line + "\n");
			}
			
			return stringBuilder.toString();
		}
		
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		finally {
			// close the reader; this can throw an exception too, so
			// wrap it in another try/catch block.
			if (reader != null) {
				
				try {
					reader.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}
}
   