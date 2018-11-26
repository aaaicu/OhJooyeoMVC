package kr.co.ohjooyeo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ohjooyeo.vo.AdvertisementVO;
import kr.co.ohjooyeo.vo.WorshipAdVO;

@Repository
public class AdvertisementDAO {
	@Autowired
	SqlSession sqlSession; 

	public void insertVOList(List<WorshipAdVO> adList) {		
		sqlSession.insert("advertisement.insertVOList",adList);
	}

	public List<AdvertisementVO> getWorshipAdList(String worshipId) {
		System.out.println(sqlSession.selectList("advertisement.getAdsList",worshipId));
		return sqlSession.selectList("advertisement.getAdsList",worshipId);
	}
}
