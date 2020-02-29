package essilor.integrator.adapter.service;

import org.junit.Test;

public class UploadOrderByActionResultBuilderTest {

    private String errorText = "<?xml version=\"1.0\" encoding=\"utf-16\"?>\n" +
            "<ERROR_MESSAGE xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
            "  <LOGIN UserName=\"ESCZ#CZ999000\" RefID=\"VWOPTOSYSCZ\" />\n" +
            "  <ERROR xmlns=\"http://services.visionweb.com/UploadCustomFileAction\">\n" +
            "    <ERROR CODE=\"33608\" d3p1:SEVERITY=\"Error\" xmlns:d3p1=\"WebServiceErrorSeverity\">Není možné použít zvolený průměr.</ERROR>\n" +
            "    <ERROR CODE=\"33608\" d3p1:SEVERITY=\"Error\" xmlns:d3p1=\"WebServiceErrorSeverity\">Není možné použít zvolený průměr.</ERROR>\n" +
            "  </ERROR>\n" +
            "</ERROR_MESSAGE>";

    @Test
    public void testExtractErrorCode() {
        UploadOrderByActionResultBuilder builder = (UploadOrderByActionResultBuilder) UploadOrderByActionResultBuilder
                .getInstance(null, null, null);

        System.out.println(builder.extractErrorCode(errorText));
    }
}