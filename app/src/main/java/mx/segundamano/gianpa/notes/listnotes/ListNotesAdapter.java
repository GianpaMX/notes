package mx.segundamano.gianpa.notes.listnotes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mx.segundamano.gianpa.notes.NoteViewModel;
import mx.segundamano.gianpa.notes.R;
import mx.segundamano.gianpa.notes.addeditnote.AddEditNoteActivity;

public class ListNotesAdapter extends RecyclerView.Adapter<ListNotesAdapter.ViewHolder> {
    private Context context;
    private List<NoteViewModel> noteViewModelList;

    public ListNotesAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_notes_row, parent, false);
        textView.setOnClickListener(onClickListener);
        return new ViewHolder(textView);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int position = (int) view.getTag();
            Intent intent = new Intent(context, AddEditNoteActivity.class);
            intent.putExtra(AddEditNoteActivity.NOTE_VIEW_MODEL, noteViewModelList.get(position));
            context.startActivity(intent);
        }
    };

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.titleTextView.setText(noteViewModelList.get(position).title);
        holder.titleTextView.setTag(position);
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
