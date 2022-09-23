package com.example.foodmed.User.ui.home;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.foodmed.R;
import com.example.foodmed.User.ui.cart.CartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class UserProductAdapter extends RecyclerView.Adapter<UserProductAdapter.MyViewHolder>{

    private Context mCtx;
    private static final int REQUEST_CALL = 1;
    private ArrayList<UserProductsModel> categoryList;

    private FragmentManager fragmentManager;
    ProgressDialog progressDialog;
    AlertDialog alertDialog;
    StorageReference photoRef;
    DatabaseReference ref;
    Query applesQuery;
    DatabaseReference databaseReference;

    FirebaseDatabase database;
    private FirebaseAuth mAuth;

    public UserProductAdapter(Activity activity , ArrayList <UserProductsModel> categoryList,ProgressDialog progressDialog,    DatabaseReference databaseReference, FirebaseDatabase database) {
        this.mCtx = activity;
        this.categoryList = categoryList;
        this.progressDialog=progressDialog;
        this.databaseReference=databaseReference;
this.database=database;

    }


    @NonNull
    @Override
    public UserProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from ( mCtx );
        View view = inflater.inflate ( R.layout.user_product_list , null );
        return new UserProductAdapter.MyViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull UserProductAdapter.MyViewHolder holder, int position) {

        progressDialog.dismiss();


        final UserProductsModel category = categoryList.get(position);



        holder.name.setText(category.getName());
        holder.price.setText(category.getPrice());
        holder.Name=category.getName();
        holder.Price=category.getPrice();
        holder.id=category.getId();
        holder.Image=category.getImage();
        holder.Quantity="1";
        holder.addTocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();


                databaseReference = FirebaseDatabase.getInstance().getReference().child("cart");

                databaseReference.orderByChild("product_id").equalTo(holder.id)
                        .addListenerForSingleValueEvent(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {

                                    Toast.makeText(mCtx, "Product Already in the cart", Toast.LENGTH_LONG).show();
                                    progressDialog.dismiss();
                                } else {
                                    mAuth = FirebaseAuth.getInstance();
                                    FirebaseUser user = mAuth.getCurrentUser();



                                    CartModel model = new CartModel(holder.Image, holder.Name,"",user.getUid(), holder.id, holder.Price, "1");
                                    FirebaseDatabase.getInstance().getReference("cart")
                                            .child(user.getUid()).child(holder.Name).setValue(model)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(mCtx,"Add To Cart Successful",Toast.LENGTH_LONG).show();
                                                    progressDialog.dismiss();

                                                }
                                            });

                                }
                            }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(mCtx, "Internet Error", Toast.LENGTH_LONG).show();
                                    progressDialog.dismiss();
                            };
                        });

            }
        });

//        holder.favimg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                //adding into my favorites table in DB
//                holder.favimg.setBackgroundResource(fav_image_fill);
//                //storing user data to firebase realtime database
//                HallRegistrationModel haalmodel = new HallRegistrationModel
//                        (holder.hname.getText().toString(),holder.address,
//                                holder.hcontact.getText().toString(), holder.in, holder.out,
//                                holder.hcap.getText().toString(), holder.desc, holder.rent,
//                                holder.pav, holder.dec, holder.image, "");
//                FirebaseDatabase.getInstance().getReference("Favorites")
//                        .child(firebaseUser.getUid()).child(holder.hname.getText().toString()).setValue(haalmodel)
//                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                Toast.makeText(ctx, "Added To Favorites", Toast.LENGTH_SHORT).show();
//
//                            }
//                        });
//
//
//
//
//
//            }
//        });






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

        TextView name,price;
        ImageView image;
        String id,Name,Price;
        String Image;
        Button addTocart;
        String  Quantity;




        public MyViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);

            image = itemView.findViewById(R.id.image);
            addTocart = itemView.findViewById(R.id.addToCart);



        }
    }


}