package questuinAttr;

/**
 * 选项类： 属性：选项号、选项内容、选项正确性
 */
public class Option {

	public Option() {
	}

	public Option(Character optionId, String optionContent, Boolean correct) {
		this.optionId = optionId;
		this.optionContent = optionContent;
		this.correct = correct;
	}

	private Character optionId;// 选项号
	private String optionContent;// 选项内容
	private Boolean correct;// 选项正确性

	public Character getOptionId() {
		return optionId;
	}

	public void setOptionId(Character optionId) {
		this.optionId = optionId;
	}

	public String getOptionContent() {
		return optionContent;
	}

	public void setOptionContent(String optionContent) {
		this.optionContent = optionContent;
	}

	public Boolean getCorrect() {
		return correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

	@Override
	public String toString() {
		return "Option [optionId=" + optionId + ", optionContent=" + optionContent + ", correct=" + correct + "]";
	}

}
