/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.Gson;
/**
 *
 * @author Javier Arias
 */
public class InformationManager {
    
    	public static String getCharacteristics() {
		    Gson objConvertidor= new Gson();	    
		    maintenanceInformation objThis= new maintenanceInformation();
		    String JSON = objConvertidor.toJson(objThis);
		    System.out.println(JSON);	  
		    return JSON;
        }
    	
    	public static maintenanceInformation getCharacteristics(String pJSON) {
    	    Gson objConvertidor= new Gson();	    
    	    maintenanceInformation objMaintenanceInformation= objConvertidor.fromJson(pJSON, maintenanceInformation.class);  
    	    return objMaintenanceInformation;
        }
    
}
