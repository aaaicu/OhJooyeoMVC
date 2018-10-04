package kr.co.ohjooyeo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import kr.co.ohjooyeo.dao.BibleDAO;
import kr.co.ohjooyeo.dao.OrderDAO;
import kr.co.ohjooyeo.dao.WorshipDAO;
import kr.co.ohjooyeo.vo.WorshipOrderVO;

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
	
	public void setWorshipOrder(String worshipId,String [] types,String [] titles,String [] details,String [] presenters) {
		WorshipOrderVO [] orderArray = new WorshipOrderVO[titles.length];
		for(int i = 0 ; i < titles.length ; i ++) {
			orderArray[i] = new WorshipOrderVO(worshipId,i,i,types[i], titles[i],details[i], presenters[i] );
//			System.out.println(orderArray[i] );
		}
		orderDAO.setWorshipOrder(orderArray);
	}

	public Map<String, Object> getOrderByWorshipId(String id) {
		String worshipId = id;
		Map<String,Object> result = new HashMap<>();
		Map<String,String> worship = worshipDAO.getWorship(worshipId);
		Map<String,String> nextPresenter = new HashMap<>();
		
		List<Map<String,String>> order = orderDAO.getAllOrder(worshipId);
		
		result.put("worshipOrder",order);
		nextPresenter.put("offer",  worship.get("next_offer"));
		nextPresenter.put("prayer",  worship.get("next_prayer"));
		nextPresenter.put("mainPresenter", worship.get("next_presenter"));
		
		result.put("mainPresenter",worship.get("main_presenter"));
		result.put("nextPresenter",nextPresenter);
		return result;
	}
	/* 런처 말씀 */
	public Map<String, String> getLaunchPhrase(String userId, String date) {
		Map<String,String> result = new HashMap<>();
		result.put("phrase", orderDAO.getLaunchPhrase(userId,date));
		
		return result;
	}
	
	public List<WorshipOrderVO> getWorshipOrderList(String worshipId) {
		return orderDAO.getWorshipOrderList(worshipId);
	}
	
	public Map <String,List<WorshipOrderVO>> analyzeValues(MultiValueMap<String, String> params) {
		Map <String,List<WorshipOrderVO>> result = new HashMap<>();
		List<WorshipOrderVO> addList = new ArrayList<>();
		List<WorshipOrderVO> updateList = new ArrayList<>();
		
		result.put("addList" ,addList);
		result.put("updateList", updateList);
		
		/* worship_id에 속하는 orderId의 최대값 구하기 */
		int maxId = 0;
		for(String id : params.get("orderId")) {
			if(maxId <= Integer.valueOf(id)) {
				maxId = Integer.valueOf(id);				
			}
		}
		
		for(int i = 0 ; i < params.get("orderId").size() ; i++) {
			WorshipOrderVO order = new WorshipOrderVO(
					params.get("worship_id").get(0), 
					Integer.valueOf(params.get("orderId").get(i)), 
					i, 
					params.get("type").get(i), 
					params.get("title").get(i), 
					params.get("detail").get(i), 
					params.get("presenter").get(i));
			if (params.get("orderId").get(i).equals("-1") ) {
				order.setOrderId(++maxId);
				addList.add(order);
			}else if(params.get("updateYN").get(i).equals("1")){
				updateList.add(order);
			}
			System.out.println(order);
		}
		return result;
	}
	
	//미사용
	public Map<String,Object> getPhraseByDate(String date) {

		// return bibleDAO.getPhrase(sVO, eVO);
		// '성경봉독' 이라는 단어는 바뀌지 않는다는 전제가 필요

		String worshipId = worshipDAO.getWorshipId(date);
		String readPhrase = "성경봉독";
		String rawPhrases = orderDAO.getOrder(worshipId, readPhrase).get("detail");
		
		Map<String,Object> result = new HashMap<>();
		result.put("phraseList", bibleService.getPhrase(rawPhrases));
		return result;
	}

}
