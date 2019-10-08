package kr.co.ohjooyeo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ohjooyeo.dao.ChurchDAO;
import kr.co.ohjooyeo.vo.ChurchVO;

@Service
public class ChurchService {
	@Autowired
	ChurchDAO churchDao;

	public boolean regChurchInfo(Map<String, String> churchInfoMap) {
		ChurchVO church = new ChurchVO();
		church.setName(churchInfoMap.get("name"));
		church.setAddress(churchInfoMap.get("address"));
		church.setLatitude(Double.parseDouble(churchInfoMap.get("latitude")));
		church.setLongitude(Double.parseDouble(churchInfoMap.get("longitude")));
		church.setDescription(churchInfoMap.get("description"));
		
		if (churchDao.setChurchInfo(church) > 0) {
			return true;
		}else {
			return false;
		}
	}
	

}
