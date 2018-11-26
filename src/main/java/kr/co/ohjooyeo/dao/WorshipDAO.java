package kr.co.ohjooyeo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ohjooyeo.vo.WorshipVO;

@Repository
public class WorshipDAO {
	@Autowired
	SqlSession sqlSession;
	
	public String getWorshipId(String date) {
		return sqlSession.selectOne("worship.getWorshipId", date);
	}
	public Map<String, String> getWorshipInfo(String worshipId) {
		return sqlSession.selectOne("worship.getWorshipMap", worshipId);
	}
	
	public String getVersionById(String id) {
		return sqlSession.selectOne("worship.getVersionById", id);
	}
	public List<Map<String, String>> getWorshipList() {
		return sqlSession.selectList("worship.getWorshipList");
	}
	public String getWorshipDateById(String id) {
		return sqlSession.selectOne("worship.getWorshipDateById",id);
	}
	public void insertWorship(WorshipVO worshipVO) {
		sqlSession.insert("worship.insertWorship",worshipVO);
	}
	public List<String> getWorshipIdList(String userId) {
		return sqlSession.selectList("worship.getWorshipIdList",userId);
	}
	public void getVersionById(String worshipId, String version) {
		Map<String, String > inputMap = new HashMap<>();
		inputMap.put("worshipId",  worshipId);
		inputMap.put("version", version);
		sqlSession.selectList("worship.setVersion",inputMap);
	}
}
