<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"
            integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <script type="text/javascript" src="/resources/index.js"></script>
    <title>Test for Flow</title>
</head>
<body>
<div id="type-container">
    <c:forEach items="${typeList}" var="list" varStatus="status">
        <p>${list.rejectedFile}a</p>
    </c:forEach>
</div>
</body>
</html>
