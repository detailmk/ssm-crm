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
<table id="dg" style="width:600px;height:333px"
       title="联系人信息管理" toolbar="#tb" pagination="true" rownumbers="true">
    <thead>
    <tr>
        <th field="id" width="30">编号</th>
        <th field="linkName" width="83" editor="{type:'validatebox',options:{required:true}}">客户联系人</th>
        <th field="sex" width="50" editor="{type:'combobox',
                    options:{
		   				valueField:'id',
		   				textField:'name',
		   				data:[{id:'男',name:'男'},{id:'女',name:'女'}],
		   				required:true,panelHeight:50
		   		    }}">性别
        </th>
        <th field="zhiwei" width="100" editor="text">职位</th>
        <th field="officePhone" width="150" editor="{type:'validatebox',options:{required:true}}">办公电话</th>
        <th field="phone" width="150" editor="text">手机号码</th>
    </tr>
    </thead>
</table>

<!-- 工具栏 -->
<div id="tb">
    <a onclick="add();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
    <a onclick="cancel();" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true">取消</a>
    <a onclick="save();" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true">保存</a>
    <a onclick="destroy();" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
</div>

<script type="text/javascript" src="${ctx}/jquery-easyui-1.3.3/jquery.edatagrid.js"></script>
<script type="text/javascript" src="${ctx}/js/customer_linkman.js"></script>
</body>
</html>