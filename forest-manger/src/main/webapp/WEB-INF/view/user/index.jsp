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
            <h1>用户列表</h1>
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
                    <div id="table"></div>
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
                        <form id="form" action="/user/saveOrUpdate" method="post">
                            <input type="hidden" id="id" name="uid"/>
                            <div class="form-group">
                                <label for="name">昵称</label>
                                <input type="input" class="form-control  validate[required]" name="name" id="name"
                                       placeholder="name">
                            </div>
                            <div class="form-group">
                                <label>角色</label>
                                <select class="form-control select2" id="roleId" name="roleId" style="width: 100%;">
                                    <option selected="selected" value="2">管理员</option>
                                    <option value="3">超级管理员</option>
                                    <option value="1">普通用户</option>
                                </select>
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
<!-- ./wrapper -->

<script src="${ctx}/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${ctx}/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/plugins/bootstrap-table/bootstrap-table.js"></script>
<script src="${ctx}/plugins/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/plugins/jQuery-Validation-Engine/js/jquery.validationEngine.js"></script>
<script src="${ctx}/plugins/jQuery-Validation-Engine/js/languages/jquery.validationEngine-zh_CN.js"></script>
<script src="${ctx}/dist/js/app.min.js"></script>
<script src="${ctx}/dist/js/demo.js"></script>
<script>
    $(function () {


        window.initHandle = {
            // 编辑
            editModel: function (id) {
                alert("---------edit model-----");
                $.ajax({
                    type: "post",
                    url: "",
                    data: {id: id},
                    dateType: "json",
                    success: function (result) {
                        //
                        console.log(result);

                        //
                    }
                });
            },
            saveModel: function () {
                if ($("#form").validationEngine('validate')) {
                    $.ajax({
                        type: "post",
                        url: "/user/saveOrUpdate",
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
            },
            deleteModel: function (id) {
                $.ajax({
                    type: "post",
                    url: "/user/delById",
                    data: {id: id},
                    dataType: "json",
                    success: function (result) {
                        if (result.code == 0) {
                            alert("删除成功");
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
                        {field: 'uid', title: '用户id', align: 'center', width: '5%'},
                        {field: 'name', title: '用户昵称', sortable: 'true', align: 'center', width: '15%'},
                        {field: 'roleName', title: '用户角色', width: '25%'},
                        {field: 'profile', title: '描述', width: '25%'},
                        {field: 'path', title: '用户创建时间', align: 'center', width: '5%'},
                        {
                            field: 'id',
                            title: '操作',
                            align: 'center',
                            width: '15%',
                            formatter: function (val, row, index) {
                                return [
                                    '<button class="btn btn-primary" onclick="initHandle.editModel("' + row.uid + '")"><i class="glyphicon glyphicon-edit"></i>编辑</button>&nbsp;&nbsp;',
                                    '<button class="btn btn-danger" onclick="initHandle.deleteModel(\'' + row.uid + '\')"><i class="glyphicon glyphicon-remove"></i>删除</button>',
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
                    url: '/user/list',
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
        // 初始化table
        initHandle.initTable();

    });


</script>
</body>
</html>
