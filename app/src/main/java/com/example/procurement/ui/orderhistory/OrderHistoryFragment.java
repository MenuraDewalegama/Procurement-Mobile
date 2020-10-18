package com.example.procurement.ui.orderhistory;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.procurement.R;
import com.example.procurement.controllers.Order;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class OrderHistoryFragment extends Fragment {

    private static String TAG = "OrderHistoryFragment";

    ArrayList<Order> orderArrayList = new ArrayList<>();

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference ordersRef = db.collection("Orders");

    String data = "";
    TextView ordered_list_in_db;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_order_history, container, false);


        ordered_list_in_db = root.findViewById(R.id.ordered_list_in_db);

        getOrders();

        return root;
    }

    public void getOrders(){
        ordersRef.addSnapshotListener(getActivity(), new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    return;
                }

                for (QueryDocumentSnapshot documentSnapshot : value){
                    Order order = documentSnapshot.toObject(Order.class);
                    order.setDocumentId(documentSnapshot.getId());

                    String documetId = order.getDocumentId();
                    String orderId = order.getOrderId();
                    String orderDate = order.getOrderDate();
                    String manager = order.getManager();
                    String designation = order.getDesignation();
                    int contact = order.getContact();
                    String projectName = order.getProjectName();
                    String address = order.getAddress();
                    double orderTotal = order.getOrderTotal();
                    Map<String, Object> products = order.getProducts();

                    orderArrayList.add(new Order(orderId, orderDate, manager, designation, contact, projectName, address, products, orderTotal));

                    data = "\n"+orderId +"\n" +orderDate +"\n" +manager +"\n" +designation +"\n" +contact +"\n" +projectName +"\n" +address
                            +"\n" +products +"\n" +orderTotal +"\n\n\n";
                    Log.e(TAG, ""+data);
                }
                ordered_list_in_db.setText(""+data);
            }
        });

    }
}
