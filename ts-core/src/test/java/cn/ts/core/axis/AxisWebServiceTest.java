package cn.ts.core.axis;

import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

/**
 * @author Created by YL on 2017/5/23.
 */
public class AxisWebServiceTest {
    @Test
    public void test() {
        final String request = "<?xml version=\"1.0\" " +
                "encoding=\"GB2312\"?><SendData><BaseInfo><syscode>10000MH</syscode><password>123456</password" +
                "><FuncCode>SmpServInfo</FuncCode></BaseInfo><param rows=\"1\" cols=\"5\"><row rownum=\"1\"><col " +
                "colnum=\"1\" param_id=\"1\">755</col><col colnum=\"2\" param_id=\"2\">18098912509</col><col " +
                "colnum=\"3\" param_id=\"3\">01</col><col colnum=\"4\" param_id=\"4\"></col><col colnum=\"5\" " +
                "param_id=\"5\"></col></row></param></SendData>";
        final String url = "http://132.122.16.168:8882/smtccs_agent_intf/services/wsagent";
        try {
            String call = AxisWebService.custom().url(url).timeout(5000).portName(new QName("WsAgent"))
                    .operationName(new QName("input")).build().call(new Object[]{request});
            System.out.println(call);

            System.out.println("-------------------------------------------------------");

            AxisParameter[] parameters = new AxisParameter[]{
                    new AxisParameter(new QName("arg0"), org.apache.axis.encoding.XMLType.XSD_STRING, ParameterMode.IN),
                    new AxisParameter(new QName("arg1"), org.apache.axis.encoding.XMLType.XSD_STRING, ParameterMode.IN),
                    new AxisParameter(new QName("arg2"), org.apache.axis.encoding.XMLType.XSD_STRING,
                            ParameterMode.IN)};
            call = AxisWebService.custom().url("http://180.153.61.12:8082/SingleLoginInInterface/SingleLoginWSPort")
                    .timeout(5000).portName(new QName("http://SingleLoginAtIM/", "SingleLoginWSDelegate"))
                    .operationName(new QName("http://SingleLoginAtIM/", "userChecker"))
                    .returnType(org.apache.axis.encoding.XMLType.SOAP_STRING).parameters(parameters).build()
                    .call(new Object[]{"guangdongRobot", "guangdong1234", "oBBmBjht5Gr_Wa2BrHQgzqYsw-Pk"});
            System.out.println(call);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
