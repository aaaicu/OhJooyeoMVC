<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.js"></script>
<link rel="stylesheet" href="<c:url value='/css/order-manage.css' />">


<title>Update Worship</title>
</head>
<body>
	<%-- <%@include file="nav.jsp" %> --%>
	<%-- <p><c:url value="/js/insert-worship.js" /> </p> --%>
	<%-- <%@include file="nav.jsp" %> --%>

	<div id = "worshipArea">
	<form id = "worshipForm">
	<div>
		<span>
		Worship ID : <select id="selectWorshipId" name="selectWorshipId">
		</select>
		</span>
		<span>
		<input type = "button" value = "삭제" id = "deleteWorship">
		</span>
	</div>
	<ul id="worshipInfo">
		<li id = "ws0">
			<table>
				<tr>
					<td>
					<input type='hidden' name='worshipUpdateYN' value ='0'>
					Worship Date
					</td>
					<td><input type="text" class ='chkTarget' name="worshipDate"></td>
				</tr>
				<tr>
					<td>Main Presenter</td>
					<td><input type="text" class ='chkTarget' name="mainPresenter"></td>
				</tr>
				<tr>
					<td>Next Presenter</td>
					<td><input type="text" class ='chkTarget' name="nextPresenter"></td>
				</tr>
				<tr>
					<td>Next Prayer</td>
					<td><input type="text" class ='chkTarget' name="nextPrayer"></td>
				</tr>
				<tr>
					<td>Next Offer</td>
					<td><input type="text" class ='chkTarget' name="nextOffer"></td>
				</tr>
			</table>
		</li>
	</ul>
	</form>
	</div>
	<div id = "renderArea">
		<div id = "orderArea">	
			<form id = "orderForm">
			예배순서 <span><input type = "button" name = "order" class = "addHtml" value = "순서추가"></span><br/>
			<ul id="orderList"></ul>
			</form>
		</div>
		<div id = "adArea">	
			<form id = "adForm">
			광고 <span><input type = "button" name = "ad" class = "addHtml" value = "광고추가"></span><br/>
			<ul id="adList"></ul>
			</form>
		</div>
	</div>

	<br/>
	<br/> 
	<input type="button" id = "updateButton" value="확인">
	<%-- <%@include file="footer.jsp" %> --%>
<script src="<c:url value="/js/insert-worship.js" />" ></script>
<script src="<c:url value="/js/order-manage.js" />" ></script>
</body>

<script type ="text/javascript">
$(document).ready(function(){
	
	updateWorshipInit();
	init();
});


function updateWorshipInit(){
	/* 예배ID 리스트 비동기식으로 조회 */
	$.ajax({
		url : "${pageContext.request.contextPath }/getWorshipIdList",
		type : "post",
		contentType : "application/json",
		data : "admin",
		dataType : "json",
		success : function(worshipIdList){
			$("#selectWorshipId").children().remove();
			for(var i = 0; i <worshipIdList.length; i++){
				$("#selectWorshipId").prepend("<option>"+worshipIdList[i]+"</option>")
			}
			if(worshipIdList.length > 0 ) {
				getWorshipInfo(worshipIdList[0]);
				getWorshipDetailList(worshipIdList[0],"order");
				getWorshipDetailList(worshipIdList[0],"ad");
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});  

}

function getWorshipInfo(worshipId){
	console.log("함수 시작");
	console.log(worshipId);
	/* $.ajax({
		url : "${pageContext.request.contextPath}/getWorshipInfo",
		type : "post",
		contentType : "application/json",
		data : worshipId,
		dataType : "json",
		success : function (data) {
			resolve(data);
			console.log("성공");
		},
		error : function(err) {
			console.log(err);
		}
	}) */
	return new Promise(function(resolve, reject){
		$.ajax({
			url : "${pageContext.request.contextPath}/getWorshipInfo",
			type : "post",
			contentType : "application/json",
			data : worshipId,
			dataType : "json",
			success : function (data) {
				console.log(data);
				resolve(data);
			},
			error : function(err) {
				reject("에러ㅎㅎㅎ");
			}
		})
		}).then(function (vo) {
			$("[name=worshipUpdateYN]").val("0");
			$("[name=worshipDate]").val(vo.worshipDate);
			$("[name=mainPresenter]").val(vo.mainPresenter);
			$("[name=nextPresenter]").val(vo.nextPresenter);
			$("[name=nextPrayer]").val(vo.nextPrayer);
			$("[name=nextOffer]").val(vo.nextOffer);
		});
}

/* value : li에 들어갈 VO, 
 *listType : order / ad 
 */
function getWorshipDetailList (value,listType) {
	 var area ;
	 var urlOption ;
	 if (listType === "order"){
		 area = "#orderList";
		 urlOption = "getWorshipOrderList";
	 }else if(listType === "ad"){
		 area = "#adList";
		 urlOption = "getWorshipAdList";
	 };
	 
	return new Promise(function(resolve, reject){
		$.ajax({
			url : "${pageContext.request.contextPath}/"+urlOption,
			type : "post",
			contentType : "application/json",
			data : value,
			dataType : "json",
			success : function (data) {
				resolve(data);
			},
			error : function(err) {
				reject();
			}
		})
		}).then(function (vo) {
			for(var j = 0 ; j <  vo.length ; j++){
				var html = templateFactory(listType,vo[j]);
				render(area,html,"append")
			}
			var rowList = document.querySelectorAll('.row');
			[].forEach.call(rowList,addHandlers)
			console.log("rowList",rowList);
		});
}

/* 예배ID 변경시 순서 재호출 */
$("#selectWorshipId").change(function() {
	console.log("변경발생");
	$("#orderList").children().remove();
	$("#adList").children().remove();
	getWorshipInfo($(this).val());
	getWorshipDetailList($(this).val(),"order"); 
	getWorshipDetailList($(this).val(),"ad"); 
	
});

$("#deleteWorship").on("click",function(){

	$.ajax({
		url : "${pageContext.request.contextPath }/deleteWorship",
		type : "post",
		contentType : "application/json",
		dataType : "text",
		data : $("#selectWorshipId").val(),
		success : function(){
			
			$("#orderList").children().remove();
			$("#adList").children().remove();
			updateWorshipInit();
			init();
			alert("삭제되었습니다.");
		},
		
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});  
	
})

/* 예배ID 변경시 순서 재호출 */
$("#updateButton").on("click",function() {
	console.log("클릭");
	worshipForm = $("#worshipForm").serialize();
	orderForm = $("#orderForm").serialize();
	adForm = $("#adForm").serialize();
	
	paramObject = { 
			removeOrderList : removeOrderList,
			removeAdList : removeAdList,
			worship : worshipForm,
			order : orderForm,
			ad : adForm }
	
	console.log(paramObject);
	
	$.ajax({
		url : "${pageContext.request.contextPath }/updateWorship",
		type : "post",
		contentType : "application/json",
		dataType : "text",
		data : JSON.stringify(paramObject),
		success : function(){

			console.log("업데이트발생");
			console.log($("#selectWorshipId"));
			$("#orderList").children().remove();
			$("#adList").children().remove();
			getWorshipInfo($("#selectWorshipId").val());
			getWorshipDetailList($("#selectWorshipId").val(),"order");
			getWorshipDetailList($("#selectWorshipId").val(),"ad");
			alert("수정되었습니다.")
		},
		
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});  
	
});
</script>

</html>