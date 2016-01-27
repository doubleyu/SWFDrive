/**
 * This code was generated from {@link com.dby.swf.UnloadActivities}.
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

public class TestActivitiesClientImpl extends ActivitiesClientBase implements TestActivitiesClient {

	public TestActivitiesClientImpl() {
        this(null, new com.amazonaws.services.simpleworkflow.flow.JsonDataConverter(), null);
    }

    public TestActivitiesClientImpl(GenericActivityClient genericClient) {
        this(genericClient, new com.amazonaws.services.simpleworkflow.flow.JsonDataConverter(), null);
    }
    
    public TestActivitiesClientImpl(GenericActivityClient genericClient, 
            DataConverter dataConverter, ActivitySchedulingOptions schedulingOptions) {
            
        super(genericClient, dataConverter, schedulingOptions);
    }
    
    @Override
    public final Promise<Void> actUnload(java.lang.String date) {
        return actUnloadImpl(Promise.asPromise(date), (ActivitySchedulingOptions)null);
    }

    @Override
    public final Promise<Void> actUnload(java.lang.String date, Promise<?>... waitFor) {
        return actUnloadImpl(Promise.asPromise(date), (ActivitySchedulingOptions)null, waitFor);
    }

    @Override
    public final Promise<Void> actUnload(java.lang.String date, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor) {
        return actUnloadImpl(Promise.asPromise(date), optionsOverride, waitFor);
    }

    @Override
    public final Promise<Void> actUnload(Promise<java.lang.String> date) {
        return actUnloadImpl(date, (ActivitySchedulingOptions)null);
    }

    @Override
    public final Promise<Void> actUnload(Promise<java.lang.String> date, Promise<?>... waitFor) {
        return actUnloadImpl(date, (ActivitySchedulingOptions)null, waitFor);
    }

    @Override
    public final Promise<Void> actUnload(Promise<java.lang.String> date, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor) {
        return actUnloadImpl(date, optionsOverride, waitFor);
    }
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected Promise<Void> actUnloadImpl(final Promise<java.lang.String> date, final ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor) {

        ActivityType activityType = new ActivityType();
		activityType.setName("Unload to S3");
		activityType.setVersion("5.0");

        Promise[] _input_ = new Promise[1];
        _input_[0] = date;

        return (Promise)scheduleActivity(activityType, _input_, optionsOverride, Void.class, waitFor);
    }

}