package kr.co.ohjooyeo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.ohjooyeo.service.OrderService;
import kr.co.ohjooyeo.service.WorshipService;
import kr.co.ohjooyeo.vo.WorshipOrderVO;
import kr.co.ohjooyeo.vo.WorshipVO;

@Controller

public class WorshipController {
	@Autowired
	WorshipService worshipService;

	@Autowired
	OrderService orderService;

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

	@RequestMapping(value = "/add-worship-order", method = RequestMethod.POST)
	public String addWorshipOrder(@RequestParam("worship_id") String worshipId,@RequestParam("type") String[] types, @RequestParam("title") String[] titles,
			@RequestParam("detail") String[] details, @RequestParam("presenter") String[] presenters) {
		orderService.setWorshipOrder(worshipId,types, titles, details, presenters);
		return "redirect:worship-order";
	}
	
	@RequestMapping(value = "/getWorshipOrderList", method = RequestMethod.POST)
	public @ResponseBody List<WorshipOrderVO> getWorshipOrderList(@RequestBody String worshipId) {
		System.out.println(worshipId+"선택");
		System.out.println(orderService.getWorshipOrderList(worshipId));
		
		return orderService.getWorshipOrderList(worshipId);
	}

}
