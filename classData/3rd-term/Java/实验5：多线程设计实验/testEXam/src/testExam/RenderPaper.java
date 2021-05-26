package testExam;

import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import questuinAttr.Option;
import questuinAttr.Question;
import questuinAttr.QuestionType;
import questuinAttr.Title;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;

import javax.swing.JFrame;

public class RenderPaper implements ActionListener {
	// 三种题目的数量,题目总数
	public int cntSingle = 0, cntMul = 0, cntPanduan = 0, totCnt = 50;
	private int tot=0;//题目得分
	// 三种题目的选项
	private JRadioButton[] aRadioButton = new JRadioButton[300];
	private JCheckBox[] bcheckbox = new JCheckBox[120];
	private JRadioButton[] cRadioButton = new JRadioButton[300];

	private int[][] SeclectAnswer = new int[100][5];// 选择的答案
	private List<Question> questionsList = new ArrayList<Question>();// 问题列表
	private List<Title> titleList = new ArrayList<Title>();// 标题列表
	private List<String> words = new ArrayList<String>();// 文件读取存放数组

	// GUI组件
	public JFrame frame;// 窗体
	private JPanel jp;// 容器
	private LoadingWindow loading;// 进度条
	private Menu fileMenu = new Menu("选择文件");// 菜单条
	private MenuItem fileOpen = new MenuItem("选择");
	private MenuItem fileExit = new MenuItem("退出");
	private int x = 0, y = 22;
	public JButton submit = new JButton("提交");
	private JLabel score = new JLabel();

	// 中间变量
	String anwser = "";
	String examTitle = "";

	/* 主函数 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RenderPaper window = new RenderPaper();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/* 构造函数 */
	public RenderPaper() {
		this.initWindow();
	}

