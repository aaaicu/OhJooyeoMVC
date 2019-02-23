
	let dragElem = null;
	let parentArea = null;
	let areaCheck = false;

	document.getElementById('order-area');

	function handleDragStart(e){
		dragElem = null;

	    // console.log("드래그 스타트 : ",e);
//	    console.log("this : ", this);
	    if(this.dataset["editYn"] === "0"){
			dragElem = this;
			parentArea = this.parentNode;
			e.dataTransfer.setData("text","옮김");
			e.dataTransfer.effectAllowed='move';
		}else {
			alert("저장되지 않은 순서입니다.")
		}
	}

	function handleDragEnter(e){
	    // console.log("드래그 앤터 : ",e.path);
		areaCheck = false;

	    for( let pathIndex in e.path){
			if(parentArea == e.path[pathIndex]){
				areaCheck = true;
			}
		}
		console.log("대상? : ",areaCheck	);

	}

	function handleDragOver(e){

	    if(e.preventDefault()){
	        e.preventDefault();
	    }
		if(areaCheck) {
			this.classList.add('over');
		}
	    // e.dataTransfer.dropEffect = 'move';
	    // console.log("드래그 오버 : ",e);
	}
	function handleDragLeave(e){
	    this.classList.remove('over');
	    // console.log("드래그 리브 : ",e);
	}
	function handleDrop(e){
	    if(e.stopPropagation){
	        e.stopPropagation()
	    }

	    if(dragElem !== this && dragElem !== null && areaCheck ) {
	        this.parentNode.removeChild(dragElem);
	        this.insertAdjacentHTML('beforebegin',dragElem.outerHTML);
	        let dropElem = this.previousSibling;
	        addHandlers(dropElem);
	    }
	    this.classList.remove('over');
	    console.log(e.dataTransfer.getData("text"));
	    // console.log("드래그 드랍 : ",e);
	    // console.log("this : ", this);
	}
	function handleDragEnd(e){
	    // console.log("드래그 엔드 : ",e);
	    // console.log("this : ", this);
	}

function addHandlers(elem){
	console.log(elem);
	elem.getElementsByClassName('modify-btn')[0].addEventListener('click', modifyCard);

    elem.addEventListener('dragstart',handleDragStart,false);
    elem.addEventListener('dragenter',handleDragEnter,false);
    elem.addEventListener('dragover',handleDragOver,false);
    elem.addEventListener('dragleave',handleDragLeave,false);
    elem.addEventListener('drop',handleDrop,false);
    elem.addEventListener('dragend',handleDragEnd);
}

