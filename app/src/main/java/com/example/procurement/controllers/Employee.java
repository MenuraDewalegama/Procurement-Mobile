package com.example.procurement.controllers;


import com.google.firebase.firestore.Exclude;

public class Employee {

    private String documentId;
    private int Contact;
    private String Designation;
    private String Email;
    private String EmployeeID;
    private String FullName;
    private String Nic;
    private String Password;
    private String SiteAddress;
    private String projectName;

//    public Employee(String contact, String designation, String email, String employeeID, String fullName, String nic, String password, String siteAddress, String projectName) {
//        this.contact = contact;
//        this.designation = designation;
//        this.email = email;
//        this.employeeID = employeeID;
//        this.fullName = fullName;
//        this.nic = nic;
//        this.password = password;
//        this.siteAddress = siteAddress;
//        this.projectName = projectName;
//    }

    public Employee() {
    }

    public Employee(int contact, String designation, String email, String employeeID, String fullName, String nic, String password, String siteAddress, String projectName) {
        this.documentId = documentId;
        Contact = contact;
        Designation = designation;
        Email = email;
        EmployeeID = employeeID;
        FullName = fullName;
        Nic = nic;
        Password = password;
        SiteAddress = siteAddress;
        this.projectName = projectName;
    }

    public int getContact() {
        return Contact;
    }

    public void setContact(int contact) {
        Contact = contact;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getNic() {
        return Nic;
    }

    public void setNic(String nic) {
        Nic = nic;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getSiteAddress() {
        return SiteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        SiteAddress = siteAddress;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Exclude
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    //    public void order(){
//        orderId
//        item itemId
//        orderDate
//        status
//        employeeID
//        projectName
//        addres
//        pmId
//    }

}
