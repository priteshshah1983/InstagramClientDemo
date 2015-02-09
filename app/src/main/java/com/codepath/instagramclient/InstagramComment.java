package com.codepath.instagramclient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class InstagramComment {

    private String username;
    private String text;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static InstagramComment fromJson(JSONObject commentsJSON) {
        InstagramComment comment = new InstagramComment();
        JSONObject from = commentsJSON.optJSONObject("from");
        if (from != null) {
            comment.setUsername(from.optString("username"));
        }
        comment.setText(commentsJSON.optString("text"));
        // Return new object
        return comment;
    }

    public static ArrayList<InstagramComment> fromJson(JSONArray jsonArray) {
        ArrayList<InstagramComment> comments = new ArrayList<>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject commentJSON = null;
            try {
                commentJSON = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            InstagramComment comment = InstagramComment.fromJson(commentJSON);
            if (comment != null) {
                comments.add(comment);
            }
        }

        return comments;
    }
}
