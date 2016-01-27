/**
 * This code was generated from {@link com.dby.swf.LoadActivities}.
 *
 * Any changes made directly to this file will be lost when 
 * the code is regenerated.
 */
package com.dby.swf;

import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.amazonaws.services.simpleworkflow.flow.ActivitiesClient;
import com.amazonaws.services.simpleworkflow.flow.ActivitySchedulingOptions;

public interface LoadActivitiesClient extends ActivitiesClient
{
    Promise<Void> actLoad(java.lang.String inputPath);
    Promise<Void> actLoad(java.lang.String inputPath, Promise<?>... waitFor);
    Promise<Void> actLoad(java.lang.String inputPath, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor);
    Promise<Void> actLoad(Promise<java.lang.String> inputPath);
    Promise<Void> actLoad(Promise<java.lang.String> inputPath, Promise<?>... waitFor);
    Promise<Void> actLoad(Promise<java.lang.String> inputPath, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor);
}