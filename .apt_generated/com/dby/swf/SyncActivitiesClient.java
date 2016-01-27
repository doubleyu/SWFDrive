/**
 * This code was generated from {@link com.dby.swf.SyncActivities}.
 *
 * Any changes made directly to this file will be lost when 
 * the code is regenerated.
 */
package com.dby.swf;

import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.amazonaws.services.simpleworkflow.flow.ActivitiesClient;
import com.amazonaws.services.simpleworkflow.flow.ActivitySchedulingOptions;

public interface SyncActivitiesClient extends ActivitiesClient
{
    Promise<Void> actUnload(java.lang.String date);
    Promise<Void> actUnload(java.lang.String date, Promise<?>... waitFor);
    Promise<Void> actUnload(java.lang.String date, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor);
    Promise<Void> actUnload(Promise<java.lang.String> date);
    Promise<Void> actUnload(Promise<java.lang.String> date, Promise<?>... waitFor);
    Promise<Void> actUnload(Promise<java.lang.String> date, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor);
    Promise<Void> actEMR(java.lang.String inputPath, java.lang.String outputPath);
    Promise<Void> actEMR(java.lang.String inputPath, java.lang.String outputPath, Promise<?>... waitFor);
    Promise<Void> actEMR(java.lang.String inputPath, java.lang.String outputPath, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor);
    Promise<Void> actEMR(Promise<java.lang.String> inputPath, Promise<java.lang.String> outputPath);
    Promise<Void> actEMR(Promise<java.lang.String> inputPath, Promise<java.lang.String> outputPath, Promise<?>... waitFor);
    Promise<Void> actEMR(Promise<java.lang.String> inputPath, Promise<java.lang.String> outputPath, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor);
    Promise<Void> actLoad(java.lang.String inputPath);
    Promise<Void> actLoad(java.lang.String inputPath, Promise<?>... waitFor);
    Promise<Void> actLoad(java.lang.String inputPath, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor);
    Promise<Void> actLoad(Promise<java.lang.String> inputPath);
    Promise<Void> actLoad(Promise<java.lang.String> inputPath, Promise<?>... waitFor);
    Promise<Void> actLoad(Promise<java.lang.String> inputPath, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor);
}