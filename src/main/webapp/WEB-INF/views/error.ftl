<#include "common.ftl" >
<script type="text/javascript">
    $(function () {
        $.messager.alert('系统提示', '${errorMsg}', 'error');
        setTimeout(function () {
            if('${uri}' == '/index'){
                location.href = "${ctx}/login";
            }else{
                parent.location.href = "${ctx}/login";
            }
        }, 2000);
    });
</script>
