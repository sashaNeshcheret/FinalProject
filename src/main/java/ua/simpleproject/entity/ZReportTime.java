package ua.simpleproject.entity;

import java.sql.Timestamp;

public class ZReportTime {
    private int id;
    private Timestamp zTime;

    public ZReportTime() {
    }

    public ZReportTime(int id, Timestamp zTime) {
        this.id = id;
        this.zTime = zTime;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Timestamp getZTime() {
        return zTime;
    }
    public void setZTime(Timestamp zTime) {
        this.zTime = zTime;
    }
}
