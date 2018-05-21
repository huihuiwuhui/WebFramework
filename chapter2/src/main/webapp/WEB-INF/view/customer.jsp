<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="BASE" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>客户管理</title>
</head>
<body>
<div style="width:70%;margin:auto;"> 
 <h1 style="margin:auto;">客户列表</h1>
 
 <table>
    <tr>
       <th>客户名称</th>
       <th>联系人</th>
       <th>电话号码</th>
       <th>邮箱地址</th>
       <th>操作</th>
    </tr>
    <c:forEach var="customer" items="${customerList}">
       <tr>
           <td>${customer.name}</td>
           <td>${customer.contact}</td>
           <td>${customer.telephone}</td>
           <td>${customer.email}</td>
           <td>
              <a href ="${BASE}/customer_edit?id=${customer.id}">编辑</a>
              <a href ="${BASE}/customer_delete?id=${customer.id}">删除</a> 
           </td>
       </tr> 
    </c:forEach>
 </table>
</div>
</body>
</html>