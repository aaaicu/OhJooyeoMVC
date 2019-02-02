<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="worshipArea">
				<div style="width: 270px;">
					<span> <select id="selectWorshipId" class="custom-select" name="selectWorshipId">
					</select>
					</span> <span> <input type="button" class="btn btn-primary btn-sm" value="삭제" id="deleteWorship">
					</span>
				</div>
				<br />
	<div class="card border-dark mb-1" style="max-width: 40rem;">
		<div class="card-header">
			<h4 class="card-title">예배정보</h4>
		</div>
		<div class="card-body">

			<form id="worshipForm">

				<ul id="worshipInfo">
					<li id="ws0">
						<table>
							<tr>
								<td><input type='hidden' name='worshipUpdateYN' value='0'>
									<h5 class="text-muted">예배 일자</h5></td>
								<td><input type="text" class='chkTarget form-control form-control-sm' name="worshipDate"></td>
							</tr>
							<tr>
								<td>
									<h5 class="text-muted">사회자</h5>
								</td>
								<td><input type="text" class='chkTarget form-control form-control-sm' name="mainPresenter"></td>
							</tr>
							<tr>
								<td><h5 class="text-muted">다음 사회자</h5></td>
								<td><input type="text" class='chkTarget form-control form-control-sm' name="nextPresenter"></td>
							</tr>
							<tr>
								<td><h5 class="text-muted">다음 기도자</h5></td>
								<td><input type="text" class='chkTarget form-control form-control-sm' name="nextPrayer"></td>
							</tr>
							<tr>
								<td><h5 class="text-muted">다음 봉헌</h5></td>
								<td><input type="text" class='chkTarget form-control form-control-sm' name="nextOffer"></td>
							</tr>
						</table>
					</li>
				</ul>
			</form>

		</div>
	</div>

</div>
<br />

<div class="card border-dark mb-3" style="max-width: 40rem;">
  <div class="card-header"><h4 class="card-title">순서 <input type="button" name="order" class="addHtml btn btn-info btn-sm" value="추가"></h4></div>
  <div class="card-body">
  
  
  
  <div class="alert alert-dismissible alert-secondary">
  <button type="button" class="close" data-dismiss="alert">&times;</button>
<select class="custom-select">

<option>일반순서</option>
</select>
<br/>
<br/>
      <div class="form-group">
      <label for="exampleInputEmail1"><strong>순서명</strong></label>
      <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="순서명을 입력해주세요">
      <small id="emailHelp" class="form-text text-muted">필수입력사항입니다.</small>
       
      <label for="exampleInputEmail2"><strong>상세 내용</strong></label>
      <input type="email" class="form-control" id="exampleInputEmail2" aria-describedby="emailHelp" placeholder="상세내용을 입력해주세요">
      
      <label for="exampleInputEmail3"><strong>진행자</strong></label>
      <input type="email" class="form-control" id="exampleInputEmail3" aria-describedby="emailHelp" placeholder="진행자 입력해주세요">
      <small id="emailHelp" class="form-text text-muted">필수입력사항입니다.</small>
    </div>
  
</div>

<div class="alert alert-dismissible alert-secondary">
  <button type="button" class="close" data-dismiss="alert">&times;</button>
  <div>
	  <div><strong>일반순서</strong></div>
	  <div style = "font-size: 20px;">&nbsp;&nbsp;성경봉독 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  창세기 1:1
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  박재현
	  </div>
	  <div></div>
	  <div></div>
  </div>
</div>


<div class="alert alert-dismissible alert-secondary">
  <button type="button" class="close" data-dismiss="alert">&times;</button>
  <strong>Well done!</strong> You successfully read <a href="#" class="alert-link">this important alert message</a>.
</div>
  
  
  
  <div id="renderArea">
	<div id="orderArea">
		<form id="orderForm">
			<ul id="orderList"></ul>
		</form>
	</div>
	<br />
	<div id="adArea">
		<form id="adForm">
			<h2>
				광고 <input type="button" name="ad" class="addHtml btn btn-info btn-sm" value="광고추가">
			</h2>
			<ul id="adList"></ul>
		</form>
	</div>
</div>
  </div>
