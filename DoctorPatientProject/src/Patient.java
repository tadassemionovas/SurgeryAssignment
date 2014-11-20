import java.io.Serializable;
import java.util.*;

public class Patient {
	/**
	 * 
	 */
	private int pId;
	private String pName;

	private String pAddress;
	private int pPhone;
	private Date pDOB;
	private ArrayList<PatientHistory> myHistory = new ArrayList<>();

	public Patient(int pId, int doctorId, String fName, String pAddress, int no, Date d) {
		setPId(pId); 
		setPName(fName);
		setPAddress(pAddress);
		setPPhone(no);
		setPDOB(d);
		
	}

	public void setPId(int x){
		this.pId = x;
	}
	public void setPName(String pName) {
		this.pName = pName;
	}

	public void setPAddress(String pAddress) {
		this.pAddress = pAddress;
	}

	public void setPPhone(int pPhone) {
		this.pPhone = pPhone;
	}

	public void setPDOB(Date d) {
		this.pDOB = d;
	}

	public void addPatientHistory(PatientHistory h) {
		myHistory.add(h);
		sortHistoryByDate();

	}
	private void sortHistoryByDate(){
				Collections.sort(myHistory, new Comparator<PatientHistory>() {
		    public int compare(PatientHistory result1, PatientHistory result2) {
		        return result2.getVisitDate().compareTo(result1.getVisitDate());
		    }
		});
	}

	public int getPId() {
		return pId;
	}

	public String getPName() {
		return pName;
	}

	public String getPAddress() {
		return pAddress;
	}

	public int getPPhone() {
		return pPhone;
	}

	public Date getPDOB() {
		return pDOB;
	}

	public ArrayList<PatientHistory> getPatientHistories(){
		return myHistory;
	}

	@SuppressWarnings("unused")
	public String getPatientHistoryReport() {
		String historyReports = "";

		String[] dates = new String[myHistory.size()];
		int i = 0;
		for (PatientHistory h : myHistory) {
			historyReports += h.getHistoryReport()+"\n\n\n";
		}

		return historyReports;
	}

	@SuppressWarnings("deprecation")
	public String toString() {

		return ("ID: " + getPId() + "   " + getPName() + "         DOB: "
				+ getPDOB().getDate() + "/" + getPDOB().getMonth() + "/"
				+ getPDOB().getYear() + "      Phone no: " + getPPhone()+ "\n").toUpperCase();
	}

}
