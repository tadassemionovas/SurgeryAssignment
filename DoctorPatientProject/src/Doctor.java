import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Doctor  {

	/**
	 * 
	 */
	private static int thisdoctorid = 0;
	private int doctorId;
	private String doctorName;
	private ArrayList<Patient> pList = new ArrayList<>();
	private String doctorPassword;
	@SuppressWarnings("unused")

	public Doctor(int id, String doctorName, String doctorPassword) {
		setDoctorName(doctorName);
		setDoctorPassword(doctorPassword);
		setDoctorId(id);
	}

	public void setDoctorId(int x) {
		this.doctorId = x;
	}

	public void setDoctorName(String x) {
		this.doctorName = x;
	}

	public void setDoctorPassword(String x) {
		this.doctorPassword = x;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public String getDoctorPassword() {
		return doctorPassword;
	}

	public ArrayList<Patient> getPList() {
		return pList;
	}

	public void restorePList(ArrayList<Patient> list) {
		pList = list;
	}

	public void addPatient(Patient p) {

		pList.add(p);
	}
	private void sortPatientsByName(){
		Collections.sort(pList, new Comparator<Patient>() {
			public int compare(Patient result1, Patient result2) {
				return result1.getPName().compareTo(result2.getPName());
			}
		});
	}

	public void updatePatient(Patient p) {

		for (Patient o : pList) {
			if (o.getPId() == p.getPId()) {
				o = p;
				break;
			}
		}
	}

	public String listPatiens() {
		String patientsList = "";

		for (Patient p : pList) {
			patientsList += p.toString() + "\n";

		}

		return patientsList;
	}

	public int searchForPatientName(String fullName) {
		int id = 0;

		for (Patient p : pList) {
			if (p.getPName().compareToIgnoreCase(fullName) == 0) {
				id = p.getPId();
				break;
			}
		}
		return id;
	}
	public int searchForPatientId(int patientId) {
		int id = 0;

		for (Patient p : pList) {
			if (p.getPId() == patientId) {
				id = p.getPId();
				break;
			}
		}
		return id;
	}


	public Patient getPatient(int x) {
		Patient tempP = null;

		for (Patient p : pList) {
			if (p.getPId() == x) {
				tempP = p;
				break;
			}
		}

		return tempP;
	}

	public Patient getPatient(String x) {
		Patient tempP = null;

		for (Patient p : pList) {
			if (p.getPName().compareToIgnoreCase(x) == 0) {
				tempP = p;
				break;
			}
		}

		return tempP;
	}
	public String printPatientsAndHistory(){
		sortPatientsByName();
		
		String s = "";
		
		for(Patient p: pList){
			s+=p.toString() + "\n\n" +p.getPatientHistoryReport();
		}
		
		return s;
		
	}
	public ArrayList<Patient> getPatients(){
		return pList;
	}
}
