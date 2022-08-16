package hn.com.tigo.josm.orchestrator.adapter.symphonica.task;

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
import hn.com.tigo.josm.orchestrator.driver.symphonica.operation.SymphonicaWorkflowOrderTaskOperation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import org.apache.log4j.Logger;

/**
 * The Class SymphonicaWorkflowOrderTask is used to execute the method SymphonicaWorkflowOrderTask (SymphonicaDriver) 
 * @author Leonardo Vijil
 * @version 1.0.0
 * @since 24/02/2022
 */
@WebService
@Stateless(mappedName = "SymphonicaWorkflowOrderTask")
public class SymphonicaWorkflowOrderTask extends AbstractTask<SymphonicaWorkflowOrderTaskOperation, SymphonicaDriver> implements Task {
	
	/** Attribute that determine log. */
	protected static final transient Logger LOGGER = Logger.getLogger(SymphonicaWorkflowOrderTask.class);
	
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
	
	/**
	 * Method responsible to create the request for SymphonicaWorkflowOrderTask (NexusDriver)
	 * the incoming parameter.
	 * 
	 * @param taskType
	 *            the task type is an object with a set of parameters with pair
	 *            values.
	 * @return the v.
	 * @throws AdapterException
	 *             exception controlled
	 */
	@Override
	protected SymphonicaWorkflowOrderTaskOperation createRequest(final TaskRequestType taskType) throws AdapterException {				
		final String json = SymphonicaAdapter.getParameterValue(SymphonicaConstantsAdapter.JSON, taskType);
		printParameterValue(SymphonicaConstantsAdapter.JSON, json);
		validateParameter(SymphonicaConstantsAdapter.JSON, json, AdapterValidationType.ALFANUMERIC_ALL, true);

		return new SymphonicaWorkflowOrderTaskOperation(json);
	}

	/**
	 * Method main responsible to execute SymphonicaWorkflowOrderTask task. This method access method executeAccountDeleteTask ()
	 * 
	 * @throws AdapterException
	 *             the adapter exception
	 */
	@Override
	protected TaskResponseType executeDriverTask(SymphonicaDriver driver) throws AdapterException {
		return driver.executeDriver(request);
	}
}
