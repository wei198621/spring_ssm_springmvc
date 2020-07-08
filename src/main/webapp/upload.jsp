<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>测试文件上传</title>
</head>
<body>


<%--注意文件上传需要在springmvc.xml中配置 上传解析器
 <bean class="org.springframework.web.multipart.commons.CommonsMultipartResolver" id="multipartResolver">

--%>
<h1>文件上传</h1>
<form action="${pageContext.request.contextPath}/file/upload" method="post" enctype="multipart/form-data" >
    <input type="file" name="fileName"/>
    <input type="submit" value="上传文件">
</form>



</body>
</html>
