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
		List<WorshipOrderVO> orderList = new ArrayList<>();
		for(int i = 0 ; i < titles.length ; i ++) {
			orderList.add(new WorshipOrderVO(worshipId,i,i,types[i], titles[i],details[i], presenters[i] ));
		}
		orderDAO.insertVOList(orderList);
	}

	public Map<String, Object> getOrderByWorshipId(String id) {
		String worshipId = id;
		Map<String,Object> result = new HashMap<>();
		Map<String,String> worship = worshipDAO.getWorship(worshipId);
		Map<String,String> nextPresenter = new HashMap<>();
		
		List<WorshipOrderVO> order = orderDAO.getWorshipOrderList(worshipId);
		
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
					Integer.valueOf(params.get("order").get(i)),
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
		}
		return result;
	}
	/* 순서추가 */
	public int add(List<WorshipOrderVO> list) {
		if(list.size() > 0 ) {
			orderDAO.insertVOList(list);
			return 1;
		}
		return 0;
	}

	/* 순서수정 */
	public int update(List<WorshipOrderVO> list) {
		if(list.size() > 0 ) {
			orderDAO.updateVOList(list);
			return 1;
		}
		return 0;
	}

	/* 순서삭제 */
	public int delete(String worshipId , List<String> list) {
		if(list.size() > 0 ) {
			Map<String,Object> deleteMap = new HashMap<>();
			deleteMap.put("worshipId", worshipId );
			deleteMap.put("list",list);
			
			System.out.println(deleteMap);
			orderDAO.deleteVOList(deleteMap);
			return 1;
		}
		return 0;
	}
	

}
