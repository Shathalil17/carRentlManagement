
package com.mycompany.start;



class VehicleType {

    private int carSeats;
    private int vanSeats=15;
    private DateTime LastMaintenance;
    private String[] days = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};


    VehicleType(int seats)
    {
        this.carSeats=seats;
    }
 
    VehicleType(int seats,DateTime LastMaintenance){
        this.vanSeats=seats;
        this.LastMaintenance=LastMaintenance;
    }
 
    public int getSeats(String type)
    {
        if(type.equals("car")){
            return this.carSeats;
        }
        else{
            return this.vanSeats;
        }
    }

    
    public int getCarSeats()
    {
        return this.carSeats;
    }

    
    public void setCarSeats(int seats)
    {
        this.carSeats=seats;
    }

   
    private int indexOf(String day){
        for(int index=0;index<days.length;index++)
            if(days[index].equals(day))
                return index;
        return -1;
    }

    
    public DateTime getLastMaintenance(){
        return this.LastMaintenance;
    }

    
    public void setLastMaintenance(DateTime date)
    {
        this.LastMaintenance=date;
    }


    public int canBeRentedForMinimumDays(DateTime date,String type)
    {
        if(this.indexOf(date.getNameOfDay())+1<=5 && this.indexOf(date.getNameOfDay())+1>=1 && type.equals("car")){
            return 2;
        }
        else if(type.equals("car")){
            return 3;
        }
        else{
            return 1; 
        }
    }

    
    public boolean IsUnderMaintenance(DateTime rentDate,String type,int numOfRentDays)
    {
        DateTime nextMaintenance=new DateTime(this.LastMaintenance,12);
        if(type.equals("van") && DateTime.diffDays(nextMaintenance,new DateTime(rentDate,numOfRentDays))>=0 && numOfRentDays<=12)
        {
            return false;
        }
		if(type.equals("car"))
		{
			return false;
		}
        else
        {
            return true;
        }
    }
}

