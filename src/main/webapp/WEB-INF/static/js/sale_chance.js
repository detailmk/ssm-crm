// 格式化显示
/**
 * @param value  字段值。
 * @param row 行记录数据。
 * @param index 行索引。
 */
function formatState(value, row, index) {
    if (0 == value) {
        return "未分配";
    }
    if (1 == value) {
        return "已分配";
    }
}

function formatDevResult(value) {
    if (0 == value) {
        return "未开发";
    }
    if (1 == value) {
        return "开发中";
    }
    if (2 == value) {
        return "开发成功";
    }
    if (3 == value) {
        return "开发失败";
    }
}

// 更改背景颜色
$(function () {
    // 当 datagrid 加载完成后再执行
    $('#dg').datagrid({
        rowStyler: function (index, row) {
            // 获取开发状态字段的值
            var devResult = row.devResult;
            if (0 == devResult) {
                return "background-color:#5bc0de;"; // 蓝色
            } else if (1 == devResult) {
                return "background-color:#f0ad4e;"; // 黄色
            } else if (2 == devResult) {
                return "background-color:#5cb85c;"; // 绿色
            } else if (3 == devResult) {
                return "background-color:#d9534f;"; // 红色
            }
        }
    });
});

// 查询
function selectSaleChancesByParams() {
    $('#dg').datagrid('load', {
        customerName: $('#customerName').val(),
        state: $('#state').combobox('getValue'),
        devResult: $('#devResult').combobox('getValue'),
        createDate: $('#time').datebox('getValue')
    });
}

// 打开添加对话款
function openAddSaleChacneDialog() {
    // 清空表单数据
    $("#fm").form("clear");
    $("#dlg").dialog("open");
}

// 关闭添加对话框
function closeDlg() {
    $("#dlg").dialog("close");
}

// 添加
function saveOrUpdateSaleChance() {
    $('#fm').form('submit', {
        url: ctx + '/saleChance/saveOrUpdateSaleChance',
        onSubmit: function () {
            return $(this).form('validate');
        },
        success: function (data) {
            var result = JSON.parse(data);
            if (200 == result.code) {
                $.messager.alert("系统提示", result.message, "info", function () {
                    // 清空表单数据
                    $("#fm").form("clear");
                    // 关闭添加对话框
                    $("#dlg").dialog("close");
                    // 重新加载数据
                    $("#dg").datagrid("reload");
                });
            } else {
                $.messager.alert('系统提示', result.message, 'error');
            }
        }
    });
}

// 打开修改对话框
function openModifySaleChanceDialog() {
    var rows = $("#dg").datagrid("getSelections");
    if (0 >= rows.length) {
        $.messager.show({
            title: '系统提示',
            msg: '请选择一条数据',
            timeout: 2000,
            showType: 'slide'
        });
        return;
    }
    if (1 < rows.length) {
        $.messager.show({
            title: '系统提示',
            msg: '只能选择一条数据',
            timeout: 2000,
            showType: 'slide'
        });
        return;
    }
    // 打开修改对话框
    $("#dlg").dialog("open");
    // 返显数据
    $("#fm").form("load", rows[0]);
}

// 逻辑删除
function deleteSaleChance() {
    var rows = $("#dg").datagrid("getSelections");
    if (0 >= rows.length) {
        $.messager.show({
            title: '系统提示',
            msg: '请选择一条数据',
            timeout: 2000,
            showType: 'slide'
        });
        return;
    }

    $.messager.confirm("系统提示", "确定删除 " + rows.length + " 条数据？", function (r) {
        if (r) {
            var ids = "";
            for (var i = 0; i < rows.length; i++) {
                ids += "ids=" + rows[i].id + "&";
            }
            $.ajax({
                url: ctx + "/saleChance/deleteBatchSaleChance?" + ids,
                type: "POST",
                dataType: "JSON",
                success: function (result) {
                    if (200 == result.code) {
                        $.messager.alert("系统提示", result.message, "info", function () {
                            // 重新加载数据
                            $("#dg").datagrid("reload");
                        });
                    } else {
                        $.messager.alert('系统提示', result.message, 'error');
                    }
                }
            });
        }
    });
}
