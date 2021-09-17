package com.example.loadinglayout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.library.LoadingLayout;


public class LoadingFragment extends Fragment {


    private RecyclerView recycler;
    private LoadingLayout loadingLaout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loading, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

        initLoad();
    }

    private void initLoad() {

        loadingLaout.showLoading();
        new Thread() {

            @Override
            public void run() {
                super.run();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        loadingLaout.showLoadSuccess();
//                        loadingLaout.showLoadFailed();
                        loadingLaout.showLoadEmpty();
                        recycler.setAdapter(new RecyclerView.Adapter<VH>() {
                            @NonNull
                            @Override
                            public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                View root = LayoutInflater.from(getActivity()).inflate(R.layout.recy_item, parent, false);
                                return new VH(root);
                            }

                            @Override
                            public void onBindViewHolder(@NonNull VH holder, int position) {


                                TextView name = holder.itemView.findViewById(R.id.name);
                                name.setText("hello" + position);
                            }

                            @Override
                            public int getItemCount() {
                                return 5;
                            }
                        });
                    }
                });

            }
        }.start();

    }

    private void initView() {
        recycler = (RecyclerView) getView().findViewById(R.id.recyclerview);
        loadingLaout = (LoadingLayout) getView().findViewById(R.id.loadingLayout);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadingLaout.setLoadingText("loading--xxx",android.R.color.holo_green_light,22f)
                .setBgColor(android.R.color.white);

    }

    class VH extends RecyclerView.ViewHolder {

        public TextView title;

        public VH(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.name);
        }
    }
}