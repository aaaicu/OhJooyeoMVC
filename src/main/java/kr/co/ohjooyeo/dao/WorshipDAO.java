package kr.co.ohjooyeo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WorshipDAO {
	@Autowired
	SqlSession sqlSession;
	
	public String getWorshipId(String date) {
		return sqlSession.selectOne("worship.getWorshipId", date);
	}
	public Map<String, String> getWorship(String worshipId) {
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
}
