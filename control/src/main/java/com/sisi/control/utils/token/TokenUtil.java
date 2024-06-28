package com.sisi.control.utils.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtil {

    @Value("${token.secretKey}")
    private String secretKey;

    //todo
    Map<String,String> tokenCache = new HashMap<>();
    /**
     * 加密token.
     */
    public String getToken(String userId, String tenantId) {
        //这个是放到负载payLoad 里面,魔法值可以使用常量类进行封装.
        String token = JWT.create()
                .withClaim("userId" ,userId)
                .withClaim("tenantId", tenantId)
                .withClaim("timeStamp", System.currentTimeMillis())
                .sign(Algorithm.HMAC256(secretKey));
        return token;
    }
    /**
     * 解析token.
     * {
     * "userId": "weizhong",
     * "userRole": "ROLE_ADMIN",
     * "timeStamp": "134143214"
     * }
     */
    public ControlToken parseToken(String token) {
        DecodedJWT decodedjwt = JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token);
        String userId = decodedjwt.getClaim("userId").asString();
        String tenantId = decodedjwt.getClaim("tenantId").asString();
        long timeStamp = decodedjwt.getClaim("timeStamp").asLong();
        ControlToken controlToken = new ControlToken();
        controlToken.setUserId(userId);
        controlToken.setTenantId(tenantId);
        controlToken.setTimeStamp(timeStamp);

        return controlToken;
    }

    //todo 切换存储方式 存储时间
    public void saveToken(String token) {
        tokenCache.put(token,"1");
    }

    //一天
    private long expireTime = 86400000;

    public ControlToken containsToken(String token) {
        if(!tokenCache.containsKey(token)){
            return null;
        }
        ControlToken controlToken = parseToken(token);
        long time = System.currentTimeMillis() - controlToken.getTimeStamp();
        if(time > 86400000){
            tokenCache.remove(token);
            return null;
        }
        return controlToken;
    }

}
