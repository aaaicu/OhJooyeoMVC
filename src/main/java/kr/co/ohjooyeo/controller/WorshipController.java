package kr.co.ohjooyeo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.ohjooyeo.service.AdvertisementService;
import kr.co.ohjooyeo.service.OrderService;
import kr.co.ohjooyeo.service.VersionService;
import kr.co.ohjooyeo.service.WorshipService;
import kr.co.ohjooyeo.vo.WorshipAdVO;
import kr.co.ohjooyeo.vo.WorshipVO;

@CrossOrigin
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

	@RequestMapping(value = "/worship/list", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, String>> worshipList(
			@RequestBody Map<String, String> reqMap
			){
		String churchId = reqMap.get("churchId");
		logger.debug(worshipService.getWorshipList(churchId).toString());
		return worshipService.getWorshipList(churchId);
	}
	
	@RequestMapping(value = "/worship/info", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> worshipInfo(
			@RequestBody Map<String, String> reqMap
			) {
		String churchId = reqMap.get("churchId");
		String worshipId = reqMap.get("worshipId");
		
		int reqVersion = Integer.valueOf(reqMap.get("version"));
		
		
		Map<String, Object> info = new HashMap<String, Object>();
		info = worshipService.getWorshipOrder(churchId,worshipId,info);
		info = worshipService.getWorshipMc(churchId,worshipId,info);
		
		if(reqVersion == Integer.parseInt(info.get("version").toString())) {
			info = null;
		}
		return info;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	@RequestMapping(value = "/worship/add", method = RequestMethod.POST)
	public @ResponseBody String worshipAdd(@RequestBody  Map<String,Object> worship) {
		
		/* 파라미터 데이터 파싱 */
		Map<String,Object> worshipData = (Map<String, Object>) worship.get("worshipInfo");
		List<Map<String,Object>> orderData = (List<Map<String,Object>>) worship.get("worshipOrder");
		List<Map<String,Object>> adData = (List<Map<String,Object>>) worship.get("worshipAd");
		
		String newWorshipId = worshipService.getNewWorshipId((int)worshipData.get("churchId"));
		
		WorshipVO worshipVO = new WorshipVO();
		
		worshipVO.setWorshipId(newWorshipId);
		worshipVO.setWorshipDate((String)worshipData.get("worshipDate"));
		worshipVO.setMainPresenter((String)worshipData.get("mainPresenter"));
		worshipVO.setNextPresenter((String)worshipData.get("nextPresenter"));
		worshipVO.setNextPrayer((String)worshipData.get("nextPrayer"));
		worshipVO.setNextOffer((String)worshipData.get("nextOffer"));
		worshipVO.setChurchId((int)worshipData.get("churchId"));
		
		worshipService.addWorship(worshipVO);
		
		orderService.addWorshipOrder((int)worshipData.get("churchId"),newWorshipId,orderData);
		return newWorshipId;
	}
	
	
	
	@RequestMapping(value = "/worship/delete", method = RequestMethod.POST)
	public @ResponseBody String deleteWorship(@RequestBody String worshipId) {
		
		orderService.deleteAll(worshipId);
		adService.deleteAll(worshipId);
		worshipService.delete(worshipId);
		
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
		
//		orderUpdateYN = orderUpdateYN || orderService.add((List<Map<String, Object>>) inputMap.get("addOrderList"));
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
	

	
	@RequestMapping(value = "/getWorshipAdList", method = RequestMethod.POST)
	public @ResponseBody List<WorshipAdVO> getWorshipAdList(@RequestBody String worshipId) {
		return adService.getWorshipAdList(worshipId);
	}
	
}
