<%--
  Created by IntelliJ IDEA.
  User: fhh
  Date: 2020-1-9
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>分配角色</title>
    <script  src="<%=request.getContextPath()%>/statics/zTree_v3/js/jquery-1.4.4.min.js"></script>
    <script>

        $(function () {
            $("#assigbtn").click(function () {
                var ckArray=$(".ck");
                var roleArray="";
                for (var i=0;i<ckArray.length;i++){
                    if(ckArray[i].checked){
                        roleArray+=ckArray[i].value+","
                    }
                }
                roleArray=roleArray.substring(0,roleArray.length-1);
                var userId = $("#userIdH").val();
                console.log(userId);
                console.log(roleArray);
                $.ajax({
                    url:"<%=request.getContextPath()%>/user/assignRole.html",
                    type:"Post",
                    dataType:"json",
                    data:{"userId":userId,"roleArray":roleArray},
                    success:function (data) {
                        if(data.msg=="success"){
                            alert("分配成功");
                        }else {
                            alert("分配失败");
                        }
                    }
                });
            });
        });
    </script>
</head>
<body>
    <c:forEach varStatus="i" items="${roleBeans}" var="roleb">
        <input type="checkbox"  ${roleb.check} class="ck" value="${roleb.role.id}">${roleb.role.name}
    </c:forEach>
    <input type="hidden" value="${userId}" id="userIdH">
    <input type="button" value="重新分配角色" id="assigbtn">
</body>
</html>
