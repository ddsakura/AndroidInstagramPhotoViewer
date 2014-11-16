
package com.cire.instagramphotoviewer;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cire.instagramphotoviewer.modal.InstagramPhoto;
import com.cire.instagramphotoviewer.ui.CircledImageView;
import com.cire.instagramphotoviewer.ui.SquareImageView;
import com.squareup.picasso.Picasso;

public class InstagramPhotoAdapter extends ArrayAdapter<InstagramPhoto> {

    public InstagramPhotoAdapter(Context context, List<InstagramPhoto> photos) {
        super(context, 0, photos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InstagramPhoto photo = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
        }

        SquareImageView ivPhoto = (SquareImageView)convertView.findViewById(R.id.ivPhoto);
        ivPhoto.setImageResource(0);
        Picasso.with(getContext()).load(photo.getImages().getStandard_resolution().getUrl()).placeholder(R.drawable.placeholder).into(ivPhoto);

        CircledImageView ivProfile = (CircledImageView)convertView.findViewById(R.id.ivProfile);
        ivProfile.setImageResource(0);
        Picasso.with(getContext()).load(photo.getUser().getProfile_picture()).into(ivProfile);

        TextView tvUsername = (TextView)convertView.findViewById(R.id.tvUsername);
        tvUsername.setText(photo.getUser().getUsername());

        TextView tvLike = (TextView)convertView.findViewById(R.id.tvLike);
        tvLike.setText(String.valueOf(photo.getLikes().getCount()));

        if (photo.getCaption() != null) {
            TextView tvCaption = (TextView)convertView.findViewById(R.id.tvCaption);
            tvCaption.setText(photo.getCaption().getText());
        }

        return convertView;
    }
}
