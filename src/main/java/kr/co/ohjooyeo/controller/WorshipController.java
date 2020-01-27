package kr.co.ohjooyeo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

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
import kr.co.ohjooyeo.vo.WorshipOrderVO;
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
		
		logger.debug(newWorshipId);
		logger.debug(worshipData.get("churchId").toString());
		
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
		adService.addWorshipAd((int)worshipData.get("churchId"),newWorshipId,adData);
		
		return newWorshipId;
	}
	
	@RequestMapping(value = "/worship/delete", method = RequestMethod.DELETE)
	public @ResponseBody boolean worshipDelete(@RequestBody Map<String,String> map) {
		int resultOrder = orderService.deleteWorshipOrder(map);
		int resultAd = adService.deleteWorshipAd(map);
		int resultWorship = worshipService.deleteWorship(map);
		boolean result = false;
		logger.debug("resultOrder : " + resultOrder+"");
		logger.debug("resultAd : "+resultAd+"");
		logger.debug("resultWorship : "+resultWorship+"");
		
		if (resultWorship > 0 ) {
			result= true;
		}
		return result;
	}


	
	/* 업데이트 내용을 받는 컨트롤러 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/worship/update", method = RequestMethod.PUT)
	public @ResponseBody Boolean updateWorship(
			@RequestBody Map<String,Object> worship
			){
		
		/* 파라미터 데이터 파싱 */
		Map<String,Object> worshipData = (Map<String, Object>) worship.get("worshipInfo");
		List<Map<String,Object>> orderData = (List<Map<String,Object>>) worship.get("worshipOrder");
		List<Map<String,Object>> adData = (List<Map<String,Object>>) worship.get("worshipAd");
		
		WorshipVO worshipVO = new WorshipVO();
		
		worshipVO.setChurchId((int)worshipData.get("churchId"));
		worshipVO.setWorshipId((String)worshipData.get("worshipId"));
		worshipVO.setWorshipDate((String)worshipData.get("worshipDate"));
		worshipVO.setMainPresenter((String)worshipData.get("mainPresenter"));
		worshipVO.setNextPresenter((String)worshipData.get("nextPresenter"));
		worshipVO.setNextPrayer((String)worshipData.get("nextPrayer"));
		worshipVO.setNextOffer((String)worshipData.get("nextOffer"));
		
		boolean result = true;
		result = result && worshipService.updateWorship(worshipVO);
		result = result && orderService.updateWorshipOrder((int)worshipData.get("churchId"),(String)worshipData.get("worshipId"),orderData);
		result = result && adService.updateWorshipAd((int)worshipData.get("churchId"),(String)worshipData.get("worshipId"),adData);
		if(result) {
			versionService.versionUp((int)worshipData.get("churchId"),(String)worshipData.get("worshipId")) ;
		}
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/worship/ad/info", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> worshipAdInfo(@RequestBody Map<String, String> reqMap) {
		String churchId = reqMap.get("churchId");
		String worshipId = reqMap.get("worshipId");
		int reqVersion = Integer.valueOf(reqMap.get("version"));
		
		Map<String, Object> info = new HashMap<String, Object>();
		info = adService.getWorshipAd(churchId,worshipId,info);
		List<Map<String, Object>> worshipAd = (List<Map<String, Object>>)info.get("worshipAd");
		
		
		if(worshipAd.size() >0) {
			int version = (int)worshipAd.get(0).get("version");
			if(reqVersion == version) {
				info = null;
			}
		}else {
			info = null;
		}
		return info;
	}
	
//	/* 예배 정보 관련 API*/
//	
//	@RequestMapping(value = "/getWorshipInfo", method = RequestMethod.POST)
//	public @ResponseBody Map<String,String> getWorshipInfo(@RequestBody String worshipId) {
//		return worshipService.getWorshipInfo(worshipId);
//	}
	

	

	
}
