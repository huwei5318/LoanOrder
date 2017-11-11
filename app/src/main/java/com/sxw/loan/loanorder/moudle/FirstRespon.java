package com.sxw.loan.loanorder.moudle;

import java.util.List;

/**
 * Created by Administrator on 2017/7/19.
 */

public class FirstRespon {


    /**
     * pageInfo : {"pageNum":1,"pageSize":10,"size":10,"startRow":1,"endRow":10,"total":13,"pages":2,"list":[{"id":103,"name":"宋耀林","city":"成都市","amount":20000,"credit_period":3,"time":1499770221000,"productType":"1","qdFlag":"0","workType":null,"creditRecord":null,"security":"1","fund":null,"householdAssets":"0","readFlag":"0","orderTime":"2017-07-11","isSuccess":"0"},{"id":99,"name":"陈世洋","city":"成都市","amount":8000,"credit_period":24,"time":1499757910000,"productType":"0","qdFlag":"0","workType":"1","creditRecord":"1","security":"0","fund":null,"householdAssets":"1","readFlag":"0","orderTime":"2017-07-11","isSuccess":"0"},{"id":96,"name":"宋耀林","city":"成都市","amount":5000,"credit_period":6,"time":1499681103000,"productType":"0","qdFlag":"0","workType":"1","creditRecord":"2","security":"1","fund":null,"householdAssets":"0","readFlag":"0","orderTime":"2017-07-10","isSuccess":"0"},{"id":94,"name":"马鹏","city":"成都市","amount":5000,"credit_period":1,"time":1499419553000,"productType":"0","qdFlag":"0","workType":"1","creditRecord":"1","security":"1","fund":null,"householdAssets":"0","readFlag":"0","orderTime":"2017-07-07","isSuccess":"0"},{"id":93,"name":"曾一潇","city":"成都市","amount":10000,"credit_period":12,"time":1499416519000,"productType":"0","qdFlag":"0","workType":"2","creditRecord":"0","security":"1","fund":null,"householdAssets":"1","readFlag":"0","orderTime":"2017-07-07","isSuccess":"0"},{"id":106,"name":"多雪梅","city":"成都市","amount":3000,"credit_period":3,"time":1499793703000,"productType":"0","qdFlag":"0","workType":"5","creditRecord":"0","security":null,"fund":null,"householdAssets":"0","readFlag":"0","orderTime":"2017-07-12","isSuccess":"0"},{"id":107,"name":"孙雨祥","city":"成都市","amount":5000,"credit_period":10,"time":1499795611000,"productType":"1","qdFlag":"0","workType":"1","creditRecord":"1","security":"0","fund":null,"householdAssets":"1","readFlag":"0","orderTime":"2017-07-12","isSuccess":"0"},{"id":111,"name":"孙雨祥","city":"成都市","amount":3000,"credit_period":3,"time":1499935323000,"productType":"0","qdFlag":"0","workType":null,"creditRecord":"1","security":"0","fund":null,"householdAssets":"1","readFlag":"0","orderTime":"2017-07-13","isSuccess":"0"},{"id":114,"name":"白农琳","city":"成都市","amount":8000,"credit_period":3,"time":1500002344000,"productType":"0","qdFlag":"0","workType":"1","creditRecord":"1","security":"1","fund":null,"householdAssets":"0","readFlag":"0","orderTime":"2017-07-14","isSuccess":"0"},{"id":115,"name":"白农琳","city":"成都市","amount":8000,"credit_period":3,"time":1500002563000,"productType":"0","qdFlag":"0","workType":"1","creditRecord":"2","security":"1","fund":null,"householdAssets":"0","readFlag":"0","orderTime":"2017-07-14","isSuccess":"0"}],"firstPage":1,"prePage":0,"nextPage":2,"lastPage":2,"isFirstPage":true,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2]}
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
         * total : 13
         * pages : 2
         * list : [{"id":103,"name":"宋耀林","city":"成都市","amount":20000,"credit_period":3,"time":1499770221000,"productType":"1","qdFlag":"0","workType":null,"creditRecord":null,"security":"1","fund":null,"householdAssets":"0","readFlag":"0","orderTime":"2017-07-11","isSuccess":"0"},{"id":99,"name":"陈世洋","city":"成都市","amount":8000,"credit_period":24,"time":1499757910000,"productType":"0","qdFlag":"0","workType":"1","creditRecord":"1","security":"0","fund":null,"householdAssets":"1","readFlag":"0","orderTime":"2017-07-11","isSuccess":"0"},{"id":96,"name":"宋耀林","city":"成都市","amount":5000,"credit_period":6,"time":1499681103000,"productType":"0","qdFlag":"0","workType":"1","creditRecord":"2","security":"1","fund":null,"householdAssets":"0","readFlag":"0","orderTime":"2017-07-10","isSuccess":"0"},{"id":94,"name":"马鹏","city":"成都市","amount":5000,"credit_period":1,"time":1499419553000,"productType":"0","qdFlag":"0","workType":"1","creditRecord":"1","security":"1","fund":null,"householdAssets":"0","readFlag":"0","orderTime":"2017-07-07","isSuccess":"0"},{"id":93,"name":"曾一潇","city":"成都市","amount":10000,"credit_period":12,"time":1499416519000,"productType":"0","qdFlag":"0","workType":"2","creditRecord":"0","security":"1","fund":null,"householdAssets":"1","readFlag":"0","orderTime":"2017-07-07","isSuccess":"0"},{"id":106,"name":"多雪梅","city":"成都市","amount":3000,"credit_period":3,"time":1499793703000,"productType":"0","qdFlag":"0","workType":"5","creditRecord":"0","security":null,"fund":null,"householdAssets":"0","readFlag":"0","orderTime":"2017-07-12","isSuccess":"0"},{"id":107,"name":"孙雨祥","city":"成都市","amount":5000,"credit_period":10,"time":1499795611000,"productType":"1","qdFlag":"0","workType":"1","creditRecord":"1","security":"0","fund":null,"householdAssets":"1","readFlag":"0","orderTime":"2017-07-12","isSuccess":"0"},{"id":111,"name":"孙雨祥","city":"成都市","amount":3000,"credit_period":3,"time":1499935323000,"productType":"0","qdFlag":"0","workType":null,"creditRecord":"1","security":"0","fund":null,"householdAssets":"1","readFlag":"0","orderTime":"2017-07-13","isSuccess":"0"},{"id":114,"name":"白农琳","city":"成都市","amount":8000,"credit_period":3,"time":1500002344000,"productType":"0","qdFlag":"0","workType":"1","creditRecord":"1","security":"1","fund":null,"householdAssets":"0","readFlag":"0","orderTime":"2017-07-14","isSuccess":"0"},{"id":115,"name":"白农琳","city":"成都市","amount":8000,"credit_period":3,"time":1500002563000,"productType":"0","qdFlag":"0","workType":"1","creditRecord":"2","security":"1","fund":null,"householdAssets":"0","readFlag":"0","orderTime":"2017-07-14","isSuccess":"0"}]
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
             * id : 103
             * name : 宋耀林
             * city : 成都市
             * amount : 20000.0
             * credit_period : 3
             * time : 1499770221000
             * productType : 1
             * qdFlag : 0
             * workType : null
             * creditRecord : null
             * security : 1
             * fund : null
             * householdAssets : 0
             * readFlag : 0
             * orderTime : 2017-07-11
             * isSuccess : 0
             */

