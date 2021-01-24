<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Test for Flow</title>
</head>
<body>
<h1>파일 확장자 차단</h1>
<table style="border-collapse: collapse; width: 100%;" border="1">
    <tbody>
    <tr>
        <td style="width: 22.5852%;">고정확장자</td>
        <td style="width: 77.4148%;"><input type="checkbox" checked />bat <input type="checkbox" />cmd <input type="checkbox" />com <input type="checkbox" />cpl <input type="checkbox" />exe <input type="checkbox" />scr <input type="checkbox" />js</td>
    </tr>
    <tr>
        <td style="width: 22.5852%;">커스텀 확장자</td>
        <td style="width: 77.4148%;"><input type="text" /> <input type="button" value="+추가" /></td>
    </tr>
    <tr>
        <td style="width: 22.5852%;">&nbsp;</td>
        <td style="width: 77.4148%;">목록표시부</td>
    </tr>
    </tbody>
</table>
</body>
</html>
