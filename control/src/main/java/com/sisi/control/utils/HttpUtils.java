package com.sisi.control.utils;

import com.sisi.control.utils.log.LogHelper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HttpUtils {

    @Autowired
    OkHttpClient okHttpClient;

    public String get(String apiUrl){
        Request request = new Request.Builder()
                .url(apiUrl)
                .header("Content-Type", "application/json")
                .build();
        return createRequest(request);
    }


    public String createRequest(Request request){
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                ResponseBody responseBody = response.body();
                if(responseBody == null){
                    return null;
                }

                return responseBody.string();
            } else {
                LogHelper.logError("HttpError::apiGet HTTP request failed." +
                        " apiUrl= " + request.url().url().getPath() + " response.code= " + response.code());
                return null;
            }
        } catch (Exception e) {
            LogHelper.logError("HttpError::apiGet. apiUrl= " + request.url().url().getPath(), e);
            return null;
        }
    }


}
