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
import kr.co.ohjooyeo.service.BibleService;
import kr.co.ohjooyeo.service.OrderService;
import kr.co.ohjooyeo.service.VersionService;
import kr.co.ohjooyeo.service.WorshipService;

@Controller
//@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class BibleController {
	private static final Logger logger = LoggerFactory.getLogger(BibleController.class);
	
	@Autowired
	WorshipService worshipService;

	@Autowired
	OrderService orderService;
	
	@Autowired
	VersionService versionService;
	
	@Autowired
	BibleService bibleService;
	
	@Autowired
	AdvertisementService adService;
	
	@RequestMapping(value = "/search-bible", method = RequestMethod.GET)
	public String searchBible(){
		return "win/search-bible";
	}

	/*  "성경 a:b(~성경 c:d)(/성경 a:b(~성경 c:d))" [String] */
	@RequestMapping(value = "/phrase", method = RequestMethod.POST)
	public @ResponseBody List<List<Map<String, String>>> phrase(@RequestBody Map<String, String []> phrase) {
		return bibleService.getPhrase(phrase.get("phraseRange"));	
	}
	
	@RequestMapping(value = "/getChapterList", method = RequestMethod.POST)
	public @ResponseBody List<String> getChapterList(@RequestBody String book) {
		logger.debug(book);
		List<String> result = bibleService.getChapterList(book);
		logger.debug(result.toString());
		return result;	
	}
	
	@RequestMapping(value = "/getSectionList", method = RequestMethod.POST)
	public @ResponseBody List<String> getSection(@RequestBody Map<String,String> inputMap) {
		logger.debug(inputMap.get("book") +" / "+ inputMap.get("chapter"));
		List<String> result = bibleService.getSectionList(inputMap.get("book"), inputMap.get("chapter"));
		return result;	
	}

	
}
