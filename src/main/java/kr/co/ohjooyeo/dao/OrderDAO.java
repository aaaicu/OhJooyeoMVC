package kr.co.ohjooyeo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAO {
	@Autowired
	SqlSession sqlSession;
	
	public Map<String,String> getOrder(String worshipId, String findOrder) {
		Map<String,String> inputMap = new HashMap<>();
		inputMap.put("worshipId", worshipId);
		inputMap.put("findOrder", findOrder);
		return sqlSession.selectOne("order.getOrder", inputMap);
	}

	public List<Map<String, String>> getAllOrder(String worshipId) {
		return sqlSession.selectList("order.getAllOrder", worshipId);
	}

	public String getLaunchPhrase(String userId, String date) {
		Map<String,String> inputMap = new HashMap<>();
		inputMap.put("userId", userId);
		inputMap.put("date", date);
		System.out.println(inputMap);
		return sqlSession.selectOne("order.getLaunchPhrase", inputMap);
	}
	
}
