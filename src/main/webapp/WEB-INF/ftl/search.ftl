<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, maximum-scale=1.0,user-scalable=no">
    <link rel="stylesheet" href="/resources/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="/resources/raty/lib/jquery.raty.css">
    <script src="/resources/jquery.3.3.1.min.js"></script>
    <script src="/resources/bootstrap/bootstrap.min.js"></script>
    <script src="/resources/art-template.js"></script>
    <script src="/resources/raty/lib/jquery.raty.js"></script>
    <style>
        .container {
            width: 500px;
            height: 50px;
            margin: 100px auto;
        }

        .parent {
            width: 100%;
            height: 42px;
            top: 4px;
            position: relative;
        }

        .parent>input:first-of-type {
            /*输入框高度设置为40px, border占据2px，总高度为42px*/
            width: 380px;
            height: 40px;
            border: 1px solid #ccc;
            font-size: 16px;
            outline: none;
        }

        .parent>input:first-of-type:focus {
            border: 1px solid #317ef3;
            padding-left: 10px;
        }

        .parent>input:last-of-type {
            /*button按钮border并不占据外围大小，设置高度42px*/
            width: 100px;
            height: 44px;
            position: absolute;
            background: #317ef3;
            border: 1px solid #317ef3;
            color: #fff;
            font-size: 16px;
            outline: none;
        }
    </style>
<body>


<a href="/">
    返回主页
</a>

<div class="container">
    <form action="/persondata/username/" class="parent" method="post">
        <input type="text" id="username" name="username">
        <input type="submit" value="搜索">
    </form>
</div>
</body>
</html>