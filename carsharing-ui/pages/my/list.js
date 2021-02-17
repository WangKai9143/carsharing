// pages/my/list.js
var app = getApp();
var util = require('../../utils/util.js');
var page = 1;
var list = new Array();
Page({
    data: {
        tabs: ["车找人", "人找车"]
    },
    del: function (e) {
        var that = this;
        var currentTarget = e.currentTarget.id;
        wx.showModal({
            title: '提示',
            content: '确定删除?',
            success: function (res) {
                if (res.confirm) {
                    util.req('http://wk.test.com:8080/info/del', {
                        sk: app.globalData.sk,
                        id: list[currentTarget].id
                    }, function (data) {
                        if (data.status == 1) {
                            list.splice(currentTarget, 1);
                            that.setData({list: list});
                            wx.showToast({
                                title: '删除成功',
                                icon: 'success',
                                duration: 2000
                            })
                        } else {
                            util.isError('删除失败,请重试', that);
                            return false;
                        }
                    })
                }
            }
        })
        return false;
    },
    edit: function (e) {
        var currentTarget = e.currentTarget.id;
        console.log('/pages/info/add?id=' + list[currentTarget].id);
        wx.navigateTo({
            url: '/pages/info/edit?id=' + list[currentTarget].id,
            complete: function (res) {
                console.log(res)
            }
        })
    },
    onReachBottom: function () {
        if (!this.data.nomore) {
            page++;
            this.getList();
        }
    },
    getList() {
        var that = this;
        util.req('http://wk.test.com:8080/info/mylist', {sk: app.globalData.sk, page: page}, function (result) {
            if (result.data.length == 0) {
                if (page == 1) {
                    console.log(page);
                    that.setData({'isnull': true});
                }
                that.setData({nomore: true});
                return false;
            }

            if (page == 1) {
                list = new Array();
            }

            var surp = new Array('', '空位', '人');
            result.data.forEach(function (item) {
                var obj = {
                    start: ((item.departure).split('市')[1]).replace(/([\u4e00-\u9fa5]+[县区]).+/, '$1'),
                    over: ((item.destination).split('市')[1]).replace(/([\u4e00-\u9fa5]+[县区]).+/, '$1'),
                    type: that.data.tabs[item.type],
                    tp: item.type,
                    time: util.formatTime(new Date(item.time * 1000)),
                    surplus: item.surplus + surp[item.type],
                    see: item.see,
                    gender: item.gender,
                    url: '/pages/info/index?id=' + item.id,
                    tm: util.getDateDiff(item.time * 1000),
                    id: item.id
                };
                list.push(obj);
            })
            that.setData({list: list});
            // 防止首页数据小于5，前端显示加载中
            if (result.data.length < 5) {
                that.setData({nomore: true});
                return false;
            }
        })
    },
    onPullDownRefresh: function () {
        page = 1;
        this.setData({nomore: false});
        this.getList();
        wx.stopPullDownRefresh();
    },
    onShow: function () {
        this.setData({nomore: false});
        page = 1;
        this.getList();
    }
})