</div>





<br />
<br />
<input type="button" id="updateButton" value="확인">
<%-- <%@include file="footer.jsp" %> --%>
<script src="${pageContext.request.contextPath}/js/insert-worship.js"></script>
<script src="${pageContext.request.contextPath}/js/order-manage.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		updateWorshipInit();
		init();
	});

	function updateWorshipInit() {
		/* 예배ID 리스트 비동기식으로 조회 */
		$.ajax({
			url : "${pageContext.request.contextPath }/getWorshipIdList",
			type : "post",
			contentType : "application/json",
			data : "admin",
			dataType : "json",
			success : function(worshipIdList) {
				$("#selectWorshipId").children().remove();
				for (var i = 0; i < worshipIdList.length; i++) {
					$("#selectWorshipId").prepend(
							"<option>" + worshipIdList[i] + "</option>")
				}
				if (worshipIdList.length > 0) {
					getWorshipInfo(worshipIdList[0]);
					getWorshipDetailList(worshipIdList[0], "order");
					getWorshipDetailList(worshipIdList[0], "ad");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	}

	function getWorshipInfo(worshipId) {
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
		return new Promise(function(resolve, reject) {
			$.ajax({
				url : "${pageContext.request.contextPath}/getWorshipInfo",
				type : "post",
				contentType : "application/json",
				data : worshipId,
				dataType : "json",
				success : function(data) {
					console.log(data);
					resolve(data);
				},
				error : function(err) {
					reject("에러ㅎㅎㅎ");
				}
			})
		}).then(function(vo) {
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
	function getWorshipDetailList(value, listType) {
		var area;
		var urlOption;
		if (listType === "order") {
			area = "#orderList";
			urlOption = "getWorshipOrderList";
		} else if (listType === "ad") {
			area = "#adList";
			urlOption = "getWorshipAdList";
		}
		;

		return new Promise(function(resolve, reject) {
			$.ajax({
				url : "${pageContext.request.contextPath}/" + urlOption,
				type : "post",
				contentType : "application/json",
				data : value,
				dataType : "json",
				success : function(data) {
					resolve(data);
				},
				error : function(err) {
					reject();
				}
			})
		}).then(function(vo) {
			for (var j = 0; j < vo.length; j++) {
				var html = templateFactory(listType, vo[j]);
				render(area, html, "append")
			}
			var rowList = document.querySelectorAll('.row');
			[].forEach.call(rowList, addHandlers)
			console.log("rowList", rowList);
		});
	}

	/* 예배ID 변경시 순서 재호출 */
	$("#selectWorshipId").change(function() {
		console.log("변경발생");
		$("#orderList").children().remove();
		$("#adList").children().remove();
		getWorshipInfo($(this).val());
		getWorshipDetailList($(this).val(), "order");
		getWorshipDetailList($(this).val(), "ad");

	});

	$("#deleteWorship").on("click", function() {

		$.ajax({
			url : "${pageContext.request.contextPath }/deleteWorship",
			type : "post",
			contentType : "application/json",
			dataType : "text",
			data : $("#selectWorshipId").val(),
			success : function() {

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
	$("#updateButton").on("click", function() {
		console.log("클릭");
		worshipForm = $("#worshipForm").serialize();
		orderForm = $("#orderForm").serialize();
		adForm = $("#adForm").serialize();

		paramObject = {
			removeOrderList : removeOrderList,
			removeAdList : removeAdList,
			worship : worshipForm,
			order : orderForm,
			ad : adForm
		}

		console.log(paramObject);

		$.ajax({
			url : "${pageContext.request.contextPath }/updateWorship",
			type : "post",
			contentType : "application/json",
			dataType : "text",
			data : JSON.stringify(paramObject),
			success : function() {

				console.log("업데이트발생");
				console.log($("#selectWorshipId"));
				$("#orderList").children().remove();
				$("#adList").children().remove();
				getWorshipInfo($("#selectWorshipId").val());
				getWorshipDetailList($("#selectWorshipId").val(), "order");
				getWorshipDetailList($("#selectWorshipId").val(), "ad");
				alert("수정되었습니다.")
			},

			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	});
</script>