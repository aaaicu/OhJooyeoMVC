package kr.co.ohjooyeo.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.ohjooyeo.service.AdvertisementService;
import kr.co.ohjooyeo.service.OrderService;
import kr.co.ohjooyeo.service.VersionService;
import kr.co.ohjooyeo.service.WorshipService;
import kr.co.ohjooyeo.util.WorshipIdGenerator;
import kr.co.ohjooyeo.vo.WorshipAdVO;
import kr.co.ohjooyeo.vo.WorshipOrderVO;
import kr.co.ohjooyeo.vo.WorshipVO;


@Controller
//@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class WorshipController {
	
	private static final Logger logger = LoggerFactory.getLogger(WorshipController.class);
	
	@Autowired
	WorshipService worshipService;

	@Autowired
	OrderService orderService;

	@Autowired
	VersionService versionService;

	@Autowired
	AdvertisementService adService;

	@RequestMapping(value = "/deleteWorship", method = RequestMethod.POST)
	public @ResponseBody String deleteWorship(@RequestBody String worshipId) {
		
		orderService.deleteAll(worshipId);
		adService.deleteAll(worshipId);
		worshipService.delete(worshipId);
		
		return "";
	}

	@RequestMapping(value = "/add/worship", method = RequestMethod.POST)
	public @ResponseBody String addWorship(@RequestBody  Map<String,Object> worshipData) {
		logger.debug(worshipData.toString());
		String lastWorshipId = worshipService.getLastWorshipId((String)worshipData.get("churchId"));
		String newWorshipId = "";
		if("".equals(lastWorshipId.trim())) {
			newWorshipId = WorshipIdGenerator.newWorshipId();

		}else {
			newWorshipId = lastWorshipId;
		}
		WorshipVO worshipVO = new WorshipVO();
		logger.debug("worshipDate : "+worshipData.get("worshipDate").toString());

		worshipVO.setWorshipId(newWorshipId);
		worshipVO.setWorshipDate((String)worshipData.get("worshipDate"));
		worshipVO.setMainPresenter((String)worshipData.get("mainPresenter"));
		worshipVO.setNextPresenter((String)worshipData.get("nextPresenter"));
		worshipVO.setNextPrayer((String)worshipData.get("nextPrayer"));
		worshipVO.setNextOffer((String)worshipData.get("nextOffer"));
		worshipVO.setVersion("aaa");
		worshipVO.setChurchId((String)worshipData.get("churchId"));
		logger.debug("worshipVO : "+worshipVO.toString());
		worshipService.addWorship(worshipVO);
		
		return "";
	}
	
	/* 업데이트 내용을 받는 컨트롤러 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/process/update", method = RequestMethod.POST)
	public @ResponseBody String updateWorship(
			@RequestBody Map<String,Object> inputMap
			){

		logger.debug("worshipObject : "+ inputMap.get("worshipObject"));
		logger.debug("orderList : "+ inputMap.get("orderList"));
		logger.debug("addOrderList : "+ inputMap.get("addOrderList"));
		logger.debug("adList : "+ inputMap.get("adList"));
		logger.debug("addAdList : "+ inputMap.get("addAdList"));
		logger.debug("worshipId : "+ inputMap.get("worshipId"));
		
		String version = versionService.getVersionById((String)inputMap.get("worshipId"));
		
		
		boolean worshipInfoUpdateYN = false;
		boolean orderUpdateYN = false;
		boolean adUpdateYN = false;
		Map<String,String> worshipMap = (Map<String,String>)inputMap.get("worshipObject");
		worshipMap.put("worshipId", (String)inputMap.get("worshipId"));
		worshipMap.put("userId", "admin");
		
		worshipInfoUpdateYN = worshipInfoUpdateYN || worshipService.update(worshipMap);
		if (worshipInfoUpdateYN) { 
			version = versionService.versionUp(version , 2) ;
		}
		
		orderUpdateYN = orderUpdateYN || orderService.add((List<Map<String, Object>>) inputMap.get("addOrderList"));
		orderUpdateYN = orderUpdateYN || orderService.update((List<Map<String, Object>>) inputMap.get("orderList"));
		orderUpdateYN = orderUpdateYN || orderService.delete((String)inputMap.get("worshipId"), (List<String>)inputMap.get("removeOrderList"));
		
		logger.debug("order version update YN : "+ orderUpdateYN);
		if (orderUpdateYN) { 
			version = versionService.versionUp(version , 0) ;
		}
		
		adUpdateYN = adUpdateYN || adService.add((List<Map<String, Object>>) inputMap.get("addAdList"));
		adUpdateYN = adUpdateYN || adService.update((List<Map<String, Object>>) inputMap.get("adList"));
		adUpdateYN = adUpdateYN || adService.delete((String)inputMap.get("worshipId"), (List<String>)inputMap.get("removeAdList"));
		
		logger.debug("advertisement version update YN : "+ adUpdateYN);
		if (adUpdateYN) { 
			version = versionService.versionUp(version , 1) ;
		}
		
		logger.debug(version);
		versionService.updateVersion((String)inputMap.get("worshipId"),version);
		
		return "";
	}
	
	/* 예배 정보 관련 API*/
	
	@RequestMapping(value = "/getWorshipInfo", method = RequestMethod.POST)
	public @ResponseBody Map<String,String> getWorshipInfo(@RequestBody String worshipId) {
		return worshipService.getWorshipInfo(worshipId);
	}
	
	@RequestMapping(value = "/getWorshipOrderList", method = RequestMethod.POST)
	public @ResponseBody List<WorshipOrderVO> getWorshipOrderList(@RequestBody String worshipId) {
		return orderService.getWorshipOrderList(worshipId);
	}
	
	@RequestMapping(value = "/getWorshipAdList", method = RequestMethod.POST)
	public @ResponseBody List<WorshipAdVO> getWorshipAdList(@RequestBody String worshipId) {
		return adService.getWorshipAdList(worshipId);
	}
	
	@RequestMapping(value = "/worship-list", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, String>> worshipList(){
		logger.debug(worshipService.getWorshipList().toString());
		return worshipService.getWorshipList();
	}
	

//	
//	@RequestMapping(value = "/form/update", method = RequestMethod.GET)
//	public String formUpdate(Model model) {
//		model.addAttribute("pageName","worship-update");
//		return "home";
//	}
//	
//	@RequestMapping(value = "/form/add", method = RequestMethod.GET)
//	public String formAdd(Model model) {
//		model.addAttribute("pageName","worship-add");
//		return "home";
//	}
//	
//	@RequestMapping(value = "/win/list/worship", method = RequestMethod.GET)
//	public String winListWorship(Model model) {
//		return "win/worship-list";
//	}
//	
//
//	@RequestMapping(value = "/order", method = RequestMethod.POST)
//	public @ResponseBody Map<String,Object> getOrder(@RequestParam String id) {
//		return orderService.getOrderByWorshipId(id);
//	}	
}
