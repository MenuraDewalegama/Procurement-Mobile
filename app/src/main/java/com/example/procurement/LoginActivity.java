package com.example.procurement;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.procurement.controllers.Employee;
import com.example.procurement.controllers.Order;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference ref = db.collection("Employees");
    private CollectionReference ordersRef = db.collection("Order2");

    ArrayList<Employee> employeeArrayList = new ArrayList<>();
    ArrayList<Order> orderArrayList = new ArrayList<>();
    EditText phone_number_input, password_input;
    Button login_button;

    String phoneNumber = "";
    String passwordI = "";
    int orderedHistorySize = 0;

    Order order = new Order();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phone_number_input = findViewById(R.id.phone_number_input);
        password_input = findViewById(R.id.password_input);

        login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (phone_number_input.getText().toString().equals("") && password_input.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this, "Please fill user name and password field", Toast.LENGTH_SHORT).show();
                }
                else {
                    phoneNumber = phone_number_input.getText().toString();
                    passwordI = password_input.getText().toString();

                    validate();
                }
            }
        });
    }

    //used this method to get employees from firebase
    @Override
    protected void onStart() {
        super.onStart();
        ref.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null){
                    return;
                }
                String data = "";
                for (QueryDocumentSnapshot documentSnapshot : value){
                    Employee employee = documentSnapshot.toObject(Employee.class);
                    employee.setDocumentId(documentSnapshot.getId());

                    String documetId = employee.getDocumentId();
                    int contact = employee.getContact();
                    String designation = employee.getDesignation();
                    String email = employee.getEmail();
                    String employeeID = employee.getEmployeeID();
                    String fullName = employee.getFullName();
                    String nic = employee.getNic();
                    String password = employee.getPassword();
                    String siteAddress = employee.getSiteAddress();
                    String projectName = employee.getProjectName();

                    data = "ID : " +documetId +"\n" +"Contact : " +contact +"\n" +"Password : " +password;
                    Log.e("Right Here : ", ""+data);
                    employeeArrayList.add(new Employee(contact, designation, email, employeeID, fullName, nic, password, siteAddress, projectName));
                }
            }
        });

        ordersRef.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    return;
                }
                String data = "";
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
                }
                orderedHistorySize = orderArrayList.size();
                Log.e("Hereeeeeeeeeee", ""+orderedHistorySize);
            }
        });

    }

    //this method used to validate login
    public  void validate(){
        int phone = Integer.parseInt(phoneNumber);
        for (Employee employee: employeeArrayList){
            if (employee.getContact() == phone && employee.getPassword().equals(passwordI)){

                int contact = employee.getContact();
                String designation = employee.getDesignation();
                String email = employee.getEmail();
                String employeeID = employee.getEmployeeID();
                String fullName = employee.getFullName();
                String nic = employee.getNic();
                String password = employee.getPassword();
                String siteAddress = employee.getSiteAddress();
                String projectName = employee.getProjectName();

                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);

                //passing value to dashboard activity
                intent.putExtra("fullName", fullName);
                intent.putExtra("employeeID", employeeID);
                intent.putExtra("projectName", projectName);
                intent.putExtra("siteAddress", siteAddress);
                intent.putExtra("designation", designation);
                intent.putExtra("contact", contact);
                intent.putExtra("email", email);
                intent.putExtra("orderedHistorySize", orderedHistorySize);

                startActivity(intent);
                finish();
            }
        }
    }

}
