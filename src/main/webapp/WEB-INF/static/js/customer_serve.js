// 添加服务
function saveCustomerServe() {
    $("#fm").form("submit", {
        url: ctx + '/customerServe/saveOrUpdateCustomerServe',
        onSubmit: function () {
            return $(this).form("validate");
        },
        success: function (data) {
            var result = JSON.parse(data);
            if (200 == result.code) {
                $.messager.alert("系统提示", result.message, "info", function () {
                    $("#fm").form("clear");// 清空表单
                });
            } else {
                $.messager.alert("系统提示", data.message, "error");
            }
        }
    })
}

// 重置
function resetValue() {
    $("#fm").form("clear");
}

// 打开服务对话框
function openCustomerServeDialog(title) {
    openModifyDialog('dg', 'fm', 'dlg', title);
}

// 处理服务，修改服务状态
function updateCustomerServe() {
    saveOrUpdateData("fm", ctx + "/customerServe/saveOrUpdateCustomerServe",
        "dlg", function () {
            $('#dg').datagrid('load');
        });
}

// 关闭服务对话框
function closeCustomerServeDialog() {
    closeDlgData("dlg");
}

// 搜索
function selectCustomerServeByParams() {
    $('#dg').datagrid('load', {
        customer: $('#customer').val(),
        overview: $('#overview').val(),
        serveType: $('#serveType').combobox('getValue'),
        createDate: $('#time').datebox('getValue')
    });
}
