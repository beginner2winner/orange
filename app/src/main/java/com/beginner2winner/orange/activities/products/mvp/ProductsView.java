package com.beginner2winner.orange.activities.products.mvp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.beginner2winner.orange.R;
import com.beginner2winner.orange.activities.products.ui.ProductsAdapter;
import com.beginner2winner.orange.app.network.model.EndClothingProductsList;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * MVP (Container) View for Products
 *
 * This is where RxMVP distinguishes itself from established MVP.
 * By creating a 'container view' we can both control the constructor parameters,
 * allowing mocking and dagger injection, and separate out the Activity(or Fragment)
 * from the role of the View and stop it all becoming a big mess with system events
 * all over the place.
 *
 * We have to suppress the compiler warnings over constructor overrides because of this.
 *
 * Created by richard, © copyright Beginner2Winner Ltd
 */

@SuppressLint("ViewConstructor")
public class ProductsView extends FrameLayout {

    private final ProgressDialog progressDialog = new ProgressDialog(getContext());
    private final Picasso picasso;

    @BindView(R.id.recycler_view_products_list)
    RecyclerView recyclerView;

    public ProductsView(Activity activity, Picasso picasso) {
        super(activity);

        this.picasso = picasso;

        inflate(getContext(), R.layout.activity_products_list, this);

        progressDialog.setMessage("Looking up user");
        ButterKnife.bind(this);

        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.recyclerView.setHasFixedSize(true);
    }

    public void showLoading(boolean loading) {
        if (loading) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }

    public void setAdapter(ProductsAdapter adapter) {
        this.recyclerView.setAdapter(adapter);
    }

    public void setData(EndClothingProductsList endClothingProductsList) {
        showLoading(false);
        Toast.makeText(getContext(), "We have "+endClothingProductsList.getItemCount()+" products", Toast.LENGTH_SHORT).show();

        ((ProductsAdapter)this.recyclerView.getAdapter()).setData(endClothingProductsList);
    }
}
