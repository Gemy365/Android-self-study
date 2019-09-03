package com.example.user.listofcall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class numbersAdapter extends ArrayAdapter<numbers>{
    public numbersAdapter(Context context, ArrayList<numbers> num) {
        super(context,0,num);
    }

    /**
     * From Code > Override Method > getView  Or [Ctrl + O] > getView
     * Use getView Method To Fill The List View By Its Own Items        [IMPORTANT]
     * @param position    Get the position of elements
     * @param convertView Allows to reuse the view to save the size of memory
     * @param parent      Make ListView parent itself
     * @return The View when all is done
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /**
         * Make convert as a View
         * Check if this View is null..[At the first time will be null]
         * Make New View by [R.layout.list_item] this Layout include the texts & image
         * parent > ListView
         * false > No need to make ListView.. I Created one
         */
        View convert = convertView;
        if (convert == null)
            convert = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        // Make currentItem as numbers Class to getItem by Position.
        numbers currentItem = getItem(position);

        // Get Names & don't Forget [convert.].
        TextView names = (TextView) convert.findViewById(R.id.text_for_name);
        names.setText(currentItem.getmNames());

        // Get Nums.
        TextView nums = (TextView) convert.findViewById(R.id.text_for_num);
        nums.setText(currentItem.getmNums());
        return convert;
    }
}
