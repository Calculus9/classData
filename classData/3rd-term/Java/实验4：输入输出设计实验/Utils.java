package test;

import java.awt.Font;

import javax.swing.JLabel;

public class Utils {
	public int strStr(String haystack, String needle) {
		if(needle.equals("")||haystack.equals(needle)){
            return 0;
        }
        int index=-1;
        if(haystack.contains(needle)){
            String[] str=haystack.split(needle);
            if(str.length>=1){
                index=str[0].length();
            }else {
                index=0;
            }
        }else{
                index=-1;
            }
        return index;
    }
	public JLabel titleQues(String words,int y)//��Ŀ����
	{
		JLabel title=new JLabel(words);
		title.setFont(new Font("����", Font.BOLD, 22));
		title.setBounds(0,y, 840, 20);
		return title;	
	}
	public void stemQues(String words,StringBuilder cBuilder,int cnt,String str,int[][] RightAnswer,int cntSingle,int cntMul,int cntPanduan) {//�����Ϣ
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
}
