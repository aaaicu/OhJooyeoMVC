package kr.co.ohjooyeo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ohjooyeo.dao.AdvertisementDAO;
import kr.co.ohjooyeo.dao.WorshipDAO;
import kr.co.ohjooyeo.vo.WorshipAdVO;
import kr.co.ohjooyeo.vo.WorshipOrderVO;

@Service
public class AdvertisementService {
	@Autowired
	WorshipDAO worshipDAO;
	
	@Autowired
	AdvertisementDAO advertisementDAO;
	
	/* 광고추가 */
//	public void setWorshipAd(String worshipId, String[] titles, String[] contents) {
//		List<WorshipAdVO> adList = new ArrayList<>();
//		for(int i = 0 ; i < titles.length ; i ++) {
//			adList.add(new WorshipAdVO(worshipId,i,i, titles[i],contents[i]));
//		}
//		advertisementDAO.insertVOList(adList);
//	}

	public Map<String,Object> getWorshipAd(String churchId,String worshipId, Map<String,Object> info) {
		info.put("worshipAd", advertisementDAO.getWorshipAd(churchId,worshipId));
		return info;
	}
	
	public boolean addWorshipAd(int churchId, String worshipId, List<Map<String,Object>>  list ) {
		if(list.size() > 0 ) {
			List<WorshipAdVO > adVOList = new ArrayList<>();
			for( Map<String, Object> o : list ) {
				 
				WorshipAdVO vo = new WorshipAdVO();
				vo.setChurchId(churchId);
				vo.setWorshipId(worshipId);
				
				vo.setAdId((int)o.get("adId"));
				vo.setTitle((String)o.get("title"));
				vo.setContent((String)o.get("content"));
				vo.setOrder((int)o.get("order"));
				adVOList.add(vo);
			}
			
			advertisementDAO.insertVOList(adVOList);
			
			
			return true;
		} else {
			return false;
		}
	}
	public boolean updateWorshipAd(int churchId, String worshipId,List<Map<String,Object>>  list ) {
		
		Map<String, String> map = new HashMap<String,String>();
		map.put("churchId",churchId+"");
		map.put("worshipId",worshipId);
		deleteWorshipAd(map);
		
		// TODO : 일괄 삭제 성공도 리턴에 반영될 수 있도록 수정 필요
		//순서 일괄 추가
		return addWorshipAd(churchId,worshipId,list);
//		if(list.size() > 0 ) {
//			List<WorshipAdVO > adVOList = new ArrayList<>();
//			for( Map<String, Object> o : list ) {
//				 
//				WorshipAdVO vo = new WorshipAdVO();
//				vo.setChurchId(churchId);
//				vo.setWorshipId(worshipId);
//				
//				vo.setAdId((int)o.get("adId"));
//				vo.setTitle((String)o.get("title"));
//				vo.setContent((String)o.get("content"));
//				vo.setOrder((int)o.get("order"));
//				adVOList.add(vo);
//			}
//			if (advertisementDAO.updateVOList(adVOList)  < 1) {
//				return false;
//			}
//			return true;
//		} else {
//			return false;
//		}
	}

	public boolean delete(String worshipId, List<String> list) {
		if(list.size() > 0 ) {
			Map<String,Object> deleteMap = new HashMap<>();
			deleteMap.put("worshipId", worshipId );
			deleteMap.put("list",list);
			
			System.out.println(deleteMap);
			advertisementDAO.deleteVOList(deleteMap);
			return true;
		} else {
			return false;
		}
	}

	public int deleteWorshipAd(Map<String,String> map) {
		return advertisementDAO.deleteWorshipAd(map);
	}

}
