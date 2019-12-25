<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <td>id</td>
        <td>文件名</td>
        <td>操作</td>

    </tr>
    <#list tasks as task>

        <tr>
            <td>${task.id}</td>
            <td>${task.fileName}</td>
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