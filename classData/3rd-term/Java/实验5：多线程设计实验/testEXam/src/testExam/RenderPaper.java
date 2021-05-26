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
	// ������Ŀ������,��Ŀ����
	public int cntSingle = 0, cntMul = 0, cntPanduan = 0, totCnt = 50;
	private int tot=0;//��Ŀ�÷�
	// ������Ŀ��ѡ��
	private JRadioButton[] aRadioButton = new JRadioButton[300];
	private JCheckBox[] bcheckbox = new JCheckBox[120];
	private JRadioButton[] cRadioButton = new JRadioButton[300];

	private int[][] SeclectAnswer = new int[100][5];// ѡ��Ĵ�
	private List<Question> questionsList = new ArrayList<Question>();// �����б�
	private List<Title> titleList = new ArrayList<Title>();// �����б�
	private List<String> words = new ArrayList<String>();// �ļ���ȡ�������

	// GUI���
	public JFrame frame;// ����
	private JPanel jp;// ����
	private LoadingWindow loading;// ������
	private Menu fileMenu = new Menu("ѡ���ļ�");// �˵���
	private MenuItem fileOpen = new MenuItem("ѡ��");
	private MenuItem fileExit = new MenuItem("�˳�");
	private int x = 0, y = 22;
	public JButton submit = new JButton("�ύ");
	private JLabel score = new JLabel();

	// �м����
	String anwser = "";
	String examTitle = "";

	/* ������ */
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

	/* ���캯�� */
	public RenderPaper() {
		this.initWindow();
	}

	/* ��ʼ������ */
	public void initWindow() {
		//��ʼ������
		frame = new JFrame();
		frame.setBounds(300, 25, 900, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jp = new JPanel();
		jp.setPreferredSize(new Dimension(1000, 12500));
		// �˵���
		fileMenu.add(fileOpen);
		fileOpen.addActionListener(this);
		fileMenu.addSeparator();
		fileMenu.add(fileExit);
		fileExit.addActionListener(this);
		MenuBar menu = new MenuBar();
		menu.add(fileMenu);
		menu.setFont(new Font("����", Font.BOLD, 16));
		frame.setMenuBar(menu);
		//������
		JScrollPane sp = new JScrollPane(jp);
		jp.setLayout(null);
		sp.setBounds(0, 0, 935, 790);
		
		frame.getContentPane().add(sp);
		frame.setVisible(true);
	}

	/* ��Ⱦ */
	public void render() {
		// ��Ⱦ�Ծ��ܱ���
		JLabel title1 = new JLabel(examTitle);
		title1.setFont(new Font("����", Font.BOLD, 22));
		title1.setBounds(x, y, 500, 20);
		y += 40;
		jp.add(title1);
		// ��Ⱦ��Ŀ
		this.renderQuestionType();
		// ��Ⱦ�ύ�÷ְ�ť
		this.renderSubmit();
	}

	/* ��Ⱦ��Ŀ */
	public void renderQuestionType() {
		for (Title title : titleList) {
			String titleString = title.getTitleString();
			JLabel aJLabel = new JLabel(titleString);
			aJLabel.setFont(new Font("����", Font.BOLD, 22));
			aJLabel.setBounds(x, y, 800, 20);
			y += 40;
			jp.add(aJLabel);
			if (titleString.contains("��ѡ��")) {
				this.renderSingle();
			} else if (titleString.contains("��ѡ��")) {
				this.renderMul();
			} else {
				this.renderJud();
			}
		}
	}

	/* ��Ⱦ��ѡ�� */
	public void renderSingle() {
		for (Question question : questionsList) {
			if (question.getType() == QuestionType.SINGLE.getCode()) {
				this.renderQuesStem(question.getContent());//���
				this.renderOption(question.getOptionList(), 1);//ѡ��
			}
		}
	}

	/* ��Ⱦ��ѡ�� */
	public void renderMul() {
		for (Question question : questionsList) {
			if (question.getType() == QuestionType.MUlTIPLE.getCode()) {
				this.renderQuesStem(question.getContent());//���
				this.renderCheckBox(question.getOptionList());//ѡ��
			}
		}
	}

	/* ��Ⱦ�ж��� */
	public void renderJud() {
		for (Question question : questionsList) {
			if (question.getType() == QuestionType.ADJUST.getCode()) {
				this.renderQuesStem(question.getContent());//���
				this.renderOption(question.getOptionList(), 3);//ѡ��
			}
		}
	}

	/* ��Ⱦ�ύ��ť */
	public void renderSubmit() {
		// ���Ӱ�ť���
		jp.add(submit);
		jp.add(score);
		// �¼�����
		submit.setForeground(Color.LIGHT_GRAY);
		submit.setFont(new Font("����", Font.BOLD, 20));
		submit.setBounds(250, y, 100, 50);
		submit.addMouseListener(new MouseAdapter() {
			// ���������ťѡ����
			public void mouseClicked(MouseEvent e) {
				Choose();
				Count();
				score.setText("��ǰ�÷֣�" + tot);// ��ʾ���ķ���
				score.setForeground(Color.red);
				tot = 0;
				score.setFont(new Font("����", Font.BOLD, 35));// ��������
				score.setBounds(400, y, 350, 50);
			}
		});
	}

	/* ��Ⱦ��� */
	public void renderQuesStem(String tmpString) {
		String[] string = tmpString.split("\t");
		JLabel aJLabel = new JLabel(string[0]);
		aJLabel.setFont(new Font("����", Font.BOLD, 20));
		aJLabel.setBounds(x, y, 8000, 20);
		y += 40;
		jp.add(aJLabel);
		if (string.length > 1) {
			for (int i = 1; i < string.length; i++) {
				JLabel tmpJLabel = new JLabel(string[i]);
				tmpJLabel.setFont(new Font("����", Font.BOLD, 18));
				tmpJLabel.setBounds(x, y, 800, 20);
				y += 40;
				jp.add(tmpJLabel);
			}
		}
	}

	/* ��ȾchecBox */
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

	/* ��Ⱦѡ�� */
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

	/* �õ���ѡѡ�� */
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

	/* ͳ�Ʒ��� */
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

	/* �����Ŀ */
	public void getQuestion(String file) {
		words = readFile(file);
		// ����ÿһ����
		Question currentQuestion = null;
		Integer currentType = null;
		Integer currentScore = null;
		// ������
		examTitle = words.get(0);
		for (int i = 1; i < words.size() - 2; i++) {
			String word = words.get(i);
			// ��ȡ��Ŀ����
			if (word.contains("��ѡ��") || word.contains("��ѡ��") || word.contains("�ж���")) {
				currentType = getQuestionTitle(word, i);
				currentScore = getQuestionScore(word);
				continue;
			}
			// �ж�ÿһ�е�һ���ַ�,������������������Ŀ����,��֮����Ŀѡ��
			if (Character.isDigit(word.charAt(0))) {
				currentQuestion = new Question();// ��������Ŀ
				currentQuestion.setType(currentType);// ������Ŀ����
				currentQuestion.setScore(currentScore);// ������Ŀ�÷�
				currentQuestion.setId(word.split("��")[0]);// ������ĿId
				String contentString = "";// ������Ŀ����
				// �������,�����ǰ�����ַ�,���ۼ�
				while (words.get(i).charAt(0) != 'A' && words.get(i).charAt(1) != '.') {
					contentString += words.get(i);
					i++;
				}
				StringBuilder cBuilder = new StringBuilder(contentString);
				cBuilder = handleContent(cBuilder);
				currentQuestion.setContent(cBuilder.toString());
				i--;
			} else {
				List<Option> optionList = new ArrayList<>();// ��ȡ��Ŀѡ���б�
				// ������Ŀѡ��
				while (i < words.size() - 2 && Character.isLetter(words.get(i).charAt(0))
						&& words.get(i).charAt(0) != 'һ' && words.get(i).charAt(0) != '��'
						&& words.get(i).charAt(0) != '��') {
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
						System.out.println("�Ծ��ļ���ȡ�ɹ���");
						currentQuestion.setOptionList(optionList);
						questionsList.add(currentQuestion);
						return;
					}
				}
				i--;
				// �������
				currentQuestion.setOptionList(optionList);
				questionsList.add(currentQuestion);
				anwser = "";
			}
		}
	}

	/* ���ļ� */
	public List<String> readFile(String file) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String linestr;// ���ж�ȡ ��ÿ�ζ�ȡһ�еĽ����ֵ
			while ((linestr = br.readLine()) != null) {
				words.add(linestr);
			}
			br.close();// �ر�IO
		} catch (Exception e) {
			System.out.println("�ļ�����ʧ��");
			e.printStackTrace();
		}
		return words;
	}

	/* �����Ŀ���� */
	public Integer getQuestionTitle(String word, int i) {
		Integer currentQuestionType = null;
		if (word.contains("��ѡ��")) {
			currentQuestionType = QuestionType.SINGLE.getCode();
			titleList.add(new Title(word, i));
		} else if (word.contains("��ѡ��")) {
			currentQuestionType = QuestionType.MUlTIPLE.getCode();
			titleList.add(new Title(word, i));
		} else if (word.contains("�ж���")) {
			currentQuestionType = QuestionType.ADJUST.getCode();
			titleList.add(new Title(word, i));
		}
		return currentQuestionType;
	}

	/* �����Ŀ���� */
	public Integer getQuestionScore(String word) {
		Pattern pattern = Pattern.compile("ÿ��(.*?)��");
		Matcher matcher = pattern.matcher(word);
		if (matcher.find()) {
			String w = word.substring(matcher.start(), matcher.end());
			w.substring(2, w.length() - 1);
			return Integer.valueOf(w.substring(2, w.length() - 1));
		}
		return new Integer(-1);
	}

	/* ����������ݲ���ô� */
	public StringBuilder handleContent(StringBuilder s) {
		for (int i = 0; i < s.length(); i++) {
			int f = 0;
			if (s.charAt(i) == '(' || s.charAt(i) == '��') {
				for (int l = i;; l++) {
					char c = s.charAt(l);
					if (c >= 65 && c <= 90)// �ҵ���ѡ��
					{
						anwser += s.charAt(l);
						f = 1;
						s.setCharAt(l, ' ');
					}
					if (s.charAt(l) == '��' || s.charAt(l) == '��') {
						if (s.charAt(l) == '��')
							anwser = "A";
						else
							anwser = "B";
						f = 1;
						s.setCharAt(l, ' ');
						s.setCharAt(l + 1, ' ');
					}
					if (s.charAt(l) == ')' || s.charAt(l) == '��')
						break; // ƥ�䵽')' ������ɵĴ���
				}
			}
			if (f == 1)
				break;
		}
		return s;
	}

	/*����ʱ*/
	public void countDown() {
		CountDown countDown = new CountDown(jp, 100 * 60, this);
		countDown.execute(500, 0);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == fileExit)
			System.exit(0);
		else if (e.getSource() == fileOpen) {
			FileDialog fd = new FileDialog(frame, "Open File", FileDialog.LOAD);// ��open file����һ����ȡ�ļ����ļ��Ի��򴰿ڡ�
			fd.setVisible(true);// ������ɼ�
			if (fd.getFile() != null) {
				File file = new File(fd.getDirectory() + fd.getFile());// �ļ�·��
				if (file.exists())// ����ļ��Ƿ����
				{
					frame.setVisible(false);
					getQuestion(file.toString());
					/*������*/
					loading = new LoadingWindow(this);
					loading.start();
				} else
					System.out.println("�ļ�����Ч�ģ�");
			}
			render();
			fd.dispose();

		}
	}

}
