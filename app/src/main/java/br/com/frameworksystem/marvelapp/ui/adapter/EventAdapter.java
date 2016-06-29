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
import br.com.frameworksystem.marvelapp.model.Event;
import br.com.frameworksystem.marvelapp.ui.activities.EventDetailActivity;

/**
 * Created by User on 27/06/2016.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private Context context;
    private List<Event> events;
    private RecyclerView recyclerView;

    public EventAdapter(Context context, List<Event> events, RecyclerView recyclerView) {
        this.context = context;
        this.events = events;
        this.recyclerView = recyclerView;
    }

    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_event, null);

        EventAdapter.ViewHolder viewHolder = new EventAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventAdapter.ViewHolder holder, int position) {

        Event event = events.get(position);
        holder.eventTitle.setText(event.getTitle());

        Picasso.with(context).load(event.getImgUrl()).centerCrop().resize(400,400).into(holder.eventImg);

    }

    @Override
    public int getItemCount() {

        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView eventImg;
        TextView eventTitle;

        public ViewHolder(View itemView) {
            super(itemView);

            eventImg = (ImageView) itemView.findViewById(R.id.event_img);
            eventTitle = (TextView) itemView.findViewById(R.id.event_title);

            itemView.setOnClickListener(onClickListener);
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int posicao = recyclerView.getChildAdapterPosition(view);
                Event event = events.get(posicao);

                Intent intent = new Intent(context, EventDetailActivity.class);
                intent.putExtra("event", event);
                context.startActivity(intent);

            }
        };
    };
}
