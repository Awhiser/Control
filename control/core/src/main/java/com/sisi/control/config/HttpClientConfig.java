package com.sisi.control.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class HttpClientConfig {

    @Bean
    public OkHttpClient httpClient(){
        var client = new OkHttpClient.Builder()
                .readTimeout(20, java.util.concurrent.TimeUnit.SECONDS)
                .build();
        return client;
    }

}