<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}/static" scope="session"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>fores管理后台</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${ctx}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${ctx}/dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="${ctx}/plugins/bootstrap-table/bootstrap-table.css">
    <link rel="stylesheet" href="${ctx}/plugins/jQuery-Validation-Engine/css/validationEngine.jquery.css">

</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
    <jsp:include page="../header.jsp"/>
    <jsp:include page="../siderbar.jsp"/>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>角色列表</h1>
        </section>
        <!-- Main content -->
        <section class="content" style="min-height: 850px;width: 100%">
            <div class="box">
                <div id="toolbar">
                    <div class="form-inline" role="form">
                        <div class="form-group">
                            <a id="add" class="create btn btn-default" href="javascript:">新增</a>
                        </div>
                    </div>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <table id="table"></table>
                </div>
            </div>
        </section>
    </div>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">更新用户</h4>
            </div>
            <div class="modal-body">
                <div class="box box-default">
                    <!-- /.box-header -->
                    <div class="box-body">
                        <form id="form" method="post">
                            <input type="hidden" id="id" name="id"/>
                            <div class="form-group">
                                <label for="roleName">角色名称</label>
                                <input type="input" class="form-control  validate[required]" name="roleName"
                                       id="roleName"
                                       placeholder="角色名称">
                            </div>
                            <div class="form-group">
                                <label for="profile">描述</label>
                                <input type="text" class="form-control" name="profile" id="profile" placeholder="">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="submit" id="save" class="btn btn-primary">保存</button>
            </div>
        </div>

    </div>
</div>
</div>

<jsp:include page="../footer.jsp"/>
<jsp:include page="../control-siderbar.jsp"/>
<div class="control-sidebar-bg"></div>
</div>
<script src="${ctx}/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${ctx}/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/plugins/bootstrap-table/bootstrap-table.js"></script>
<script src="${ctx}/plugins/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/plugins/bootstrap-table/bootstrap-table-helper.js"></script>
<script src="${ctx}/plugins/jQuery-Validation-Engine/js/jquery.validationEngine.js"></script>
<script src="${ctx}/plugins/jQuery-Validation-Engine/js/languages/jquery.validationEngine-zh_CN.js"></script>
<script src="${ctx}/dist/js/app.min.js"></script>
<script src="${ctx}/dist/js/demo.js"></script>
<script>
    $(function () {

        window.initHandle = {
            // 编辑
            editModel: function (id) {
                $.ajax({
                    type: "post",
                    url: "/role/getById",
                    data: {id: id},
                    dateType: "json",
                    success: function (result) {
                        $("#id").val(result.id);
                        $("#roleName").val(result.roleName);
                        $("#profile").val(result.profile);
                        $("#modal").modal('show');
                    }
                });
            },
            deleteModel: function (id) {
                console.log(id);
                $.ajax({
                    type: "post",
                    url: "/role/delById",
                    data: {id: id},
                    dataType: "json",
                    success: function (result) {
                        if (result.code == 0) {
                            alert("删除成功");
                            TableHelper.doRefresh("#table");
                        } else {
                            alert("删除失败：Message：" + result.msg);
                        }
                    }
                });
            },
            initTable: function () {
                $('#table').bootstrapTable('destroy');
                $('#table').bootstrapTable({
                    columns: [
                        {field: 'id', title: 'id', align: 'center', width: '15%'},
                        {field: 'roleName', title: '角色名称', sortable: 'true', align: 'center', width: '25%'},
                        {field: 'profile', title: '描述', width: '30%'},
                        {
                            field: 'id',
                            title: '操作',
                            align: 'center',
                            width: '20%',
                            formatter: function (val, row, index) {
                                return [
                                    '<button class="btn btn-primary" onclick="initHandle.editModel(\'' + row.id + '\')"><i class="glyphicon glyphicon-edit"></i>编辑</button>&nbsp;&nbsp;',
                                    '<button class="btn btn-danger" onclick="initHandle.deleteModel(\'' + row.id + '\')"><i class="glyphicon glyphicon-remove"></i>删除</button>',
                                ].join('');
                            }
                        },
                    ],
                    cache: false,
                    striped: true,
                    showRefresh: true,
                    pagination: true,
                    pageList: [5, 10, 20, 30, 50],
                    search: true,
                    sidePagination: "client",
                    toolbar: '#toolbar',
                    url: '/role/list',
                    queryParams: function queryParams(params) {   //设置查询参数
                        return {};
                    }
                });

            }
        }

        // 设置校验ui
        $('#form').validationEngine('attach', {
            promptPosition: 'centerRight',
            scroll: false
        });

        $("#add").click(function () {
            $("#form")[0].reset();
            $("#id").val(null);
            // 打开编辑弹窗
            $("#modal").modal('show');
        });

        $("#save").click(function () {
            if ($("#form").validationEngine('validate')) {
                $.ajax({
                    type: "post",
                    url: "/role/save",
                    data: $('#form').serialize(),
                    dataType: "json",
                    success: function (json) {
                        if (json.code == 0) {
                            $("#modal").modal('hide');
                            TableHelper.doRefresh("#table");
                        }
                    }
                });
            }
        });

        // 初始化table
        initHandle.initTable();

    });


</script>
</body>
</html>
