package Java_code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Spark_Interface {

	public static ArrayList<ArrayList<String>> ParseJson(String JsonStr) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		try {
			JSONObject json = new JSONObject(JsonStr);
			int colNum = Integer.parseInt(json.getString("colNum"));
			int rowNum = Integer.parseInt(json.getString("rowNum"));
			JSONArray headerJ = json.getJSONArray("header");
			JSONArray resultJ = json.getJSONArray("result");
			ArrayList<String> headerA = new ArrayList<String>();
			for (int i = 0; i < colNum; ++i)
				headerA.add(headerJ.get(i).toString());
			result.add(headerA);
			for (int i = 0; i < rowNum - 1; ++i) {
				ArrayList<String> resultA = new ArrayList<String>();
				for (int j = 0; j < colNum; ++j)
					resultA.add(resultJ.getJSONArray(i).get(j).toString());
				result.add(resultA);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * Sending HTTP request(body) using POST method to Spark server and return
	 * the body of response.
	 */
	public static String HTTP_POST(String url, String request) {
		String result = "";

		URL httpUrl = null;
		try {
			httpUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		// build the connection
		URLConnection connection = null;
		try {
			connection = httpUrl.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		connection.setRequestProperty("accept", "*/*");
		connection.setRequestProperty("connection", "Keep-Alive");
		connection.setRequestProperty("user-agent", "CS-211@UCLA");
		connection.setRequestProperty("content-type", "application/SQL-lines");
		connection.setRequestProperty("Content-Lenth",
				String.valueOf(request.length()));
		connection.setDoOutput(true);
		connection.setDoInput(true);
		OutputStreamWriter outputStream = null;
		try {
			outputStream = new OutputStreamWriter(connection.getOutputStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			outputStream.write(request);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String line;
		try {
			while ((line = bufferedReader.readLine()) != null)
				result += line;
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}
}
