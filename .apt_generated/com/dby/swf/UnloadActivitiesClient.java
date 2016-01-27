/**
 * This code was generated from {@link com.dby.swf.UnloadActivities}.
 *
 * Any changes made directly to this file will be lost when 
 * the code is regenerated.
 */
package com.dby.swf;

import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.amazonaws.services.simpleworkflow.flow.ActivitiesClient;
import com.amazonaws.services.simpleworkflow.flow.ActivitySchedulingOptions;

public interface UnloadActivitiesClient extends ActivitiesClient
{
    Promise<Void> actUnload(java.lang.String date);
    Promise<Void> actUnload(java.lang.String date, Promise<?>... waitFor);
    Promise<Void> actUnload(java.lang.String date, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor);
    Promise<Void> actUnload(Promise<java.lang.String> date);
    Promise<Void> actUnload(Promise<java.lang.String> date, Promise<?>... waitFor);
    Promise<Void> actUnload(Promise<java.lang.String> date, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor);
}