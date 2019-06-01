package kr.co.ohjooyeo.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
	private static final Logger logger = LoggerFactory.getLogger(OrderDAO.class);
	@Autowired
	SqlSession sqlSession;
	
	//사용하지않음
//	public boolean loginCheck(Map<String,String> map) {
//		logger.debug(map.toString());
//		return sqlSession.selectOne("user.loginCheck", map);
//	}

	public String getSecurityPass(Map<String, String> map) {
		logger.debug(map.toString());
		return sqlSession.selectOne("user.getSecurityPass", map);
	}

}
