package kr.co.ohjooyeo.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import kr.co.ohjooyeo.service.AdvertisementService;
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

	@Autowired
	AdvertisementService adService;
	
	@RequestMapping(value = "/getWorshipIdList", method = RequestMethod.POST)
	public @ResponseBody List<String> getWorshipIdList(@RequestBody String userId) {

		List<String> result = worshipService.getWorshipIdList(userId);
		return result;
	}

	@RequestMapping(value = "/worship", method = RequestMethod.GET)
	public String worshipForm() {
		return "worship-form";
	}

	@RequestMapping(value = "/worship-delete", method = RequestMethod.GET)
	public String worshipDelete(Model model) {
		//userId 하드코딩
		model.addAttribute("worshipIdList", worshipService.getWorshipIdList("admin"));
		return "worship-delete";
	}
	
	@RequestMapping(value = "/add-worship", method = RequestMethod.POST)
	public String addWorship(@ModelAttribute WorshipVO vo,@RequestParam("type") String[] types, @RequestParam("title") String[] titles,
			@RequestParam("detail") String[] details, @RequestParam("presenter") String[] presenters,
			@RequestParam("adOrder") String[] adOrders, @RequestParam("adTitle") String[] adTitles,
			@RequestParam("adContent") String[] adContents) {
		// 디폴트 버전
		vo.setVersion("aaa");
		// 하드코딩 (유저생성시 변경)
		vo.setUserId("admin");

		// 확인
		System.out.println(vo);
		for(String a : adOrders) {			
			System.out.println(a);
		}
		for(String a : adTitles) {			
			System.out.println(a);
		}
		for(String a : adContents) {			
			System.out.println(a);
		}
		worshipService.addWorship(vo);
		// 순서 추가
		orderService.setWorshipOrder(vo.getWorshipId(),types, titles, details, presenters);
		adService.setWorshipAd(vo.getWorshipId(), adTitles, adContents);

		return "redirect:worship";
	}

	@RequestMapping(value = "/worship-update-temp", method = RequestMethod.GET)
	public String worshipOrderFormTemp() {
//		return "worship-order-form";
		return "worship-update";
	}
	
	@RequestMapping(value = "/worship-update", method = RequestMethod.GET)
	public String worshipOrderForm() {
		return "worship-order-form";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateTarget", method = RequestMethod.POST)
	public @ResponseBody String updateTarget(
			@RequestBody Map<String,Object> inputMap
			) throws UnsupportedEncodingException {
		System.out.println("inputMap : "+ inputMap);
		
		/* 파라미터 처리 로직 */
		String values = (String)inputMap.get("values");
		values = "?"+URLDecoder.decode(values, "UTF-8");
	    MultiValueMap<String, String> parameters = UriComponentsBuilder.fromUriString(values).build().getQueryParams();
	    System.out.println(parameters);
	    
	    /* 파라미터 가공  */
//	    System.out.println(updateWorshipOrderVOList);
	    String worshipId = parameters.get("worship_id").get(0);
	    int chk = 0;
	    if(parameters.get("orderId")!=null) {
	    	
	    Map <String,List<WorshipOrderVO>> updateWorshipOrderVOList = orderService.analyzeValues(parameters);
	    
	    /* add update delete 처리하는 method */
	    chk += orderService.add(updateWorshipOrderVOList.get("addList")); 
	    chk += orderService.update(updateWorshipOrderVOList.get("updateList"));
	    }
	    chk += orderService.delete(worshipId,(List<String>)inputMap.get("deleteList"));
	    
		if (chk > 0) {			
			/* 버전 관리 부분 (버전증가) */
			String version = versionService.getVersionById(worshipId);
			versionService.updateVersion(worshipId , versionService.versionUp(version,0));
		}
	    
		return "";
	}
	
	@RequestMapping(value = "/getWorshipOrderList", method = RequestMethod.POST)
	public @ResponseBody List<WorshipOrderVO> getWorshipOrderList(@RequestBody String worshipId) {
		return orderService.getWorshipOrderList(worshipId);
	}
}
