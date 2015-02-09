package com.codepath.instagramclient;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makeramen.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {

    private static final int MAX_COMMENTS = 2;

    // View lookup cache
    private static class ViewHolder {
        TextView username;
        TextView caption;
        ImageView profilePhoto;
        ImageView photo;
        TextView timestamp;
        TextView likes;
        TextView viewAllComments;
        LinearLayout comments;
    }

    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> objects) {
        super(context, R.layout.item_photo, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        InstagramPhoto photo = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
            viewHolder.username = (TextView) convertView.findViewById(R.id.tvUsername);
            viewHolder.caption = (TextView) convertView.findViewById(R.id.tvCaption);
            viewHolder.profilePhoto = (ImageView) convertView.findViewById(R.id.ivProfilePhoto);
            viewHolder.photo = (ImageView) convertView.findViewById(R.id.ivPhoto);
            viewHolder.timestamp = (TextView) convertView.findViewById(R.id.tvTimestamp);
            viewHolder.likes = (TextView) convertView.findViewById(R.id.tvLikes);
            viewHolder.viewAllComments = (TextView) convertView.findViewById(R.id.tvViewAllComments);
            viewHolder.comments = (LinearLayout) convertView.findViewById(R.id.llComments);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Populate the data into the template view using the data object
        viewHolder.username.setText(photo.getUsername());
        String formattedCaptionText = getFormattedCaptionText(photo.getUsername(), photo.getCaption());
        viewHolder.caption.setText(Html.fromHtml(formattedCaptionText));
//        viewHolder.profilePhoto.setImageResource(0);
//        viewHolder.photo.setImageResource(0);

        String dateString = (String) DateUtils.getRelativeTimeSpanString(photo.getCreatedTime() * 1000, System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        dateString = formatDateString(dateString);
        viewHolder.timestamp.setText(dateString);
        Resources res = getContext().getResources();
        int likesCount = photo.getLikesCount();
        String likes = res.getQuantityString(R.plurals.likes, likesCount, likesCount);
        viewHolder.likes.setText(likes);
        int commentsCount = photo.getCommentsCount();
        String viewAllComments = res.getQuantityString(R.plurals.comments, commentsCount, commentsCount);
        viewHolder.viewAllComments.setText(viewAllComments);

        Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(Color.BLACK)
                .borderWidthDp(0)
                .cornerRadiusDp(30)
                .oval(false)
                .build();

        Picasso.with(getContext())
                .load(photo.getProfilePhotoUrl())
                .placeholder(R.drawable.default_avatar)
                .transform(transformation)
                .into(viewHolder.profilePhoto);

        Picasso.with(getContext())
                .load(photo.getImageUrl())
                .placeholder(R.drawable.default_placeholder)
                .fit()
                .into(viewHolder.photo);


        viewHolder.comments.removeAllViews();

        for (int i = 0; i < Math.min(MAX_COMMENTS, photo.getComments().size()); i++) {
            InstagramComment comment = photo.getComments().get(i);
            TextView commentTextView = new TextView(getContext());
            // TODO: Define a layout
            commentTextView.setTextSize(17);
            commentTextView.setMaxLines(2);
            commentTextView.setEllipsize(TextUtils.TruncateAt.END);
            String formattedCommentText = getFormattedCommentText(comment.getUsername(), comment.getText());
            commentTextView.setText(Html.fromHtml(formattedCommentText));
            viewHolder.comments.addView(commentTextView);
        }

        // Return the completed view to render on screen
        return convertView;
    }

    private String getFormattedCaptionText(String username, String caption) {
        int darkBlueColor = getContext().getResources().getColor(R.color.dark_blue_color);
        String hexDarkBlueColor = getHexColor(darkBlueColor);
        int darkGrayColor = getContext().getResources().getColor(R.color.dark_gray_color);
        String hexDarkGrayColor = getHexColor(darkGrayColor);
        return "<font color=\"" + hexDarkBlueColor + "\">" + username + "</font>&nbsp;" + "<font color=\"" + hexDarkGrayColor + "\">" + caption + "</font>";
    }

    private String getFormattedCommentText(String username, String caption) {
        return getFormattedCaptionText(username, caption);
    }

    private String getHexColor(int color) {
        return String.format("#%06X", (0xFFFFFF & color));
    }

    // Dirty hack! How do we deal with locales?
    private String formatDateString(String dateString) {
        dateString = dateString.replace(" ago", "");
        dateString = dateString.replace(" seconds", "s");
        dateString = dateString.replace(" second", "s");
        dateString = dateString.replace(" minutes", "m");
        dateString = dateString.replace(" minute", "m");
        dateString = dateString.replace(" hours", "h");
        dateString = dateString.replace(" hour", "h");

        return dateString;
    }
}
