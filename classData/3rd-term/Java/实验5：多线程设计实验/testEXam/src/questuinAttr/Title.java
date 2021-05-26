package questuinAttr;

public class Title {
	private String titleString;
	private Integer idInteger;
	public String getTitleString() {
		return titleString;
	}
	public void setTitleString(String titleString) {
		this.titleString = titleString;
	}
	public Integer getIdInteger() {
		return idInteger;
	}
	public void setIdInteger(Integer idInteger) {
		this.idInteger = idInteger;
	}
	public Title(String titleString, Integer idInteger) {
		this.titleString = titleString;
		this.idInteger = idInteger;
	}
	public Title() {
		// TODO Auto-generated constructor stub
	}
	
}
