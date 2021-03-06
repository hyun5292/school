package com.ses.dto;

public class PageDTO {
	private int TotalCnt; // 전체 게시물 개수
	private int PageNum; // 현재 페이지 번호
	private int ContentNum; // 한 페이지에 표시할 페이지 수
	private int StartPage; // 현재 페이지 블록의 시작 페이지
	private int EndPage; // 현재 페이지 블록의 마지막 페이지
	private boolean Prev; // 이전 페이지로 가는 화살표
	private boolean Next; // 다음 페이지로 가는 화살표
	private int CurBlock; // 현재 페이지 블록
	private int LastBlock; // 마지막 페이지 블록

	public PageDTO() {
	}

	public void prevnext(int PageNum) {
		if (PageNum > 0 && PageNum < 6) {
			if ((getTotalCnt() / getContentNum()) < getContentNum()) {
				setPrev(false);
				setNext(false);
			} else if (((getTotalCnt() / getContentNum()) == getContentNum())
					&& ((getTotalCnt() % getContentNum()) == 0)) {
				setPrev(false);
				setNext(false);
			} else {
				setPrev(false);
				setNext(true);
			}
		} else if(getLastBlock() == getCurBlock()) {
			setPrev(true);
			setNext(false);
		} else {
			setPrev(true);
			setNext(true);
		}
	}

	// 전체 페이지 수 계산
	public int CalcPage(int TotalCnt, int ContentNum) {
		int total;
		total = TotalCnt / ContentNum;
		if (TotalCnt % ContentNum > 0) {
			total++;
		}
		return total;
	}

	public int getTotalCnt() {
		return TotalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		TotalCnt = totalCnt;
	}

	public int getPageNum() {
		return PageNum;
	}

	public void setPageNum(int pageNum) {
		PageNum = pageNum;
	}

	public int getContentNum() {
		return ContentNum;
	}

	public void setContentNum(int contentNum) {
		ContentNum = contentNum;
	}

	public int getStartPage() {
		return StartPage;
	}

	public void setStartPage(int CurrBlock) {
		StartPage = (CurrBlock * 5) - 4;
	}

	public int getEndPage() {
		return EndPage;
	}

	public void setEndPage(int GetLastBlock, int GetCurrBlock) {
		if (GetLastBlock == GetCurrBlock) {
			EndPage = CalcPage(getTotalCnt(), getContentNum());
		} else {
			EndPage = getStartPage() + 4;
		}
	}

	public boolean isPrev() {
		return Prev;
	}

	public void setPrev(boolean prev) {
		Prev = prev;
	}

	public boolean isNext() {
		return Next;
	}

	public void setNext(boolean next) {
		Next = next;
	}

	public int getCurBlock() {
		return CurBlock;
	}

	public void setCurBlock(int pageNum) {
		CurBlock = pageNum / 5;
		if (pageNum % 5 > 0) {
			CurBlock++;
		}
	}

	public int getLastBlock() {
		return LastBlock;
	}

	public void setLastBlock(int TotalCnt) {
		LastBlock = TotalCnt / (5 * ContentNum);
		if (TotalCnt % (5 * this.ContentNum) > 0) {
			LastBlock++;
		}
	}
}
