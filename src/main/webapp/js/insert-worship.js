var addOrderList;
var updateOrderList;
var removeOrderList;

var addAdList;
var updateAdList;
var removeAdList;

var tempId;

function init() {
	addOrderList = [];
	updateOrderList = [];
	removeOrderList = [];

	addAdList = [];
	updateAdList = [];	
	removeAdList = [];
	tempId = -1;
}

function templateFactory(templateType,optionalObject) {

/*
 * 추가된 li의 경우 Id 값이 정해지 않았기 때문에 기본값 "-1"로 저장 (실제 저장 값은 서버에서 할당)
 * order의 경우 기존의 값과 비교하여 변경된 li를 구분하기위한 변수이기 때문에 추가된 li의 경우 기본값 "-1"로 저장 (실제 저장 값은 서버에서 할당)
 */
	var resultHtml = "";
	
	var liId = "tp"+tempId;
	var id = "-1";
	var order = "-1";
	var type = "0";
	var title = "";
	var detail = "";
	var presenter = "";
	
	if(templateType === "orderButton"){
		if(optionalObject instanceof Object){
			liId = "od"+optionalObject.orderId;
			id = optionalObject.orderId;
			order = optionalObject.order;
			type = optionalObject.type;
			title = optionalObject.title;
			detail = optionalObject.detail;
			presenter = optionalObject.presenter;
		}
		
	} else if(templateType === "adButton"){
		if(optionalObject instanceof Object){		
		liId = "ad"+optionalObject.orderId;
		id = optionalObject.adId;
		order = optionalObject.order;
		title = optionalObject.title;
		detail = optionalObject.content;
		}
	}
	
	resultHtml += "<li id = '"+liId+"'>";
	resultHtml += "<table>";
	resultHtml += "<tr>";
	/* 순서 템플릿 생성 */
	 console.log(templateType);
	 console.log(templateType === "orderButton");
	if(templateType === "orderButton"){
		/* 처음 td hidden input 포함  */
		resultHtml += "<td>"
		resultHtml += "<input type='hidden' name='orderUpdateYN' value ='0'>"
		resultHtml += "<input type='hidden' name='orderOrder' value ='"+order+"'>";
		resultHtml += "<input type='hidden' name='orderId' value ='"+id+"'>"
		resultHtml += "<select class = 'selectType chkTarget'  name='orderType'>";
		resultHtml += "<option value = '0'";
		if (type === '0'){
			resultHtml += "selected = 'selected'";
		}
		resultHtml += ">일반순서</option>";
		
		resultHtml += "<option value = '1'";
		if (type == '1'){
			resultHtml += "selected = 'selected'";
		}
		resultHtml += ">성경봉독</option>";
		
		resultHtml += "<option value = '2'";
		if (type == '2'){
			resultHtml += "selected = 'selected'";
		}
		resultHtml += ">찬양</option>";
		resultHtml += "</select>";
		resultHtml += "</td>";
		/* 처음 td 끝  */
		
		resultHtml += "<td><input type='text' class ='chkTarget' name='orderTitle' value ='"+title+"'></td>";

		resultHtml +="<td>";
		if (type == '1'){
			resultHtml += "<input type='text' size = '15' class ='chkTarget' name='detail' readonly value ='"+detail+"'>";
			resultHtml += "<input type='button' class='searchBible' value = '찾기'>"
		}else {
			resultHtml += "<input type='text' class ='chkTarget' name='detail' value ='"+detail+"'>";
		}
		resultHtml +="</td>";
		
		resultHtml += "<td><input type='text' class ='chkTarget' name='orderPresenter' value ='"+presenter+"'></td>";
		resultHtml += "<td><input type = 'button' class ='addBeforeHtml chkTarget' name = 'orderButton' value = '앞에추가'></td>";
		
	/* 광고 템플릿 생성 */	
	}else if(templateType === "adButton"){
		/* 처음 td hidden input 포함  */
		resultHtml += "<td>"
		resultHtml += "<input type='hidden' name='adUpdateYN' value ='0'>"
		resultHtml += "<input type='hidden' name='adOrder' value ='"+order+"'>";
		resultHtml += "<input type='hidden' name='adId' value ='"+id+"'>"
		resultHtml += "<input type='text' class ='chkTarget' name='adTitle' value ='"+title+"'></td>";
		/* 처음 td 끝  */
		resultHtml += "<td><textarea name='adContent' class ='chkTarget' style='margin: 0px; width: 330px; height: 90px; resize: none;'>"+detail+"</textarea></td>";
		resultHtml += "<td><input type = 'button' class ='addBeforeHtml chkTarget' name = 'adButton' value = '앞에추가'></td>";
	}
	resultHtml += "<td class = 'del'>x</td>";
	resultHtml += "</tr>";
	resultHtml += "</table>";
	resultHtml += "</li>";
		return resultHtml;
	};
 
	/* type 변경시 "1"일 경우 detail input box 비활성화
   다른 타입으로 변경시 readonly 풀림
*/
$("ul").on("change", ".selectType" , function () {
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
	var pathname = window.location.pathname;
	var rootName = /.*[\/$]/.exec(pathname);
	var initValue = "";
	var $this = $(this);
	
	openWin = window.open(rootName+"search-bible", "search-bible", "width=500, height=400, toolbar=no, menubar=no, scrollbars=no, resizable=no" );  
	$(openWin).on("load", function(){
		/* 자식창 저장 시 부모엘리먼트를 찾아서 value를 넣어주기 위해 자식창에 id 저장 */
		openWin.document.getElementById("targetId").value = $this.closest("li")[0].id;
		
		if($this.siblings()[0].value != null && $this.siblings()[0].value != ""){	
			
			/* 부모창의 입력되어 있던 값 파싱 */
			initValue = $this.siblings()[0].value.split("/");
			for( var i = 0 ; i < initValue.length ; i ++){
				tag = "<tr><td class = 'range'>"+initValue[i]+"</td><td class = 'del'><input type = 'button' del = 'del-button' value = '삭제'></td></tr>";
				$(openWin.document).find("#addArea").append($(tag));				
			}
		}
	});
});

function render(area, html, method) {
	$(area)[method](html);
	tempId -= 1;
};

/* 삭제 기능*/
$("#renderArea").on("click", ".del", function() {
	var $this = $(this);
	$this.closest("li").remove();
});

/* 추가버튼 클릭 */
$(".addHtml").on("click",function(){
	var $this = $(this);
	var html = templateFactory($this[0].name,"");
	var area = "";
	console.log(html);		
	
	if($this[0].name === "orderButton") {
		area = "#orderList";
	} else if ($this[0].name === "adButton") {
		area = "#adList";
	}
	render(area,html,"append");
})
/* 앞에추가버튼 클릭 */
$("#renderArea").on("click",".addBeforeHtml",function(){
	console.log("앞에추가");	
	var $this = $(this);
	var html = templateFactory($this[0].name);
	render($this.closest("li"),html,"before");
})	

