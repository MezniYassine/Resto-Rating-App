package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyViewHolder> {

    private  Context context ;
    private ArrayList review_id, res_name,adresse, res_rating,avis ;

    RestaurantAdapter(Context context, ArrayList review_id, ArrayList res_name, ArrayList adresse, ArrayList res_rating, ArrayList avis){
        this.context = context;
        this.review_id = review_id;
        this.res_name = res_name;
        this.adresse=adresse;
        this.avis=avis;
        this.res_rating = res_rating;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.liste_restaurant, parent, false);
        return new MyViewHolder(view);
    }
    public void updateDataSet(ArrayList<String> newReviewIds, ArrayList<String> newResNames, ArrayList<String> newAdresses, ArrayList<String> newRatings, ArrayList<String> newAvis) {
        review_id.clear();
        review_id.addAll(newReviewIds);

        res_name.clear();
        res_name.addAll(newResNames);

        adresse.clear();
        adresse.addAll(newAdresses);

        res_rating.clear();
        res_rating.addAll(newRatings);

        avis.clear();
        avis.addAll(newAvis);

        notifyDataSetChanged();
    }
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Bind the data to the views here
        holder.id.setText(String.valueOf(review_id.get(position))); // bind review_id
        holder.nomResto.setText(String.valueOf(res_name.get(position))); // bind res_name
        holder.adresse.setText(String.valueOf(adresse.get(position))); // bind adresse
        holder.avis.setText(String.valueOf(avis.get(position))); // bind avis
        holder.rating.setText(String.valueOf(res_rating.get(position))); // bind res_rating
    }

    @Override
    public int getItemCount() {
        return review_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id, nomResto, adresse, avis, rating;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.review_id_txt); // ID for review
            nomResto = itemView.findViewById(R.id.nomResto); // Name of the restaurant
            adresse = itemView.findViewById(R.id.adresse); // Address
            avis = itemView.findViewById(R.id.Avis); // Review
            rating = itemView.findViewById(R.id.rating); // Rating
        }
    }

}