package urconnection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CopyPasteDB {

	public static void main(String[] args) throws ClassNotFoundException {
		
		
		String cardName = "manual-data-link\" href=\"/cards/(.*?)\" d";
		String cost = "<td class=\"col-cost\">(.*?)<";
		String type = "<td class=\"col-type\">(.*?)</td>";
		String rarity = "<a class=\"rarity-(.*?) s";
		String _set = " set-(.*?) manual-data-link";
		String attack = "<td class=\"col-attack\">(.*?)<";
		String health = "<td class=\"col-health\">(.*?)<";
		String _class = "<span class=\"class-(.*?)\">";
		String cardNumber = "manual-data-link\" href=\"/cards/(.*?)-(.*?)\" d";
		String name = "data-type-id=\".*?\" >(.*?)</a>";
		
//		Connect to each web and save the html output in a file
		String [] url = {"http://www.hearthpwn.com/cards?display=1", "http://www.hearthpwn.com/cards?display=1&page=2",
				"http://www.hearthpwn.com/cards?display=1&page=3","http://www.hearthpwn.com/cards?display=1&page=4",
				"http://www.hearthpwn.com/cards?display=1&page=5","http://www.hearthpwn.com/cards?display=1&page=6"};
		String file = "resultado.txt";
		
		for(int i=0; i<url.length;i++)
			new JavaHttpUrlConnectionReader(url[i], file);

//		load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:hearthdb.db");
			Statement statement = connection.createStatement();

		/////////////// Database connection opened /////////////////
			
			String result = ReadFile(file);

			onCreate(statement);

			for (int i=0; i<link(result,cardName).length;i++ )
				onInsert(statement, "card", "cardName","'"+link(result,cardName)[i]+"'");
			for (int i=0; i<link(result,cost).length;i++ )
				onUpdate(statement, "card", "cost", "'"+link(result,cost)[i]+"'", i+1);
			for (int i=0; i<link(result,type).length;i++ )
				onUpdate(statement, "card", "type", "'"+link(result,type)[i]+"'", i+1);
			for (int i=0; i<link(result,rarity).length;i++ )
				onUpdate(statement, "card", "rarity", "'"+link(result,rarity)[i]+"'", i+1);
			for (int i=0; i<link(result,_set).length;i++ )
				onUpdate(statement, "card", "_set", "'"+link(result,_set)[i]+"'", i+1);
			for (int i=0; i<link(result,attack).length;i++ )
				onUpdate(statement, "card", "attack", "'"+link(result,attack)[i]+"'", i+1);
			for (int i=0; i<link(result,health).length;i++ )
				onUpdate(statement, "card", "health", "'"+link(result,health)[i]+"'", i+1);
			for (int i=0; i<link(result,_class).length;i++ )
				onUpdate(statement, "card", "class", "'"+link(result,_class)[i]+"'", i+1);
			for (int i=0; i<link(result,cardNumber).length;i++ )
				onUpdate(statement, "card", "cardNumber", "'"+link(result,cardNumber)[i]+"'", i+1);
			for (int i=0; i<link(result,name).length;i++ )
				onUpdate(statement, "card", "name", "'"+link(result,name)[i]+"'", i+1);
			
			onSelect(statement);

		/////////////// Database connection closed /////////////////
			
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		finally {
			try {
				if(connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void onCreate(Statement statement) throws SQLException {
		
//  	I am pretty sure that sqlite3 treats text, char and varchar completely the same. It ignores the number after char(x) or varchar(x).  -> TODO TEXT PUES!
		String query = "CREATE TABLE card ("
						+"_id INTEGER PRIMARY KEY AUTOINCREMENT,"
						+"rarity INT ,"  //////////////////////////// BORRAR LOS NOT NULL O ESTO NO VA NI PA TRAS //
						+"_set INT,"
						+"cardNumber INT ,"
						+"cardName TEXT ,"
						+"name TEXT ,"			//		id;
						+"type TEXT ,"			//		typeId;  ---> no creadas de hearthpwn
						+"class TEXT,"
						+"cost INT,"
						+"attack INT,"
						+"health INT"
						+")";
//		
//		CREATE TABLE card (
//				_id 				INTEGER		PRIMARY KEY 	AUTOINCREMENT,
//				rarity 				INT 		NOT NULL,
//				_set 				INT,
//				cardNumber 			INT 		NOT NULL,
//				cardName 			TEXT 		NOT NULL,
//				name 				TEXT 		NOT NULL,
//				type 				TEXT 		NOT NULL,			
//				class 				TEXT,
//				cost 				INT,						
//				attack	 			INT,
//				health				INT
//				)
//				;
		
			statement.setQueryTimeout(30);
			statement.executeUpdate("DROP TABLE IF EXISTS card");
			statement.executeUpdate(query);
	}
	
//	onInsert(card,nameCard,Leeroy Jenkins);
	public static void onInsert(Statement statement, String table, String row, String value) throws SQLException { 
		
//		statement.executeUpdate("INSERT INTO card (cardName, cardNumber) VALUES ('Leeroy Jenkins', 173)");
		statement.executeUpdate("INSERT INTO "+table+" ("+row+") VALUES ("+value+")");
//		statement.executeUpdate("UPDATE "+table+" SET "+row+" VALUES = "+value+"");
	}
	
	public static void onUpdate(Statement statement, String table, String row, String value, int _id) throws SQLException {
		
		statement.executeUpdate("UPDATE "+table+" SET "+row+" = "+value+" WHERE _id = "+_id+"");
	}
	
	public static void onSelect(Statement statement) throws SQLException {
		
		String [] atributes = {"ID\t","Rarity\t","Set\t","Nº Card\t","Card\t","Name\t","Type\t","Class\t","Cost\t","Attack\t","Health\t"};
		ResultSet rs = statement.executeQuery("SELECT * FROM card");
		
		while(rs.next()) {
//		read the result set
			System.out.println("------------");
			for(int i=1; i<12;i++) {								// i<12 as TABLE has 11 atributes
				System.out.println(atributes[i-1]+" = " + rs.getString(i));
			}
		}
	}
	
	public static String ReadFile(String FilePath) {
		   
	      File file = null;
	      FileReader fr = null;
	      BufferedReader br = null;
	      String line = new String();
	      String message = new String();
	      
	      try {
	      file = new File (FilePath);
	      fr = new FileReader (file);
	      br = new BufferedReader(fr);
	      while ((line = br.readLine()) != null) {
//	          buffer.append(line);
	          message += line;
	      }
	      br.close();
	      fr.close();
	      }
	      catch (Exception e) {
	      }
	      return message;
	}
	
	static String [] link(String file, String pattern) {
		
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(file);
		int i=0;
		String [] result = new String[526]; 	// 526 cards I have found on Heartpwn
		
		while (m.find()) {
			result[i] = m.group(1);
			i++;
		}
		if(result[0]!= null)
			return result;
		else
			return null;
	}
}

