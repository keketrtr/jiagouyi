function turnPage(pageNo){
	var urls = window.location.href;
	var site=urls.indexOf("?");
	if(site >0){
		urls = urls.substring(0,site)
	}
	urls = urls+"?currentPage="+pageNo;
	
	var queryJson = document.getElementById("queryJsonStr").value;
	alert(queryJson);
	if(queryJson != null && queryJson != ''){
		urls = urls+"&queryJsonStr="+queryJson;
	}
	window.location.href = urls;
}