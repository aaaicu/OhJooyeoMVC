<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="worshipArea">
	<div style="width: 270px;">
		<span> <select id="selectWorshipId" class="custom-select" name="selectWorshipId">
		</select>
		</span> 
		<span> <input type="button" class="btn btn-danger btn-sm" value="삭제" id="deleteWorship">
		</span>
		<span style = "text-align:right;">
		<input type="button" class="btn btn-success btn-sm" id="updateButton" value="확인">
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

  <!-- 예배순서  -->

<div class="card border-dark mb-3" style="max-width: 40rem;">
	<div class="card-header"><h4 class="card-title">순서</h4></div>
	<div class="card-body">
		<div id ="order-area">
		
		
		</div>
		<div>
			<button name="order" class="add-html btn btn-default" style="width: 100%;">
				<img src="${pageContext.request.contextPath }/img/add.png" height="20" width="20"></img>
			</button>
		</div>
	</div>
</div>


  <!-- 광고  -->

<div class="card border-dark mb-3" style="max-width: 40rem;">
	<div class="card-header"><h4 class="card-title">광고</h4></div>
	<div class="card-body">
		<div id ="ad-area">
		</div>
		<div>
			<button name="ad" class="add-html btn btn-default" style="width: 100%;">
				<img src="${pageContext.request.contextPath }/img/add.png" height="20" width="20"></img>
			</button>
		</div>
	</div>
</div>
<%-- 

<div class="card border-dark mb-3" style="max-width: 40rem;">
  <div class="card-header"><h4 class="card-title"> 샘플순서</h4></div>
  <div class="card-body">


<div class="alert alert-dismissible alert-secondary">
  <button type="button" class="close" data-dismiss="alert">&times;</button>
  <div>
	  <div>
		<select class="custom-select" name="selectType" style="width: 110px;height: 24px;padding-bottom: 2px;padding-top: 0px;padding-left: 10px;">
			<option>성경봉독</option>
		</select>
	  </div>
	  <div class = "order-element">
		  <div class = "col-2 unit">
		   	<input type="text" class="form-control-plaintext" >
		  </div>
		  <div class = "unit-dotted col"><hr size="2" noshade align="center" style="color:#999999;border-style:dashed"> </div>
		  <div class = "unit">
		  	<input readonly type="text" class="form-control-plaintext" >
		  </div>
		  <button type="button" class="btn btn-default">
		  	<img src="${pageContext.request.contextPath }/img/search.png" height="20" width="20"></img>
		  </button>
		  <div class = "unit-dotted col"><hr size="2" noshade align="center" style="color:#999999;border-style:dashed"> </div>
		  <div class = "col-auto unit">
		  <input type="text" class="form-control-plaintext" >
		  </div>
	  </div>
  	  <div style = "text-align : right">
  	 	 <br/>
  		  <input type="button" class="btn btn-primary btn-sm" value="리셋" >
  		  <input type="button" class="btn btn-info btn-sm" value="저장" >
	  </div>
  </div>
</div>

<div class="alert alert-dismissible alert-secondary">
  <button type="button" class="close" data-dismiss="alert">&times;</button>
  <div>
	  <div>
		<select class="custom-select" name="selectType" style="width: 110px;height: 24px;padding-bottom: 2px;padding-top: 0px;padding-left: 10px;">
			<option>일반순서</option>
		</select>
	  </div>
	  <div class = "order-element">
		  <div class = "col-2 unit">
		   	<input type="text" class="form-control-plaintext" >
		  </div>
		  <div class = "unit-dotted col"><hr size="2" noshade align="center" style="color:#999999;border-style:dashed"> </div>
		  <div class = "unit">
		  	<input type="text" class="form-control-plaintext" >
		  </div>
		  <div class = "unit-dotted col"><hr size="2" noshade align="center" style="color:#999999;border-style:dashed"> </div>
		  <div class = "col-auto unit">
		  <input type="text" class="form-control-plaintext" >
		  </div>
	  </div>
	  <div style = "text-align : right">
		  <br/>
  		  <input type="button" class="btn btn-primary btn-sm" value="리셋" >
  		  <input type="button" class="btn btn-info btn-sm" value="저장" >
	  </div>
  </div>
</div>
<div>
  <button class="btn btn-default" style="width: 100%;">
 	 <img src="${pageContext.request.contextPath }/img/add.png" height="20" width="20"></img>
  </button>
</div>
<br/>

  </div>
</div>

 --%>


<!-- Modal -->
<!-- 
<div class="modal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">삭제 확인</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>삭제하시겠습니까?</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal" >Delete</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
 -->

<%-- <%@include file="footer.jsp" %> --%>
<script src="${pageContext.request.contextPath}/js/insert-worship.js"></script>
<script src="${pageContext.request.contextPath}/js/update-worship.js"></script>
<script src="${pageContext.request.contextPath}/js/order-manage.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		updateWorshipInit();
		
		init();
	});

	
	function showModal(){
		$(".modal").modal();
	}

	
</script>