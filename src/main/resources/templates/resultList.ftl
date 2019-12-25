<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<table  border="1">
    <tr>
        <td>id</td>
        <td>关键词</td>
        <td>页</td>
        <td>行</td>
        <td>描述</td>

    </tr>
    <#list analys as analy>

        <tr>
            <td>${analy.id}</td>
            <td>${analy.keyWord}</td>
            <td>${analy.page}</td>
            <td>${analy.line}</td>
            <td>${analy.description}</td>
        </tr>
    </#list>
</table>
</body>
</html>