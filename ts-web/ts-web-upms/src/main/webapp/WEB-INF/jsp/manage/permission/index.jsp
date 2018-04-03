﻿<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@ include file="../../common/IncHeader.jsp" %>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <%@ include file="../../common/IncMeta.jsp" %>
    <title>权限管理</title>
    <%@ include file="../../common/IncLink.jsp" %>
</head>
<body>
<div id="main">
    <div id="toolbar">
        <shiro:hasPermission name="upms:permission:create">
            <a class="waves-effect waves-button" href="javascript:createAction();">
                <i class="zmdi zmdi-plus"></i>
                新增权限</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="upms:permission:update">
            <a class="waves-effect waves-button" href="javascript:updateAction();">
                <i class="zmdi zmdi-edit"></i>
                编辑权限</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="upms:permission:delete">
            <a class="waves-effect waves-button" href="javascript:deleteAction();">
                <i class="zmdi zmdi-close"></i>
                删除权限</a>
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
            url: '${ctx}/manage/permission/list',
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
            idField: 'permissionId',
            maintainSelected: true,
            toolbar: '#toolbar',
            columns: [
                {field: 'ck', checkbox: true},
                {field: 'id', title: '编号', sortable: true, align: 'center'},
                {field: 'systemId', title: '所属系统'},
                {field: 'pid', title: '所属上级'},
                {field: 'name', title: '权限名称'},
                {field: 'type', title: '类型', formatter: 'typeFormatter'},
                {field: 'permissionValue', title: '权限值'},
                {field: 'uri', title: '路径'},
                {field: 'icon', title: '图标', align: 'center', formatter: 'iconFormatter'},
                {field: 'status', title: '状态', sortable: true, align: 'center', formatter: 'statusFormatter'},
                {
                    field: 'action',
                    title: '操作',
                    align: 'center',
                    formatter: 'actionFormatter',
                    events: 'actionEvents',
                    clickToSelect: false
                }
            ]
        });
    });
    // 格式化操作按钮
    function actionFormatter(value, row, index) {
        return [
            '<a class="update" href="javascript:updateAction();" data-toggle="tooltip" title="Edit"><i class="glyphicon glyphicon-edit"></i></a>　',
            '<a class="delete" href="javascript:deleteAction();" data-toggle="tooltip" title="Remove"><i class="glyphicon glyphicon-remove"></i></a>'
        ].join('');
    }
    // 格式化图标
    function iconFormatter(value, row, index) {
        return '<i class="' + value + '"></i>';
    }
    // 格式化类型
    function typeFormatter(value, row, index) {
        if (value == 1) {
            return '目录';
        }
        if (value == 2) {
            return '菜单';
        }
        if (value == 3) {
            return '按钮';
        }
        return '-';
    }
    // 格式化状态
    function statusFormatter(value, row, index) {
        if (value == 1) {
            return '<span class="label label-success">正常</span>';
        } else {
            return '<span class="label label-default">锁定</span>';
        }
    }
    // 新增
    var createDialog;
    function createAction() {
        createDialog = $.dialog({
            animationSpeed: 300,
            title: '新增权限',
            content: 'url:${ctx}/manage/permission/create',
            onContentReady: function () {
                initMaterialInput();
                $('select').select2();
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
                title: '编辑权限',
                content: 'url:${ctx}/manage/permission/update/' + rows[0].permissionId,
                onContentReady: function () {
                    initMaterialInput();
                    $('select').select2();
                    initType();
                    initSelect2();
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
                content: '确认删除该权限吗？',
                buttons: {
                    confirm: {
                        text: '确认',
                        btnClass: 'waves-effect waves-button',
                        action: function () {
                            var ids = new Array();
                            for (var i in rows) {
                                ids.push(rows[i].permissionId);
                            }
                            $.ajax({
                                type: 'get',
                                url: '${ctx}/manage/permission/delete/' + ids.join("-"),
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