<html>
<head>
    <#include "common.ftl" >
    <script type="text/javascript" src="${ctx}/js/customer_serve.js"></script>
</head>
<body>
<div id="p" class="easyui-panel" title="客服服务创建" fit="true">
    <form id="fm" method="post">
        <table cellspacing="15px">
            <tr>
                <td>服务类型：</td>
                <td>
                    <input class="easyui-combobox" id="serveType" value="请选服务类型"
                           name="serveType" panelHeight="auto" editable="false" required="true"
                           valueField="dataDicValue" textField="dataDicValue"
                           url="${ctx}/datadic/selectDatadicByDataDicName?dataDicName=服务类型"/>
                    &nbsp;<font color="red">*</font>
                </td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>客户：</td>
                <td><input id="customer" name="customer" class="easyui-combobox"
                           valueField="name" textField="name" value="请选客户名称"
                           editable="false" required="true"
                           url="${ctx}/customer/selectCustomerNameList"/>
                    <font color="red">*</font>
                </td>
            </tr>
            <tr>
                <td>概要：</td>
                <td colspan="4">
                    <input type="text" id="overview" name="overview"
                           style="width: 480px" class="easyui-validatebox"
                           required="true"/>&nbsp;<font color="red">*</font>
                </td>
            </tr>
            <tr>
                <td>服务请求：</td>
                <td colspan="4">
 <textarea id="serviceRequest" name="serviceRequest"
           rows="10" cols="66" class="easyui-validatebox"
           required="true"></textarea>&nbsp;<font color="red">*</font>
                </td>
            </tr>
            <tr>
                <td colspan="4"></td>
                <td>
                    <a href="javascript:saveCustomerServe()"
                       class="easyui-linkbutton" iconCls="icon-ok">保存</a>&nbsp;&nbsp;
                    <a href="javascript:resetValue()"
                       class="easyui-linkbutton" iconCls="icon-reset">重置</a>
                </td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>