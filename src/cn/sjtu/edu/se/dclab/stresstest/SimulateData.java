package cn.sjtu.edu.se.dclab.stresstest;

import java.util.List;

import org.json.JSONObject;

public interface SimulateData {
	public void create();
	public List<JSONObject> get();
}