            private int id;
            private String name;
            private String city;
            private Integer amount;
            private int credit_period;
            private long time;
            private String productType;
            private String qdFlag;
            private Object workType;
            private Object creditRecord;
            private String security;
            private Object fund;
            private String householdAssets;
            private String readFlag;
            private String orderTime;
            private String isSuccess;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

            public int getCredit_period() {
                return credit_period;
            }

            public void setCredit_period(int credit_period) {
                this.credit_period = credit_period;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public String getProductType() {
                return productType;
            }

            public void setProductType(String productType) {
                this.productType = productType;
            }

            public String getQdFlag() {
                return qdFlag;
            }

            public void setQdFlag(String qdFlag) {
                this.qdFlag = qdFlag;
            }

            public Object getWorkType() {
                return workType;
            }

            public void setWorkType(Object workType) {
                this.workType = workType;
            }

            public Object getCreditRecord() {
                return creditRecord;
            }

            public void setCreditRecord(Object creditRecord) {
                this.creditRecord = creditRecord;
            }

            public String getSecurity() {
                return security;
            }

            public void setSecurity(String security) {
                this.security = security;
            }

            public Object getFund() {
                return fund;
            }

            public void setFund(Object fund) {
                this.fund = fund;
            }

            public String getHouseholdAssets() {
                return householdAssets;
            }

            public void setHouseholdAssets(String householdAssets) {
                this.householdAssets = householdAssets;
            }

            public String getReadFlag() {
                return readFlag;
            }

            public void setReadFlag(String readFlag) {
                this.readFlag = readFlag;
            }

            public String getOrderTime() {
                return orderTime;
            }

            public void setOrderTime(String orderTime) {
                this.orderTime = orderTime;
            }

            public String getIsSuccess() {
                return isSuccess;
            }

            public void setIsSuccess(String isSuccess) {
                this.isSuccess = isSuccess;
            }
        }
    }
}
