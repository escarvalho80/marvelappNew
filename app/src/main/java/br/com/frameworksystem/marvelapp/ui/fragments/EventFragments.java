package br.com.frameworksystem.marvelapp.ui.fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import br.com.frameworksystem.marvelapp.R;
import br.com.frameworksystem.marvelapp.api.EventApi;
import br.com.frameworksystem.marvelapp.model.Event;
import br.com.frameworksystem.marvelapp.service.IMarvelappService;
import br.com.frameworksystem.marvelapp.service.MarvelappService;
import br.com.frameworksystem.marvelapp.ui.adapter.EventAdapter;

/**
 * Created by User on 27/06/2016.
 */
public class EventFragments extends Fragment {

    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;

    private IMarvelappService service;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            service = ((MarvelappService.MarvelappServiceBinder)iBinder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            service = null;
        }
    };

    private void connectService (){
        Intent intent = new Intent(getActivity(), MarvelappService.class);
//        startActivity(intent);
        getActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStart() {
        super.onStart();
        connectService();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        service.finalizar();
    }

    public static Fragment newInstancia() {

        return new EventFragments();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView = (RecyclerView) view.findViewById(R.id.fragment);
        recyclerView.setLayoutManager(layoutManager);

        getEvents();



    }

    private void getEvents() {

        final EventApi eventApi = new EventApi(getActivity());
        eventApi.events(new EventApi.OnEventsListener() {
            @Override
            public void onEvents(final List<Event> events, int errorCode) {
                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        if (events != null){
                            // eventAdapter = new EventAdapter(getActivity(), Mock.getEvents(), recyclerView);
                            eventAdapter = new EventAdapter(getActivity(), events, recyclerView);
                            recyclerView.setAdapter(eventAdapter);
                        }else {
                            Toast.makeText(getActivity(), R.string.msg_erro_generic, Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
