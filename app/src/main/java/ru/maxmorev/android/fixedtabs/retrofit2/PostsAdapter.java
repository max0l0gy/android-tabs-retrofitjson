package ru.maxmorev.android.fixedtabs.retrofit2;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.rick.a1402retrofitjson.R;
import com.example.rick.a1402retrofitjson.databinding.ListItemBinding;

import java.util.ArrayList;
import java.util.List;


public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private static final String TAG = "PostsAdapter";

    private LayoutInflater mInflater;
    private List<Post> mPosts = new ArrayList<>();

    public  PostsAdapter(LayoutInflater inflater){
        this.mInflater = inflater;
    }

    /**
     * Биндим макет итема листа и передаем биндинг в конструктор холдера.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final ListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_item, parent, false);
        return new ViewHolder(binding);

        //return new ViewHolder(mInflater.inflate(android.R.layout.simple_list_item_1, parent, false));
    }

    /**
     * Устанавливаем экземпляр "коммента" в биндер холдера, при этом в макете автоматически применятся данные.
     */
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.binding.setPost(mPosts.get(position));
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public void setPosts(final List<Post> posts) {
        mPosts = posts;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ListItemBinding binding;

        /**
         * Назначаем ссылку на биндер внутрь экземпляра,
         * и устанавливаем некоторые свойства поля text.
         */
        public ViewHolder(@NonNull final ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.text.setMaxLines(3);
            binding.text.setEllipsize(TextUtils.TruncateAt.END);
        }
    }
}