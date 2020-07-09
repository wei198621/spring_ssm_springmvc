<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>测试springmvc与ajax集成</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
    <script>
        $(function(){
          $("#btn").click(function(){
              //$.get("${pageContext.request.contextPath}/userAjax/findAll"
              //$.get("${pageContext.request.contextPath}/userAjax/findAll2_1"
              $.get("${pageContext.request.contextPath}/userAjax/findAll2_2"
                  ,function (res) {

                      $.each(res, function (i, user) {
                          var ul=$("<ul/>");
                          var idLi = $("<li/>").text(user.id);
                          var nameLi = $("<li/>").text(user.name);
                          var ageLi = $("<li/>").text(user.age);
                          var birLi = $("<li/>").text(user.bir);
                          ul.append(idLi).append(nameLi).append(ageLi).append(birLi);
                          $("#bd").append(ul);
                      })
                  }
                  ,"JSON"
              );
          });

        });
    </script>

</head>
<body id="bd">
   <button id="btn">显示一群人</button>
</body>
</html>
