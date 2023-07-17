package net.zxclass.online_class_ww.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.zxclass.online_class_ww.model.entity.User;

import java.util.Date;

/**
 * Jwt工具类
 * 注意点:
 * 1、生成的token, 是可以通过base64进行解密出明文信息
 * 2、base64进行解密出明文信息，修改再进行编码，则会解密失败
 * 3、无法作废已颁布的token，除非改秘钥
 */
public class JWTUtils {


    /**
     * 过期时间，一周
     * 单位：毫秒
     */
    private  static final long EXPIRE = 60000 * 60 * 24 * 7;
//    private  static final long EXPIRE = 1;


    /**
     * 加密秘钥
     */
    private  static final String SECRET = "zxclass.net168";


    /**
     * 令牌前缀
     */
    private  static final String TOKEN_PREFIX = "zxclass";


    /**
     * 谁颁布的，主题
     * subject
     */
    private  static final String SUBJECT = "zxclass";


    /**
     * 根据用户信息，生成令牌
     * @param user
     * @return
     */
    public static String geneJsonWebToken(User user){

        String token = Jwts.builder().setSubject(SUBJECT)      //链式调用
                .claim("head_img",user.getHeadImg())
                .claim("id",user.getId())
                .claim("name",user.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))   //令牌过期时间（当前时间+过期时间）
                .signWith(SignatureAlgorithm.HS256,SECRET).compact();   //签名，指定加密算法，，compact()返回一个字符串

        token = TOKEN_PREFIX + token;    //拼一下，看需要，也可以不拼


        return token;
    }


    /**
     * 校验token的方法
     * @param token
     * @return
     */
    public static Claims checkJWT(String token){

        try{

            final  Claims claims = Jwts.parser().setSigningKey(SECRET)    //parser() 解析密钥的
                    .parseClaimsJws(token.replace(TOKEN_PREFIX,"")).getBody();    //解密之前要将前缀替换掉，如果没有，不需要替换,这些复杂操作知识为了让他更难破解

            return claims;

        }catch (Exception e){
            return null;    //解密失败我们就出异常，然后就返回null
        }

    }

}
