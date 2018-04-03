<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="../../common/IncHeader.jsp" %>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <%@ include file="../../common/IncMeta.jsp" %>
    <title>组织管理</title>
    <%@ include file="../../common/IncLink.jsp" %>
</head>
<body>
<div id="main">
    <div id="toolbar">
        <shiro:hasPermission name="upms:organization:create">
            <a class="waves-effect waves-button" href="javascript:void(0);"
               onclick="createAction()">
                <i class="zmdi zmdi-plus"></i>
                新增组织</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="upms:organization:update">
            <a class="waves-effect waves-button" href="javascript:void(0);"
               onclick="updateAction()">
                <i class="zmdi zmdi-edit"></i>
                编辑组织</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="upms:organization:delete">
            <a class="waves-effect waves-button" href="javascript:void(0);"
               onclick="deleteAction()">
                <i class="zmdi zmdi-close"></i>
                删除组织</a></shiro:hasPermission>
    </div>
    <table id="table"></table>
</div>
<%@ include file="../../common/IncScript.jsp" %>
<script type="text/javascript">
    var $table = $('#table');
    $(function () {
        // bootstrap table初始化
        $table.bootstrapTable({
            url: '${ctx}/manage/organization/list',
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
                {field: 'ck', radio: true},
                {field: 'id', title: '编号', sortable: true, align: 'center'},
                {field: 'name', title: '组织名称'},
                {field: 'description', title: '组织描述'}
            ]
        });
    });
    // 新增
    var createDialog;
    function createAction() {
        createDialog = $.dialog({
            animationSpeed: 300,
            title: '新增组织',
            content: 'url:${ctx}/manage/organization/create',
            onContentReady: function () {
                initMaterialInput();
            }
        });
    }
    // 编辑
    var updateDialog;
    function updateAction() {
        var rows = $table.bootstrapTable('getSelections');
        if (rows.length != 1) {
            $.confirm({
                title: false,
                content: '请选择一条记录！',
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
            updateDialog = $.dialog({
                animationSpeed: 300,
                title: '编辑组织',
                content: 'url:${ctx}/manage/organization/update/' + rows[0].id,
                onContentReady: function () {
                    initMaterialInput();
                }
            });
        }
    }
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
                content: '确认删除该组织吗？',
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
                                url: '${ctx}/manage/organization/delete/' + ids.join("-"),
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