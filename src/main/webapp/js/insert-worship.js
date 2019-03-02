
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
function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname
			.indexOf("/", 2));
}

/* 태그 프로토타입 */

let inputModel = document.createElement('input');
inputModel.setAttribute('type','text');
inputModel.classList.add('modify-form','form-control-plaintext');
inputModel.setAttribute('value','');

let textareaModel = document.createElement('textarea');
textareaModel.setAttribute('rows','4');
textareaModel.style.width = "100%";
textareaModel.classList.add('modify-form','form-control');

let selectModel = document.createElement('select');
selectModel.classList.add('modify-form','custom-select');

/* 하드 코딩 */
let option0 = document.createElement("option");
option0.value = "0";
option0.text = "일반순서";
option0.selected = true;
selectModel.add(option0,null);

let option1 = document.createElement("option");
option1.value = "1";
option1.text = "성경봉독";
selectModel.add(option1,null);

let searchImg = document.createElement('img');
searchImg.setAttribute('src', getContextPath() + '/img/search.png');
searchImg.setAttribute('height', '15');
searchImg.setAttribute('width', '15');

let searchBibleBtnModel = document.createElement('button');
searchBibleBtnModel.classList.add('modify-form','btn','btn-default','search');
searchBibleBtnModel.appendChild(searchImg);
searchBibleBtnModel.append(" 성경찾기");

let saveResetDivModel = document.createElement('div');
saveResetDivModel.style.textAlign = "right";
saveResetDivModel.style.paddingTop = "10px";
saveResetDivModel.classList.add('saveResetDiv');

let resetBtnModel = document.createElement('input');
resetBtnModel.classList.add('btn','btn-primary','btn-sm');
resetBtnModel.setAttribute('type','button');
resetBtnModel.setAttribute('value','취소');

let saveBtnModel = document.createElement('input');
saveBtnModel.classList.add('btn','btn-info','btn-sm');
saveBtnModel.setAttribute('type','button');
saveBtnModel.setAttribute('value','저장');

