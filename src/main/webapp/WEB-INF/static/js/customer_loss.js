// 格式化流失状态
function formateState(value) {
    if (value == 1) return '暂缓流失';
    if (value == 2) return '已流失';
    return value;
}

// 格式化操作
function formateOp(value, row) {
    var state = row.state;
    if (1 == state) {
        var href = "javascript:openAddReprieveDataTab(" + row.id + ")";
        return "<a href=" + href + ">添加暂缓措施</a>";
    }
    if (2 == state) return "确认流失";
    return value;
}

// 搜索
function selectCustomerLossByParams() {
    $('#dg').datagrid('load', {
        cusName: $('#cusName').val(),
        cusNo: $('#cusNo').val(),
        createDate: $('#time').datebox('getValue')
    });
}

// 打开添加暂缓措施窗口
function openAddReprieveDataTab(lossId) {
    parent.openTab("添加暂缓措施_" + lossId, ctx + "/customerLoss/reprieve/index?lossId=" + lossId);
}
