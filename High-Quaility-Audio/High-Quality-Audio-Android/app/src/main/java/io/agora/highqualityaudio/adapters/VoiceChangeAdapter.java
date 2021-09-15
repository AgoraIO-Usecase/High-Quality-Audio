package io.agora.highqualityaudio.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.agora.highqualityaudio.R;
import io.agora.highqualityaudio.data.ChangeVoiceItem;

public class VoiceChangeAdapter extends RecyclerView.Adapter<VoiceChangeAdapter.ViewHolder> {
    private final LayoutInflater mInflater;

    private List<ChangeVoiceItem> mChangeVoiceItems;

    public VoiceChangeAdapter(Context context, @ArrayRes int resId) {
        this.mInflater = LayoutInflater.from(context);
        initVoiceItems(context, resId);
    }

    private void initVoiceItems(Context context, @ArrayRes int resId) {
        String[] titles = context.getResources().getStringArray(resId);
        mChangeVoiceItems = new ArrayList<>(titles.length);
        for (String title : titles) {
            mChangeVoiceItems.add(new ChangeVoiceItem(title));
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.change_voice_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ChangeVoiceItem item = mChangeVoiceItems.get(position);
        holder.option.setText(item.getTitle());
        holder.option.setSelected(item.isSelected());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectedPosition(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mChangeVoiceItems.size();
    }

    public void setSelectedPosition(int position) {
        int selected = getSelectedPosition();
        if (selected>=0 && selected < mChangeVoiceItems.size()) {
            mChangeVoiceItems.get(selected).setSelected(false);
            notifyItemChanged(selected);
        }
        mChangeVoiceItems.get(position).setSelected(true);
        notifyItemChanged(position);
    }

    public int getSelectedPosition() {
        int position = -1;
        for (int i = 0; i < mChangeVoiceItems.size(); i++) {
            if (mChangeVoiceItems.get(i).isSelected()) {
                position = i;
                break;
            }
        }

        return position;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView option;

        ViewHolder(View view) {
            super(view);
            option = view.findViewById(R.id.change_voice_item);
        }
    }
}
