package HeartGuide;

public class showRecords {
	private String record_date, schedule, systolic_bp, diastolic_bp, pulse_rate, rec_id;

	
	
	public String getRecord_date() {
		return record_date;
	}



	public String getSchedule() {
		return schedule;
	}



	public String getSystolic_bp() {
		return systolic_bp;
	}



	public String getDiastolic_bp() {
		return diastolic_bp;
	}



	public String getPulse_rate() {
		return pulse_rate;
	}
	
	public String getRec_id() {
		return rec_id;
	}

	public showRecords(String record_date, String schedule, String systolic_bp, String diastolic_bp, String pulse_rate, String rec_id) {
		
		this.record_date = record_date;
		this.schedule = schedule;
		this.systolic_bp = systolic_bp;
		this.diastolic_bp = diastolic_bp;
		this.pulse_rate = pulse_rate;
		this.rec_id = rec_id;
	}
}
