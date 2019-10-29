// 搜索
function selectUserByParams() {
    $('#dg').datagrid('load', {
        userName: $('#userName').val(),
        email: $('#email').val(),
        phone: $('#phone').val()
    });
}

// 打开添加用户对话框
function openAddUserDailog() {
    $("#fm").form("clear");
    openAddOrUpdateDlg("dlg", "添加用户");
}

// 打开修改用户对话框
function openModifyUserDialog() {
    openModifyDialog("dg", "fm", "dlg", "修改用户");
}

// 关闭添加用户对话框
function closeDlg() {
    closeDlgData("dlg");
}

// 添加或修改用户信息
function saveOrUpdateUser() {
    saveOrUpdateData("fm", ctx + "/user/saveOrUpdateUser", "dlg", selectUserByParams);
}

// 删除用户信息
function deleteUser() {
    deleteData("dg", ctx + "/user/deleteBatchUser", selectUserByParams);
}
