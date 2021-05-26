package questuinAttr;

import java.util.List;

/**
 *    试题类：
 *    属性：题目类型、题目名称、题目编号、题目分数、题目选项
 *    方法：统计得分、判断题目是否正确
 */
/**
 * 封装：question对象实体类。 get、set，开闭原则
 */
public class Question {
	// 函数重载
	public Question() {

	}
	private String id;// 题目编号
	private Integer type;// 题目类型
	private String content;// 题目名称
	private Integer score;// 题目分数
	private List<Option> optionList;// 题目选项
	
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
