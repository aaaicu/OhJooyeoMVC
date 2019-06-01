package kr.co.ohjooyeo.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.ohjooyeo.service.AdvertisementService;
import kr.co.ohjooyeo.service.OrderService;
import kr.co.ohjooyeo.service.VersionService;
import kr.co.ohjooyeo.service.WorshipService;
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
		
		if( logger.isTraceEnabled()) {
			for(String a : adOrders) {			
				logger.trace(a);
			}
			for(String a : adTitles) {			
				logger.trace(a);
			}
			for(String a : adContents) {			
				logger.trace(a);
			}
		}
		
		worshipService.addWorship(vo);
		// 순서 추가
//		orderService.setWorshipOrder(vo.getWorshipId(),types, titles, details, presenters);
//		adService.setWorshipAd(vo.getWorshipId(), adTitles, adContents);

		return "redirect:worship";
	}
//	
//	@Override
//	@RequestMapping(value = "/worship-add-temp", method = RequestMethod.GET)
//	public String worshipAddTemp() {
//		return "worship-add";
//	}
//	
//	@Override
//	@RequestMapping(value = "/worship-update-temp", method = RequestMethod.GET)
//	public String worshipUpdateTemp() {
////		return "worship-order-form";
//		return "worship-update";
//	}
//	
//	@Override
//	@RequestMapping(value = "/worship-update", method = RequestMethod.GET)
//	public String worshipOrderForm() {
//		return "worship-order-form";
//	}
	
	/* 업데이트 내용을 받는 컨트롤러 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/update-worship", method = RequestMethod.POST)
	public @ResponseBody String updateWorship(
			@RequestBody Map<String,Object> inputMap
			){

		logger.debug("worshipObject : "+ inputMap.get("worshipObject"));
		logger.debug("orderList : "+ inputMap.get("orderList"));
		logger.debug("addOrderList : "+ inputMap.get("addOrderList"));
		logger.debug("adList : "+ inputMap.get("adList"));
		logger.debug("addAdList : "+ inputMap.get("addAdList"));
		logger.debug("worshipId : "+ inputMap.get("worshipId"));
		orderService.add((List<Map<String, Object>>) inputMap.get("addOrderList"));
		orderService.update((List<Map<String, Object>>) inputMap.get("orderList"));
		orderService.delete((String)inputMap.get("worshipId"), (List<String>)inputMap.get("removeOrderList"));
		
		adService.add((List<Map<String, Object>>) inputMap.get("addAdList"));
		adService.update((List<Map<String, Object>>) inputMap.get("adList"));
		adService.delete((String)inputMap.get("worshipId"), (List<String>)inputMap.get("removeAdList"));
		
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
	
	@RequestMapping(value = "/worship-list", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, String>> worshipList(){
		logger.debug(worshipService.getWorshipList().toString());
		return worshipService.getWorshipList();
	}

	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> getOrder(@RequestParam String id) {
		return orderService.getOrderByWorshipId(id);
	}	
	
	@RequestMapping(value = "/worship-id/{id}", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> getAllData(
			@PathVariable String id
			) {
		return versionService.getAllData(id);
	}
}
