<%--
  Created by IntelliJ IDEA.
  User: carlo
  Date: 21/01/2023
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="./css/toast.css">
</head>
<body>


<script src="toast.js"></script>

<div class="toast">
    <div class="toast-content">
        <i class="fas fa-solid fa-check check"> </i>

        <div class="messaggio">
            <span class="text text-1">Ottimo!</span>
            <span class="text text-2">Solo cose positive</span>
        </div>
    </div>

    <div class="progress"></div>
</div>

<div class="toasterre">
    <div class="toasterre-contenterre">
        <i class="fas fa-solid fa-check checkerre"> </i>

        <div class="messaggioerre">
            <span class="texterre texterre-1">Sbagliato</span>
            <span class="texterre texterre-2">Solo cose negative</span>
        </div>
    </div>

    <div class="progresserre"></div>
</div>

<button onclick="lanciaToastGreen()">Test Toast</button>
<button onclick="lanciaToastRed()">Test Toast</button>

</body>
</html>