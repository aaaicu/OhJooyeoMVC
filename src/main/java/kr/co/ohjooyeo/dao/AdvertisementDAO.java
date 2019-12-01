package kr.co.ohjooyeo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ohjooyeo.vo.WorshipAdVO;

@Repository
public class AdvertisementDAO {
	@Autowired
	SqlSession sqlSession; 

	private static final Logger logger = LoggerFactory.getLogger(AdvertisementDAO.class);
	
	public List<WorshipAdVO> getWorshipAdList(String worshipId) {
		return sqlSession.selectList("advertisement.getAdsList",worshipId);
	}

	public void insertVOList(List<WorshipAdVO >  list) {		
		logger.debug("추가 광고 내용 : "+list);
		sqlSession.insert("advertisement.insertVOList",list);
	}
	
	public int updateVOList(List<WorshipAdVO > list) {
		return sqlSession.update("advertisement.updateVOList",list);
	}

	public void deleteVOList(Map<String, Object> map) {
		sqlSession.delete("advertisement.deleteVOList", map);
	}

	public int deleteWorshipAd(Map<String,String> map) {
		return sqlSession.delete("advertisement.deleteWorshipAd", map);
	}
	
}
