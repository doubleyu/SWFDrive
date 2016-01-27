/**
 * This code was generated from {@link com.dby.swf.SwfWorkflow}.
 *
 * Any changes made directly to this file will be lost when 
 * the code is regenerated.
 */
package com.dby.swf;

import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.amazonaws.services.simpleworkflow.flow.StartWorkflowOptions;
import com.amazonaws.services.simpleworkflow.flow.WorkflowSelfClient;

public interface SwfWorkflowSelfClient extends WorkflowSelfClient
{
    void helloSwf(java.lang.String name);
    void helloSwf(java.lang.String name, Promise<?>... waitFor);
    void helloSwf(java.lang.String name, StartWorkflowOptions optionsOverride, Promise<?>... waitFor);
    void helloSwf(Promise<java.lang.String> name);
    void helloSwf(Promise<java.lang.String> name, Promise<?>... waitFor);
    void helloSwf(Promise<java.lang.String> name, StartWorkflowOptions optionsOverride, Promise<?>... waitFor);
}