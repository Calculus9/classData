package test;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class TestExam extends JFrame {
	private JFrame frame;
	private JPanel contentPane;
	private static String[] words = new String[2000];
	private int posMul,posJud,cntSingle=0,cntMul=0,cntPanduan=0;
	private int[][] RightAnswer = new int [100][4];
	private int[][] SeclectAnswer = new int[100][4];
	private int tot = 0;
	private Utils utils =new Utils();
	private JLabel[] aLabel = new JLabel[50];
	private JLabel[] bLabel = new JLabel[50];
	private JLabel[] cLabel = new JLabel[50];

	private ButtonGroup[] aRGroup = new ButtonGroup[5];
	private JRadioButton[] aRadioButton = new JRadioButton[120];
	private JCheckBox[] bcheckbox = new JCheckBox[120];
	private ButtonGroup[] cRGroup = new ButtonGroup[5];
	private JRadioButton[] cRadioButton = new JRadioButton[120];
	
	private int x = 0,y = 62,h = 40,w = 300;

	private JButton submit = new JButton("�ύ");
	private JLabel score = new JLabel();
	
	public void readFile()
	{
		int m = 0;//words�����±�
        //��ȡ�ļ��� words �ַ���������
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("C:\\Users\\HJY\\Desktop\\�Ծ�����.txt")));
            String linestr;//���ж�ȡ ��ÿ�ζ�ȡһ�еĽ����ֵ
            while ((linestr = br.readLine()) != null) {
                words[m++] = linestr;//��ֵ��������±����
            }
            br.close();//�ر�IO
        } catch (Exception e) {
            System.out.println("�ļ�����ʧ��");
            e.printStackTrace();
        }
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestExam window = new TestExam();// ��������
					window.frame.setVisible(true);// ������ɼ�
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public TestExam() {
		readFile();
		init();
		render();
	}

	public void stemQues(String words,StringBuilder cBuilder,int cnt,String str) {//�����Ϣ
		for(int k=0;k<words.length();k++)
		{
			int f = 0;
			if(words.charAt(k)=='('||words.charAt(k)=='��')
			{
				for(int l=k;;l++)//�������ſ�ʼƥ���
				{
					/*ѡ����---*/
					if(str=="Cho")
						if(Character.isAlphabetic(words.charAt(l)))//�ҵ���ѡ��
						{
							RightAnswer[cntMul+cntSingle][words.charAt(l)-'A']=1;//��Ǵ�
							f=1;
							cBuilder.setCharAt(l,' ');
						}
					/*---ѡ����*/
					/*�ж���---*/
					if (str=="Jud") {
						if(words.charAt(l)=='��'||words.charAt(l)=='��')
						{
							if(words.charAt(l)=='��') RightAnswer[cntPanduan+cntMul+cntSingle][0]=1;//��Ǵ�
							else RightAnswer[cntPanduan+cntMul+cntSingle][1]=1;
							f=1;
							cBuilder.setCharAt(l,' ');
							cBuilder.setCharAt(l+1,' ');
						}
					}
					/*---�ж���*/
					if(words.charAt(l) == ')' || words.charAt(l) =='��') break; //ƥ�䵽')'  ������ɵĴ���
					
				}
			}
			if(f==1)break;
		}
	}
	public int singleQuestion(int x)
	{
		contentPane.add(utils.titleQues(words[x], 30));	
		for(int i = x+1;;i+=5) 
		{
			if(utils.strStr(words[i], "��ѡ��")!=-1){return i;}
			if(Character.isDigit(words[i].charAt(0)))
			{
				StringBuilder cBuilder=new StringBuilder(words[i]);
				utils.stemQues(words[i],cBuilder,cntSingle,"Cho",RightAnswer,cntSingle,0,0);
				//�������
				aRGroup[cntSingle] = new ButtonGroup();
				aLabel[cntSingle] = new JLabel(cBuilder.toString());
				aLabel[cntSingle].setFont(new Font("����", Font.BOLD,20));
				aLabel[cntSingle].setBounds(x, y, 1000, 18);
				y += 40;
				contentPane.add(aLabel[cntSingle]);
				
				//ѡ��
				for(int k=1;k<=4;k++)
				{
					int cnt=cntSingle*4+k-1;
					aRadioButton[cnt] = new JRadioButton(words[i+k]);
					aRadioButton[cnt].setBounds(x, y, w, h);
					y += 40;
					contentPane.add(aRadioButton[cnt]);
					aRGroup[cntSingle].add(aRadioButton[cnt]);
				}
				cntSingle++;
			}
			
		}
	}
	public int multiQuestion(int x)
	{
		contentPane.add(utils.titleQues(words[x],y));	
		for(int i = x+1;;i++) 
		{
			if(utils.strStr(words[i], "�ж���")!=-1){return i;}
			if(Character.isDigit(words[i].charAt(0)))
			{
				StringBuilder cBuilder=new StringBuilder(words[i]); 
				utils.stemQues(words[i],cBuilder,cntMul+cntSingle,"Cho",RightAnswer,cntSingle,cntMul,0);
				
				bLabel[cntMul] =new JLabel(cBuilder.toString());
				bLabel[cntMul].setFont(new Font("����", Font.BOLD,20));
				y+= 40;
				bLabel[cntMul].setBounds(0,y,1000,18);
				contentPane.add(bLabel[cntMul]);
				//�������
				for(int k=i+1;;k++)
				{
					if(!Character.isAlphabetic(words[k].charAt(0)))
					{
						JLabel a=new JLabel(words[k]);
						a.setFont(new Font("����", Font.BOLD,20));
						y+=30;
						a.setBounds(x, y, 1000, 18);
						contentPane.add(a);
					}
					else {
						i = k;
						break;
					}
				}
				
				//ѡ��
				for(int k=i;k<=i+3;k++)
				{
					int cnt=cntMul*4+k-i;
					bcheckbox[cnt] = new JCheckBox(words[k]);
					y+=30;
					bcheckbox[cnt].setBounds(x, y, w, h);
					contentPane.add(bcheckbox[cnt]);
				}
				cntMul++;
			}
		}
	}
	public void judgeQuestion(int x)
	{
		y+=40;
		contentPane.add(utils.titleQues(words[x],y));	
		for(int i = x+1;!words[i].equals(words[i+1]);i++) 
		{
			/*���*/
			if(Character.isDigit(words[i].charAt(0)))
			{
				StringBuilder cBuilder=new StringBuilder(words[i]);
				utils.stemQues(words[i], cBuilder, cntPanduan+cntMul+cntSingle,"Jud",RightAnswer,cntSingle,cntMul,cntPanduan);
				//�������
				cRGroup[cntPanduan] = new ButtonGroup();
				cLabel[cntPanduan] = new JLabel(cBuilder.toString());
				cLabel[cntPanduan].setFont(new Font("����", Font.BOLD,20));
				y += 40;
				cLabel[cntPanduan].setBounds(0, y, 1000, 18);
				contentPane.add(cLabel[cntPanduan]);
				//ѡ��
				for(int k=1;k<=2;k++)
				{
					int cnt=cntPanduan*2+k-1;
					cRadioButton[cnt] = new JRadioButton(words[i+k]);
					y += 40;
					cRadioButton[cnt].setBounds(0, y, w, h);
					contentPane.add(cRadioButton[cnt]);
					cRGroup[cntPanduan].add(cRadioButton[cnt]);
				}
				cntPanduan++;
			}
		}
	}
	public void getScore()
	{
		
		// ���Ӱ�ť���
		contentPane.add(submit);
		contentPane.add(score);
		//�¼�����
		submit.setFont(new Font("����", Font.BOLD,20));
		submit.setBounds(250, 2800, 100, 50);
		submit.addMouseListener(new MouseAdapter() { 
			// ���������ťѡ����
			public void mouseClicked(MouseEvent e) {
				Choose();
				Count();
				score.setText("��ķ���Ϊ��" + tot);// ��ʾ���ķ���
				tot=0;
				score.setFont(new Font("����",Font.BOLD,35));// ��������
				score.setBounds(400,2800,350, 50);
			}
		});

	}
	public void Count()
	{
		for(int i=0;i<15;i++) {
			boolean flag = true;
			for(int z=0;z<4 && flag;z++) {
				if(RightAnswer[i][z] != SeclectAnswer[i][z]) flag = false;
			}
			if(flag)
			{
				System.out.println(i);
				tot += 1;
			}
		}
	}
	public void Choose()
	{
		int j = -1;
		for(int i=0;i<cntSingle*4;i++) {
			if(i % 4 == 0) ++j;
			if(SeclectAnswer[j][i%4] == 1) SeclectAnswer[j][i%4] = 0;
			if(aRadioButton[i].isSelected()) {
				SeclectAnswer[j][i%4] = 1;
			}
		}
		for(int i=0;i<cntMul*4;i++) {
			if(i % 4 == 0) ++j;
			if(SeclectAnswer[j][i%4] == 1) SeclectAnswer[j][i%4] = 0;
			if(bcheckbox[i].isSelected()) {
				SeclectAnswer[j][i%4] = 1;
			}
		}
		for(int i=0;i<cntPanduan*2;i++) {
			if(i % 2 == 0) ++j;
			if(SeclectAnswer[j][i%2] == 1) SeclectAnswer[j][i%2] = 0;
			if(cRadioButton[i].isSelected()) SeclectAnswer[j][i%2] = 1;
		}
	}
	public void init()
	{
		frame = new JFrame("�����Ծ�");
		contentPane = new JPanel();//���һ������
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//����ر�
		frame.setBounds(100, 100, 800, 900);//������λ�ô�С
		
		//��ӹ�����
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 782, 829);
		scrollPane.setViewportView(contentPane);
		contentPane.setPreferredSize(new Dimension(1000,3460));//ǿ���޸Ĵ�С
		contentPane.setLayout(null);
		frame.getContentPane().add(scrollPane);//������������
	}
	public void render()
	{
		//�Ծ�������
		JLabel title=new JLabel(words[0]);
		title.setFont(new Font("����", Font.BOLD, 25));
		title.setBounds(120, 0, 840, 30);
		contentPane.add(title);
				
		//��Ŀ
		posMul=singleQuestion(1);//��ѡ��
		posJud=multiQuestion(posMul);//��ѡ��
		judgeQuestion(posJud);//�ж���
		
		//�����ύ
		getScore();
		
	}
}