/**
 * Created by Administrator on 2016/10/14.
 * 系统参数  统一配置
 */
var baseUrl = '//localhost:8084';


/**
 * 系统资源访问路径  统一配置
 * resUrl.sys.toLogin
 */
    // 1.http请求接口地址
var dataUrl = {
    //访客欢迎
    customer:{
        show:"/customer"
    },
    //用户管理：
    users:{
        list:"/user/listAll"
    },
    //IO操作
    io:{

    }
}

// 2.window.location.href 页面导航布局地址
var viewPath = {
    base:"/views",
    denied:"/denied.html",
    //用户管理：
    users:{
       list:"/user/list.html"
    }
}