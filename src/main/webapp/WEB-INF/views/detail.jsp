<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 화면</title>
</head>
<style>
* {
	box-sizing: border-box;
}
</style>
<body style="margin: 0px">
	<div style="display: flex;">
		<%-- 동물 정보 디스플레이 영역 --%>
		<div style="flex: 3;">
			<h1>${item.kindCd } - (${item.colorCd })</h1>
			<img src="${item.popfile }" style="height: 30%;width: 100%; object-fit: feel">
			<ul>
				<li>유기 번호 : ${item.desertionNo }</li>
				<li>발견 장소 : ${item.orgNm}${item.happenPlace }</li>			
				<li>나이 : ${item.age }</li>
				<li>체중 : ${item.weight }</li>
				<li>성별 : ${item.sexCd }</li>
				<li>중성화 여부 : ${item.neuterYn }</li>
				<li>특징 : ${item.specialMark }</li>
			</ul>
			<div id="map" style="width:100%;height:300px; margin: auto; display: flex; justify-content: center; align-items: center; border: 1px solid #dddddd">
				<c:choose>
					<c:when test="${empty addr }">
						지도 정보를 확보하지 못 해 렌더링에 실패했습니다.
					</c:when>
					<c:otherwise>
						지도를 불러옵니다.
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		
		<%-- 동물에 대한 코멘트 영역 --%>
		<div style="flex: 2;">
			<div>
				<form action="/create" method="POST">
					<input type="hidden" name="target" value="${item.desertionNo }"/>
					<textarea style="width: 100%; height: 120px; resize: none" name="body"></textarea>
					비밀번호 입력 : <input type="password" name="pass">
					<button type="submit">응원하기</button>
				</form>
			</div>
			<div>
				<h4>응원의 한마디(${fn:length(messages)}건)</h4>
				<c:forEach items="${messages }" var="m">
					<div style="padding: 4px; border: 1px solid #cccccc">
						${m.body }
					</div>
				</c:forEach>			
			</div>
		</div>
	</div>
	
	<c:if test="${!empty addr }">
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fa50ba81310359fa007dce1d4eb86753"></script>	
		<script>
			let pos = new kakao.maps.LatLng(${addr.lng}, ${addr.lat});
			let container = document.querySelector('#map'); //지도를 담을 영역의 DOM 레퍼런스
			
			let options = { //지도를 생성할 때 필요한 기본 옵션
				center: pos, //지도의 중심좌표
				level: 3 //지도의 레벨(확대, 축소 정도)
			};
	
			let map = new kakao.maps.Map(container, options);
			
			let marker = new kakao.maps.Marker({
			    position: pos
			});
			
			marker.setMap(map);
		</script>
	</c:if>
</body>
</html>