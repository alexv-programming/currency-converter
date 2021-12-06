package telran.conversion.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import telran.conversion.dto.ConversionDto;

public class ConversionAppl {

	private static final String ACCESS_KEY = "4d727cb94ce4bded72aae9fad59727bd";

	public static void main(String[] args) throws URISyntaxException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sum;
		String from = null;
		Double fromRate;
		String to = null;
		Double toRate;
		
		try {
			System.out.println("how much money?");
			sum = Integer.valueOf(br.readLine().trim());
			System.out.println("from qur?");
			from = br.readLine().trim().toUpperCase();
			System.out.println("to qur?");
			to = br.readLine().trim().toUpperCase();
			br.close();
//			System.out.println(sum);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		RestTemplate restTemplate = new RestTemplate();
		
		String url ="http://data.fixer.io/api/latest";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
												.queryParam("access_key", ACCESS_KEY)
												.queryParam("symbols", "usd,eur,ils,uah,rub,blr");
		RequestEntity<String> requestEntity = new RequestEntity<>(HttpMethod.GET,
				new URI("http://data.fixer.io/api/latest"
						+ "?access_key="
						+ "4d727cb94ce4bded72aae9fad59727bd"));
//		ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
//		System.out.println(responseEntity);
//		

		ResponseEntity<ConversionDto> responseEntity =
						restTemplate.exchange(requestEntity, 
								new ParameterizedTypeReference<ConversionDto>() {});
		
		ConversionDto posts = responseEntity.getBody();
		Map<String, Double> rates = posts.getRates();
		fromRate = rates.get(from);
		toRate = rates.get(to);
		System.out.println(1.0/fromRate*toRate);
		//posts.stream().forEach(System.out::println);
		

	}

}
