package com.abdullahteke.hpsm.domain;

import java.io.Serializable;

public class Incident implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1609903897565648470L;
	
	private String incidentKey;
	private String nodeUUID;
	private String nodeName;
	private String incidentType;
	private String nodeIP;
	private String incidentMsg;

	
	public Incident() {
		this.incidentKey="";
		this.nodeUUID="";
		this.nodeName="";
		this.incidentType="";
		this.incidentMsg="";
		this.nodeIP="";
	}


	public String getIncidentKey() {
		return incidentKey;
	}


	public void setIncidentKey(String incidentKey) {
		this.incidentKey = incidentKey;
	}


	public String getNodeUUID() {
		return nodeUUID;
	}


	public void setNodeUUID(String nodeUUID) {
		this.nodeUUID = nodeUUID;
	}


	public String getNodeName() {
		return nodeName;
	}


	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}


	public String getIncidentType() {
		return incidentType;
	}


	public void setIncidentType(String incidentType) {
		this.incidentType = incidentType;
	}


	public String getNodeIP() {
		return nodeIP;
	}


	public void setNodeIP(String nodeIP) {
		this.nodeIP = nodeIP;
	}


	public String getIncidentMsg() {
		return incidentMsg;
	}


	public void setIncidentMsg(String incidentMsg) {
		this.incidentMsg = incidentMsg;
	}


}