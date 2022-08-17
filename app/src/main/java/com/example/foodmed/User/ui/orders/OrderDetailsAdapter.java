package com.example.foodmed.User.ui.orders;





import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.foodmed.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.MyViewHolder>{

    private Context mCtx;
    private static final int REQUEST_CALL = 1;
    private ArrayList<OrderDetailsModel> categoryList;

    private FragmentManager fragmentManager;
    ProgressDialog progressDialog;
    AlertDialog alertDialog;
    StorageReference photoRef;
    DatabaseReference ref;
    Query applesQuery;
    DatabaseReference databaseReference;

    FirebaseDatabase database;
    private FirebaseAuth mAuth;
    Integer quantity;

    public OrderDetailsAdapter(Activity activity , ArrayList <OrderDetailsModel> categoryList,ProgressDialog progressDialog, DatabaseReference databaseReference, FirebaseDatabase database,FragmentManager fragmentManager) {
        this.mCtx = activity;
        this.categoryList = categoryList;
        this.progressDialog=progressDialog;
        this.databaseReference=databaseReference;
        this.database=database;
        this.fragmentManager=fragmentManager;

    }


    @NonNull
    @Override
    public OrderDetailsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from ( mCtx );
        View view = inflater.inflate ( R.layout.order_details_list , null );
        return new OrderDetailsAdapter.MyViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailsAdapter.MyViewHolder holder, int position) {

        progressDialog.dismiss();


        final OrderDetailsModel category = categoryList.get(position);


holder.name.setText(category.getName());
holder.price.setText(category.getPrice());
holder.quantity.setText(category.getQuantity());


        Glide.with(mCtx)
                .load(category.getImage())
                .override(100, Target.SIZE_ORIGINAL)
                .fitCenter()
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return categoryList.size() ;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView quantity,price,name;
        ImageView image;





        public MyViewHolder(View itemView) {
            super(itemView);


            price = itemView.findViewById(R.id.price);

            quantity = itemView.findViewById(R.id.quantity);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);



        }
    }


}