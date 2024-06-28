package com.example.addproductapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.addproductapp.EditorActivity;
import com.example.addproductapp.R;
import com.example.addproductapp.helper.Helper;
import com.example.addproductapp.model.Data;

import java.util.List;

public class Adapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> lists;
    private Helper db;

    public Adapter(Activity activity, List<Data> lists) {
        this.activity = activity;
        this.lists = lists;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.db = new Helper(activity);
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_product, parent, false);
        }

        TextView nama = convertView.findViewById(R.id.text_nama);
        TextView jenis = convertView.findViewById(R.id.text_jenis);
        TextView harga = convertView.findViewById(R.id.text_harga);
        ImageButton btnEdit = convertView.findViewById(R.id.btn_edit);
        ImageButton btnDelete = convertView.findViewById(R.id.btn_delete);

        final Data data = lists.get(position);
        nama.setText(data.getNama());
        jenis.setText(data.getJenis());
        harga.setText(String.valueOf(data.getHarga()));

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, EditorActivity.class);
                intent.putExtra("id", data.getId());
                intent.putExtra("nama", data.getNama());
                intent.putExtra("jenis", data.getJenis());
                intent.putExtra("harga", data.getHarga());
                activity.startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.delete(Integer.parseInt(data.getId()));
                lists.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
