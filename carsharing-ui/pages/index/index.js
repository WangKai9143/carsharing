//index.js
//获取应用实例
var util = require('../../utils/util.js');
var app = getApp();
var today = util.formatTime(new Date((new Date()).getTime() + (1000 * 60 * 60 * 24 * 7))).split(' ')[0];
var minday = util.formatTime(new Date()).split(' ')[0];
var maxday = util.formatTime(new Date((new Date()).getTime() + (1000 * 60 * 60 * 24 * 62))).split(' ')[0];
var sliderWidth = 96; // 需要设置slider的宽度，用于计算中间位置
var carPage = 1;
var peoplePage = 1;
var carList;
var peopleList;

Page({
    data: {
        all: 'act',
        date: today,
        minday: today,
        maxday: maxday,
        tabs: ["车找人", "人找车"],
        activeIndex: 0,
        sliderOffset: 0,
        sliderLeft: 0,
        start: '',
        over: '',
        popleNomore: false,
        carNomore: false
    },
    tabClick: function (e) {
        this.setData({
            sliderOffset: e.currentTarget.offsetLeft,
            activeIndex: e.currentTarget.id
        });
        // tab切换，有数据就不用向后台再请求
        if (this.data.activeIndex == 0 && (carList == null || carList.length == 0)) {
            carPage = 1;
            this.getList(e.detail.value, this.data.start, this.data.over);
        } else if (this.data.activeIndex == 1 && (peopleList == null || peopleList.length == 0)) {
            peoplePage = 1;
            this.getList(e.detail.value, this.data.start, this.data.over);
        }
    },
    bindDateChange: function (e) {
        this.setData({
            date: e.detail.value
        })
        this.getList(e.detail.value, this.data.start, this.data.over);
    },
    onShareAppMessage: function () {
        return {
            title: '同城拼车',
            path: 'http://wk.test.com:8080/info/lists'
        }
    },

    getCarList: function (date = '', start = '', over = '') {
        var that = this;
        util.req('http://wk.test.com:8080/info/lists',
            {start: start, over: over, date: date, page: carPage, type: that.data.activeIndex},
            function (result) {
                if (result.data.length == 0) {
                    that.setData({carNomore: true});
                    return false;
                }
                if (carPage == 1) {
                    carList = new Array();
                }
                var surp = new Array('', '空位', '人');
                result.data.forEach(function (item) {
                    try {
                        var start = ((item.departure).split('市')[1]).replace(/([\u4e00-\u9fa5]+[县区]).+/, '$1');
                    } catch (e) {
                        var start = (item.departure).split(/[县区]/)[0];
                    }

                    try {
                        var over = ((item.destination).split('市')[1]).replace(/([\u4e00-\u9fa5]+[县区]).+/, '$1');
                    } catch (e) {
                        var over = (item.destination).split(/[县区]/)[0];
                    }

                    var obj = {
                        start: start,
                        over: over,
                        type: that.data.tabs[item.type],
                        tp: item.type,
                        time: util.formatTime(new Date(item.time * 1000)),
                        // time:item.time,
                        // date:item.date,
                        surplus: item.surplus + surp[item.type],
                        see: item.see,
                        gender: item.gender,
                        avatarUrl: item.avatarUrl,
                        url: '/pages/info/index?id=' + item.id,
                        tm: util.getDateDiff(item.time * 1000)
                    };
                    carList.push(obj);
                })
                that.setData({carList: carList});
                // 防止首页数据小于5，前端显示加载中
                if (result.data.length < 5) {
                    that.setData({carNomore: true});
                    return false;
                }
            })
    },
    getPeopleList: function (date = '', start = '', over = '') {
        var that = this;
        util.req('http://wk.test.com:8080/info/lists',
            {start: start, over: over, date: date, page: peoplePage, type: that.data.activeIndex},
            function (result) {
                if (result.data.length == 0) {
                    that.setData({popleNomore: true});
                    return false;
                }
                if (peoplePage == 1) {
                    peopleList = new Array();
                }
                var surp = new Array('', '空位', '人');
                result.data.forEach(function (item) {
                    try {
                        var start = ((item.departure).split('市')[1]).replace(/([\u4e00-\u9fa5]+[县区]).+/, '$1');
                    } catch (e) {
                        var start = (item.departure).split(/[县区]/)[0];
                    }

                    try {
                        var over = ((item.destination).split('市')[1]).replace(/([\u4e00-\u9fa5]+[县区]).+/, '$1');
                    } catch (e) {
                        var over = (item.destination).split(/[县区]/)[0];
                    }

                    var obj = {
                        start: start,
                        over: over,
                        type: that.data.tabs[item.type],
                        tp: item.type,
                        time: util.formatTime(new Date(item.time * 1000)),
                        // time:item.time,
                        // date:item.date,
                        surplus: item.surplus + surp[item.type],
                        see: item.see,
                        gender: item.gender,
                        avatarUrl: item.avatarUrl,
                        url: '/pages/info/index?id=' + item.id,
                        tm: util.getDateDiff(item.time * 1000)
                    };
                    peopleList.push(obj);
                })
                that.setData({peopleList: peopleList});
                // 防止首页数据小于5，前端显示加载中
                if (result.data.length < 5) {
                    that.setData({popleNomore: true});
                    return false;
                }
            })
    },
    getList: function (date = '', start = '', over = '') {
        if (this.data.activeIndex == 0) {
            this.getCarList(date, start, over);
        } else {
            this.getPeopleList(date, start, over);
        }
    },
    onPullDownRefresh: function () {
        if (this.data.activeIndex == 0) {
            carPage = 1;
        } else {
            peoplePage = 1;
        }
        this.getList(this.data.date, this.data.start, this.data.over);
        wx.stopPullDownRefresh();
    },
    getstart: function (e) {
        this.setData({
            start: e.detail.value
        })
        if (this.data.activeIndex == 0) {
            carPage = 1;
        } else {
            peoplePage = 1;
        }
        this.getList(this.data.date, e.detail.value, this.data.over);
    },
    getover: function (e) {
        this.setData({
            over: e.detail.value
        })
        if (this.data.activeIndex == 0) {
            carPage = 1;
        } else {
            peoplePage = 1;
        }
        this.getList(this.data.date, this.data.start, e.detail.value);
    },
    onLoad: function () {
        var that = this;
        wx.getSystemInfo({
            success: function (res) {
                that.setData({
                    sliderLeft: (res.windowWidth / that.data.tabs.length - sliderWidth) / 2,
                    sliderOffset: res.windowWidth / that.data.tabs.length * that.data.activeIndex,
                    windowHeight: res.windowHeight,
                    windowWidth: res.windowWidth
                });
            }
        });

        wx.getLocation({
            success: function (res) {
                var latitude = res.latitude
                var longitude = res.longitude
                wx.request({
                    url: 'https://api.map.baidu.com/geocoder/v2/?ak=zIOkoO8wWrWA22ObIHPNkCgtLZpkP5lE&location=' + latitude + ',' + longitude + '&output=json&pois=0',
                    data: {},
                    method: 'GET',
                    header: {'Content-Type': 'application/json'},
                    success: function (res) {
                        /* that.setData({
                           start:res.data.result.addressComponent.city
                         })
                         that.getList(that.data.date,res.data.result.addressComponent.city);*/
                    }
                })
            }
        });
    },
    onShow: function () {
        if (this.data.activeIndex == 0) {
            carPage = 1;
            this.setData({carNomore: false});
        } else {
            peoplePage = 1;
            this.setData({carNomore: false});
        }
        this.getList(this.data.date, this.data.start, this.data.over);
    },
    onReachBottom: function () {
        if (this.data.activeIndex == 0) {
            if (!this.data.carNomore) {
                carPage++;
                this.getList(this.data.date, this.data.start, this.data.over);
            }
        } else {
            if (!this.data.popleNomore) {
                peoplePage++;
                this.getList(this.data.date, this.data.start, this.data.over);
            }
        }
    }
})
