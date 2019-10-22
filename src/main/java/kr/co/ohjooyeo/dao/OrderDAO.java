package kr.co.ohjooyeo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ohjooyeo.vo.WorshipOrderVO;

@Repository
public class OrderDAO {
	private static final Logger logger = LoggerFactory.getLogger(OrderDAO.class);
	@Autowired
	SqlSession sqlSession;
	
	public Map<String,String> getOrder(String worshipId, String findOrder) {
		Map<String,String> inputMap = new HashMap<>();
		inputMap.put("worshipId", worshipId);
		inputMap.put("findOrder", findOrder);
		return sqlSession.selectOne("order.getOrder", inputMap);
	}

	public List<Map<String, String>> getWorshipOrder(String churchId,String worshipId) {
		Map<String,String> inputMap = new HashMap<>();
		inputMap.put("worshipId", worshipId);
		inputMap.put("churchId", churchId);
		return sqlSession.selectList("order.getWorshipOrder", inputMap);
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

	public int insertVOList(List<Map<String,Object>> list) {
		return sqlSession.insert("order.insertVOList", list);
	}

	public void updateVOList(List<Map<String,Object>> list) {
		logger.debug(list.toString());
		sqlSession.update("order.updateVOList", list);
	}

	public void deleteVOList(Map<String,Object> map) {
		sqlSession.delete("order.deleteVOList", map);
	}

	public void deleteAll(String worshipId) {
		sqlSession.delete("order.deleteAll", worshipId);
	}

	
}