function templateFactory(templateType, optionalObject) {
	// console.log("함수", optionalObject);
	/*
	 * order의 경우 기존의 값과 비교하여 변경된 li를 구분하기위한 변수이기 때문에 추가된 li의 경우 기본값 "-1"로 저장 (실제
	 * 저장 값은 서버에서 할당) 광고 : ad + 숫자 예배순서 : od + 숫자 새롭게 추가된 li의 일련번호는 음수로함.
	 */
	let resultHTML;
	let liId = tempId;
	let id = "-1";
	let order = "-1";	
	let type = "-1";
	let title = "";
	let detail = "";
	let presenter = "";
	let content = "";

	if (templateType === "order") {
		if (optionalObject instanceof Object) {
			id = optionalObject.orderId;
			order = optionalObject.order;
			type = optionalObject.type;
			title = optionalObject.title;
			detail = optionalObject.detail;
			presenter = optionalObject.presenter;
		}

	} else if (templateType === "ad") {
		if (optionalObject instanceof Object) {
			id = optionalObject.adId;
			order = optionalObject.order;
			title = optionalObject.title;
			content = optionalObject.content;
		}
	}

	resultHTML = document.createElement('div');
	resultHTML.setAttribute('id', id);
	resultHTML.setAttribute('draggable', 'true');
	resultHTML.classList.add('alert', 'alert-dismissible', 'alert-secondary');
	resultHTML.style.overflow = 'auto';

	/* hidden data를 dataset으로 관리 (data- ~~) */
	resultHTML.dataset.order = order;
	resultHTML.dataset.updateYn = '0';
	resultHTML.dataset.editYn="0";

	let deleteButton;
	deleteButton = document.createElement('button');
	deleteButton.classList.add('close');
	deleteButton.textContent = '×';
	deleteButton.setAttribute('type', 'button');
	deleteButton.setAttribute('data-dismiss', 'alert');
	resultHTML.appendChild(deleteButton);

	/* 항목 카드 헤더 */
	let headerHTML = document.createElement('div');

	let imgHTML = document.createElement('img');
	imgHTML.setAttribute('src', getContextPath() + '/img/edit.png');
	imgHTML.setAttribute('height', '15');
	imgHTML.setAttribute('width', '15');

	let modifyButton;
	modifyButton = document.createElement('button');
	modifyButton.classList.add('btn', 'btn-default','modify-btn');
	modifyButton.appendChild(imgHTML);

	let typeDiv = document.createElement('div');
	typeDiv.classList.add('tobe-select');

	let typeStrong = document.createElement('strong');
	typeStrong.setAttribute('name','type');
	typeStrong.classList.add('text');
	typeStrong.style.float = "left";
	typeDiv.appendChild(typeStrong);

	/* 서버에서 가져오는 방식으로 변경 필요 (임시 하드코딩) */
	let typeName = "";
	if (type === "0") {
		typeName = "일반순서";
	} else if (type === "1") {
		typeName = "성경봉독";
	}
	typeStrong.textContent = typeName + " ";
	typeStrong.dataset['value'] = type;

	headerHTML.appendChild(typeDiv);
	headerHTML.appendChild(modifyButton);

	/* 항목 카드 내용 */
	let bodyHTML = document.createElement('div');

	/* 순서 템플릿 생성 */
	if (templateType === "order") {

		bodyHTML.classList.add('order-element');

		let hrDiv = document.createElement('div');
		hrDiv.classList.add('unit-dotted', 'col');

		let hr = document.createElement('hr');
		hr.setAttribute('size', '2');
		hr.setAttribute('noshade', 'true');
		hr.setAttribute('align', 'center');
		hr.style.color = "#999999";
		hr.style.borderStyle = "dashed";

		hrDiv.appendChild(hr);

		let titleDiv;
		titleDiv = document.createElement('div');
		titleDiv.setAttribute('name', 'title');
		titleDiv.classList.add('col-2', 'unit','tobe-input');
		
		let titleTextDiv = document.createElement('div');
		titleTextDiv.classList.add('text');
		titleTextDiv.innerText = title;
		titleDiv.appendChild(titleTextDiv);
		
		let detailDiv;
		detailDiv = document.createElement('div');
		detailDiv.setAttribute('name', 'detail');
		detailDiv.classList.add('unit','tobe-input');
		
		let detailTextDiv = document.createElement('div');
		detailTextDiv.classList.add('text');
		detailTextDiv.innerText = detail;
		detailDiv.appendChild(detailTextDiv);
		
		let presenterDiv;
		presenterDiv = document.createElement('div');
		presenterDiv.setAttribute('name', 'presenter');
		presenterDiv.classList.add('col-auto', 'unit','tobe-input');
		
		let presenterTextDiv = document.createElement('div');
		presenterTextDiv.classList.add('text');
		presenterTextDiv.innerText = presenter;
		presenterDiv.appendChild(presenterTextDiv);


		bodyHTML.appendChild(titleDiv);
		bodyHTML.appendChild(hrDiv.cloneNode(true));
		bodyHTML.appendChild(detailDiv);
		bodyHTML.appendChild(hrDiv.cloneNode(true));
		bodyHTML.appendChild(presenterDiv);

	} else if (templateType === "ad") {
		
		bodyHTML.classList.add('ad-element');
		
		let titleDiv;
		titleDiv = document.createElement('div');
		titleDiv.setAttribute('name', 'title');
		titleDiv.classList.add('col-4', 'unit','tobe-input');
		
		let titleTextDiv = document.createElement('div');
		titleTextDiv.classList.add('text');
		titleTextDiv.innerText = title;
		titleDiv.appendChild(titleTextDiv);
		
		
		let contentDiv;
		contentDiv = document.createElement('div');
		contentDiv.setAttribute('name', 'content');
		contentDiv.classList.add('ad-content', 'tobe-textarea');
		
		let contentTextDiv = document.createElement('div');
		contentTextDiv.classList.add('text');
		
		contentTextDiv.innerText = content;
		contentDiv.appendChild(contentTextDiv);
		
		
		bodyHTML.appendChild(titleDiv);
		bodyHTML.appendChild(contentDiv);
		
	}
	/* 항목 카드 조립 */
	let contentsHTML = document.createElement('div');
	contentsHTML.appendChild(headerHTML);
	contentsHTML.appendChild(bodyHTML);
	resultHTML.appendChild(contentsHTML.cloneNode(true));

	/* 수정버튼 클릭 이벤트 추가 */
	// resultHTML.getElementsByClassName('modify-btn')[0].addEventListener('click', modifyCard);
	addHandlers(resultHTML);
	return resultHTML;
};

