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
	public JLabel titleQues(String words,int y)//题目标题
	{
		JLabel title=new JLabel(words);
		title.setFont(new Font("宋体", Font.BOLD, 22));
		title.setBounds(0,y, 840, 20);
		return title;	
	}
	public void stemQues(String words,StringBuilder cBuilder,int cnt,String str,int[][] RightAnswer,int cntSingle,int cntMul,int cntPanduan) {//题干信息
		for(int k=0;k<words.length();k++)
		{
			int f = 0;
			if(words.charAt(k)=='('||words.charAt(k)=='（')
			{
				for(int l=k;;l++)//从左括号开始匹配答案
				{
					/*选择题---*/
					if(str=="Cho")
						if(Character.isAlphabetic(words.charAt(l)))//找到答案选项
						{
							RightAnswer[cntMul+cntSingle][words.charAt(l)-'A']=1;//标记答案
							f=1;
							cBuilder.setCharAt(l,' ');
						}
					/*---选择题*/
					/*判断题---*/
					if (str=="Jud") {
						if(words.charAt(l)=='正'||words.charAt(l)=='错')
						{
							if(words.charAt(l)=='正') RightAnswer[cntPanduan+cntMul+cntSingle][0]=1;//标记答案
							else RightAnswer[cntPanduan+cntMul+cntSingle][1]=1;
							f=1;
							cBuilder.setCharAt(l,' ');
							cBuilder.setCharAt(l+1,' ');
						}
					}
					/*---判断题*/
					if(words.charAt(l) == ')' || words.charAt(l) =='）') break; //匹配到')'  结束题干的处理
				}
			}
			if(f==1)break;
		}
	}
}
