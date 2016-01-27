/**
 * This code was generated from {@link com.dby.swf.SwfWorkflow}.
 *
 * Any changes made directly to this file will be lost when 
 * the code is regenerated.
 */
package com.dby.swf;

import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.flow.DataConverter;
import com.amazonaws.services.simpleworkflow.flow.StartWorkflowOptions;
import com.amazonaws.services.simpleworkflow.flow.WorkflowClientFactoryExternalBase;
import com.amazonaws.services.simpleworkflow.flow.generic.GenericWorkflowClientExternal;
import com.amazonaws.services.simpleworkflow.model.WorkflowExecution;
import com.amazonaws.services.simpleworkflow.model.WorkflowType;

public class SwfWorkflowClientExternalFactoryImpl extends WorkflowClientFactoryExternalBase<SwfWorkflowClientExternal>  implements SwfWorkflowClientExternalFactory {

    public SwfWorkflowClientExternalFactoryImpl(AmazonSimpleWorkflow service, String domain) {
		super(service, domain);
		setDataConverter(new com.amazonaws.services.simpleworkflow.flow.JsonDataConverter());
	}
	
	public SwfWorkflowClientExternalFactoryImpl() {
        super(null);
		setDataConverter(new com.amazonaws.services.simpleworkflow.flow.JsonDataConverter());
    }
    
    public SwfWorkflowClientExternalFactoryImpl(GenericWorkflowClientExternal genericClient) {
        super(genericClient);
		setDataConverter(new com.amazonaws.services.simpleworkflow.flow.JsonDataConverter());
    }
	
    @Override
    protected SwfWorkflowClientExternal createClientInstance(WorkflowExecution workflowExecution,
            StartWorkflowOptions options, DataConverter dataConverter, GenericWorkflowClientExternal genericClient) {
        WorkflowType workflowType = new WorkflowType();
        workflowType = new WorkflowType();
        workflowType.setName("SwfWorkflow.helloSwf");
        workflowType.setVersion("5.0");
        return new SwfWorkflowClientExternalImpl(workflowExecution, workflowType, options, dataConverter, genericClient);
    }
    
}