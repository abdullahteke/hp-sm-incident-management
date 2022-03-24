/**
 * IncidentManagementForCustomerCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */
package com.hp.schemas.sm._7;


/**
 *  IncidentManagementForCustomerCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class IncidentManagementForCustomerCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public IncidentManagementForCustomerCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public IncidentManagementForCustomerCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for createIncidentManagementForCustomer method
     * override this method for handling normal response from createIncidentManagementForCustomer operation
     */
    public void receiveResultcreateIncidentManagementForCustomer(
        com.hp.schemas.sm._7.IncidentManagementForCustomerStub.CreateIncidentManagementForCustomerResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from createIncidentManagementForCustomer operation
     */
    public void receiveErrorcreateIncidentManagementForCustomer(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for closeIncidentManagementForCustomer method
     * override this method for handling normal response from closeIncidentManagementForCustomer operation
     */
    public void receiveResultcloseIncidentManagementForCustomer(
        com.hp.schemas.sm._7.IncidentManagementForCustomerStub.CloseIncidentManagementForCustomerResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from closeIncidentManagementForCustomer operation
     */
    public void receiveErrorcloseIncidentManagementForCustomer(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for resolveIncidentManagementForCustomer method
     * override this method for handling normal response from resolveIncidentManagementForCustomer operation
     */
    public void receiveResultresolveIncidentManagementForCustomer(
        com.hp.schemas.sm._7.IncidentManagementForCustomerStub.ResolveIncidentManagementForCustomerResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from resolveIncidentManagementForCustomer operation
     */
    public void receiveErrorresolveIncidentManagementForCustomer(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for retrieveIncidentManagementForCustomerKeysList method
     * override this method for handling normal response from retrieveIncidentManagementForCustomerKeysList operation
     */
    public void receiveResultretrieveIncidentManagementForCustomerKeysList(
        com.hp.schemas.sm._7.IncidentManagementForCustomerStub.RetrieveIncidentManagementForCustomerKeysListResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from retrieveIncidentManagementForCustomerKeysList operation
     */
    public void receiveErrorretrieveIncidentManagementForCustomerKeysList(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for updateIncidentManagementForCustomer method
     * override this method for handling normal response from updateIncidentManagementForCustomer operation
     */
    public void receiveResultupdateIncidentManagementForCustomer(
        com.hp.schemas.sm._7.IncidentManagementForCustomerStub.UpdateIncidentManagementForCustomerResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from updateIncidentManagementForCustomer operation
     */
    public void receiveErrorupdateIncidentManagementForCustomer(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for retrieveIncidentManagementForCustomer method
     * override this method for handling normal response from retrieveIncidentManagementForCustomer operation
     */
    public void receiveResultretrieveIncidentManagementForCustomer(
        com.hp.schemas.sm._7.IncidentManagementForCustomerStub.RetrieveIncidentManagementForCustomerResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from retrieveIncidentManagementForCustomer operation
     */
    public void receiveErrorretrieveIncidentManagementForCustomer(
        java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for retrieveIncidentManagementForCustomerList method
     * override this method for handling normal response from retrieveIncidentManagementForCustomerList operation
     */
    public void receiveResultretrieveIncidentManagementForCustomerList(
        com.hp.schemas.sm._7.IncidentManagementForCustomerStub.RetrieveIncidentManagementForCustomerListResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from retrieveIncidentManagementForCustomerList operation
     */
    public void receiveErrorretrieveIncidentManagementForCustomerList(
        java.lang.Exception e) {
    }
}
