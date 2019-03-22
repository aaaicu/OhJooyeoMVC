package kr.co.ohjooyeo.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import kr.co.ohjooyeo.vo.WorshipAdVO;
import kr.co.ohjooyeo.vo.WorshipOrderVO;
import kr.co.ohjooyeo.vo.WorshipVO;


@Controller

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
	
	@RequestMapping(value = "/getWorshipIdList", method = RequestMethod.POST)
	public @ResponseBody List<String> getWorshipIdList(@RequestBody String userId) {
		List<String> result = worshipService.getWorshipIdList(userId);
		logger.debug("result : " +result);
		return result;
	}

	@RequestMapping(value = "/worship", method = RequestMethod.GET)
	public String worshipForm() {
		return "worship-form";
	}

	@RequestMapping(value = "/deleteWorship", method = RequestMethod.POST)
	public @ResponseBody String deleteWorship(@RequestBody String worshipId) {
		logger.trace("test : " +worshipId);
		
		orderService.deleteAll(worshipId);
		adService.deleteAll(worshipId);
		worshipService.delete(worshipId);
		
		
		return "";
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
		logger.debug(vo.toString());
		for(String a : adOrders) {			
			logger.trace(a);
		}
		for(String a : adTitles) {			
			logger.trace(a);
		}
		for(String a : adContents) {			
			logger.trace(a);
		}
		worshipService.addWorship(vo);
		// 순서 추가
		orderService.setWorshipOrder(vo.getWorshipId(),types, titles, details, presenters);
//		adService.setWorshipAd(vo.getWorshipId(), adTitles, adContents);

		return "redirect:worship";
	}
	
	@RequestMapping(value = "/worship-add-temp", method = RequestMethod.GET)
	public String worshipAddTemp() {
		return "worship-add";
	}
	

	@RequestMapping(value = "/worship-update-temp", method = RequestMethod.GET)
	public String worshipUpdateTemp() {
//		return "worship-order-form";
		return "worship-update";
	}
	
	@RequestMapping(value = "/worship-update", method = RequestMethod.GET)
	public String worshipOrderForm() {
		return "worship-order-form";
	}
	
	/* 업데이트 내용을 받는 컨트롤러 */
	@RequestMapping(value = "/updateWorship", method = RequestMethod.POST)
	public @ResponseBody String updateWorship(
			@RequestBody Map<String,Object> inputMap
			) throws UnsupportedEncodingException {

		logger.debug("inputMap : "+ inputMap);
		
		/* 파라미터 처리 로직 */
		String worship = (String)inputMap.get("worship");
		String order = (String)inputMap.get("order");
		String ad= (String)inputMap.get("ad");
		worship = "?"+URLDecoder.decode(worship, "UTF-8");
		order = "?"+URLDecoder.decode(order, "UTF-8");
		ad = "?"+URLDecoder.decode(ad, "UTF-8");
		
		/* 파라미터 가공  */
		/* VO업데이트 및 추가 시 예배 ID 필요*/
		Map<String, List<String>> worshipParam = new HashMap<>();
		worshipParam.putAll(UriComponentsBuilder.fromUriString(worship).build().getQueryParams());
		
	    Map<String, List<String>> orderParam = new HashMap<>();
	    orderParam.putAll(UriComponentsBuilder.fromUriString(order).build().getQueryParams());
	    
	    Map<String, List<String>> adParam = new HashMap<>();
	    adParam.putAll(UriComponentsBuilder.fromUriString(ad).build().getQueryParams());
	    
	    logger.trace(worshipParam.toString());
	    logger.trace(orderParam.toString());
	    logger.trace(adParam.toString());
	    
	    //순서상에 변동이 있는지 확인필요
	    
	    
	    
	    List<String> worshipId = worshipParam.get("selectWorshipId");
	    
	    String version = versionService.getVersionById(worshipId.get(0));

	    boolean worshipVersionUp = false;
	    
	    /* 예배정보 업데이트 */
	    if(worshipParam.get("worshipUpdateYN").contains("1")) {
	    	logger.trace("예배정보 업데이트메서드 추가");
	    	
	    	worshipVersionUp = true;
	    	
	    	WorshipVO updateWorshipVO = new WorshipVO(
	    			worshipId.get(0),
	    			worshipParam.get("worshipDate").get(0),
	    			worshipParam.get("mainPresenter").get(0),
	    			worshipParam.get("nextPresenter").get(0),
	    			worshipParam.get("nextPrayer").get(0),
	    			worshipParam.get("nextOffer").get(0),
	    			"",
	    			"admin");
	    	worshipService.update(updateWorshipVO);
	    }
	    
	    /* 예배순서 업데이트 */
	    /* 변경이 되었거나 새롭게 추가된 순서가 있다면 업데이트 실행 */
	    if(!orderParam.isEmpty()&&(orderParam.get("orderUpdateYN").contains("1") || orderParam.get("orderOrder").contains("-1"))) {
	    	
	    	logger.trace("예배순서 업데이트메서드 추가");

	    	worshipVersionUp = true;
	    	
		    orderParam.put("worshipId", worshipId);
	    	Map<String, List<Object>> updateWorshipOrderVOList = worshipService.analyzeValues(orderParam,"order");
	    	logger.debug("분석결과 : "+updateWorshipOrderVOList);
	    	
	    	List<Object> addList = updateWorshipOrderVOList.get("addList");
	    	List<WorshipOrderVO> convertAddList = new ArrayList<>(addList.size());
	    	for(Object obj : addList) {
	    		convertAddList.add((WorshipOrderVO)obj);
	    	}
		    orderService.add(convertAddList); 
		
		    
	    	List<Object> updateList = updateWorshipOrderVOList.get("updateList");
	    	List<WorshipOrderVO> convertUpdateList = new ArrayList<>(updateList.size());
	    	for(Object obj : updateList) {
	    		convertUpdateList.add((WorshipOrderVO)obj);
	    	}
		    orderService.update(convertUpdateList);
	    }
	    
	    @SuppressWarnings("unchecked")
		List<String> removeOrderList = (List<String>)inputMap.get("removeOrderList");
	    
	    /* 예배순서 삭제 */
	    if (!removeOrderList.isEmpty()) {	    	
	    	logger.debug(removeOrderList.toString());
	    	orderService.delete(worshipId.get(0),removeOrderList);
	    }
	    
	    /* 둘중 하나라도 업데이트가 됐다면 버전변경 */
	    if(worshipVersionUp) {
	    	//versionService.versionUp(version,0) 채번 메서드
	    	versionService.updateVersion(worshipId.get(0) , versionService.versionUp(version,0));
	    }
	    
	    /* 광고 업데이트 */
	    if(!adParam.isEmpty()&&(adParam.get("adUpdateYN").contains("1") || adParam.get("adOrder").contains("-1"))) {
	    	logger.trace("광고 업데이트메서드 추가");
	    	versionService.updateVersion(worshipId.get(0) , versionService.versionUp(version,1));

		    adParam.put("worshipId", worshipId);
	    	Map<String, List<Object>> updateAdVOList = worshipService.analyzeValues(adParam,"ad");
	    	List<Object> addList = updateAdVOList.get("addList");
	    	List<WorshipAdVO> convertAddList = new ArrayList<>(addList.size());
	    	for(Object obj : addList) {
	    		convertAddList.add((WorshipAdVO)obj);
	    	}
		    adService.add(convertAddList);
		    
	    	List<Object> updateList = updateAdVOList.get("updateList");
	    	List<WorshipAdVO> convertUpdateList = new ArrayList<>(updateList.size());
	    	for(Object obj : updateList) {
	    		convertUpdateList.add((WorshipAdVO)obj);
	    	}
	    	adService.update(convertUpdateList);
		    
	    }
	    
		@SuppressWarnings("unchecked")
		List<String> removeAdList = (List<String>) inputMap.get("removeAdList");
	    
	    /* 광고 삭제 */
	    if (!removeAdList.isEmpty()) {	    	
	    	logger.debug(removeAdList.toString());
	    	adService.delete(worshipId.get(0),removeAdList);
	    }
	    
	    /* 찬양 업데이트 */
	    
		return "";
	}
	
	@RequestMapping(value = "/getWorshipOrderList", method = RequestMethod.POST)
	public @ResponseBody List<WorshipOrderVO> getWorshipOrderList(@RequestBody String worshipId) {
		return orderService.getWorshipOrderList(worshipId);
	}
	
	@RequestMapping(value = "/getWorshipAdList", method = RequestMethod.POST)
	public @ResponseBody List<WorshipAdVO> getWorshipAdList(@RequestBody String worshipId) {
		logger.debug(adService.getWorshipAdList(worshipId).toString());
		return adService.getWorshipAdList(worshipId);
	}
	@RequestMapping(value = "/getWorshipInfo", method = RequestMethod.POST)
	public @ResponseBody Map<String,String> getWorshipInfo(@RequestBody String worshipId) {
		
		logger.debug("결과 : " + worshipService.getWorshipInfo(worshipId));
		return worshipService.getWorshipInfo(worshipId);
	}
	
	@RequestMapping(value = "/testCSS", method = RequestMethod.GET)
	public String testCSS(Model model) {
		model.addAttribute("pageName","worship-update");
		return "home";
	}
}
