package kr.co.ohjooyeo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ohjooyeo.vo.WorshipOrderVO;

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
	public List<WorshipOrderVO> getWorshipOrderList(String worshipId) {
		return sqlSession.selectList("order.getWorshipOrderVOList", worshipId);
	}

	public String getLaunchPhrase(String userId, String date) {
		Map<String,String> inputMap = new HashMap<>();
		inputMap.put("userId", userId);
		inputMap.put("date", date);
		System.out.println(inputMap);
		return sqlSession.selectOne("order.getLaunchPhrase", inputMap);
	}

	public void insertVOList(List<WorshipOrderVO> list) {
		sqlSession.insert("order.insertVOList", list);
	}

	public void updateVOList(List<WorshipOrderVO> list) {
		sqlSession.update("order.updateVOList", list);
	}

	public void deleteVOList(Map<String,Object> map) {
		sqlSession.delete("order.deleteVOList", map);
	}

	
}
