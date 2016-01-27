/**
 * This code was generated from {@link com.dby.helloworld.HelloWorldWorkflow}.
 *
 * Any changes made directly to this file will be lost when 
 * the code is regenerated.
 */
package com.dby.helloworld;

import com.amazonaws.services.simpleworkflow.flow.StartWorkflowOptions;
import com.amazonaws.services.simpleworkflow.flow.WorkflowClientExternal;

public interface HelloWorldWorkflowClientExternal extends WorkflowClientExternal
{
    void helloWorld(java.lang.String name);
    void helloWorld(java.lang.String name, StartWorkflowOptions optionsOverride);
}