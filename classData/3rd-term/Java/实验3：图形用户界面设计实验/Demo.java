import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;

public class Demo{

	private JFrame frame;
	private JPanel contentPane;
	// ��׼��
	private int[][] RightAnswer = {
	{0,0,1,0},{1,0,0,0},{0,1,0,0},{0,0,0,1},{0,0,1,0},
	{1,1,0,0},{0,0,1,1},{1,0,1,0},{1,1,1,0},{0,0,1,1},
	{1,0,0,0},{0,1,0,0},{1,0,0,0},{1,0,0,0},{0,1,0,0}
	};
	// ѡ���
	private int[][] SeclectAnswer = new int[100][4];
	//�ܷ�
	private int tot = 0;

	//����
	private JLabel[] aLabel = new JLabel[5];
	private JLabel[] bLabel = new JLabel[5];
	private JLabel[] cLabel = new JLabel[5];
	//��Ŀ�İ�ť
	private JRadioButton[] aRadioButton = new JRadioButton[20];
	private JCheckBox[] bcheckbox = new JCheckBox[20];
	private JRadioButton[] cRadioButton = new JRadioButton[10];
	// ���ư�ť���õ�λ��
	private int x = 0,y = 22,h = 40,w = 300;
	//��ѡ��ͻ
	private ButtonGroup[] aRGroup = new ButtonGroup[5];//����ѡ�����group��
	private ButtonGroup[] cRGroup = new ButtonGroup[5];
	
	// �ύ��ť
	private JButton submit = new JButton("�ύ");
	// ������ʾ��
	private JLabel score = new JLabel();
	// ��ȡ�ļ�
    File file = new File("C:\\Users\\Dawn\\Desktop\\test\\�����2.txt");
	//File file = new File("D:\\Desktop\\�����2.txt");//��������Դ
	File ans = new File("C:\\Users\\Dawn\\Desktop\\test\\��������.txt");
    // ��һ��
	��ʦ�з�����
	
	/*
	 * ��ָ���ļ��ж�ȡ��Ŀѡ��ʹ�
	 */
	void readFile()
	{
		String s,question,choiceA,choiceB,choiceC,choiceD,answer;
		try
		{
			int count=0;
			FileInputStream in=new FileInputStream(file);//����Ŀ�ļ�
			byte b[]=new byte[in.available()];
			in.read(b);
			s=new String(b);
			
			// �ָ���Ŀ��ѡ��
			String c[]=s.split("&&");
			
			for(int i=0;i<c.length;i++)
			{
				// ������Ŀѡ��ʹ�
				question = c[i++];
				choiceA = c[i++];
				choiceB = c[i++];
				choiceC = c[i++];
				choiceD = c[i++];
				answer = c[i];
				questions[count++] = new QuestionBank(question,choiceA,choiceB,choiceC,choiceD,answer);
			}
		}catch(Exception e1){ e1.printStackTrace();}
	}

	// ������
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// ��������
					Demo window = new Demo();
					// ���ÿɼ�
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Demo() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("�����Ծ�");
		contentPane = new JPanel();//���һ������
		frame.setBounds(100, 100, 800, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//����ر�
		
		//��ӹ�����
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 782, 829);
		scrollPane.setViewportView(contentPane);
		contentPane.setPreferredSize(new Dimension(1000,2460));//ǿ���޸Ĵ�С
		contentPane.setLayout(null);
		frame.getContentPane().add(scrollPane);//������������
		
