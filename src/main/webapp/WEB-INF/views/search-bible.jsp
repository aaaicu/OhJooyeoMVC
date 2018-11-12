<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.js"></script>
<title>성경등록</title>
</head>
<body>
	<%-- <%@include file="nav.jsp" %> --%>
	<div>
		<table border = 1 width = "350px">
		<thead>
			<tr>
				<th>범위</th>
				<th width = "50px">삭제</th>
			</tr>
		</thead>
		<tbody id = "addArea">
		
		</tbody>	
		</table>
	</div>
	<br>
	<div>
		<span> 
			<input type = "hidden" id = "targetId" value = "">
			<select size="5" id="select-book" name="book">
				<optgroup label = "=== 성경 ===">
					<option value="갈라디아서">갈라디아서</option>
					<option value="고린도전서">고린도전서</option>
					<option value="고린도후서">고린도후서</option>
					<option value="골로새서">골로새서</option>
					<option value="나훔">나훔</option>
					<option value="누가복음">누가복음</option>
					<option value="느헤미야">느헤미야</option>
					<option value="다니엘">다니엘</option>
					<option value="데살로니가전서">데살로니가전서</option>
					<option value="데살로니가후서">데살로니가후서</option>
					<option value="디도서">디도서</option>
					<option value="디모데전서">디모데전서</option>
					<option value="디모데후서">디모데후서</option>
					<option value="레위기">레위기</option>
					<option value="로마서">로마서</option>
					<option value="룻기">룻기</option>
					<option value="마가복음">마가복음</option>
					<option value="마태복음">마태복음</option>
					<option value="말라기">말라기</option>
					<option value="미가">미가</option>
					<option value="민수기">민수기</option>
					<option value="베드로전서">베드로전서</option>
					<option value="베드로후서">베드로후서</option>
					<option value="빌레몬서">빌레몬서</option>
					<option value="빌립보서">빌립보서</option>
					<option value="사도행전">사도행전</option>
					<option value="사무엘상">사무엘상</option>
					<option value="사무엘하">사무엘하</option>
					<option value="사사기">사사기</option>
					<option value="스가랴">스가랴</option>
					<option value="스바냐">스바냐</option>
					<option value="시편">시편</option>
					<option value="신명기">신명기</option>
					<option value="아가">아가</option>
					<option value="아모스">아모스</option>
					<option value="야고보서">야고보서</option>
					<option value="에베소서">에베소서</option>
					<option value="에스겔">에스겔</option>
					<option value="에스더">에스더</option>
					<option value="에스라">에스라</option>
					<option value="여호수아">여호수아</option>
					<option value="역대상">역대상</option>
					<option value="역대하">역대하</option>
					<option value="열왕기상">열왕기상</option>
					<option value="열왕기하">열왕기하</option>
					<option value="예레미야">예레미야</option>
					<option value="예레미야애가">예레미야애가</option>
					<option value="오바댜">오바댜</option>
					<option value="요나">요나</option>
					<option value="요엘">요엘</option>
					<option value="요한계시록">요한계시록</option>
					<option value="요한복음">요한복음</option>
					<option value="요한삼서">요한삼서</option>
					<option value="요한이서">요한이서</option>
					<option value="요한일서">요한일서</option>
					<option value="욥기">욥기</option>
					<option value="유다서">유다서</option>
					<option value="이사야">이사야</option>
					<option value="잠언">잠언</option>
					<option value="전도서">전도서</option>
					<option value="창세기">창세기</option>
					<option value="출애굽기">출애굽기</option>
					<option value="하박국">하박국</option>
					<option value="학개">학개</option>
					<option value="호세아">호세아</option>
					<option value="히브리서">히브리서</option>
				</optgroup>
			</select>
			<select id="select-start-chapter" size = "5" name="chapter">
				<optgroup id="start-chapter" label = "=== 장 ===">
				</optgroup>
			</select>
	
			<select id="select-start-section"  size = "5"  name="section">
				<optgroup id="start-section" label = "=== 절 ===">
				</optgroup>
			</select>
		</span>
		 ~
		<span>
			<select id="select-end-chapter" size = "5" name="chapter">
				<optgroup id="end-chapter" label = "=== 장 ===">
				</optgroup>
			</select>
	
			<select id="select-end-section"  size = "5"  name="section">
				<optgroup id="end-section" label = "=== 절 ===">
				</optgroup>
			</select>
		</span>
		<span><input type = "button" id = "addList" value ="추가"/></span>
	</div>
	<div>
	<p id = "content-container">
	미리보기
	</p>
	</div>
	<div>
	<input type = "button" id = "saveBible" value = "저장" >
	<input type = "button" value = "취소" onClick = "window.close()">
	</div>
	<%-- <%@include file="footer.jsp" %> --%>
</body>
<script type="text/javascript">

$(document).ready(function() {
	//최초 부모창에서 데이터 가져와서 파싱하는 로직 추가 필요
	
	
});

chapterList = [];
sectionList = [];

function initialize() {
	$("#select-book").val(null);
	$("#select-start-chapter").val(null);
	$("#select-end-chapter").val(null);
	$("#select-start-section").val(null);
	$("#select-end-section").val(null);
	$("#start-chapter").children().remove();
	$("#start-section").children().remove();
	$("#end-chapter").children().remove();
	$("#end-section").children().remove();
}

