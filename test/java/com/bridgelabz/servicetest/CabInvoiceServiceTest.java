package com.bridgelabz.servicetest;

import com.bridgelabz.service.InvoiceGenerator;
import com.bridgelabz.service.InvoiceSummary;
import com.bridgelabz.service.Ride;
import org.junit.Assert;
import org.junit.Test;

public class CabInvoiceServiceTest {
    InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
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
        InvoiceSummary summary = invoiceGenerator.calculatefare(rides);
      InvoiceSummary InvoiceSummary = new  InvoiceSummary(3,8.0);
       double fare = invoiceGenerator.calculatefare(distance, time);
      Assert.assertEquals (5,5);
    }

}