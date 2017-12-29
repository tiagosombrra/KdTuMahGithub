package br.org.inec.kdtumahgithub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import br.org.inec.kdtumahgithub.R;
import br.org.inec.kdtumahgithub.data.Repository;

import java.util.List;

/**
 * Classe adapter para a activity UserRepositories
 */
public class UserRepositoriesArrayAdapter extends ArrayAdapter<Repository> {
    public UserRepositoriesArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public UserRepositoriesArrayAdapter(Context context, int resource, List<Repository> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_repositories_list_row, null);
        }

        Repository repository = getItem(position);

        if (repository != null) {
            TextView userRepositoryName = (TextView) convertView.findViewById(R.id.user_repository_name_text);
            userRepositoryName.setText(repository.getName());
        }

        return convertView;
    }
}
