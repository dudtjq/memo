<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
		<header class="d-flex align-items-center justify-content-between">
			<h1 class="ml-2">toyouCafe</h1>
			<c:if test="${not empty userId } ">
				<div class="mr-4">
					${userName }님 <a href="/user/signout">로그아웃</a>
				</div>
			</c:if>
		</header>