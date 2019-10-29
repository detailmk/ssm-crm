<html>
<head>
    <#include "common.ftl" >
    <script type="text/javascript" src="${ctx}/js/customer_serve.js"></script>
</head>
<body style="margin: 1px">
<table id="dg" class="easyui-datagrid"
       pagination="true" rownumbers="true"
       url="${ctx}/customerServe/selectCustomerServeByParams?state=4"
       fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="50" align="center">编号</th>
        <th field="customer" width="200" align="center">客户名称</th>
        <th field="overview" width="200" align="center">概要</th>
        <th field="serveType" width="100" align="center">服务类型</th>
        <th field="createPeople" width="100" align="center">服务创建人</th>
        <th field="createDate" width="160" align="center">创建时间</th>
        <th field="assigner" width="50" align="center">分配人</th>
        <th field="assignTime" width="150" align="center">分配时间</th>
        <th field="serviceProcePeople" width="100" align="center">服务处理人</th>
        <th field="serviceProceTime" width="150" align="center">服务处理时间</th>
        <th field="serviceProceResult" width="100" align="center">处理结果</th>
        <th field="myd" width="100" align="center">满意度</th>
    </tr>
    </thead>
</table>
<div id="tb">
    客户名称:<input type="text" id="customer"/>
    概要:<input type="text" id="overview"/>
    服务类型:
    <input class="easyui-combobox" id="serveType" value="请选服务类型"
           name="serveType" panelHeight="auto" editable="false" required="true"
           valueField="dataDicValue" textField="dataDicValue"
           url="${ctx}/datadic/selectDatadicByDataDicName?dataDicName=服务类型"/>
    创建时间:<input id="time" type="text" class="easyui-datebox"></input>
    <a href="javascript:selectCustomerServeByParams()"
       class="easyui-linkbutton" iconCls="icon-search"
       plain="true">搜索</a>
</div>

</body>
</html>
