package questuinAttr;

import java.util.List;

/**
 *    �����ࣺ
 *    ���ԣ���Ŀ���͡���Ŀ���ơ���Ŀ��š���Ŀ��������Ŀѡ��
 *    ������ͳ�Ƶ÷֡��ж���Ŀ�Ƿ���ȷ
 */
/**
 * ��װ��question����ʵ���ࡣ get��set������ԭ��
 */
public class Question {
	// ��������
	public Question() {

	}
	private String id;// ��Ŀ���
	private Integer type;// ��Ŀ����
	private String content;// ��Ŀ����
	private Integer score;// ��Ŀ����
	private List<Option> optionList;// ��Ŀѡ��
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public List<Option> getOptionList() {
		return optionList;
	}
	public void setOptionList(List<Option> optionList) {
		this.optionList = optionList;
	}



}
