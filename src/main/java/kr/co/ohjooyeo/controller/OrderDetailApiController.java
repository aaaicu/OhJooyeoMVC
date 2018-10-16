package kr.co.ohjooyeo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ohjooyeo.service.AdvertisementService;
import kr.co.ohjooyeo.service.BibleService;
import kr.co.ohjooyeo.service.OrderService;

@RestController
public class OrderDetailApiController {
	@Autowired
	OrderService orderService;
	
	@Autowired
	BibleService bibleService;
	
	@Autowired
	AdvertisementService adService;

	/*  "성경 a:b(~성경 c:d)(/성경 a:b(~성경 c:d))" [String] */
	@RequestMapping(value = "/phrase", method = RequestMethod.POST)
	public List<Map<String, String>> phrase(@RequestBody Map<String, String> phrase) {
		System.out.println(bibleService.getPhrase(phrase.get("phraseRange")));
		return bibleService.getPhrase(phrase.get("phraseRange"));	
	}
	
	@RequestMapping(value = "/getChapterList", method = RequestMethod.POST)
	public @ResponseBody List<String> getChapterList(@RequestBody String book) {
		System.out.println(book);
		List<String> result = bibleService.getChapterList(book);
		System.out.println(result);
		return result;	
	}
	
	@RequestMapping(value = "/getSectionList", method = RequestMethod.POST)
	public @ResponseBody List<String> getSection(@RequestBody Map<String,String> inputMap) {
		System.out.println(inputMap.get("book") +" / "+ inputMap.get("chapter"));
		List<String> result = bibleService.getSectionList(inputMap.get("book"), inputMap.get("chapter"));
		return result;	
	}
	
	
	//미사용
	@RequestMapping(value = "/advertisement", method = RequestMethod.POST)
	public List<Map<String,String>> getAdvertisement(@RequestParam String worshipId) {
		return adService.getAdsByWorshipId(worshipId);
	}
}
