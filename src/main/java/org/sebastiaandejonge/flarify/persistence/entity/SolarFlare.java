package org.sebastiaandejonge.flarify.persistence.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document
public class SolarFlare implements Serializable {

    @Id
    private ObjectId _id;
    private String flareId;
    private String classType;
    private Double classSuffix;
    private Integer activeRegion;
    private Date beginTime;
    private Date peakTime;
    private Date endTime;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date modifiedAt;

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId id) {
        this._id = id;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
