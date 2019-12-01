package kr.co.ohjooyeo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ohjooyeo.dao.WorshipDAO;

@Service
public class VersionService {
	@Autowired
	WorshipDAO worshipDAO;
	
	@Autowired
	AdvertisementService adService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	MusicService musicService;
	
	public Map<String, Object> getAllData(String id) {
		
		Map<String, Object> result = new HashMap<>();
		
		//날짜기준으로 가장 가까운 업로드된 worship date 구하는 method 필요 
		String worshipDate = worshipDAO.getWorshipDateById(id);
		
		result.put("worship", null);
		result.put("advertisement", null);
		result.put("music", null);
		result.put("worshipDate", worshipDate);

			result.put("worship", orderService.getOrderByWorshipId(id));
			result.put("advertisement", adService.getWorshipAdList(id));
			result.put("music", musicService.getMusicListByWorshipId(id));
		System.out.println(result);
		return result;
	}
	
	/* WorshipControlle.updateWorship에서 버전업데이트 시에 사용 (12/1 회의를 통해 list에서 버전을 가져오기로하였음 )*/
	public String getVersionById(String id) {
		return worshipDAO.getVersionById(id);
	}
	
	public boolean versionUp(int churchId, String worshipId) {
		if(worshipDAO.versionUp(churchId,worshipId) > 0) {
			return true;
		}
		return false;
	}

	public void updateVersion(String worshipId, String version) {
		worshipDAO.getVersionById(worshipId,version);
	}
	
}
