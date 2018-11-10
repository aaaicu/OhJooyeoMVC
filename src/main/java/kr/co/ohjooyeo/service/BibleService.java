package kr.co.ohjooyeo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ohjooyeo.dao.BibleDAO;
import kr.co.ohjooyeo.vo.BibleVO;

@Service
public class BibleService {
	@Autowired
	BibleDAO bibleDAO;
	
	public List<List<Map<String,String>>> getPhrase(String [] phrases){
		List<List<Map<String,String>>> result = new ArrayList<List<Map<String,String>>>();
		for (String phrase : phrases) {
			result.add(getRawPhrase(phrase));
		}
		return result;
	}
	//ex) rawPhrases = 요나서 2:7-2:10/고전 2:1-3:1
	public List<Map<String,String>> getRawPhrase(String rawPhrases){
		List<Map<String,String>> result = new LinkedList<>();
		BibleVO startVO;
		BibleVO endVO;
		String[] phrasesList = rawPhrases.split("/");
		String bookRegex = "^[ㄱ-힣]*";
		String detailRegex = "[0-9]+:[0-9]+";
		Pattern bookPattern = Pattern.compile(bookRegex);
		Pattern detailPattern = Pattern.compile(detailRegex);

		Matcher bookMatch;
		Matcher detailMatch;

		for (int i = 0; i < phrasesList.length; i++) {

			String book = "";
			int startChapter;
			int endChapter;
			int startSection;
			int endSection;
			 
			bookMatch = bookPattern.matcher(phrasesList[i]);
			detailMatch = detailPattern.matcher(phrasesList[i]);

			String detail[] = new String[2];
			while (bookMatch.find()) {
				book = bookMatch.group();
			}
			System.out.println("book : "+book);
			System.out.println("book : "+book);

			int count = 0;
			while (detailMatch.find()) {
				detail[count++] = detailMatch.group();
			}
			// 요청하는 말씀이 범위가 아닌 단일 절일 경우
			// 시작 BibleVO 와 끝 BibleVO가 동일
			startChapter = Integer.parseInt(detail[0].split(":")[0]);
			startSection = Integer.parseInt(detail[0].split(":")[1]);

			if (detail[1] == null) {
				endChapter = Integer.parseInt(detail[0].split(":")[0]);
				endSection = Integer.parseInt(detail[0].split(":")[1]);
			} else {
				endChapter = Integer.parseInt(detail[1].split(":")[0]);
				endSection = Integer.parseInt(detail[1].split(":")[1]);
			}

			startVO = new BibleVO(book, startChapter, startSection);
			endVO = new BibleVO(book, endChapter, endSection);
			
			System.out.println(startVO);
			System.out.println(endVO);

			List<BibleVO> voList = bibleDAO.getPhrase(startVO, endVO);
			
			for(BibleVO vo : voList) {
				Map<String,String> inputMap = new HashMap<>();
				String phrase = getBookAddName(vo.getBook());
				phrase += " "+vo.getChapter()+":"+vo.getSection();
				inputMap.put("phrase",phrase);
				inputMap.put("contents", vo.getContents());
				result.add(inputMap);
			}
		}
		return result;
	}
	
	public List<String> getChapterList(String book) {
		return bibleDAO.getChapterList(book);
	}
	public List<String> getSectionList(String book, String chapter) {
		return bibleDAO.getSectionList(book,chapter);
	}
	
	//성경봉독 순서의 디테일이 변경되어 미사용 
	public String getBookAddName(String bookName) {
		String result = "";
		switch (bookName) {
		case "창세기":
			result = "창";
			break;
		case "출애굽기":
			result = "출";
			break;
		case "레위기":
			result = "레";
			break;
		case "민수기":
			result = "민";
			break;
		case "신명기":
			result = "신";
			break;
		case "여호수아":
			result = "수";
			break;
		case "사사기":
			result = "삿";
			break;
		case "룻기":
			result = "룻";
			break;
		case "사무엘상":
			result = "삼상";
			break;
		case "사무엘하":
			result = "삼하";
			break;
		case "열왕기상":
			result = "왕상";
			break;
		case "열왕기하":
			result = "왕하";
			break;
		case "역대상":
			result = "대상";
			break;
		case "역대하":
			result = "대하";
			break;
		case "에스라":
			result = "스";
			break;
		case "느헤미야":
			result = "느";
			break;
		case "에스더":
			result = "에";
			break;
		case "욥기":
			result = "욥";
			break;
		case "시편":
			result = "시";
			break;
		case "잠언":
			result = "잠";
			break;
		case "전도서":
			result = "전";
			break;
		case "아가":
			result = "아";
			break;
		case "이사야":
			result = "사";
			break;
		case "예레미야":
			result = "렘";
			break;
		case "예레미야애가":
			result = "애";
			break;
		case "에스겔":
			result = "겔";
			break;
		case "다니엘":
			result = "단";
			break;
		case "호세아":
			result = "호";
			break;
		case "요엘":
			result = "욜";
			break;
		case "아모스":
			result = "암";
			break;
		case "오바댜":
			result = "옵";
			break;
		case "요나":
			result = "욘";
			break;
		case "미가":
			result = "미";
			break;
		case "나훔":
			result = "나";
			break;
		case "하박국":
			result = "합";
			break;
		case "스바냐":
			result = "습";
			break;
		case "학개":
			result = "학";
			break;
		case "스가랴":
			result = "슥";
			break;
		case "말라기":
			result = "말";
			break;
		case "마태복음":
			result = "마";
			break;
		case "마가복음":
			result = "막";
			break;
		case "누가복음":
			result = "눅";
			break;
		case "요한복음":
			result = "요";
			break;
		case "사도행전":
			result = "행";
			break;
		case "로마서":
			result = "롬";
			break;
		case "고린도전서":
			result = "고전";
			break;
		case "고린도후서":
			result = "고후";
			break;
		case "갈라디아서":
			result = "갈";
			break;
		case "에베소서":
			result = "엡";
			break;
		case "빌립보서":
			result = "빌";
			break;
		case "골로새서":
			result = "골";
			break;
		case "데살로니가전서":
			result = "살전";
			break;
		case "데살로니가후서":
			result = "살후";
			break;
		case "디모데전서":
			result = "딤전";
			break;
		case "디모데후서":
			result = "딤후";
			break;
		case "디도서":
			result = "딛";
			break;
		case "빌레몬서":
			result = "몬";
			break;
		case "히브리서":
			result = "히";
			break;
		case "야고보서":
			result = "약";
			break;
		case "베드로전서":
			result = "벧전";
			break;
		case "베드로후서":
			result = "벧후";
			break;
		case "요한일서":
			result = "요일";
			break;
		case "요한이서":
			result = "요이";
			break;
		case "요한삼서":
			result = "요삼";
			break;
		case "유다서":
			result = "유";
			break;
		case "요한계시록":
			result = "계";
			break;
		}

		return result;
	}
	
