import java.io.Serializable;
import java.util.Date;

public class PatientHistory implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1441596920825720365L;
	@SuppressWarnings("unused")
	static private int historyId = 1;
	private int hId;
	private int doctorId;
	private int pId;
	private Date visitDate;
	private String description;
	private String medicine;
	private String procedures;

	public PatientHistory(int hId,int doctorId, int pId, String description,
		String medicine, String procedure, Date visitDate) {
		
		setHId(hId);
		setDocorId(doctorId);
		setPatientId(pId);
		setDescription(description);
		setMedicine(medicine);
		setProcedure(procedure);
		setVisitDate(visitDate);
		historyId++;
	}
	public void setHId(int hid){
		this.hId  = hid;
	}

	public void setDocorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public void setPatientId(int pId) {
		this.pId = pId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}

	public void setProcedure(String procedure) {
		this.procedures = procedure;
	}

	public void setVisitDate(Date d) {
		this.visitDate = d;
	}

	public int getDocorId() {
		return doctorId;
	}

	public int getPatientId() {
		return pId;
	}

	public String getDescription() {
		return description;
	}

	public String getMedicine() {
		return medicine;
	}

	public String getProcedure() {
		return procedures;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	@SuppressWarnings("deprecation")
	public String getHistoryReport() {
		String report = "Visit Date:\t" + getVisitDate().getDate() + "/"
				+ getVisitDate().getMonth() + "/" + getVisitDate().getYear()
				+ "\nMedicine:\t" + getMedicine() + "\nProcedure:\t"
				+ getProcedure() + "\nDescription:\t" + getDescription();

		return report;
	}

}
