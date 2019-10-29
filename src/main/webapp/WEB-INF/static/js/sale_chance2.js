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
    openAddOrUpdateDlg("dlg", "添加营销机会");
}

// 关闭添加对话框
function closeDlg() {
    closeDlgData("dlg");
}

// 添加
function saveOrUpdateSaleChance() {
    saveOrUpdateData("fm", ctx + '/saleChance/saveOrUpdateSaleChance',
        "dlg", selectSaleChancesByParams);
}

// 打开修改对话框
function openModifySaleChanceDialog() {
    openModifyDialog("dg", "fm", "dlg", "修改营销机会");
}

// 逻辑删除
function deleteSaleChance() {
    deleteData("dg", ctx + "/saleChance/deleteBatchSaleChance",
        selectSaleChancesByParams);
}
