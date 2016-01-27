/**
 * This code was generated from {@link com.dby.swf.EMRActivities}.
 *
 * Any changes made directly to this file will be lost when 
 * the code is regenerated.
 */
package com.dby.swf;

import com.amazonaws.services.simpleworkflow.flow.ActivitiesClientBase;
import com.amazonaws.services.simpleworkflow.flow.ActivitySchedulingOptions;
import com.amazonaws.services.simpleworkflow.flow.DataConverter;
import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.amazonaws.services.simpleworkflow.flow.generic.GenericActivityClient;
import com.amazonaws.services.simpleworkflow.model.ActivityType;

public class EMRActivitiesClientImpl extends ActivitiesClientBase implements EMRActivitiesClient {

	public EMRActivitiesClientImpl() {
        this(null, new com.amazonaws.services.simpleworkflow.flow.JsonDataConverter(), null);
    }

    public EMRActivitiesClientImpl(GenericActivityClient genericClient) {
        this(genericClient, new com.amazonaws.services.simpleworkflow.flow.JsonDataConverter(), null);
    }
    
    public EMRActivitiesClientImpl(GenericActivityClient genericClient, 
            DataConverter dataConverter, ActivitySchedulingOptions schedulingOptions) {
            
        super(genericClient, dataConverter, schedulingOptions);
    }
    
    @Override
    public final Promise<Void> actEMR(java.lang.String inputPath, java.lang.String outputPath) {
        return actEMRImpl(Promise.asPromise(inputPath), Promise.asPromise(outputPath), (ActivitySchedulingOptions)null);
    }

    @Override
    public final Promise<Void> actEMR(java.lang.String inputPath, java.lang.String outputPath, Promise<?>... waitFor) {
        return actEMRImpl(Promise.asPromise(inputPath), Promise.asPromise(outputPath), (ActivitySchedulingOptions)null, waitFor);
    }

    @Override
    public final Promise<Void> actEMR(java.lang.String inputPath, java.lang.String outputPath, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor) {
        return actEMRImpl(Promise.asPromise(inputPath), Promise.asPromise(outputPath), optionsOverride, waitFor);
    }

    @Override
    public final Promise<Void> actEMR(Promise<java.lang.String> inputPath, Promise<java.lang.String> outputPath) {
        return actEMRImpl(inputPath, outputPath, (ActivitySchedulingOptions)null);
    }

    @Override
    public final Promise<Void> actEMR(Promise<java.lang.String> inputPath, Promise<java.lang.String> outputPath, Promise<?>... waitFor) {
        return actEMRImpl(inputPath, outputPath, (ActivitySchedulingOptions)null, waitFor);
    }

    @Override
    public final Promise<Void> actEMR(Promise<java.lang.String> inputPath, Promise<java.lang.String> outputPath, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor) {
        return actEMRImpl(inputPath, outputPath, optionsOverride, waitFor);
    }
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected Promise<Void> actEMRImpl(final Promise<java.lang.String> inputPath, final Promise<java.lang.String> outputPath, final ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor) {

        ActivityType activityType = new ActivityType();
		activityType.setName("EMR Calculate");
		activityType.setVersion("5.0");

        Promise[] _input_ = new Promise[2];
        _input_[0] = inputPath;
        _input_[1] = outputPath;

        return (Promise)scheduleActivity(activityType, _input_, optionsOverride, Void.class, waitFor);
    }

}