<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@ include file="../../common/IncHeader.jsp" %>
<div id="roleDialog" class="crudDialog">
    <form id="roleForm" method="post">
        <div class="form-group">
            <select id="roleId" name="roleId" multiple="multiple" style="width: 100%">
                <c:forEach var="upmsRole" items="${upmsRoles}">
                    <option value="${upmsRole.id}"
                            <c:forEach var="upmsUserRole" items="${upmsUserRoles}">
                            <c:if test="${upmsRole.id==upmsUserRole.roleId}">selected="selected"</c:if>
                    </c:forEach>>${upmsRole.title}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group text-right dialog-buttons">
            <a class="waves-effect waves-button" href="javascript:void(0);" onclick="roleSubmit();">保存</a>
            <a class="waves-effect waves-button" href="javascript:void(0);" onclick="roleDialog.close();">取消</a>
        </div>
    </form>
</div>
<script type="text/javascript">
    function roleSubmit() {
        $.ajax({
            type: 'post',
            url: '${ctx}/manage/user/role/' + roleUserId,
            data: $('#roleForm').serialize(),
            beforeSend: function () {

            },
            success: function (result) {
                if (result.code != 1) {
                    if (result.data instanceof Array) {
                        $.each(result.data, function (index, value) {
                            $.confirm({
                                theme: 'dark',
                                animation: 'rotateX',
                                closeAnimation: 'rotateX',
                                title: false,
                                content: value.errorMsg,
                                buttons: {
                                    confirm: {
                                        text: '确认',
                                        btnClass: 'waves-effect waves-button waves-light'
                                    }
                                }
                            });
                        });
                    } else {
                        $.confirm({
                            theme: 'dark',
                            animation: 'rotateX',
                            closeAnimation: 'rotateX',
                            title: false,
                            content: result.data.errorMsg,
                            buttons: {
                                confirm: {
                                    text: '确认',
                                    btnClass: 'waves-effect waves-button waves-light'
                                }
                            }
                        });
                    }
                } else {
                    roleDialog.close();
                    $table.bootstrapTable('refresh');
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $.confirm({
                    theme: 'dark',
                    animation: 'rotateX',
                    closeAnimation: 'rotateX',
                    title: false,
                    content: textStatus,
                    buttons: {
                        confirm: {
                            text: '确认',
                            btnClass: 'waves-effect waves-button waves-light'
                        }
                    }
                });
            }
        });
    }
</script>