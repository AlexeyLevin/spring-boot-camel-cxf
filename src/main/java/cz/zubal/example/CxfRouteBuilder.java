package cz.zubal.example;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.component.cxf.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CxfRouteBuilder extends RouteBuilder {

    @Autowired
    CamelContext context;

    @Override
    public void configure() throws Exception {
        CamelContext camelContext = getContext();

        CxfEndpoint cxfEndpoint = new CxfEndpoint();
        cxfEndpoint.setAddress("http://localhost:8088/interface");
        cxfEndpoint.setServiceClass(SimpleService.class);
        cxfEndpoint.setCamelContext(camelContext);
        cxfEndpoint.setDataFormat(DataFormat.POJO);
        context.addEndpoint("myEndpoint", cxfEndpoint);

        from(cxfEndpoint)
            .setBody(constant("Hi!"));

    }

}

