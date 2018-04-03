package cn.ts.web.upms.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodec;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.sf.json.JSONObject;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JSON Web Token
 * <p>
 * 官网：https://jwt.io/
 * 
 * <pre>
 * id: 对应body中的jti。
 * issuer: 对应body中的iss。请求的发起者
 * issuedAt: 对应body中的iat。请求的发起时间
 * expiration: 对应body中的exp。过期时间
 * compressWith: 对应heaer中的zip。
 * signWith: 对应header中的alg。
 * key: 签名key
 * </pre>
 *
 * @author Created by YL on 2017/05/24
 */
public class JWT {
    private final String id;
    private final String issuer;
    private final Date issuedAt;
    private final Date expiration;
    private final CompressionCodec compressWith;
    private final SignatureAlgorithm signWith;
    private final String key;

    private JwtBuilder builder = null;
    private SecretKey secretKey = null;

    /**
     * 不允许通过new的方式创建对象
     */
    private JWT(String id, String issuer, Date issuedAt, Date expiration,
            CompressionCodec compressWith, SignatureAlgorithm signWith, String key) {
        super();
        this.id = id;
        this.issuer = issuer;
        this.issuedAt = issuedAt;
        this.expiration = expiration;
        this.compressWith = compressWith;
        this.signWith = signWith;
        this.key = key;

        if (!JWT.isNotNull(this.getKey())) {
            throw new IllegalArgumentException("key is null");
        }
        if (this.getSignWith() == null) {
            throw new IllegalArgumentException("signWith is null");
        }
        builder = Jwts.builder();
        if (JWT.isNotNull(this.getId())) {
            builder.setId(this.getId());
        }
        if (JWT.isNotNull(this.getIssuer())) {
            builder.setIssuer(this.getIssuer());
        }
        if (this.getIssuedAt() != null) {
            builder.setIssuedAt(this.getIssuedAt());
        }
        if (this.getExpiration() != null) {
            builder.setExpiration(this.getExpiration());
        }
        if (this.getCompressWith() != null) {
            builder.compressWith(this.getCompressWith());
        }
        secretKey = JWT.createKey(this.getKey());
        // 两个参数，一个是加密算法，一个秘钥
        builder.signWith(this.getSignWith(), secretKey);
    }

    public String getId() {
        return id;
    }

    public String getIssuer() {
        return issuer;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public Date getExpiration() {
        return expiration;
    }

    public String getKey() {
        return key;
    }

    public CompressionCodec getCompressWith() {
        return compressWith;
    }

    public SignatureAlgorithm getSignWith() {
        return signWith;
    }

    public static Builder copy(final JWT copy) {
        return new Builder(copy.key).id(copy.id).issuer(copy.issuer).issuedAt(copy.issuedAt)
                .expiration(copy.expiration);
    }

    public static class Builder {
        private String id;
        private String issuer;
        private Date issuedAt;
        private Date expiration;
        private CompressionCodec compressWith;
        private SignatureAlgorithm signWith = SignatureAlgorithm.HS256;
        private String key;

        public Builder(String key) {
            this.key = key;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder issuer(String issuer) {
            this.issuer = issuer;
            return this;
        }

        public Builder issuedAt(Date issuedAt) {
            this.issuedAt = issuedAt;
            return this;
        }

        public Builder expiration(Date expiration) {
            this.expiration = expiration;
            return this;
        }

        public Builder compressWith(CompressionCodec compressWith) {
            this.compressWith = compressWith;
            return this;
        }

        public Builder signWith(SignatureAlgorithm signWith) {
            this.signWith = signWith;
            return this;
        }

        public Builder key(String key) {
            this.key = key;
            return this;
        }

        public JWT build() {
            return new JWT(id, issuer, issuedAt, expiration, compressWith, signWith, key);
        }
    }


    /**
     * 根据key生成SecretKey
     * 
     * @throws UnsupportedEncodingException
     */
    private static SecretKey createKey(String key) {
        byte[] b = null;
        try {
            b = key.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            // 如果明确有这个编码，这个异常可以忽略掉
        }
        SecretKeySpec skeySpec = new SecretKeySpec(b, "AES");
        return skeySpec;
    }

    private static boolean isNotNull(String str) {
        if (str != null && str.trim().length() > 0)
            return true;
        return false;
    }

    /**
     * 构建了JWT，并将其序列化为一个紧凑的，URL安全的字符串
     * 
     * @param subject 传递的参数
     */
    public String compact(String subject) {
        if (!JWT.isNotNull(subject)) {
            throw new IllegalArgumentException("subject is null");
        }
        builder.setSubject(subject);
        return builder.compact();
    }

    /**
     * 根据构建器的当前配置状态解析指定的紧凑型串行化JWS字符串，并返回结果声明JWS实例
     * 
     * @param compact 加密的jwt信息
     */
    public Jws<Claims> parser(String compact) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(compact);
    }


    // 初始化jwt时，推荐使用静态成员变量，多少种jwt实现就多少个静态成员变量。这样可以减少JwtBuilder、SecretKey等对象的创建
    private static final String KEY = "10000"; // 密钥
    private static final JWT jwt = new JWT.Builder(KEY).build();
    private static final JWT jwt_10086 = new JWT.Builder("10086").build();
    // 如果要使用不同的jwt对象构建解析，推荐使用这种方法
    // private static final JWT jwt_10086 = JWT.copy(jwt).key("10086").build();

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", "88888");
        map.put("username", "yangli11");
        // 一般将对象转换成json串再构建JWT串
        String compact = jwt.compact(JSONObject.fromObject(map).toString());
        System.out.println("compact : " + compact);
        Jws<Claims> parser = jwt.parser(compact);
        System.out.println("parser : " + parser);
        System.out.println("parser : " + parser.getBody().getSubject());
        System.out.println("parser : " + parser.getBody().get("sub"));
        // 由于密钥不同，这里会报错
        System.out.println(jwt_10086.parser(compact));
    }
}
