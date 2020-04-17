package com.bridgelabz.service;

import java.util.ArrayList;

public class InvoiceService {
    private  int COST_PER_TIME;
    private  double MINIMUM_COST_PER_KILOMETER;
    private double MINIMUM_FARE;

    public enum subscriptionPlan {PremimumRides, NormalRides}

    RideRepository rideRepository = new RideRepository();
    ArrayList<Ride> listOfRides = new ArrayList<Ride>();

    public InvoiceService(InvoiceService.subscriptionPlan plan) {
        if (plan.equals(InvoiceService.subscriptionPlan.PremimumRides)) {
            this.MINIMUM_FARE = 15;
            this.MINIMUM_COST_PER_KILOMETER = 20;
            this.COST_PER_TIME = 2;
        }
        if (plan.equals(InvoiceService.subscriptionPlan.NormalRides)) {
            this.MINIMUM_FARE = 5;
            this.MINIMUM_COST_PER_KILOMETER = 10;
            this.COST_PER_TIME = 1;
        }
    }
    public double calculatefare(double distance, int time) {
        double totalFare = distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_TIME;
        return Math.max(totalFare, MINIMUM_FARE);
    }
    public double calculateFareForMultipleRides(Ride[] rides) {
        double aggregateFare = 0;
        for (Ride ride : rides) {
            listOfRides.add(ride);
            aggregateFare += calculatefare(ride.distance, ride.time);
        }
        return aggregateFare;
    }

    public InvoiceSummary getInvoiceSummary(Ride[] rides) {
        return new InvoiceSummary(rides.length, calculateFareForMultipleRides(rides));
    }

    public void addRides(String userId) {
        rideRepository.addUserRides(userId, listOfRides);
    }

    public ArrayList<Ride> getRidesByUserId(String userId) {
        ArrayList<Ride> ridesByUserId = rideRepository.getRidesByUserId(userId);
        return ridesByUserId;
    }
}