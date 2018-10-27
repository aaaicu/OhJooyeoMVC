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
	
	public Map<String, Object> compareVersion(String id, String userVersion) {
		
		Map<String, Object> result = new HashMap<>();		
		String currentVersion = getVersionById(id);
		
		//날짜기준으로 가장 가까운 업로드된 worship date 구하는 method 필요 
		String worshipDate = worshipDAO.getWorshipDateById(id);
		
		result.put("worship", null);
		result.put("advertisement", null);
		result.put("music", null);
		result.put("currentVersion", currentVersion);
		result.put("worshipDate", worshipDate);

		if (userVersion.equals("***")) {
			result.put("worship", orderService.getOrderByWorshipId(id));
			result.put("advertisement", adService.getAdsByWorshipId(id));
			result.put("music", musicService.getMusicListByWorshipId(id));
		} else {
			char userOrderVer = userVersion.charAt(0);
			char userAdVer = userVersion.charAt(1);
			char userMusicVer = userVersion.charAt(2);
			
			char orderVer = currentVersion.charAt(0);
			char adVer = currentVersion.charAt(1);
			char musicVer = currentVersion.charAt(2);
			
			if(userOrderVer != orderVer) {
				result.put("worship", orderService.getOrderByWorshipId(id));
			}
			if(userAdVer != adVer) {
				result.put("advertisement", adService.getAdsByWorshipId(id));
			}
			if(userMusicVer != musicVer) {
				result.put("music", musicService.getMusicListByWorshipId(id));
			}
		}
		System.out.println(result);
		return result;
	}
	
	public String getVersionById(String id) {
		return worshipDAO.getVersionById(id);
	}
	
	/* a-z(97-122) A-Z() */
	/* index = 0 : orderVersion / 1 : advertiseVersion / 2 : musicVersrion  */
	public String versionUp(String currentVersion, int index) {
		String result = null;
		if ( currentVersion.length() != 3) {
			System.out.println("input error!");
		} else {
			int tempInt = (char)(currentVersion.charAt(index) + 1);
			if (tempInt == 123) {
				tempInt = 65;
			} else if (tempInt == 91) {
				// 다시 a 로 돌아가게 했지만 다른 전략이나 에러 처리 필요 
				tempInt = 97;
				System.out.println("index error!");
			} 
			char tempChar = (char)(tempInt);
			
			result = currentVersion.substring(0, index)+tempChar+currentVersion.substring(index+1);
		}
		
		return result;
	}

	public void updateVersion(String worshipId, String version) {
		worshipDAO.getVersionById(worshipId,version);
	}
}
