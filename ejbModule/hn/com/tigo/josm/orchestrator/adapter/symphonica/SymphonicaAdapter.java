package hn.com.tigo.josm.orchestrator.adapter.symphonica;

import static javax.ejb.ConcurrencyManagementType.BEAN;
import hn.com.tigo.josm.common.adapter.AbstractAdapter;
import hn.com.tigo.josm.common.adapter.config.AdapterConfig;
import hn.com.tigo.josm.common.exceptions.AdapterException;
import hn.com.tigo.josm.orchestrator.adapter.symphonica.utils.SymphonicaConstantsAdapter;
import hn.com.tigo.josm.orchestrator.driver.symphonica.SymphonicaDriver;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.log4j.Logger;

/**
 * The Class SymphonicaAdapter class singleton, used to manage the
 * configuration for attributes Throughput and TimeOut for tasks in
 * SymphonicaAdapter .This attributes come from ConfigurationJosm service from
 * SymphonicaAdapter.xml. Also create a Driver's Pool to manage the maintenance
 * threads execution and monitoring.
 * 
 * @author Leonardo Vijil
 * @version 1.0.0
 * @since 24/02/2022
 */
@Singleton
@Startup
@DependsOn("MonitoringManager")
@ConcurrencyManagement(BEAN)
public class SymphonicaAdapter extends AbstractAdapter<SymphonicaDriver> {

	/** The Constant LOGGER.*/
	private static final transient Logger LOGGER = Logger.getLogger(SymphonicaAdapter.class);


	/**
	 * Instantiates a new NexusAdapter and create a SymphonicaAdapter Monitor
	 * instance. Also Load the configuration and create the drivers.
	 */
	public SymphonicaAdapter() {
		LOGGER.info("Init SymphonicaAdapter Singleton");
	}

	
	/* (non-Javadoc)
	 * @see hn.com.tigo.josm.common.adapter.AbstractAdapter#createDriver()
	 */
	@Override
	public SymphonicaDriver createDriver() throws AdapterException {
		final AdapterConfig config =  this.getConfigurationType();
		final String endpointSymLogin = config.getDriverConfig().getConnections().get("SymLogin").getParameters().get(SymphonicaConstantsAdapter.ENDPOINT);
		final String endpointSymProv = config.getDriverConfig().getConnections().get("SymProv").getParameters().get(SymphonicaConstantsAdapter.ENDPOINT);
		final String endpointSymServiceProv = config.getDriverConfig().getConnections().get("SymServiceProv").getParameters().get(SymphonicaConstantsAdapter.ENDPOINT);
		final String userName = config.getDriverConfig().getConnections().get("SymLogin").getParameters().get(SymphonicaConstantsAdapter.USERNAME);
		final String password = config.getDriverConfig().getConnections().get("SymLogin").getParameters().get(SymphonicaConstantsAdapter.PASSWORD);
		
		final String endpointEventBrokLogin = config.getDriverConfig().getConnections().get("EventBrokLogin").getParameters().get(SymphonicaConstantsAdapter.ENDPOINT);
		final String endpointEventBrokProv = config.getDriverConfig().getConnections().get("EventBrokProv").getParameters().get(SymphonicaConstantsAdapter.ENDPOINT);
		final String client_id = config.getDriverConfig().getConnections().get("EventBrokLogin").getParameters().get(SymphonicaConstantsAdapter.CLIENT_ID);
		final String client_secret = config.getDriverConfig().getConnections().get("EventBrokLogin").getParameters().get(SymphonicaConstantsAdapter.CLIENT_SECRET);
		
		return new SymphonicaDriver(endpointSymLogin,endpointSymProv,endpointSymServiceProv,userName,password, 
				endpointEventBrokLogin, endpointEventBrokProv, client_id, client_secret);
	}

}
