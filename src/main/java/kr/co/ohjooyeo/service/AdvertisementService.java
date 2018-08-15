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
	
	public List<Map<String,String>> getAdsById(String id) {
		List<Map<String,String>> result = advertisementDAO.getAdsList(id);
		
		return result;
	}

}
