package com.example.procurement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.procurement.adapters.OrderAdapter;
import com.example.procurement.controllers.Order;
import com.example.procurement.controllers.ProductItem;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class OrderPreviewActivity extends AppCompatActivity {

    ArrayList<ProductItem> orderArrayList;
    TextView op_order_amount, op_order_id, op_date_time, op_contact_number, op_site_address, op_project_name, op_designation, op_name;
    Button upload_btn;
    RecyclerView ordered_list;
    OrderAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    private String fullName = "";
    private String projectName = "";
    private String siteAddress = "";
    private String designation = "";
    private int contact = 0;
    private double orderTotal = 0;
    private String orderID = "";
    private String orderDate = "";

    private static final String ORDER_ID = "orderId";
    private static final String ORDER_DATE = "orderDate";
    private static final String ORDER_STATUS = "status";
    private static final String ORDER_EMPLOYEE_ID = "employeeID";
    private static final String ORDER_PROJECT_NAME = "projectName";
    private static final String ORDER_ADDRESS = "address";
    private static final String ORDER_PM_ID = "pmId";
    private static final String ORDER_COMMENT = "comment";
    private static String ORDER_PRODUCT = "product";
    private static final String ORDER_REQUESTED_MANAGER = "manager";
    private static final String ORDER_REQUESTED_MANAGER_DESIGNATION = "designation";
    private static final String ORDER_REQUESTED_MANAGER_CONTACT = "contact";
    private static final String TAG = "OrderPreviewActivity";


    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference orderRef = db.collection("Orders");
    NumberFormat nm = NumberFormat.getNumberInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_preview);

        orderArrayList = new ArrayList<>();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            orderArrayList = (ArrayList<ProductItem>) getIntent().getSerializableExtra("previewList");
            fullName = bundle.getString("fullName", fullName);
            projectName = bundle.getString("projectName", projectName);
            siteAddress = bundle.getString("siteAddress", siteAddress);
            designation = bundle.getString("designation", designation);
            contact = bundle.getInt("contact", contact);
        }

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd / HH:mm:ss", Locale.getDefault());
        orderID = sdf2.format(new Date());
        orderDate = sdf3.format(new Date());

        op_order_amount = findViewById(R.id.op_order_amount);
        op_order_id = findViewById(R.id.op_order_id);
        op_date_time = findViewById(R.id.op_date_time);
        op_contact_number = findViewById(R.id.op_contact_number);
        op_site_address = findViewById(R.id.op_site_address);
        op_project_name = findViewById(R.id.op_project_name);
        op_designation = findViewById(R.id.op_designation);
        op_name = findViewById(R.id.op_name);
        upload_btn = findViewById(R.id.upload_btn);

        setOrderDetails();

        ordered_list = findViewById(R.id.ordered_list);
        ordered_list.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(OrderPreviewActivity.this);

        adapter = new OrderAdapter(orderArrayList);
        ordered_list.setLayoutManager(layoutManager);
        ordered_list.setAdapter(adapter);

        syncDataToFirebase();

    }

    public void setOrderDetails(){
        op_date_time.setText(""+orderDate);
        op_order_id.setText(""+orderID);
        op_name.setText(""+fullName);
        op_designation.setText(""+designation);
        op_project_name.setText(""+projectName);
        op_site_address.setText(""+siteAddress);
        op_contact_number.setText(""+contact);

        for (ProductItem productItem : orderArrayList) {
            orderTotal = productItem.getProductTotalPrice() + orderTotal;
        }

        op_order_amount.setText("RS. " +nm.format(orderTotal));
    }

    public void syncDataToFirebase(){
        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> products = new HashMap<>();
                for (ProductItem productItem : orderArrayList) {
                    String details = "" +productItem.getQty() +" x " +productItem.getProductPrice() +" = " +productItem.getProductTotalPrice();
                    products.put(productItem.getProductName(), details);
                }

                if(orderTotal>100000){
                    Toast.makeText(OrderPreviewActivity.this, "Can not order more than Rs.100,000, Please contact procurement", Toast.LENGTH_SHORT).show();
                }
                else {
                    Order note = new Order(orderID, orderDate, fullName, designation, contact, projectName, siteAddress, products, orderTotal);
                    orderRef.add(note);
                    Toast.makeText(OrderPreviewActivity.this, "Order requested", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(OrderPreviewActivity.this, DashboardActivity.class);
                    startActivity(intent);

                }
            }
        });



    }


}
