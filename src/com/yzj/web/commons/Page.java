package com.yzj.web.commons;

import java.io.Serializable;
import java.util.List;

/**
 * 封装的类
 * 
 * @author 86186
 *
 */
public class Page implements Serializable {
	private int currentPageNum;// 当前页 -
	private int pageSize = 10;// 每页的数量 -
	private int totalRecords;// 总记录数 -
	private int startIndex;// 开始的索引 -
	private int totalPage;// 总页数
	private int prePageNum;// 上一页
	private int nextPageNum;// 下一页
	private List Result;// 当前页的结果集

	/**
	 * 要想使用此类必须要给这两个参数赋值
	 * 
	 * @param currentPageNum
	 * @param totalRecords
	 */
	public Page(int currentPageNum, int totalRecords) {
		this.currentPageNum = currentPageNum;
		this.totalRecords = totalRecords;

		// 索引
		startIndex = (currentPageNum - 1) *pageSize;

		// 总页数
		totalPage = totalRecords / pageSize == 0 ? totalRecords / pageSize : totalRecords / pageSize + 1;

	}

	public int getPrePageNum() {
//		上一页
		prePageNum=currentPageNum-1;
		if (prePageNum<1) {
			prePageNum=1;
		}
		return prePageNum;
	}

	public int getNextPageNum() {
//		下一页
		nextPageNum=currentPageNum+1;
		
		if (nextPageNum>totalPage) {
			nextPageNum=totalPage;
		}
		
		return nextPageNum;
	}

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List getResult() {
		return Result;
	}

	public void setResult(List result) {
		Result = result;
	}

	public void setPrePageNum(int prePageNum) {
		this.prePageNum = prePageNum;
	}

	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}

	@Override
	public String toString() {
		return "Page [currentPageNum=" + currentPageNum + ", pageSize=" + pageSize + ", totalRecords=" + totalRecords
				+ ", startIndex=" + startIndex + ", totalPage=" + totalPage + ", prePageNum=" + prePageNum
				+ ", nextPageNum=" + nextPageNum + ", Result=" + Result + "]";
	}
	

}
