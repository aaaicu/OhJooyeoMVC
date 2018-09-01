package kr.co.ohjooyeo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ohjooyeo.dao.MusicDAO;

@Service
public class MusicService {
	@Autowired
	MusicDAO musicDAO;
	public List<Map<String,String>> getMusicListByWorshipId(String id) {
		List<Map<String,String>> result = musicDAO.getMusicListByWorshipId(id);
		return result;
	}
	
}
