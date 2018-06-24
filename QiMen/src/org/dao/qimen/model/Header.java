package org.dao.qimen.model;

import java.util.Date;
import org.dao.qimen.config.Configurator;

import org.dao.calendar.DaoCalendar;
import org.dao.calendar.model.Division;
import org.dao.calendar.model.JieQi;
import org.dao.calendar.model.QiDivision;
import org.dao.calendar.model.QiTerm;
import org.dao.calendar.model.SolarDate;
import org.dao.qimen.config.Configurator;

import com.google.gson.JsonObject;

public class Header {
	
	private DaoCalendar daoCalendar;
	private SolarDate solarDate;
	private QiDivision qiDivision;
	private QiTerm qiTerm;
	private Structure structure;
	
    private String zf_, zy_, zfs_,jg_, ju_;
	
	public Header (Date date) {
		
		this.daoCalendar = new DaoCalendar(date);
		
		Configurator c = new Configurator();
		
		this.solarDate = new SolarDate();
		
		if (this.daoCalendar.trueSolarDate() != null) {
			this.solarDate = daoCalendar.trueSolarDate();
			this.qiDivision = daoCalendar.trueQiDivision();
			this.qiTerm = daoCalendar.trueQiTerm();
		} else {
			this.solarDate = daoCalendar.solarDate();
			this.qiDivision = daoCalendar.qiDivision();
			this.qiTerm = daoCalendar.qiTerm();
		}
		
		this.structure = new Structure(JieQi.fromJieQi(this.qiTerm.currentTerm()), Division.fromYuan(this.qiDivision.division()));
						
		zf_ = (Configurator.zf()) ? "ZhuangPan":"FeiPan";
		zy_ = (Configurator.rb()) ? "ChaiBu" : "ZhiRun";
	    zfs_ = (Configurator.zf()) ? "XiaoZhiFuTianPan":"XiaoZhiFuDiPan";
	    jg_ = (Configurator.kg()) ? "YinKunYangGen":"YongJiKunGong";
	}
		
	public DaoCalendar daoCalendar() {
		return daoCalendar;
	}
	
	public SolarDate solarDate () {
		return this.solarDate;
	}
	
	public Structure structure () {
		return this.structure;
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
		
		json.add("ju", this.structure.toJson());
		
		return json;
	}
	
	class Structure {
		private int ju;
	    private boolean flow; // true -- yang, false -- yin
	    private int order;
	    
	    public Structure (int jie, int yuan) {
	    	this.ju = Configurator.yydun()[jie][yuan];
	    	this.flow = this.ju > 0;
	    	this.order = Math.abs(this.ju);
	    }
	    
		public boolean flow() {
			return this.flow;
		}

		public int order() {
			return this.order;
		}
		
		public int ju() {
			return this.ju;
		}
		
		public JsonObject toJson () {
			JsonObject json = new JsonObject();
			
			json.addProperty("ju", this.ju);
			json.addProperty("flow", this.flow);
			json.addProperty("order", this.order);
			
			return json;
		}
	}
}