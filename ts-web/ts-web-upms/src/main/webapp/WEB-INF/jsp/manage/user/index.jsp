<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@ include file="../../common/IncHeader.jsp" %>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <%@ include file="../../common/IncMeta.jsp" %>
    <title>用户管理</title>
    <%@ include file="../../common/IncLink.jsp" %>
</head>
<body>
<div id="main">
    <div id="toolbar">
        <shiro:hasPermission name="upms:user:create">
            <a class="waves-effect waves-button" href="javascript:void(0);"
               onclick="createAction()">
                <i class="zmdi zmdi-plus"></i>
                新增用户</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="upms:user:update">
            <a class="waves-effect waves-button" href="javascript:void(0);"
               onclick="updateAction()">
                <i class="zmdi zmdi-edit"></i>
                编辑用户</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="upms:user:delete">
            <a class="waves-effect waves-button" href="javascript:void(0);"
               onclick="deleteAction()">
                <i class="zmdi zmdi-close"></i>
                删除用户</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="upms:user:organization">
            <a class="waves-effect waves-button" href="javascript:void(0);"
               onclick="organizationAction()">
                <i class="zmdi zmdi-accounts-list"></i>
                用户组织</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="upms:user:role">
            <a class="waves-effect waves-button" href="javascript:void(0);"
               onclick="roleAction()">
                <i class="zmdi zmdi-accounts"></i>
                用户角色</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="upms:user:permission">
            <a class="waves-effect waves-button" href="javascript:void(0);"
               onclick="permissionAction()">
                <i class="zmdi zmdi-key"></i>
                用户权限</a>
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
            url: '${ctx}/manage/user/list',
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
                {field: 'username', title: '帐号'},
                {field: 'realname', title: '姓名'},
                {field: 'avatar', title: '头像', align: 'center', formatter: 'avatarFormatter'},
                {field: 'phone', title: '电话'},
                {field: 'email', title: '邮箱'},
                {field: 'sex', title: '性别', formatter: 'sexFormatter'},
                {field: 'locked', title: '状态', sortable: true, align: 'center', formatter: 'lockedFormatter'}
            ]
        });
    });
    // 格式化图标
    function avatarFormatter(value, row, index) {
        return '<img src="${ctx}' + value + '" style="width:20px;height:20px;"/>';
    }
    // 格式化性别
    function sexFormatter(value, row, index) {
        if (value == 1) {
            return '男';
        }
        if (value == 2) {
            return '女';
        }
        return '-';
    }
    // 格式化状态
    function lockedFormatter(value, row, index) {
        if (value == 1) {
            return '<span class="label label-default">锁定</span>';
        } else {
            return '<span class="label label-success">正常</span>';
        }
    }
    // 新增
    var createDialog;
    function createAction() {
        createDialog = $.dialog({
            animationSpeed: 300,
            title: '新增用户',
            content: 'url:${ctx}/manage/user/create',
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
                title: '编辑用户',
                content: 'url:${ctx}/manage/user/update/' + rows[0].id,
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
                content: '确认删除该用户吗？',
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
                                url: '${ctx}/manage/user/delete/' + ids.join("-"),
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
    // 用户组织
    var organizationDialog;
    var organizationUserId;
    function organizationAction() {
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
            organizationUserId = rows[0].id;
            organizationDialog = $.dialog({
                animationSpeed: 300,
                title: '用户组织',
                content: 'url:${ctx}/manage/user/organization/' + organizationUserId,
                onContentReady: function () {
                    initMaterialInput();
                    $('select').select2({
                        placeholder: '请选择用户组织',
                        allowClear: true
                    });
                }
            });
        }
    }
    // 用户角色
    var roleDialog;
    var roleUserId;
    function roleAction() {
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
            roleUserId = rows[0].id;
            roleDialog = $.dialog({
                animationSpeed: 300,
                title: '用户角色',
                content: 'url:${ctx}/manage/user/role/' + roleUserId,
                onContentReady: function () {
                    initMaterialInput();
                    $('select').select2({
                        placeholder: '请选择用户角色',
                        allowClear: true
                    });
                }
            });
        }
    }
    // 用户权限
    var permissionDialog;
    var permissionUserId;
    function permissionAction() {
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
            permissionUserId = rows[0].id;
            permissionDialog = $.dialog({
                animationSpeed: 300,
                title: '用户授权',
                columnClass: 'large',
                content: 'url:${ctx}/manage/user/permission/' + permissionUserId,
                onContentReady: function () {
                    initMaterialInput();
                    initTree();
                }
            });
        }
    }
</script>
</body>
</html>