// 格式化开发状态
function formatterState(value) {
    if ('0' == value) return '正常';
    if ('1' == value) return '暂时流失';
    if ('2' == value) return '确认流失';
    return value;
}

// 搜索
function selectCustomersByParams() {
    $('#dg').datagrid('load', {
        name: $('#cusName').val(),
        khno: $('#khno').val(),
        fr: $('#fr').val()
    });
}

// 打开添加对话框
function openAddCustomerDialog() {
    $("#fm").form("clear");
    openAddOrUpdateDlg("dlg", "添加客户");
}

// 打开修改对话框
function openModifyCustomerDialog() {
    openModifyDialog("dg", "fm", "dlg", selectCustomersByParams);
}

// 添加或修改客户
function saveOrUpdateCustomer() {
    saveOrUpdateData("fm", ctx + "/customer/saveOrUpdateCustomer",
        "dlg", selectCustomersByParams);
}

// 删除客户
function deleteCustomer() {
    deleteData("dg", ctx + "/customer/deleteBatchCustomer", selectCustomersByParams);
}

// 关闭对话框
function closeCustomerDialog() {
    closeDlgData("dlg");
}

// 打开联系人管理
function openCustomerOtherInfo(title, flag) {
    var rows = $("#dg").datagrid("getSelections");
    if (rows.length == 0) {
        $.messager.alert("系统提示", "请选择一条记录", "warning");
        return;
    }
    if (rows.length > 1) {
        $.messager.alert("系统提示", "只能选择一条记录", "warning");
        return;
    }

    var customerId = rows[0].id;
    if (1 == flag)
        parent.openTab(title + "_" + customerId, ctx + '/customer/index/1?id=' + customerId, 'icon-lxr');
    if (2 == flag)
        parent.openTab(title + "_" + customerId, ctx + '/customer/index/2?id=' + customerId, 'icon-jwjl');
    if (3 == flag)
        parent.openTab(title + "_" + customerId, ctx + '/customer/index/3?id=' + customerId, 'icon-lsdd');
}
