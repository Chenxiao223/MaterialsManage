package com.zjrfid.materialsmanage.acdbentity;

/**
 * Created by zhouyu on 2017/4/29.
 */
public class IntRequsetBackRecord {

        private   int currentpage;
        private   int numPerPage;
        private   int pageNumShown;
        private   int totalCount;

    public IntRequsetBackRecord(int currentpage, int numPerPage, int pageNumShown, int totalCount) {
        this.currentpage = currentpage;
        this.numPerPage = numPerPage;
        this.pageNumShown = pageNumShown;
        this.totalCount = totalCount;
    }



    public void setCurrentpage(int currentpage) {
        this.currentpage = currentpage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    public void setPageNumShown(int pageNumShown) {
        this.pageNumShown = pageNumShown;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentpage() {
        return currentpage;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public int getPageNumShown() {
        return pageNumShown;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
