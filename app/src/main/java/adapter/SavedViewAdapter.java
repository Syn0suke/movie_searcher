package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
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

import com.bumptech.glide.request.RequestOptions;
import com.example.stefandy_2201789536.DeleteDetail;
import com.example.stefandy_2201789536.MainActivity;
import com.example.stefandy_2201789536.R;

import java.io.File;
import java.util.List;

import helper.DatabaseHelper;
import model.Film;

public class SavedViewAdapter extends RecyclerView.Adapter<SavedViewAdapter.SavedViewHolder> implements PopupMenu.OnMenuItemClickListener {

    private String judul,link;
    DatabaseHelper db;
    private Context mContext;
    private List<Film> mData;
    RequestOptions option;

    public SavedViewAdapter(Context context, List<Film> mData) {
        this.mContext = context;
        this.mData = mData;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_foreground);
    }

    @NonNull
    @Override
    public SavedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        db = new DatabaseHelper(mContext);
        view = inflater.inflate(R.layout.item_layout, parent, false);
        return new SavedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedViewHolder holder, final int position) {
        holder.tv_title.setText(mData.get(position).getTitle());
        holder.iv_thumbnail.setImageBitmap(BitmapFactory.decodeFile(mData.get(position).getImage_url()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DeleteDetail.class);
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
                Film pos = mData.get(position);
                judul = pos.getTitle();
                link = pos.getImage_url();
                showPopup(v);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class SavedViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title;
        private ImageView iv_thumbnail;
        private CardView cardView;

        public SavedViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = (TextView)itemView.findViewById(R.id.tv_item);
            iv_thumbnail = (ImageView)itemView.findViewById(R.id.iv_item);
            cardView = (CardView)itemView.findViewById(R.id.cardview_id);
        }
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(mContext, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.delete_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete:
                db.Delete(judul);
                File file = new File(link);
                file.delete();
                Toast.makeText(mContext,"Delete Successful",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, MainActivity.class);
                mContext.startActivity(intent);
                return true;
            default:
                return false;
        }
    }
}
