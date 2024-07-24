package com.uniview.project0719.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;

import java.text.ParseException;
import java.util.Map;

/**
 * JWT的工具类
 */
public class JWTUtil {
    public static String createJWT(Map map) throws JOSEException {
        //JWSHeader: 表示JWT的头部。
        //JWSAlgorithm.HS256: 指定使用HMAC SHA-256算法进行签名。HS256是一种对称加密算法，使用相同的密钥进行签名和验证。
        //JOSEObjectType.JWT: 指明这个令牌是JSON Web Token。
        //Builder: 用于构建 JWSHeader 对象的构建器。
        //算法（alg）: HS256
        //类型（typ）: JWT
        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.HS256)
                .type(JOSEObjectType.JWT).build();
        System.out.println(header.toBase64URL());
        //2.生成载荷
        Payload payload = new Payload(map);
        System.out.println(payload.toBase64URL());
        //3.生成签名 加密方式HS256 (头部 + 载荷 + 密钥)
        JWSObject jwsObject = new JWSObject(header,payload);
        String secretKey = "今天天气不错,出去吃火锅呀!!!!!!";//不可少于256bits
        MACSigner signer = new MACSigner(secretKey);
        jwsObject.sign(signer);//加密

        return jwsObject.serialize();//吧jwt对象反序列化成字符串
    }

    /**
     *序列化
     */
    public static Map getJWTUserInfo(String jwt) throws ParseException {
        JWSObject jwtObj = JWSObject.parse(jwt);
        return jwtObj.getPayload().toJSONObject();
    }
}
