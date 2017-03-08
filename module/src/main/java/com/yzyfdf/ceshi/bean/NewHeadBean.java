package com.yzyfdf.ceshi.bean;


import java.util.List;


public class NewHeadBean  {

    /**
     * code : 1
     * message : success
     * result : {"items":[{"detail":"","href":"https://www.oschina.net/event/2231301","id":2231301,"img":"https://static.oschina.net/uploads/cooperation/78455/intellij-idea-2016-3-public-preview_1c5d5bc3-f202-48a5-9002-f955256d08b1.jpg","name":"源创会 | 深圳源创会开始报名啦！","pubDate":"2017-03-06 11:02:53","type":5},{"detail":"","href":"https://www.oschina.net/news/82323/mayun-newsee","id":82323,"img":"https://static.oschina.net/uploads/cooperation/75323/ubuntu-forum-black-sql_f56538b5-299c-4b53-b3c7-aba7fc72e6c7.jpg","name":"码云全面改版，新界面新态度","pubDate":"2017-02-27 16:37:49","type":6},{"detail":"","href":"https://www.oschina.net/news/82460/who-said-programmer-35-years-old-is-deadline","id":82460,"img":"https://static.oschina.net/uploads/cooperation/78083/chrome55-save-at-least-35-percent-memory_d587a686-7f8d-4445-9afa-7f93e40b718d.jpg","name":"谁说程序员干到 35 岁就不行了？","pubDate":"2017-03-06 11:04:35","type":6},{"detail":"","href":"https://www.oschina.net/news/82544/10-prediction-of-programmer","id":82544,"img":"https://static.oschina.net/uploads/cooperation/77929/top-income-programming-languages-2016_690b5329-95b9-487d-ab4e-f27564d9c145.jpg","name":"有关程序员的十个预言","pubDate":"2017-03-06 11:07:05","type":6},{"detail":"","href":"https://www.oschina.net/news/82567/wtf-is-machine-learning","id":82567,"img":"https://static.oschina.net/uploads/cooperation/78455/intellij-idea-2016-3-public-preview_c5926935-9de1-4031-bf18-1fe9d569315f.jpg","name":"再来重新认识到底什么是机器学习","pubDate":"2017-03-06 11:09:13","type":6}],"nextPageToken":"61AF0C190D6BD629","prevPageToken":"3EA621243546C8A5","requestCount":5,"responseCount":5,"totalResults":5}
     * time : 2017-03-06 15:52:56
     */

    private int        code;
    private String     message;
    private ResultBean result;
    private String     time;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static class ResultBean {
        /**
         * items : [{"detail":"","href":"https://www.oschina.net/event/2231301","id":2231301,"img":"https://static.oschina.net/uploads/cooperation/78455/intellij-idea-2016-3-public-preview_1c5d5bc3-f202-48a5-9002-f955256d08b1.jpg","name":"源创会 | 深圳源创会开始报名啦！","pubDate":"2017-03-06 11:02:53","type":5},{"detail":"","href":"https://www.oschina.net/news/82323/mayun-newsee","id":82323,"img":"https://static.oschina.net/uploads/cooperation/75323/ubuntu-forum-black-sql_f56538b5-299c-4b53-b3c7-aba7fc72e6c7.jpg","name":"码云全面改版，新界面新态度","pubDate":"2017-02-27 16:37:49","type":6},{"detail":"","href":"https://www.oschina.net/news/82460/who-said-programmer-35-years-old-is-deadline","id":82460,"img":"https://static.oschina.net/uploads/cooperation/78083/chrome55-save-at-least-35-percent-memory_d587a686-7f8d-4445-9afa-7f93e40b718d.jpg","name":"谁说程序员干到 35 岁就不行了？","pubDate":"2017-03-06 11:04:35","type":6},{"detail":"","href":"https://www.oschina.net/news/82544/10-prediction-of-programmer","id":82544,"img":"https://static.oschina.net/uploads/cooperation/77929/top-income-programming-languages-2016_690b5329-95b9-487d-ab4e-f27564d9c145.jpg","name":"有关程序员的十个预言","pubDate":"2017-03-06 11:07:05","type":6},{"detail":"","href":"https://www.oschina.net/news/82567/wtf-is-machine-learning","id":82567,"img":"https://static.oschina.net/uploads/cooperation/78455/intellij-idea-2016-3-public-preview_c5926935-9de1-4031-bf18-1fe9d569315f.jpg","name":"再来重新认识到底什么是机器学习","pubDate":"2017-03-06 11:09:13","type":6}]
         * nextPageToken : 61AF0C190D6BD629
         * prevPageToken : 3EA621243546C8A5
         * requestCount : 5
         * responseCount : 5
         * totalResults : 5
         */

        private String          nextPageToken;
        private String          prevPageToken;
        private int             requestCount;
        private int             responseCount;
        private int             totalResults;
        private List<ItemsBean> items;

        public String getNextPageToken() {
            return nextPageToken;
        }

        public void setNextPageToken(String nextPageToken) {
            this.nextPageToken = nextPageToken;
        }

        public String getPrevPageToken() {
            return prevPageToken;
        }

        public void setPrevPageToken(String prevPageToken) {
            this.prevPageToken = prevPageToken;
        }

        public int getRequestCount() {
            return requestCount;
        }

        public void setRequestCount(int requestCount) {
            this.requestCount = requestCount;
        }

        public int getResponseCount() {
            return responseCount;
        }

        public void setResponseCount(int responseCount) {
            this.responseCount = responseCount;
        }

        public int getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(int totalResults) {
            this.totalResults = totalResults;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * detail :
             * href : https://www.oschina.net/event/2231301
             * id : 2231301
             * img : https://static.oschina.net/uploads/cooperation/78455/intellij-idea-2016-3-public-preview_1c5d5bc3-f202-48a5-9002-f955256d08b1.jpg
             * name : 源创会 | 深圳源创会开始报名啦！
             * pubDate : 2017-03-06 11:02:53
             * type : 5
             */

            private String detail;
            private String href;
            private int    id;
            private String img;
            private String name;
            private String pubDate;
            private int    type;

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPubDate() {
                return pubDate;
            }

            public void setPubDate(String pubDate) {
                this.pubDate = pubDate;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
