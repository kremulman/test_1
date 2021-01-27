<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <script type="text/javascript" src="/resources/index.js"></script>
    <script type="text/javascript">
        var fixedTypeList = '${fixedTypeList}';
    </script>
    <title>Test for Flow</title>
</head>
<body>
<h1>파일 확장자 차단</h1>
<table style="border-collapse: collapse; width: 100%;" border="1">
    <tbody>
    <tr>
        <td style="width: 22.5852%;">고정확장자</td>
        <td style="width: 77.4148%;">
            <input class="fixed-type" type="checkbox" value="bat"/>bat
            <input class="fixed-type" type="checkbox" value="cmd"/>cmd
            <input class="fixed-type" type="checkbox" value="com"/>com
            <input class="fixed-type" type="checkbox" value="cpl"/>cpl
            <input class="fixed-type" type="checkbox" value="exe"/>exe
            <input class="fixed-type" type="checkbox" value="scr"/>scr
            <input class="fixed-type" type="checkbox" value="js"/>js
        </td>
    </tr>
    <tr>
        <td style="width: 22.5852%;">커스텀 확장자</td>
        <td style="width: 77.4148%;"><input type="text" id="type-string"/>
            <input id="submit-add" type="button" value="+추가"/>
        </td>
    </tr>
    <tr>
        <td style="width: 22.5852%;">&nbsp;</td>
        <td style="width: 77.4148%;">
            <div id="type-container">
                <c:forEach items="${customTypeList}" var="list" varStatus="status">
                    <div id="custom-type-${list.id}" style="display: inline-block">${list.fileTypeString}<button onclick='delete_custom_type("${list.fileTypeString}")'>X</button></div>
                </c:forEach>
            </div>
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>
