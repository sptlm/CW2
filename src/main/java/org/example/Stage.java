package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public abstract class ConstructionStage implements Stage {
    protected String description;
    protected double cost;
    protected String startDate;
    protected String endDate;
    protected Status status;
    protected ConstructionStage nextStage;
    protected ConstructionStage prevStage;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public ConstructionStage(String description, double cost, String startDate, String endDate) throws InvalidDateException {
        this.description = description;
        this.cost = cost;
        this.status = Status.PLANNED;

        if (!isValidDate(startDate) || !isValidDate(endDate)) {
            throw new InvalidDateException("Invalid date format. Use dd.MM.yyyy");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private boolean isValidDate(String date) {
        try {
            DATE_FORMAT.setLenient(false);
            DATE_FORMAT.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public Stage next() { return nextStage; }
    public Stage prev() { return prevStage; }

    public void start() {
        this.status = Status.IN_PROGRESS;
        System.out.println(description + " has started.");
    }

    public void stop() {
        this.status = Status.COMPLETED;
        System.out.println(description + " is completed.");
    }

    public void reject() {
        this.status = Status.REJECTED;
        System.out.println(description + " has been rejected!");
    }
}
