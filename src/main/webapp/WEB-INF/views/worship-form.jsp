<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.js"></script>
<title>Add Worship</title>
</head>
<body>
	<%-- <%@include file="nav.jsp" %> --%>
	<form action="${pageContext.request.contextPath}/add-worship"
		method="post">
		<table>
			<tr>
				<td>입력</td>
			</tr>
			<tr>
				<td>Worship ID</td>
				<td><input type="text" name="worshipId"></td>
			</tr>
			<tr>
				<td>Worship Date</td>
				<td><input type="text" name="worshipDate"></td>
			</tr>
			<tr>
				<td>Main Presenter</td>
				<td><input type="text" name="mainPresenter"></td>
			</tr>
			<tr>
				<td>Next Presenter</td>
				<td><input type="text" name="nextPresenter"></td>
			</tr>
			<tr>
				<td>Next Prayer</td>
				<td><input type="text" name="nextPrayer"></td>
			</tr>
			<tr>
				<td>Next Offer</td>
				<td><input type="text" name="nextOffer"></td>
			</tr>

			<tr>
				<td colspan=2>
					<ul id="form-list">
						<li>
							<table id="form-head">
								<thead>
									<tr>
										<th>type</th>
										<th>title</th>
										<th>detail</th>
										<th>presenter</th>
										<th id="plus">+</th>
									</tr>
								</thead>
							</table>
						</li>
					</ul>
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="등록"></td>
			</tr>
		</table>
	</form>

	<%-- <%@include file="footer.jsp" %> --%>
</body>

<script type="text/javascript">
	$(document).ready(function() {

		$("#plus").on("click", function() {
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
		str += "				<td><input type='text' name='type'></td>";
		str += "				<td><input type='text' name='title'></td>";
		str += "				<td><input type='text' name='detail'></td>";
		str += "				<td><input type='text' name='presenter'></td>";
		str += "				<td class = 'del'>x</td>";
		str += "			</tr>";
		str += "		</table>";
		str += "</li>";
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