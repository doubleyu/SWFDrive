/**
 * This code was generated from {@link com.swf.booking.BookingWorkflow}.
 *
 * Any changes made directly to this file will be lost when 
 * the code is regenerated.
 */
package com.swf.booking;

import com.amazonaws.services.simpleworkflow.flow.core.AndPromise;
import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.amazonaws.services.simpleworkflow.flow.core.Task;
import com.amazonaws.services.simpleworkflow.flow.DataConverter;
import com.amazonaws.services.simpleworkflow.flow.StartWorkflowOptions;
import com.amazonaws.services.simpleworkflow.flow.WorkflowSelfClientBase;
import com.amazonaws.services.simpleworkflow.flow.generic.ContinueAsNewWorkflowExecutionParameters;
import com.amazonaws.services.simpleworkflow.flow.generic.GenericWorkflowClient;

public class BookingWorkflowSelfClientImpl extends WorkflowSelfClientBase implements BookingWorkflowSelfClient {

    public BookingWorkflowSelfClientImpl() {
        this(null, new com.amazonaws.services.simpleworkflow.flow.JsonDataConverter(), null);
    }

    public BookingWorkflowSelfClientImpl(GenericWorkflowClient genericClient) {
        this(genericClient, new com.amazonaws.services.simpleworkflow.flow.JsonDataConverter(), null);
    }

    public BookingWorkflowSelfClientImpl(GenericWorkflowClient genericClient, 
            DataConverter dataConverter, StartWorkflowOptions schedulingOptions) {
            
        super(genericClient, dataConverter, schedulingOptions);
    }

    @Override
    public final void makeBooking(int requestID, int customerID, boolean reserveAir, boolean reserveCar) { 
        makeBookingImpl(Promise.asPromise(requestID), Promise.asPromise(customerID), Promise.asPromise(reserveAir), Promise.asPromise(reserveCar), (StartWorkflowOptions)null);
    }

    @Override
    public final void makeBooking(int requestID, int customerID, boolean reserveAir, boolean reserveCar, Promise<?>... waitFor) { 
        makeBookingImpl(Promise.asPromise(requestID), Promise.asPromise(customerID), Promise.asPromise(reserveAir), Promise.asPromise(reserveCar), (StartWorkflowOptions)null, waitFor);
    }
    
    @Override
    public final void makeBooking(int requestID, int customerID, boolean reserveAir, boolean reserveCar, StartWorkflowOptions optionsOverride, Promise<?>... waitFor) {
        makeBookingImpl(Promise.asPromise(requestID), Promise.asPromise(customerID), Promise.asPromise(reserveAir), Promise.asPromise(reserveCar), optionsOverride, waitFor);
    }
    
    @Override
    public final void makeBooking(Promise<Integer> requestID, Promise<Integer> customerID, Promise<Boolean> reserveAir, Promise<Boolean> reserveCar) {
        makeBookingImpl(requestID, customerID, reserveAir, reserveCar, (StartWorkflowOptions)null);
    }

    @Override
    public final void makeBooking(Promise<Integer> requestID, Promise<Integer> customerID, Promise<Boolean> reserveAir, Promise<Boolean> reserveCar, Promise<?>... waitFor) {
        makeBookingImpl(requestID, customerID, reserveAir, reserveCar, (StartWorkflowOptions)null, waitFor);
    }

    @Override
    public final void makeBooking(Promise<Integer> requestID, Promise<Integer> customerID, Promise<Boolean> reserveAir, Promise<Boolean> reserveCar, StartWorkflowOptions optionsOverride, Promise<?>... waitFor) {
        makeBookingImpl(requestID, customerID, reserveAir, reserveCar, optionsOverride, waitFor);
    }
    
    protected void makeBookingImpl(final Promise<Integer> requestID, final Promise<Integer> customerID, final Promise<Boolean> reserveAir, final Promise<Boolean> reserveCar, final StartWorkflowOptions schedulingOptionsOverride, Promise<?>... waitFor) {
        new Task(new Promise[] { requestID, customerID, reserveAir, reserveCar, new AndPromise(waitFor) }) {
    		@Override
			protected void doExecute() throws Throwable {
                ContinueAsNewWorkflowExecutionParameters _parameters_ = new ContinueAsNewWorkflowExecutionParameters();
                Object[] _input_ = new Object[4];
                _input_[0] = requestID.get();
                _input_[1] = customerID.get();
                _input_[2] = reserveAir.get();
                _input_[3] = reserveCar.get();
                String _stringInput_ = dataConverter.toData(_input_);
				_parameters_.setInput(_stringInput_);
				_parameters_ = _parameters_.createContinueAsNewParametersFromOptions(schedulingOptions, schedulingOptionsOverride);
                
                if (genericClient == null) {
                    genericClient = decisionContextProvider.getDecisionContext().getWorkflowClient();
                }
                genericClient.continueAsNewOnCompletion(_parameters_);
			}
		};
    }
}