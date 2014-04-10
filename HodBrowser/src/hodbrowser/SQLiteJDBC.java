package hodbrowser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SQLiteJDBC {
	private Connection c = null;
	private Statement stmt = null;
	private String tableQuery1;
	private String tableQuery2;
	private String insertQuery;
	private String deleteQuery;
	private ResultSet resultQuery;
	private Date date;
	private DateFormat dateFormat;
	private ArrayList<String> result = new ArrayList<String>();
	private int i=0;
	private boolean test;


	protected void injectEntriesSQL(String url, String title, String table) {
		try {
			Class.forName("org.sqlite.JDBC");
			this.date = new Date();
			this.insertQuery = null;
			this.dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			this.c = DriverManager.getConnection("jdbc:sqlite:HodDatabase.db");
			this.stmt = this.c.createStatement();
			if (table.equals("B")) {
				
				this.insertQuery = "INSERT INTO BOOKMARK (TITLE,URL) VALUES ('"+ title + "' ,'" + url + "');";
				this.stmt.execute(insertQuery);
				this.stmt.close();
				this.c.close();
				
			}
			if (table.equals("H")) {
				
				this.insertQuery = "INSERT INTO HISTORY (DATE,TITLE,URL) VALUES ('"+ dateFormat.format(date) + "','" + title + "' ,'" + url + "');";
				this.stmt.execute(insertQuery);
				this.stmt.close();
				this.c.close();
			}
			else{
				
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	protected void deleteHistory(){
		try{
		Class.forName("org.sqlite.JDBC");
		this.deleteQuery = null;
		this.c = DriverManager.getConnection("jdbc:sqlite:HodDatabase.db");
		this.stmt = this.c.createStatement();
		this.deleteQuery = "DELETE FROM HISTORY ;";
		this.stmt.execute(deleteQuery);
		this.stmt.close();
		this.c.close();


	} catch (Exception e) {
		// TODO: handle exception
	}
}

	protected void deleteEntrySQL(String url, String table) {
		try {
			Class.forName("org.sqlite.JDBC");
			this.deleteQuery = null;
			this.c = DriverManager.getConnection("jdbc:sqlite:HodDatabase.db");
			this.stmt = this.c.createStatement();
			if (table.equals("H")) {
				
				this.deleteQuery = "DELETE FROM HISTORY WHERE URL='" + url+ "';";
				this.stmt.execute(deleteQuery);
				System.out.println(deleteQuery);
				this.stmt.close();
				this.c.close();
				
			}
			if (table.equals("B")) {
				
				this.deleteQuery = "DELETE FROM BOOKMARK WHERE URL='" + url+ "';";
				this.stmt.execute(deleteQuery);
				this.stmt.close();
				this.c.close();
				
			}
			else{
				
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	protected ArrayList<String> getEntriesSQL(String table) {
		try {
			Class.forName("org.sqlite.JDBC");
			this.result.clear();
			this.c = DriverManager.getConnection("jdbc:sqlite:HodDatabase.db");
			this.stmt = this.c.createStatement();
			if (table.equals("B")) {
				this.resultQuery = stmt.executeQuery("SELECT * FROM BOOKMARK;");
				while(resultQuery.next()){
					this.result.add(resultQuery.getString("TITLE"));
					this.result.add(resultQuery.getString("URL"));
				}
				resultQuery.close();
			}
			else if (table.equals("H")) {
				
				this.resultQuery = stmt.executeQuery("SELECT * FROM HISTORY ORDER BY DATE;");
				while(resultQuery.next()){
					this.result.add(resultQuery.getString("DATE"));
					this.result.add(resultQuery.getString("TITLE"));
					this.result.add(resultQuery.getString("URL"));
					
				}
				this.resultQuery.close();
				
			}
			else if(table.equals("S")){
				this.resultQuery = stmt.executeQuery("SELECT * FROM SETTINGS;");
				while(resultQuery.next()){
					this.result.add(resultQuery.getString("HOME"));
					this.result.add(Boolean.toString(resultQuery.getBoolean("BLIND")));
					this.result.add(Boolean.toString(resultQuery.getBoolean("CONTRAST")));
				}
			}
			else {
			}
			
			return result;

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	private void creationTable() {
//TODO: créer implémentation table Settings
		try {
			Class.forName("org.sqlite.JDBC");
			this.tableQuery1 = null;
			this.tableQuery2 = null;
			this.c = DriverManager.getConnection("jdbc:sqlite:HodDatabase.db");
			this.stmt = this.c.createStatement();
			// Creation of the Favorite table
			this.tableQuery1 = "CREATE TABLE FAVORITE "
					+ "(ID INT PRIMARY KEY     AUTOINCREMENT,"
					+ " URL            TEXT    NOT NULL,"
					+ " TITLE          TEXT    NOT NULL)";
			// Creation of the favorite table
			this.tableQuery2 = "CREATE TABLE HISTORY "
					+ "(ID INT PRIMARY KEY     AUTOINCREMENT,"
					+ " URL            TEXT    NOT NULL,"
					+ " TITLE          TEXT    NOT NULL,"
					+ " DATE		   DATE    NOT NULL	)";
			this.stmt.executeUpdate(this.tableQuery1);
			this.stmt.executeUpdate(this.tableQuery2);
			this.stmt.close();
			this.c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
//	CREATE TABLE "SETTINGS" ("ID" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , "HOME" TEXT NOT NULL , "BLIND" BOOL NOT NULL , "CONTRAST" BOOL NOT NULL )
}



