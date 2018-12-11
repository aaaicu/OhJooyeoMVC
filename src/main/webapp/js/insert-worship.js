var updateOrderList;
var removeOrderList;

var updateAdList;
var removeAdList;

var tempId;
var memory;

function init() {
	memory = new Map();
	updateOrderList = [];
	removeOrderList = [];

	updateAdList = [];	
	removeAdList = [];
	tempId = -1;
}

function templateFactory(templateType,optionalObject) {
console.log("함수",optionalObject);
/*
 * order의 경우 기존의 값과 비교하여 변경된 li를 구분하기위한 변수이기 때문에 추가된 li의 경우 기본값 "-1"로 저장 (실제 저장 값은 서버에서 할당)
 * 광고 : ad + 숫자
 * 예배순서 : od + 숫자
 * 새롭게 추가된 li의 일련번호는 음수로함.
 */
	var resultHtml = "";
	
	var liId = tempId;
	var id = "-1";
	var order = "-1";
	var type = "0";
	var title = "";
	var detail = "";
	var presenter = "";
	
	if(templateType === "order"){
		liId = "od" +liId;
		if(optionalObject instanceof Object){
			liId = "od"+optionalObject.orderId;
			id = optionalObject.orderId;
			order = optionalObject.order;
			type = optionalObject.type;
			title = optionalObject.title;
			detail = optionalObject.detail;
			presenter = optionalObject.presenter;
		}
		
	} else if(templateType === "ad"){
		liId = "ad" +liId;
		if(optionalObject instanceof Object){		
		liId = "ad"+optionalObject.adId;
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
	if(templateType === "order"){
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
		resultHtml += "<td><input type = 'button' class ='addBeforeHtml chkTarget' name = 'order' value = '앞에추가'></td>";
		
	/* 광고 템플릿 생성 */	
	}else if(templateType === "ad"){
		/* 처음 td hidden input 포함  */
		resultHtml += "<td>"
		resultHtml += "<input type='hidden' name='adUpdateYN' value ='0'>"
		resultHtml += "<input type='hidden' name='adOrder' value ='"+order+"'>";
		resultHtml += "<input type='hidden' name='adId' value ='"+id+"'>"
		resultHtml += "<input type='text' class ='chkTarget' name='adTitle' value ='"+title+"'></td>";
		/* 처음 td 끝  */
		resultHtml += "<td><textarea name='adContent' class ='chkTarget' style='margin: 0px; width: 330px; height: 90px; resize: none;'>"+detail+"</textarea></td>";
		resultHtml += "<td><input type = 'button' class ='addBeforeHtml chkTarget' name = 'ad' value = '앞에추가'></td>";
	}
	resultHtml += "<td class = 'del'>x</td>";
	resultHtml += "</tr>";
	resultHtml += "</table>";
	resultHtml += "</li>";
	return resultHtml;
};
 
function render(area, html, method) {
	$(area)[method](html);
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


/* 삭제버튼 클릭*/
$("#renderArea").on("click", ".del", function() {
	var $this = $(this);
	var thisId = $($this.closest("li")[0]).attr("id");
	var thisNo = thisId.substr(2);
	var thisType = thisId.substr(0,2);
	console.log(thisType == "od");
	//새롭게 추가되는 li는 삭제명단에서 관리할 필요가 없기때문에 음수는 배제
	if(parseInt(thisNo)>=0){	
		if(thisType === "od"){
			removeOrderList.push(thisNo)
		}else if(thisType === "ad"){
			removeAdList.push(thisNo);
		}
	}
	$this.closest("li").remove();
});

/* 추가버튼 클릭 */
$(".addHtml").on("click",function(){
	var $this = $(this);
	var html = templateFactory($this[0].name,"");
	var area = "";
	if($this[0].name === "order") {
		area = "#orderList";
	} else if ($this[0].name === "ad") {
		area = "#adList";
	}
	console.log($this[0]);
	console.log(area);
	console.log(html);
	tempId -= 1;
	render(area,html,"append");
})
/* 앞에추가버튼 클릭 */
$("#renderArea").on("click",".addBeforeHtml",function(){
	console.log("앞에추가");	
	var $this = $(this);
	var html = templateFactory($this[0].name);
	tempId -= 1;
	render($this.closest("li"),html,"before");
})	

/* 업데이트 관리 */
/* 포커스되는 순간을 변경의 시작으로 보고 변경전 값을 Map(:memory)에 추가 */
$("ul").on("focus", ".chkTarget",function() { 
	var $this = $(this);
	
	var thisId = $this.closest("li")[0].id
	var thisNo = thisId.substr(2);
	var name = $this.attr("name")
	var contents = $this.val();

	if(thisNo >= 0 ){
		/* 처음 포커스되는 태그의 경우 처리 로직 */
		if (memory.get(thisId) == undefined ){
			memory.set(thisId,{});
		} 
		if(!memory.get(thisId).hasOwnProperty(name)){
			memory.get(thisId)[name] = contents;
		}
	}
	console.log(memory);
});	

/* 변경이 일어난 경우 처음 값에서 바뀌었는지 비교 후 바뀌었다면 chkInputBox의 값을 "1"로 변경 */
/* 한번 업데이트 대상으로 지정되면 (chkInputBox에서 "1"로 처리되면) 원래값으로 변경했다 하더라도 "0" 로 돌아가지는 않음 (10/4 협의)*/
$("ul").on("change", ".chkTarget",function() { 
	var $this = $(this);
	
	var thisId = $this.closest("li")[0].id;
	var thisNo = thisId.substr(2);
	var thisType =thisId.substr(0,2);
	var name = $this.attr("name")
	var updateTargetStr ;
	var contents = $this.val();
	
	/* thisNo가 양수인 경우는 디비에서 내용을 갖고와서 수정이 가능한 대상임 
	 * 음수인 경우는 새롭게 추가한 li이기 때문에 update관리를 할 필요없이 일괄 등록하면 됨
	 */
	if(thisNo >= 0 ){
		
		if(thisType === "od"){
			updateTargetStr = "orderUpdateYN";
		}else if(thisType === "ad") {
			updateTargetStr = "adUpdateYN";
		}else if(thisType === "ws") {
			updateTargetStr = "worshipUpdateYN";
		}
		
		
		var chkInputBox = $($this.closest("li")[0]).find("[name='"+updateTargetStr+"']");
		console.log(chkInputBox);
		if(memory.get(thisId)[name] != contents && chkInputBox.val() == "0"  ){
			chkInputBox.val("1");
			// 업데이트 리스트 input value 변경 
		}
	}
});
