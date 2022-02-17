package com.example.prschedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<Model> schedule;
    public Adapter(Context context, List<Model> model){
        this.schedule = model;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model model = schedule.get(position);
        holder.title.setText(model.getTitle());
        holder.nameZnam1.setText(model.getNameZnam1());
        if (model.getNameDel1() == null)
        {
            holder.nameDel1.setVisibility(View.GONE);
        }
        else {
            holder.nameDel1.setText(model.getNameDel1());
            holder.nameDel1.setVisibility(View.VISIBLE);
        }
        holder.nameZnam2.setText(model.getNameZnam2());
        if (model.getNameDel2() == null)
        {
            holder.nameDel2.setVisibility(View.GONE);
        }
        else {
            holder.nameDel2.setText(model.getNameDel2());
            holder.nameDel2.setVisibility(View.VISIBLE);
        }
        holder.nameZnam3.setText(model.getNameZnam3());
        if (model.getNameDel3() == null)
        {
            holder.nameDel3.setVisibility(View.GONE);
        }
        else {
            holder.nameDel3.setText(model.getNameDel3());
            holder.nameDel3.setVisibility(View.VISIBLE);
        }
        holder.nameZnam4.setText(model.getNameZnam4());
        if (model.getNameDel4() == null)
        {
            holder.nameDel4.setVisibility(View.GONE);
        }
        else {
            holder.nameDel4.setText(model.getNameDel4());
            holder.nameDel4.setVisibility(View.VISIBLE);
        }
        holder.nameZnam5.setText(model.getNameZnam5());
        if (model.getNameDel5() == null)
        {
            holder.nameDel5.setVisibility(View.GONE);
        }
        else {
            holder.nameDel5.setText(model.getNameDel5());
            holder.nameDel5.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return schedule.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        final TextView title;
        final TextView nameZnam1;
        final TextView nameDel1;
        final TextView nameZnam2;
        final TextView nameDel2;
        final TextView nameZnam3;
        final TextView nameDel3;
        final TextView nameZnam4;
        final TextView nameDel4;
        final TextView nameZnam5;
        final TextView nameDel5;

        ViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.title);
           nameZnam1 = view.findViewById(R.id.Znam1);
           nameDel1 = view.findViewById(R.id.Del1);
            nameZnam2 = view.findViewById(R.id.Znam2);
            nameDel2 = view.findViewById(R.id.Del2);
            nameZnam3 = view.findViewById(R.id.Znam3);
            nameDel3 = view.findViewById(R.id.Del3);
            nameZnam4 = view.findViewById(R.id.Znam4);
            nameDel4 = view.findViewById(R.id.Del4);
            nameZnam5 = view.findViewById(R.id.Znam5);
            nameDel5 = view.findViewById(R.id.Del5);
        }
    }
}

