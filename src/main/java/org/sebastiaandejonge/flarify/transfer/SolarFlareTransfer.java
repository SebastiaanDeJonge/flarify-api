package org.sebastiaandejonge.flarify.transfer;

import java.util.Date;

public class SolarFlareTransfer implements TransferInterface {

    private String id;
    private String flareId;
    private String classType;
    private Double classSuffix;
    private Integer activeRegion;
    private Date beginTime;
    private Date peakTime;
    private Date endTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlareId() {
        return flareId;
    }

    public void setFlareId(String flareId) {
        this.flareId = flareId;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public Double getClassSuffix() {
        return classSuffix;
    }

    public void setClassSuffix(Double classSuffix) {
        this.classSuffix = classSuffix;
    }

    public Integer getActiveRegion() {
        return activeRegion;
    }

    public void setActiveRegion(Integer activeRegion) {
        this.activeRegion = activeRegion;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getPeakTime() {
        return peakTime;
    }

    public void setPeakTime(Date peakTime) {
        this.peakTime = peakTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
