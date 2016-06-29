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
import br.com.frameworksystem.marvelapp.model.Character;
import br.com.frameworksystem.marvelapp.ui.activities.CharacterDetailActivity;

/**
 * Created by User on 23/06/2016.
 */
public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {

    private Context context;
    private List<Character> characters;
    private RecyclerView recyclerView;

    public CharacterAdapter(Context context, List<Character> characters, RecyclerView recyclerView) {
        this.context = context;
        this.characters = characters;
        this.recyclerView = recyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_character, null);

        CharacterAdapter.ViewHolder viewHolder = new CharacterAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Character character = characters.get(position);
        holder.textView.setText(character.getName());
        holder.characterDescricao.setText(character.getDescription());

        Picasso.with(context).load(character.getThumbnailUrl()).centerCrop().resize(400, 400).into(holder.characterImage);

    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView characterImage;
        TextView textView;
        TextView characterDescricao;

        public ViewHolder(View itemView) {

            super(itemView);

            characterImage = (ImageView) itemView.findViewById(R.id.character_imagem);
            textView = (TextView) itemView.findViewById(R.id.character_titulo);
            characterDescricao = (TextView) itemView.findViewById(R.id.character_descricao);

            itemView.setOnClickListener(onClickListener);
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int posicao = recyclerView.getChildAdapterPosition(view);

                Character character = characters.get(posicao);

                Intent intent = new Intent(context, CharacterDetailActivity.class);
                intent.putExtra("character", character);
                context.startActivity(intent);

            }
        };
    }
}