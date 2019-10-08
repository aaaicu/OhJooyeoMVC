<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.js"></script>
<title>예배 불러오기</title>
</head>
<body>
	<%-- <div>
		<input type="text" name="" value="" >
		<br>
	</div> --%>
	<div>
		<table border = 1>
		<thead>
			<tr>
				<th>순서</th>
				<th width = "50px">예배</th>
			</tr>
		</thead>
		<tbody id = "addArea">

		</tbody>
		</table>
	</div>
	<script type="text/javascript">
	function getContextPath() {
		return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
	}

	$.ajax({
		url : getContextPath()+"/getWorshipIdList",
		type : "post",
		contentType : "application/json",
		data : "admin",
		dataType : "json",
		success : function(worshipIdList) {
			console.log(worshipIdList);
			for ( var i = 0 ; i < worshipIdList.length ; i++) {
				$("#addArea").append('<tr><td>'+(i+1)+'</td><td>'+worshipIdList[i]+'</td></tr>');
			}
		},
		error : function(XHR, status, error) {
		console.error(status + " : " + error);
		}
		});
	</script>
</body>
</html>
