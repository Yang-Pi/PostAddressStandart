package spring.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class DadataUtils {
    public static JSONObject getStandartAddress(String badAddress) {
        JSONObject res = null;
        try {
            String sUrl = "https://cleaner.dadata.ru/api/v1/clean/address";
            URL url = new URL(sUrl);
            HttpsURLConnection https = null;
            try {
                https = (HttpsURLConnection) url.openConnection();
                https.setRequestMethod("POST");
                https.setDoOutput(true);

                Properties properties = new Properties();
                try {
                    properties.load(new FileInputStream("src/main/resources/config.properties"));
                    https.setRequestProperty("Authorization", "Token " + properties.getProperty("apiKey"));
                    https.setRequestProperty("X-Secret", properties.getProperty("secretKey"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                https.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                byte[] requestBody = ("[ \"" + badAddress + "\"]").getBytes(StandardCharsets.UTF_8);
                https.setFixedLengthStreamingMode(requestBody.length);
                https.connect();
                try(OutputStream os = https.getOutputStream()) {
                    os.write(requestBody);
                }

                JSONArray jsonArray = makeJSONArray(https);
                res = jsonArray.getJSONObject(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                https.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        finally {
            return res;
        }
    }

    public static JSONArray makeJSONArray(HttpsURLConnection https) throws JSONException {
        JSONArray res = null;
        try {
            if (https.getResponseCode() == HttpURLConnection.HTTP_OK) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                InputStream is = https.getInputStream();
                //pack response to JSON
                byte[] buffer = new byte[999];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                }
                byte[] data = byteArrayOutputStream.toByteArray();
                String s = new String(data);

                JSONArray json = new JSONArray(s);
                res = json;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return res;
        }
    }
}
