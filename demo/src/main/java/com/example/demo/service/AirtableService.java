package com.example.demo.service;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Service
public class AirtableService {

    /*
    public static void main(String[] args) throws Exception {
        String accessToken = "pat0TalhEwsr9igsU.11f3fd461c25f39875650c2e2d593c0c7b6baca09641ec4e34e949a30c055e96";
        String baseId = "applto3j1obQLAVnr";
        String tableName = "Videos";

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("https://api.airtable.com/v0/" + baseId + "/" + tableName);

        request.setHeader("Authorization", "Bearer " + accessToken);
        String response = EntityUtils.toString(httpClient.execute(request).getEntity());
        System.out.println(response);
    }
    */


    public static void main(String[] args) throws IOException, InterruptedException, NoSuchAlgorithmException, JSONException {
     String accessToken = "pat0TalhEwsr9igsU.11f3fd461c25f39875650c2e2d593c0c7b6baca09641ec4e34e949a30c055e96";
     String baseId = "applto3j1obQLAVnr";
     String tableName = "Testimonials";

     HttpClient httpClient = HttpClientBuilder.create().build();
     HttpGet request = new HttpGet("https://api.airtable.com/v0/" + baseId + "/" + tableName);

     request.setHeader("Authorization", "Bearer " + accessToken);
     String response = EntityUtils.toString(httpClient.execute(request).getEntity());

        JSONObject outerObject = new JSONObject(response);
        JSONArray jsonArray = outerObject.getJSONArray("records");

        for (int i = 0, size = jsonArray.length(); i < size; i++)
        {
            JSONObject objectInArray = jsonArray.getJSONObject(i);
            String objectInArrayString = objectInArray.toString();
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hashBytes = digest.digest(objectInArrayString.getBytes(StandardCharsets.UTF_8));
            String hash = bytesToHex(hashBytes);
            System.out.println("Number: "+i+" The MD5 hash of the Airtable response is: " + hash);
        }
 }

 private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

 private static String bytesToHex(byte[] bytes) {
    char[] hexChars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            hexChars[i * 2] = HEX_ARRAY[v >>> 4];
            hexChars[i * 2 + 1] = HEX_ARRAY[v & 0x0F];
         }
    return new String(hexChars);
 }








}






