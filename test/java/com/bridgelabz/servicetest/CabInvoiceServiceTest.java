package com.bridgelabz.servicetest;

import com.bridgelabz.service.InvoiceService;
import com.bridgelabz.service.InvoiceSummary;
import com.bridgelabz.service.Ride;
import org.junit.Assert;
import org.junit.Test;

public class CabInvoiceServiceTest {
    InvoiceService invoiceGenerator = new InvoiceService();
    private double distance;
    private int time;

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 1.0;
        int time = 5;
        double fare = invoiceGenerator.calculatefare(distance, time);
        Assert.assertEquals(15, fare, 0.0);
    }

    @Test
    public void givenLessDistanceOrTime_ShouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceGenerator.calculatefare(distance, time);
        Assert.assertEquals(5, fare, 0.0);

    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(0.1, 1),
                new Ride(4.1, 25)
        };
        double totalFare = invoiceGenerator.calculateFareForMultipleRides(rides);
        Assert.assertEquals(96, totalFare, 0);
    }

    @Test
    public void givenUserIdAndRide_shouldReturnInvoiceSummary() {
        String userId = "gdev3123@gmail.com";
        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(0.1, 1),
                new Ride(4.1, 25)
        };
        invoiceGenerator.addRides(userId);
        InvoiceSummary invoiceSummary = invoiceGenerator.getInvoiceSummary(rides);
        InvoiceSummary fare = new InvoiceSummary(3, 96);
        Assert.assertEquals(fare, invoiceSummary);
        Assert.assertEquals(rides.length, invoiceGenerator.getRidesByUserId(userId).size());
    }
}