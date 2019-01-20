
	let dragElem = null;

	function handleDragStart(e){
	    dragElem = this;
	    console.log("드래그 스타트 : ",e);

//	    console.log("this : ", this);
	    console.dir(this);
	    e.dataTransfer.setData("text","옮김");
	    e.dataTransfer.effectAllowed='move';
	}

	function handleDragEnter(e){
	    console.log("드래그 앤터 : ",e);
	}

	function handleDragOver(e){

	    if(e.preventDefault()){
	        e.preventDefault();
	    }
	    this.classList.add('over');
	    // e.dataTransfer.dropEffect = 'move';
	    // console.log("드래그 오버 : ",e);
	}
	function handleDragLeave(e){
	    this.classList.remove('over');
	    console.log("드래그 리브 : ",e);
	}
	function handleDrop(e){
	    if(e.stopPropagation){
	        e.stopPropagation()
	    }

	    if(dragElem != this) {
	        this.parentNode.removeChild(dragElem);
	        this.insertAdjacentHTML('beforebegin',dragElem.outerHTML);
	        let dropElem = this.previousSibling;
	        addHandlers(dropElem);
	    }
	    this.classList.remove('over');
	    console.log(e.dataTransfer.getData("text"));
	    console.log("드래그 드랍 : ",e);
	    console.log("this : ", this);
	    console.log("this : ", this);
	}
	function handleDragEnd(e){
	    console.log("드래그 엔드 : ",e);
	    console.log("this : ", this);
	}

function addHandlers(elem){
	console.log(elem);
    elem.addEventListener('dragstart',handleDragStart,false);
    elem.addEventListener('dragenter',handleDragEnter,false);
    elem.addEventListener('dragover',handleDragOver,false);
    elem.addEventListener('dragleave',handleDragLeave,false);
    elem.addEventListener('drop',handleDrop,false);
    elem.addEventListener('dragend',handleDragEnd);
}

