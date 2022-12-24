<%--
  Created by IntelliJ IDEA.
  User: eljon
  Date: 24/12/2022
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>BeHub-Intro</title>
    <link rel="stylesheet" href="css/intro.css">
    <script src="https://code.jquery.com/jquery-3.2.1.js"></script>

</head>
<body>
<div class="welcome-section content-hidden">
    <div class="wrapper">
        <div class="static-txt">Be</div>
        <ul class="dynamic-txts">
            <li><span>Clever</span></li>
            <li><span>Different</span></li>
            <li><span>Rich</span></li>
            <li><span>Hub</span></li>
        </ul>



    </div>
    <a href="index.jsp" class="enter-button">Inizia</a>
</div>

<script type="text/javascript">

    $(function() {
        var welcomeSection = $('.welcome-section'), enterButton = welcomeSection.find('.enter-button');

        setTimeout(function() {
            welcomeSection.removeClass('content-hidden');
        },200);



    });

</script>

</body>
</html>

