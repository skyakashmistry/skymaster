package com.vecna.dbDiff.tools;

import java.util.Scanner;

import org.apache.commons.lang.StringUtils;

public class MainClass extends ToolsCommon {

	private static final String DEFAULT_DB_FILENAME = "myDb.ser";
	private static final String DEFAULT_JDBC_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	
	public static void main(String[] args) throws Exception {
		
		// Make reference of first database
		
		StringBuilder url1, username1, pw1 , url2, username2 , pw2;

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please enter the url for first database ...");
		url1 = new StringBuilder("jdbc:" + sc.nextLine());
		System.out.println("Please enter the Username for first database ...");
		username1 = new StringBuilder( sc.nextLine());
		System.out.println("Please enter the password for first database ...");
		pw1 =  new StringBuilder( sc.nextLine());
		
		System.out.println("Please enter the url for second database ...");
		url2 = new StringBuilder( "jdbc:" + sc.nextLine());
		System.out.println("Please enter the Username for second database ...");
		username2 = new StringBuilder( sc.nextLine());
		System.out.println("Please enter the password for second database ...");
		pw2 =  new StringBuilder( sc.nextLine());
		
		StringBuilder referenceDbFile = new StringBuilder(DEFAULT_DB_FILENAME);
		StringBuilder jdbcDriver = new StringBuilder(DEFAULT_JDBC_DRIVER_CLASS);

		parseArgs(args, url1, username1, pw1, referenceDbFile, jdbcDriver);

		if (StringUtils.isBlank(url1.toString())) {
			System.out.println(
					"[ERROR] JDBC url required, eg \"jdbc:postgresql://localhost/sqm\".  Use --url or -l param");
		} else if (StringUtils.isBlank(username1.toString())) {
			System.out.println("[ERROR] JDBC username required, eg \"admin\".  Use --username or -u param");
		} else {
			MakeReferenceDatabase me = new MakeReferenceDatabase();
			me.run(url1.toString(), username1.toString(), pw1.toString(), referenceDbFile.toString(),
					jdbcDriver.toString());
		}
		
		/*
		 *  Compare with second database ...
		 */
		 parseArgs(args,url1,username2 ,pw2 , referenceDbFile,jdbcDriver );

		 if (StringUtils.isBlank(url2.toString())) {
		      System.out.println("[ERROR] JDBC url required, eg \"jdbc:postgresql://localhost/sqm\".  Use --url or -l param");
		    } else if (StringUtils.isBlank(username2.toString())) {
		      System.out.println("[ERROR] JDBC username required, eg \"admin\".  Use --username or -u param");
		    } else {
		      CompareDatabase me = new CompareDatabase();
		      me.run(url2.toString(), username2.toString(), pw2.toString(), referenceDbFile.toString(), jdbcDriver.toString());
		    }
		 
	}

}
