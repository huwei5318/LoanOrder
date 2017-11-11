package com.sxw.loan.loanorder.moudle;

import java.util.List;

/**
 * Created by Administrator on 2017/7/28.
 */

public class MineOrderRet {


    /**
     * pageInfo : {"pageNum":1,"pageSize":10,"size":5,"startRow":1,"endRow":5,"total":5,"pages":1,"list":[{"name":"宋耀林","workType":null,"city":"成都市","amount":20000,"creditPeriod":3,"lastDealTime":null,"status":0,"orderId":103,"pageNum":null,"userId":null},{"name":"马鹏","workType":"1","city":"成都市","amount":5000,"creditPeriod":1,"lastDealTime":null,"status":3,"orderId":94,"pageNum":null,"userId":null},{"name":"孙雨祥","workType":"105","city":"成都市","amount":5000,"creditPeriod":12,"lastDealTime":null,"status":0,"orderId":1,"pageNum":null,"userId":null},{"name":"向云满","workType":"122","city":"成都市","amount":8000,"creditPeriod":12,"lastDealTime":null,"status":0,"orderId":1,"pageNum":null,"userId":null},{"name":"任凯","workType":"124","city":"上海市","amount":30000,"creditPeriod":24,"lastDealTime":null,"status":0,"orderId":1,"pageNum":null,"userId":null}],"firstPage":1,"prePage":0,"nextPage":0,"lastPage":1,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1]}
     * code : 0
     */

    private PageInfoBean pageInfo;
    private String code;

    public PageInfoBean getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfoBean pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static class PageInfoBean {
        /**
         * pageNum : 1
         * pageSize : 10
         * size : 5
         * startRow : 1
         * endRow : 5
         * total : 5
         * pages : 1
         * list : [{"name":"宋耀林","workType":null,"city":"成都市","amount":20000,"creditPeriod":3,"lastDealTime":null,"status":0,"orderId":103,"pageNum":null,"userId":null},{"name":"马鹏","workType":"1","city":"成都市","amount":5000,"creditPeriod":1,"lastDealTime":null,"status":3,"orderId":94,"pageNum":null,"userId":null},{"name":"孙雨祥","workType":"105","city":"成都市","amount":5000,"creditPeriod":12,"lastDealTime":null,"status":0,"orderId":1,"pageNum":null,"userId":null},{"name":"向云满","workType":"122","city":"成都市","amount":8000,"creditPeriod":12,"lastDealTime":null,"status":0,"orderId":1,"pageNum":null,"userId":null},{"name":"任凯","workType":"124","city":"上海市","amount":30000,"creditPeriod":24,"lastDealTime":null,"status":0,"orderId":1,"pageNum":null,"userId":null}]
         * firstPage : 1
         * prePage : 0
         * nextPage : 0
         * lastPage : 1
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : [1]
         */

        private int pageNum;
        private int pageSize;
        private int size;
        private int startRow;
        private int endRow;
        private int total;
        private int pages;
        private int firstPage;
        private int prePage;
        private int nextPage;
        private int lastPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private int navigatePages;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            /**
             * name : 宋耀林
             * workType : null
             * city : 成都市
             * amount : 20000.0
             * creditPeriod : 3
             * lastDealTime : null
             * status : 0
             * orderId : 103
             * pageNum : null
             * userId : null
             */

            private String name;
            private Object workType;
            private String city;
            private Integer amount;
            private int creditPeriod;
            private String lastDealTime;
            private int status;
            private int orderId;
            private Object pageNum;
            private Object userId;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getWorkType() {
                return workType;
            }

            public void setWorkType(Object workType) {
                this.workType = workType;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public Integer getAmount() {
                return amount;
            }

            public void setAmount(Integer amount) {
                this.amount = amount;
            }

            public int getCreditPeriod() {
                return creditPeriod;
            }

            public void setCreditPeriod(int creditPeriod) {
                this.creditPeriod = creditPeriod;
            }

            public String getLastDealTime() {
                return lastDealTime;
            }

            public void setLastDealTime(String lastDealTime) {
                this.lastDealTime = lastDealTime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public Object getPageNum() {
                return pageNum;
            }

            public void setPageNum(Object pageNum) {
                this.pageNum = pageNum;
            }

            public Object getUserId() {
                return userId;
            }

            public void setUserId(Object userId) {
                this.userId = userId;
            }
        }
    }
}
