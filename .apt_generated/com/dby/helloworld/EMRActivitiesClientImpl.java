/**
 * This code was generated from {@link com.dby.helloworld.EMRActivities}.
 *
 * Any changes made directly to this file will be lost when 
 * the code is regenerated.
 */
package com.dby.helloworld;

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
    public final Promise<Void> actEMR(java.lang.String name) {
        return actEMRImpl(Promise.asPromise(name), (ActivitySchedulingOptions)null);
    }

    @Override
    public final Promise<Void> actEMR(java.lang.String name, Promise<?>... waitFor) {
        return actEMRImpl(Promise.asPromise(name), (ActivitySchedulingOptions)null, waitFor);
    }

    @Override
    public final Promise<Void> actEMR(java.lang.String name, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor) {
        return actEMRImpl(Promise.asPromise(name), optionsOverride, waitFor);
    }

    @Override
    public final Promise<Void> actEMR(Promise<java.lang.String> name) {
        return actEMRImpl(name, (ActivitySchedulingOptions)null);
    }

    @Override
    public final Promise<Void> actEMR(Promise<java.lang.String> name, Promise<?>... waitFor) {
        return actEMRImpl(name, (ActivitySchedulingOptions)null, waitFor);
    }

    @Override
    public final Promise<Void> actEMR(Promise<java.lang.String> name, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor) {
        return actEMRImpl(name, optionsOverride, waitFor);
    }
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected Promise<Void> actEMRImpl(final Promise<java.lang.String> name, final ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor) {

        ActivityType activityType = new ActivityType();
		activityType.setName("EMRActivity");
		activityType.setVersion("1.0");

        Promise[] _input_ = new Promise[1];
        _input_[0] = name;

        return (Promise)scheduleActivity(activityType, _input_, optionsOverride, Void.class, waitFor);
    }

}