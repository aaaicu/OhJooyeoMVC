package kr.co.ohjooyeo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ohjooyeo.dao.BibleDAO;
import kr.co.ohjooyeo.dao.OrderDAO;
import kr.co.ohjooyeo.dao.WorshipDAO;

@Service
public class OrderService {
	@Autowired
	BibleDAO bibleDAO;

	@Autowired
	WorshipDAO worshipDAO;

	@Autowired
	OrderDAO orderDAO;
	
	@Autowired
	BibleService bibleService;

	public Map<String,Object> getPhraseByDate(String date) {

		// return bibleDAO.getPhrase(sVO, eVO);
		// '성경봉독' 이라는 단어는 바뀌지 않는다는 전제가 필요

		String worshipId = worshipDAO.getWorshipId(date);
		String readPhrase = "성경봉독";
		String rawPhrases = orderDAO.getOrder(worshipId, readPhrase).get("detail");

//		성경 검색 test
//		System.out.println(bibleService.getPhrase("마 1:1"));
		
		Map<String,Object> result = new HashMap<>();
		result.put("phraseList", bibleService.getPhrase(rawPhrases));
		return result;
	}

	public Map<String, Object> getOrderByDate(String date) {
		String worshipId = worshipDAO.getWorshipId(date);
		Map<String,Object> result = new HashMap<>();
		Map<String,String> worship = worshipDAO.getWorship(worshipId);
		Map<String,String> nextPresenter = new HashMap<>();
		
		List<Map<String,String>> order = orderDAO.getAllOrder(worshipId);
		
		result.put("order",order);
		nextPresenter.put("offer",  worship.get("next_offer"));
		nextPresenter.put("prayer",  worship.get("next_prayer"));
		nextPresenter.put("mainPresenter", worship.get("next_presenter"));
		
		result.put("mainPresenter",worship.get("main_presenter"));
		result.put("nextPresenter",nextPresenter);
		return result;
	}

	public Map<String, String> getLaunchPhrase(String userId, String date) {
		Map<String,String> result = new HashMap<>();
		result.put("phrase", orderDAO.getLaunchPhrase(userId,date));
		
		return result;
	}

}
