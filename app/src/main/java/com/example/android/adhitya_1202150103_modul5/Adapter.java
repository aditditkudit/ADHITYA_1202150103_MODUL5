package com.example.android.adhitya_1202150103_modul5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Tavs on 25/03/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.holder> {
    //Membuat inisiasi varibel yang akan digunakan disaat membuat RecylerView
private Context cntxs;
private List<Model> list;
int color;

    public Adapter(Context cntxs, List<Model> list, int color) {
        this.cntxs = cntxs;
        this.list = list;
        this.color = color;
    }

    class holder extends RecyclerView.ViewHolder{
        //Deklarasi Variabel yang akan di gunakan layout RecyclerView
        public TextView TODO, DESKRIP, PRIOR;
        public CardView crv;
        public holder(View itemView){
            super(itemView);
            TODO = itemView.findViewById(R.id.toDo);
            DESKRIP = itemView.findViewById(R.id.desc);
            PRIOR = itemView.findViewById(R.id.number);
            crv = itemView.findViewById(R.id.cardlist);
        }
    }

    @Override
    public Adapter.holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cntxs).inflate(R.layout.listcardview, parent, false);
        holder hldr = new holder(view);
        return hldr;
    }

    @Override
    public void onBindViewHolder(Adapter.holder holder, int position) {
        Model model = list.get(position);
        holder.TODO.setText(model.getToDo());
        holder.DESKRIP.setText(model.getDesk());
        holder.PRIOR.setText(model.getPrior());
        holder.crv.setCardBackgroundColor(cntxs.getResources().getColor(this.color));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Model getModel(int position){
        return list.get(position);
    }

    public void deleteData(int i){
        list.remove(i);
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, list.size());
    }

}
