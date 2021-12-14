import java.sql.Date;

import javax.xml.crypto.Data;

public class Borrow {
	private int r_Id;
	private int b_Id;
	private Date borrow_Date;
	public int getR_Id() {
		return r_Id;
	}
	public void setR_Id(int r_Id) {
		this.r_Id = r_Id;
	}
	public int getB_Id() {
		return b_Id;
	}
	public void setB_Id(int b_Id) {
		this.b_Id = b_Id;
	}
	public Date getBorrow_Date() {
		return borrow_Date;
	}
	public void setBorrow_Date(Date borrow_Date) {
		this.borrow_Date = borrow_Date;
	}
	
}
