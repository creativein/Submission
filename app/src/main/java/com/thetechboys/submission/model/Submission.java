package com.thetechboys.submission.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by niraj.kumar.chauhan on 09-02-2015.
 */
@Parcel
public class Submission {

    @SerializedName("id")
    private int id;
    @SerializedName("submission")
    private String submission;
    @SerializedName("likes")
    private int likes;
    @SerializedName("hates")
    private int hates;
    @SerializedName("timestamp")
    private String timeStamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubmission() {
        return submission;
    }

    public void setSubmission(String submission) {
        this.submission = submission;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getHates() {
        return hates;
    }

    public void setHates(int hates) {
        this.hates = hates;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
