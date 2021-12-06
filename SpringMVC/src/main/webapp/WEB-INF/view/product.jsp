<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
<meta charset="UTF-8">
<title>Product From</title>
</head>
<body style="padding: 10px">
	<table>
		<tr>
			<td valign="top" style="padding: 5px">
				<form:form modelAttribute="product" 
						   class="pure-form" 
						   method="post" 
						   action="${pageContext.request.contextPath}/mvc/product/${action}">
					<fieldset>
						<legend>Product Form Post（商品上架）</legend>
						類別:
						<form:select path="group.gid"
									 items="${ groups }" 
									 itemLabel="gname" 
									 itemValue="gid"/>
						<p/>
						尺寸:
						<form:radiobuttons path="size"
										   items="${ sizes }" 
										   itemLabel="sname" 
										   itemValue="sname"
										   delimiter=" | "/>
						<p/>
						等級:
						<form:checkboxes path="levelIds" 
										 items="${ levels }"
										 itemLabel="lname" 
										 itemValue="lid"
										 delimiter=" "/>
						<p/>
						品名:
						<form:input path="name"
									placeholder="請輸入商品名稱" 
									readonly="${ action=='update'?'true':'false' }"/>
						<p/>
						價格:
						<form:input path="price" placeholder="請輸入商品價格" />
						<p/>
						數量:
						<form:input path="amount" placeholder="請輸入商品數量" />
						<p/>
						日期:
						<form:input path="date" type="date"/>
						<p/>
						<button type="submit" 
								class="pure-button pure-button-primary">
							${ action }
						</button>
						<button type="button" 
								onclick="location.href='${pageContext.request.contextPath}/mvc/product/'"
								class="pure-button pure-button-primary">
							reset
						</button>
						<p>
						<form:errors path="*"/>
					</fieldset>
				</form:form>
			</td>
			<td valign="top" style="padding: 5px">
				<form:form class="pure-form">
					<fieldset>
						 <legend>
						 	Product List（商品列表）
						 	<a href="${pageContext.request.contextPath}/mvc/product/query_json">
						 	query json</a>
						 </legend>
						 <table class="pure-table pure-table-bordered">
						 	<thead>
						 		<tr>
						 			 <th>name</th>
                                     <th>price</th>
                                     <th>amount</th>
                                     <th>group</th>
                                     <th>size</th>
                                     <th>level</th>
                                     <th>edit</th>
                                     <th>delete</th>
                                     <th>data</th>
						 		</tr>
						 	</thead>
						 	<tbody>
						 		<c:forEach var="p" items="${ products }">
						 			<tr>
						 				<td>${p.name}</td>
						 				<td>${p.price}</td>
						 				<td>${p.amount}</td>
						 				<td>
						 					${p.group }
						 				</td>
						 				<td>${p.size}</td>
						 				<td>
						 					<c:forEach var="l" items="${p.levelIds}">
						 						${ l }
						 					</c:forEach>
						 				</td>
						 				<td>
						 					<a class="update" href="${pageContext.request.contextPath}/mvc/product/get/${p.name}">Edit</a>
						 				</td>
						 				<td>
						 					<a class="delete" href="${pageContext.request.contextPath}/mvc/product/delete/${p.name}">Delete</a>
						 				</td>
						 				<td>${p}</td>
						 			</tr>
						 		</c:forEach>
						 	</tbody>
						 </table>
					</fieldset>
				</form:form>
			</td>
		</tr>
	</table>
</body>
</html>