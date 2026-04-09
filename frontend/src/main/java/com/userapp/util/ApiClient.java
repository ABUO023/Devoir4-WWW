package com.userapp.util;

import java.nio.charset.StandardCharsets;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;

public class ApiClient {
    
    private static final String API_BASE_URL = "http://userapp-api:8090/api";
    
    public static String post(String endpoint, String jsonBody) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(API_BASE_URL + endpoint);
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(jsonBody, StandardCharsets.UTF_8));
            
            return httpClient.execute(httpPost, response -> {
                if (response.getCode() >= 200 && response.getCode() < 300) {
                    return new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
                } else {
                    return "";
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    
    public static String get(String endpoint) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(API_BASE_URL + endpoint);
            
            return httpClient.execute(httpGet, response -> {
                if (response.getCode() >= 200 && response.getCode() < 300) {
                    return new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
                } else {
                    return "[]";
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return "[]";
        }
    }
}
