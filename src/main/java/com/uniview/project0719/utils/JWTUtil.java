package com.uniview.project0719.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Map;
/**
 * JWT的工具类
 */

public class JWTUtil {
    public static String createJWT(Map<String, Object> userMap) throws JOSEException {
        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.HS256).type(JOSEObjectType.JWT).build();
        Payload payload = new Payload(userMap);
        JWSObject jwsObject = new JWSObject(header, payload);
        // SECRET_KEY 为 32 字节长度的字符串，以符合 HS256 算法的要求。这里暂不做修改
        // 定义常量使用命名规则，全大写，单词之间用下划线分隔
        String SECRET_KEY = "中中中中中中中中中中中";
        // 在 MACSigner 构造函数中使用 UTF-8 编码将密钥转换为字节数组。
        MACSigner macSigner = new MACSigner(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        jwsObject.sign(macSigner);
        return jwsObject.serialize();
    }
    public static Map<String, Object> getJWTUserInfo(String jwt) throws ParseException {
        JWSObject parse = JWSObject.parse(jwt);
        return parse.getPayload().toJSONObject();

    }
}
