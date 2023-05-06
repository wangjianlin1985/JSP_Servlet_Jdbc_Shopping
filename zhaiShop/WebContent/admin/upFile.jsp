<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>文件上传</title>
	<link rel="stylesheet" type="text/css" href="common.css">
</head>
<body>
<center>
<form action="../uploadFile" method="post" enctype="multipart/form-data">
<table width=300 cellspacing=1>
<tr><th colspan=2>文件上传</th></tr>
<tr>
	<th>姓名:</th>
	<td><input type=text name="nam"></td>
</tr>
<tr>
	<th>文件:</th>
	<td><input type=file name="fd"></td>
</tr>
<tr><td colspan=2><input type=submit value="上 传"></td></tr>
</table>
</form>
</center>
</body>
</html>
