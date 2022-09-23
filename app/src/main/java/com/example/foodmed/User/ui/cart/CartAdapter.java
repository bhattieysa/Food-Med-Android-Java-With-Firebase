package com.example.foodmed.User.ui.cart;





import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.foodmed.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder>{

    private Context mCtx;
    private static final int REQUEST_CALL = 1;
    private ArrayList<CartModel> categoryList;

    private FragmentManager fragmentManager;
    ProgressDialog progressDialog;
    AlertDialog alertDialog;
    StorageReference photoRef;
    DatabaseReference ref;
    Query applesQuery;
    DatabaseReference databaseReference;

    FirebaseDatabase database;

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    private FirebaseAuth mAuth;
    Integer quantity;

    public CartAdapter(Activity activity , ArrayList <CartModel> categoryList,ProgressDialog progressDialog,    DatabaseReference databaseReference, FirebaseDatabase database) {
        this.mCtx = activity;
        this.categoryList = categoryList;
        this.progressDialog=progressDialog;

        this.database=database;

    }


    @NonNull
    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from ( mCtx );
        View view = inflater.inflate ( R.layout.cart_list , null );
        return new CartAdapter.MyViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyViewHolder holder, int position) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("cart");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        progressDialog.dismiss();


        final CartModel category = categoryList.get(position);



        holder.name.setText(category.getName());
        holder.price.setText(category.getPrice());
        holder.Name=category.getName();
        holder.Price=category.getPrice();
        holder.id=category.getId();
        holder.Image=category.getImage();
        holder.quantity.setText(category.getQuantity());
        holder.Quantity=category.getQuantity();
holder.plus.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        quantity=Integer.parseInt(holder.Quantity)+1;
        databaseReference.child(firebaseUser.getUid()).child(holder.Name).child("quantity").setValue(String.valueOf(quantity));

    }
});
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(Integer.parseInt(holder.Quantity)>1) {
                    quantity = Integer.parseInt(holder.Quantity) - 1;
                    databaseReference.child(firebaseUser.getUid()).child(holder.Name).child("quantity").setValue(String.valueOf(quantity));
                }
            }
        });
        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                photoRef = FirebaseStorage.getInstance().getReferenceFromUrl( holder.Image);
                applesQuery = databaseReference.orderByChild("id").equalTo(holder.id);
                alertDialog= new AlertDialog.Builder(mCtx)
                        // set message, title, and icon
                        .setMessage("Are You Sure To Delete This Product?")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                                            photoRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    // File deleted successfully
                                                    Log.d("eysa", "onSuccess: deleted file"+holder.id);
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception exception) {
                                                    // Uh-oh, an error occurred!
                                                    Log.d("eysa1", "onFailure: did not delete file");
                                                }
                                            });
                                            appleSnapshot.getRef().removeValue();
                                        }
                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        Log.e("error", "onCancelled", databaseError.toException());
                                    }
                                });
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                alertDialog.show();
                return false;
            }
        });
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

        TextView name,price,quantity;
        ImageView image;
        String id,Name,Price;
        String Image;
        Button plus;
        Button minus;
        String Quantity;
        CardView card;




        public MyViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            card = itemView.findViewById(R.id.card);
            price = itemView.findViewById(R.id.price);

            image = itemView.findViewById(R.id.image);
            plus = itemView.findViewById(R.id.plus);
            minus = itemView.findViewById(R.id.minus);
            quantity = itemView.findViewById(R.id.quantity);



        }
    }


}