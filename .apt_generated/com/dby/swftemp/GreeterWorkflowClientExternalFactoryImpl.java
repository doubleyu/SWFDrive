/**
 * This code was generated from {@link com.dby.swftemp.GreeterWorkflow}.
 *
 * Any changes made directly to this file will be lost when 
 * the code is regenerated.
 */
package com.dby.swftemp;

import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.flow.DataConverter;
import com.amazonaws.services.simpleworkflow.flow.StartWorkflowOptions;
import com.amazonaws.services.simpleworkflow.flow.WorkflowClientFactoryExternalBase;
import com.amazonaws.services.simpleworkflow.flow.generic.GenericWorkflowClientExternal;
import com.amazonaws.services.simpleworkflow.model.WorkflowExecution;
import com.amazonaws.services.simpleworkflow.model.WorkflowType;

public class GreeterWorkflowClientExternalFactoryImpl extends WorkflowClientFactoryExternalBase<GreeterWorkflowClientExternal>  implements GreeterWorkflowClientExternalFactory {

    public GreeterWorkflowClientExternalFactoryImpl(AmazonSimpleWorkflow service, String domain) {
		super(service, domain);
		setDataConverter(new com.amazonaws.services.simpleworkflow.flow.JsonDataConverter());
	}
	
	public GreeterWorkflowClientExternalFactoryImpl() {
        super(null);
		setDataConverter(new com.amazonaws.services.simpleworkflow.flow.JsonDataConverter());
    }
    
    public GreeterWorkflowClientExternalFactoryImpl(GenericWorkflowClientExternal genericClient) {
        super(genericClient);
		setDataConverter(new com.amazonaws.services.simpleworkflow.flow.JsonDataConverter());
    }
	
    @Override
    protected GreeterWorkflowClientExternal createClientInstance(WorkflowExecution workflowExecution,
            StartWorkflowOptions options, DataConverter dataConverter, GenericWorkflowClientExternal genericClient) {
        WorkflowType workflowType = new WorkflowType();
        workflowType = new WorkflowType();
        workflowType.setName("GreeterWorkflow.greet");
        workflowType.setVersion("2.0");
        return new GreeterWorkflowClientExternalImpl(workflowExecution, workflowType, options, dataConverter, genericClient);
    }
    
}