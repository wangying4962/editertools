<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form method="post" action="/editer/upload" enctype="multipart/form-data">
    <input type="file" name="file" accept=".pdf">
    <input type="submit" value="提交">
</form>
<table border="1">
    <tr>
        <td>id</td>
        <td>文件名</td>
        <td>上传时间</td>
        <td>操作</td>

    </tr>
    <#list tasks as task>

        <tr>
            <td>${task.id}</td>
            <td>${task.fileName}</td>
            <td>${task.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
            <td>
                <#if task.status==0>
                    <a href="/editer/analysis?id=${task.id}">分析</a>
                </#if>
                <#if task.status==1>
                    <a href="">查看分析结果</a>
                </#if>
            </td>

        </tr>
    </#list>
</table>
</body>
</html>