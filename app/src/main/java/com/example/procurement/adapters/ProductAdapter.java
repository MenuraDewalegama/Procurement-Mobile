package com.example.procurement.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.procurement.R;
import com.example.procurement.controllers.ProductItem;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> implements Filterable {

    private ArrayList<ProductItem> productItemArrayList;
    private ArrayList<ProductItem> productItemArrayListFull;
    private ArrayList<ProductItem> mFilterItems;
    private OnItemClickListener mlistener;
    NumberFormat nm = NumberFormat.getNumberInstance();

    @Override
    public Filter getFilter() {
        return productFilter;
    }

    private Filter productFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<ProductItem> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0){
                filteredList.addAll(productItemArrayListFull);
            }
            else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (ProductItem p : productItemArrayListFull){
                    if (p.getProductName().toLowerCase().contains(filterPattern)){
                        filteredList.add(p);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            productItemArrayList.clear();
            productItemArrayList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card_view, parent, false);
        ProductViewHolder pvh = new ProductViewHolder(v,mlistener);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductItem productItem = productItemArrayList.get(position);

        holder.productName.setText(productItem.getProductName());
        holder.qty.setText(""+productItem.getQty());
        holder.price.setText("Rs. "+nm.format(productItem.getProductPrice()));
    }

    @Override
    public int getItemCount() {
        return productItemArrayList.size();
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mlistener = listener;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        //        EditText productName, qty;
        TextView productName, qty, price;

        public ProductViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            productName = itemView.findViewById(R.id.product_name);
            qty = itemView.findViewById(R.id.qty);
            price = itemView.findViewById(R.id.product_price);

            qty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public ProductAdapter(ArrayList<ProductItem> productList){
        productItemArrayList = productList;
        productItemArrayListFull = new ArrayList<>(productItemArrayList);
        mFilterItems = productList;
//        notifyDataSetChanged();
    }

    public void filterList(ArrayList<ProductItem> filteredList){
        productItemArrayList = filteredList;
        notifyDataSetChanged();
    }
}
