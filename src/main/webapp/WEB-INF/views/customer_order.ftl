<!DOCTYPE html>
<html>
<head>
    <#include "common.ftl" >
</head>
<body>
<input type="hidden" id="customerId" value="${customer.id}"/>
<!-- 客户信息展示 -->
<div id="p" class="easyui-panel" title="客户基本信息"
     style="width:600px;height:150px;padding:10px;background:#fafafa;"
     data-options="iconCls:'icon-save',closable:true,
                collapsible:true,minimizable:false,closable:false,maximizable:true">
    <br/><br/>
    <label style="padding-right: 10px;padding-left: 50px">客户编号</label><input readonly value="${customer.khno}"/>
    <label style="padding-right: 10px;padding-left: 10px">客户名称</label><input readonly value="${customer.name}"/>
</div>

<!-- 联系人信息展示 -->
<table id="dg" style="width:600px;height:333px" class="easyui-datagrid"
       title="历史订单" pagination="true" rownumbers="true"
       singleSelect="true">
    <thead>
    <tr>
        <th field="id" width="30">编号</th>
        <th field="orderNo" width="100">订单号</th>
        <th field="orderDate" width="150">订购日期</th>
        <th field="address" width="150">送货地址</th>
        <th field="state" formatter="formatterState" width="60">状态</th>
        <th field="overview" width="70" formatter="handler">操作</th>
    </tr>
    </thead>
</table>

<!-- 查看详情对话框 -->
<div id="order_detail_dialog" class="easyui-dialog" data-options="closed:true" style="padding: 10px 20px">
    <table cellpadding="10px">
        <tr>
            <td style="padding-right: 10px">订单号</td>
            <td>
                <input readonly id="orderNo"/>
            </td>
            <td style="padding-left: 70px;padding-right: 10px">订购日期</td>
            <td><input readonly id="orderDate"/></td>
        </tr>
        <tr>
            <td style="padding-right: 10px">送货地址</td>
            <td>
                <input readonly id="address"/>
            </td>
            <td style="padding-left: 70px;padding-right: 10px">总金额</td>
            <td>
                <input readonly id="totalPrice"/>
            </td>
        </tr>
        <tr>
            <td style="padding-right: 10px">状态</td>
            <td colspan="3"><input id="state"/></td>
        </tr>
    </table>
    <!-- 订单详情 -->
    <table id="dg2" style="width:600px;height:333px;padding-left: 10px" class="easyui-datagrid"
           title="订单详情" pagination="true" rownumbers="true">
        <thead>
        <tr>
            <th field="id" width="30">编号</th>
            <th field="goodsName" width="100">商品名称</th>
            <th field="goodsNum" width="150">订购数量</th>
            <th field="unit" width="150">单位</th>
            <th field="price" width="60">单价</th>
            <th field="sum" width="70">总金额</th>
        </tr>
        </thead>
    </table>
</div>

<script type="text/javascript" src="${ctx}/jquery-easyui-1.3.3/jquery.edatagrid.js"></script>
<script type="text/javascript" src="${ctx}/js/customer_order.js"></script>
</body>
</html>