﻿<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@ include file="../../common/IncHeader.jsp" %>
<div id="permissionDialog" class="crudDialog">
    <form id="permissionForm" method="post">
        <div class="form-group">
            <ul id="ztree" class="ztree"></ul>
        </div>
        <div class="form-group text-right dialog-buttons">
            <a class="waves-effect waves-button" href="javascript:void(0);" onclick="permissionSubmit();">保存</a>
            <a class="waves-effect waves-button" href="javascript:void(0);" onclick="permissionDialog.close();">取消</a>
        </div>
    </form>
</div>
<script type="text/javascript">
    var changeDatas = [];
    var setting = {
        check: {
            enable: true,
            // 勾选关联父，取消关联子
            chkboxType: {"Y": "p", "N": "s"}
        },
        async: {
            enable: true,
            url: '${ctx}/manage/permission/role/' + roleId
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onCheck: function () {
                var zTree = $.fn.zTree.getZTreeObj("ztree");
                var changeNodes = zTree.getChangeCheckedNodes();
                changeDatas = [];
                for (var i = 0; i < changeNodes.length; i++) {
                    var changeData = {};
                    changeData.id = changeNodes[i].id;
                    changeData.checked = changeNodes[i].checked;
                    changeDatas.push(changeData);
                }
            }
        }
    };
    function initTree() {
        $.fn.zTree.init($('#ztree'), setting);
    }

    function permissionSubmit() {
        $.ajax({
            type: 'post',
            url: '${ctx}/manage/role/permission/' + roleId,
            data: {datas: JSON.stringify(changeDatas)},
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
                    permissionDialog.close();
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