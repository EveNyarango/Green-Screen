package com.example.greenscreen.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenscreen.R;
import com.example.greenscreen.models.Green;
import com.example.greenscreen.network.GreenApi;
import com.example.greenscreen.network.GreenService;
import com.example.greenscreen.ui.ProfileActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileHolder> implements Filterable {
    private List<Green> profileList;
    private List<Green> profileListed;
    private Context context;

    public void setProfileList(Context context, final List<Green> profileList) {
        this.context = context;
        if(this.profileList == null) {
            this.profileList = profileList;
            this.profileListed = profileList;
            notifyItemChanged(0, profileListed.size());
        }else {
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {

                    return ProfileAdapter.this.profileList.size();
                }

                @Override
                public int getNewListSize() {

                    return profileList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return ProfileAdapter.this.profileList.get(oldItemPosition).getUsername() == profileList.get(newItemPosition).getUsername();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Green newMovie = ProfileAdapter.this.profileList.get(oldItemPosition);
                    Green oldMovie = profileList.get(newItemPosition);
                    return newMovie.getUsername() == oldMovie.getUsername();
                }
            });
            this.profileList = profileList;
            this.profileListed = profileList;
            result.dispatchUpdatesTo(this);
        }
    }


    @NonNull
    @Override
    public ProfileAdapter.ProfileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.green_row, parent, false);
        return new ProfileHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ProfileHolder holder, int position) {
holder.profileUsername.setText("Username" + profileListed.get(position).getUsername());
holder.profileBio.setText("Bio" + profileListed.get(position).getBio());
        Picasso.with(context).load(profileListed.get(position).getImageurl()).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                GreenService greenService = GreenApi.getRetrofitInstance().create(GreenService.class);

                Call<Green> call = greenService.getProfile(profileListed.get(position).getUsername());
                call.enqueue(new Callback<Green>() {
                    @Override
                    public void onResponse(Call<Green> call, Response<Green> response) {

                        Intent intent = new Intent(view.getContext(), ProfileActivity.class);
                        if(response.body() !=null) {
                            intent.putExtra("username", response.body().getUsername());
                            intent.putExtra("bio", response.body().getBio());
                            intent.putExtra("email", response.body().getEmail());
                            intent.putExtra("location", response.body().getLocation());
                            intent.putExtra("image", response.body().getImageurl());
                        }

                        view.getContext().startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Green> call, Throwable t) {
                        Log.d("Error", t.getMessage());

                    }
                });
            }

        });
    }

    @Override
    public int getItemCount() {
        if (profileList !=null) {
            return profileListed.size();
        } else {
            return 0;
        }

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    profileListed = profileList;
                }else {
                    List<Green> filteredList = new ArrayList<>();
                    for (Green movie : profileList) {
                        if (movie.getUsername().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(movie);
                        }
                    }
                    profileListed = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = profileListed;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charsequence, FilterResults filterResults) {
                profileListed = (ArrayList<Green>) filterResults.values;

                notifyDataSetChanged();
            }
        };
    }

    public class ProfileHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView profileUsername;
        TextView profileBio;
        ImageView image;

        public ProfileHolder(@NonNull View v) {
            super(v);
            cardView = v.findViewById(R.id.cvProfile);
            profileUsername = v.findViewById(R.id.tvUsername);
            profileBio = v.findViewById(R.id.tvBio);
            image = v.findViewById(R.id.ivImageurl);
        }

    }

}
