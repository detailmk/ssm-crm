// 格式化显示
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

function formatterOp(value, row) {
    var devResult = row.devResult;
    if (0 == devResult || 1 == devResult) {
        var href = "javascript:openSaleChanceInfoDialog(" + '"开发机会数据"' + "," + row.id + ")";
        return "<a href='" + href + "'>开发</a>";
    }
    if (2 == devResult || 3 == devResult) {
        var href = "javascript:openSaleChanceInfoDialog(" + '"详情机会数据"' + "," + row.id + ")";
        return "<a href='" + href + "'>查看详情</a>";
    }
}

// 查询
function selectSaleChancesByParams() {
    $('#dg').datagrid('load', {
        customerName: $('#customerName').val(),
        devResult: $('#devResult').combobox('getValue'),
        createDate: $('#time').datebox('getValue')
    });
}

// 打开新选项卡
function openSaleChanceInfoDialog(title, id) {
    window.parent.openTab(title + "_" + id, ctx + "/cusDevPlan/index?sid=" + id, 'icon-khkfjh');
}
