package com.currencyconverter;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Type currency to convert from (e.g., USD):");
            String convertFrom = scanner.nextLine();
            System.out.println("Type currency to convert to (e.g., EUR):");
            String convertTo = scanner.nextLine();
            System.out.println("Type quantity to convert:");
            BigDecimal quantity = scanner.nextBigDecimal();

            // Updated API URL with your new access key
            String urlString = "https://api.exchangerate.host/live?access_key='YOUR_API_KEY'&base="
                    + convertFrom.toUpperCase() + "&symbols=" + convertTo.toUpperCase();

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(urlString)
                    .get()
                    .build();

            // Use try-with-resources for the Response object
            try (Response response = client.newCall(request).execute()) {
                // Check if the response is successful
                if (!response.isSuccessful()) {
                    System.err.println("Request failed: " + response);
                    return; // Exit if the request fails
                }

                // Check if the response body is null
                if (response.body() == null) {
                    System.err.println("Response body is null.");
                    return; // Exit if the response body is null
                }

                // Parse the response body
                String stringResponse = response.body().string();
                System.out.println("Response: " + stringResponse); // Print the full response for debugging

                // Create JSON object from response
                JSONObject jsonObject = new JSONObject(stringResponse);

                // Check if the 'quotes' key exists
                if (!jsonObject.has("quotes")) {
                    System.err.println("The 'quotes' key is missing in the response.");
                    return; // Exit if 'quotes' is missing
                }

                // Extracting the conversion rate from quotes
                String rateKey = convertFrom.toUpperCase() + convertTo.toUpperCase();
                if (!jsonObject.getJSONObject("quotes").has(rateKey)) {
                    System.err.println("The conversion rate for " + rateKey + " is not available.");
                    return; // Exit if the conversion rate is not available
                }

                BigDecimal rate = jsonObject.getJSONObject("quotes").getBigDecimal(rateKey);
                BigDecimal result = rate.multiply(quantity);
                System.out.println(quantity + " " + convertFrom.toUpperCase() + " is equal to " + result + " " + convertTo.toUpperCase());
            } catch (IOException e) {
                System.err.println("Error occurred while processing the response: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
