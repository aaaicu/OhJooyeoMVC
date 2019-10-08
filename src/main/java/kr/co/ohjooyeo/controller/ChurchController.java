package kr.co.ohjooyeo.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ohjooyeo.service.ChurchService;
import kr.co.ohjooyeo.service.OrderService;

@RestController
//@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ChurchController {
	@Autowired
	OrderService orderService;
	
	@Autowired
	ChurchService churchService;
	
	private static final Logger logger = LoggerFactory.getLogger(ChurchController.class); 
	@RequestMapping(value="/churchInfo", method = RequestMethod.POST)
	public boolean churchInfo(@RequestBody Map<String,String> churchInfoMap){
		logger.debug(churchInfoMap.toString());
//		return false;
		return churchService.regChurchInfo(churchInfoMap);
		
	}
	
	@RequestMapping(value = "/launch/{date}", method = RequestMethod.GET)
	public @ResponseBody Map<String,String> getLaunchPhrase(@PathVariable String date ) {
		/*사용자 활성화시 변수로 처리*/
		String userId = "admin";
		
		return orderService.getLaunchPhrase(userId, date);
	}
}
