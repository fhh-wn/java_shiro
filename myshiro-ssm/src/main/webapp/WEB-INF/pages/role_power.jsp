<%--
  Created by IntelliJ IDEA.
  User: fhh
  Date: 2020-1-10
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/statics/zTree_v3/css/zTreeStyle/zTreeStyle.css">
    <script src="<%=request.getContextPath()%>/statics/zTree_v3/js/jquery-1.4.4.min.js"></script>
    <script src="<%=request.getContextPath()%>/statics/zTree_v3/js/jquery.ztree.all.min.js"></script>
    <style type="text/css" >
        .container{
            width: 1200px;
        }
        #leftsdd{
            width: 200px;
            height: 500px;
            float: left;
            border: 1px solid #ccc;
        }
        #right{
            width: 950px;
            height: 500px;
            float: left;
            border: 1px solid #ccc;
        }
    </style>
    <script>
        $(function(){
            var setting = {
                view:{
                    showLine:true
                },
                data:{
                    simpleData: {
                        enable:true,
                        idKey: "id",
                        pIdKey: "pId",
                        rootPId: null
                    }
                },
                check:{
                    enable: true
                }
            };
            $.ajax({
                url:'<%=request.getContextPath()%>/role/upCheckMenu.html',
                type:"GET",
                dataType:"json",
                success:function (data) {
                    console.log(data);
                    ztreeObj=$.fn.zTree.init($("#treeDemo1"),setting,data);
                    ztreeObj.expandAll(true);
                }
            });
        });
        function openNew(p1,p2,p3){
            $("#title").html(p3.name);
            $("#content").attr("src",p3.url);
            event.preventDefault();
        }
    </script>
</head>
<body>

<div id="upPermission" >
    <ul id="treeDemo1" class="ztree"></ul>
</div>
<input type="hidden" value="${roleId}" id="roleIdH">
</body>
</html>
