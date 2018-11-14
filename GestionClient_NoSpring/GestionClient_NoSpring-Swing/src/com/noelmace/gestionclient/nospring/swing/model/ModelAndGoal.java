package com.noelmace.gestionclient.nospring.swing.model;

import java.util.HashMap;

import com.noelmace.gestionclient.nospring.swing.ctrl.ActionId;

public class ModelAndGoal {
	
	private ActionId goal;
	
	private HashMap<String, Object> dataMap = new HashMap<String, Object>();

	public ModelAndGoal(ActionId createClient) {
		this.goal = createClient;
	}
	
	public ModelAndGoal() {
	}

	public void addData(String key, Object value){
		this.dataMap.put(key, value);
	}
	
	public Object getObject(String key){
		return this.dataMap.get(key);
	}
	
	public String getString(String key){
		Object obj = this.dataMap.get(key);
		if (obj instanceof String) {
			return (String) obj;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public int getInt(String key){
		Object obj = this.dataMap.get(key);
		if(obj instanceof Number){
			return ((Number) obj).intValue();
		} else if (obj instanceof String) {
			return Integer.parseInt((String) obj);
		} else {
			throw new NumberFormatException();
		}
	}
	
	public long getLong(String key){
		Object obj = this.dataMap.get(key);
		if(obj instanceof Number){
			return ((Number) obj).longValue();
		} else if (obj instanceof String) {
			return Long.parseLong((String) obj);
		} else {
			throw new NumberFormatException();
		}
	}

	public ActionId getGoal() {
		return goal;
	}

	public Double getDouble(String key) {
		Object obj = this.dataMap.get(key);
		if(obj instanceof Number){
			return ((Number) obj).doubleValue();
		} else if (obj instanceof String) {
			return Double.parseDouble((String) obj);
		} else {
			throw new NumberFormatException();
		}
	}
}
