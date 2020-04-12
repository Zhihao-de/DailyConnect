package cn.ac.iscas.entity;

import java.util.Date;

public class Events {
    private Integer id;

    private Integer categories;

    private Date createTime;

    private String eventName;

    private String eventContent;

    private Date startTime;

    private Date endTime;

    private Integer teacher;

    private Integer frequency;

    private Integer shareType;

    private String shareTag;

    private String shareIndividuals;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategories() {
        return categories;
    }

    public void setCategories(Integer categories) {
        this.categories = categories;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName == null ? null : eventName.trim();
    }

    public String getEventContent() {
        return eventContent;
    }

    public void setEventContent(String eventContent) {
        this.eventContent = eventContent == null ? null : eventContent.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getTeacher() {
        return teacher;
    }

    public void setTeacher(Integer teacher) {
        this.teacher = teacher;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getShareType() {
        return shareType;
    }

    public void setShareType(Integer shareType) {
        this.shareType = shareType;
    }

    public String getShareTag() {
        return shareTag;
    }

    public void setShareTag(String shareTag) {
        this.shareTag = shareTag == null ? null : shareTag.trim();
    }

    public String getShareIndividuals() {
        return shareIndividuals;
    }

    public void setShareIndividuals(String shareIndividuals) {
        this.shareIndividuals = shareIndividuals == null ? null : shareIndividuals.trim();
    }
}