package cn.ts.core.axis;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

/**
 * @author Created by YL on 2017/5/23.
 */
public class AxisParameter {
    private QName paramName;
    /**
     * 参考org.apache.axis.encoding.XMLType.XSD_STRING
     */
    private QName xmlType;
    private ParameterMode parameterMode;

    public AxisParameter() {

    }

    public AxisParameter(QName paramName, QName xmlType, ParameterMode parameterMode) {
        this.paramName = paramName;
        this.xmlType = xmlType;
        this.parameterMode = parameterMode;
    }

    public QName getParamName() {
        return paramName;
    }

    public void setParamName(QName paramName) {
        this.paramName = paramName;
    }

    public QName getXmlType() {
        return xmlType;
    }

    public void setXmlType(QName xmlType) {
        this.xmlType = xmlType;
    }

    public ParameterMode getParameterMode() {
        return parameterMode;
    }

    public void setParameterMode(ParameterMode parameterMode) {
        this.parameterMode = parameterMode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AxisParameter [paramName=").append(paramName).append(", xmlType=").append(xmlType)
                .append(", parameterMode=").append(parameterMode).append("]");
        return builder.toString();
    }
}
