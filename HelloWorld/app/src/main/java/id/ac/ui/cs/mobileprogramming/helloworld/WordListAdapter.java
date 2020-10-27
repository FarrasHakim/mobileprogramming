package id.ac.ui.cs.mobileprogramming.helloworld;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class WordListAdapter extends
        RecyclerView.Adapter<WordListAdapter.WordViewHolder>  {
    public static final String TAG = "WordListAdapterDebugging";
    private List<Word> mWords; // Cached copy of words
    private LayoutInflater mInflater;

    public WordListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.recyclerview_item,
                parent, false);
        return new WordViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {

        if (mWords != null) {
            Word current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Word");
        }
    }

    void setWords(List<Word> words){
        mWords = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mWords!= null ? mWords.size():0;
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        public final TextView wordItemView;
//        final WordListAdapter mAdapter;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }

//        public WordViewHolder(View itemView, WordListAdapter adapter) {
//            super(itemView);
//            wordItemView = itemView.findViewById(R.id.textView);
//            this.mAdapter = adapter;
//            itemView.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View v) {
//            // Get the position of the item that was clicked.
//            int mPosition = getLayoutPosition();
//            // Use that to access the affected item in mWordList.
//            Log.d(TAG, String.valueOf(mPosition));
//            // Change the word in the mWordList.
//            // Notify the adapter, that the data has changed so it can
//            // update the RecyclerView to display the data.
//            mAdapter.notifyDataSetChanged();
//        }
    }
}
