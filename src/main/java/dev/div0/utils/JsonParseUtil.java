package dev.div0.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.StringReader;

public class JsonParseUtil 
{
	private static JSONParser parser = new JSONParser();
	
	public static JSONObject parse(String data)
	{
		//System.out.println( "start parse "+data+" ...");
		JSONObject json = null;
		StringReader reader = new StringReader(data);

		try 
		{
			json = (JSONObject)parser.parse(reader);
		} 
		catch (Exception e) 
		{
			System.err.println( "parse error "+e.getMessage());
			e.printStackTrace();
		}
		//System.out.println("returning json "+json);
		return json;
	}
	public static JSONArray parseToArray(String data)
	{
		//System.out.println( "start parse "+data+" ...");
		JSONArray json = null;
		StringReader reader = new StringReader(data);
		
		try 
		{
			json = (JSONArray)parser.parse(reader);
		} 
		catch (Exception e) 
		{
			System.err.println( "parse error "+e.getMessage());
			e.printStackTrace();
		}
		//System.out.println("returning json "+json);
		return json;
	}
}
