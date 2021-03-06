﻿<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@ include file="../../common/IncHeader.jsp" %>
<div id="updateDialog" class="crudDialog">
    <form id="updateForm" method="post">
        <div class="form-group">
            <label for="name">名称</label>
            <input id="name" type="text" class="form-control" name="name" maxlength="20" value="${organization.name}">
        </div>
        <div class="form-group">
            <label for="description">描述</label>
            <input id="description" type="text" class="form-control" name="description" maxlength="300"
                   value="${organization.description}">
        </div>
        <div class="form-group text-right dialog-buttons">
            <a class="waves-effect waves-button" href="javascript:void(0);" onclick="createSubmit();">保存</a>
            <a class="waves-effect waves-button" href="javascript:void(0);" onclick="updateDialog.close();">取消</a>
        </div>
    </form>
</div>
<script type="text/javascript">
    function createSubmit() {
        $.ajax({
            type: 'post',
            url: '${ctx}/manage/organization/update/${organization.id}',
            data: $('#updateForm').serialize(),
            beforeSend: function () {
                var $name = $('#name');
                if ($name.val() == '') {
                    $name.focus();
                    return false;
                }
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
                    updateDialog.close();
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