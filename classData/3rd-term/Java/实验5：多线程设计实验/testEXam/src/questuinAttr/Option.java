package questuinAttr;

/**
 * ѡ���ࣺ ���ԣ�ѡ��š�ѡ�����ݡ�ѡ����ȷ��
 */
public class Option {

	public Option() {
	}

	public Option(Character optionId, String optionContent, Boolean correct) {
		this.optionId = optionId;
		this.optionContent = optionContent;
		this.correct = correct;
	}

	private Character optionId;// ѡ���
	private String optionContent;// ѡ������
	private Boolean correct;// ѡ����ȷ��

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
