package hn.com.tigo.josm.orchestrator.adapter.symphonica.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import hn.com.tigo.josm.common.adapter.dto.ParameterArray;
import hn.com.tigo.josm.common.adapter.dto.ParameterType;
import hn.com.tigo.josm.common.adapter.dto.TaskRequestType;
import hn.com.tigo.josm.common.adapter.dto.TaskResponseType;
import hn.com.tigo.josm.common.exceptions.AdapterException;
import hn.com.tigo.josm.orchestrator.adapter.symphonica.utils.SymphonicaConstantsAdapter;

public class SymphonicaWorkflowOrderTaskTest extends AbstractTest {

	public SymphonicaWorkflowOrderTaskTest() throws Exception {
		super("java:global/SymphonicaAdapter/SymphonicaWorkflowOrderTask!hn.com.tigo.josm.common.adapter.task.Task");
	}

	@Test
	public void test()  {
		
		try {
			final TaskResponseType response = task.executeTask(buildTaskRequestAction());
			assertEquals(0, response.getResponseCode());
		} catch (AdapterException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testErr()  {
		
		try {
			final TaskResponseType response = task.executeTask(buildTaskRequestActionErr());
			assertEquals(0, response.getResponseCode());
		} catch (AdapterException e) {
			//fail(e.getMessage());
		}
	}
	
	private TaskRequestType buildTaskRequestAction() {
		final ParameterArray parameterArray = new ParameterArray();
		
		ParameterType parameterTypeReq = new ParameterType();
		parameterTypeReq.setName(SymphonicaConstantsAdapter.JSON);
		parameterTypeReq.setValue("{   \"externalId\":\"amz-get-test-002-Query-richy-2\",   \"priority\": \"4\",   \"description\":\"OTT Service Getting Information\",   \"category\":\"OTT\",   \"workflowOrderSpec\":{      \"code\":\"OTT_SERVICE.GET\",     \"source\":\"SYM-WOM\"   },   \"source\":\"SYM-WOM\",   \"input\":[     {       \"name\":\"MSISDN\",       \"value\":\"50212300005\"     },     {       \"name\":\"SUPPLIER\",       \"value\":\"AMAZON_PRIME\"     },     {       \"name\":\"CUSTOM_INPUT_1\",       \"value\":\"telco_code:HNTIGO\"     },     {       \"name\":\"CUSTOM_INPUT_2\",       \"value\":\"product_name:amazonVideoPrepaid\"     },{       \"name\":\"CUSTOM_INPUT_3\",       \"value\":\"product_retry:0\"     },{       \"name\":\"CUSTOM_INPUT_4\",       \"value\":\"product_sku:1135\"     }   ] }");
		parameterArray.getParameter().add(parameterTypeReq);

		final TaskRequestType taskRequestType = new TaskRequestType();
		taskRequestType.setParameters(parameterArray);
		return taskRequestType;
	}
	
	private TaskRequestType buildTaskRequestActionErr() {
		final ParameterArray parameterArray = new ParameterArray();
		
		ParameterType parameterTypeReq = new ParameterType();
		parameterTypeReq.setName(SymphonicaConstantsAdapter.JSON);
		parameterTypeReq.setValue("");
		parameterArray.getParameter().add(parameterTypeReq);

		final TaskRequestType taskRequestType = new TaskRequestType();
		taskRequestType.setParameters(parameterArray);
		return taskRequestType;
	}
}
