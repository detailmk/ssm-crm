// 初始化可编辑数据表格
var lossId = $("#lossId").val();
$('#dg').edatagrid({
    url: ctx + '/customerLoss/selectCustomerReprieveByParams?lossId=' + lossId,
    saveUrl: ctx + '/customerLoss/saveCustomerReprieve?lossId=' + lossId,
    updateUrl: ctx + '/customerLoss/updateCustomerReprieve?lossId=' + lossId,
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
    deleteData("dg", ctx + "/customerLoss/deleteBatchCustomerReprieve",
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

// 确认流失
function confirmLoss() {
    $.messager.prompt('系统提示', '请输入流失原因：', function (r) {
        if (r) {
            $.post(ctx + '/customerLoss/confirmLoss', {
                lossId: lossId,
                lossReason: r
            }, function (result) {
                if (200 == result.code) {
                    $.messager.alert('系统提示', result.message, 'info', function () {
                        // 隐藏工具栏
                        $("#tb").hide();
                    });
                } else {
                    $.messager.alert('系统提示', result.message, 'error');
                }
            });
        }
    });
}
