package model;

import custom_exception.NotFoundException;
import custom_exception.UnauthorizedAccessException;
import file.RiderFile;

import java.util.ArrayList;

public class Rider {

    private User user;

    public Rider(User user) throws UnauthorizedAccessException {
        validateUser(user);
        this.user = user;
    }

    public boolean validateRider(User user) throws UnauthorizedAccessException {

        if (user == null) {
            throw new UnauthorizedAccessException("Unauthorized Access! User not found.");
        }

        if (user.getUser_id() == null || user.getUser_id().trim().isEmpty()) {
            throw new UnauthorizedAccessException("Unauthorized Access! Invalid user ID.");
        }

        if (user.getUser_role() == null || !user.getUser_role().equals(String.valueOf(User.UserRole.RIDER))) {
            throw new UnauthorizedAccessException("Unauthorized Access! Only Rider can access this feature.");
        }

        return true;
    }

    public void viewPendingParcels() throws NotFoundException {

        ArrayList<String> parcels = RiderFile.getPendingParcels();

        if (parcels.isEmpty()) {
            throw new NotFoundException("No pending parcels found!");
        }

        for (String parcel : parcels) {
            System.out.println(parcel);
        }
    }

    public void acceptParcelsByRider(String parcelId, String riderID) throws NotFoundException {

        boolean result = RiderFile.assignParcel(parcelId, riderID);

        if (result) {
            System.out.println("Parcel Accepted!");
        } else {
            throw new NotFoundException("Parcel not found or failed to assign!");
        }
    }

    public void viewMyAssignedParcles() throws NotFoundException {

        ArrayList<String> parcels = RiderFile.getMyAssignedParcels(user.getUser_id());

        if (parcels.isEmpty()) {
            throw new NotFoundException("No assigned parcels found!");
        }
        for (String parcel : parcels) {
            System.out.println(parcel);
        }
    }

    public void updateParcelStatus(String parcelId, String newStatus) throws NotFoundException {
        boolean result = RiderFile.updateParcelStatusByRider(parcelId, user.getUser_id(), newStatus);
        if (result) {
            System.out.println("Parcel status updated successfully!");
        } else {
            throw new NotFoundException("Parcel not found or status update failed!");
        }
    }

    private void validateUser(User user)
            throws UnauthorizedAccessException {

        if (user == null) {
            throw new UnauthorizedAccessException("Unauthorized Access! User not found.");
        }

        if (user.getUser_id() == null || user.getUser_id().trim().isEmpty()) {
            throw new UnauthorizedAccessException("Unauthorized Access! Invalid user ID.");
        }

        if (user.getUser_role() == null) {
            throw new UnauthorizedAccessException("Unauthorized Access! User role not found.");
        }

        if (!user.getUser_role().equals(String.valueOf(User.UserRole.RIDER))) {
            throw new UnauthorizedAccessException("Unauthorized Access! Only Rider can access this feature.");
        }
    }
}