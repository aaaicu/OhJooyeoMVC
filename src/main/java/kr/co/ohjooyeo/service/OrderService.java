package kr.co.ohjooyeo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ohjooyeo.dao.BibleDAO;
import kr.co.ohjooyeo.dao.OrderDAO;
import kr.co.ohjooyeo.vo.WorshipOrderVO;
import kr.co.ohjooyeo.vo.WorshipVO;

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
//	public void setWorshipOrder(String worshipId,String [] types,String [] titles,String [] details,String [] presenters) {
//		List<WorshipOrderVO> orderList = new ArrayList<>();
//		for(int i = 0 ; i < titles.length ; i ++) {
//			orderList.add(new WorshipOrderVO(worshipId,i,i,types[i], titles[i],details[i], presenters[i] ));
//		}
//		orderDAO.insertVOList(orderList);
//	}

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
	
	/* 순서추가 */
	public boolean addWorshipOrder(int churchId, String worshipId , List<Map<String,Object>> list) {
		if(list.size() > 0 ) {

			List<WorshipOrderVO > orderVOList = new ArrayList<>();
			for( Map<String, Object> o : list ) {
				 
				WorshipOrderVO vo = new WorshipOrderVO();
				vo.setChurchId(churchId);
				vo.setWorshipId(worshipId);
				vo.setType((int)o.get("type"));
				vo.setStandupYn((int)o.get("standupYn"));
				vo.setTitle((String)o.get("title"));
				vo.setDetail((String)o.get("detail"));
				vo.setPresenter((String)o.get("presenter"));
				vo.setOrder((int)o.get("order"));
				vo.setOrderId((int)o.get("orderId"));
				orderVOList.add(vo);
			}
			
			if (orderDAO.insertVOList(orderVOList)  < 1) {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	/* 순서수정 */
	public boolean updateWorshipOrder(int churchId, String worshipId , List<Map<String,Object>> list) {
		if(list.size() > 0 ) {

			List<WorshipOrderVO > orderVOList = new ArrayList<>();
			for( Map<String, Object> o : list ) {
				 
				WorshipOrderVO vo = new WorshipOrderVO();
				vo.setChurchId(churchId);
				vo.setWorshipId(worshipId);
				vo.setType((int)o.get("type"));
				vo.setStandupYn((int)o.get("standupYn"));
				vo.setTitle((String)o.get("title"));
				vo.setDetail((String)o.get("detail"));
				vo.setPresenter((String)o.get("presenter"));
				vo.setOrder((int)o.get("order"));
				vo.setOrderId((int)o.get("orderId"));
				orderVOList.add(vo);
				System.out.println(vo);
			}
			
			if (orderDAO.updateVOList(orderVOList)  < 1) {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	/* 순서삭제 */
	public boolean delete(String worshipId , List<String> list) {
		if(list.size() > 0 ) {
			Map<String,Object> deleteMap = new HashMap<>();
			deleteMap.put("worshipId", worshipId );
			deleteMap.put("list",list);
			
			System.out.println(deleteMap);
			orderDAO.deleteVOList(deleteMap);
			return true;
		}else {
			return false;
		}
	}

	public int deleteWorshipOrder(Map<String,String> map) {
		return orderDAO.deleteWorshipOrder(map);
	}
}
