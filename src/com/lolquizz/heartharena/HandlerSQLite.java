package com.lolquizz.heartharena;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static android.provider.BaseColumns._ID;

/**
 * 
 * 
 * 
 * @author Polo
 *	Class para manejar la base de datos desde la aplicación Anddroid. 
 *	No funcionará bien con Java directamente.
 *
 *
 *
 */
public class HandlerSQLite extends SQLiteOpenHelper  {

	//public HandlerSQLite(Context context) {
		//super(context, "hearthdb.db", null, 1);
	public HandlerSQLite() {
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
//  	I am pretty sure that sqlite3 treats text, char and varchar completely the same. It ignores the number after char(x) or varchar(x).  -> TODO TEXT PUES!
		String query = "CREATE TABLE card ("
						+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
						+"rarity INT NOT NULL,"
						+"_set INT,"
						+"cardNumber INT NOT NULL,"
						+"cardName TEXT NOT NULL,"
						+"name TEXT NOT NULL,"			//		id;
						+"type TEXT NOT NULL,"			//		typeId;  ---> no creadas de hearthpwn
						+"class TEXT,"
						+"cost INT,"
						+"attack INT,"
						+"health INT,"
						+");";
		
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
		
		db.execSQL(query);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		db.execSQL("DROP TABLE IF EXISTS card");
		onCreate(db);
	}
	
	public void onInsert(String table, String row, String value) { // onInsert(card,nameCard,Leeroy Jenkins);
		ContentValues values = new ContentValues();
		values.put(row, value);
		this.getWritableDatabase().insert(table, null, values);
	}

}
