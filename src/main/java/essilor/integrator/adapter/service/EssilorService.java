package essilor.integrator.adapter.service;

import essilor.integrator.adapter.domain.getorder.GetOrderAsPDFByPoNum;
import essilor.integrator.adapter.domain.getorder.GetOrderAsPDFByPoNumResponse;
import essilor.integrator.adapter.domain.getorder.GetOrderByPoNum;
import essilor.integrator.adapter.domain.getorder.GetOrderByPoNumResponse;
import essilor.integrator.adapter.domain.owvalidation.ValidateOrderFromPMS;
import essilor.integrator.adapter.domain.owvalidation.ValidateOrderFromPMSResponse;
import essilor.integrator.adapter.domain.supplier.GetSuppliers;
import essilor.integrator.adapter.domain.supplier.GetSuppliersResponse;
import essilor.integrator.adapter.domain.uploadfile.UploadCustomFile;
import essilor.integrator.adapter.domain.uploadfile.UploadCustomFileResponse;
import essilor.integrator.adapter.domain.uploadfile.UploadOrderByAction;
import essilor.integrator.adapter.domain.uploadfile.UploadOrderByActionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.annotation.Resource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

@Service
public class EssilorService {

    @Autowired
    @Qualifier("uploadFileWsTemplate")
    private WebServiceTemplate uploadFileWsTemplate;

    @Autowired
    @Qualifier("getOrderWsTemplate")
	private WebServiceTemplate getOrderWsTemplate;

    @Autowired
    @Qualifier("owValidationWsTemplate")
    private WebServiceTemplate owValidationWsTemplate;

    @Autowired
    @Qualifier("supplierWSTemplate")
    private WebServiceTemplate supplierWSTemplate;

    public UploadCustomFileResponse uploadCustomFile(UploadCustomFile request) {
        		UploadCustomFileResponse response = (UploadCustomFileResponse) uploadFileWsTemplate
                        .marshalSendAndReceive(request);
		return response;
	}

	public String uploadCustomFile(String message) {
        StringWriter respWriter = new StringWriter();
		StreamSource source = new StreamSource(new StringReader(message));
		StreamResult result = new StreamResult(respWriter);
		uploadFileWsTemplate.sendSourceAndReceiveToResult(source, result);
		return respWriter.toString();
	}

	public GetOrderByPoNumResponse getOrderByPoNum(GetOrderByPoNum request) {
        GetOrderByPoNumResponse response = (GetOrderByPoNumResponse) getOrderWsTemplate
                .marshalSendAndReceive(request);
		return response;
	}

	public String getOrderByPoNum(String message) {
        StringWriter respWriter = new StringWriter();
		StreamSource source = new StreamSource(new StringReader(message));
		StreamResult result = new StreamResult(respWriter);
		getOrderWsTemplate.sendSourceAndReceiveToResult(source, result);
		return respWriter.toString();
	}

	public GetOrderAsPDFByPoNumResponse getOrderAsPDFByPoNum(
			GetOrderAsPDFByPoNum request) {
        GetOrderAsPDFByPoNumResponse response = (GetOrderAsPDFByPoNumResponse) getOrderWsTemplate
				.marshalSendAndReceive(request);
		return response;
	}

	public String getOrderAsPDFByPoNum(String message) {
        StringWriter respWriter = new StringWriter();
		StreamSource source = new StreamSource(new StringReader(message));
		StreamResult result = new StreamResult(respWriter);
		getOrderWsTemplate.sendSourceAndReceiveToResult(source, result);
		return respWriter.toString();
	}

	public UploadOrderByActionResponse uploadOrderByAction(
			UploadOrderByAction request) {
        UploadOrderByActionResponse response = (UploadOrderByActionResponse) uploadFileWsTemplate
				.marshalSendAndReceive(request);
		return response;
	}

	public String uploadOrderByAction(String message) {
        StringWriter respWriter = new StringWriter();
		StreamSource source = new StreamSource(new StringReader(message));
		StreamResult result = new StreamResult(respWriter);
		uploadFileWsTemplate.sendSourceAndReceiveToResult(source, result);
		return respWriter.toString();
	}

	public ValidateOrderFromPMSResponse validateOrderFromPMS(
			ValidateOrderFromPMS request) {
        ValidateOrderFromPMSResponse response = (ValidateOrderFromPMSResponse) owValidationWsTemplate
				.marshalSendAndReceive(request);
		return response;
	}

	public String validateOrderFromPMS(String message) {
        StringWriter respWriter = new StringWriter();
		StreamSource source = new StreamSource(new StringReader(message));
		StreamResult result = new StreamResult(respWriter);
		owValidationWsTemplate.sendSourceAndReceiveToResult(source, result);
		return respWriter.toString();
	}

	public GetSuppliersResponse getSuppliers(GetSuppliers request) {
        GetSuppliersResponse response = (GetSuppliersResponse) supplierWSTemplate
				.marshalSendAndReceive(request);
		return response;
	}

	public String getSuppliers(String message) {
        StringWriter respWriter = new StringWriter();
		StreamSource source = new StreamSource(new StringReader(message));
		StreamResult result = new StreamResult(respWriter);
		supplierWSTemplate.sendSourceAndReceiveToResult(source, result);
		return respWriter.toString();
	}
}
