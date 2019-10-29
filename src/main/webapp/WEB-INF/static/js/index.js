function openTab(text, url, iconCls) {
    if ($("#tabs").tabs("exists", text)) {
        $("#tabs").tabs("select", text);
    } else {
        var content = "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='" + url + "'></iframe>";
        $("#tabs").tabs("add", {
            title: text,
            iconCls: iconCls,
            closable: true,
            content: content
        });
    }
}

// 安全退出
function userLogout() {
    $.messager.confirm("系统提示", "是否要退出？", function (result) {
        if (result) {
            $.removeCookie("userIdStr", {'expires': 7, 'path': '/', 'domain': 'localhost'});
            $.removeCookie("userName", {'expires': 7, 'path': '/', 'domain': 'localhost'});
            $.removeCookie("userInfo", {'expires': 7, 'path': '/', 'domain': 'localhost'});
            $.removeCookie("trueName", {'expires': 7, 'path': '/', 'domain': 'localhost'});
            location.href = ctx + "/user/logout";
        }
    });
}

// 打开修改密码对话框
function openPasswordModifyDialog() {
    $("#dlg").dialog("open");
}

// 关闭修改密码对话框，清除对话框内容
function closePasswordModifyDialog() {
    $("#oldPassword").val("");
    $("#newPassword").val("");
    $("#confirmPassword").val("");
    $("#dlg").dialog("close");
}

// 修改密码
function modifyPassword() {
    $('#fm').form('submit', {
        url: ctx + '/user/updateUserPwd',
        onSubmit: function () {
            console.log($(this).form('validate'));
            return $(this).form('validate');
        },
        success: function (data) {
            var result = JSON.parse(data);
            if (200 == result.code) {
                $.messager.alert("系统提示", result.message, "info", function () {
                    $.removeCookie("userIdStr", {'expires': 7, 'path': '/', 'domain': 'localhost'});
                    $.removeCookie("userName", {'expires': 7, 'path': '/', 'domain': 'localhost'});
                    $.removeCookie("userInfo", {'expires': 7, 'path': '/', 'domain': 'localhost'});
                    $.removeCookie("trueName", {'expires': 7, 'path': '/', 'domain': 'localhost'});
                    location.href = ctx + '/user/logout';
                });
            } else {
                $.messager.alert('系统提示', result.message, 'error');
            }
        }
    });
}
