<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.js"></script>
<title>Update Worship</title>
</head>
<body>
	<%-- <%@include file="nav.jsp" %> --%>
	<form id = "order-form" action="${pageContext.request.contextPath}/update-worship-order"
		method="post">
		<div>
		Worship ID : <select id="select-box" name="worship_id">
		</select> <span><input type = "button" id = "add-button" class = "plus" value = "순서추가"></span><br/>
		</div>
		<ul id="form-list"></ul>

		<br /> <br /> <input type="button" id = "update-orders" value="확인">
	</form>
	<%-- <%@include file="footer.jsp" %> --%>
</body>
<script type="text/javascript">

addOrderId = 0;
	$(document).ready(function() {
		
		/* 초기 세팅 */
		$.ajax({
			url : "${pageContext.request.contextPath }/getWorshipIdList",
			type : "post",
			contentType : "application/json",
			data : "admin",
			dataType : "json",
			success : function(worshipIdList){
				for(var i = 0; i <worshipIdList.length; i++){
					renderWorshipId(worshipIdList[i]);
				}
				if(worshipIdList.length > 0 ) {
					getOrders(worshipIdList[0]);
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});  
	}); // document ready end
	
	
	/* DB에 저장된 순서 가져오기 */
	function getOrders (value) {
		$.ajax({
			url : "${pageContext.request.contextPath}/getWorshipOrderList",
			type : "post",
			contentType : "application/json",
			data : value,
			dataType : "json",
			success : function(worshipOrderList){
				// 전송리스트 선언
				memory = new Map();
				addOrderId = 0;
				deleteList = [];
				
				// table head 필요시 입력 
				var str ="" ;
		 		for (var i = 0 ; i < worshipOrderList.length; i++ ) {
		 			str += "<li id = '"+worshipOrderList[i].orderId+"'>";
		 			str += "<table>";
		 			str += "<tr>";
		 			str += "<td><input type='hidden' name='orderId' value ='"+worshipOrderList[i].orderId+"'>"
		 			str += "<input type='hidden' name='order' value ='"+worshipOrderList[i].order+"'>"
		 			str += "<input type='hidden' name='updateYN' value ='0'>"
		 			str += "<select name='type' class ='chkTarget' >";
		 			str += "<option value = '0'";
			 			if (worshipOrderList[i].type == '0'){
			 				str += "selected = 'selected'";
			 			}
		 			str += ">일반순서</option>";
		 			str += "<option value = '1'";
			 			if (worshipOrderList[i].type == '1'){
			 				str += "selected = 'selected'";
			 			}
		 			str += ">성경봉독</option>";
		 			str += "<option value = '2'";
			 			if (worshipOrderList[i].type == '2'){
			 				str += "selected = 'selected'";
			 			}
		 			str += ">찬양</option>";
		 			str += "</select></td>";
		 			str += "<td><input type='text' class ='chkTarget' name='title' value ='"+worshipOrderList[i].title+"'></td>";
		 			str += "<td><input type='text' class ='chkTarget' name='detail' value ='"+worshipOrderList[i].detail+"'></td>";
		 			str += "<td><input type='text' class ='chkTarget' name='presenter' value ='"+worshipOrderList[i].presenter+"'></td>";
		 			str += "<td><input type = 'button' class = 'plus-before' value = '앞에추가'></td>";
		 			str += "<td class = 'del'>x</td>";
		 			str += "</tr>";
		 			str += "</table>";
		 			str += "</li>";
		 		}
		 			$("#form-list").append(str);
			},
			error : function (XHR, status, error) {
				console.error(status + " : " + error)
			}
		})
	}

	/* 추가 html */
	/* 추가되는 html의 orederId는 음수를 사용 */
	addStr = "";
	addStr += "<li>";
	addStr += "<table>";
	addStr += "<tr>";
	
	addStr += "<td><input type='hidden' name='orderId' value ='-1'>"
	addStr += "<input type='hidden' name='updateYN' value ='0'>"
	addStr += "<input type='hidden' name='order' value ='-1'><select name='type'>";
	addStr += "<option value = '0'>일반순서</option>";
	addStr += "<option value = '1'>성경봉독</option>";
	addStr += "<option value = '2'>찬양</option>";
	addStr += "</select></td>";
	addStr += "<td><input type='text' name='title'></td>";
	addStr += "<td><input type='text' name='detail'></td>";
	addStr += "<td><input type='text' name='presenter'></td>";
	addStr += "<td><input type = 'button' class = 'plus-before' value = '앞에추가'></td>";
	addStr += "<td class = 'del'>x</td>";
	addStr += "</tr>";
	addStr += "</table>";
	addStr += "</li>";
	

	/* 리스트 추가 구현 */
	function render() {
		var str = $(addStr).attr("id","-1");
		$("#form-list").append(str);
	}
	
	/* 앞에추가 클릭시 입력항목 추가 함수 호출 */
	$("#form-list").on("click", ".plus-before",function(){
		var str = $(addStr).attr("id","-1");
		$(this).closest("li").before(str);
		
	})
	
	/* 순서추가 클릭시 입력항목 추가 함수 호출 */
	$(".plus").on("click", function() { 
		render();
	});	
	
	/* 삭제 버튼 클릭 구현 */
	$("#form-list").on("click", ".del", function() {
		var $this = $(this);
		thisOrderId = $($this.closest("li")[0]).attr("id");
		if(parseInt(thisOrderId)>=0){	
			deleteList.push(thisOrderId);
		}
		console.log(deleteList);
		$this.closest("li").remove();
	});

	/* 요소찾아서 삭제 (고유할때만 사용) */
	function removeKey (list,key){
		var index = list.indexOf(key);
		if ( index === -1 ) {
			return ;
		} else {
			list.splice(index,1);
		}
		console.log(list);
	}
	
	/* 셀렉트박스 값 DB 참조 */
	function renderWorshipId(option){
		var str = "";
		str += "<option>"+option+"</option>"
		$("#select-box").prepend($(str));
	}
	
	/* 셀렉트박스 변경시 순서 재호출 */
	$("#select-box").change(function() {
				/* console.log(document.querySelector('#form-list').childNodes); */
				$("#form-list").children().remove();
				getOrders($(this).val());
	});
	
	/* 포커스되는 순간을 변경의 시작으로 보고 변경전 값을 Map(:memory)에 추가 */
	$("ul").on("focus", ".chkTarget",function() { 
		var $this = $(this);
		
		var id = $this.closest("li")[0].id
		var name = $this.attr("name")
		var contents = $this.val();

		/* 처음 포커스되는 태그의 경우 처리 로직 */
		if (memory.get(id) == undefined ){
			memory.set(id,{});
		} 
		if(!memory.get(id).hasOwnProperty(name)){
			memory.get(id)[name] = contents;
		}
	});	
	
		/* 변경이 일어난 경우 처음 값에서 바뀌었는지 비교 후 바뀌었다면 chkInputBox의 값을 "1"로 변경 */
		/* 한번 업데이트 대상으로 지정되면 (chkInputBox에서 "1"로 처리되면) 원래값으로 변경했다 하더라도 "0" 로 돌아가지는 않음 (10/4 협의)*/
	$("ul").on("change", ".chkTarget",function() { 
		var $this = $(this);
		
		var id = $this.closest("li")[0].id
		var name = $this.attr("name")
		var contents = $this.val();
		
		
		console.log("변경이벤트 발생 : " , memory);
		console.log("원본 데이터 : " , memory.get(id)[name] );
		console.log("원본 데이터와 비교 : " , memory.get(id)[name] == contents );
		
		chkInputBox = $($this.parent().siblings()[0]).find("[name='updateYN']");
		if(memory.get(id)[name] != contents && chkInputBox.val() == "0"  ){
			chkInputBox.val("1");
			// 업데이트 리스트 input value 변경 
		}
	});

	
	/* 저장 버튼 구현 */
	$("#update-orders").on("click", function () {
		
		/* order update 처리 */
		liTagList = $("#form-list").children("li");
		for(var i = 0 ; i < liTagList.length ; i ++ ){
			hiddenInput = $($("#form-list").children("li")[i]);
			orderValue = $(hiddenInput.find("[name='order']")[0]).val();
			
			
			if ( i.toString() != orderValue ){
				if(orderValue != "-1"){
					hiddenInput.find("[name='updateYN']").val("1");
				}
				$(hiddenInput.find("[name='order']")[0]).val(i);
			}
		}
		
		/* ajax 전송 */
		values = $("#order-form").serialize();
		
		paramObject = { deleteList : deleteList,
				values : values }
		
		$.ajax({
			url : "${pageContext.request.contextPath }/updateTarget",
			type : "post",
			contentType : "application/json; charset=UTF-8",
			dataType : "text",
			data : JSON.stringify(paramObject),
			success : function(data){
				$("#form-list").children().remove();
				getOrders($("#select-box").val());
				alert("수정되었습니다.")
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});  
	})
	
</script>

</html>