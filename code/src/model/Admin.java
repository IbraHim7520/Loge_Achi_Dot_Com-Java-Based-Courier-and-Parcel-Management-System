package model;

import file.AdminFile;
import java.util.ArrayList;

public class Admin {
    User user = null;
    Admin(User user){
        if(!validateUser(user)){
            System.out.println("Unauthorized Access! Please be authorize before try again.");
        }else{
            this.user = user;
        }
    }

    public void viewAllUser() {
        ArrayList<User> users = AdminFile.getAllUsers();
        if(users.isEmpty()){
            System.out.println("No users found!");
            return;
        }
        for(User user : users){
            System.out.println("ID: " + user.getUser_id());
            System.out.println("Name: " + user.getUser_name());
            System.out.println("Email: " + user.getUser_email());
            System.out.println("Role: " + user.getUser_role());
            System.out.println("--------------------");
        }
    }

    public void registerNewRider(String name,String email,String password) {
        boolean result = AdminFile.registerNewRider(name,email,password);
        if(result){
            System.out.println("Rider registered successfully!");
        }else{
            System.out.println("Failed to register rider!");
        }
    }

    public void updateUser(String userID,String name,String email,String password) {
        boolean result = AdminFile.updateUser(userID,name,email,password);
        if(result){
            System.out.println("User updated successfully!");
        }else{
            System.out.println("Failed to update user!");
        }
    }

    public void viewAllParcel() {
        ArrayList<Parcel> parcels = AdminFile.getAllParcels();
        if(parcels.isEmpty()){
            System.out.println("No parcels found!");
            return;
        }
        for(Parcel parcel : parcels){
            System.out.println("Parcel ID: " + parcel.getParcelID());
            System.out.println("Parcel Name: " + parcel.getParcelName());
            System.out.println("Receiver Address: " + parcel.getReciverAddress());
            System.out.println("Receiver Phone: " + parcel.getReciverPhone());
            System.out.println("Weight: " + parcel.getWeight());
            System.out.println("Sender Email: " + parcel.getSenderEmail());
            System.out.println("Sender ID: " + parcel.getSenderId());
            System.out.println("Status: " + parcel.getParcelStatus());
            System.out.println("Delivery Charge: " + parcel.getDeliveryCharge());
            System.out.println("Rider ID: " + parcel.getRiderId());
            System.out.println("--------------------");
        }
    }

    public void searchParcel(String parcelID) {
        Parcel parcel = AdminFile.searchParcel(parcelID);
        if(parcel == null){
            System.out.println("Parcel not found!");
            return;
        }
        System.out.println("Parcel ID: " + parcel.getParcelID());
        System.out.println("Parcel Name: " + parcel.getParcelName());
        System.out.println("Receiver Address: " + parcel.getReciverAddress());
        System.out.println("Receiver Phone: " + parcel.getReciverPhone());
        System.out.println("Weight: " + parcel.getWeight());
        System.out.println("Sender Email: " + parcel.getSenderEmail());
        System.out.println("Sender ID: " + parcel.getSenderId());
        System.out.println("Status: " + parcel.getParcelStatus());
        System.out.println("Delivery Charge: " + parcel.getDeliveryCharge());
        System.out.println("Rider ID: " + parcel.getRiderId());
    }

    public void searchUser(String userID) {
        User user = AdminFile.searchUser(userID);
        if(user == null){
            System.out.println("User not found!");
            return;
        }
        System.out.println("User ID: " + user.getUser_id());
        System.out.println("Name: " + user.getUser_name());
        System.out.println("Email: " + user.getUser_email());
        System.out.println("Role: " + user.getUser_role());
    }

    public void deleteUser(String userID) {
        boolean result = AdminFile.deleteUser(userID);
        if(result){
            System.out.println("User deleted successfully!");
        }else{
            System.out.println("Failed to delete user!");
        }
    }

    public void deleteParcel(String parcelID) {
        boolean result = AdminFile.deleteParcel(parcelID);
        if(result){
            System.out.println("Parcel deleted successfully!");
        }else{
            System.out.println("Failed to delete parcel!");
        }
    }

    public void updateParcelStatus(String parcelID,String newStatus) {
        boolean result = AdminFile.updateParcelStatus(parcelID,newStatus);
        if(result){
            System.out.println("Parcel status updated successfully!");
        }else{
            System.out.println("Failed to update parcel status!");
        }
    }

    private boolean validateUser(User user) {
        if(user == null) return false;
        if(user.getUser_id() == null) return false;
        if(user.getUser_role() == null) return false;
        if(!user.getUser_role().contains("ADMIN")) return false;
        return true;
    }
}