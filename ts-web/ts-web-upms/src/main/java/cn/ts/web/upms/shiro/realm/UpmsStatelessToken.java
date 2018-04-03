package cn.ts.web.upms.shiro.realm;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import net.sf.json.JSONObject;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * shiro-jwt无状态token
 *
 * @author tidy
 */
public class UpmsStatelessToken implements AuthenticationToken {

    private Jws<Claims> parser;

    private JSONObject header;

    private JSONObject body;

    private String signature;

    public UpmsStatelessToken(Jws<Claims> parser) {
        this.parser = parser;
        this.header = JSONObject.fromObject(this.parser.getHeader());
        this.body = JSONObject.fromObject(this.parser.getBody().getSubject());
        this.signature = this.parser.getSignature();
    }

    public Jws<Claims> getParser() {
        return parser;
    }

    public void setParser(Jws<Claims> parser) {
        this.parser = parser;
    }

    public JSONObject getHeader() {
        return header;
    }

    public void setHeader(JSONObject header) {
        this.header = header;
    }

    public JSONObject getBody() {
        return body;
    }

    public void setBody(JSONObject body) {
        this.body = body;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public Object getPrincipal() {
        return this.body.get("username");
    }

    @Override
    public Object getCredentials() {
        return this.body.get("password");
    }
}