/* 추가버튼 클릭 */
$("#addList").on("click",function(){
	var book = $("#select-book").val();
	var startChapter = $("#select-start-chapter").val();
	var endChapter = $("#select-end-chapter").val();
	var startSection = $("#select-start-section").val();
	var endSection = $("#select-end-section").val();
	var tag = "";

	
	if(startSection !== null) {
		
		if((endChapter === null || endSection === null) || ( startChapter === endChapter && startSection === endSection  )){
			tag = "<tr><td class = 'range'>"+book +" "+ startChapter +":"+ startSection
			+"</td><td class = 'del'><input type = 'button' del = 'del-button' value = '삭제'></td></tr>"
		} else {
			tag = "<tr><td class = 'range'>"+book +" "+ startChapter +":"+ startSection
			+"-"+ endChapter +":"
			+ endSection +"</td><td class = 'del'><input type = 'button' del = 'del-button' value = '삭제'></td></tr>";
		}
		
		$("#addArea").append(tag);
		initialize();
		
	} else {
		alert("범위를 확인해주세요");
		
	}
	
});

/* 삭제버튼 클릭 */
$("table").on("click",".del",function(){
	$this = $(this);
	$this.parents().closest("tr").remove();
});

/* 저장버튼 클릭 */
$("#saveBible").on("click",function(){
	var findList = $("#addArea").find("[class='range']")
	var saveText = "";
	var targetId = $("#targetId").val();

	for(var i = 0 ; i < findList.length ; i++){
		if( i != 0){
			saveText +="/";
		}
		saveText +=findList[i].innerText;
	}
	$(opener.document).find("[id='"+targetId+"']").find("[name='detail']").val(saveText);
	$(opener.document).find("[id='"+targetId+"']").find("[name='updateYN']").val("1");
	window.close(); 
});

$("#select-book").change(function(){

	$("#content-container").text("미리보기");
	
	$("#start-chapter").children().remove();
	$("#start-section").children().remove();
	
	$("#end-chapter").children().remove();
	$("#end-section").children().remove();
	
  	$.ajax({
		url : "${pageContext.request.contextPath}/getChapterList" ,
		type : "post",
		data : $("#select-book").val(),
		contentType : "application/json" ,
		dataType : "json",
		success : function (data){
			optionTag = "";
			chapterList = data;
			for ( var i = 0 ; i < data.length; i++){
				optionTag += "<option value = '"+data[i]+"'>"+data[i]+"</option>";
			}
			$("#start-chapter").append(optionTag);
		}
	});
});

$("#select-start-chapter").change(function(){
	$("#start-section").children().remove();
	
	$("#end-chapter").children().remove();
	$("#end-section").children().remove();
	
	inputData = {
			book : $("#select-book").val(),
			chapter : $("#select-start-chapter").val()
	}
	
  	$.ajax({
		url : "${pageContext.request.contextPath}/getSectionList" ,
		type : "post",
		data : JSON.stringify(inputData),
		contentType : "application/json" ,
		dataType : "json",
		success : function (data){
			optionTag = "";
			sectionList = data;
			for ( var i = 0 ; i < data.length; i++){
				optionTag += "<option value = '"+data[i]+"'>"+data[i]+"</option>";
			}
			$("#start-section").append(optionTag);
		}
	});
});

$("#select-start-section").change(function() {
	
	$("#end-chapter").children().remove();
	$("#end-section").children().remove();
	
	inputData.book = $("#select-book").val();
	inputData.chapter = $("#select-start-chapter").val();
	inputData.section = $("#select-start-section").val();
	
	
///*  "성경 a:b(~성경 c:d)(/성경 a:b(~성경 c:d))" [String] */
	requestPhrase = {
			"phraseRange" : [$("#select-book").val() +" "+ $("#select-start-chapter").val() +":"+ $("#select-start-section").val()]
	};
	
	
	optionTag = "";
	for ( var i = 0 ; i < chapterList.length; i++){
		if ( inputData.chapter <= chapterList[i]){		
			optionTag += "<option value = '"+chapterList[i]+"'>"+chapterList[i]+"</option>";
		}
	}
	$("#end-chapter").append(optionTag);
	
	$.ajax({
		url : "${pageContext.request.contextPath}/phrase",
		type : "post",
		data : JSON.stringify(requestPhrase),
		contentType : "application/json",
		dataType : "json",
		success : function(data) {
			$("#content-container").text("");
			for(var i = 0 ; i < data.length[0] ; i++ ){
				$("#content-container").append(data[0][i].phrase+" "+ data[0][i].contents);				
			}
		}
	});
});

$("#select-end-chapter").change(function() {
	optionTag = "";
	$("#end-section").children().remove();
	
	for ( var i = 0 ; i < sectionList.length; i++){
		if ( $("#select-start-chapter").val() == $("#select-end-chapter").val()){
			if (  parseInt($("#select-start-section").val()) <= parseInt(sectionList[i])){		
				optionTag += "<option value = '"+sectionList[i]+"'>"+sectionList[i]+"</option>";
			}
		}else {
			optionTag += "<option value = '"+sectionList[i]+"'>"+sectionList[i]+"</option>";
		}
	}
	$("#end-section").append(optionTag);
});


$("#select-end-section").change(function() {
	
	
///*  "성경 a:b(~성경 c:d)(/성경 a:b(~성경 c:d))" [String] */
	requestPhrase = {
			"phraseRange" : [$("#select-book").val() +" "+ $("#select-start-chapter").val() +":"+ $("#select-start-section").val()
			+"-"+ $("#select-end-chapter").val() +":"+ $("#select-end-section").val()]
	};
	
	console.log(requestPhrase);
	
	$.ajax({
		url : "${pageContext.request.contextPath}/phrase",
		type : "post",
		data : JSON.stringify(requestPhrase),
		contentType : "application/json",
		dataType : "json",
		success : function(data) {
			$("#content-container").text("");
			for(var i = 0 ; i < data[0].length ; i++ ){
				$("#content-container").append(data[0][i].phrase+" "+ data[0][i].contents + "<br>");				
			}
		}
	});
});
	
	
</script>

</html>