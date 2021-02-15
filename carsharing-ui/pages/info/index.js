// pages/info/index.js
var util = require('../../utils/util.js');
var app = getApp();
var page = 1;
var comment = new Array();
Page({
  data:{
    'data.id':0,
    'back':false,
    'nomore':false,
    'shoucang':false,
    'notme':false,
    'modalFlag':false
  },
  tel:function(){
    var that = this;
    wx.makePhoneCall({
      phoneNumber: that.data.data.phone
    })
  },
  tocomment:function(){
    this.setData({toview:'comment_list'});
  },
  zan:function(event){
    var that = this;
    var Commentdata = this.data.comment;
    util.req('comment/zan',{
      'cid':Commentdata[event.currentTarget.id].id,
      'sk':app.globalData.sk
      },function(data){
      if(data.status == 1){
        Commentdata[event.currentTarget.id].zan = data.zan;
        Commentdata[event.currentTarget.id].iszan = true;
        that.setData({comment:Commentdata});
      }else{
        console.log(data.msg);
        wx.showModal({
            title: '提示',
            content: data.msg,
            showCancel: false,
            success: function (res) {
            }
        });
      }
    })
  },
  shoucang:function(){
    var that = this;
    util.req('fav/addfav',{iid:that.data.data.id,sk:app.globalData.sk},function(data){
      if(data.status == 1){
        that.setData({'shoucang':true});
        wx.showToast({
          title: '收藏成功',
          icon: 'success',
          duration: 2000
        })
      }
    })
  },
  qxshoucang:function(){
    var that = this;
    util.req('fav/delfav',{iid:that.data.data.id,sk:app.globalData.sk},function(data){
      if(data.status == 1){
        that.setData({'shoucang':false});
        wx.showToast({
          title: '取消收藏成功',
          icon: 'success',
          duration: 2000
        })
      }
    })
  },
  madal:function(){
    this.setData({modalFlag:true});
  },
  modalOk:function(){
    this.setData({modalFlag:false});
  },
  appointment:function(e){
    var fId = e.detail.formId;
    var that = this;
    console.log(e.detail.value.surplus);
    if(e.detail.value.name == ''){
        util.isError('请输入姓名',that);
        return false;
    }
    if(e.detail.value.phone == ''){
        util.isError('请输入手机号',that);
        return false;
    }
    if(!(/^1[34578]\d{9}$/.test(e.detail.value.phone))){
      util.isError('手机号码错误', that);
      return false;
    }
    if(e.detail.value.surplus == 0){
        util.isError('请选择人数',that);
        return false;
    }
    util.clearError(that);
    util.postReq('http://wk.test.com:8080/appointment/add',{form_id:fId,iid:this.data.data.id,name:e.detail.value.name,phone:e.detail.value.phone,surplus:e.detail.value.surplus,sk:app.globalData.sk},function(result){
      console.log("xxxxxxxx",JSON.stringify(result));
      if(result.code == 200){
        that.setData({modalFlag:false});
        wx.showToast({
          title: '预约成功',
          icon: 'success',
          duration: 2000
        })
      }else{
        util.isError(result.data.msg,that);
        return false;
      }
    })
  },
  setsurplus:function(e){
    this.setData({surplus:e.detail.value})
  },
  onLoad:function(options){
    var that = this;
    wx.getSystemInfo({
      success: function(res) {
        that.setData({height:res.windowHeight});
      }
    })

    that.setData({
      'userInfo.gender':app.globalData.userInfo.gender,
      'userInfo.name':(app.globalData.userInfo.name == '')?app.globalData.userInfo.nickName:app.globalData.userInfo.name,
      'userInfo.phone':app.globalData.userInfo.phone
    })

    // util.req('fav/isfav',{iid:options.id,sk:app.globalData.sk},function(data){
    //   if(data.status == 1){
    //     that.setData({'shoucang':true});
    //   }
    // })
      util.req('http://wk.test.com:8080/info/details?id=' + options.id,{},function(result){
      that.setData({data:result.data});
      // if(data.data.uid == app.globalData.userInfo.id){
      //   var notme = false;
      // }else{
      //   var notme = true;
      // }
      var notme = true;
      var Surpluss = new Array('请选择人数');
      for(var i = 1; i <= result.data.surplus; i++){
        Surpluss.push(i);
      }
      that.setData({
        'data.tm':util.formatTime(new Date(result.time*1000)),
        'data.price':(result.data.price == null)?'面议':result.data.price,
        'data.gender':result.data.gender,
        'notme':notme,
        'Surpluss':Surpluss,
        'surplus':0
        });
    })   
    page = 1; 
    this.getCount(options.id);
    this.getComment(options.id);
    if(getCurrentPages().length == 1){
      that.setData({'back':true});
    }
  },
  previmg:function(e){
    var that = this;    
    wx.previewImage({
      current: that.data.comment[e.target.dataset.iid].img[e.target.dataset.id],
      urls: that.data.comment[e.target.dataset.iid].img,
    })
  },
  getComment:function(id){
    var that = this;
    util.req('http://wk.test.com:8080/comment/get',{iid:id,type:'info',page:page},function(result){
      if(result.code == 200){
        if(page == 1){          
          comment = new Array();
        }
        if (result.data.length == 0) {
          that.setData({'nomore':true});
          return false;
        } else{
            result.data.forEach(function(item){
              comment.push({
              id:item.id,
              avatarUrl:item.avatarUrl,
              content:item.content,
              fid:item.fid,
              nickName:item.nickName,
              //img:JSON.parse(item.img),
              zan:item.zan,
              reply:item.reply,
              time:util.getDateBiff(item.time*1000)
            })
          })
        }
        console.log(page+'XXXXXXXXXXXXXX'+comment);
        that.setData({comment:comment});
        // 防止首页数据小于20，前端显示加载中
        if (result.data.length < 20) {
          that.setData({'nomore':true});
          return false;
        }
      }
    })
  },
  toIndex:function(){
    wx.reLaunch({
      url: '/pages/index/index'
    })
  },
  onShareAppMessage: function () { 
    return {
      title: '拼车详情',
      path: 'http://wk.test.com:8080/pages/info/index?id='+this.data.data.id
    }
  },
  getCount:function(id){  
    var that = this;
    util.req('http://wk.test.com:8080/comment/getCount',{id:id,type:'info'},function(result){  //获取评论总数
      if(result.code == 200){
        that.setData({comnum:result.data});
      }
    })
  },
  onShow:function(){      
    page = 1; 

    if(this.data.data) {
      this.getCount(this.data.data.id);
      this.getComment(this.data.data.id);
    }
  },
  tobottom:function(){
    if(!this.data.nomore){
      page++;
      this.getComment(this.data.data.id);
    }
  }
})