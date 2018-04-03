<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@ include file="../../common/IncHeader.jsp" %>
<div id="organizationDialog" class="crudDialog">
    <form id="organizationForm" method="post">
        <div class="form-group">
            <select id="organizationId" name="organizationId" multiple="multiple" style="width: 100%">
                <c:forEach var="upmsOrganization" items="${upmsOrganizations}">
                    <option value="${upmsOrganization.id}"
                            <c:forEach var="upmsUserOrganization" items="${upmsUserOrganizations}">
                                <c:if test="${upmsOrganization.id==upmsUserOrganization.organizationId}">selected="selected"</c:if>
                            </c:forEach>>${upmsOrganization.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group text-right dialog-buttons">
            <a class="waves-effect waves-button" href="javascript:void(0);" onclick="organizationSubmit();">保存</a>
            <a class="waves-effect waves-button" href="javascript:void(0);" onclick="organizationDialog.close();">取消</a>
        </div>
    </form>
</div>
<script type="text/javascript">
    function organizationSubmit() {
        $.ajax({
            type: 'post',
            url: '${ctx}/manage/user/organization/' + organizationUserId,
            data: $('#organizationForm').serialize(),
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
                    organizationDialog.close();
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