	/* 初始化窗口 */
	public void initWindow() {
		//初始化窗体
		frame = new JFrame();
		frame.setBounds(300, 25, 900, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jp = new JPanel();
		jp.setPreferredSize(new Dimension(1000, 12500));
		// 菜单条
		fileMenu.add(fileOpen);
		fileOpen.addActionListener(this);
		fileMenu.addSeparator();
		fileMenu.add(fileExit);
		fileExit.addActionListener(this);
		MenuBar menu = new MenuBar();
		menu.add(fileMenu);
		menu.setFont(new Font("宋体", Font.BOLD, 16));
		frame.setMenuBar(menu);
		//滑动条
		JScrollPane sp = new JScrollPane(jp);
		jp.setLayout(null);
		sp.setBounds(0, 0, 935, 790);
		
		frame.getContentPane().add(sp);
		frame.setVisible(true);
	}

	/* 渲染 */
	public void render() {
		// 渲染试卷总标题
		JLabel title1 = new JLabel(examTitle);
		title1.setFont(new Font("宋体", Font.BOLD, 22));
		title1.setBounds(x, y, 500, 20);
		y += 40;
		jp.add(title1);
		// 渲染题目
		this.renderQuestionType();
		// 渲染提交得分按钮
		this.renderSubmit();
	}

	/* 渲染题目 */
	public void renderQuestionType() {
		for (Title title : titleList) {
			String titleString = title.getTitleString();
			JLabel aJLabel = new JLabel(titleString);
			aJLabel.setFont(new Font("宋体", Font.BOLD, 22));
			aJLabel.setBounds(x, y, 800, 20);
			y += 40;
			jp.add(aJLabel);
			if (titleString.contains("单选题")) {
				this.renderSingle();
			} else if (titleString.contains("多选题")) {
				this.renderMul();
			} else {
				this.renderJud();
			}
		}
	}

	/* 渲染单选题 */
	public void renderSingle() {
		for (Question question : questionsList) {
			if (question.getType() == QuestionType.SINGLE.getCode()) {
				this.renderQuesStem(question.getContent());//题干
				this.renderOption(question.getOptionList(), 1);//选项
			}
		}
	}

	/* 渲染多选题 */
	public void renderMul() {
		for (Question question : questionsList) {
			if (question.getType() == QuestionType.MUlTIPLE.getCode()) {
				this.renderQuesStem(question.getContent());//题干
				this.renderCheckBox(question.getOptionList());//选项
			}
		}
	}

	/* 渲染判断题 */
	public void renderJud() {
		for (Question question : questionsList) {
			if (question.getType() == QuestionType.ADJUST.getCode()) {
				this.renderQuesStem(question.getContent());//题干
				this.renderOption(question.getOptionList(), 3);//选项
			}
		}
	}

	/* 渲染提交按钮 */
	public void renderSubmit() {
		// 增加按钮组件
		jp.add(submit);
		jp.add(score);
		// 事件监听
		submit.setForeground(Color.LIGHT_GRAY);
		submit.setFont(new Font("宋体", Font.BOLD, 20));
		submit.setBounds(250, y, 100, 50);
		submit.addMouseListener(new MouseAdapter() {
			// 鼠标点击，按钮选择处理
			public void mouseClicked(MouseEvent e) {
				Choose();
				Count();
				score.setText("当前得分：" + tot);// 显示最后的分数
				score.setForeground(Color.red);
				tot = 0;
				score.setFont(new Font("宋体", Font.BOLD, 35));// 设置字体
				score.setBounds(400, y, 350, 50);
			}
		});
	}

	/* 渲染题干 */
	public void renderQuesStem(String tmpString) {
		String[] string = tmpString.split("\t");
		JLabel aJLabel = new JLabel(string[0]);
		aJLabel.setFont(new Font("宋体", Font.BOLD, 20));
		aJLabel.setBounds(x, y, 8000, 20);
		y += 40;
		jp.add(aJLabel);
		if (string.length > 1) {
			for (int i = 1; i < string.length; i++) {
				JLabel tmpJLabel = new JLabel(string[i]);
				tmpJLabel.setFont(new Font("宋体", Font.BOLD, 18));
				tmpJLabel.setBounds(x, y, 800, 20);
				y += 40;
				jp.add(tmpJLabel);
			}
		}
	}

	/* 渲染checBox */
	public void renderCheckBox(List<Option> optionList) {
		for (int i = 0; i < optionList.size(); i++) {
			String idString = optionList.get(i).getOptionId().toString();
			idString += '.';
			int cnt = cntMul * 4 + i;
			bcheckbox[cnt] = new JCheckBox(idString + optionList.get(i).getOptionContent());
			bcheckbox[cnt].setBounds(x, y, 500, 50);
			y += 50;
			jp.add(bcheckbox[cnt]);
		}
		cntMul++;
		loading.amount++;
	}

	/* 渲染选项 */
	public void renderOption(List<Option> optionList, int code) {
		ButtonGroup aButtonGroup = new ButtonGroup();
		if (code == 1) {
			for (int i = 0; i < optionList.size(); i++) {
				String idString = optionList.get(i).getOptionId().toString();
				idString += '.';
				int cnt = cntSingle * 4 + i;
				aRadioButton[cnt] = new JRadioButton(idString + optionList.get(i).getOptionContent());
				aRadioButton[cnt].setBounds(x, y, 500, 50);
				y += 50;
				jp.add(aRadioButton[cnt]);
				aButtonGroup.add(aRadioButton[cnt]);
			}
			cntSingle++;
			loading.amount++;
		} else {
			for (int i = 0; i < optionList.size(); i++) {
				String idString = optionList.get(i).getOptionId().toString();
				idString += '.';
				int cnt = cntPanduan * 2 + i;
				cRadioButton[cnt] = new JRadioButton(idString + optionList.get(i).getOptionContent());
				cRadioButton[cnt].setBounds(x, y, 500, 50);
				y += 50;
				jp.add(cRadioButton[cnt]);
				aButtonGroup.add(cRadioButton[cnt]);
			}
			cntPanduan++;
			loading.amount++;
		}
	}

	/* 得到所选选项 */
	public void Choose() {
		int j = -1;
		for (int i = 0; i < cntSingle * 4; i++) {
			if (i % 4 == 0)
				++j;
			if (SeclectAnswer[j][i % 4] == 1)
				SeclectAnswer[j][i % 4] = 0;
			if (aRadioButton[i].isSelected()) {
				SeclectAnswer[j][i % 4] = 1;
			}
		}
		for (int i = 0; i < cntMul * 4; i++) {
			if (i % 4 == 0)
				++j;
			if (SeclectAnswer[j][i % 4] == 1)
				SeclectAnswer[j][i % 4] = 0;
			if (bcheckbox[i].isSelected()) {
				SeclectAnswer[j][i % 4] = 1;
			}
		}
		for (int i = 0; i < cntPanduan * 2; i++) {
			if (i % 2 == 0)
				++j;
			if (SeclectAnswer[j][i % 2] == 1)
				SeclectAnswer[j][i % 2] = 0;
			if (cRadioButton[i].isSelected())
				SeclectAnswer[j][i % 2] = 1;
		}
	}

	/* 统计分数 */
	public void Count() {
		for (int i = 0; i < cntSingle + cntMul; i++) {
			boolean flag = true;
			for (int z = 0; z < 4 && flag; z++) {
				Option tmpOption = questionsList.get(i).getOptionList().get(z);
				if ((SeclectAnswer[i][z] == 1 && tmpOption.getCorrect() == false)
						|| (tmpOption.getCorrect() == true && SeclectAnswer[i][z] == 0))
					flag = false;
			}
			if (flag) {
				tot += 1;
			}
		}
		for (int i = cntSingle + cntMul; i < cntPanduan + cntMul + cntSingle; i++) {
			boolean flag = true;
			for (int z = 0; z < 2 && flag; z++) {
				Option tmpOption = questionsList.get(i).getOptionList().get(z);

				if ((SeclectAnswer[i][z] == 1 && tmpOption.getCorrect() == false)
						|| (tmpOption.getCorrect() == true && SeclectAnswer[i][z] == 0))
					flag = false;
			}
			if (flag) {
				tot += 1;
			}
		}
	}

	/* 获得题目 */
	public void getQuestion(String file) {
		words = readFile(file);
		// 处理每一道题
		Question currentQuestion = null;
		Integer currentType = null;
		Integer currentScore = null;
		// 读标题
		examTitle = words.get(0);
		for (int i = 1; i < words.size() - 2; i++) {
			String word = words.get(i);
			// 读取题目标题
			if (word.contains("单选题") || word.contains("多选题") || word.contains("判断题")) {
				currentType = getQuestionTitle(word, i);
				currentScore = getQuestionScore(word);
				continue;
			}
			// 判断每一行第一个字符,如果是数字则代表是题目内容,反之是题目选项
			if (Character.isDigit(word.charAt(0))) {
				currentQuestion = new Question();// 创建新题目
				currentQuestion.setType(currentType);// 设置题目类型
				currentQuestion.setScore(currentScore);// 设置题目得分
				currentQuestion.setId(word.split("、")[0]);// 设置题目Id
				String contentString = "";// 设置题目内容
				// 补充题干,如果当前行是字符,则累加
				while (words.get(i).charAt(0) != 'A' && words.get(i).charAt(1) != '.') {
					contentString += words.get(i);
					i++;
				}
				StringBuilder cBuilder = new StringBuilder(contentString);
				cBuilder = handleContent(cBuilder);
				currentQuestion.setContent(cBuilder.toString());
				i--;
			} else {
				List<Option> optionList = new ArrayList<>();// 获取题目选项列表
				// 处理题目选项
				while (i < words.size() - 2 && Character.isLetter(words.get(i).charAt(0))
						&& words.get(i).charAt(0) != '一' && words.get(i).charAt(0) != '二'
						&& words.get(i).charAt(0) != '三') {
					Option currentOption = new Option();
					Character currentId = words.get(i).charAt(0);
					currentOption.setOptionId(currentId);
					if (anwser.contains(currentId.toString()))
						currentOption.setCorrect(true);
					else
						currentOption.setCorrect(false);
					currentOption.setOptionContent(words.get(i).substring(2));
					optionList.add(currentOption);
					i++;
					if (i > words.size() - 1) {
						System.out.println("试卷文件读取成功！");
						currentQuestion.setOptionList(optionList);
						questionsList.add(currentQuestion);
						return;
					}
				}
				i--;
				// 处理完毕
				currentQuestion.setOptionList(optionList);
				questionsList.add(currentQuestion);
				anwser = "";
			}
		}
	}

	/* 读文件 */
	public List<String> readFile(String file) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String linestr;// 按行读取 将每次读取一行的结果赋值
			while ((linestr = br.readLine()) != null) {
				words.add(linestr);
			}
			br.close();// 关闭IO
		} catch (Exception e) {
			System.out.println("文件操作失败");
			e.printStackTrace();
		}
		return words;
	}

	/* 获得题目类型 */
	public Integer getQuestionTitle(String word, int i) {
		Integer currentQuestionType = null;
		if (word.contains("单选题")) {
			currentQuestionType = QuestionType.SINGLE.getCode();
			titleList.add(new Title(word, i));
		} else if (word.contains("多选题")) {
			currentQuestionType = QuestionType.MUlTIPLE.getCode();
			titleList.add(new Title(word, i));
		} else if (word.contains("判断题")) {
			currentQuestionType = QuestionType.ADJUST.getCode();
			titleList.add(new Title(word, i));
		}
		return currentQuestionType;
	}

	/* 获得题目分数 */
	public Integer getQuestionScore(String word) {
		Pattern pattern = Pattern.compile("每题(.*?)分");
		Matcher matcher = pattern.matcher(word);
		if (matcher.find()) {
			String w = word.substring(matcher.start(), matcher.end());
			w.substring(2, w.length() - 1);
			return Integer.valueOf(w.substring(2, w.length() - 1));
		}
		return new Integer(-1);
	}

	/* 处理题干内容并获得答案 */
	public StringBuilder handleContent(StringBuilder s) {
		for (int i = 0; i < s.length(); i++) {
			int f = 0;
			if (s.charAt(i) == '(' || s.charAt(i) == '（') {
				for (int l = i;; l++) {
					char c = s.charAt(l);
					if (c >= 65 && c <= 90)// 找到答案选项
					{
						anwser += s.charAt(l);
						f = 1;
						s.setCharAt(l, ' ');
					}
					if (s.charAt(l) == '正' || s.charAt(l) == '错') {
						if (s.charAt(l) == '正')
							anwser = "A";
						else
							anwser = "B";
						f = 1;
						s.setCharAt(l, ' ');
						s.setCharAt(l + 1, ' ');
					}
					if (s.charAt(l) == ')' || s.charAt(l) == '）')
						break; // 匹配到')' 结束题干的处理
				}
			}
			if (f == 1)
				break;
		}
		return s;
	}

	/*倒计时*/
	public void countDown() {
		CountDown countDown = new CountDown(jp, 100 * 60, this);
		countDown.execute(500, 0);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == fileExit)
			System.exit(0);
		else if (e.getSource() == fileOpen) {
			FileDialog fd = new FileDialog(frame, "Open File", FileDialog.LOAD);// 以open file创建一个读取文件的文件对话框窗口。
			fd.setVisible(true);// 设置其可见
			if (fd.getFile() != null) {
				File file = new File(fd.getDirectory() + fd.getFile());// 文件路径
				if (file.exists())// 检测文件是否存在
				{
					frame.setVisible(false);
					getQuestion(file.toString());
					/*进度条*/
					loading = new LoadingWindow(this);
					loading.start();
				} else
					System.out.println("文件是无效的！");
			}
			render();
			fd.dispose();

		}
	}

}
