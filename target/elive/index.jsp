<%@page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <body>
        <a href="user/account/findAll">测试SpringMVC查询</a>
        <form action="upload/img" method="post" enctype="multipart/form-data">
            <input type="file" name="file">
            <input type="submit" value="提交">提交
        </form>
    </body>

</html>
