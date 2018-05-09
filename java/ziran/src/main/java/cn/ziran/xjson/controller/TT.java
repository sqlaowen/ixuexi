package cn.ziran.xjson.controller;

import java.util.Map;

public class TT {
	private int id;
	private String name;
	private boolean sex;
	private double height;
	private Map<String,Object> map;

	public Map<String,Object> getMap() {
		return map;
	}

	public void setMap(Map<String,Object> map) {
		this.map = map;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

}
