/**
 * This code was generated from {@link com.swf.booking.BookingActivities}.
 *
 * Any changes made directly to this file will be lost when 
 * the code is regenerated.
 */
package com.swf.booking;

import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.amazonaws.services.simpleworkflow.flow.ActivitiesClient;
import com.amazonaws.services.simpleworkflow.flow.ActivitySchedulingOptions;

public interface BookingActivitiesClient extends ActivitiesClient
{
    Promise<Void> reserveCar(int requestId);
    Promise<Void> reserveCar(int requestId, Promise<?>... waitFor);
    Promise<Void> reserveCar(int requestId, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor);
    Promise<Void> reserveCar(Promise<Integer> requestId);
    Promise<Void> reserveCar(Promise<Integer> requestId, Promise<?>... waitFor);
    Promise<Void> reserveCar(Promise<Integer> requestId, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor);
    Promise<Void> reserveAirline(int requestId);
    Promise<Void> reserveAirline(int requestId, Promise<?>... waitFor);
    Promise<Void> reserveAirline(int requestId, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor);
    Promise<Void> reserveAirline(Promise<Integer> requestId);
    Promise<Void> reserveAirline(Promise<Integer> requestId, Promise<?>... waitFor);
    Promise<Void> reserveAirline(Promise<Integer> requestId, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor);
    Promise<Void> sendConfirmationActivity(int customerId);
    Promise<Void> sendConfirmationActivity(int customerId, Promise<?>... waitFor);
    Promise<Void> sendConfirmationActivity(int customerId, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor);
    Promise<Void> sendConfirmationActivity(Promise<Integer> customerId);
    Promise<Void> sendConfirmationActivity(Promise<Integer> customerId, Promise<?>... waitFor);
    Promise<Void> sendConfirmationActivity(Promise<Integer> customerId, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor);
}