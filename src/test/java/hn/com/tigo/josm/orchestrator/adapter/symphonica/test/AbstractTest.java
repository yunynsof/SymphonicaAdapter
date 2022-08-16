package hn.com.tigo.josm.orchestrator.adapter.symphonica.test;

import javax.ejb.embeddable.EJBContainer;

import org.apache.log4j.Logger;

import hn.com.tigo.josm.common.adapter.task.Task;
import hn.com.tigo.josm.orchestrator.adapter.symphonica.SymphonicaAdapter;


public class AbstractTest {

	/** Attribute that determine container. */
	protected static EJBContainer container;

	protected static SymphonicaAdapter daAdapter;

	/** Attribute that determine task. */
	protected Task task;

	/** Attribute that determine a Constant of LOGGER. */
	protected static final transient Logger LOGGER = Logger.getLogger(AbstractTest.class);

	/**
	 * Instantiates a new abstract test.
	 *
	 * @param jndi the jndi
	 * @throws Exception the exception
	 */
	public AbstractTest(final String jndi) throws Exception {
		container = EjbContainerContext.INSTANCE.getContainer();
		daAdapter = (SymphonicaAdapter) container.getContext().lookup(
				"java:global/SymphonicaAdapter/SymphonicaAdapter!hn.com.tigo.josm.orchestrator.adapter.symphonica.SymphonicaAdapter");
		task = (Task) container.getContext().lookup(jndi);
	}

}
