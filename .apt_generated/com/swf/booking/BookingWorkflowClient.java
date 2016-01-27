/**
 * This code was generated from {@link com.swf.booking.BookingWorkflow}.
 *
 * Any changes made directly to this file will be lost when 
 * the code is regenerated.
 */
package com.swf.booking;

import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.amazonaws.services.simpleworkflow.flow.StartWorkflowOptions;
import com.amazonaws.services.simpleworkflow.flow.WorkflowClient;

public interface BookingWorkflowClient extends WorkflowClient
{
    Promise<Void> makeBooking(int requestID, int customerID, boolean reserveAir, boolean reserveCar);
    Promise<Void> makeBooking(int requestID, int customerID, boolean reserveAir, boolean reserveCar, Promise<?>... waitFor);
    Promise<Void> makeBooking(int requestID, int customerID, boolean reserveAir, boolean reserveCar, StartWorkflowOptions optionsOverride, Promise<?>... waitFor);
    Promise<Void> makeBooking(Promise<Integer> requestID, Promise<Integer> customerID, Promise<Boolean> reserveAir, Promise<Boolean> reserveCar);
    Promise<Void> makeBooking(Promise<Integer> requestID, Promise<Integer> customerID, Promise<Boolean> reserveAir, Promise<Boolean> reserveCar, Promise<?>... waitFor);
    Promise<Void> makeBooking(Promise<Integer> requestID, Promise<Integer> customerID, Promise<Boolean> reserveAir, Promise<Boolean> reserveCar, StartWorkflowOptions optionsOverride, Promise<?>... waitFor);
}