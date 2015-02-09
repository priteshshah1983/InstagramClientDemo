package com.codepath.instagramclient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InstagramPhoto {

    private String username;
    private String caption;
    private String profilePhotoUrl;
    private String imageUrl;
    private int imageHeight;
    private int likesCount;
    private long createdTime;

    private List<InstagramComment> comments;
    // Do we need this?
    private int commentsCount;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public static InstagramPhoto fromJson(JSONObject photoJSON) {
        InstagramPhoto photo = new InstagramPhoto();
        JSONObject user = photoJSON.optJSONObject("user");
        if (user != null) {
            photo.setUsername(user.optString("username"));
            photo.setProfilePhotoUrl(user.optString("profile_picture"));
        }
        JSONObject caption = photoJSON.optJSONObject("caption");
        if (caption != null) {
            photo.setCaption(caption.optString("text"));
            photo.setCreatedTime(Long.parseLong(caption.optString("created_time")));
        }
        JSONObject comments = photoJSON.optJSONObject("comments");
        if (comments != null) {
            photo.setCommentsCount(comments.optInt("count"));
            JSONArray commentsData = comments.optJSONArray("data");
            photo.setComments(InstagramComment.fromJson(commentsData));
        }
        JSONObject images = photoJSON.optJSONObject("images");
        if (images != null) {
            JSONObject standardResolution = images.optJSONObject("standard_resolution");
            if (standardResolution != null) {
                photo.setImageUrl(standardResolution.optString("url"));
                photo.setImageHeight(standardResolution.optInt("height"));
            }
        }
        JSONObject likes = photoJSON.optJSONObject("likes");
        if (likes != null) {
            photo.setLikesCount(likes.optInt("count"));
        }
        // Return new object
        return photo;
    }

    public static ArrayList<InstagramPhoto> fromJson(JSONArray jsonArray) {
        ArrayList<InstagramPhoto> photos = new ArrayList<>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject photoJSON = null;
            try {
                photoJSON = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            InstagramPhoto photo = InstagramPhoto.fromJson(photoJSON);
            if (photo != null) {
                photos.add(photo);
            }
        }

        return photos;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public List<InstagramComment> getComments() {
        return comments;
    }

    public void setComments(List<InstagramComment> comments) {
        this.comments = comments;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }
}
