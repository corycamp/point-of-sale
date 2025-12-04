package com.pos.client.api;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import com.pos.client.models.CartItem;

import java.net.http.*;
import java.net.URI;
import java.util.List;

public class ApiClient {

    private final HttpClient http = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();
    private final String baseUrl;

    public ApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public ProductDto getProductBySku(String sku) {
        try {
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/products/sku/" + sku))
                    .GET()
                    .build();

            HttpResponse<String> res = http.send(req, HttpResponse.BodyHandlers.ofString());
            if (res.statusCode() == 404) return null;

            return mapper.readValue(res.body(), ProductDto.class);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean sendOrder(List<CartItem> items) {
        try {
            String json = mapper.writeValueAsString(new OrderRequest(items));

            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/orders"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> res = http.send(req, HttpResponse.BodyHandlers.ofString());
            return res.statusCode() == 201;
        } catch (Exception e) {
            return false;
        }
    }
}
