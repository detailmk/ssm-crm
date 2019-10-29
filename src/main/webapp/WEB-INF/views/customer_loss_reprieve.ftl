<!DOCTYPE html>
<html>
<head>
    <#include "common.ftl" >
</head>
<body>
<input type="hidden" id="lossId" value="${customerLoss.id}"/>
<!-- 客户流失信息展示 -->
<div id="p" class="easyui-panel" title="客户流失基本信息"
     style="width:600px;height:150px;padding:10px;background:#fafafa;"
     data-options="iconCls:'icon-save',closable:true,
                collapsible:true,minimizable:false,closable:false,maximizable:true">
    <br/>
    <label style="padding-right: 10px;padding-left: 50px">客户编号</label>
    <input readonly value="${customerLoss.cusNo}"/>
    <label style="padding-right: 35px;padding-left: 10px">客户名称</label>
    <input readonly value="${customerLoss.cusName}"/>
    <br/><br/><br/>
    <label style="padding-right: 10px;padding-left: 50px">客户经理</label>
    <input readonly value="${customerLoss.cusManager}"/>
    <label style="padding-right: 10px;padding-left: 10px">上次下单时间</label>
    <input readonly value="${customerLoss.lastOrderTime?string('yyyy/MM/dd HH:mm:ss')}"/>
</div>

<!-- 客户流失措施展示 -->
<table id="dg" style="width:600px;height:333px" class="easyui-datagrid"
       title="客户流失措施管理" toolbar="#tb" pagination="true" rownumbers="true">
    <thead>
    <tr>
        <th field="id" width="50">编号</th>
        <th field="measure" width="520" editor="{type:'validatebox',options:{required:true}}">流失措施</th>
    </tr>
    </thead>
</table>

<!-- 工具栏 -->
<div id="tb">
    <a onclick="add();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
    <a onclick="cancel();" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true">撤销行</a>
    <a onclick="save();" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true">保存</a>
    <a onclick="destroy();" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
    <a onclick="confirmLoss();" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">确认流失</a>
</div>

<script type="text/javascript" src="${ctx}/jquery-easyui-1.3.3/jquery.edatagrid.js"></script>
<script type="text/javascript" src="${ctx}/js/customer_loss_reprieve.js"></script>
</body>
</html>
