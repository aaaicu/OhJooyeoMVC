package kr.co.ohjooyeo.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import kr.co.ohjooyeo.service.OrderService;
import kr.co.ohjooyeo.service.VersionService;
import kr.co.ohjooyeo.service.WorshipService;
import kr.co.ohjooyeo.vo.WorshipOrderVO;
import kr.co.ohjooyeo.vo.WorshipVO;

@Controller

public class WorshipController {
	@Autowired
	WorshipService worshipService;

	@Autowired
	OrderService orderService;
	
	@Autowired
	VersionService versionService;

	@RequestMapping(value = "/getWorshipIdList", method = RequestMethod.POST)
	public @ResponseBody List<String> getWorshipIdList(@RequestBody String userId) {

		List<String> result = worshipService.getWorshipIdList(userId);
		return result;
	}

	@RequestMapping(value = "/worship", method = RequestMethod.GET)
	public String worshipForm() {
		return "worship-form";
	}

	@RequestMapping(value = "/add-worship", method = RequestMethod.POST)
	public String addWorship(@ModelAttribute WorshipVO vo,@RequestParam("type") String[] types, @RequestParam("title") String[] titles,
			@RequestParam("detail") String[] details, @RequestParam("presenter") String[] presenters) {
		// 디폴트 버전
		vo.setVersion("aaa");
		// 하드코딩 (유저생성시 변경)
		vo.setUserId("admin");

		// 확인
		System.out.println(vo);
		worshipService.addWorship(vo);
		// 순서 추가
		orderService.setWorshipOrder(vo.getWorshipId(),types, titles, details, presenters);

		return "redirect:worship";
	}

	@RequestMapping(value = "/worship-order", method = RequestMethod.GET)
	public String worshipOrderForm() {
		return "worship-order-form";
	}

	@RequestMapping(value = "/update-worship-order", method = RequestMethod.POST)
	public String updateWorshipOrder(
			@RequestParam(value = "worship_id", required=false, defaultValue="") String worshipId,
			@RequestParam(value = "type", required=false, defaultValue="") String[] types, 
			@RequestParam(value = "title", required=false, defaultValue="") String[] titles,
			@RequestParam(value = "detail", required=false, defaultValue="") String[] details, 
			@RequestParam(value = "presenter", required=false, defaultValue="") String[] presenters
			) {
		
		for(String a : titles) {
			System.out.println(a);
		}
		/* 순서 추가 */
//		orderService.setWorshipOrder(worshipId,types, titles, details, presenters);
		
		/* 버전 관리 부분 */
		String version = versionService.getVersionById(worshipId);
//		versionService.updateVersion(worshipId , versionService.versionUp(version,0));
		
		return "redirect:worship-order";
	}
	
	@RequestMapping(value = "/updateTarget", method = RequestMethod.POST)
	public @ResponseBody String updateTarget(
			@RequestBody Map<String,Object> inputMap
			) throws UnsupportedEncodingException {
		System.out.println("inputMap : "+ inputMap);
		
		/* 파라미터 처리 로직 */
		String values = (String)inputMap.get("values");
		values = "?"+URLDecoder.decode(values, "UTF-8");
	    MultiValueMap<String, String> parameters =
	            UriComponentsBuilder.fromUriString(values).build().getQueryParams();
	    System.out.println(parameters);
	    
	    /* 업데이트 리스트로 변환 */
	    Map <String,List<WorshipOrderVO>> updateWorshipOrderVOList = orderService.analyzeValues(parameters);
	    
		return "";
	}
//	System.out.println(URLDecoder.decode(deleteList, "UTF-8"));
	//request 객체를 생성해서 생성자로 추가 후 조작하는 방법 고민
	
	@RequestMapping(value = "/getWorshipOrderList", method = RequestMethod.POST)
	public @ResponseBody List<WorshipOrderVO> getWorshipOrderList(@RequestBody String worshipId) {
		return orderService.getWorshipOrderList(worshipId);
	}
}
