package br.com.frameworksystem.marvelapp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.frameworksystem.marvelapp.R;
import br.com.frameworksystem.marvelapp.model.Comic;
import br.com.frameworksystem.marvelapp.model.MarvelImage;
import br.com.frameworksystem.marvelapp.ui.activities.ComicDetailActivity;

/**
 * Created by fo050220 on 30/06/2016.
 */
public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ViewHolder> {

    private Context context;
    private List<Comic> comics;
    private RecyclerView recyclerView;

    public ComicAdapter(Context context, List<Comic> comics, RecyclerView recyclerView) {
        this.context = context;
        this.comics = comics;
        this.recyclerView = recyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_comic, null);

        ComicAdapter.ViewHolder viewHolder = new ComicAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comic comic = comics.get(position);
        holder.comicName.setText(comic.getTitle());
        Picasso.with(context).load(comic.getThumbnail().getImageUrl(MarvelImage.Size.DETAIL)).centerCrop().resize(400, 400).into(holder.comicImg);
    }

    @Override
    public int getItemCount() {
        return comics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView comicImg;
        TextView comicName;

        public ViewHolder(View itemView) {
            super(itemView);

            comicImg = (ImageView) itemView.findViewById(R.id.comic_img);
            comicName = (TextView) itemView.findViewById(R.id.comic_per);

            itemView.setOnClickListener(onClickListener);

        }

        View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                int posicao = recyclerView.getChildAdapterPosition(view);
                Comic comic = comics.get(posicao);

                Intent intent = new Intent(context, ComicDetailActivity.class);
                intent.putExtra("comic", comic);
                context.startActivity(intent);
            }
        };
    }
}
