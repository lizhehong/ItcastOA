<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List To Entity</title>
</head>
<body>
	<s:iterator value="roleList">
		<s:property value="id"/>
		<s:property value="name"/>
		<s:property value="desciption"/>
		<!-- 使用s:a标签，可以自动加上后缀扩展名和前缀工程名称 -->
		<s:a action="role_del?id=%{id}">删除</s:a>
		<s:a action="#">修改</s:a><hr/>
	</s:iterator>
</body>
</html>