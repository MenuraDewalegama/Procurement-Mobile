package com.example.procurement.ui.product;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.procurement.DashboardActivity;
import com.example.procurement.OrderPreviewActivity;
import com.example.procurement.R;
import com.example.procurement.adapters.ProductAdapter;
import com.example.procurement.controllers.ProductItem;
import com.example.procurement.dialog.QuantityChangeDialog;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ProductFragment extends Fragment {

    ProductAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    private static final String TAG = "Gallery";

    String productName = "";
    int productQty = 0;
    double productPrice = 0;

    Button order_btn;
    RecyclerView product_list;

    ArrayList<ProductItem> orderArrayList;
    ArrayList<ProductItem> productArrayList;
    ArrayList<ProductItem> previewList;

    private String fullName = "";
    private String projectName = "";
    private String siteAddress = "";
    private String designation = "";
    private int contact = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_product, container, false);
        product_list = root.findViewById(R.id.product_list);

        DashboardActivity dashboardActivity = (DashboardActivity) getActivity();
        Log.e("Ename", ""+dashboardActivity.getFullName());
        fullName = dashboardActivity.getFullName();
        projectName = dashboardActivity.getProjectName();
        siteAddress = dashboardActivity.getSiteAddress();
        designation = dashboardActivity.getDesignation();
        contact = dashboardActivity.getContact();

        order_btn = root.findViewById(R.id.order_btn);
        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrderPreviewActivity.class);
                previewList = new ArrayList<>();
                for (ProductItem productItem : orderArrayList) {
                    if (productItem.getQty()!=0){
                        double total = productItem.getQty() * productItem.getProductPrice();
                        previewList.add(new ProductItem(productItem.getProductName(), productItem.getQty(), productItem.getProductPrice(), total));
                    }
                }

                intent.putExtra("previewList", previewList);
                intent.putExtra("fullName", fullName);
                intent.putExtra("projectName", projectName);
                intent.putExtra("siteAddress", siteAddress);
                intent.putExtra("contact", contact);
                intent.putExtra("designation", designation);

                startActivity(intent);
            }
        });

        showList();

        adapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity(), "Working", Toast.LENGTH_SHORT).show();
                ProductItem i = orderArrayList.get(position);
                String name = i.getProductName();
                int qty = i.getQty();
                double price = i.getProductPrice();

                productName = name;
                productQty = qty;
                productPrice = price;

                openDialog(position);
            }
        });

        return root;
    }

    public void openDialog(final int position) {
        QuantityChangeDialog qcd = new QuantityChangeDialog(getActivity(), true, new QuantityChangeDialog.IOnOkClickListener() {
            @Override
            public void okClicked(double value) {
                ProductItem e = orderArrayList.get(position);
                orderArrayList.remove(e);
                int val = (int) value;
                e.setQty(val);
                orderArrayList.add(position, e);
                adapter.notifyDataSetChanged();
            }
        }, productName, productQty, productPrice);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        qcd.show();
    }

    public void showList(){
        productArrayList = new ArrayList<>();
        productArrayList.add(new ProductItem("Cement", 0, 1000, 0));
        productArrayList.add(new ProductItem("Bricks", 0, 50, 0));
        productArrayList.add(new ProductItem("Pipes", 0, 300, 0));
        productArrayList.add(new ProductItem("Steel", 0, 150, 0));


        orderArrayList = new ArrayList<>();
        if (productArrayList.size()!=0){
            for (ProductItem productItem : productArrayList){
                orderArrayList.add(new ProductItem(productItem.getProductName(), productItem.getQty(), productItem.getProductPrice(), productItem.getProductTotalPrice()));
            }
        }

        product_list.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new ProductAdapter(orderArrayList);
        product_list.setLayoutManager(layoutManager);
        product_list.setAdapter(adapter);
    }
}
