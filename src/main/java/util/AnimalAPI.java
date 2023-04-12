package util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import data.animal.AnimalResponse;
import data.animal.AnimalResponseResult;

public class AnimalAPI {

	// OPEN API 연동해서 데이터 받아오고 파싱해서 컨트롤러로 전송.
	public synchronized static AnimalResponse getAnimals(String upkind, String upr_cd, String pageNo, String bgnde, String endde) {

		try {

			String target = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic";

			Map<String, String> params = new HashMap<>();
			params.put("_type", "json");
			params.put("serviceKey", "pn%2BYJ4SQX3S%2B%2FgbKi30JDEXj5Wqo2HYKhhKbzU1dC9d3NcSrmyo1a4WAbD72FlI0g2dPY%2B7ngYVX7i0gmvp5pw%3D%3D");
			params.put("numOfRows", "12");
			
			params.put("upkind", upkind == null ? "" : upkind);
			params.put("upr_cd", upr_cd == null ? "" : upr_cd);
			params.put("pageNo", pageNo == null ? "1" : pageNo);
			params.put("bgnde", bgnde == null ? "" : bgnde);
			params.put("endde", endde == null ? "" : endde);
			
			String queryString = QueryStringBuilder.build(params);
			System.out.println(queryString);
			URI uri = new URI(target + "?" + queryString);

			// HttpRequest 객체를 활용하는 방식

			HttpClient client = HttpClient.newHttpClient();

			HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
			// POST 요청
			// >>> HttpRequest request = HttpRequest.newBuilder(uri).POST(null).build();

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			Gson gson = new Gson();
			AnimalResponseResult responseResult = gson.fromJson(response.body(), AnimalResponseResult.class);

			return responseResult.getResponse();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
