<!DOCTYPE html>
<html lang="en">
<head>
    <#include "common.ftl" >
    <meta charset="utf-8"/>
    <title>CRM | 登录</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <link href="${ctx}/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/css/login.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
    <!-- BEGIN BODY -->
<body class="login">
<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
<div class="menu-toggler sidebar-toggler">
</div>
<!-- END SIDEBAR TOGGLER BUTTON -->
<!-- BEGIN LOGO -->
<div class="logo">
</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="content">
    <!-- BEGIN LOGIN FORM -->
    <form class="login-form" action="index.html" method="get">
        <h3 class="form-title">CRM 登录</h3>
        <div class="alert alert-danger display-hide">
            <button class="close" data-close="alert"></button>
            <span>
			用户名</span>
        </div>
        <div class="form-group">
            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
            <label class="control-label visible-ie8 visible-ie9">用户名</label>
            <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off"
                   placeholder="用户名" name="userName" id="userName"/>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">密码</label>
            <input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off"
                   placeholder="密码" name="userPwd" id="userPwd"/>
        </div>
        <div class="form-group">
            <input type="checkbox" id="rememberMe" name="rememberMe" value="1"/>
            <span style="color: #6c778b;">记住我</span>
        </div>
        <div class="form-actions">
            <button type="button" onclick="userLogin();" class="btn btn-success uppercase btn-xs btn-block">登录</button>
        </div>
    </form>
    <!-- END LOGIN FORM -->
</div>
<div class="copyright">
    上海尚学堂地址：上海市松江区荣乐东路2369弄45号绿地伯顿大厦2层 咨询电话：021-67690939<br/>
    上海尚学堂智能科技有限公司 的icp备案号 （沪ICP备16053543号）
</div>

<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->

<!-- 引入 jQuer -->
<script type="text/javascript" src="${ctx}/jquery-easyui-1.3.3/jquery.min.js"></script>
<!-- 引入公共 js 内部封装了一些工具函数 -->
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.base64.js"></script>
<!-- 引入自己编写的 js -->
<script type="text/javascript" src="${ctx}/js/login.js"></script>
</html>