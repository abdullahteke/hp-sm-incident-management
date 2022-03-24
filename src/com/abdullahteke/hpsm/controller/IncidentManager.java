package com.abdullahteke.hpsm.controller;

import java.rmi.RemoteException;

import org.apache.axiom.om.NodeUnavailableException;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.impl.httpclient3.HttpTransportPropertiesImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.abdullahteke.hpsm.domain.Incident;
import com.hp.schemas.sm._7.IncidentManagementForCustomerStub;
import com.hp.schemas.sm._7.IncidentManagementForCustomerStub.CloseIncidentManagementForCustomerRequest;
import com.hp.schemas.sm._7.IncidentManagementForCustomerStub.CloseIncidentManagementForCustomerResponse;
import com.hp.schemas.sm._7.IncidentManagementForCustomerStub.CreateIncidentManagementForCustomerRequest;
import com.hp.schemas.sm._7.IncidentManagementForCustomerStub.CreateIncidentManagementForCustomerResponse;
import com.hp.schemas.sm._7.IncidentManagementForCustomerStub.Description_type0;
import com.hp.schemas.sm._7.IncidentManagementForCustomerStub.RetrieveIncidentManagementForCustomerKeysListRequest;
import com.hp.schemas.sm._7.IncidentManagementForCustomerStub.RetrieveIncidentManagementForCustomerKeysListResponse;
import com.hp.schemas.sm._7.IncidentManagementForCustomerStub.RetrieveIncidentManagementForCustomerRequest;
import com.hp.schemas.sm._7.IncidentManagementForCustomerStub.RetrieveIncidentManagementForCustomerResponse;
import com.hp.schemas.sm._7.IncidentManagementForCustomerStub.Solution_type0;
import com.hp.schemas.sm._7.IncidentManagementForCustomerStub.StringType;
import com.hp.schemas.sm._7.IncidentManagementForCustomerStub.IncidentManagementForCustomerInstanceType;
import com.hp.schemas.sm._7.IncidentManagementForCustomerStub.IncidentManagementForCustomerKeysType;
import com.hp.schemas.sm._7.IncidentManagementForCustomerStub.IncidentManagementForCustomerModelType;


public class IncidentManager {

	private static IncidentManager instance;
	
	
	private String wsAddress;
	private String userName;
	private String userPasswd;
	//private IncidentManagementForCustomerStub stub;
	
	private static final Logger logger = (Logger) LogManager.getLogger(IncidentManager.class);
	
	public static IncidentManager getInstance() {
		
		if (instance==null){
			instance=new IncidentManager();
		}
		return instance;
	}
	

	public IncidentManager() {
		wsAddress=ConfigManager.getInstance().getSmWsURL();
		userName=ConfigManager.getInstance().getSmWsUser();
		userPasswd=ConfigManager.getInstance().getSmWsPasswd();
		//stub=getStub();
	}
	
	
	private IncidentManagementForCustomerStub getStub() {
		
		IncidentManagementForCustomerStub stub=null;
		
		try {
			stub= new IncidentManagementForCustomerStub(wsAddress);

			HttpTransportPropertiesImpl.Authenticator auth= new HttpTransportPropertiesImpl.Authenticator();
			auth.setUsername(userName);
			auth.setPassword(userPasswd);
			auth.setPreemptiveAuthentication(true);
			
			final Options clientOptions=stub._getServiceClient().getOptions();
			clientOptions.setProperty(HTTPConstants.AUTHENTICATE,auth);
			clientOptions.setProperty(Constants.Configuration.MESSAGE_TYPE,HTTPConstants.MEDIA_TYPE_APPLICATION_ECHO_XML);
			clientOptions.setProperty(HTTPConstants.CHUNKED, Constants.VALUE_FALSE);

			stub._getServiceClient().setOptions(clientOptions);

		} catch (AxisFault e) {

			e.printStackTrace();
		}
		
		return stub;
	}

