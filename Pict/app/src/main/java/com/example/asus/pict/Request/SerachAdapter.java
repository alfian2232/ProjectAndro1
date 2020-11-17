package com.example.asus.pict.Request;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.asus.pict.R;

import java.util.ArrayList;
import java.util.List;

public class SerachAdapter extends BaseAdapter implements Filterable {
    private List<SearchResponse>originalData = null;
    private List<SearchResponse> filteredData = null;
    private LayoutInflater mInflater;
    private ItemFilter mFilter = new ItemFilter();

    public SerachAdapter(Context context, List<SearchResponse> data) {
        this.filteredData = data ;
        this.originalData = data ;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return filteredData.size();
    }

    public Object getItem(int position) {
        return filteredData.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // A ViewHolder keeps references to children views to avoid unnecessary calls
        // to findViewById() on each row.
        ViewHolder holder;

        // When convertView is not null, we can reuse it directly, there is no need
        // to reinflate it. We only inflate a new View when the convertView supplied
        // by ListView is null.
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_list, null);

            // Creates a ViewHolder and store references to the two children views
            // we want to bind data to.
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.tv_title);

            // Bind the data efficiently with the holder.

            convertView.setTag(holder);
        } else {
            // Get the ViewHolder back to get fast access to the TextView
            // and the ImageView.
            holder = (ViewHolder) convertView.getTag();
        }

        // If weren't re-ordering this you could rely on what you set last time
        holder.text.setText(filteredData.get(position).getUsername());

        return convertView;
    }

    static class ViewHolder {
        TextView text;
    }

    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<SearchResponse> list = originalData;

            int count = list.size();
            final ArrayList<SearchResponse> nlist = new ArrayList<SearchResponse>(count);

            String filterableString;

            for (SearchResponse data : nlist) {
                filterableString = data.getUsername().toLowerCase();
                if (filterableString.contains(filterString)) {
                    nlist.add(data);
                }
            }

//            for (int i = 0; i < count; i++) {
//                filterableString = list.get(i).getUsername();
//                if (filterableString.toLowerCase().contains(filterString)) {
//                    nlist.add(filterableString);
//                }
//            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<SearchResponse>) results.values;
            notifyDataSetChanged();
        }
    }
}
