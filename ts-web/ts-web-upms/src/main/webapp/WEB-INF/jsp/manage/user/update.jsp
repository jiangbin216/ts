﻿<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@ include file="../../common/IncHeader.jsp" %>
<div id="updateDialog" class="crudDialog">
    <form id="updateForm" method="post">
        <div class="form-group">
            <label for="username">帐号</label>
            <input id="username" type="text" class="form-control" name="username" maxlength="20"
                   value="${user.username}" readonly>
        </div>
        <div class="form-group">
            <label for="realname">姓名</label>
            <input id="realname" type="text" class="form-control" name="realname" maxlength="20"
                   value="${user.realname}">
        </div>
        <div class="form-group">
            <label for="avatar">头像</label>
            <input id="avatar" type="text" class="form-control" name="avatar" maxlength="50" value="${user.avatar}">
        </div>
        <div class="form-group">
            <label for="phone">电话</label>
            <input id="phone" type="text" class="form-control" name="phone" maxlength="20" value="${user.phone}">
        </div>
        <div class="form-group">
            <label for="email">邮箱</label>
            <input id="email" type="text" class="form-control" name="email" maxlength="50" value="${user.email}">
        </div>
        <div class="radio">
            <div class="radio radio-inline radio-info">
                <input id="sex_1" type="radio" name="sex" value="1" <c:if test="${user.sex==1}">checked</c:if>>
                <label for="sex_1">男 </label>
            </div>
            <div class="radio radio-inline radio-danger">
                <input id="sex_0" type="radio" name="sex" value="0" <c:if test="${user.sex==0}">checked</c:if>>
                <label for="sex_0">女 </label>
            </div>
            <div class="radio radio-inline radio-success">
                <input id="locked_0" type="radio" name="locked" value="0" <c:if test="${user.locked==0}">checked</c:if>>
                <label for="locked_0">正常 </label>
            </div>
            <div class="radio radio-inline">
                <input id="locked_1" type="radio" name="locked" value="1" <c:if test="${user.locked==1}">checked</c:if>>
                <label for="locked_1">锁定 </label>
            </div>
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
            url: '${ctx}/manage/user/update/${user.id}',
            data: $('#updateForm').serialize(),
            beforeSend: function () {
                var $username = $('#username');
                if ($username.val() == '') {
                    $username.focus();
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