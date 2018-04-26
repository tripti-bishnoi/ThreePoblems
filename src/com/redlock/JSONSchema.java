package com.redlock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONSchema {

	public static void main(String[] args) {
		generateJSONSchema("/Users/triptibishnoi/Desktop/sample.txt");
	}

	public static void generateJSONSchema(String jsonSample) {

		try {

			BufferedReader br = new BufferedReader(new FileReader(jsonSample));
			StringBuilder sample = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sample.append(line);
			}

			JSONObject schema = new JSONObject();
			JSONObject jsonObj = new JSONObject(sample.toString());
			schema = helper(jsonObj, schema);

			// creating json schema file
			try (FileWriter file = new FileWriter("/Users/triptibishnoi/Desktop/output.json")) {
				file.write(schema.toString());
				file.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println(schema);

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public static String createPremitive(Object value) {
		if (value instanceof String)
			return "String";
		if (value instanceof Integer)
			return "Integer";
		if (value instanceof JSONArray)
			return "JSONArray";
		if (value instanceof JSONObject)
			return "JSONObject";
		if (value instanceof Boolean)
			return "Boolean";
		return "number";
	}

	public static JSONObject helper(JSONObject jsonObj, JSONObject schema) throws JSONException {
		Iterator itr = jsonObj.keys();

		while (itr.hasNext()) {
			String key = (String) itr.next();
			String premitive = createPremitive(jsonObj.get(key));
			if (premitive.equals("JSONArray")) {
				JSONArray arr = (JSONArray) jsonObj.get(key);
				JSONArray resultArr = new JSONArray();
				for (int i = 0; i < arr.length(); i++) {
					if (arr.get(i) instanceof JSONObject) {
						JSONObject result = helper(arr.getJSONObject(i), new JSONObject());
						resultArr.put(result);
					}
				}
				schema.put(key, resultArr);
			} else if (premitive.equals("JSONObject")) {
				JSONObject result = helper((JSONObject) jsonObj.get(key), new JSONObject());
				schema.put(key, result);
			} else {
				schema.put(key, premitive);
			}
		}

		return schema;
	}
}
