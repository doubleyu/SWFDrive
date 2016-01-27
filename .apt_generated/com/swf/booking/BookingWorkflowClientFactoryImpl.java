/**
 * This code was generated from {@link com.swf.booking.BookingWorkflow}.
 *
 * Any changes made directly to this file will be lost when 
 * the code is regenerated.
 */
package com.swf.booking;

import com.amazonaws.services.simpleworkflow.flow.DataConverter;
import com.amazonaws.services.simpleworkflow.flow.StartWorkflowOptions;
import com.amazonaws.services.simpleworkflow.flow.WorkflowClientFactoryBase;
import com.amazonaws.services.simpleworkflow.flow.generic.GenericWorkflowClient;
import com.amazonaws.services.simpleworkflow.model.WorkflowExecution;
import com.amazonaws.services.simpleworkflow.model.WorkflowType;

public class BookingWorkflowClientFactoryImpl extends WorkflowClientFactoryBase<BookingWorkflowClient> implements BookingWorkflowClientFactory {
    
    public BookingWorkflowClientFactoryImpl() {
        this(null, null, null);
    }

    public BookingWorkflowClientFactoryImpl(StartWorkflowOptions startWorkflowOptions) {
        this(startWorkflowOptions, null, null);
    }

    public BookingWorkflowClientFactoryImpl(StartWorkflowOptions startWorkflowOptions, DataConverter dataConverter) {
        this(startWorkflowOptions, dataConverter, null);
    }

    public BookingWorkflowClientFactoryImpl(StartWorkflowOptions startWorkflowOptions, DataConverter dataConverter,
            GenericWorkflowClient genericClient) {
        super(startWorkflowOptions, new com.amazonaws.services.simpleworkflow.flow.JsonDataConverter(), genericClient);
    }
    
    @Override
    protected BookingWorkflowClient createClientInstance(WorkflowExecution execution,
            StartWorkflowOptions options, DataConverter dataConverter, GenericWorkflowClient genericClient) {
        WorkflowType workflowType = new WorkflowType();
        workflowType.setName("MakeBooking");
        workflowType.setVersion("1.0");
        return new BookingWorkflowClientImpl(execution, workflowType, options, dataConverter, genericClient);
    }
   
}