package com.example.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private LayoutInflater inflater;
    private List<GetData> getData;

    Adapter(Context context, List<GetData> getData){
        this.getData = getData;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final GetData data = getData.get(position);
        holder.tvId.setText(String.format("%s", data.getId()));
        holder.tvTitle.setText(data.getTitle());
        holder.tvActualTime.setText(String.format("%s", data.getActualTime()));
        holder.tvStatus.setText(data.getStatus());
        holder.tvLocation.setText(data.getLocation());
    }

    @Override
    public int getItemCount() {
        return getData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvId, tvTitle, tvActualTime, tvStatus, tvLocation;
        ViewHolder(View view){
            super(view);
            tvId = view.findViewById(R.id.tvId);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvActualTime = view.findViewById(R.id.tvActualTime);
            tvStatus = view.findViewById(R.id.tvStatus);
            tvLocation = view.findViewById(R.id.tvLocation);
        }
    }
}
