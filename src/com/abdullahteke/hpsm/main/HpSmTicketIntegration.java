package com.abdullahteke.hpsm.main;

import com.abdullahteke.hpsm.controller.IncidentManager;
import com.abdullahteke.hpsm.domain.Incident;

public class HpSmTicketIntegration {

	public HpSmTicketIntegration() {
	}

	public static void main(String[] args) {
		
		
		String lifeCycleState= args[0];
		if (lifeCycleState.equalsIgnoreCase("com.hp.nms.incident.lifecycle.Registered")) {
				
			Incident inc= new Incident();
			
			inc.setIncidentType(args[1]);
			inc.setNodeUUID(args[2]);
			inc.setNodeName(args[3]);
			inc.setNodeIP(args[4]);
			inc.setIncidentMsg(args[5]);
			
			IncidentManager.getInstance().createIncident(inc);
					
		}else if (lifeCycleState.equalsIgnoreCase("com.hp.nms.incident.lifecycle.Closed")) {
			IncidentManager.getInstance().closeIncident(args[2],args[1]);
		}
	}

}