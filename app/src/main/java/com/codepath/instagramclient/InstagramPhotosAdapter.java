package com.codepath.instagramclient;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {

    // View lookup cache
    private static class ViewHolder {
        TextView username;
        TextView caption;
        ImageView profilePhoto;
        ImageView photo;
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
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Populate the data into the template view using the data object
        viewHolder.username.setText(photo.getUsername());
        viewHolder.caption.setText(photo.getCaption());
        viewHolder.profilePhoto.setImageResource(0);
        viewHolder.photo.setImageResource(0);
        Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(Color.BLACK)
                .borderWidthDp(0)
                .cornerRadiusDp(30)
                .oval(false)
                .build();
        Picasso.with(getContext()).load(photo.getProfilePhotoUrl()).transform(transformation).into(viewHolder.profilePhoto);
        Picasso.with(getContext()).load(photo.getImageUrl()).into(viewHolder.photo);
        // Return the completed view to render on screen
        return convertView;
    }
}
