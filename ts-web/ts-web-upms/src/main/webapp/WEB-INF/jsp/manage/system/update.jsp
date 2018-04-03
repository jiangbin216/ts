<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@ include file="../../common/IncHeader.jsp" %>
<div id="updateDialog" class="crudDialog">
    <form id="updateForm" method="post">
        <div class="form-group">
            <label for="icon">图标</label>
            <div class="input-group">
                <!-- 这里要用readonly，不能用disable，否在jquery.serialize()取不到该编辑框的值 -->
                <input id="icon" type="text" class="form-control" name="icon" maxlength="50" value="${system.icon}" readonly/>
                <div id="icon-btn" class="input-group-addon">选择图标</div>
            </div>
        </div>
        <div class="form-group">
            <label for="title">系统标题</label>
            <input id="title" type="text" class="form-control" name="title" maxlength="20" value="${system.title}"/>
        </div>
        <div class="form-group">
            <label for="name">系统名称</label>
            <input id="name" type="text" class="form-control" name="name" maxlength="20" value="${system.name}"/>
        </div>
        <div class="form-group">
            <label for="theme">主题</label>
            <input id="theme" type="color" class="form-control" name="theme" maxlength="50" value="${system.theme}"/>
        </div>
        <div class="form-group">
            <label for="description">描述</label>
            <input id="description" type="text" class="form-control" name="description" maxlength="300"
                   value="${system.description}"/>
        </div>
        <div class="form-group">
            <label for="basepath">根目录</label>
            <input id="basepath" type="text" class="form-control" name="basepath" maxlength="100"
                   value="${system.basepath}"/>
        </div>
        <div class="radio">
            <div class="radio radio-inline radio-success">
                <input id="status_1" type="radio" name="status" value="1"
                       <c:if test="${system.status==1}">checked</c:if> />
                <label for="status_1">正常 </label>
            </div>
            <div class="radio radio-inline">
                <input id="status_0" type="radio" name="status" value="-1"
                       <c:if test="${system.status!=1}">checked</c:if> />
                <label for="status_0">锁定 </label>
            </div>
        </div>
        <div class="form-group text-right dialog-buttons">
            <a class="waves-effect waves-button" href="javascript:void(0);" onclick="createSubmit();">保存</a>
            <a class="waves-effect waves-button" href="javascript:void(0);" onclick="updateDialog.close();">取消</a>
        </div>
    </form>
</div>
<script type="text/javascript">
    $(function() {
        $('#icon-btn').materialDesignIconicFont($('#icon'), 48);
    });
    function createSubmit() {
        $.ajax({
            type: 'post',
            url: '${ctx}/manage/system/update/${system.id}',
            data: $('#updateForm').serialize(),
            beforeSend: function () {
                var $title = $('#title');
                if ($title.val() == '') {
                    $title.focus();
                    return false;
                }
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