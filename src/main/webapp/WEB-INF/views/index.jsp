<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유기동물 정보 조회</title>
</head>
<body>
	<div style="text-align: center">
		<h1>유기동물 정보 조회 <small>(OPEN API 활용)</small></h1>
		<div>
			<form action="/index">
				<select name="upr_cd">
					<option value="">전국</option>
					<c:forEach items="${sidos }" var="obj">
						<option value="${obj.orgCd }">${obj.orgdownNm }</option>
					</c:forEach>
				</select>
				<select name="upkind">
					<option value="" ${param.upkind eq '' ? 'selected' : '' }>전체</option>
					<option value="417000" ${param.upkind eq '417000' ? 'selected' : '' }>개</option>
					<option value="422400" ${param.upkind eq '422400' ? 'selected' : '' }>고양이</option>
					<option value="429900" ${param.upkind eq '429900' ? 'selected' : '' }>기타</option> 
				</select>
				<button type="submit">조회</button>
			</form>
		</div>
		<div>
			총 ${total } 건의 유기동물 정보가 존재합니다.
		</div>
		<div style="display: flex; flex-wrap: wrap;">
			<c:forEach items="${datas }" var="obj">
				<div style="width: 50%">
					${obj.kindCd } | (${obj.specialMark }) 
					${obj.orgNm }${obj.happenPlace } 
					<img src="${obj.filename }"/>
				</div>
			</c:forEach>
		</div>	
	</div>
</body>
</html>