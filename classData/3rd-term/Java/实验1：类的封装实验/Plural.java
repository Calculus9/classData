package Hello;
public class Plural {

	double real,virtual;
	//构造函数
	Plural(double real,double virtual){
		this.real = real;
		this.virtual  =virtual;
	}
	//加
	public void add(Plural p){
		this.real += p.real;
		this.virtual += p.virtual;
	}
	//减
	public void minus(Plural p){
		this.real -= p.real;
		this.virtual -= p.virtual;
	}
	//乘
	public void mul(Plural p){
		double temp =this.real; 
		this.real = this.real*p.real - this.virtual*p.virtual;
		this.virtual = temp*p.virtual + this.virtual*p.real;
	}
	//求模
	public double mod(){
		return Math.sqrt((this.real*this.real+this.virtual*this.virtual));
	}
	
	public static void main(String[] args) {
		TestPlural1 test1 = new TestPlural1();
		test1.run();
		TestPlural2 test2 = new TestPlural2();
		test2.run();
	}
}
/*
 * 
 * 
 * 
 * package Hello;

public class Hello {
	public static void main(String[] args) {
		TestPlural1 test1 = new TestPlural1();
		test1.run();
		TestPlural2 test2 = new TestPlural2();
		test2.run();
		
	}
}

class Plural{
	double real,virtual;
	//构造函数
	Plural(double real,double virtual){
		this.real = real;
		this.virtual  =virtual;
	}
	//加
	public void add(Plural p){
		this.real += p.real;
		this.virtual += p.virtual;
	}
	//减
	public void minus(Plural p){
		this.real -= p.real;
		this.virtual -= p.virtual;
	}
	//乘
	public void mul(Plural p){
		double temp =this.real; 
		this.real = this.real*p.real - this.virtual*p.virtual;
		this.virtual = temp*p.virtual + this.virtual*p.real;
	}
	//求模
	public double mod(){
		return Math.sqrt((this.real*this.real+this.virtual*this.virtual));
	}
}

class TestPlural1{//测试1
	Plural plural1 = new Plural(-1,0);
	Plural plural2 = new Plural(0,-3);
	Plural plural3 = new Plural(2,-2);
	Plural plural4 = new Plural(0,1);
	public void run(){
		plural1.add(plural2);
		plural1.minus(plural3);
		plural1.mul(plural4);
		System.out.println("表达式1："+plural1.real+plural1.virtual+"i");
	}
}

class TestPlural2{//测试2
	Plural plural1 = new Plural(3,0);
	Plural plural2 = new Plural(-4,5.0);
	Plural plural3 = new Plural(-5.1,-7.2);
	Plural plural4 = new Plural(-1,-2.0);
	public void run(){
		plural1.add(plural2);
		plural1.mul(plural3);
		plural1.minus(plural4);
		System.out.println("表达式2："+plural1.mod());
	}
}

*/
