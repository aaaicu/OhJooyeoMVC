<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<form action="${pageContext.request.contextPath}/add-worship-order"
		method="post">
		<b>Worship_id : </b> <input type="text" name="worship_id"></br>
		</br>
		<ul id="form-list">
			<li>
				<table id="form-head">
					<thead>
						<tr>
							<th>title</th>
							<th>detail</th>
							<th>presenter</th>
							<th class="plus">+</th>
						</tr>
					</thead>
				</table>
			</li>
		</ul>
		<br /> <br /> <input type="submit" value="확인">
	</form>

	<%-- <%@include file="footer.jsp" %> --%>
</body>
<script type="text/javascript">
	$(document).ready(function() {

		$(".plus").on("click", function() {
			console.log("click plus");
			render("down");

		});
	});

	$("#form-list").on("click", ".del", function() {
		var $this = $(this);
		$this.closest("li").remove();
	});

	function render(updown) {
		var str = "";
		str += " <li>";
		str += "		<table>";
		str += "			<tr>";
		str += "				<td><input type='text' name='title'></td>";
		str += "				<td><input type='text' name='detail'></td>";
		str += "				<td><input type='text' name='presenter'></td>";
		str += "				<td class = 'del'>x</td>";
		str += "			</tr>";
		str += "		</table>";
		str += "</li>";
		console.log("태그 추가")
		if (updown == "up") {
			$("#form-list").prepend(str);
		} else if (updown == "down") {
			$("#form-list").append(str);

		} else {
			console.log("오류")
		}
	}
</script>

</html>