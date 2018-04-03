﻿<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@ include file="../../common/IncHeader.jsp" %>
<div id="permissionDialog" class="crudDialog">
    <form id="permissionForm" method="post">
        <div class="row">
            <div class="col-sm-6">
                <label>加权限</label>
                <div class="form-group">
                    <div class="fg-line">
                        <ul id="ztree1" class="ztree"></ul>
                    </div>
                </div>
            </div>
            <div class="col-sm-6">
                <label>减权限</label>
                <div class="form-group">
                    <div class="fg-line">
                        <ul id="ztree2" class="ztree"></ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-group text-right dialog-buttons">
            <a class="waves-effect waves-button" href="javascript:void(0);" onclick="permissionSubmit();">保存</a>
            <a class="waves-effect waves-button" href="javascript:void(0);" onclick="permissionDialog.close();">取消</a>
        </div>
    </form>
</div>
<script type="text/javascript">
    var changeDatas1 = [];
    var setting1 = {
        check: {
            enable: true,
            // 勾选关联父，取消关联子
            chkboxType: {"Y": "", "N": ""}
        },
        async: {
            enable: true,
            url: '${ctx}/manage/permission/user/' + permissionUserId + '?type=1'
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onCheck: function () {
                var zTree = $.fn.zTree.getZTreeObj("ztree1");
                var changeNodes = zTree.getChangeCheckedNodes();
                changeDatas1 = [];
                for (var i = 0; i < changeNodes.length; i++) {
                    var changeData = {};
                    changeData.id = changeNodes[i].id;
                    changeData.checked = changeNodes[i].checked;
                    changeData.type = 1;
                    changeDatas1.push(changeData);
                }
            }
        }
    };
    var changeDatas2 = [];
    var setting2 = {
        check: {
            enable: true,
            // 勾选关联父，取消关联子
            chkboxType: {"Y": "", "N": ""}
        },
        async: {
            enable: true,
            url: '${ctx}/manage/permission/user/' + permissionUserId + '?type=-1'
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onCheck: function () {
                var zTree = $.fn.zTree.getZTreeObj("ztree2");
                var changeNodes = zTree.getChangeCheckedNodes();
                changeDatas2 = [];
                for (var i = 0; i < changeNodes.length; i++) {
                    var changeData = {};
                    changeData.id = changeNodes[i].id;
                    changeData.checked = changeNodes[i].checked;
                    changeData.type = -1;
                    changeDatas2.push(changeData);
                }
            }
        }
    };
    function initTree() {
        $.fn.zTree.init($('#ztree1'), setting1);
        $.fn.zTree.init($('#ztree2'), setting2);
    }

    function permissionSubmit() {
        // 合并提交
        var changeDatas = changeDatas1.concat(changeDatas2);
        $.ajax({
            type: 'post',
            url: '${ctx}/manage/user/permission/' + permissionUserId,
            data: {datas: JSON.stringify(changeDatas), permissionUserId: permissionUserId},
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