function windowOpen(e) {
	let element = this.offsetParent;
	let contextPath = getContextPath();
	let openWin = window.open(contextPath + "/search-bible", "search-bible",
			"width=500, height=400, toolbar=no, menubar=no, scrollbars=no, resizable=no");

	openWin.onload = function() {
		/*
         * 자식창 저장 시 부모엘리먼트를 찾아서 value를 넣어주기 위해
         * 자식창에 id 저장
         */
		openWin.document.getElementById("targetId").value = element.id;

		let initValue = null;
		let detailValue = element.getElementsByClassName('order-element')[0].children[2].children[1];

		if (detailValue.value !== null && detailValue.value !== "") {

			/* 부모창의 입력되어 있던 값 파싱 */
			initValue = detailValue.value.split("/");

			for (let i = 0; i < initValue.length; i++) {
				let trElement = openWin.document.createElement('tr');
				let tdElement0 = openWin.document.createElement('td');
				tdElement0.classList.add('range');
				tdElement0.textContent = initValue[i];

				let inputElement = openWin.document.createElement('input');
				inputElement.setAttribute('type','button');
				inputElement.setAttribute('del','del-button');
				inputElement.setAttribute('value','삭제');

				let tdElement1 = openWin.document.createElement('td');
				tdElement1.classList.add('del');
				tdElement1.appendChild(inputElement);

				trElement.appendChild(tdElement0);
				trElement.appendChild(tdElement1);

				openWin.document.getElementById("addArea").appendChild(trElement);
			}
		}
	}

}

function selectListener(evnet) {
	if(this.selectedIndex === 1){
		console.dir(this.offsetParent);
		/* 성경 고르기 버튼 추가 */
		let searchBibleBtn = searchBibleBtnModel.cloneNode(true);

		/* 성경찾기 클릭 이벤트 추가( 새창으로 열기 콜백 )*/
		searchBibleBtn.onclick = windowOpen;
		this.parentNode.appendChild(searchBibleBtn);

		/* 디테일 인풋박스 내용 지우기 및 비활성화 */
		this.offsetParent.getElementsByClassName('order-element')[0].children[2].children[1].value = "";
		this.offsetParent.getElementsByClassName('order-element')[0].children[2].children[1].setAttribute("readonly","readonly");

	}else {
		/* 성경 고르기 버튼 제거 */
		this.parentNode.removeChild(this.parentNode.getElementsByClassName('search')[0]);
		/* 디테일 인풋박스 내용 활성화 */
		this.offsetParent.getElementsByClassName('order-element')[0].children[2].children[1].removeAttribute("readonly");
	}
};

function modifyCard(e){
	let tobeInput = this.offsetParent.getElementsByClassName('tobe-input');
	let tobeTextarea = this.offsetParent.getElementsByClassName('tobe-textarea');
	let tobeSelect = this.offsetParent.getElementsByClassName('tobe-select');
	let saveResetDiv = saveResetDivModel.cloneNode();
	let textarea = textareaModel.cloneNode();
	let resetBtn = resetBtnModel.cloneNode();
	let saveBtn = saveBtnModel.cloneNode();
	let searchBibleBtn = searchBibleBtnModel.cloneNode(true);
	let area = null;

	for( let pathIndex in e.path){
		if(document.getElementById('order-area') === e.path[pathIndex]){
			area = "order-area";
		}else if(document.getElementById('ad-area') === e.path[pathIndex]){
			area = "ad-area";
		}
	}
	
	saveResetDiv.appendChild(resetBtn);
	saveResetDiv.appendChild(saveBtn);
	
	saveResetDiv.children[0].addEventListener('click',resetCard);
	saveResetDiv.children[1].addEventListener('click',saveCard);

	if(this.offsetParent.dataset['editYn']==="0"){
		/* 입력모드로 전환 */

		[].forEach.call(tobeInput,function(e){
			let input =inputModel.cloneNode();
			input.setAttribute('value',e.textContent);
			e.getElementsByClassName('text')[0].style.display='none';
			e.appendChild(input);
		});

		[].forEach.call(tobeSelect,function (e) {
			if(area === "order-area"){

				let select = selectModel.cloneNode(true);

				/* 속성 변경시 리스너 추가 */
				select.onchange= selectListener;

				// selectModel.setAttribute('value',e.textContent);
				// console.dir(select);
				e.getElementsByClassName('text')[0].style.display='none';

				/* text의 값에 따라 기본값을 변경하는 부분  */
				/* 추가된 항목(-1)이 아니라면 가져온 데이터를 기본값으로 지정 */
				if(e.getElementsByClassName('text')[0].dataset['value'] !== "-1"){
					select.selectedIndex = e.getElementsByClassName('text')[0].dataset['value'];
				}

				e.appendChild(select);
				if(select.selectedIndex === 1){
					select.offsetParent.getElementsByClassName('order-element')[0].children[2].children[1].setAttribute("readonly","readonly");
					searchBibleBtn.onclick = windowOpen;
					e.appendChild(searchBibleBtn);

				}

			}

			// e.getElementsByClassName('text')[0].style.display='none';
		});

		[].forEach.call(tobeTextarea,function(e){
			textarea.textContent = e.textContent;
			e.getElementsByClassName('text')[0].style.display='none';
			e.appendChild(textarea.cloneNode());
		});

		this.offsetParent.appendChild(saveResetDiv);
		
	}
	
	/* 토근 변경 */
	this.offsetParent.dataset['editYn']  = "1" ;
	
	this.parentNode.children[1].style.display = "none";
}

