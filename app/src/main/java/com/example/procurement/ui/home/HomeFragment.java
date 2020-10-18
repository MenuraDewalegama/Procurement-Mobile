package com.example.procurement.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.procurement.DashboardActivity;
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

public class HomeFragment extends Fragment {

    int orderedHistorySize = 0;
    ArrayList<Order> orderArrayList = new ArrayList<>();

    TextView home_previous_order_history_size;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        DashboardActivity dashboardActivity = (DashboardActivity) getActivity();

        home_previous_order_history_size = root.findViewById(R.id.home_previous_order_history_size);
        orderedHistorySize = dashboardActivity.getOrderedHistorySize();
        home_previous_order_history_size.setText("Number of requested you have made : "+orderedHistorySize);
        return root;
    }
}
