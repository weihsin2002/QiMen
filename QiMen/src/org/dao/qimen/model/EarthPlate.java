package org.dao.qimen.model;

import com.google.gson.JsonObject;

public class EarthPlate {
	private Palace KanPalace;
	private Palace GenPalace;
	private Palace ZhenPalace;
	private Palace XunPalace;
	private Palace LiPalace;
	private Palace KunPalace;
	private Palace DuiPalace;
	private Palace QianPalace;
	private Palace ZhongPalace;
	
	EarthPlate (int ju) {
		this.KanPalace = new Palace(1, ju);
		this.GenPalace = new Palace(8, ju);
		this.ZhenPalace = new Palace(3, ju);
		this.XunPalace = new Palace(4, ju);
		this.LiPalace = new Palace(9, ju);
		this.KunPalace = new Palace(2, ju);
		this.DuiPalace = new Palace (7, ju);
		this.QianPalace = new Palace (6, ju);
		this.ZhongPalace = new Palace(5, ju);
	}

	public Palace kanPalace() {
		return KanPalace;
	}

	public Palace genPalace() {
		return GenPalace;
	}

	public Palace zhenPalace() {
		return ZhenPalace;
	}

	public Palace xunPalace() {
		return XunPalace;
	}

	public Palace liPalace() {
		return LiPalace;
	}

	public Palace kunPalace() {
		return KunPalace;
	}

	public Palace duiPalace() {
		return DuiPalace;
	}

	public Palace qianPalace() {
		return QianPalace;
	}

	public Palace zhongPalace() {
		return ZhongPalace;
	}
	
	public JsonObject toJson () {
		JsonObject json = new JsonObject();
		
		json.add("KanPalace", this.KanPalace.toJson());
		json.add("GenPalace", this.GenPalace.toJson());
		json.add("ZhenPalace", this.ZhenPalace.toJson());
		json.add("XunPalace", this.XunPalace.toJson());
		json.add("LiPalace", this.LiPalace.toJson());
		json.add("KunPalace", this.KunPalace.toJson());
		json.add("DuiPalace", this.DuiPalace.toJson());
		json.add("QianPalace", this.QianPalace.toJson());
		json.add("ZhongPalace", this.ZhongPalace.toJson());
		
		return json;
	}
}