package cn.ziran.xutil;

import com.ziran.xtest.ICat;

public class Cat implements ICat {

	@Override
	public String cry(String x) {
		return ">>>>> 小猫 miao miao ...";
	}

}
