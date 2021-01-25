package com.example.mobil_vize_1;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserAdapterVH> implements Filterable {

    private List<UserResponse> userResponseList;
    private List<UserResponse> userResponseListFiltered;
    private Context context;
    private ClickedItem clickedItem;

    public UsersAdapter(ClickedItem clickedItem){
        this.clickedItem = clickedItem;
        this.userResponseListFiltered = userResponseList;
    }
    public void SetData( List<UserResponse> userResponseList ){
        this.userResponseList = userResponseList;
        this.userResponseListFiltered = userResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        return new UsersAdapter.UserAdapterVH(LayoutInflater.from(context).inflate(R.layout.satir,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterVH holder, int position) {

        UserResponse userResponse = userResponseList.get(position);
        String username = userResponse.getUsername();
        String prefix = null;

        if(userResponse.isIs_active()){
            prefix = username.substring(0,1).toUpperCase();
        }

        holder.kullaniciLogo.setText(prefix);
        holder.kullaniciAdi.setText(username);
        holder.imageMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedItem.ClickedUser(userResponse);
            }
        });


    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if(charSequence == null | charSequence.length() == 0){
                    filterResults.count = userResponseListFiltered.size();
                    filterResults.values = userResponseListFiltered;
                }else{
                    String searchChar = charSequence.toString().toLowerCase();
                    List<UserResponse> resultData = new ArrayList<>();
                    for(UserResponse userResponse: userResponseListFiltered){
                        if(userResponse.getUsername().toLowerCase().contains(searchChar)){
                            resultData.add(userResponse);
                        }
                    }
                    filterResults.count = resultData.size();
                    filterResults.values = resultData;
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                userResponseList = (List<UserResponse>) filterResults.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

    public interface ClickedItem{
        public void ClickedUser(UserResponse userResponse);
    }

    @Override
    public int getItemCount() {
        return userResponseList.size();
    }

    public class UserAdapterVH extends RecyclerView.ViewHolder {
        TextView kullaniciAdi, kullaniciLogo;
        ImageView imageMore;

        public UserAdapterVH(@NonNull View itemView) {
            super(itemView);
            kullaniciAdi = itemView.findViewById(R.id.satir_kullanici_adi);
            kullaniciLogo = itemView.findViewById((R.id.satir_logo));
            imageMore = itemView.findViewById(R.id.imageMore);
        }
    }
}
