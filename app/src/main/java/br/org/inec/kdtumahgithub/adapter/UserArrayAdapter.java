package br.org.inec.kdtumahgithub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import br.org.inec.kdtumahgithub.R;
import br.org.inec.kdtumahgithub.data.User;

import java.util.List;

/**
 * Classe adapter para a activity UserSearch
 */
public class UserArrayAdapter extends ArrayAdapter<User> {
    public UserArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public UserArrayAdapter(Context context, int resource, List<User> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_list_row, null);
        }

        User user = getItem(position);

        if (user != null) {
            ImageView userAvatar = (ImageView) convertView.findViewById(R.id.user_profile_avatar);
            TextView userLogin = (TextView) convertView.findViewById(R.id.user_login);
            userLogin.setText(user.getLogin());
            Glide.with(getContext())
                    .load(user.getAvatarUrl())
                    .override(200, 200)
                    .centerCrop()
                    .into(userAvatar);
        }

        return convertView;
    }
}
