package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.stefandy_2201789536.DatabaseHelper;
import com.example.stefandy_2201789536.DetailActivity;
import com.example.stefandy_2201789536.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import model.Film;

public class SearchViewAdapter extends RecyclerView.Adapter<SearchViewAdapter.MovieViewHolder> implements PopupMenu.OnMenuItemClickListener {

    private String savedImagePath = null;
    private String title,year,id;
    DatabaseHelper db;
    private Context mContext;
    private List<Film> mData;
    RequestOptions option;
    String url;

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
        db = new DatabaseHelper(mContext);
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
                title = mData.get(position).getTitle();
                year = mData.get(position).getYear();
                id = mData.get(position).getId();
                url = mData.get(position).getImage_url();
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
                Glide.with(mContext)
                        .asBitmap()
                        .load(url)
                        .into(new CustomTarget<Bitmap>(400,400) {

                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                saveImage(resource);
                                db.AddMovie(title,year,id,savedImagePath);
                            }

                            @Override
                            public void onLoadCleared(@Nullable Drawable placeholder) {

                            }
                        });

                return true;
            default:
                return false;
        }
    }

    private String saveImage(Bitmap image) {

        String imageFileName = "JPEG_" + title + ".jpg";
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                + "/MOVIE");
        boolean success = true;
        if (!storageDir.exists()) {
            success = storageDir.mkdirs();
        }
        if (success) {
            File imageFile = new File(storageDir, imageFileName);
            savedImagePath = imageFile.getAbsolutePath();
            try {
                OutputStream fOut = new FileOutputStream(imageFile);
                image.compress(Bitmap.CompressFormat.JPEG, 400, fOut);
                fOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            galleryAddPic(savedImagePath);
        }
        return savedImagePath;
    }

    private void galleryAddPic(String imagePath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(imagePath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        mContext.sendBroadcast(mediaScanIntent);
    }
}
