package com.example.api_crud_mock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    Context ctx;
    int layoutItem;
    ArrayList<User> arrayList;

    public UserAdapter(Context ctx, int layoutItem, ArrayList<User> arrayList) {
        this.ctx = ctx;
        this.layoutItem = layoutItem;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(ctx).inflate(layoutItem, viewGroup, false);

        TextView txtId = view.findViewById(R.id.txtId);
        TextView txtMssv = view.findViewById(R.id.txtLMssv);
        TextView txtName = view.findViewById(R.id.txtName);
        TextView txtEmail= view.findViewById(R.id.txtEmail);

        txtId.setText(arrayList.get(i).getId());
        txtName.setText(arrayList.get(i).getNAME());
        txtEmail.setText(arrayList.get(i).getEMAIL());
        txtMssv.setText(arrayList.get(i).getMSSV());

        return view;
    }

}
