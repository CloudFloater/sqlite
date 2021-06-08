package com.example.sqlite;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by lw on 2017/4/14.
 */

public class MedicineAdapter extends ArrayAdapter<Medicine> implements View.OnClickListener {
    private final int resourceId;
    Context context;
    private InnerItemOnclickListener mListener;
    public MedicineAdapter(Context context, int textViewResourceId, List<Medicine> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Medicine medicine = getItem(position);
        View view ;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.medicine_item, null);
            viewHolder = new ViewHolder();
            viewHolder.medicineName = (TextView) view.findViewById(R.id.medicine_name);
            viewHolder.medicineFamily_name = (TextView) view.findViewById(R.id.medicine_family_name);
            viewHolder.button = (Button)view.findViewById(R.id.button_edit);
            viewHolder.button1 =(Button)view.findViewById(R.id.button_delete);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.medicineName.setText(medicine.getName());
        viewHolder.medicineFamily_name.setText(medicine.getFamily_name());
        viewHolder.button1.setText("Delete");
        viewHolder.button.setOnClickListener(this);
        viewHolder.button1.setOnClickListener(this);
        viewHolder.button.setTag(position);
        viewHolder.button1.setTag(position);
        viewHolder.button.setText("Edit");

        return view;
    }
    class ViewHolder{
        TextView medicineName;
        TextView medicineFamily_name;
        Button button;
        Button button1;
    }
    interface InnerItemOnclickListener {
        void itemClick(View v);
    }

    public void setOnInnerItemOnClickListener(InnerItemOnclickListener listener){
        this.mListener=listener;
    }

    @Override
    public void onClick(View v) {
        mListener.itemClick(v);
    }
}
