
package com.mycompany.start;


import java.util.LinkedList;



class Van extends Vehicle {
    
    private double rate = 235;
    private double lateFee = 299;
    private LinkedList<RentalRecord>rentalRecords;

    
    Van(String vehicleId, int year, String make, String model, int status, VehicleType vehicleType) {
        super(vehicleId, year, make, model, status, vehicleType);
        this.rate = rate;
        this.rentalRecords = new LinkedList<>();
    }

  
    public double getLateFee(DateTime endDate, DateTime startDate) {
        if (DateTime.diffDays(endDate, startDate) > 0)
            return this.lateFee * DateTime.diffDays(endDate, startDate);
        else
            return this.lateFee = 0.0;
    }

 
    public boolean returnVehicle(DateTime returnDate) {
        String vehicleType;
        if (this.Vehicle_id.contains("C_"))
            vehicleType = "car";
        else
            vehicleType = "van";
        if (this.vehicleStatus != 0) {
            DateTime estimatedDate = this.rentalRecords.getLast().getEstimatedReturnDate();
            DateTime rentDate = this.rentalRecords.getLast().getRentDate();

            if (vehicleType.equals("van") && DateTime.diffDays(returnDate, rentDate) < 1) {
                return false;
            } else {
                double rent = this.rate * DateTime.diffDays(returnDate, this.rentalRecords.getLast().getRentDate());
                this.rentalRecords.getLast().setData(returnDate, rent, this.getLateFee(returnDate, estimatedDate));
                this.vehicleStatus = 0;
                return true;
            }
        } else return false;
    }

   
    public boolean completeMaintenance(DateTime completionDate) {
        if (this.vehicleStatus != 2 && DateTime.diffDays(completionDate, this.vehicleType.getLastMaintenance()) < 12)
            return false;
        this.vehicleType.setLastMaintenance(completionDate);
        this.vehicleStatus = 0;
        return true;
    }

    
    public String toString() {
        String details = super.toString();
        DateTime lastMaintenanceDate = this.vehicleType.getLastMaintenance();
        details += ":" + lastMaintenanceDate.toString();
        return details;
    }

    
    public String getDetails() {
        String details = super.getDetails();
        details += "\nLast maintenance date:\t" + (this.vehicleType.getLastMaintenance()).toString();
        if (this.rentalRecords.isEmpty())
            details += "\nRENTAL RECORD:\tempty";
        else {
            details += "\nRENTAL RECORD:\n";
            for (RentalRecord record : this.rentalRecords) {
                details += record.getDetails() + "                     \n";
                for (int innerIndex = 0; innerIndex < 10; innerIndex++)
                    details += "-";
                details += "                     \n";
            }
        }
        return details;
    }

   
    public void addRentalRecord(RentalRecord record) {
        this.rentalRecords.add(record);
    }
}
