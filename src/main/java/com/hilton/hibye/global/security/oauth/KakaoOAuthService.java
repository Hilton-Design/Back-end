package com.hilton.hibye.global.security.oauth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hilton.hibye.domain.user.domain.OAuthToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class KakaoOAuthService {

    @Value("${content-type}")
    private static String CONTENT_TYPE;

    @Value("${token-request.uri}")
    private String TOKEN_REQUEST_URI;
    @Value("${token-request.body.grant-type}")
    private String GRANT_TYPE;
    @Value("${token-request.body.client-id}")
    private String CLIENT_ID;
    @Value("${token-request.body.request-uri}")
    private String REDIRECT_URI;

    @Value("${profile-request.uri}")
    private String PROFILE_REQUEST_URI;

    public OAuthToken  getOAuthToken(String code) {
        // http 요청할 수 있는 오브젝트
        RestTemplate restTemplate = new RestTemplate();

        // header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", CONTENT_TYPE);

        // body 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", GRANT_TYPE);
        params.add("client_id", CLIENT_ID);
        params.add("redirect_uri", REDIRECT_URI);
        params.add("code", code);

        // header, body 를 한 오브젝트에 담기
        HttpEntity<MultiValueMap<String,String>> tokenRequest = new HttpEntity<>(params, headers);

        // http 요청하기
        ResponseEntity<String> response = restTemplate.exchange(
                TOKEN_REQUEST_URI,      // 요청 주소
                HttpMethod.POST,        // 요청 메소드
                tokenRequest,      // header, body 값
                String.class            // 응답값 자료형
        );

        // Json 매핑 오브젝트
        ObjectMapper objectMapper = new ObjectMapper();

        OAuthToken oAuthToken = null;
        try {
            // Json 을 OAuthToken 에 매핑
            oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return oAuthToken;
    }

    public String getUserProfile(OAuthToken oAuthToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", CONTENT_TYPE);
        headers.add("Authorization", "Bearer "+oAuthToken.getAccess_token());

        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                PROFILE_REQUEST_URI,
                HttpMethod.POST,
                request,
                String.class
        );

        return response.getBody();
    }
}
