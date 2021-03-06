package com.sishuok.architecture1.common.vo;

public class WebModel {
	private String queryJsonStr;
	private int currentPage = 1;
	private int numPerPage = 0;

	public String getQueryJsonStr() {
		return queryJsonStr;
	}

	public void setQueryJsonStr(String queryJsonStr) {
		this.queryJsonStr = queryJsonStr;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	@Override
	public String toString() {
		return "WebModel [queryJsonStr=" + queryJsonStr
				+ ", currentPage=" + currentPage + ", numPerPage=" + numPerPage
				+ "]";
	}

}
