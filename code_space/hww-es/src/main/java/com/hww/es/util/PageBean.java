package com.hww.es.util;

import java.util.ArrayList;
import java.util.List;

public class PageBean<T> {
	private int pageSize = 10;// 每页显示记录
	private int pageNum = 1;// 当前页号
	private int total = 0;// 记录总数
	private int pages = 1;// 总页

	private int[] numbers;// 展示页数集合
	protected List<T> list;// 要显示到页面的数据集

	/**
	 * 得到
	 * @return
	 */
	public int getStartRow() {

		return (pageNum - 1) * pageSize;
	}

	/**
	 * 得到结束记录
	 * @return
	 */
	public int getEndRow() {

		return pageNum * pageSize;
	}

	/**
	 * @return Returns the size.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param size
	 * The size to set.
	 */
	public void setPageSize(int pageSize) {
		if (pageSize > 0) {
			this.pageSize = pageSize;
		}
	}

	/**
	 * @return Returns the currentPageNo.
	 */
	public int getPageNum() {
		if (pages == 0) {

			return 0;
		}

		return pageNum;
	}

	/**
	 * @param currentPageNo
	 * The currentPageNo to set.
	 */
	public void setPageNum(int pageNum) {
		if (pageNum > 0) {
			this.pageNum = pageNum;
		}
	}

	/**
	 * @return Returns the totalCount.
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param totalCount
	 *  The totalCount to set.
	 */
	public void setTotal(int totalCount) {
		if (totalCount >= 0) {
			this.total = totalCount;
			setPagesByRs();// 根据总记录数计算总页
		}
	}

	public int getPages() {
		return this.pages;
	}

	/**
	 * 根据总记录数计算总页
	 */
	private void setPagesByRs() {
		if (this.pageSize > 0 && this.total > 0 && this.total % this.pageSize == 0) {
			this.pages = this.total / this.pageSize;
		} else if (this.pageSize > 0 && this.total > 0 && this.total % this.pageSize > 0) {
			this.pages = (this.total / this.pageSize) + 1;
		} else {
			this.pages = 0;
		}
		setNumbers(pages);// 获取展示页数集合
	}

	public int[] getNumbers() {
		return numbers;
	}

	/**
	 * 设置显示页数集合
	 * @param totalPageCount
	 */
	public void setNumbers(int totalPageCount) {
		if (totalPageCount > 0) {
			// !.当前数组的长度
			int[] numbers = new int[totalPageCount > 10 ? 10 : totalPageCount];// 页面要显示的页数集合
			int k = 0;
			//
			// 1.数组长度<10 1 2 3 4 .... 7
			// 2.数组长度>=10
			// 当前页<=6 1 2 3 4 10
			// 当前页>=总页数-5 ......12 13 14 15
			// 其他 5 6 7 8 当前页(10) 10 11 12 13
			for (int i = 0; i < totalPageCount; i++) {
				// 保证当前页为集合的中�?
				if ((i >= pageNum - (numbers.length / 2 + 1) || i >= totalPageCount - numbers.length)
						&& k < numbers.length) {
					numbers[k] = i + 1;
					k++;
				} else if (k >= numbers.length) {
					break;
				}
			}

			this.numbers = numbers;
		}

	}

	public void setNumbers(int[] numbers) {
		this.numbers = numbers;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public void setBean(T bean) {
		if (this.list == null) {
			list = new ArrayList<T>();
		}
		list.add(bean);
	}

	/*
	 * public static int getTotalPageCount(int iTotalRecordCount, int iPageSize)
	 * { if (iPageSize == 0) { return 0; } else { return (iTotalRecordCount %
	 * iPageSize) == 0 ? (iTotalRecordCount / iPageSize) : (iTotalRecordCount /
	 * iPageSize) + 1; } }
	 */
}
