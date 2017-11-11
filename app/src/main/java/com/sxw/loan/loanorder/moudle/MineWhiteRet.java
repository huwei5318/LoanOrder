package com.sxw.loan.loanorder.moudle;

import java.util.List;

/**
 * Created by Administrator on 2017/7/28.
 */

public class MineWhiteRet {

    /**
     * pageInfo : {"pageNum":1,"pageSize":10,"size":10,"startRow":1,"endRow":10,"total":12,"pages":2,"list":[{"orderId":104,"name":"宋耀林","sexFlag":null,"city":"成都市","btName":"白条提现","time":1499776021000,"timeS":"2017-07-11","isSuccess":"1","readFlag":"1","orderStatus":null,"amount":0,"creditPeriod":0},{"orderId":102,"name":"宋耀林","sexFlag":null,"city":"成都市","btName":"白条提现","time":1499770103000,"timeS":"2017-07-11","isSuccess":"0","readFlag":"1","orderStatus":null,"amount":0,"creditPeriod":0},{"orderId":100,"name":"方健达","sexFlag":null,"city":"惠州市","btName":"白条提现","time":1499765755000,"timeS":"2017-07-11","isSuccess":"0","readFlag":"1","orderStatus":null,"amount":0,"creditPeriod":0},{"orderId":98,"name":"宋耀林","sexFlag":null,"city":"成都市","btName":"白条提现","time":1499744039000,"timeS":"2017-07-11","isSuccess":"0","readFlag":"1","orderStatus":null,"amount":0,"creditPeriod":0},{"orderId":97,"name":"宋耀林","sexFlag":null,"city":"成都市","btName":"白条提现","time":1499744018000,"timeS":"2017-07-11","isSuccess":"0","readFlag":"1","orderStatus":null,"amount":0,"creditPeriod":0},{"orderId":95,"name":"马鹏","sexFlag":null,"city":"成都市","btName":"白条提现","time":1499419652000,"timeS":"2017-07-07","isSuccess":"0","readFlag":"0","orderStatus":null,"amount":0,"creditPeriod":0},{"orderId":108,"name":"孙雨祥","sexFlag":null,"city":"成都市","btName":"白条提现","time":1499822934000,"timeS":"2017-07-12","isSuccess":"1","readFlag":"1","orderStatus":null,"amount":0,"creditPeriod":0},{"orderId":110,"name":"孙雨祥","sexFlag":null,"city":"成都市","btName":"白条提现","time":1499935234000,"timeS":"2017-07-13","isSuccess":"0","readFlag":"0","orderStatus":null,"amount":0,"creditPeriod":0},{"orderId":113,"name":"吴清华","sexFlag":null,"city":"荆门市","btName":"白条提现","time":1499953303000,"timeS":"2017-07-13","isSuccess":"0","readFlag":"1","orderStatus":null,"amount":0,"creditPeriod":0},{"orderId":120,"name":"焦晓东","sexFlag":null,"city":"石家庄市","btName":"白条提现","time":1500010665000,"timeS":"2017-07-14","isSuccess":"1","readFlag":"1","orderStatus":null,"amount":0,"creditPeriod":0}],"firstPage":1,"prePage":0,"nextPage":2,"lastPage":2,"isFirstPage":true,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2]}
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
         * size : 10
         * startRow : 1
         * endRow : 10
         * total : 12
         * pages : 2
         * list : [{"orderId":104,"name":"宋耀林","sexFlag":null,"city":"成都市","btName":"白条提现","time":1499776021000,"timeS":"2017-07-11","isSuccess":"1","readFlag":"1","orderStatus":null,"amount":0,"creditPeriod":0},{"orderId":102,"name":"宋耀林","sexFlag":null,"city":"成都市","btName":"白条提现","time":1499770103000,"timeS":"2017-07-11","isSuccess":"0","readFlag":"1","orderStatus":null,"amount":0,"creditPeriod":0},{"orderId":100,"name":"方健达","sexFlag":null,"city":"惠州市","btName":"白条提现","time":1499765755000,"timeS":"2017-07-11","isSuccess":"0","readFlag":"1","orderStatus":null,"amount":0,"creditPeriod":0},{"orderId":98,"name":"宋耀林","sexFlag":null,"city":"成都市","btName":"白条提现","time":1499744039000,"timeS":"2017-07-11","isSuccess":"0","readFlag":"1","orderStatus":null,"amount":0,"creditPeriod":0},{"orderId":97,"name":"宋耀林","sexFlag":null,"city":"成都市","btName":"白条提现","time":1499744018000,"timeS":"2017-07-11","isSuccess":"0","readFlag":"1","orderStatus":null,"amount":0,"creditPeriod":0},{"orderId":95,"name":"马鹏","sexFlag":null,"city":"成都市","btName":"白条提现","time":1499419652000,"timeS":"2017-07-07","isSuccess":"0","readFlag":"0","orderStatus":null,"amount":0,"creditPeriod":0},{"orderId":108,"name":"孙雨祥","sexFlag":null,"city":"成都市","btName":"白条提现","time":1499822934000,"timeS":"2017-07-12","isSuccess":"1","readFlag":"1","orderStatus":null,"amount":0,"creditPeriod":0},{"orderId":110,"name":"孙雨祥","sexFlag":null,"city":"成都市","btName":"白条提现","time":1499935234000,"timeS":"2017-07-13","isSuccess":"0","readFlag":"0","orderStatus":null,"amount":0,"creditPeriod":0},{"orderId":113,"name":"吴清华","sexFlag":null,"city":"荆门市","btName":"白条提现","time":1499953303000,"timeS":"2017-07-13","isSuccess":"0","readFlag":"1","orderStatus":null,"amount":0,"creditPeriod":0},{"orderId":120,"name":"焦晓东","sexFlag":null,"city":"石家庄市","btName":"白条提现","time":1500010665000,"timeS":"2017-07-14","isSuccess":"1","readFlag":"1","orderStatus":null,"amount":0,"creditPeriod":0}]
         * firstPage : 1
         * prePage : 0
         * nextPage : 2
         * lastPage : 2
         * isFirstPage : true
         * isLastPage : false
         * hasPreviousPage : false
         * hasNextPage : true
         * navigatePages : 8
         * navigatepageNums : [1,2]
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
             * orderId : 104
             * name : 宋耀林
             * sexFlag : null
             * city : 成都市
             * btName : 白条提现
             * time : 1499776021000
             * timeS : 2017-07-11
             * isSuccess : 1
             * readFlag : 1
             * orderStatus : null
             * amount : 0.0
             * creditPeriod : 0
             */

            private int orderId;
            private String name;
            private Object sexFlag;
            private String city;
            private String btName;
            private long time;
            private String timeS;
            private String isSuccess;
            private String readFlag;
            private int orderStatus;
            private Integer amount;
            private int creditPeriod;

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getSexFlag() {
                return sexFlag;
            }

            public void setSexFlag(Object sexFlag) {
                this.sexFlag = sexFlag;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getBtName() {
                return btName;
            }

            public void setBtName(String btName) {
                this.btName = btName;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public String getTimeS() {
                return timeS;
            }

            public void setTimeS(String timeS) {
                this.timeS = timeS;
            }

            public String getIsSuccess() {
                return isSuccess;
            }

            public void setIsSuccess(String isSuccess) {
                this.isSuccess = isSuccess;
            }

            public String getReadFlag() {
                return readFlag;
            }

            public void setReadFlag(String readFlag) {
                this.readFlag = readFlag;
            }

            public int getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(int orderStatus) {
                this.orderStatus = orderStatus;
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
        }
    }
}
