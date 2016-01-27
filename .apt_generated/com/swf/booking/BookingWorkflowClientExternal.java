/**
 * This code was generated from {@link com.swf.booking.BookingWorkflow}.
 *
 * Any changes made directly to this file will be lost when 
 * the code is regenerated.
 */
package com.swf.booking;

import com.amazonaws.services.simpleworkflow.flow.StartWorkflowOptions;
import com.amazonaws.services.simpleworkflow.flow.WorkflowClientExternal;

public interface BookingWorkflowClientExternal extends WorkflowClientExternal
{
    void makeBooking(int requestID, int customerID, boolean reserveAir, boolean reserveCar);
    void makeBooking(int requestID, int customerID, boolean reserveAir, boolean reserveCar, StartWorkflowOptions optionsOverride);
}