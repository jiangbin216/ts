package cn.ts.core.axis;

import cn.ts.core.exp.CoreException;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

/**
 * 调用webservice接口工具类
 * <p>
 * <pre>
 * url：webservice接口地址
 * timeout：调用超时时间。默认：8000ms
 * portName：对应wsdl文档的wsdl:port name属性值
 * portTypeName：对应wsdl文档的wsdl:portType name属性值
 * operationName：调用的接口方法。wsdl:operation name属性值
 * parameters：call.addParameter(QName paramName, QName xmlType, ParameterMode parameterMode)方法
 * useSOAPAction：是否使用saop action。默认：false
 * soapActionURI：对应wsdl文档的soap:operation soapAction属性值。如果useSOAPAction=true时有效
 * returnType：返回格式，参考：org.apache.axis.encoding.XMLType.SOAP_STRING
 * </pre>
 *
 * @author Created by YL on 2017/5/23.
 */
public class AxisWebService {
    private final String url;
    private final int timeout;
    private final QName portName;
    private final QName portTypeName;
    private final QName operationName;
    private final AxisParameter[] parameters;
    private final boolean useSOAPAction;
    private final String soapActionURI;
    private final QName returnType;

    AxisWebService(String url, int timeout, QName portName, QName portTypeName, QName operationName,
                   AxisParameter[] parameters, boolean useSOAPAction, String soapActionURI, QName returnType) {
        super();
        this.url = url;
        this.timeout = timeout;
        this.portName = portName;
        this.portTypeName = portTypeName;
        this.operationName = operationName;
        this.parameters = parameters;
        this.useSOAPAction = useSOAPAction;
        this.soapActionURI = soapActionURI;
        this.returnType = returnType;
    }

    public static Builder custom() {
        return new Builder();
    }

    public static Builder copy(AxisWebService c) {
        return new Builder().url(c.getUrl()).timeout(c.getTimeout()).portName(c.getPortName())
                .portTypeName(c.getPortTypeName()).operationName(c.getOperationName()).parameters(c.getParameters())
                .useSOAPAction(c.isUseSOAPAction()).soapActionURI(c.getSoapActionURI()).returnType(c.getReturnType());
    }

    public String getUrl() {
        return url;
    }

    public int getTimeout() {
        return timeout;
    }

    public QName getPortName() {
        return portName;
    }

    public QName getPortTypeName() {
        return portTypeName;
    }

    public QName getOperationName() {
        return operationName;
    }

    public AxisParameter[] getParameters() {
        return parameters;
    }

    public boolean isUseSOAPAction() {
        return useSOAPAction;
    }

    public String getSoapActionURI() {
        return soapActionURI;
    }

    public QName getReturnType() {
        return returnType;
    }

    public static class Builder {
        private String url = "";
        private int timeout = 8000;
        private QName portName = null;
        private QName portTypeName = null;
        private QName operationName = null;
        private AxisParameter[] parameters = null;
        private boolean useSOAPAction = false;
        private String soapActionURI = "";
        private QName returnType = null;

        Builder() {
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder timeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder portName(QName portName) {
            this.portName = portName;
            return this;
        }

        public Builder portTypeName(QName portTypeName) {
            this.portTypeName = portTypeName;
            return this;
        }

        public Builder operationName(QName operationName) {
            this.operationName = operationName;
            return this;
        }

        public Builder parameters(AxisParameter[] parameters) {
            this.parameters = parameters;
            return this;
        }

        public Builder useSOAPAction(boolean useSOAPAction) {
            this.useSOAPAction = useSOAPAction;
            return this;
        }

        public Builder soapActionURI(String soapActionURI) {
            this.soapActionURI = soapActionURI;
            return this;
        }

        public Builder returnType(QName returnType) {
            this.returnType = returnType;
            return this;
        }

        public AxisWebService build() {
            return new AxisWebService(url, timeout, portName, portTypeName, operationName, parameters, useSOAPAction,
                    soapActionURI, returnType);
        }
    }

    public String call(Object[] request) throws CoreException {
        try {
            if (AxisWebService.isNullOrEmpty(this.getUrl())) {
                throw new CoreException("url参数不能为空");
            }
            if (this.isUseSOAPAction()) {
                if (AxisWebService.isNullOrEmpty(this.getSoapActionURI())) {
                    throw new CoreException("soapActionURI参数不能为空");
                }
            }
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(new URL(this.getUrl()));
            call.setTimeout(this.getTimeout());
            if (this.getPortName() != null) {
                call.setPortName(this.getPortName());
            }
            if (this.getPortTypeName() != null) {
                call.setPortTypeName(this.getPortTypeName());
            }
            if (this.getOperationName() != null) {
                call.setOperationName(this.getOperationName());
            }
            AxisParameter[] parameters = this.getParameters();
            if (parameters != null) {
                for (AxisParameter parameter : parameters) {
                    call.addParameter(parameter.getParamName(), parameter.getXmlType(), parameter.getParameterMode());
                }
            }
            if (this.isUseSOAPAction()) {
                // 在Service、Call等对象创建前验证soapActionURI是否为空，防止产生不必要对象
                call.setUseSOAPAction(this.isUseSOAPAction());
                call.setSOAPActionURI(this.getSoapActionURI());
            }
            if (this.getReturnType() != null) {
                call.setReturnType(this.getReturnType());
            }
            String response = (String) call.invoke(request);
            return response;
        } catch (ServiceException e) {
            throw new CoreException("创建Call对象异常", e);
        } catch (MalformedURLException e) {
            throw new CoreException("未知协议", e);
        } catch (RemoteException e) {
            if (e.getMessage().contains("connect timed out")) {
                throw new CoreException("连接超时", e);
            }
            throw new CoreException(e);
        }
    }

    private static boolean isNullOrEmpty(String str) {
        if (str != null && !str.trim().equals(""))
            return false;
        return true;
    }
}
