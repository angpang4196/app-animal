package util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

import data.animal.AnimalResponse;
import data.animal.AnimalResponseResult;

public class AnimalAPI {

	// OPEN API 연동해서 데이터 받아오고 파싱해서 컨트롤러로 전송.
	public static AnimalResponse getAnimals() {

		try {

			String target = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic";
			String queryString = "serviceKey=pn%2BYJ4SQX3S%2B%2FgbKi30JDEXj5Wqo2HYKhhKbzU1dC9d3NcSrmyo1a4WAbD72FlI0g2dPY%2B7ngYVX7i0gmvp5pw%3D%3D&_type=json";
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
