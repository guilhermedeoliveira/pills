package com.josenaves.pills.data;

public class TrackingRepository implements TrackingDataSource {

    private final TrackingDataSource dataSource;

    public TrackingRepository(TrackingDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void trackShareEvent(String phrase) {
        dataSource.trackShareEvent(phrase);
    }
}
