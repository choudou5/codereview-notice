package com.dreamerpartner.codereview.page;

import java.util.List;


public class PageBean<T> {
	
	public static final Integer MAX_ROWS = 30;	// 每页最大记录数限制
	
	protected int pageNo = 1;
	protected int pageSize = 30;
	protected List<T> result = null;
	protected int totalCount = 0;

	public PageBean() {
	}

	public PageBean(int pageSize) {
		setPageSize(pageSize);
	}
	
	public PageBean(int pageSize, int pageNo) {
		setPageSize(pageSize);
		setPageNo(pageNo);
	}

	public PageBean(int pageSize, int pageNo, int totalCount) {
		setPageSize(pageSize);
		setPageNo(pageNo);
		setTotalCount(totalCount);
	}
	
	public PageBean(int pageSize, int pageNo, int totalCount, List<T> docs) {
		setPageSize(pageSize);
		setPageNo(pageNo);
		setTotalCount(totalCount);
		setResult(docs);
	}
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;
		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	public PageBean<T> pageNo(final int thePageNo) {
		setPageNo(thePageNo);
		return this;
	}

	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;

		if (pageSize < 1) {
			this.pageSize = 1;
		}else if(pageSize > MAX_ROWS){
			this.pageSize = MAX_ROWS;
		}
	}

	public PageBean<T> pageSize(final int thePageSize) {
		setPageSize(thePageSize);
		return this;
	}

	public int getFirstResult() {
		return (pageNo - 1) * pageSize;
	}

	public List<T> getResult() {
		return result;
	}

	
	public void setResult(final List<T> result) {
		this.result = result;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(final int totalCount) {
		this.totalCount = totalCount;
	}
	
	public long getTotalPages() {
		if (totalCount <= 0)
			return 0;

		long count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}
	
	public int getIntTotalPages() {
		if (totalCount <= 0)
			return 0;

		int count = Long.valueOf(totalCount / pageSize).intValue();
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}

	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	public int getNextPage() {
		if (isHasNext())
			return pageNo + 1;
		else
			return pageNo;
	}

	public boolean isHasPre() {
		return (pageNo - 1 >= 1);
	}

	public int getPrePage() {
		if (isHasPre())
			return pageNo - 1;
		else
			return pageNo;
	}
    
}
