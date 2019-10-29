// 初始化可编辑数据表格
var saleChanceId = $("#saleChanceId").val();
$('#dg').edatagrid({
    url: ctx + '/cusDevPlan/selectCusDevPlanByParams?saleChanceId=' + saleChanceId,
    saveUrl: ctx + '/cusDevPlan/saveOrUpdateCusDevPlan?saleChanceId=' + saleChanceId,
    updateUrl: ctx + '/cusDevPlan/saveOrUpdateCusDevPlan?saleChanceId=' + saleChanceId,
    destroyUrl: ''
});

// 新增一行空白记录
function addRow() {
    $("#dg").edatagrid("addRow");
}

// 添加或修改客户开发计划
function saveOrUpdateCusDevPlan() {
    $("#dg").edatagrid("saveRow");
    $("#dg").edatagrid("reload");
}

// 逻辑删除客户开发计划
function delCusDevPlan() {
    deleteData("dg", ctx + "/cusDevPlan/deleteBatchCusDevPlan", function () {
        $("#dg").edatagrid("reload");
    });
}

// 开发成功或终止开发
function updateSaleChanceDevResult(devResult) {
    $.ajax({
        url: ctx + "/saleChance/updateSaleChanceDevResult",
        type: "POST",
        data: {
            id: saleChanceId,
            devResult: devResult
        },
        dataType: "JSON",
        success: function (result) {
            if (200 == result.code) {
                $.messager.alert('系统提示', result.message, 'info', function () {
                    // 隐藏工具栏
                    $("#toolbar").hide();
                    // 表格不可编辑
                    $("#dg").edatagrid("disableEditing");
                });
            } else {
                $.messager.alert('系统提示', result.message, 'error');
            }
        },
        error: function (result) {
            console.log(result);
        }
    });
}

// 进入页面根据开发状态判断是否显示操作菜单
$(function () {
   var devResult = $("#devResult").val();
   if (2 == devResult || 3 == devResult) {
       // 隐藏工具栏
       $("#toolbar").hide();
       // 表格不可编辑
       $("#dg").edatagrid("disableEditing");
   }
});
