/**
 * This code was generated from {@link com.dby.swf.LoadActivities}.
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

public class LoadActivitiesClientImpl extends ActivitiesClientBase implements LoadActivitiesClient {

	public LoadActivitiesClientImpl() {
        this(null, new com.amazonaws.services.simpleworkflow.flow.JsonDataConverter(), null);
    }

    public LoadActivitiesClientImpl(GenericActivityClient genericClient) {
        this(genericClient, new com.amazonaws.services.simpleworkflow.flow.JsonDataConverter(), null);
    }
    
    public LoadActivitiesClientImpl(GenericActivityClient genericClient, 
            DataConverter dataConverter, ActivitySchedulingOptions schedulingOptions) {
            
        super(genericClient, dataConverter, schedulingOptions);
    }
    
    @Override
    public final Promise<Void> actLoad(java.lang.String inputPath) {
        return actLoadImpl(Promise.asPromise(inputPath), (ActivitySchedulingOptions)null);
    }

    @Override
    public final Promise<Void> actLoad(java.lang.String inputPath, Promise<?>... waitFor) {
        return actLoadImpl(Promise.asPromise(inputPath), (ActivitySchedulingOptions)null, waitFor);
    }

    @Override
    public final Promise<Void> actLoad(java.lang.String inputPath, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor) {
        return actLoadImpl(Promise.asPromise(inputPath), optionsOverride, waitFor);
    }

    @Override
    public final Promise<Void> actLoad(Promise<java.lang.String> inputPath) {
        return actLoadImpl(inputPath, (ActivitySchedulingOptions)null);
    }

    @Override
    public final Promise<Void> actLoad(Promise<java.lang.String> inputPath, Promise<?>... waitFor) {
        return actLoadImpl(inputPath, (ActivitySchedulingOptions)null, waitFor);
    }

    @Override
    public final Promise<Void> actLoad(Promise<java.lang.String> inputPath, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor) {
        return actLoadImpl(inputPath, optionsOverride, waitFor);
    }
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected Promise<Void> actLoadImpl(final Promise<java.lang.String> inputPath, final ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor) {

        ActivityType activityType = new ActivityType();
		activityType.setName("Load Result to Redshift");
		activityType.setVersion("5.0");

        Promise[] _input_ = new Promise[1];
        _input_[0] = inputPath;

        return (Promise)scheduleActivity(activityType, _input_, optionsOverride, Void.class, waitFor);
    }

}