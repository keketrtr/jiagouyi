package com.sishuok.pageutil;

import java.util.Collections;
import java.util.List;

public class Page<E> {
	private int startIndex;
	private int numPerPage = 2;
	private int totalCount;
	private int totalPage;
	private int currentPage;
	private List<E> result = Collections.emptyList();

	public int getStartIndex() {
		startIndex = (currentPage - 1) * numPerPage;
		if (startIndex < 0) {
			startIndex = 0;
		}
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return (int) Math.ceil(totalCount*1.0 / numPerPage);
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		if (currentPage < 1) {
			currentPage = 1;
		}
		if (currentPage > getTotalPage()) {
			currentPage = getTotalPage();
		}
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<E> getResult() {
		return result;
	}

	public void setResult(List<E> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Page [startIndex=" + startIndex + ", numPerPage=" + numPerPage
				+ ", totalCount=" + totalCount + ", totalPage=" + getTotalPage()
				+ ", currentPage=" + currentPage + ", result=" + result + "]";
	}

}
