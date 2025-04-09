package org.example.demo.common.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.transport.http.WsdlDefinitionHandlerAdapter;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;


@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<>(servlet, "/ws/services/*");
	}

	@Bean(name = "students")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema studentsSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("StudentPort");
		wsdl11Definition.setLocationUri("/ws/services/mystudent");
		wsdl11Definition.setTargetNamespace("http://www.example.org/demo/");
		wsdl11Definition.setSchema(studentsSchema);
		Properties props = new Properties();
		props.put("getStudent", "http://www.example.org/demo/getStudentRequest");
		props.put("findCountry", "http://www.example.org/demo/findCountryRequest");
		wsdl11Definition.setSoapActions(props);

		return wsdl11Definition;
	}

	//deploy con SOAP
	@Bean
	public WsdlDefinitionHandlerAdapter wsdlDefinitionHandlerAdapter() {
		return new WsdlDefinitionHandlerAdapter() {
			@Override
			protected String transformLocation(String location, HttpServletRequest request) {
				try {
					URI uri = new URI(location);
					return new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), -1, uri.getPath(), uri.getQuery(),
							uri.getFragment()).toString();
				} catch (URISyntaxException e) {
					return location;
				}
			}
		};
	}

	@Bean
	public XsdSchema studentsSchema() {
		return new SimpleXsdSchema(new ClassPathResource("com/xsd/demo.xsd"));
	}
}
