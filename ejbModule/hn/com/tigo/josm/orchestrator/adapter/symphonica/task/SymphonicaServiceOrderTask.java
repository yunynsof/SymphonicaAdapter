package hn.com.tigo.josm.orchestrator.adapter.symphonica.task;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import org.apache.log4j.Logger;

import hn.com.tigo.josm.common.adapter.AbstractAdapter;
import hn.com.tigo.josm.common.adapter.AdapterValidationType;
import hn.com.tigo.josm.common.adapter.dto.TaskRequestType;
import hn.com.tigo.josm.common.adapter.dto.TaskResponseType;
import hn.com.tigo.josm.common.adapter.task.AbstractTask;
import hn.com.tigo.josm.common.adapter.task.Task;
import hn.com.tigo.josm.common.exceptions.AdapterException;
import hn.com.tigo.josm.orchestrator.adapter.symphonica.SymphonicaAdapter;
import hn.com.tigo.josm.orchestrator.adapter.symphonica.utils.SymphonicaConstantsAdapter;
import hn.com.tigo.josm.orchestrator.driver.symphonica.SymphonicaDriver;
import hn.com.tigo.josm.orchestrator.driver.symphonica.operation.SymphonicaServiceOrderTaskOperation;
import hn.com.tigo.josm.orchestrator.driver.symphonica.operation.SymphonicaWorkflowOrderTaskOperation;

/**
 * The Class SymphonicaServiceOrderTask is used to execute the method
 * SymphonicaServiceOrderTask (SymphonicaDriver)
 * 
 * @author Laurent G Cáceres
 * @version 1.0.0
 * @since 26/07/2022
 */
@WebService
@Stateless(mappedName = "SymphonicaServiceOrderTask")
public class SymphonicaServiceOrderTask extends AbstractTask<SymphonicaServiceOrderTaskOperation, SymphonicaDriver>
		implements Task {

	/** Attribute that determine log. */
	protected static final transient Logger LOGGER = Logger.getLogger(SymphonicaServiceOrderTask.class);
	
	
	@EJB
	private SymphonicaAdapter symphonicaAdapter;

	/**
	 * Method responsible for obtaining singleton instance that manages the adapter.
	 * 
	 * @return the singleton adapter
	 */
	@Override
	protected AbstractAdapter<SymphonicaDriver> getSingletonAdapter() {
		return symphonicaAdapter;
	}
	
	@Override
	protected SymphonicaServiceOrderTaskOperation createRequest(final TaskRequestType taskType) throws AdapterException {				
		final String json = SymphonicaAdapter.getParameterValue(SymphonicaConstantsAdapter.JSON, taskType);
		printParameterValue(SymphonicaConstantsAdapter.JSON, json);
		validateParameter(SymphonicaConstantsAdapter.JSON, json, AdapterValidationType.ALFANUMERIC_ALL, true);

		return new SymphonicaServiceOrderTaskOperation(json);
	}


	@Override
	protected TaskResponseType executeDriverTask(SymphonicaDriver driver) throws AdapterException {
		return driver.executeDriver(request);
	}
}
