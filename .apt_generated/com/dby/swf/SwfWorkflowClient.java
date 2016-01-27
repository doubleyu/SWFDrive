/**
 * This code was generated from {@link com.dby.swf.SwfWorkflow}.
 *
 * Any changes made directly to this file will be lost when 
 * the code is regenerated.
 */
package com.dby.swf;

import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.amazonaws.services.simpleworkflow.flow.StartWorkflowOptions;
import com.amazonaws.services.simpleworkflow.flow.WorkflowClient;

public interface SwfWorkflowClient extends WorkflowClient
{
    Promise<Void> helloSwf(java.lang.String name);
    Promise<Void> helloSwf(java.lang.String name, Promise<?>... waitFor);
    Promise<Void> helloSwf(java.lang.String name, StartWorkflowOptions optionsOverride, Promise<?>... waitFor);
    Promise<Void> helloSwf(Promise<java.lang.String> name);
    Promise<Void> helloSwf(Promise<java.lang.String> name, Promise<?>... waitFor);
    Promise<Void> helloSwf(Promise<java.lang.String> name, StartWorkflowOptions optionsOverride, Promise<?>... waitFor);
}