	public String getBookName(String abb) {
		String result = "";
		switch (abb) {
		case "창":
			result = "창세기";
			break;
		case "출":
			result = "출애굽기";
			break;
		case "레":
			result = "레위기";
			break;
		case "민":
			result = "민수기";
			break;
		case "신":
			result = "신명기";
			break;
		case "수":
			result = "여호수아";
			break;
		case "삿":
			result = "사사기";
			break;
		case "룻":
			result = "룻기";
			break;
		case "삼상":
			result = "사무엘상";
			break;
		case "삼하":
			result = "사무엘하";
			break;
		case "왕상":
			result = "열왕기상";
			break;
		case "왕하":
			result = "열왕기하";
			break;
		case "대상":
			result = "역대상";
			break;
		case "대하":
			result = "역대하";
			break;
		case "스":
			result = "에스라";
			break;
		case "느":
			result = "느헤미야";
			break;
		case "에":
			result = "에스더";
			break;
		case "욥":
			result = "욥기";
			break;
		case "시":
			result = "시편";
			break;
		case "잠":
			result = "잠언";
			break;
		case "전":
			result = "전도서";
			break;
		case "아":
			result = "아가";
			break;
		case "사":
			result = "이사야";
			break;
		case "렘":
			result = "예레미야";
			break;
		case "애":
			result = "예레미야애가";
			break;
		case "겔":
			result = "에스겔";
			break;
		case "단":
			result = "다니엘";
			break;
		case "호":
			result = "호세아";
			break;
		case "욜":
			result = "요엘";
			break;
		case "암":
			result = "아모스";
			break;
		case "옵":
			result = "오바댜";
			break;
		case "욘":
			result = "요나";
			break;
		case "미":
			result = "미가";
			break;
		case "나":
			result = "나훔";
			break;
		case "합":
			result = "하박국";
			break;
		case "습":
			result = "스바냐";
			break;
		case "학":
			result = "학개";
			break;
		case "슥":
			result = "스가랴";
			break;
		case "말":
			result = "말라기";
			break;
		case "마":
			result = "마태복음";
			break;
		case "막":
			result = "마가복음";
			break;
		case "눅":
			result = "누가복음";
			break;
		case "요":
			result = "요한복음";
			break;
		case "행":
			result = "사도행전";
			break;
		case "롬":
			result = "로마서";
			break;
		case "고전":
			result = "고린도전서";
			break;
		case "고후":
			result = "고린도후서";
			break;
		case "갈":
			result = "갈라디아서";
			break;
		case "엡":
			result = "에베소서";
			break;
		case "빌":
			result = "빌립보서";
			break;
		case "골":
			result = "골로새서";
			break;
		case "살전":
			result = "데살로니가전서";
			break;
		case "살후":
			result = "데살로니가후서";
			break;
		case "딤전":
			result = "디모데전서";
			break;
		case "딤후":
			result = "디모데후서";
			break;
		case "딛":
			result = "디도서";
			break;
		case "몬":
			result = "빌레몬서";
			break;
		case "히":
			result = "히브리서";
			break;
		case "약":
			result = "야고보서";
			break;
		case "벧전":
			result = "베드로전서";
			break;
		case "벧후":
			result = "베드로후서";
			break;
		case "요일":
			result = "요한일서";
			break;
		case "요이":
			result = "요한이서";
			break;
		case "요삼":
			result = "요한삼서";
			break;
		case "유":
			result = "유다서";
			break;
		case "계":
			result = "요한계시록";
			break;
		}

		return result;
	}


}
