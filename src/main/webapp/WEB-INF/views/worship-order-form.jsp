<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.js"></script>
<title>Worship</title>
</head>
<body>
	<%-- <%@include file="nav.jsp" %> --%>
	<form id = "order-form" action="${pageContext.request.contextPath}/add-worship-order"
		method="post">
		Worship ID : <select id="select-box" name="worship_id">

		</select> </br>
		<ul id="form-list"></ul>
		<br /> <br /> <input type="submit" value="확인">
	</form>

	<%-- <%@include file="footer.jsp" %> --%>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		console.log(document.querySelector('#form-list').childNodes);
		$(".plus").on("click", function() {
			console.log("click plus");
			render("down");

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
				getOrders(worshipIdList[worshipIdList.length-1]);
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
				var str ="" ;
		 		for (var i = 0 ; i < worshipOrderList.length; i++ ) {
		 			str += "<li calss = 'worship-order'>";
		 			str += "<table>";
		 			str += "<tr>";
		 			str += "<td><input type='hidden' name='orderId' value ='"+worshipOrderList[i].orderId+"'><select name='type' >";
		 			str += "<option value = '0'";
			 			if (worshipOrderList[worshipOrderList.length-1].type == '0'){
			 				str += "selected = 'selected'";
			 			}
		 			str += ">일반순서</option>";
		 			str += "<option value = '1'";
			 			if (worshipOrderList[worshipOrderList.length-1].type == '1'){
			 				str += "selected = 'selected'";
			 			}
		 			str += ">성경봉독</option>";
		 			str += "<option value = '2'";
			 			if (worshipOrderList[worshipOrderList.length-1].type == '2'){
			 				str += "selected = 'selected'";
			 			}
		 			str += ">찬양</option>";
		 			str += "</select></td>";
		 			str += "<td><input type='text' name='title' value ='"+worshipOrderList[i].title+"'></td>";
		 			str += "<td><input type='text' name='detail' value ='"+worshipOrderList[i].detail+"'></td>";
		 			str += "<td><input type='text' name='presenter' value ='"+worshipOrderList[i].presenter+"'></td>";
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
		$this.closest("li").remove();
	});

	
	
	function renderWorshipId(option){
		var str = "";
		str += "<option>"+option+"</option>"
		$("#select-box").prepend(str);
	}
	
	function render() {
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
		str += "<td class = 'del'>x</td>";
		str += "</tr>";
		str += "</table>";
		str += "</li>";
		$("#form-list").append(str);
	}
</script>

</html>