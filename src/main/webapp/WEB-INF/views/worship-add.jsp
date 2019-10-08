<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="worship-info-area" data-update-yn = "0">
	<div style="width: 270px;">
		<span style = "text-align:right;">
		<input type="button" class="btn btn-success btn-sm" id="openWorshipListButton" value="불러오기">
		</span>
	</div>
	<br />
	<div class="card border-dark mb-1" style="max-width: 40rem;">
		<div class="card-header">
			<h4 class="card-title">예배정보</h4>
		</div>
		<div class="card-body">
			<table>
				<tr>
					<td>
						<h5 class="text-muted">예배 일자</h5></td>
					<td><input type="date" class='change-check form-control form-control-sm' name="worshipDate"></td>
				</tr>
				<tr>
					<td>
						<h5 class="text-muted">사회자</h5>
					</td>
					<td><input type="text" class='change-check form-control form-control-sm' name="mainPresenter"></td>
				</tr>
				<tr>
					<td><h5 class="text-muted">다음 사회자</h5></td>
					<td><input type="text" class='change-check form-control form-control-sm' name="nextPresenter"></td>
				</tr>
				<tr>
					<td><h5 class="text-muted">다음 기도자</h5></td>
					<td><input type="text" class='change-check form-control form-control-sm' name="nextPrayer"></td>
				</tr>
				<tr>
					<td><h5 class="text-muted">다음 봉헌</h5></td>
					<td><input type="text" class='change-check form-control form-control-sm' name="nextOffer"></td>
				</tr>
			</table>
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

<div style="width: 270px;">
	<span style = "text-align:right;">
	<input type="button" class="btn btn-success btn-sm" id="addButton" value="저장">
	</span>
</div>
<br />

<script src="${pageContext.request.contextPath}/js/worship-add.js"></script>
