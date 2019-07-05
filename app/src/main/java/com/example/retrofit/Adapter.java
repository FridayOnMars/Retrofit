package com.example.retrofit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private LayoutInflater inflater;
    private List<GetData> getData;
    private ItemFromActivity connect;
    @SuppressLint("SimpleDateFormat")
    private final SimpleDateFormat time = new SimpleDateFormat("dd MM yyyy, HH:mm");

    Adapter(Context context, List<GetData> getData){
        this.getData = getData;
        this.inflater = LayoutInflater.from(context);
    }

    public interface ItemFromActivity{
        void getInfoForNewActivity(int id);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        Context context = recyclerView.getContext();
        if(context instanceof ItemFromActivity){
            connect = (ItemFromActivity) context;
        }
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connect.getInfoForNewActivity(data.getId());
            }
        });
        holder.tvTitle.setText(data.getTitle());
        holder.tvActualTime.setText(time.format(data.getActualTime()*1000));
        holder.tvStatus.setText(data.getStatus());
        holder.tvLocation.setText(data.getLocation());
    }

    @Override
    public int getItemCount() {
        return getData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle, tvActualTime, tvStatus, tvLocation;
        ViewHolder(View view){
            super(view);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvActualTime = view.findViewById(R.id.tvActualTime);
            tvStatus = view.findViewById(R.id.tvStatus);
            tvLocation = view.findViewById(R.id.tvLocation);
        }
    }
}
