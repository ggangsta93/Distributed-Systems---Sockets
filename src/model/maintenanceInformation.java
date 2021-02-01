package model; 
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class maintenanceInformation {

	private String fechaHora= "";
	private String osName = System.getProperty("os.name").toString();
	private String osVersion = System.getProperty("os.version").toString();
	private String osArchitecture = System.getProperty("os.arch").toString();
	private String userName = System.getProperty("user.name").toString();
	private String userLanguage = System.getProperty("user.language").toString();
	private String sunCpuEndian = System.getProperty("sun.cpu.endian").toString();

	
	public maintenanceInformation() {
		Date date=new Date();
		DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		this.setFechaHora(hourdateFormat.format(date).toString());		
	}
	
	public String getOsName() {
		return osName;
	}


	public void setOsName(String osName) {
		this.osName = osName;
	}


	public String getOsVersion() {
		return osVersion;
	}


	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}


	public String getOsArchitecture() {
		return osArchitecture;
	}


	public void setOsArchitecture(String osArchitecture) {
		this.osArchitecture = osArchitecture;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserLanguage() {
		return userLanguage;
	}


	public void setUserLanguage(String userLanguage) {
		this.userLanguage = userLanguage;
	}


	public String getSunCpuEndian() {
		return sunCpuEndian;
	}


	public void setSunCpuEndian(String sunCpuEndian) {
		this.sunCpuEndian = sunCpuEndian;
	}


	public String getFechaHora() {
		return fechaHora;
	}


	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}
	
}
