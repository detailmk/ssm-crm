// 初始化可编辑数据表格
var customerId = $("#customerId").val();
$('#dg').edatagrid({
    url: ctx + '/customer/selectCustomerContactByParams?cusId=' + customerId,
    saveUrl: ctx + '/customer/saveCustomerContact?cusId=' + customerId,
    updateUrl: ctx + '/customer/updateCustomerContact?cusId=' + customerId,
    destroyUrl: ''
});

// 添加行
function add() {
    $('#dg').edatagrid('addRow');
}

// 取消行
function cancel() {
    $('#dg').edatagrid('cancelRow');
}

// 删除行
function destroy() {
    deleteData("dg", ctx + "/customer/deleteBatchCustomerContact",
        function () {
            $("#dg").edatagrid("reload");
        });
}

// 保存行
function save() {
    $('#dg').edatagrid('saveRow');
    setTimeout(function () {
        $('#dg').edatagrid('reload');
    }, 200);
}
