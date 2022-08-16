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

public class EventBrokerNotificationTaskTest extends AbstractTest {

	public EventBrokerNotificationTaskTest() throws Exception {
		super("java:global/SymphonicaAdapter/EventBrokerNotificationTask!hn.com.tigo.josm.common.adapter.task.Task");
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
		parameterTypeReq.setValue("{\r\n" + 
				"    \"event_header\":{\r\n" + 
				"       \"country\": \"HN\",\r\n" + 
				"       \"customer_type\": \"prepaid_mobile\",\r\n" + 
				"       \"customer_action\": \"billing\",\r\n" + 
				"       \"event_name\": \"Prime_Video_Purchase\",\r\n" + 
				"       \"event_code\": \"PVME\",\r\n" + 
				"       \"event_description\": \"Notificacion para compra de paquete Amazon Prime Video\",\r\n" + 
				"       \"event_source\": \"exacaster\",\r\n" + 
				"       \"event_uid\": \"M1\"\r\n" + 
				"    },\r\n" + 
				"    \"event_data\":{\r\n" + 
				"       \"client_name\": \"\",\r\n" + 
				"       \"subscriber\":\"94347003\",\r\n" + 
				"       \"transaction_type\": \"Prime_Video_Purchase\",\r\n" + 
				"       \"transaction_status\": \"Completed\"\r\n" + 
				"    },\r\n" + 
				"    \"customer_profile\":{\r\n" + 
				"       \"phone_number_main_contact\":\"94347003\",\r\n" + 
				"       \"country\":\"HN\",\r\n" + 
				"       \"account_flags\": {         \r\n" + 
				"       }\r\n" + 
				"    }  \r\n" + 
				" }");
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
