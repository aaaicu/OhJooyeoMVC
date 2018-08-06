package kr.co.ohjooyeo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ohjooyeo.service.OrderService;
import kr.co.ohjooyeo.service.VersionService;

@RestController
public class MainApiController {
	@Autowired
	OrderService orderService;
	
	@Autowired
	VersionService versionService;


	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public Map<String,Object> getOrder(@RequestParam String date) {
		return orderService.getOrderByDate(date);
	}
	
	@RequestMapping(value = "/launch/{date}", method = RequestMethod.GET)
	public Map<String,String> getLaunchPhrase(@PathVariable String date ) {
		/*사용자 활성화시 변수로 처리*/
		String userId = "admin";
		
		return orderService.getLaunchPhrase(userId, date);
	}
	
	@RequestMapping(value = "/date/{date}/check/version/{version}", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> compareVersion(
			@PathVariable String date,
			@PathVariable String version
			) {
		return versionService.compareVersion(date, version);
	}
}
