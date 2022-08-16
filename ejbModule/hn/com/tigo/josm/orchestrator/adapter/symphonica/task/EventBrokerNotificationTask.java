package hn.com.tigo.josm.orchestrator.adapter.symphonica.task;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

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
import hn.com.tigo.josm.orchestrator.driver.symphonica.operation.EventBrokerNotificationOperation;

/**
 * EventBrokerNotificationTask Class in charge of instantiating and carrying out the processes related to the EventBrokerNotification task.
 *
 * @author Yuny Rene Rodriguez Perez {@literal<mailto: yrodriguez@hightech-corp.com />}
 * @version  1.0.0
 * @since 08-16-2022 09:45:48 AM 2022
 */
@WebService
@Stateless(mappedName = "EventBrokerNotificationTask")
public class EventBrokerNotificationTask  extends AbstractTask<EventBrokerNotificationOperation, SymphonicaDriver> implements Task {
	
	/** Attribute that determine symphonicaAdapter. */
	@EJB
	private SymphonicaAdapter symphonicaAdapter;

	/* (non-Javadoc)
	 * @see hn.com.tigo.josm.common.adapter.task.AbstractTask#getSingletonAdapter()
	 */
	@Override
	protected AbstractAdapter<SymphonicaDriver> getSingletonAdapter() {
		return symphonicaAdapter;
	}

	/* (non-Javadoc)
	 * @see hn.com.tigo.josm.common.adapter.task.AbstractTask#executeDriverTask(java.lang.Object)
	 */
	@Override
	protected TaskResponseType executeDriverTask(final SymphonicaDriver driver) throws AdapterException {
		return driver.executeDriver(request);
	}

	/* (non-Javadoc)
	 * @see hn.com.tigo.josm.common.adapter.task.AbstractTask#createRequest(hn.com.tigo.josm.common.adapter.dto.TaskRequestType)
	 */
	@Override
	protected EventBrokerNotificationOperation createRequest(final TaskRequestType taskType) throws AdapterException {

		final String json = SymphonicaAdapter.getParameterValue(SymphonicaConstantsAdapter.JSON, taskType);
		printParameterValue(SymphonicaConstantsAdapter.JSON, json);
		validateParameter(SymphonicaConstantsAdapter.JSON, json, AdapterValidationType.ALL, true);

		return new EventBrokerNotificationOperation(json);
	}
}
