console.log("worship-add");

document.getElementById('openWorshipListButton').addEventListener('click',
function () {
	console.log("click");
	let contextPath = getContextPath();
	let openWin = window.open(contextPath + "/win/list/worship", "worship-list",
	"width=500, height=400, toolbar=no, menubar=no, scrollbars=no, resizable=no");
	openWin.onload = function() {
//		/* 예배ID 리스트 비동기식으로 조회 */

	}
})


document.getElementById('addButton').addEventListener('click',function (e) {
	let paramObject;

	let worshipObject = {};
	let addOrderList = [];
	let addAdList = [];

		[].forEach.call(document.querySelectorAll('#worship-info-area input.change-check'),function (e){
			worshipObject[e.name] = e.value;
		});

	[].forEach.call(document.querySelectorAll('#order-area > div'),function (e,index) {
			let orderObject ;
			let [type, title, detail, presenter] = e.querySelectorAll('.text');
			orderObject = {
				'worshipId' : '',
				'id' : e.id,
				'order' : index,
				'type' : type.dataset['value'],
				'title' : title.textContent.trim(),
				'detail' : detail.textContent.trim(),
				'presenter' : presenter.textContent.trim()
			};
			console.log(orderObject);
			addOrderList.push(orderObject);
	});

	[].forEach.call(document.querySelectorAll('#ad-area > div'), function(e,index) {
			let adObject ;
			let title = e.querySelectorAll('.text')[1];
			let content = e.querySelectorAll('.text')[2];
			adObject = {
				'worshipId' : '',
				'id' : e.id,
				'order' : index,
				'title' : title.textContent.trim(),
				'content' : content.textContent.trim(),
			};
			addAdList.push(adObject);
	});

	paramObject = {worshipId: '',
			worshipObject,
			addOrderList,
			addAdList
			};
	console.log(JSON.stringify(paramObject));

 	$.ajax({
		url : getContextPath()+"/process/add",
		type : "post",
		contentType : "application/json",
		dataType : "text",
		data : JSON.stringify(paramObject),
		success : function() {
			
			console.log("추가발생");
			window.location.href = getContextPath() + "/form/add"
//			$("#order-area").children().remove();
//			$("#ad-area").children().remove();
//			getWorshipInfo($("#selectWorshipId").val());
//			getWorshipDetailList($("#selectWorshipId").val(), "order");
//			getWorshipDetailList($("#selectWorshipId").val(), "ad");
//			alert("수정되었습니다.")
		},
//
		error : function(XHR, status, error) {
//			console.error(status + " : " + error);
		}
	});

});



function getWorshipInfo(worshipId) {
	console.log("함수 시작");
	console.log(worshipId);
	return new Promise(function(resolve, reject) {
		$.ajax({
			url : getContextPath()+"/getWorshipInfo",
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
		$("[name=worshipDate]").val(vo.worshipDate);
		$("[name=mainPresenter]").val(vo.mainPresenter);
		$("[name=nextPresenter]").val(vo.nextPresenter);
		$("[name=nextPrayer]").val(vo.nextPrayer);
		$("[name=nextOffer]").val(vo.nextOffer);
	});
}

/* value : li에 들어갈 VO,
 *getType : order / ad
 */
function getWorshipDetailList(value, getType) {
	var area;
	var urlOption;
	if (getType === "order") {
		area = "#order-area";
		urlOption = "getWorshipOrderList";
	} else if (getType === "ad") {
		area = "#ad-area";
		urlOption = "getWorshipAdList";
	}
	;

	return new Promise(function(resolve, reject) {
		$.ajax({
			url : getContextPath()+"/" + urlOption,
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
			var html = templateFactory(getType, vo[j]);
			render(area, html, "append")
		}
		var rowList = document.querySelectorAll('.row');
		[].forEach.call(rowList, addHandlers)
		console.log("rowList", rowList);
	});
}

/* 예배ID 변경시 순서 재호출 */
// $("#selectWorshipId").change(function() {
// 	console.log("변경발생");
// 	$("#order-area").children().remove();
// 	$("#ad-area").children().remove();
// 	getWorshipInfo($(this).val());
// 	getWorshipDetailList($(this).val(), "order");
// 	getWorshipDetailList($(this).val(), "ad");
//
// });