function saveCard(e){
	let tobeInput = this.offsetParent.getElementsByClassName('tobe-input');
	let tobeTextarea = this.offsetParent.getElementsByClassName('tobe-textarea');
	let tobeSelect = this.offsetParent.getElementsByClassName('tobe-select');
	let area = null;

	for( let pathIndex in e.path){
		if(document.getElementById('order-area') === e.path[pathIndex]){
			area = "order-area";
		}else if(document.getElementById('ad-area') === e.path[pathIndex]){
			area = "ad-area";
		}
	}

	/* 확인모드로 전환 */

	[].forEach.call(tobeSelect,function(e){
		if(area === "order-area") {
			e.getElementsByClassName('text')[0].textContent = e.children[1].selectedOptions[0].textContent;
			e.getElementsByClassName('text')[0].style.display = null;
			e.getElementsByClassName('text')[0].dataset['value'] = e.getElementsByClassName('modify-form')[0].selectedIndex;

			e.removeChild(e.getElementsByClassName('modify-form')[0]);
		}
	});

	[].forEach.call(tobeInput,function(e){
		e.getElementsByClassName('text')[0].textContent = e.children[1].value;
		e.getElementsByClassName('text')[0].style.display=null;

		e.removeChild(e.getElementsByClassName('modify-form')[0]);

	});

	[].forEach.call(tobeTextarea,function(e){
		e.getElementsByClassName('text')[0].textContent = e.children[1].value;
		e.getElementsByClassName('text')[0].style.display=null;

		e.removeChild(e.getElementsByClassName('modify-form')[0]);
	});

	let searchBtn = this.offsetParent.getElementsByClassName('search');
	if(searchBtn.length !== 0 ){
		searchBtn[0].parentNode.removeChild(searchBtn[0]);
	}

	this.offsetParent.getElementsByClassName('modify-btn')[0].style.display = null;
	this.offsetParent.dataset['editYn']  = "0" ;
	this.offsetParent.removeChild(this.offsetParent.getElementsByClassName('saveResetDiv')[0]);

}

function resetCard(e){
	let tobeInput = this.offsetParent.getElementsByClassName('tobe-input');
	let tobeTextarea = this.offsetParent.getElementsByClassName('tobe-textarea');
	let tobeSelect = this.offsetParent.getElementsByClassName('tobe-select');

	let area = null;

	for( let pathIndex in e.path){
		if(document.getElementById('order-area') === e.path[pathIndex]){
			area = "order-area";
		}else if(document.getElementById('ad-area') === e.path[pathIndex]){
			area = "ad-area";
		}
	}

	/* 확인모드로 전환 */
	[].forEach.call(tobeSelect,function(e){
		if(area === "order-area"){

			e.getElementsByClassName('text')[0].style.display=null;
			e.removeChild(e.getElementsByClassName('modify-form')[0]);
		}

	});

	[].forEach.call(tobeInput,function(e){
		e.getElementsByClassName('text')[0].style.display=null;

		e.removeChild(e.getElementsByClassName('modify-form')[0]);
	});

	[].forEach.call(tobeTextarea,function(e){
		e.getElementsByClassName('text')[0].style.display=null;
		e.removeChild(e.getElementsByClassName('modify-form')[0]);
	});

	let searchBtn = this.offsetParent.getElementsByClassName('search');
	if(searchBtn.length !== 0 ){
		searchBtn[0].parentNode.removeChild(searchBtn[0]);
	}

	this.offsetParent.getElementsByClassName('modify-btn')[0].style.display = null;
	this.offsetParent.dataset['editYn']  = "0" ;
	this.offsetParent.removeChild(this.offsetParent.getElementsByClassName('saveResetDiv')[0]);
}




