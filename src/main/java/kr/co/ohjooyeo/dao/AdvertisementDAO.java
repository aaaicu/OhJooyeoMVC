package kr.co.ohjooyeo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ohjooyeo.vo.WorshipAdVO;

@Repository
public class AdvertisementDAO {
	@Autowired
	SqlSession sqlSession; 

	public List<WorshipAdVO> getWorshipAdList(String worshipId) {
		return sqlSession.selectList("advertisement.getAdsList",worshipId);
	}

	public void insertVOList(List<WorshipAdVO> list) {		
		System.out.println("추가 광고 내용 : "+list);
		sqlSession.insert("advertisement.insertVOList",list);
	}
	
	public void updateVOList(List<WorshipAdVO> list) {
		System.out.println("업데이트 광고 내용 : "+list);
		sqlSession.update("advertisement.updateVOList",list);
	}

	public void deleteVOList(Map<String, Object> map) {
		sqlSession.delete("advertisement.deleteVOList", map);
	}

	public void deleteAll(String worshipId) {
		sqlSession.delete("advertisement.deleteAll", worshipId);
	}
	
}
