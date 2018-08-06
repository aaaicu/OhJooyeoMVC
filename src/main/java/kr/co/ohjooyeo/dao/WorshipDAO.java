package kr.co.ohjooyeo.dao;

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
	
	public String getVersion(String date) {
		return sqlSession.selectOne("worship.getVersion", date);
	}
}
