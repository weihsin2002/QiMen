package org.dao.qimen.utils;

import org.boc.db.SiZhu;
import org.boc.db.YiJing;
import org.boc.db.qm.QiMen;

public class Utils {
	  /**
	   * 由用神类型得到描述、落宫、干支信息
	   * @param type 要判断大格局的用神, 4为年年柱，3为月柱，2为日柱，1为时柱,0则取gan与zi的值
	   * @param gan
	   * @param zi
	   * @return
	   */
		public String[] getYShenInfo(int type, int gan ,int zi) {
			int gong = 0; 		//为用神宫
			String yshen = type==YSHENHOUR?"时干为用":type==YSHENDAY?"日干为用":type==YSHENMONTH?"月干为用":type==YSHENYEAR?"年干为用":"指定天盘干["+YiJing.TIANGANNAME[gan]+"]为用";
			if(type==YSHENHOUR) {
				gong = getTianGong(SiZhu.sg, SiZhu.sz); //得到时干宫
				gan = SiZhu.sg; zi = SiZhu.sz;
			}
			else if(type==YSHENDAY) {
				gong = getTianGong(SiZhu.rg, SiZhu.rz); //得到日干宫
				gan = SiZhu.rg; zi = SiZhu.rz;
			}
			else if(type==YSHENMONTH) {
				gong = getTianGong(SiZhu.yg, SiZhu.yz); //得到月干宫
				gan = SiZhu.yg; zi = SiZhu.yz;
			}
			else if(type==YSHENYEAR) {
				gong = getTianGong(SiZhu.ng, SiZhu.nz); //得到年干宫
				gan = SiZhu.ng; zi = SiZhu.nz;
			}
			else if(gan!=0 && zi!=0)
				gong = getTianGong(gan,zi); //得到指定的干与支之落宫
		//如果指定了八门九星八神三奇六仪为用神，则日干宫为用神
			else {			
				int[] ys = {0,0,0,0,0,1,2,3,4,6,7,8,9};  //目前对应关系: 5～12为八门；13~21为九宫
				if(type>=5 && type<=12) {  //八门为用神
					yshen = QiMen.bm1[ys[type]]+"门为用";
					gong = this.getMenGong(ys[type]); 
					int[] typjy = this.getTpjy(gong);
					gan = typjy[0]; zi = 0;
				}else if(type>=13 && type<=21) {
					gong = type-12;  //九宫为用神
					yshen = "第"+QiMen.BIGNUM[gong]+"宫为用";
					int[] typjy = this.getTpjy(gong);
					gan = typjy[0]; zi = 0;
				}
			}
			
			return new String[]{yshen, gong+"", gan+"", zi+""};
		}


}
