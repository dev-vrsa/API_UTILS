package com.app.utility.me.repository.impl;
import com.app.utility.me.repository.ApiClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import lombok.SneakyThrows;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class DefaultApiClientRepository implements ApiClientRepository {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public <T> T sendGet(String endpoint, Class<T> valueType) {
        HttpURLConnection connection = createConnection(endpoint, "GET");
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            return getResponse(connection, valueType);
        } else {
            throw new ApiException("GET request failed: HTTP code " + responseCode);
        }
    }

    @SneakyThrows
    @Override
    public <T> T sendPost(String endpoint, String payload, Class<T> valueType) {
        HttpURLConnection connection = createConnection(endpoint, "POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            os.write(payload.getBytes());
            os.flush();
        }

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
            return getResponse(connection, valueType);
        } else {
            throw new ApiException("POST request failed: HTTP code " + responseCode);
        }
    }

    private HttpURLConnection createConnection(String endpoint, String method) throws IOException {
        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        return connection;
    }

    private <T> T getResponse(HttpURLConnection connection, Class<T> valueType) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            return objectMapper.readValue(response.toString(), valueType);
        }
    }

    public static class ApiException extends RuntimeException {
        public ApiException(String message) {
            super(message);
        }
    }
}
