/**
 * This code was generated from {@link com.dby.swf.SwfWorkflow}.
 *
 * Any changes made directly to this file will be lost when 
 * the code is regenerated.
 */
package com.dby.swf;

import com.amazonaws.services.simpleworkflow.flow.StartWorkflowOptions;
import com.amazonaws.services.simpleworkflow.flow.WorkflowClientExternal;

public interface SwfWorkflowClientExternal extends WorkflowClientExternal
{
    void helloSwf(java.lang.String name);
    void helloSwf(java.lang.String name, StartWorkflowOptions optionsOverride);
}