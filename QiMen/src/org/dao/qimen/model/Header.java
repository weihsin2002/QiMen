package org.dao.qimen.model;

import java.util.Date;

import org.dao.calendar.DaoCalendar;
import org.dao.qimen.config.Configurator;

import com.google.gson.JsonObject;

public class Header {
	
	private DaoCalendar daoCalendar;
    private String zf_, zy_, zfs_,jg_, ju_;
	
	public Header (Date date) {
		
		daoCalendar = new DaoCalendar(date);
				
		zf_ = (Configurator.zf()) ? "ZhuangPan":"FeiPan";
		zy_ = (Configurator.rb()) ? "ChaiBu" : "ZhiRun";
	    zfs_ = (Configurator.zf()) ? "XiaoZhiFuTianPan":"XiaoZhiFuDiPan";
	    jg_ = (Configurator.kg()) ? "YinKunYangGen":"YongJiKunGong";
	}
		
	public DaoCalendar daoCalendar() {
		return daoCalendar;
	}

	public JsonObject toJson () {
		JsonObject json = new JsonObject();
		
		if (daoCalendar.trueSolarDate() != null) {
			json.add("YangLi", daoCalendar.trueSolarDate().toJson());
			json.add("NongLi", daoCalendar.trueLuniSolarDate().toJson());
			json.add("JieQi", daoCalendar.trueQiTerm().toJson());
			json.add("Yuan", daoCalendar.trueQiDivision().toJson());			
			json.add("SiZhu", daoCalendar.trueFourPillars().toJson());
		} else {
			json.add("YangLi", daoCalendar.solarDate().toJson());
			json.add("NongLi", daoCalendar.luniSolarDate().toJson());
			json.add("JieQi", daoCalendar.qiTerm().toJson());
			json.add("Yuan", daoCalendar.qiDivision().toJson());
			json.add("SiZhu", daoCalendar.fourPillars().toJson());
		}
		
		return json;
	}
}
