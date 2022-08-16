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

public class SymphonicaServiceOrderTaskTest extends AbstractTest {

	public SymphonicaServiceOrderTaskTest() throws Exception {
		super("java:global/SymphonicaAdapter/SymphonicaServiceOrderTask!hn.com.tigo.josm.common.adapter.task.Task");
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
		parameterTypeReq.setValue("{   \"externalId\": \"EXT-9999\",   \"priority\": \"4\",   \"description\": \"some description\",   \"category\": \"OTT_SERVICE\",   \"region\": \"\",   \"source\": \"SYM-SOM\",   \"orderType\": \"PROVIDE\",   \"notes\": [],   \"orderItems\": [     {       \"id\": \"1\",       \"action\": \"ADD\",       \"appointments\": [],       \"orderItemRelationships\": [],       \"service\": {         \"serviceSpecification\": {           \"code\": \"OTT_SERVICE_CFS\",           \"version\": \"1.0\"         },         \"characteristics\": [           {             \"name\": \"SUPPLIER\",             \"value\": \"TEST\"           },           {             \"name\": \"EMAIL\",             \"value\": \"ott.user.new@ott.com\"           },           {             \"name\": \"USER_ID\",             \"value\": \"ott.user\"           },           {             \"name\": \"PASSWORD\",             \"value\": \"ott.password\"           },           {             \"name\": \"SUBS_TYPE\",             \"value\": \"10\"           },           {             \"name\": \"URN\",             \"value\": \"tve:hbo\"           },           {             \"name\": \"CUSTOM_INFO\",             \"value\": \"PACKAGE\",             \"characteristicRelationships\": [               {                 \"name\": \"VALUE\",                 \"value\": \"Premium\"               }             ]           },           {             \"name\": \"CUSTOM_INFO\",             \"value\": \"FAMILY_PLAN\",             \"characteristicRelationships\": [               {                 \"name\": \"VALUE\",                 \"value\": \"true\"               }             ]           }         ],         \"places\":[           {             \"name\":\"Main Address\",             \"address\":{               \"streetName\":\"Wedeking\",               \"streetNumber\":\"6045\",               \"streetSuffix\":\"Ave\",               \"postCode\":\"47715\",               \"city\":\"Evansville\",               \"stateOrProvince\":\"IN\",               \"country\":\"United States\"             }           }         ],         \"publicIdentifier\": \"HN-TEST_OTT_CUSTOMER_01_SKU_SUPPLIERTEST\"       }     }   ],   \"orderRelationships\": [],   \"relatedParty\": [     {       \"id\": \"HN-TEST_OTT_CUSTOMER_01\",       \"source\": \"BSS\",       \"name\": \"Juan Perez\",       \"role\": \"CUSTOMER\"     }   ],   \"extraValues\": [] } ");
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
