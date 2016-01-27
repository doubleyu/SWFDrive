/**
 * This code was generated from {@link com.dby.swf.SwfWorkflow}.
 *
 * Any changes made directly to this file will be lost when 
 * the code is regenerated.
 */
package com.dby.swf;

import com.amazonaws.services.simpleworkflow.flow.DataConverter;
import com.amazonaws.services.simpleworkflow.flow.StartWorkflowOptions;
import com.amazonaws.services.simpleworkflow.flow.WorkflowClientExternalBase;
import com.amazonaws.services.simpleworkflow.flow.generic.GenericWorkflowClientExternal;
import com.amazonaws.services.simpleworkflow.model.WorkflowExecution;
import com.amazonaws.services.simpleworkflow.model.WorkflowType;

class SwfWorkflowClientExternalImpl extends WorkflowClientExternalBase implements SwfWorkflowClientExternal {

    public SwfWorkflowClientExternalImpl(WorkflowExecution workflowExecution, WorkflowType workflowType, 
            StartWorkflowOptions options, DataConverter dataConverter, GenericWorkflowClientExternal genericClient) {
        super(workflowExecution, workflowType, options, dataConverter, genericClient);
    }

    @Override
    public void helloSwf(java.lang.String name) { 
        helloSwf(name, null);
    }

    @Override
    public void helloSwf(java.lang.String name, StartWorkflowOptions startOptionsOverride) {
    
        Object[] _arguments_ = new Object[1]; 
        _arguments_[0] = name;
        dynamicWorkflowClient.startWorkflowExecution(_arguments_, startOptionsOverride);
    }


}