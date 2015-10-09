function turnPage(pageNo){
	var urls = window.location.href;
	var site=urls.indexOf("?");
	if(site >0){
		urls = urls.substring(0,site)
	}
	urls = urls+"?currentPage="+pageNo;
	window.location.href = urls;
}