package com.zzt8888.beans;

import java.util.List;

public class TypeDataBean {


    /**
     * error : false
     * results : [{"_id":"59659268421aa90ca3bb6ae5","createdAt":"2017-07-12T11:07:20.125Z","desc":"里面有几篇自定义 Behavior 的文章，推荐给大家","publishedAt":"2017-07-13T12:28:15.167Z","source":"web","type":"Android","url":"https://github.com/pinguo-zhouwei/MaterialDesignSamples","used":true,"who":"王荣强"},{"_id":"5965f4aa421aa90c9203d37c","createdAt":"2017-07-12T18:06:34.118Z","desc":"Activity、View、Window的理解一篇文章就够了","images":["http://img.gank.io/5924d4cd-0093-4d29-822e-2ba7a09eb35a"],"publishedAt":"2017-07-13T12:28:15.167Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/5297e307a688","used":true,"who":"Zane"},{"_id":"5966d950421aa90c9203d380","createdAt":"2017-07-13T10:22:08.808Z","desc":"如何写一个优雅的 Android Launcher，请看这个项目。","images":["http://img.gank.io/ba74581b-894e-4d69-b7d2-88fcf905dcdf"],"publishedAt":"2017-07-13T12:28:15.167Z","source":"chrome","type":"Android","url":"https://github.com/Deletescape-Media/Lawnchair","used":true,"who":"代码家"},{"_id":"5966db23421aa90ca209c457","createdAt":"2017-07-13T10:29:55.700Z","desc":"基于 Javascript 实现的 JVM 虚拟机。","publishedAt":"2017-07-13T12:28:15.167Z","source":"chrome","type":"Android","url":"http://plasma-umass.github.io/doppio-demo/","used":true,"who":"代码家"},{"_id":"596587a7421aa97de5c7c91c","createdAt":"2017-07-12T10:21:27.468Z","desc":"Android library to display progress like google does in some of his services.","publishedAt":"2017-07-12T13:05:59.766Z","source":"chrome","type":"Android","url":"https://github.com/jpardogo/GoogleProgressBar","used":true,"who":"galois"},{"_id":"5965ad43421aa90ca3bb6ae9","createdAt":"2017-07-12T13:01:55.875Z","desc":"Android 上最便捷的第三方 Keyboard 集合。","images":["http://img.gank.io/6f9816fd-bada-4912-a133-6a7194d35292"],"publishedAt":"2017-07-12T13:05:59.766Z","source":"chrome","type":"Android","url":"https://github.com/hoanganhtuan95ptit/AwesomeKeyboard","used":true,"who":"代码家"},{"_id":"595f7f6d421aa90ca209c416","createdAt":"2017-07-07T20:32:45.22Z","desc":"根据实际开发中遇到的需求，使用 Gradle 对应用的不同版本进行个性化定制。","images":["http://img.gank.io/0be70b9b-dc5a-4778-bb7b-0f5ff0e73d2a"],"publishedAt":"2017-07-11T13:46:33.911Z","source":"chrome","type":"Android","url":"http://www.imliujun.com/gradle1.html","used":true,"who":"LiuJun"},{"_id":"5964263e421aa90ca3bb6adc","createdAt":"2017-07-11T09:13:34.550Z","desc":"你的Android应用稳定吗？","publishedAt":"2017-07-11T13:46:33.911Z","source":"web","type":"Android","url":"http://url.cn/4BbWlxC","used":true,"who":"陈宇明"},{"_id":"59646466421aa90c9203d367","createdAt":"2017-07-11T13:38:46.38Z","desc":"Android 信用卡交易效果 UI 。","images":["http://img.gank.io/b9a34460-8224-449d-903d-3ef54a3f35b6"],"publishedAt":"2017-07-11T13:46:33.911Z","source":"chrome","type":"Android","url":"https://github.com/KingsMentor/Luhn","used":true,"who":"代码家"},{"_id":"59646491421aa90ca209c433","createdAt":"2017-07-11T13:39:29.898Z","desc":"效果很棒的 Fab 按钮。","images":["http://img.gank.io/221dc9b8-f3cb-4602-8a52-43a780328925"],"publishedAt":"2017-07-11T13:46:33.911Z","source":"chrome","type":"Android","url":"https://github.com/jahirfiquitiva/FABsMenu","used":true,"who":"代码家"}]
     */

    private boolean error;
    private List<ResultsEntity> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsEntity> getResults() {
        return results;
    }

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }

    public static class ResultsEntity {
        /**
         * _id : 59659268421aa90ca3bb6ae5
         * createdAt : 2017-07-12T11:07:20.125Z
         * desc : 里面有几篇自定义 Behavior 的文章，推荐给大家
         * publishedAt : 2017-07-13T12:28:15.167Z
         * source : web
         * type : Android
         * url : https://github.com/pinguo-zhouwei/MaterialDesignSamples
         * used : true
         * who : 王荣强
         * images : ["http://img.gank.io/5924d4cd-0093-4d29-822e-2ba7a09eb35a"]
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
