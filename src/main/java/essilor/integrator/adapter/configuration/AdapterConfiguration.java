package essilor.integrator.adapter.configuration;

import essilor.integrator.adapter.domain.AdapterConfigInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import java.util.Map;

@Configuration
public class AdapterConfiguration {

    @Autowired
    private AdapterConfigurationService configurationService;

    @Autowired
    private SaajSoapMessageFactory messageFactory;

    @Autowired
    private Jaxb2Marshaller uploadFileServiceMarshaller;

    @Autowired
    private Jaxb2Marshaller getOrderServiceMarshaller;

    @Autowired
    private Jaxb2Marshaller owValidationServiceMarshaller;

    @Autowired
    private Jaxb2Marshaller supplierServiceMarshaller;

    @Value("${adapter.uri.uploadfile}")
    private String uploadFileUri;

    @Value("${adapter.uri.getorder}")
    private String getOrderUri;

    @Value("${adapter.uri.owvalidation}")
    private String owvalidationUri;

    @Value("${adapter.uri.supplier}")
    private String supplierUri;

    @Value("${adapter.datasources}")
    private String dataSources;

    @Bean
    Map<String, AdapterConfigInfo> adapterConfigMap() {
        return configurationService.getAdapterConfiguration(dataSources);
    }

    @Bean
    public WebServiceTemplate uploadFileWsTemplate() {
        WebServiceTemplate wsTemplate = new WebServiceTemplate(this.messageFactory);
        wsTemplate.setMarshaller(this.uploadFileServiceMarshaller);
        wsTemplate.setUnmarshaller(this.uploadFileServiceMarshaller);
        wsTemplate.setDefaultUri(uploadFileUri);
        wsTemplate.setMessageSender(new HttpComponentsMessageSender());
        return wsTemplate;
    }

    @Bean
    WebServiceTemplate getOrderWsTemplate() {
        WebServiceTemplate wsTemplate = new WebServiceTemplate(this.messageFactory);
        wsTemplate.setMarshaller(this.getOrderServiceMarshaller);
        wsTemplate.setUnmarshaller(this.getOrderServiceMarshaller);
        wsTemplate.setDefaultUri(getOrderUri);
        wsTemplate.setMessageSender(new HttpComponentsMessageSender());
        return wsTemplate;
    }

    @Bean
    WebServiceTemplate owValidationWsTemplate() {
        WebServiceTemplate wsTemplate = new WebServiceTemplate(this.messageFactory);
        wsTemplate.setMarshaller(this.owValidationServiceMarshaller);
        wsTemplate.setUnmarshaller(this.owValidationServiceMarshaller);
        wsTemplate.setDefaultUri(owvalidationUri);
        wsTemplate.setMessageSender(new HttpComponentsMessageSender());
        return wsTemplate;
    }


    @Bean
    WebServiceTemplate supplierWSTemplate() {
        WebServiceTemplate wsTemplate = new WebServiceTemplate(this.messageFactory);
        wsTemplate.setMarshaller(this.supplierServiceMarshaller);
        wsTemplate.setUnmarshaller(this.supplierServiceMarshaller);
        wsTemplate.setDefaultUri(supplierUri);
        wsTemplate.setMessageSender(new HttpComponentsMessageSender());
        return wsTemplate;
    }
}
