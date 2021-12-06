<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Auth Error Page</title>
<link rel="stylesheet"
	href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
</head>
<body style="padding: 10px">
	<form class="pure-form">
		<fieldset>
			<legend>Auth Error Page</legend>
			<h2>exception : ${exception }</	h2>
			<h2>exception.date : 
				<fmt:formatDate value="${exception.date }" pattern="yyyy-MM-dd"/>
			</h2>
			<h2>exception.message : ${exception.message }</h2>
		</fieldset>
	</form>
</body>
</html>