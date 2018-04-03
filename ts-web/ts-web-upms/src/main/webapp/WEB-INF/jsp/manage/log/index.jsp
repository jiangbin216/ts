<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@ include file="../../common/IncHeader.jsp" %>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <%@ include file="../../common/IncMeta.jsp" %>
    <title>日志管理</title>
    <%@ include file="../../common/IncLink.jsp" %>
</head>
<body>
<div id="main">
    <div id="toolbar">
        <shiro:hasPermission name="upms:log:delete">
            <a class="waves-effect waves-button" href="javascript:deleteAction();">
                <i class="zmdi zmdi-close"></i>
                删除日志</a>
        </shiro:hasPermission>
    </div>
    <table id="table"></table>
</div>
<%@ include file="../../common/IncScript.jsp" %>
<script type="text/javascript">
    var $table = $('#table');
    $(function () {
        // bootstrap table初始化
        $table.bootstrapTable({
            url: '${ctx}/manage/log/list',
            height: getHeight(),
            striped: true,
            search: true,
            showRefresh: true,
            showColumns: true,
            minimumCountColumns: 2,
            clickToSelect: true,
            // pageSize: 50,
            queryParamsType: '',
            queryParams: function (params) {
                return {
                    page: params.pageNumber,
                    size: params.pageSize,
                    search: params.searchText,
                    sort: params.sortName,
                    order: params.sortOrder
                };
            },
            detailView: true,
            detailFormatter: 'detailFormatter',
            pagination: true,
            paginationLoop: false,
            sidePagination: 'server',
            silentSort: false,
            smartDisplay: false,
            escape: true,
            searchOnEnterKey: true,
            idField: 'id',
            maintainSelected: true,
            toolbar: '#toolbar',
            columns: [
                {field: 'ck', checkbox: true},
                {field: 'id', title: '编号', sortable: true, align: 'right'},
                {field: 'description', title: '操作'},
                {field: 'username', title: '操作用户', align: 'right'},
                {field: 'startTime', title: '操作时间'},
                {field: 'spendTime', title: '耗时', align: 'center'},
                {field: 'url', title: '请求路径'},
                {field: 'method', title: '请求类型', align: 'center'},
                {field: 'parameter', title: '请求参数'},
                {field: 'userAgent', title: '用户标识'},
                {field: 'ip', title: 'IP地址'},
                {field: 'permissions', title: '权限值'}
            ]
        });
    });
    // 删除
    var deleteDialog;
    function deleteAction() {
        var rows = $table.bootstrapTable('getSelections');
        if (rows.length == 0) {
            $.confirm({
                title: false,
                content: '请至少选择一条记录！',
                autoClose: 'cancel|3000',
                backgroundDismiss: true,
                buttons: {
                    cancel: {
                        text: '取消',
                        btnClass: 'waves-effect waves-button'
                    }
                }
            });
        } else {
            deleteDialog = $.confirm({
                type: 'red',
                animationSpeed: 300,
                title: false,
                content: '确认删除该系统吗？',
                buttons: {
                    confirm: {
                        text: '确认',
                        btnClass: 'waves-effect waves-button',
                        action: function () {
                            var ids = new Array();
                            for (var i in rows) {
                                ids.push(rows[i].id);
                            }
                            $.ajax({
                                type: 'get',
                                url: '${ctx}/manage/log/delete/' + ids.join("-"),
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
                                        deleteDialog.close();
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
                    },
                    cancel: {
                        text: '取消',
                        btnClass: 'waves-effect waves-button'
                    }
                }
            });
        }
    }
</script>
</body>
</html>