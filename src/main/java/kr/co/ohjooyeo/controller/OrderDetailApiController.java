package kr.co.ohjooyeo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ohjooyeo.service.AdvertisementService;
import kr.co.ohjooyeo.service.BibleService;
import kr.co.ohjooyeo.service.OrderService;
//미사용 
@RestController
public class OrderDetailApiController {
	@Autowired
	OrderService orderService;
	
	@Autowired
	BibleService bibleService;
	
	@Autowired
	AdvertisementService adService;

	@RequestMapping(value = "/phrase", method = RequestMethod.POST)
	public List<Map<String, String>> phrase(@RequestBody Map<String, String> phrase) {
		System.out.println(bibleService.getPhrase(phrase.get("phraseRange")));
		return bibleService.getPhrase(phrase.get("phraseRange"));	
	}
	
	@RequestMapping(value = "/advertisement", method = RequestMethod.POST)
	public List<Map<String,String>> getAdvertisement(@RequestParam String worshipId) {
		return adService.getAdsByWorshipId(worshipId);
	}
}
