package com.bridgelabz.servicetest;

import com.bridgelabz.service.InvoiceService;
import com.bridgelabz.service.InvoiceSummary;
import com.bridgelabz.service.Ride;
import org.junit.Assert;
import org.junit.Test;

public class CabInvoiceServiceTest {
    InvoiceService invoiceServiceForPremimum = new InvoiceService(InvoiceService.subscriptionPlan.PremimumRides);
    InvoiceService invoiceServiceForNormal = new InvoiceService(InvoiceService.subscriptionPlan.NormalRides);

    @Test
    public void givenDistanceAndTime_withNormalPlan_shouldReturnTotalFare() {
        double distance = 5.0;
        int time = 15;
        double fare = invoiceServiceForNormal.calculatefare(distance, time);
        Assert.assertEquals(65, fare, 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_withNormalPlan_shouldReturnMinFare() {
        double distance = 0.1;
        int time = 2;
        double fare = invoiceServiceForNormal.calculatefare(distance, time);
        Assert.assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_withNormalPlan_shouldReturnInvoiceSummary() {
        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(0.1, 1),
                new Ride(4.1, 25)
        };
        double totalFare = invoiceServiceForNormal.calculateFareForMultipleRides(rides);
        Assert.assertEquals(96, totalFare, 0);
    }

    @Test
    public void givenUserIdAndRide_withNormalPlan_shouldReturnInvoiceSummary() {
        String userId = "gdev3123@gmail.com";
        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(0.1, 1),
                new Ride(4.1, 25)
        };
        invoiceServiceForNormal.addRides(userId);
        InvoiceSummary invoiceSummary = invoiceServiceForNormal.getInvoiceSummary(rides);
        InvoiceSummary fare = new InvoiceSummary(3, 96);
        Assert.assertEquals(fare, invoiceSummary);
        Assert.assertEquals(rides.length, invoiceServiceForNormal.getRidesByUserId(userId).size());
    }

    @Test
    public void givenDistanceAndTime_withPremimumPlan_shouldReturnTotalFare() {
        double distance = 5.0;
        int time = 15;
        double fare = invoiceServiceForPremimum.calculatefare(distance, time);
        Assert.assertEquals(130, fare, 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_withPremimumPlan_shouldReturnMinFare() {
        double distance = 0.1;
        int time = 2;
        double fare = invoiceServiceForPremimum.calculatefare(distance, time);
        Assert.assertEquals(15.0, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_withPremimumPlan_shouldReturnInvoiceSummary() {
        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(0.1, 1),
                new Ride(4.1, 25)
        };
        double totalFare = invoiceServiceForPremimum.calculateFareForMultipleRides(rides);
        Assert.assertEquals(197, totalFare, 0);
    }

    @Test
    public void givenUserIdAndRide_withPremimumPlan_shouldReturnInvoiceSummary() {
        String userId = "gdev3123@gmail.com";
        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(0.1, 1),
                new Ride(4.1, 25)
        };
        invoiceServiceForPremimum.addRides(userId);
        InvoiceSummary invoiceSummary = invoiceServiceForPremimum.getInvoiceSummary(rides);
        InvoiceSummary fare = new InvoiceSummary(3, 197);
        Assert.assertEquals(fare, invoiceSummary);
        Assert.assertEquals(rides.length, invoiceServiceForPremimum.getRidesByUserId(userId).size());
    }
}