package com.example.procurement;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DashboardActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    TextView nav_header_user_name, nav_header_user_email;
    Bundle bundle;

    private String fullName = "";
    private String email = "";
    private String employeeID = "";
    private String projectName = "";
    private String siteAddress = "";
    private String designation = "";
    private int contact = 0;
    private int orderedHistorySize = 0;

    public int getOrderedHistorySize() {
        return orderedHistorySize;
    }

    public String getFullName() {
        return fullName;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public String getDesignation() {
        return designation;
    }

    public int getContact() {
        return contact;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bundle = new Bundle();
        bundle = getIntent().getExtras();
        if (bundle != null){
            fullName = bundle.getString("fullName", fullName);
            email = bundle.getString("email", email);
            employeeID = bundle.getString("employeeID", employeeID);
            projectName = bundle.getString("projectName", projectName);
            siteAddress = bundle.getString("siteAddress", siteAddress);
            designation = bundle.getString("designation", designation);
            contact = bundle.getInt("contact", contact);
            orderedHistorySize = bundle.getInt("orderedHistorySize",orderedHistorySize);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View view = navigationView.getHeaderView(0);

        nav_header_user_name = view.findViewById(R.id.nav_header_user_name);
        nav_header_user_name.setText(""+fullName);
        nav_header_user_email  = view.findViewById(R.id.nav_header_user_email);
        nav_header_user_email.setText(""+email);

        Toast.makeText(this, "Welcome "+fullName, Toast.LENGTH_SHORT).show();

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_product, R.id.nav_order_history)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
