// 格式化菜单等级
function formatGrade(value) {
    if (0 == value)
        return "根级菜单";
    if (1 == value)
        return "一级菜单";
    if (2 == value)
        return "二级菜单";
}

// 搜索
function selectModulesByParams() {
    $('#dg').datagrid('load', {
        moduleName: $('#moduleName').val(),
        parentId: $('#parentId').val(),
        grade: $('#grade').combobox('getValue'),
        optValue: $('#optValue').val()
    });
}

// 打开添加对话框
function openAddModuleDailog() {
    $("#fm").form("clear");
    $('#parentMenu').hide();
    openAddOrUpdateDlg('dlg', '添加模块');
}

// 打开修改对话框
function openModifyModuleDialog() {
    var rows = $("#dg").datagrid("getSelections");
    if (rows.length == 0) {
        $.messager.alert("系统提示", "请选择一条记录进行更新!");
        return;
    }
    if (rows.length > 1) {
        $.messager.alert("系统提示", "只能选择一条记录进行更新!");
        return;
    }
    // 打开修改对话框
    $("#dlg").dialog("open");
    // 手动返显数据
    $("#id").val(rows[0].id);
    $("#moduleName02").val(rows[0].moduleName);
    $("#optValue02").val(rows[0].optValue);
    $("#orders").val(rows[0].orders);
    // 返显菜单层级
    $("#grade02").combobox("setValue", rows[0].grade);
    // 判断是否显示上级菜单
    if (rows[0].parentId) {
        $('#parentMenu').show();
        // 返显上级菜单
        $('#parentId02').combobox('setValue', rows[0].parentId);
    } else {
        $('#parentMenu').hide();
    }
}

$(function () {
    // 进入页面隐藏上级菜单
    $('#parentMenu').hide();

    // 动态获取上级菜单
    $('#grade02').combobox({
        onChange: function (newValue, oldValue) {
            //console.log(newValue, oldValue);
            // 选择非根据菜单，显示上级菜单选项
            if (1 == newValue || 2 == newValue) {
                $('#parentMenu').show();
                $('#parentId02').combobox({
                    url: ctx + '/module/selectModuleByGrade?grade=' + (newValue - 1),// 上级菜单
                    valueField: 'id',
                    textField: 'moduleName'
                });
            } else {
                $('#parentMenu').hide();
                // 重置上级菜单parentId为0
                $('#parentId02').combobox('setValue', 0);
            }
        }
    });
});

// 添加或修改模块
function saveOrUpdateModule() {
    saveOrUpdateData('fm', ctx + '/module/saveOrUpdateModule', 'dlg', selectModulesByParams);
}

// 关闭对话框
function closeDlg() {
    closeDlgData("dlg");
}

// 删除模块
function deleteModule() {
    var rows = $("#dg").datagrid("getSelections");
    if (rows.length == 0) {
        $.messager.alert("系统提示", "请选择一条记录进行更新!");
        return;
    }
    $.messager.confirm("系统提示", "确定删除选中的记录?", function (r) {
        if (r) {
            $.ajax({
                url: ctx + '/module/deleteModuleByOptValue?moduleId=' + rows[0].id,
                type: 'POST',
                success: function (result) {
                    if (200 == result.code) {
                        // 刷新表格数据
                        $('#dg').datagrid('load');
                    } else {
                        $.messager.alert('系统提示', result.message, 'error');
                    }
                }
            })
        }
    });
}
