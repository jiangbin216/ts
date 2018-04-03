package cn.ts.web.upms.util;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.Session;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * Shiro Session 序列化工具
 *
 * @author Created by YL on 2017/3/12.
 */
public class ShiroSerializationUtils {

    /**
     * 序列化操作：对 Session 进行序列化
     *
     * @param serializable
     * @return
     */
    public static String serialize(Serializable serializable) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
            ShiroSerializationUtils.serialize(serializable, baos);
            byte[] value = baos.toByteArray();
            return Base64.encodeToString(value);
        } catch (Exception e) {
            throw new RuntimeException("serialize error", e);
        }
    }

    private static void serialize(Serializable obj, OutputStream outputStream) {
        if (outputStream == null) {
            throw new IllegalArgumentException("The OutputStream must not be null");
        }
        ObjectOutputStream out = null;
        try {
            // stream closed in the finally
            out = new ObjectOutputStream(outputStream);
            out.writeObject(obj);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
        }
    }

    /**
     * 反序列化操作：对 Session 进行反序列化
     *
     * @param base64
     * @return
     */
    public static Session deserialize(String base64) {
        if (base64 == null || base64.trim().length() == 0) {
            return null;
        }
        try {
            byte[] bt = Base64.decode(base64);
            ByteArrayInputStream bais = new ByteArrayInputStream(bt);
            return (Session) deserialize(bais);
        } catch (Exception e) {
            throw new RuntimeException("deserialize error", e);
        }
    }

    private static Object deserialize(InputStream inputStream) {
        if (inputStream == null) {
            throw new IllegalArgumentException("The InputStream must not be null");
        }
        ObjectInputStream in = null;
        try {
            // stream closed in the finally
            in = new ObjectInputStream(inputStream);
            return in.readObject();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
        }
    }
}
