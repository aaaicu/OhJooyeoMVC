package kr.co.ohjooyeo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ohjooyeo.dao.WorshipDAO;
import kr.co.ohjooyeo.vo.WorshipVO;

@Service
public class WorshipService {
	@Autowired
	WorshipDAO worshipDAO;
	
	public List<Map<String, String>> getWorshipList() {
		return worshipDAO.getWorshipList();
	}

	public void addWorship(WorshipVO worshipVO) {
		worshipDAO.insertWorship(worshipVO);
	}
	
}
