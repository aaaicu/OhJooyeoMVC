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
		<table width = "500px">
			<tr>
				<td width = "200px">입력</td>
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
				<td>
					예배순서
				</td>
				<td>
					<input type = "button" id = "plus" value = "순서추가">
				</td>
			</tr>
			<tr>
			<td colspan = "2">
					<ul id="order-list">
					</ul>
			</td>
			</tr>
			<tr>
				<td>
					광고
				</td>
				<td>
					<input type = "button" id = "plusAd" value = "광고추가">
				</td>
			</tr>
			<tr>
			<td colspan = "2">
					<ul id="ad-list">
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
		liId = 0 ;
		
		/* 추가 html */
		/* 추가되는 html의 orederId는 음수를 사용 */
		addStr = "";
		addStr += "<li>";
		addStr += "<table>";
		addStr += "<tr>";
		addStr += "<td><input type='hidden' name='orderId' value ='-1'>"
		addStr += "<input type='hidden' name='updateYN' value ='0'>"
		addStr += "<input type='hidden' name='order' value ='-1'><select class = 'type-select'  name='type'>";
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
		
		$("#plus").on("click", function() {
			console.log("click plus");
			render();
		});
		
		ad = "";
		ad += "<li>";
		ad += "<table>";
		ad += "<tr>";
		ad += "<td><input type='hidden' name='orderId' value ='-1'>"
		ad += "<input type='hidden' name='updateYN' value ='0'>"
		ad += "<input type='hidden' name='adOrder' value ='-1'></td>";
		ad += "<td><input type='text' name='adTitle'></td>";
		ad += "<td><textarea name='adContent' style='margin: 0px; width: 330px; height: 90px; resize: none;'></textarea></td>";
		ad += "<td><input type = 'button' class = 'ad-plus-before' value = '앞에추가'></td>";
		ad += "<td class = 'del'>x</td>";
		ad += "</tr>";
		ad += "</table>";
		ad += "</li>";
		
		$("#plusAd").on("click", function() {
			console.log("plusAd");
			renderAd();
		});
		
		/* 리스트 추가 구현 */
		function render() {
			liId -=1;
			var str =  $(addStr).attr("id",liId);
			$("#order-list").append(str);
		}
		
		/* 리스트 추가 구현 */
		function renderAd() {
			liId -=1;
			var str =  $(ad).attr("id","ad"+liId);
			$("#ad-list").append(str);
		}
		
	});

	$("table").on("click", ".del", function() {
		var $this = $(this);
		$this.closest("li").remove();
	});


	
	/* 앞에추가 클릭시 입력항목 추가 함수 호출 */
	$("#order-list").on("click", ".plus-before",function(){
		liId -=1;
		var str = $(addStr).attr("id",liId);
		$(this).closest("li").before(str);
	})
	
	/* 앞에추가 클릭시 입력항목 추가 함수 호출 */
	$("#ad-list").on("click", ".ad-plus-before",function(){
		liId -=1;
		var str = $(ad).attr("id",liId);
		$(this).closest("li").before(str);
	})
	
	
	/* type 변경시 "1"일 경우 detail input box 비활성화
	   다른 타입으로 변경시 readonly 풀림
	*/
	$("ul").on("change", ".type-select" , function () {
		var $this = $(this);
		var detailTag = $($this.closest("li")).find("[name='detail']");
		if( $this.val() == "1"){
			detailTag.val("");
			detailTag.attr("readonly","readonly");
			detailTag.attr("size","15");
			detailTag.after("<input type='button' class='searchBible' value = '찾기'>");
		}else {
			detailTag.removeAttr("readonly");
			if(detailTag.siblings("[class='searchBible']").length == 1){
				detailTag.siblings("[class='searchBible']").remove();
				detailTag.removeAttr("size");
			}
		}
	})
	
	/* 찾기 버튼 구현 */
	$("ul").on("click",".searchBible",function () {
		openWin = window.open("${pageContext.request.contextPath}/search-bible", "search-bible", "width=500, height=400, toolbar=no, menubar=no, scrollbars=no, resizable=no" );  
		$this = $(this);
		initValue = $this.siblings()[0].value.split("/");
		
		$(openWin).on("load", function(){
			openWin.document.getElementById("targetId").value = $this.closest("li")[0].id;
			if($this.siblings()[0].value != null && $this.siblings()[0].value != ""){	
				for( var i = 0 ; i < initValue.length ; i ++){
					tag = "<tr><td class = 'range'>"+initValue[i]+"</td><td class = 'del'><input type = 'button' del = 'del-button' value = '삭제'></td></tr>";
					$(openWin.document).find("#addArea").append($(tag));				
				}
			}
		});
	});
</script>
<%-- <script src="<c:url value="/js/insert-worship.js" />"></script> --%>
</html>