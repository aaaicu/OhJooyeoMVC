package kr.co.ohjooyeo.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import kr.co.ohjooyeo.vo.ChurchVO;

@Repository
public class ChurchDAO {
	@Autowired
	SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(ChurchDAO.class); 
	
	public int setChurchInfo(ChurchVO church) {
		logger.debug(church.toString());
		return sqlSession.insert("church.setChurchInfo", church);
	}

}
