package cn.sjtu.edu.se.dclab.stresstest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

public class RequestOperation {

	public void doPost(String urlStr){
		try {
			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			
			Random rand = new Random();
			JSONObject jsonObj = new JSONObject();
			int year = rand.nextInt(10) + 1990;
			String version = "" + (char)('A' + rand.nextInt(26));
			try {
				jsonObj.put("ptime", year);
				jsonObj.put("version", version);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			System.out.println(Thread.currentThread().getName() 
					+ " put data: ptime= " + year + ", version = " + version);
			
			out.write(jsonObj.toString());
			out.flush();
			
			BufferedReader in = new BufferedReader(new InputStreamReader
					(connection.getInputStream()));
			while(in.readLine() != null);
			
			out.close();
			in.close();
			connection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
