/**
 * This code was generated from {@link com.dby.swf.SwfWorkflow}.
 *
 * Any changes made directly to this file will be lost when 
 * the code is regenerated.
 */
package com.dby.swf;

import com.amazonaws.services.simpleworkflow.flow.DataConverter;
import com.amazonaws.services.simpleworkflow.flow.StartWorkflowOptions;
import com.amazonaws.services.simpleworkflow.flow.WorkflowClientBase;
import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.amazonaws.services.simpleworkflow.flow.generic.GenericWorkflowClient;
import com.amazonaws.services.simpleworkflow.model.WorkflowExecution;
import com.amazonaws.services.simpleworkflow.model.WorkflowType;

class SwfWorkflowClientImpl extends WorkflowClientBase implements SwfWorkflowClient {

    public SwfWorkflowClientImpl(WorkflowExecution workflowExecution, WorkflowType workflowType,  
            StartWorkflowOptions options, DataConverter dataConverter, GenericWorkflowClient genericClient) {
        super(workflowExecution, workflowType, options, dataConverter, genericClient);
    }
    
    @Override
    public final Promise<Void> helloSwf(java.lang.String name) { 
        return helloSwf(Promise.asPromise(name), (StartWorkflowOptions)null);
    }
    
    @Override
    public final Promise<Void> helloSwf(java.lang.String name, Promise<?>... waitFor) {
        return helloSwf(Promise.asPromise(name), (StartWorkflowOptions)null, waitFor);
    }
    
    
    @Override
    
    public final Promise<Void> helloSwf(java.lang.String name, StartWorkflowOptions optionsOverride, Promise<?>... waitFor) {
        return helloSwf(Promise.asPromise(name), optionsOverride, waitFor);
    }

    @Override
    public final Promise<Void> helloSwf(Promise<java.lang.String> name) {
        return helloSwf(name, (StartWorkflowOptions)null);
    }

    @Override
    public final Promise<Void> helloSwf(Promise<java.lang.String> name, Promise<?>... waitFor) {
        return helloSwf(name, (StartWorkflowOptions)null, waitFor);
    }

    @Override
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public final Promise<Void> helloSwf(Promise<java.lang.String> name, StartWorkflowOptions optionsOverride, Promise<?>... waitFor) {
        Promise[] _input_ = new Promise[1];
        _input_[0] = name;
        return (Promise) startWorkflowExecution(_input_, optionsOverride, Void.class, waitFor);
    }
    	

}