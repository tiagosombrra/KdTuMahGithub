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
 * Classe adapter para a activity RepositoryProfile
 */
public class RepositoryArrayAdapter extends ArrayAdapter<Repository> {
    public RepositoryArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public RepositoryArrayAdapter(Context context, int resource, List<Repository> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.repository_list_row, null);
        }

        Repository repository = getItem(position);

        if (repository != null) {
            TextView repositoryName = (TextView) convertView.findViewById(R.id.repository_name_text);
            TextView repositoryOwnerName = (TextView) convertView.findViewById(R.id.repository_owner_name_text);
            repositoryName.setText(repository.getName());
            repositoryOwnerName.setText(repository.getOwnerName());
        }

        return convertView;
    }
}
