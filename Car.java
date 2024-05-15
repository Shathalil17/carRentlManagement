
package com.mycompany.start;



public class Car extends  Vehicle {


    private double rentRate=78;
    private double lateFee;
    private int seats=0;

    Car(String VehicleId,int Year,String Make,String Model,int status,VehicleType vehicleType)
    {
        super(VehicleId,Year,Make,Model,status,vehicleType);
        seats=vehicleType.getCarSeats();
        if(seats==7)
            rentRate=113;
    }

   
    public double getLateFee(DateTime endDate,DateTime startDate)
    {

        if(DateTime.diffDays(endDate,startDate)>0)
            this.lateFee= 1.25 * this.rentRate * DateTime.diffDays(endDate,startDate);
        else
            this.lateFee=0.0;
        return this.lateFee;
    }

   // return date not before estimated return date, return date within the minimum rental pirod from the rental start date
    public  boolean returnVehicle(DateTime returnDate)
    {
        String vehicleType;
        if(this.Vehicle_id.contains("C_"))
            vehicleType="car";
        else
            vehicleType="van";
        if(this.vehicleStatus!=0)
        {
        DateTime estdate= this.records[this.getLastElementIndex()].getEstimatedReturnDate();
        DateTime rentDate= this.records[this.getLastElementIndex()].getRentDate();
        if(vehicleType.equals("car") && DateTime.diffDays(returnDate,estdate)<0 && DateTime.diffDays(returnDate,rentDate)<this.vehicleType.canBeRentedForMinimumDays(rentDate,vehicleType)){
            return false;
        }

        else{

            this.records[this.getLastElementIndex()].setData(returnDate,this.rentRate * DateTime.diffDays(returnDate,rentDate),this.getLateFee(returnDate,estdate));
            this.vehicleStatus=0;
            return true;
        }}
        else {
            return false;
        }
    }

    
    public boolean completeMaintenance()
    {
        if(super.vehicleStatus!=2)
            return false;
        this.vehicleStatus=0;
        return true;
    }

// car history, prints rental record of car
    
    public String getDetails()
    {
        String details =super.getDetails();
        if(this.records[0]==null){
            details+="\nRENTAL RECORD:\tempty";
		}
        else{
            details+="\nRENTAL RECORD\n";
			int count=0;
			for(int index=0;this.records[index]!=null;index++)
				count++;
            for(int index=count-1;index>=0;index--){
                details+=this.records[index].getDetails()+"                     \n";
                for(int innerIndex=0;innerIndex<10;innerIndex++)
                    details+="-";
                details+="                     \n";
            }
        }
        return details;
    }
	
}