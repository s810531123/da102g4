package android.alt.model;


import java.util.List;

public interface AltDAO_interface {

	public boolean insert(Alt alt);

	public boolean update(Alt alt);
	public boolean updateARR_LEA(String st_num);
	
	
	public List<Alt> getAltBySt_num_today(String st_num);

	// 用學號查詢
	public List<Alt> getAltBySt_num(String st_num);

	// 找全部
	public List<Alt> getAll();
	
	public void insertASC(Alt alt);
	
	

}