	public void createIncident(Incident inc) {
		
		if (retrieveTicket(inc.getNodeUUID(),inc.getIncidentType())== null) {
		
			IncidentManagementForCustomerStub stub=getStub();
			
			StringType st;
			
			IncidentManagementForCustomerModelType model= new IncidentManagementForCustomerModelType();
			IncidentManagementForCustomerKeysType keys= new IncidentManagementForCustomerKeysType();
			
			model.setKeys(keys);   
			
			IncidentManagementForCustomerInstanceType instance= new IncidentManagementForCustomerInstanceType();

			st=new StringType();
			st.setString(inc.getNodeUUID()+"_"+inc.getIncidentType());
			instance.setNNMID(st);
			
			st= new StringType();
			st.setString("incident");
			instance.setCategory(st);
	
			st= new StringType();
			st.setString("NNMUSER");
			instance.setOpenedBy(st);
			
			st= new StringType();
			st.setString("1");
			instance.setUrgency(st);
			
			st= new StringType();
			st.setString("Network");
			instance.setAssignmentGroup(st);
	
			//Açıklama kısmı
			Description_type0 descType= new Description_type0();
			st= new StringType();
			st.setString(inc.getIncidentMsg()+"_"+inc.getNodeIP()); // Buraya eklenebilir.
			descType.addDescription(st);
			descType.setType("ArrayType");
			instance.setDescription(descType);
			
			st= new StringType();
			st.setString("NNMUSER");
			instance.setContact(st);
			
			//Başlık kısmı
			st= new StringType();
			st.setString(inc.getNodeName()+" cihazı üzerinde "+inc.getIncidentType()+" olayı oluşmuştur.");
			instance.setTitle(st);
			
			st= new StringType();
			st.setString("network");
			instance.setArea(st);
			
			st= new StringType();
			st.setString("1");
			instance.setSiteCategory(st);
			
			st= new StringType();
			st.setString("arıza");
			instance.setSubarea(st);
			
			st= new StringType();
			st.setString("1");
			instance.setUserPriority(st);
			instance.setImpact(st);
			
			st= new StringType();
			st.setString("Network");
			instance.setService(st);
	
			model.setInstance(instance);
			model.setKeys(keys);
			
			CreateIncidentManagementForCustomerRequest request= new CreateIncidentManagementForCustomerRequest();
			request.setModel(model);
			request.setIgnoreEmptyElements(true);
	  
		   
			CreateIncidentManagementForCustomerResponse response=null;
		    
			try {
				 response=stub.createIncidentManagementForCustomer(request);
				 
				if (response.getMessage().equalsIgnoreCase("Success")){
					logger.info("Ticket Opened: TicketID:"+response.getModel().getKeys().getIncidentID());
					System.out.println(response.getModel().getInstance().getRecordid());
					
				}else{
					logger.error("Ticket Can't Opened: TicketID:"+response.getModel().getKeys().getIncidentID()+ "Node IP:---; Response:"+response.getMessage());
				
				}
				
			} catch (RemoteException e) {
				
					logger.error("Hata:"+e.getMessage());
					e.printStackTrace();
			}
		
		}else {
			logger.error(inc.getNodeName()+" Cihazı için açılmış bir "+inc.getIncidentType()+" kaydı bulunmaktadır.");
		}
	}


	public String retrieveTicket(String nodeUUID, String incidentType) {

		String retVal=null;
		IncidentManagementForCustomerStub stub= getStub();
		RetrieveIncidentManagementForCustomerRequest request= new RetrieveIncidentManagementForCustomerRequest();
		RetrieveIncidentManagementForCustomerResponse response=null;
		IncidentManagementForCustomerModelType model= new IncidentManagementForCustomerModelType();
		IncidentManagementForCustomerKeysType keys= new IncidentManagementForCustomerKeysType();
		IncidentManagementForCustomerInstanceType instance= new IncidentManagementForCustomerInstanceType();

		model.setKeys(keys);  
		
		StringType st;
		
		st=new StringType();
		st.setString(nodeUUID+"_"+incidentType);
		instance.setNNMID(st);
		
		st= new StringType();
		st.setString("Open");
		instance.setStatus(st);
		
		model.setInstance(instance);
		
		request.setModel(model);
		try {
			 response=stub.retrieveIncidentManagementForCustomer(request);
			 
			if (response.getMessage().equalsIgnoreCase("Success")){
				retVal=response.getModel().getKeys().getIncidentID().getString();
				logger.info("Ticket Retrieved: TicketID:"+response.getModel().getKeys().getIncidentID());
				
			}else{
				logger.info("There is no ticket with TicketID:"+response.getModel().getKeys().getIncidentID()+ "Node IP:---; Response:"+response.getMessage());
				logger.error(response.getMessage());
			}
			
		} catch (RemoteException e) {
			
				logger.error("Hata:"+e.getMessage());
				e.printStackTrace();
		}
		
		return retVal;
	}


	public void closeIncident(String nodeUUID, String incidentType) {
		String incID= retrieveTicket(nodeUUID, incidentType);
		//String incID="IM10332";
		System.out.println(incID);
		
		if (incID!=null) {
			
			IncidentManagementForCustomerStub stub= getStub();
			CloseIncidentManagementForCustomerRequest request= new CloseIncidentManagementForCustomerRequest();
			IncidentManagementForCustomerModelType model= new IncidentManagementForCustomerModelType();
			IncidentManagementForCustomerKeysType keys= new IncidentManagementForCustomerKeysType();
			IncidentManagementForCustomerInstanceType instance= new IncidentManagementForCustomerInstanceType();
			
			StringType st=new StringType();
			st.setString(incID);		
			keys.setIncidentID(st);
			
			model.setKeys(keys);
			
			st= new StringType();
			st.setString("Otomatik Kapatıldı");
			instance.setClosureCode(st);
			
			Solution_type0 solType= new Solution_type0();
			st= new StringType();
			st.setString("Çözüm Açıklaması Yazılacak"); // Buraya eklenebilir.
			solType.setType("ArrayType");
			solType.addSolution(st);
			instance.setSolution(solType);

			st= new StringType();
			st.setString("NNMUSER");
			instance.setAssignee(st);
			
			st= new StringType();
			st.setString("network");
			instance.setArea(st);
			
			st= new StringType();
			st.setString("arıza");
			instance.setSubarea(st);
			
			
			model.setInstance(instance);
			request.setModel(model);
			CloseIncidentManagementForCustomerResponse response=null;
			try {
				 response=stub.closeIncidentManagementForCustomer(request);
				 
				if (response.getMessage().equalsIgnoreCase("Success")){
				
					logger.info("Ticket Closed: TicketID:"+incID);
					
				}else{
					logger.error("Ticket Can't Closed: TicketID:"+incID+ "Node IP:---; Response:"+response.getMessage());
				
				}
				System.out.println(response.getStatus().getValue());
				System.out.println(response.getMessage());
				//System.out.println(response.getQuery());
				
			} catch (RemoteException e) {
				
					logger.error("Hata:"+e.getMessage());
					e.printStackTrace();
			}
			
			
		}
	}
	

	
}
