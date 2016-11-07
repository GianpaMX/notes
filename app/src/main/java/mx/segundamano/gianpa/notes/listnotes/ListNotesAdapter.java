package mx.segundamano.gianpa.notes.listnotes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mx.segundamano.gianpa.notes.NoteViewModel;
import mx.segundamano.gianpa.notes.R;

public class ListNotesAdapter extends RecyclerView.Adapter<ListNotesAdapter.ViewHolder> {
    List<NoteViewModel> noteViewModelList;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_notes_row, parent, false);
        return new ViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.titleTextView.setText(noteViewModelList.get(position).title);
    }

    @Override
    public int getItemCount() {
        return hasViewModelList() ? noteViewModelList.size() : 0;
    }

    private boolean hasViewModelList() {
        return noteViewModelList != null;
    }

    public void replace(List<NoteViewModel> noteViewModelList) {
        this.noteViewModelList = noteViewModelList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;

        public ViewHolder(TextView itemView) {
            super(itemView);
            titleTextView = itemView;
        }
    }
}
