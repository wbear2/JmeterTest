package cn.sjtu.edu.se.dclab.stresstest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

public class KpdFactory implements SimulateData{
	
	private int amount = 20;
	private int typeNum = 10;
	
	private List<Kpd> kpds = new ArrayList<Kpd>();

	public KpdFactory(int amount,int typeNum){
		this.amount = amount;
		this.typeNum = typeNum;
	}
	
	@Override
	public void create() {
		Random rand = new Random();
		for(int t = 0;t < amount;t++){
			int type = rand.nextInt(typeNum) + 1;
			
			int year = rand.nextInt(10) + 2004;
			int month = rand.nextInt(12) + 1;
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.MONTH, month);
			Date startTime = calendar.getTime();
			
			int length = (2013 - year)*12 + (12- month);
			int[] records = new int[length];
			for(int i = 0;i < length;i++){
				int j = 0;
				for(int k = 0;k < 3;k++)
					if(Math.random() < 0.05)
						j++;
				records[i] = j;
			}
			
			Kpd kpd = new Kpd(type,startTime,records);
			kpds.add(kpd);
		}		
	}
	
	public List<JSONObject> get(){
		create();
		
		ArrayList<JSONObject> jsonObjs = new ArrayList<JSONObject>();
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		for(Kpd kpd : kpds){
			try {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("type", kpd.getType());
				jsonObj.put("start_time", df.format(kpd.getStartTime()));
				StringBuffer sb = new StringBuffer();
				int[] records = kpd.getRecords();
				for(int i : records)
					sb.append("," + i);
				if(sb.length() > 0)
					jsonObj.put("repair_record", sb.substring(1));
				else
					jsonObj.put("repair_record", "");
				jsonObjs.add(jsonObj);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return jsonObjs;
	}
	
	
	public List<Kpd> getList(){
		create();
		return kpds;
	}
/*
	public static void main(String[] args){		
		KpdFactory kf = new KpdFactory(20,10);
		List<JSONObject> jsonObjs = kf.get();
		for(JSONObject jsonObj : jsonObjs)
			System.out.println(jsonObj.toString());
	}
	*/
}
