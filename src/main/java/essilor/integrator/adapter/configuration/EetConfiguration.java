package essilor.integrator.adapter.configuration;

import essilor.integrator.adapter.domain.eet.EetConfigInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.soap.security.wss4j.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j.support.CryptoFactoryBean;

import javax.xml.bind.*;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class EetConfiguration {

    private static final Logger logger = Logger.getLogger(EetConfiguration.class);

    @Autowired
    private EetConfigurationService configurationService;

    @Autowired
    private ApplicationContext applicationContext;


    @Bean
    Map<String, EetConfigInfo> eetConfigMap() {
        return configurationService.getEetConfiguration();
    }

    @Bean
    Map<String, WebServiceTemplate> wsTemplateMap() throws Exception {
        String eetUri = configurationService.getEetUri();
        logger.info("eet ur: " + eetUri);
        String keystoreType = configurationService.getEetKeystoreType();
        logger.info("eet keystore type: " + keystoreType);
        Map<String, EetConfigInfo> eetConfig = configurationService.getEetConfiguration();
        Map<String, WebServiceTemplate> wsMap = new HashMap<>();
        for (EetConfigInfo eetConfigInfo : eetConfig.values()) {
            WebServiceTemplate wsTemplate = new WebServiceTemplate(applicationContext.getBean("eetMessageFactory", SaajSoapMessageFactory.class));
            wsTemplate.setMarshaller(applicationContext.getBean("eetServiceMarshaller", Jaxb2Marshaller.class));
            wsTemplate.setUnmarshaller(applicationContext.getBean("eetServiceMarshaller", Jaxb2Marshaller.class));
            wsTemplate.setDefaultUri(eetUri);
            wsTemplate.setInterceptors(new ClientInterceptor[] {
                    getWsSecurityInterceptor(eetConfigInfo, keystoreType)
            });
            wsMap.put(eetConfigInfo.getKod(), wsTemplate);
        }
        return wsMap;
    }

    private Wss4jSecurityInterceptor getWsSecurityInterceptor(EetConfigInfo eetConfigInfo, String keystoreType) throws Exception {
        Wss4jSecurityInterceptor interceptor = new Wss4jSecurityInterceptor();
        interceptor.setSecurementActions("Signature");
        interceptor.setSecurementSignatureKeyIdentifier("DirectReference");
        interceptor.setSecurementUsername(eetConfigInfo.getKeyAlias());
        interceptor.setSecurementPassword(eetConfigInfo.getKeystorePwd());
        interceptor.setSecurementSignatureAlgorithm("http://www.w3.org/2001/04/xmldsig-more#rsa-sha256");
        interceptor.setSecurementSignatureDigestAlgorithm("http://www.w3.org/2001/04/xmlenc#sha256");
        CryptoFactoryBean cryptoFactoryBean = new CryptoFactoryBean();
        cryptoFactoryBean.setKeyStoreLocation(applicationContext.getResource(eetConfigInfo.getKeystorePath()));
        cryptoFactoryBean.setKeyStorePassword(eetConfigInfo.getKeystorePwd());
        cryptoFactoryBean.setKeyStoreType(keystoreType);
        cryptoFactoryBean.afterPropertiesSet();
        interceptor.setSecurementSignatureCrypto(cryptoFactoryBean.getObject());
        return interceptor;
    }

    @Bean
    public JAXBContext eetJaxbContext() throws JAXBException {
        return JAXBContext.newInstance("cz.mfcr.fs.eet.schema.v3");
    }

    @Bean
    public Jaxb2Marshaller eetServiceMarshaller() {
        Jaxb2Marshaller m = new Jaxb2Marshaller();
        m.setContextPath("cz.mfcr.fs.eet.schema.v3");
        return m;
    }

    @Bean
    public Jaxb2Marshaller eetServiceMarshallerFormattedOutput() {
        Jaxb2Marshaller m = new Jaxb2Marshaller();
        m.setContextPath("cz.mfcr.fs.eet.schema.v3");
        Map<String, Boolean> map = new HashMap<>();
        map.put(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.setMarshallerProperties(map);
        return m;
    }

    @Bean
    public SaajSoapMessageFactory eetMessageFactory() {
        SaajSoapMessageFactory f = new SaajSoapMessageFactory();
        f.setSoapVersion(SoapVersion.SOAP_11);
        return f;
    }
}

