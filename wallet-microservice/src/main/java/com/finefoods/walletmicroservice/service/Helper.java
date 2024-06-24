package com.finefoods.walletmicroservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finefoods.walletmicroservice.model.CardInfo;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;


@Slf4j
@Data
@Service
public class Helper {
    private static SecretKeySpec secretKey;
    private static byte[] key;
    private static final String ALGORITHM = "AES";
    public void prepareSecreteKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, ALGORITHM);


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String strToEncrypt, String secret) {
        try {
            prepareSecreteKey(secret);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public String decrypt(String strToDecrypt, String secret) {
        try {
            prepareSecreteKey(secret);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public CardInfo getCardInfo(String cardNumber) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.bincodes.com/bin/json/a0b5314fd9538af1162d3c0888ee0b85/" + cardNumber)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            String responseBodyString = response.body().string();

            JsonNode responseBody = new ObjectMapper().readTree(responseBodyString);
            String bankName = responseBody.has("bank") ? responseBody.get("bank").toString() : "Unknown Bank";
            String brand = responseBody.has("card") ? responseBody.get("card").toString() : "Unknown Brand";
            String type = responseBody.has("type") ? responseBody.get("type").toString() : "Unknown Brand";
            return CardInfo.builder().
                    bankName(bankName.replaceAll("\"", ""))
                    .brand(brand.replaceAll("\"", "")).
                    type(type.replaceAll("\"", "")).
                    build();

        } catch (IOException e) {
            e.printStackTrace();
            return CardInfo.builder().build();
        }

    }

    public boolean varifyCard(String cardNumber) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.bincodes.com/cc/json/a0b5314fd9538af1162d3c0888ee0b85/" + cardNumber)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            String responseBodyString = response.body().string();
            JsonNode responseBody = new ObjectMapper().readTree(responseBodyString);

            if (responseBody.has("error")) {
                return false;
            } else {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }
}