		submit.setBounds(250, 2400, 100, 50);
		score.setBounds(400,2000,350, 50);
		// ���Ӱ�ť���
		contentPane.add(submit);
		contentPane.add(score);
		//�¼�����
		submit.addMouseListener(new MouseAdapter() { 
			// ���������ťѡ����
			public void mouseClicked(MouseEvent e) {
				int j = -1;
				for(int i=0;i<aRadioButton.length;i++) {
					if(i % 4 == 0) ++j;
					if(aRadioButton[i].isSelected()) SeclectAnswer[j][i%4] = 1;
				}
				for(int i=0;i<bcheckbox.length;i++) {
					if(i % 4 == 0) ++j;
					if(bcheckbox[i].isSelected()) SeclectAnswer[j][i%4] = 1;
				}
				for(int i=0;i<cRadioButton.length;i++) {
					if(i % 2 == 0) ++j;
					if(cRadioButton[i].isSelected()) SeclectAnswer[j][i%2] = 1;
				}
				for(int i=0;i<15;i++) {
					boolean plas = true;
					for(int z=0;z<4 && plas;z++) {
						if(RightAnswer[i][z] != SeclectAnswer[i][z]) plas = false;
					}
					if(plas) tot += 5;
				}
				// ��ʾ���ķ���
				score.setText("���Ϊ��" + tot);
				// ��������
				score.setFont(new Font("����",Font.BOLD,35));
			}
			
		});
		//�������͵�����
		danXuan();
		duoXuan();
		xuanZe();
		
	}
	/*
	 * ��ѡ
	 * */
	public void danXuan() {
		JLabel lblNewLabel = new JLabel("��ѡ��");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel.setBounds(0, 0, 84, 18);
		//  �������ӱ�ǩ
		contentPane.add(lblNewLabel);
		
		for(int i=0,j=-1;i<aRadioButton.length;i++) {
			if(i % 4 == 0) {
				aRGroup[++j] = new ButtonGroup();
				aLabel[j] = new JLabel();
				aLabel[j].setBounds(x, y, 400, 18);
				y += 22;
				contentPane.add(aLabel[j]);
			}
			aRadioButton[i] = new JRadioButton();
			aRadioButton[i].setBounds(x, y, w, h);
			y += 40;
			contentPane.add(aRadioButton[i]);
			aRGroup[j].add(aRadioButton[i]);
		}
		// ������Ŀ
		aLabel[0].setText("1: 1 + 1 = ��");
		aRadioButton[0].setText("A: 4");
		aRadioButton[1].setText("B: 3");
		aRadioButton[2].setText("C: 2");
		aRadioButton[3].setText("D: 1");
		
		aLabel[1].setText("2: 1+1=?");
		aRadioButton[4].setText("A: 4");
		aRadioButton[5].setText("B: 3");
		aRadioButton[6].setText("C: 2");
		aRadioButton[7].setText("D: 1");
		
		aLabel[2].setText("3: 1+1=?");
		aRadioButton[8].setText("A: 1");
		aRadioButton[9].setText("B: 2");
		aRadioButton[10].setText("C: 3");
		aRadioButton[11].setText("D: 4");
		
		aLabel[3].setText("4:1+1=��");
		aRadioButton[12].setText("A: 2");
		aRadioButton[13].setText("B: 1");
		aRadioButton[14].setText("C: 3");
		aRadioButton[15].setText("D: 4");
		
		aLabel[4].setText("5: 1+1=��");
		aRadioButton[16].setText("A:1");
		aRadioButton[17].setText("B:2");
		aRadioButton[18].setText("C:3");
		aRadioButton[19].setText("D:3");
	}
	/*
	 * ��ѡ
	 * */
	public void duoXuan() {
		JLabel lblNewLabel_1 = new JLabel("��ѡ��");
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel_1.setBounds(0, y, 84, 18);
		contentPane.add(lblNewLabel_1);
		y += 22;
		for(int i=0,j=-1;i<aRadioButton.length;i++) {
			if(i % 4 == 0) {
				bLabel[++j] = new JLabel();
				bLabel[j].setBounds(x, y, 400, 18);
				y += 22;
				contentPane.add(bLabel[j]);
			}
			bcheckbox[i] = new JCheckBox();
			bcheckbox[i].setBounds(x, y, w, h);
			y += 40;
			contentPane.add(bcheckbox[i]);
		}
		// ��ѡbox����
		bLabel[0].setText("1: ab");
		bcheckbox[0].setText("A: a");
		bcheckbox[1].setText("B: b");
		bcheckbox[2].setText("C: c");
		bcheckbox[3].setText("D: d");
		
		bLabel[1].setText("2: ad");
		bcheckbox[4].setText("A: a");
		bcheckbox[5].setText("B: b");
		bcheckbox[6].setText("C: c");
		bcheckbox[7].setText("D: d");
		
		bLabel[2].setText("3: bd");
		bcheckbox[8].setText("A: a");
		bcheckbox[9].setText("B: b");
		bcheckbox[10].setText("C: c");
		bcheckbox[11].setText("D: d");
		
		bLabel[3].setText("4: bc");
		bcheckbox[12].setText("A: a");
		bcheckbox[13].setText("B: b");
		bcheckbox[14].setText("C: c");
		bcheckbox[15].setText("D: d");
		
		bLabel[4].setText("5: cd");
		bcheckbox[16].setText("A: a");
		bcheckbox[17].setText("B: b");
		bcheckbox[18].setText("C: c");
		bcheckbox[19].setText("D: d");
	}
	/*
	 * �ж�
	 * */
	public void xuanZe() {
		JLabel lblNewLabel_1 = new JLabel("�ж���");
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel_1.setBounds(0, y, 84, 18);
		contentPane.add(lblNewLabel_1);
		y += 22;
		for(int i=0,j=-1;i<cRadioButton.length;i++) {
			if(i % 2 == 0) {
				cRGroup[++j] = new ButtonGroup();
				cLabel[j] = new JLabel();
				cLabel[j].setBounds(x, y, 400, 18);
				y += 22;
				contentPane.add(cLabel[j]);
			}
			cRadioButton[i] = new JRadioButton();
			cRadioButton[i].setBounds(x, y, w, h);
			y += 40;
			contentPane.add(cRadioButton[i]);
			cRGroup[j].add(cRadioButton[i]);
		}
		// �жϰ�ť����+ ��Ŀ���� 
		for(Integer i=1;i<=5;i++)
		{
			cLabel[i-1].setText(i.toString()+":1+1=?");
		}
		for(int i=0;i<10;i++)
		{
			if((i+1)%2==0)
			{
				cRadioButton[i].setText("��");
			}else {
				cRadioButton[i].setText("��");
			}
		}
	}
	
}