package kr.co.ohjooyeo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ohjooyeo.vo.BibleVO;

@Repository
public class BibleDAO {
	@Autowired 
	private SqlSession sqlSession;

	public List<BibleVO> getPhrase(BibleVO startVO, BibleVO endVO) {
		Map<String, Object> input = new HashMap<>();
		// Book이 다른 경우는 하나의 요청요소로 받지 않음 : null 반환
		if (startVO.getBook().equals(endVO.getBook())) {
			
			input.put("book", startVO.getBook());

			// Chapter 가 다른 경우는 별도의 계산이 필요함
			if (startVO.getChapter() != endVO.getChapter()) {
				
				input.put("startChapter", startVO.getChapter());
				input.put("endChapter", endVO.getChapter());
				
				input.put("startSection", startVO.getSection());
				input.put("endSection", endVO.getSection());
				
				return sqlSession.selectList("bible.getPhrase",input);
				
			//Chapter 가 같은 경우는 절에 대해서 between 절에 대해서만 요청
			} else {
				input.put("chapter", startVO.getChapter());
				input.put("startSection", startVO.getSection());
				input.put("endSection", endVO.getSection());
				return sqlSession.selectList("bible.getPhraseInChapter",input);
			}

		} else {
			return null;
		}
		
	}
}
