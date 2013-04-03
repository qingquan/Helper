package yl.demo.pathHelper.db.parser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;


public abstract class BaseParser {
	protected JSONArray wholeArray;
	protected int length;
	
	public BaseParser(String jsonFilePath) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(jsonFilePath), "utf-8"));
			String jsonString = "";
			String line = null;
			while ((line = reader.readLine()) != null) {
				jsonString += line + "\n";
			}
			reader.close();
			wholeArray = new JSONArray(jsonString);
			length = wholeArray.length();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public abstract void parserAndInsert();
}
