package cn.ziran.xjson.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JsonController {

	// 执行返回json
	@ResponseBody
	@RequestMapping(value = { "/json", "jsonp" }, method = { RequestMethod.GET, RequestMethod.POST })
	public List<TT> jsonResult() {
		List<TT> list = new ArrayList<TT>();
		TT t = null;
		Map<String,Object> map = null;
		for (int i = 0; i < 10; i++) {
			t = new TT();
			t.setId(i);
			t.setName("name" + i);
			t.setSex(i % 2 == 0 ? true : false);
			t.setHeight(i % 3 * 2);

			map = new HashMap<String,Object>();
			map.put("map1", "value1");
			map.put("map2", "value2");
			t.setMap(map);
			list.add(t);
		}
		return list;
	}
}
