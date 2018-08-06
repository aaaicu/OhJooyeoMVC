package kr.co.ohjooyeo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ohjooyeo.dao.AdvertisementDAO;
import kr.co.ohjooyeo.dao.WorshipDAO;

@Service
public class AdvertisementService {
	@Autowired
	WorshipDAO worshipDAO;
	
	@Autowired
	AdvertisementDAO advertisementDAO;
	
	public Map<String, Object> getAdsByDate(String date) {
		String worshipId = worshipDAO.getWorshipId(date);
		Map<String,Object> result = new HashMap<>();
		List<Map<String,String>> adsList = advertisementDAO.getAdsList(worshipId);
		result.put("advertisementList", adsList);
		
		return result;
	}

}
