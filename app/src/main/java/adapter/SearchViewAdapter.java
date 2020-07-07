package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.stefandy_2201789536.DetailActivity;
import com.example.stefandy_2201789536.R;

import java.util.List;

import model.Film;

public class SearchViewAdapter extends RecyclerView.Adapter<SearchViewAdapter.MovieViewHolder> implements PopupMenu.OnMenuItemClickListener {

    private Context mContext;
    private List<Film> mData;
    RequestOptions option;

    public SearchViewAdapter(Context context, List<Film> mData) {
        this.mContext = context;
        this.mData = mData;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_foreground);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.item_layout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {
        holder.tv_title.setText(mData.get(position).getTitle());
        Glide.with(mContext).load(mData.get(position).getImage_url()).apply(option).into(holder.iv_thumbnail);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("Title",mData.get(position).getTitle());
                intent.putExtra("Year",mData.get(position).getYear());
                intent.putExtra("Id",mData.get(position).getId());
                intent.putExtra("Image",mData.get(position).getImage_url());
                mContext.startActivity(intent);
            }
        });

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showPopup(v);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title;
        private ImageView iv_thumbnail;
        CardView cardView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = (TextView)itemView.findViewById(R.id.tv_item);
            iv_thumbnail = (ImageView)itemView.findViewById(R.id.iv_item);
            cardView = (CardView)itemView.findViewById(R.id.cardview_id);
        }
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(mContext, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.add_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                Toast.makeText(mContext,"clicked",Toast.LENGTH_SHORT).show();
                //save to savedfragment
                return true;
            default:
                return false;
        }
    }
}
