package com.example.procurement.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.procurement.R;
import com.example.procurement.controllers.ProductItem;

import java.text.NumberFormat;
import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private ArrayList<ProductItem> orderItemArrayList;
    NumberFormat nm = NumberFormat.getNumberInstance();

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_preview_list_card_view, parent,false);
        OrderViewHolder ovh = new OrderViewHolder(v);
        return ovh;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        ProductItem productItem = orderItemArrayList.get(position);


        holder.productName.setText(productItem.getProductName());
        holder.qty.setText(""+productItem.getQty());
        holder.price.setText("RS. "+nm.format(productItem.getProductPrice()));
        holder.total.setText("RS. "+nm.format(productItem.getProductTotalPrice()));
    }

    @Override
    public int getItemCount() {
        return orderItemArrayList.size();
    }

    public OrderAdapter(ArrayList<ProductItem> orderList) {
        orderItemArrayList = orderList;
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView productName, qty, price, total;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.order_product_name);
            price = itemView.findViewById(R.id.order_price);
            qty = itemView.findViewById(R.id.order_qty);
            total = itemView.findViewById(R.id.order_product_total);

        }
    }


}
