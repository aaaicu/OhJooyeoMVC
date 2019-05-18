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
	WorshipService worshipService;

	@Autowired
	OrderDAO orderDAO;
	
	@Autowired
	BibleService bibleService;
	
	public void setWorshipOrder(String worshipId,String [] types,String [] titles,String [] details,String [] presenters) {
		List<WorshipOrderVO> orderList = new ArrayList<>();
		for(int i = 0 ; i < titles.length ; i ++) {
			orderList.add(new WorshipOrderVO(worshipId,i,i,types[i], titles[i],details[i], presenters[i] ));
		}
		orderDAO.insertVOList(orderList);
	}

	public Map<String, Object> getOrderByWorshipId(String id) {
		String worshipId = id;
		Map<String,Object> result = new HashMap<>();
		Map<String,String> worship = worshipService.getWorshipInfo(worshipId);
		Map<String,String> nextPresenter = new HashMap<>();
		
		List<WorshipOrderVO> order = orderDAO.getWorshipOrderList(worshipId);
		
		result.put("worshipOrder",order);
		nextPresenter.put("offer",  worship.get("nextOffer"));
		nextPresenter.put("prayer",  worship.get("nextPrayer"));
		nextPresenter.put("mainPresenter", worship.get("nextPresenter"));
		
		result.put("mainPresenter",worship.get("mainPresenter"));
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
	
	/* 순서추가 */
	public void add(List<WorshipOrderVO> list) {
		if(list.size() > 0 ) {
			orderDAO.insertVOList(list);
		}
	}

	/* 순서수정 */
	public void update(List<Map<String,Object>> list) {
		if(list.size() > 0 ) {
			orderDAO.updateVOList(list);
		}
	}

	/* 순서삭제 */
	public void delete(String worshipId , List<String> list) {
		if(list.size() > 0 ) {
			Map<String,Object> deleteMap = new HashMap<>();
			deleteMap.put("worshipId", worshipId );
			deleteMap.put("list",list);
			
			System.out.println(deleteMap);
			orderDAO.deleteVOList(deleteMap);
		}
	}

	public void deleteAll(String worshipId) {
		orderDAO.deleteAll(worshipId);
	}
	

}
