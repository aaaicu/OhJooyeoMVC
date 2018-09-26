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
	<form id = "order-form" action="${pageContext.request.contextPath}/add-worship-order"
		method="post">
		<div>
		Worship ID : <select id="select-box" name="worship_id">
		</select> <span><input type = "button" id = "add-button" class = "plus" value = "순서추가"></span><br/>
		</div>
		<ul id="form-list"></ul>
		<br /> <br /> <input type="submit" value="확인">
	</form>
	<%-- <%@include file="footer.jsp" %> --%>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		
		console.log(document.querySelector('#form-list').childNodes);
		$(".plus").on("click", function() { 
			render(this.value);
		});
		
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
					console.log(worshipIdList[0]);
					getOrders(worshipIdList[0]);
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});  
		
		$("#select-box").change(function() {
					/* console.log(document.querySelector('#form-list').childNodes); */
					$("#form-list").children().remove();
					getOrders($(this).val());
		});
			
	});
	
	function getOrders (value) {
		$.ajax({
			url : "${pageContext.request.contextPath}/getWorshipOrderList",
			type : "post",
			contentType : "application/json",
			data : value,
			dataType : "json",
			success : function(worshipOrderList){
				// table head 필요시 입력 
				deleteList = [];
				var str ="" ;
		 		for (var i = 0 ; i < worshipOrderList.length; i++ ) {
		 			console.log(worshipOrderList[worshipOrderList.length-1].type);
		 			str += "<li calss = 'worship-order'>";
		 			str += "<table>";
		 			str += "<tr>";
		 			str += "<td><input type='hidden' class='orderId' id ='"+worshipOrderList[i].orderId+"'><select name='type' >";
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
		 			str += "<td><input type='text' name='title' value ='"+worshipOrderList[i].title+"'></td>";
		 			str += "<td><input type='text' name='detail' value ='"+worshipOrderList[i].detail+"'></td>";
		 			str += "<td><input type='text' name='presenter' value ='"+worshipOrderList[i].presenter+"'></td>";
		 			str += "<td><input type = 'button' class = 'plus' value = '앞에추가'></td>";
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

	$("#form-list").on("click", ".del", function() {
		var $this = $(this);
		console.log($this.closest("table").find(".orderId")[0].id);
		deleteList.push($this.closest("table").find(".orderId")[0].id);
		console.log(deleteList);
		$this.closest("li").remove();
	});

	
	
	function renderWorshipId(option){
		var str = "";
		str += "<option>"+option+"</option>"
		$("#select-box").prepend(str);
	}
	
	function render(updown) {
		var str = "";
		str += "<li>";
		str += "<table>";
		str += "<tr>";
		str += "<td><select>";
		str += "<option value = '0'>일반순서</option>";
		str += "<option value = '1'>성경봉독</option>";
		str += "<option value = '2'>찬양</option>";
		str += "</select></td>";
		str += "<td><input type='text' name='title'></td>";
		str += "<td><input type='text' name='detail'></td>";
		str += "<td><input type='text' name='presenter'></td>";
		str += "<td><input type = 'button' id = 'add-before-button' value = '앞에추가'></td>";
		str += "<td class = 'del'>x</td>";
		str += "</tr>";
		str += "</table>";
		str += "</li>";
		
		console.log("태그 추가")
		if (updown == "앞에추가") {
			$("#form-list").prepend(str);
		} else if (updown == "순서추가") {
			$("#form-list").append(str);

		} else {
			console.log("오류")
		}

	}
</script>

</html>