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
								<td><input type="date" class='chkTarget form-control form-control-sm' name="worshipDate"></td>
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

<%-- <%@include file="footer.jsp" %> --%>
