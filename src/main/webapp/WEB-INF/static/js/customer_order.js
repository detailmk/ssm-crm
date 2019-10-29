// 初始化可编辑数据表格
var customerId = $("#customerId").val();
$('#dg').edatagrid({
    url: ctx + '/customer/selectCustomerOrderByParams?cusId=' + customerId
});

// 格式化状态
function formatterState(value) {
    if ('0' == value) return '未回款';
    if ('1' == value) return '已回款';
    return value;
}

// 操作
function handler(value, row, index) {
    return '<a href="javascript:detail(' + row.id + ');">查看详情</a>'
}

// 查看详情对话框
function detail(orderId) {
    // 根据订单主键查询订单
    $.get(ctx + '/customer/selectCustomerOrderAndTotalPriceById?id=' + orderId, function (result) {
        var obj = result.result;
        // 返显数据
        $('#orderNo').val(obj.orderNo);
        $('#orderDate').val(obj.orderDate);
        $('#address').val(obj.address);
        $('#totalPrice').val(obj.totalPrice);
        if (0 == obj.state) {
            $('#state').val('未回款');
        } else if (1 == obj.state) {
            $('#state').val('已回款');
        }
    });

    // 初始化编辑器
    $('#dg2').edatagrid({
        url: ctx + '/customer/selectOrderDetailsByOrderId?orderId=' + orderId
    });

    // 打开对话框
    $('#order_detail_dialog').dialog('open');
}

// 初始化查看详情对话框
$('#order_detail_dialog').dialog({
    title: '订单详情',
    width: 700,
    height: 420,
    iconCls: 'icon-lsdd',
    resizable: false,
    modal: true, // 模态
    draggable: false, // 不可移动
    closed: true, // 是否关闭
    buttons: [{
        text: '关闭',
        iconCls: 'icon-cancel',
        handler: function () {
            $('#order_detail_dialog').dialog('close');
        }
    }]
});
