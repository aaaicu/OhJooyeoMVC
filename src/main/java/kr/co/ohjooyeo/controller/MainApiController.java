package kr.co.ohjooyeo.controller;

import java.util.List;
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
import kr.co.ohjooyeo.service.WorshipService;


//메인 화면(주보관련 조회정보) 
@RestController
public class MainApiController {
	@Autowired
	OrderService orderService;
	
	@Autowired
	VersionService versionService;
	
	@Autowired
	WorshipService worshipService;
	
	@RequestMapping(value = "/worship-list", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, String>> worshipList(){
		System.out.println(worshipService.getWorshipList());
		return worshipService.getWorshipList();
	}

	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> getOrder(@RequestParam String id) {
		return orderService.getOrderById(id);
	}
	
	@RequestMapping(value = "/launch/{date}", method = RequestMethod.GET)
	public @ResponseBody Map<String,String> getLaunchPhrase(@PathVariable String date ) {
		/*사용자 활성화시 변수로 처리*/
		String userId = "admin";
		
		return orderService.getLaunchPhrase(userId, date);
	}
	
	@RequestMapping(value = "/worship-id/{id}/check/version/{version}", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> compareVersion(
			@PathVariable String id,
			@PathVariable String version
			) {
		return versionService.compareVersion(id, version);
	}
	
}
