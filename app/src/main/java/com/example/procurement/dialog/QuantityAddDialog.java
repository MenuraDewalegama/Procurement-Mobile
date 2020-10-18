package com.example.procurement.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.procurement.R;
import com.example.procurement.controllers.Order;

import java.util.ArrayList;

public class QuantityAddDialog extends AppCompatDialogFragment {

    private String productName;
    private int qty;

    ArrayList<String> orderList = new ArrayList<>();
    String orderDetail = "";

    Order order;

    public QuantityAddDialog(String productName, int qty) {
        this.productName = productName;
        this.qty = qty;
    }

    TextView product_name;
    EditText add_qty;

    private String title = "Change Quantity";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_quantity_add_dialog, null);

        product_name = view.findViewById(R.id.product_name);
        product_name.setText(""+productName);
        add_qty = view.findViewById(R.id.add_qty);
        add_qty.setText(""+qty);

//        String qty = add_qty.getText().toString();




        builder.setView(view)
                .setTitle(title)
                .setNeutralButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Nothing changed", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        qty = Integer.parseInt(add_qty.getText().toString());

                        orderDetail = "" +productName +" : " +"" +qty;
                        orderList.add(orderDetail);
                        Log.e("This one ",  ""+orderDetail);

                        Toast.makeText(getActivity(), "Added", Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();
    }
}
