package dto;

import com.google.gson.annotations.SerializedName;

public class CompanyDto {
    
    @SerializedName("id")
    public String Id;
    
    @SerializedName("name")
    public String Name;
    
    @SerializedName("adress")
    public String Adress;

}

//
//Company ID
//? Name
//? Address
//? City
//? Country
//? E-mail (not required)
//? Phone Number (not required)
//? One or more employees
