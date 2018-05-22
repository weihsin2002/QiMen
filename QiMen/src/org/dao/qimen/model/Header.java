package org.dao.qimen.model;

import java.util.Date;

import org.dao.calendar.SolarTerms;
import org.dao.calendar.model.FourPillars;
import org.dao.calendar.model.LuniSolarDate;
import org.dao.calendar.model.QiDivision;
import org.dao.calendar.model.QiTerm;
import org.dao.calendar.model.SolarDate;
import org.dao.qimen.config.Configurator;

import com.google.gson.JsonObject;

public class Header {
	SolarDate solarDate;
	LuniSolarDate luniSolarDate = new LuniSolarDate ();
	QiTerm term;
	QiDivision qiDivision;
	FourPillars fourPillars;
	
    String zf_, zy_, zfs_,jg_, ju_;
	
	public Header (Date date) {
		solarDate = new SolarDate (date);
		luniSolarDate = luniSolarDate.SolarToLunar(solarDate);
		
		SolarTerms solarTerms = new SolarTerms();
		fourPillars = new FourPillars(solarDate, luniSolarDate);
		
		term = solarTerms.Term(solarDate);
		qiDivision = new QiDivision (fourPillars);
		
		zf_ = (Configurator.zf()) ? "ZhuangPan":"FeiPan";
		zy_ = (Configurator.rb()) ? "ChaiBu" : "ZhiRun";
	    zfs_ = (Configurator.zf()) ? "XiaoZhiFuTianPan":"XiaoZhiFuDiPan";
	    jg_ = (Configurator.kg()) ? "YinKunYangGen":"YongJiKunGong";
	}
	
	public JsonObject toJson () {
		JsonObject json = new JsonObject();
		
		json.add("YangLi", solarDate.toJson());
		json.add("NongLi", luniSolarDate.toJson());
		json.add("JieQi", term.toJson());
		json.add("Yuan", qiDivision.toJson());
		
		return json;
	}
}
