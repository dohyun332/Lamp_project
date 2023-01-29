package com.lamp.app.domain;

public class Pagination {
    private int totalCount;
    private int pageListCnt;
    private int naviSize = 10;
    private int totalPage;
    private int currPage;
    private int beginPage;
    private int endPage;
    private boolean showPrev;
    private boolean showNext;
    private boolean showPPrev;
    private boolean showNNext;
    public Pagination() {}
    public Pagination(int totalCount, int currPage, int pageListCnt) {
        this.totalCount = totalCount;
        this.currPage = currPage;
        this.pageListCnt = pageListCnt;

        this.totalPage = (int)Math.ceil(totalCount / (double)pageListCnt);
        this.beginPage = ((currPage-1) / naviSize) * naviSize + 1;
        this.endPage = Math.min((beginPage + naviSize-1), totalPage);

        this.showPrev = beginPage != 1;
        this.showNext = endPage != totalPage;
        this.showPPrev = (beginPage != (1+naviSize)) && (beginPage != 1);
        this.showNNext = endPage != (int)(Math.ceil(totalPage / (double)naviSize)*naviSize - naviSize) && (endPage != totalPage);
    }
    public Pagination(int totalCount, int currPage) {
        this(totalCount, currPage, 10);
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageListCnt() {
        return pageListCnt;
    }

    public void setPageListCnt(int pageListCnt) {
        this.pageListCnt = pageListCnt;
    }

    public int getNaviSize() {
        return naviSize;
    }

    public void setNaviSize(int naviSize) {
        this.naviSize = naviSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isShowPrev() {
        return showPrev;
    }

    public void setShowPrev(boolean showPrev) {
        this.showPrev = showPrev;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public boolean isShowPPrev() {
        return showPPrev;
    }

    public void setShowPPrev(boolean showPPrev) {
        this.showPPrev = showPPrev;
    }

    public boolean isShowNNext() {
        return showNNext;
    }

    public void setShowNNext(boolean showNNext) {
        this.showNNext = showNNext;
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "totalCount=" + totalCount +
                ", pageListCnt=" + pageListCnt +
                ", naviSize=" + naviSize +
                ", totalPage=" + totalPage +
                ", currPage=" + currPage +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                ", showPPrev=" + showPPrev +
                ", showNNext=" + showNNext +
                '}';
    }
}
