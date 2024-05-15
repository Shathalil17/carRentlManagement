
package com.mycompany.start;



public class Vehicle {
    protected String Vehicle_id;
    protected int Year;
    protected String Make;
    protected String Model;
    protected int vehicleStatus;
    protected VehicleType vehicleType;
    protected RentalRecord records[]= new RentalRecord[10];

    
    Vehicle(String VehicleId,int Year,String Make,String Model,int status,VehicleType vehicleType){
        this.Vehicle_id=VehicleId;
        this.Year=Year;
        this.Make=Make;
        this.Model= Model;
        this.vehicleStatus=status;
        this.vehicleType=vehicleType;
    }
 
  // compar with the exist
    public String getVehicleId(){
        return this.Vehicle_id;
    }

  //reinted or not 
    public boolean rent(String customerId, DateTime rentDate, int numOfRentDay)
    {
          String typeOfVehicle;
          if(this.Vehicle_id.contains("C_"))
              typeOfVehicle="car";
          else
              typeOfVehicle="van";
          if(this.vehicleStatus!=0 || numOfRentDay<this.vehicleType.canBeRentedForMinimumDays(rentDate,typeOfVehicle) || numOfRentDay>=14||numOfRentDay<2)
          {
              return false;
          }
          else if( typeOfVehicle.equals("van") )
          {
			  if(this.vehicleStatus!=0 || this.vehicleType.IsUnderMaintenance(rentDate,typeOfVehicle,numOfRentDay) ||  numOfRentDay==0)
              return false;
		      else
			  {
			  String rentId= this.Vehicle_id+"_"+customerId+"_"+rentDate. getEightDigitDate();
              this.records[this.getLastElementIndex()+1]=new RentalRecord(rentId,rentDate,new DateTime(rentDate,numOfRentDay));
              this.vehicleStatus=1;
              return true;
			  }
          }
          else {
              String rentId= this.Vehicle_id+"_"+customerId+"_"+rentDate. getEightDigitDate();
              this.records[this.getLastElementIndex()+1]=new RentalRecord(rentId,rentDate,new DateTime(rentDate,numOfRentDay));
              this.vehicleStatus=1;
              return true;
          }
    }
    
    //mintenns
    public boolean performMaintenance()
    {
        if(this.vehicleStatus==1 || this.vehicleStatus==2)
            return false;
        this.vehicleStatus=2;
        return true;
    }
    
    public String toString()
    {
        String repository="";
        if(this.Vehicle_id.contains("V_"))
        {
            repository=this.Vehicle_id+":"+String.valueOf(this.Year)+":"+this.Make+":"+this.Model+":15"+":";
        }
        if(this.Vehicle_id.contains("C_")) {
            repository = this.Vehicle_id + ":" + String.valueOf(this.Year) + ":" + this.Make + ":" + this.Model + ":" + String.valueOf(this.vehicleType.getSeats("car")) + ":";
        }
        switch(this.vehicleStatus)
        {
            case 0:repository+="Available";
                break;
            case 1: repository+="Rented";
                break;
            case 2: repository+="Maintenance";
                break;
        }
        return repository;
    }

   
    public String getDetails()
    {
        String data = "Vehicle ID:\t"+this.Vehicle_id+"\n Year:\t "+String.valueOf(this.Year)+"\n Make:\t"+this.Make+"\n Model:\t"+this.Model+"\n Number of Seats:\t";
		if(this.Vehicle_id.contains("C_"))
			data+=String.valueOf(this.vehicleType.getSeats("car"))+"\n Status:\t";
		else {
            data += String.valueOf(this.vehicleType.getSeats("van")) + "\n Status:\t";
        }
        switch(this.vehicleStatus)
        {
            case 0:data+="Available";
                break;
            case 1: data+="Rented";
                break;
            case 2: data+="Maintenance";
                break;
        }
        return data;

    }

   
    public int getLastElementIndex()
	{
		int index=0;
		for(index=0;this.records[index]!=null;index++);
		return index-1;
	}
}
