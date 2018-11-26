package kr.co.ohjooyeo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ohjooyeo.dao.AdvertisementDAO;
import kr.co.ohjooyeo.dao.WorshipDAO;
import kr.co.ohjooyeo.vo.AdvertisementVO;
import kr.co.ohjooyeo.vo.WorshipAdVO;

@Service
public class AdvertisementService {
	@Autowired
	WorshipDAO worshipDAO;
	
	@Autowired
	AdvertisementDAO advertisementDAO;
	
	/* 광고추가 */
	public void setWorshipAd(String worshipId, String[] titles, String[] contents) {
		List<WorshipAdVO> adList = new ArrayList<>();
		for(int i = 0 ; i < titles.length ; i ++) {
			adList.add(new WorshipAdVO(worshipId,i,i, titles[i],contents[i]));
		}
		advertisementDAO.insertVOList(adList);
	}

	public List<AdvertisementVO> getWorshipAdList(String worshipId) {
		return advertisementDAO.getWorshipAdList(worshipId);
	}

}
