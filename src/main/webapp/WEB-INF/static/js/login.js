// 登录
function userLogin() {
    /**
     * 1. 获取输入框参数
     * 2. 校验参数
     * 3. 发送ajax请求
     * 4. 处理返回结果
     */
    // 1. 获取输入框参数
    var userName = $("#userName").val().trim();
    var userPwd = $("#userPwd").val().trim();
    // 选中是 true 否则 false
    var rememberMe = $("#rememberMe").is(':checked');

    // 2. 校验参数
    if (isEmpty(userName)) {
        alert("请输入用户名");
        return;
    }

    if (isEmpty(userPwd)) {
        alert("请输入密码")
        return;
    }

    // 3. 发送ajax请求
    $.ajax({
        url: "http://localhost:9999/crm/user/login",
        type: "POST",
        data: {
            userName: userName,
            userPwd: userPwd,
            rememberMe: rememberMe
        },
        dataType: "JSON",
        success: function (result) {
            if (200 == result.code) {
                // 将用户信息写入cookie，有效期一周
                writeCookie(result.result);
                alert(result.message);
                location.href = "http://localhost:9999/crm/index";
            } else {
                alert(result.message);
            }
        },
        error: function (result) {
            console.log(result);
        }
    });
}

// 将用户信息写入cookie，有效期一周
function writeCookie(user) {
    $.cookie("userIdStr", user.userIdStr, {'expires': 7, 'path': '/', 'domain': 'localhost'});
    $.cookie("userName", user.userName, {'expires': 7, 'path': '/', 'domain': 'localhost'});
    $.cookie("userInfo", user.userInfo, {'expires': 7, 'path': '/', 'domain': 'localhost'});
    $.cookie("trueName", user.trueName, {'expires': 7, 'path': '/', 'domain': 'localhost'});
}

// 记住我
$(function () {
    // 获取cookie
    var userName = $.cookie('userName');
    var userInfo = $.cookie('userInfo');

    // 判断是否有用户信息
    if (isEmpty(userName) || isEmpty(userInfo))
        return;

    // 存在的话把用户名密码填充到对应文本框
    $("#userName").val(userName);
    $("#userPwd").val($.base64.decode(userInfo));
});
