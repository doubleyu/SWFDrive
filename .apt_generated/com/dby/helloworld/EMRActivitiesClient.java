/**
 * This code was generated from {@link com.dby.helloworld.EMRActivities}.
 *
 * Any changes made directly to this file will be lost when 
 * the code is regenerated.
 */
package com.dby.helloworld;

import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.amazonaws.services.simpleworkflow.flow.ActivitiesClient;
import com.amazonaws.services.simpleworkflow.flow.ActivitySchedulingOptions;

public interface EMRActivitiesClient extends ActivitiesClient
{
    Promise<Void> actEMR(java.lang.String name);
    Promise<Void> actEMR(java.lang.String name, Promise<?>... waitFor);
    Promise<Void> actEMR(java.lang.String name, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor);
    Promise<Void> actEMR(Promise<java.lang.String> name);
    Promise<Void> actEMR(Promise<java.lang.String> name, Promise<?>... waitFor);
    Promise<Void> actEMR(Promise<java.lang.String> name, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor);
}