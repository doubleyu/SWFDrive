/**
 * This code was generated from {@link com.swf.booking.BookingActivities}.
 *
 * Any changes made directly to this file will be lost when 
 * the code is regenerated.
 */
package com.swf.booking;

import com.amazonaws.services.simpleworkflow.flow.ActivitiesClientBase;
import com.amazonaws.services.simpleworkflow.flow.ActivitySchedulingOptions;
import com.amazonaws.services.simpleworkflow.flow.DataConverter;
import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.amazonaws.services.simpleworkflow.flow.generic.GenericActivityClient;
import com.amazonaws.services.simpleworkflow.model.ActivityType;

public class BookingActivitiesClientImpl extends ActivitiesClientBase implements BookingActivitiesClient {

	public BookingActivitiesClientImpl() {
        this(null, new com.amazonaws.services.simpleworkflow.flow.JsonDataConverter(), null);
    }

    public BookingActivitiesClientImpl(GenericActivityClient genericClient) {
        this(genericClient, new com.amazonaws.services.simpleworkflow.flow.JsonDataConverter(), null);
    }
    
    public BookingActivitiesClientImpl(GenericActivityClient genericClient, 
            DataConverter dataConverter, ActivitySchedulingOptions schedulingOptions) {
            
        super(genericClient, dataConverter, schedulingOptions);
    }
    
    @Override
    public final Promise<Void> reserveCar(int requestId) {
        return reserveCarImpl(Promise.asPromise(requestId), (ActivitySchedulingOptions)null);
    }

    @Override
    public final Promise<Void> reserveCar(int requestId, Promise<?>... waitFor) {
        return reserveCarImpl(Promise.asPromise(requestId), (ActivitySchedulingOptions)null, waitFor);
    }

    @Override
    public final Promise<Void> reserveCar(int requestId, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor) {
        return reserveCarImpl(Promise.asPromise(requestId), optionsOverride, waitFor);
    }

    @Override
    public final Promise<Void> reserveCar(Promise<Integer> requestId) {
        return reserveCarImpl(requestId, (ActivitySchedulingOptions)null);
    }

    @Override
    public final Promise<Void> reserveCar(Promise<Integer> requestId, Promise<?>... waitFor) {
        return reserveCarImpl(requestId, (ActivitySchedulingOptions)null, waitFor);
    }

    @Override
    public final Promise<Void> reserveCar(Promise<Integer> requestId, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor) {
        return reserveCarImpl(requestId, optionsOverride, waitFor);
    }
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected Promise<Void> reserveCarImpl(final Promise<Integer> requestId, final ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor) {

        ActivityType activityType = new ActivityType();
		activityType.setName("BookingActivities.reserveCar");
		activityType.setVersion("1.0");

        Promise[] _input_ = new Promise[1];
        _input_[0] = requestId;

        return (Promise)scheduleActivity(activityType, _input_, optionsOverride, Void.class, waitFor);
    }

    @Override
    public final Promise<Void> reserveAirline(int requestId) {
        return reserveAirlineImpl(Promise.asPromise(requestId), (ActivitySchedulingOptions)null);
    }

    @Override
    public final Promise<Void> reserveAirline(int requestId, Promise<?>... waitFor) {
        return reserveAirlineImpl(Promise.asPromise(requestId), (ActivitySchedulingOptions)null, waitFor);
    }

    @Override
    public final Promise<Void> reserveAirline(int requestId, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor) {
        return reserveAirlineImpl(Promise.asPromise(requestId), optionsOverride, waitFor);
    }

    @Override
    public final Promise<Void> reserveAirline(Promise<Integer> requestId) {
        return reserveAirlineImpl(requestId, (ActivitySchedulingOptions)null);
    }

    @Override
    public final Promise<Void> reserveAirline(Promise<Integer> requestId, Promise<?>... waitFor) {
        return reserveAirlineImpl(requestId, (ActivitySchedulingOptions)null, waitFor);
    }

    @Override
    public final Promise<Void> reserveAirline(Promise<Integer> requestId, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor) {
        return reserveAirlineImpl(requestId, optionsOverride, waitFor);
    }
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected Promise<Void> reserveAirlineImpl(final Promise<Integer> requestId, final ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor) {

        ActivityType activityType = new ActivityType();
		activityType.setName("BookingActivities.reserveAirline");
		activityType.setVersion("1.0");

        Promise[] _input_ = new Promise[1];
        _input_[0] = requestId;

        return (Promise)scheduleActivity(activityType, _input_, optionsOverride, Void.class, waitFor);
    }

    @Override
    public final Promise<Void> sendConfirmationActivity(int customerId) {
        return sendConfirmationActivityImpl(Promise.asPromise(customerId), (ActivitySchedulingOptions)null);
    }

    @Override
    public final Promise<Void> sendConfirmationActivity(int customerId, Promise<?>... waitFor) {
        return sendConfirmationActivityImpl(Promise.asPromise(customerId), (ActivitySchedulingOptions)null, waitFor);
    }

    @Override
    public final Promise<Void> sendConfirmationActivity(int customerId, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor) {
        return sendConfirmationActivityImpl(Promise.asPromise(customerId), optionsOverride, waitFor);
    }

    @Override
    public final Promise<Void> sendConfirmationActivity(Promise<Integer> customerId) {
        return sendConfirmationActivityImpl(customerId, (ActivitySchedulingOptions)null);
    }

    @Override
    public final Promise<Void> sendConfirmationActivity(Promise<Integer> customerId, Promise<?>... waitFor) {
        return sendConfirmationActivityImpl(customerId, (ActivitySchedulingOptions)null, waitFor);
    }

    @Override
    public final Promise<Void> sendConfirmationActivity(Promise<Integer> customerId, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor) {
        return sendConfirmationActivityImpl(customerId, optionsOverride, waitFor);
    }
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected Promise<Void> sendConfirmationActivityImpl(final Promise<Integer> customerId, final ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor) {

        ActivityType activityType = new ActivityType();
		activityType.setName("BookingActivities.sendConfirmationActivity");
		activityType.setVersion("1.0");

        Promise[] _input_ = new Promise[1];
        _input_[0] = customerId;

        return (Promise)scheduleActivity(activityType, _input_, optionsOverride, Void.class, waitFor);
    }

}