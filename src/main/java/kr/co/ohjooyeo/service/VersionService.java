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
	
	public Map<String, Object> compareVersion(String date, String userVersion) {
		
		Map<String, Object> result = new HashMap<>();		
		String currentVersion = getVersion(date);
		
		result.put("worship", null);
		result.put("advertisement", null);
		result.put("music", null);
		result.put("currentVersion", currentVersion);

		if (userVersion.equals("***")) {
			result.put("worship", orderService.getOrderByDate(date));
			result.put("advertisement", adService.getAdsByDate(date));
			result.put("music", "");
		} else {
			char userOrderVer = userVersion.charAt(0);
			char userAdVer = userVersion.charAt(1);
			char userMusicVer = userVersion.charAt(2);
			
			char orderVer = currentVersion.charAt(0);
			char adVer = currentVersion.charAt(1);
			char musicVer = currentVersion.charAt(2);
			
			if(userOrderVer != orderVer) {
				result.put("worship", orderService.getOrderByDate(date));
			}
			if(userAdVer != adVer) {
				result.put("advertisement", adService.getAdsByDate(date));
			}
			if(userMusicVer != musicVer) {
				result.put("music", "");
			}
		}
		return result;
	}
	
	public String getVersion(String date) {
		return worshipDAO.getVersion(date);
	}
}
