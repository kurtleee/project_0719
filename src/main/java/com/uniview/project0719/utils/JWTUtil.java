package com.uniview.project0719.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;

import java.text.ParseException;
import java.util.Map;

public class JWTUtil {
    public static String createJWT(Map userMap) throws JOSEException {
        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.HS256).type(JOSEObjectType.JWT).build();
        Payload payload = new Payload(userMap);
        JWSObject jwsObject = new JWSObject(header, payload);
        String secretKey = "中中中中中中中中中中中";
        MACSigner macSigner = new MACSigner(secretKey);
        jwsObject.sign(macSigner);
        return jwsObject.serialize();
    }
    public static Map getJWTUserInfo(String jwt) throws ParseException {
        JWSObject parse = JWSObject.parse(jwt);
        return parse.getPayload().toJSONObject();
    }
}
