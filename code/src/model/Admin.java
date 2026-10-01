package model;

import custom_exception.InvalidAmountException;
import custom_exception.NotFoundException;
import custom_exception.UnauthorizedAccessException;
import file.AdminFile;

import java.util.ArrayList;

public class Admin {

    private User user;

    public Admin(User user)
            throws UnauthorizedAccessException {

        validateUser(user);
        this.user = user;
    }


    public void viewAllUser()
            throws NotFoundException {

        ArrayList<User> users =
                AdminFile.getAllUsers();

        if (users.isEmpty()) {

            throw new NotFoundException(
                    "No users found!"
            );
        }

        for (User user : users) {

            System.out.println(
                    "ID: " + user.getUser_id()
            );

            System.out.println(
                    "Name: " + user.getUser_name()
            );

            System.out.println(
                    "Email: " + user.getUser_email()
            );

            System.out.println(
                    "Role: " + user.getUser_role()
            );

            System.out.println(
                    "--------------------"
            );
        }
    }



    public void registerNewRider(
            String name,
            String email,
            String password
    ) throws NotFoundException {

        boolean result =
                AdminFile.registerNewRider(
                        name,
                        email,
                        password
                );

        if (result) {

            System.out.println(
                    "Rider registered successfully!"
            );

        } else {

            throw new NotFoundException(
                    "Failed to register rider!"
            );
        }
    }



    public void updateUser(
            String userID,
            String name,
            String email,
            String password
    ) throws NotFoundException {

        boolean result =
                AdminFile.updateUser(
                        userID,
                        name,
                        email,
                        password
                );

        if (result) {

            System.out.println(
                    "User updated successfully!"
            );

        } else {

            throw new NotFoundException(
                    "User not found or update failed!"
            );
        }
    }



    public void viewAllParcel()
            throws NotFoundException,
            UnauthorizedAccessException,
            InvalidAmountException {

        ArrayList<Parcel> parcels =
                AdminFile.getAllParcels();

        if (parcels.isEmpty()) {

            throw new NotFoundException(
                    "No parcels found!"
            );
        }

        for (Parcel parcel : parcels) {

            printParcel(parcel);

            System.out.println(
                    "--------------------"
            );
        }
    }



    public void searchParcel(
            String parcelID
    ) throws NotFoundException,
            UnauthorizedAccessException,
            InvalidAmountException {

        Parcel parcel =
                AdminFile.searchParcel(parcelID);

        if (parcel == null) {

            throw new NotFoundException(
                    "Parcel not found!"
            );
        }

        printParcel(parcel);
    }



    public void searchUser(
            String userID
    ) throws NotFoundException {

        User user =
                AdminFile.searchUser(userID);

        if (user == null) {

            throw new NotFoundException(
                    "User not found!"
            );
        }

        System.out.println(
                "User ID: " + user.getUser_id()
        );

        System.out.println(
                "Name: " + user.getUser_name()
        );

        System.out.println(
                "Email: " + user.getUser_email()
        );

        System.out.println(
                "Role: " + user.getUser_role()
        );
    }



    public void deleteUser(
            String userID
    ) throws NotFoundException {

        boolean result =
                AdminFile.deleteUser(userID);

        if (result) {

            System.out.println(
                    "User deleted successfully!"
            );

        } else {

            throw new NotFoundException(
                    "User not found or delete failed!"
            );
        }
    }


    public void deleteParcel(
            String parcelID
    ) throws NotFoundException {

        boolean result =
                AdminFile.deleteParcel(parcelID);

        if (result) {

            System.out.println(
                    "Parcel deleted successfully!"
            );

        } else {

            throw new NotFoundException(
                    "Parcel not found or delete failed!"
            );
        }
    }


    public void updateParcelStatus(
            String parcelID,
            String newStatus
    ) throws NotFoundException {

        boolean result =
                AdminFile.updateParcelStatus(
                        parcelID,
                        newStatus
                );

        if (result) {

            System.out.println(
                    "Parcel status updated successfully!"
            );

        } else {

            throw new NotFoundException(
                    "Parcel not found or status update failed!");
        }
    }


    private void printParcel(Parcel parcel) {
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



    private void validateUser(User user) throws UnauthorizedAccessException {
        if (user == null) {
            throw new UnauthorizedAccessException("Unauthorized Access! User not found.");
        }

        if (user.getUser_id() == null || user.getUser_id().trim().isEmpty()) {
            throw new UnauthorizedAccessException("Unauthorized Access! Invalid user ID.");
        }

        if (user.getUser_role() == null || user.getUser_role().trim().isEmpty()) {
            throw new UnauthorizedAccessException("Unauthorized Access! User role not found.");
        }
        if (!user.getUser_role().equalsIgnoreCase(String.valueOf(User.UserRole.ADMIN))) {
            throw new UnauthorizedAccessException("Unauthorized Access! Only Admin can access this feature.");
        }
    }
}