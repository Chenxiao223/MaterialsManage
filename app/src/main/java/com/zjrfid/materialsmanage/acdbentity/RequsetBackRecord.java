package com.zjrfid.materialsmanage.acdbentity;

/**
 * Created by zhouyu on 2017/4/29.
 */
public class RequsetBackRecord {

        private   String currentpage;
        private   String numPerPage;
        private   String pageNumShown;
        private   String totalCount;

    public RequsetBackRecord(String currentpage, String numPerPage, String pageNumShown, String totalCount) {
        this.currentpage = currentpage;
        this.numPerPage = numPerPage;
        this.pageNumShown = pageNumShown;
        this.totalCount = totalCount;
    }

    public String getCurrentpage() {
            return currentpage;
        }

        public void setCurrentpage(String currentpage) {
            this.currentpage = currentpage;
        }

        public String getNumPerPage() {
            return numPerPage;
        }

        public void setNumPerPage(String numPerPage) {
            this.numPerPage = numPerPage;
        }

        public String getPageNumShown() {
            return pageNumShown;
        }

        public void setPageNumShown(String pageNumShown) {
            this.pageNumShown = pageNumShown;
        }

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }

}
