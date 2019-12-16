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
	public Map<String, String> getWorshipInfo(String churchId, String worshipId){
		Map<String,String> inputMap = new HashMap<>();
		inputMap.put("worshipId", worshipId);
		inputMap.put("churchId", churchId);
		return sqlSession.selectOne("worship.getWorshipMap", inputMap);
	}
	
	public List<Map<String, String>> getWorshipList(String churchId) {
		return sqlSession.selectList("worship.getWorshipIdList",churchId);
	}
	
	public String getVersionById(String id) {
		return sqlSession.selectOne("worship.getVersionById", id);
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
	
	public String getLastWorshipId(int churchId) {
		return sqlSession.selectOne("worship.getLastWorshipId", churchId);
	}
	
	public void getVersionById(String worshipId, String version) {
		Map<String, String > inputMap = new HashMap<>();
		inputMap.put("worshipId",  worshipId);
		inputMap.put("version", version);
		sqlSession.selectList("worship.setVersion",inputMap);
	}
	public int updateWorship(WorshipVO worship) {
		return sqlSession.update("worship.updateWorship",worship);
	}
	public int deleteWorship(Map<String,String> map) {
		return sqlSession.delete("worship.deleteWorship",map);
	}
	public int versionUp(int churchId, String worshipId) {
		Map<String, Object > inputMap = new HashMap<>();
		inputMap.put("churchId",  churchId);
		inputMap.put("worshipId", worshipId);
		System.out.println(inputMap);
		return sqlSession.update("worship.versionUp",inputMap);
	}
}