function render(area, html, method) {
	$(area)[method](html);
};

/* 삭제버튼 클릭 */
$("#renderArea").on("click", ".del", function() {
	var $this = $(this);
	var thisId = $($this.closest("li")[0]).attr("id");
	var thisNo = thisId.substr(2);
	var thisType = thisId.substr(0, 2);
	console.log(thisType == "od");
	// 새롭게 추가되는 li는 삭제명단에서 관리할 필요가 없기때문에 음수는 배제
	if (parseInt(thisNo) >= 0) {
		if (thisType === "od") {
			removeOrderList.push(thisNo)
		} else if (thisType === "ad") {
			removeAdList.push(thisNo);
		}
	}
	$this.closest("li").remove();
});

/* 추가버튼 클릭 */
$(".add-html").on("click", function() {
	var $this = $(this);
	var html = templateFactory($this[0].name, "");
	var area = "";
	if ($this[0].name === "order") {
		area = "#order-area";
	} else if ($this[0].name === "ad") {
		area = "#ad-area";
	}
	console.log($this[0]);
	console.log(area);
	console.log(html);
	tempId -= 1;

	render(area, html, "append");
	rowList = document.querySelectorAll('.row');
	[].forEach.call(rowList, addHandlers)
	console.log("rowList", rowList);
})

/* 업데이트 관리 */
/* 포커스되는 순간을 변경의 시작으로 보고 변경전 값을 Map(:memory)에 추가 */
$("ul").on("focus", ".chkTarget", function() {
	var $this = $(this);

	var thisId = $this.closest("li")[0].id
	var thisNo = thisId.substr(2);
	var name = $this.attr("name")
	var contents = $this.val();

	if (thisNo >= 0) {
		/* 처음 포커스되는 태그의 경우 처리 로직 */
		if (memory.get(thisId) == undefined) {
			memory.set(thisId, {});
		}
		if (!memory.get(thisId).hasOwnProperty(name)) {
			memory.get(thisId)[name] = contents;
		}
	}
	console.log(memory);
});

/* 변경이 일어난 경우 처음 값에서 바뀌었는지 비교 후 바뀌었다면 chkInputBox의 값을 "1"로 변경 */
/*
 * 한번 업데이트 대상으로 지정되면 (chkInputBox에서 "1"로 처리되면) 원래값으로 변경했다 하더라도 "0" 로 돌아가지는 않음
 * (10/4 협의)
 */
$("ul").on(
		"change",
		".chkTarget",
		function() {
			var $this = $(this);

			var thisId = $this.closest("li")[0].id;
			var thisNo = thisId.substr(2);
			var thisType = thisId.substr(0, 2);
			var name = $this.attr("name")
			var updateTargetStr;
			var contents = $this.val();

			/*
			 * thisNo가 양수인 경우는 디비에서 내용을 갖고와서 수정이 가능한 대상임 음수인 경우는 새롭게 추가한 li이기
			 * 때문에 update관리를 할 필요없이 일괄 등록하면 됨
			 */
			if (thisNo >= 0) {

				if (thisType === "od") {
					updateTargetStr = "orderUpdateYN";
				} else if (thisType === "ad") {
					updateTargetStr = "adUpdateYN";
				} else if (thisType === "ws") {
					updateTargetStr = "worshipUpdateYN";
				}

				var chkInputBox = $($this.closest("li")[0]).find(
						"[name='" + updateTargetStr + "']");
				console.log(chkInputBox);
				if (memory.get(thisId)[name] != contents
						&& chkInputBox.val() == "0") {
					chkInputBox.val("1");
					// 업데이트 리스트 input value 변경 
				}
			}
		});
