package com.hwj.modules.base.entity;

import java.io.Serializable;

public abstract class PaginationParam implements Serializable {

	private static final long serialVersionUID = 6981668746930390546L;

	/**
	 * 起始页位置
	 */
	private int pageStart = 0;

	/**
	 * 分页大小
	 */
	private int pageSize = 10;

	/**
	 * 当前分页, 分页从1开始
	 */
	private int pageNow = 0;

	/**
	 * 排序栏位
	 */
	private String sorter;

	/**
	 * 排列方式 ASC: 升序 DESC: 降序
	 */
	private String sortOrder;

	/**
	 * 排序字符串 例如  name.desc ,  name.asc
	 */
	private String sortString;

	public int getPageStart() {
		if (pageSize > -1) {
			pageStart = pageSize * pageNow;
		}
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = (pageNow > 0 ? (pageNow - 1) : 0);
	}

	public String getSorter() {
		return sorter;
	}

	public void setSorter(String sorter) {
		this.sorter = sorter;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSortString() {
		return sortString;
	}

	public void setSortString(String sortString) {
		this.sortString = sortString;
	}

}
