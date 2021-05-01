package cn.zzy.library_web.jwt;



import cn.zzy.library_web.config.SpringUtil;
import cn.zzy.library_web.entity.User;


import cn.zzy.library_web.response.ResponseData;
import cn.zzy.library_web.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.stereotype.Component;



import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Component
public class JWTHS256 {
    private UserService userService = SpringUtil.getBean(UserService .class);

    // token加密时使用的秘钥，一旦得到此秘钥也就可以伪造token了
    public static String secretKey = "ZzyLibraryJwtSecretKey";
    // 代表token的有效时间
    public final static long KEEP_TIME = 180 * 1000;

    /**
     * JWT由3个部分组成,分别是 头部Header,载荷Payload一般是用户信息和声明,签证Signature一般是密钥和签名
     * 当头部用base64进行编码后一般都会呈现eyJ...形式,而载荷为非强制使用,签证则包含了哈希算法加密后的数据,
     * 包括转码后的header,payload和secretKey
     *
     * @param id 用户id
     * @param issuer 签发者
     * @param subject 一般用户名
     * @return String
     */
    public static String generateToken(String id, String issuer, String subject) {
        long ttlMillis = KEEP_TIME;
        // 使用Hash256算法进行加密
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 获取当前时间戳
        long nowMills = System.currentTimeMillis();
        Date now = new Date(nowMills);
        // 将秘钥转成base64形式再转成字节码
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        // 对其使用Hash256进行加密
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        // JWT生成类，此时设置iat， 以及根据传入的id设置token
        JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now);
        // 由于payload（有效载荷）是非必须的，所以这时加入检测
        if (subject != null) {
            builder.setSubject(subject);
        }
        if (issuer != null) {
            builder.setIssuer(issuer);
        }
        // 进行签名，生成signature
        builder.signWith(signatureAlgorithm, signingKey);
        if (ttlMillis >= 0) {
            long expMills = nowMills + ttlMillis;
            Date exp = new Date(expMills);
            builder.setExpiration(exp);
        }
        // 返回最终的token结果
        return builder.compact();
    }

    /**
     * 此函数用于更新token
     * @param token token
     * @return String
     */
    public String updateToken(String token) {
        // Claims 就是包含了我们的payload信息类
        Claims claims = disassembleToken(token);
        String id = claims.getId();
        String subject = claims.getSubject();
        String issuer = claims.getIssuer();
        // 生成新的token，根据现在的时间
        return generateToken(id, issuer, subject);
    }

    /**
     * 将token解析，将payload信息包装成Claims类再返回
     * @param token token
     * @return Claims
     */
    private static Claims disassembleToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .parseClaimsJws(token).getBody();
        return claims;
    }
    public static int getTokenUserId(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        token = token.substring(1,token.length() - 1);

        Claims claims = disassembleToken(token);
        return Integer.parseInt(claims.getId());
    }
    public ResponseData verifyToken(String token){
        ResponseData responseData = null;
        Claims claims = null;

        claims = disassembleToken(token);
        if (claims == null){
            return ResponseData.unauthorized();
        }
        System.out.println(claims);
        String id = claims.getId();
        String subject = claims.getSubject();
        String issuer = claims.getIssuer();
        String preSubject = "Library-Security-Demo";
        User user = null;

        user = userService.getUserById(Integer.parseInt(id));


        if (user == null){
            return ResponseData.unauthorized();
        }
        if (!user.getUserName().equals(subject) || !issuer.equals(preSubject)){
            return ResponseData.unauthorized();
        }
        return responseData;
    }
}
