package questuinAttr;

public enum QuestionType {
	SINGLE(0,"��ѡ��") ,
	MUlTIPLE(1,"��ѡ��"),
	ADJUST(2,"�ж���");
	
	private Integer code;
	private String message;
	
	QuestionType(Integer code,String message){
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}
}
