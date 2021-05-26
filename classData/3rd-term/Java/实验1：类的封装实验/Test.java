package Hello;

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