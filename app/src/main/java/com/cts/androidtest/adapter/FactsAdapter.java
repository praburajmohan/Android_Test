package com.cts.androidtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cts.androidtest.R;
import com.cts.androidtest.model.FactsBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FactsAdapter extends BaseAdapter {
    private ArrayList<FactsBean> myFactList;
    private LayoutInflater inflater;
    private Context context;

    public FactsAdapter(Context context, ArrayList<FactsBean> myFactList) {
        this.myFactList = myFactList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setAdapter (ArrayList<FactsBean> factsBeanArrayList){
        myFactList = factsBeanArrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(myFactList == null)
            return 0;
        else
            return myFactList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_details, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        FactsBean factsBean = myFactList.get(position);

        mViewHolder.factTitle.setText(factsBean.getTitle());
        mViewHolder.factDescription.setText(factsBean.getDescription());
        Picasso.with(context).load(factsBean.getImageURL()).into(mViewHolder.factImage);

        return convertView;
    }

    private class MyViewHolder {
        TextView factTitle, factDescription;
        ImageView factImage;

        public MyViewHolder(View view) {
            factTitle = (TextView) view.findViewById(R.id.title);
            factDescription = (TextView) view.findViewById(R.id.description);
            factImage = (ImageView) view.findViewById(R.id.image);
        }
    }
}
