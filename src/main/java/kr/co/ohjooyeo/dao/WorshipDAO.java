package kr.co.ohjooyeo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ohjooyeo.vo.WorshipVO;

@Repository
public class WorshipDAO {
	private static Logger log = LoggerFactory.getLogger(WorshipDAO.class);
	
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
	public int insertWorship(WorshipVO worshipVO) {
		return sqlSession.insert("worship.insertWorship",worshipVO);
	}
	
	public List<String> getWorshipIdList(String churchId) {
		return sqlSession.selectList("worship.getWorshipIdList",churchId);
	}
	
	public String getLastWorshipId(String userId) {
		log.debug("'" + sqlSession.selectOne("worship.getLastWorshipId", userId).toString()+"'");
		return sqlSession.selectOne("worship.getLastWorshipId", userId);
	}
	
	public void getVersionById(String worshipId, String version) {
		Map<String, String > inputMap = new HashMap<>();
		inputMap.put("worshipId",  worshipId);
		inputMap.put("version", version);
		sqlSession.selectList("worship.setVersion",inputMap);
	}
	public void updateWorship(Map<String,String> worshipMap) {
		sqlSession.update("worship.updateWorship",worshipMap);
	}
	public void delete(String worshipId) {
		sqlSession.delete("worship.deleteWorship",worshipId);
	}

}
