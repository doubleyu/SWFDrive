/**
 * This code was generated from {@link com.swf.booking.BookingWorkflow}.
 *
 * Any changes made directly to this file will be lost when 
 * the code is regenerated.
 */
package com.swf.booking;

import com.amazonaws.services.simpleworkflow.flow.DataConverter;
import com.amazonaws.services.simpleworkflow.flow.StartWorkflowOptions;
import com.amazonaws.services.simpleworkflow.flow.WorkflowClientExternalBase;
import com.amazonaws.services.simpleworkflow.flow.generic.GenericWorkflowClientExternal;
import com.amazonaws.services.simpleworkflow.model.WorkflowExecution;
import com.amazonaws.services.simpleworkflow.model.WorkflowType;

class BookingWorkflowClientExternalImpl extends WorkflowClientExternalBase implements BookingWorkflowClientExternal {

    public BookingWorkflowClientExternalImpl(WorkflowExecution workflowExecution, WorkflowType workflowType, 
            StartWorkflowOptions options, DataConverter dataConverter, GenericWorkflowClientExternal genericClient) {
        super(workflowExecution, workflowType, options, dataConverter, genericClient);
    }

    @Override
    public void makeBooking(int requestID, int customerID, boolean reserveAir, boolean reserveCar) { 
        makeBooking(requestID, customerID, reserveAir, reserveCar, null);
    }

    @Override
    public void makeBooking(int requestID, int customerID, boolean reserveAir, boolean reserveCar, StartWorkflowOptions startOptionsOverride) {
    
        Object[] _arguments_ = new Object[4]; 
        _arguments_[0] = requestID;
        _arguments_[1] = customerID;
        _arguments_[2] = reserveAir;
        _arguments_[3] = reserveCar;
        dynamicWorkflowClient.startWorkflowExecution(_arguments_, startOptionsOverride);
    }


}