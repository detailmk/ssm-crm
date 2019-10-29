// 搜索
function selectRolesByParams() {
    $('#dg').datagrid('load', {
        roleName: $('#roleName').val(),
        createDate: $('#time').datebox('getValue')
    });
}

// 打开添加角色对话框
function openAddRoleDailog() {
    $("#fm").form("clear");
    openAddOrUpdateDlg("dlg", "添加角色");
}

// 打开修改角色对话框
function openModifyRoleDialog() {
    openModifyDialog("dg", "fm", "dlg", "修改角色");
}

// 关闭添加角色对话框
function closeDlg() {
    closeDlgData("dlg");
}

// 添加或修改角色信息
function saveOrUpdateRole() {
    saveOrUpdateData("fm", ctx + "/role/saveOrUpdateRole", "dlg", selectRolesByParams);
}

// 删除角色信息
function deleteRole() {
    deleteData("dg", ctx + "/role/deleteBatchRole", selectRolesByParams);
}

// 打开关联权限窗口
function openRelationPermissionDialog() {
    var rows = $('#dg').datagrid('getSelections');
    if (0 >= rows.length) {
        $.messager.alert('系统提示', '请选择一条数据进行授权', 'warning');
        return;
    }
    if (1 < rows.length) {
        $.messager.alert('系统提示', '只能选择一条数据进行授权', 'warning');
        return;
    }
    // 加载模块
    loadModule(rows[0].id);
}

// 关闭关联权限窗口
function closePermissionDlg() {
    closeDlgData("permissionDlg");
}

var treeObj;

// 加载模块
function loadModule(roleId) {
    // 设置隐藏域的roleId
    $('#roleId').val(roleId);

    // 根据roleId查询当前角色的模块权限
    $.ajax({
        url: ctx + '/module/selectAllModuleListByRoleId?roleId=' + roleId,
        success: function (data) {
            // 树 配置信息
            var setting = {
                check: {
                    enable: true,
                    chkboxType: {"Y": "ps", "N": "ps"}
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback: {
                    onCheck: zTreeOnCheck
                }
            };
            // 树 节点数据
            var zNodes = data;
            // 初始化
            treeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            openAddOrUpdateDlg('permissionDlg', '角色授权');
        }
    });
}

// 获取已勾选的节点
function zTreeOnCheck() {
    var nodes = treeObj.getCheckedNodes(true);
    // console.log(nodes);
    // moduleIds=1&moduleIds=2&moduleIds=3
    var moduleIds = '';
    for (var i = 0; i < nodes.length; i++) {
        moduleIds += 'moduleIds=' + nodes[i].id + '&';
    }
    // 设置参数至隐藏域
    // console.log(moduleIds);
    $('#moduleIds').val(moduleIds);
}

// 添加权限
function doGrant() {
    // 角色id
    var roleId = $('#roleId').val();
    // 模块id
    var moduleIds = $('#moduleIds').val();
    $.ajax({
        url: ctx + '/role/doGrant?roleId=' + roleId + '&' + moduleIds,
        success: function (result) {
            if (200 == result.code) {
                $.messager.alert('系统提示', '授权成功', 'info', function () {
                    // 关闭授权弹窗
                    closeDlgData('permissionDlg');
                    // 清除隐藏域的值
                    $('#roleId, #moduleIds').val('');
                    // 刷新表格数据
                    $('#dg').datagrid('load');
                });
            } else {
                $.messager.alert('系统提示', result.message, 'error');
            }
        }
    });
}
