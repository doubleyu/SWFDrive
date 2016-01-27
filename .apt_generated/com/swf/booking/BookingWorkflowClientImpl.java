/**
 * This code was generated from {@link com.swf.booking.BookingWorkflow}.
 *
 * Any changes made directly to this file will be lost when 
 * the code is regenerated.
 */
package com.swf.booking;

import com.amazonaws.services.simpleworkflow.flow.DataConverter;
import com.amazonaws.services.simpleworkflow.flow.StartWorkflowOptions;
import com.amazonaws.services.simpleworkflow.flow.WorkflowClientBase;
import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.amazonaws.services.simpleworkflow.flow.generic.GenericWorkflowClient;
import com.amazonaws.services.simpleworkflow.model.WorkflowExecution;
import com.amazonaws.services.simpleworkflow.model.WorkflowType;

class BookingWorkflowClientImpl extends WorkflowClientBase implements BookingWorkflowClient {

    public BookingWorkflowClientImpl(WorkflowExecution workflowExecution, WorkflowType workflowType,  
            StartWorkflowOptions options, DataConverter dataConverter, GenericWorkflowClient genericClient) {
        super(workflowExecution, workflowType, options, dataConverter, genericClient);
    }
    
    @Override
    public final Promise<Void> makeBooking(int requestID, int customerID, boolean reserveAir, boolean reserveCar) { 
        return makeBooking(Promise.asPromise(requestID), Promise.asPromise(customerID), Promise.asPromise(reserveAir), Promise.asPromise(reserveCar), (StartWorkflowOptions)null);
    }
    
    @Override
    public final Promise<Void> makeBooking(int requestID, int customerID, boolean reserveAir, boolean reserveCar, Promise<?>... waitFor) {
        return makeBooking(Promise.asPromise(requestID), Promise.asPromise(customerID), Promise.asPromise(reserveAir), Promise.asPromise(reserveCar), (StartWorkflowOptions)null, waitFor);
    }
    
    
    @Override
    
    public final Promise<Void> makeBooking(int requestID, int customerID, boolean reserveAir, boolean reserveCar, StartWorkflowOptions optionsOverride, Promise<?>... waitFor) {
        return makeBooking(Promise.asPromise(requestID), Promise.asPromise(customerID), Promise.asPromise(reserveAir), Promise.asPromise(reserveCar), optionsOverride, waitFor);
    }

    @Override
    public final Promise<Void> makeBooking(Promise<Integer> requestID, Promise<Integer> customerID, Promise<Boolean> reserveAir, Promise<Boolean> reserveCar) {
        return makeBooking(requestID, customerID, reserveAir, reserveCar, (StartWorkflowOptions)null);
    }

    @Override
    public final Promise<Void> makeBooking(Promise<Integer> requestID, Promise<Integer> customerID, Promise<Boolean> reserveAir, Promise<Boolean> reserveCar, Promise<?>... waitFor) {
        return makeBooking(requestID, customerID, reserveAir, reserveCar, (StartWorkflowOptions)null, waitFor);
    }

    @Override
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public final Promise<Void> makeBooking(Promise<Integer> requestID, Promise<Integer> customerID, Promise<Boolean> reserveAir, Promise<Boolean> reserveCar, StartWorkflowOptions optionsOverride, Promise<?>... waitFor) {
        Promise[] _input_ = new Promise[4];
        _input_[0] = requestID;
        _input_[1] = customerID;
        _input_[2] = reserveAir;
        _input_[3] = reserveCar;
        return (Promise) startWorkflowExecution(_input_, optionsOverride, Void.class, waitFor);
    }
    	

}