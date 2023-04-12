package util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import data.sido.SidoResponse;
import data.sido.SidoResponseResult;

public class SidoAPI {

	public synchronized static SidoResponse getSidos() {

		try {

			String target = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/sido";
			
			Map<String, String> params = new HashMap<>();
			params.put("serviceKey", "pn%2BYJ4SQX3S%2B%2FgbKi30JDEXj5Wqo2HYKhhKbzU1dC9d3NcSrmyo1a4WAbD72FlI0g2dPY%2B7ngYVX7i0gmvp5pw%3D%3D");
			params.put("_type", "json");
			params.put("numOfRows", "17");

			String queryString = QueryStringBuilder.build(params);

			URI uri = new URI(target + "?" + queryString);

			// HttpRequest 객체를 활용하는 방식

			HttpClient client = HttpClient.newHttpClient();

			HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
			// POST 요청
			// >>> HttpRequest request = HttpRequest.newBuilder(uri).POST(null).build();

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			Gson gson = new Gson();
			SidoResponseResult responseResult = gson.fromJson(response.body(), SidoResponseResult.class);

			return responseResult.getResponse();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
