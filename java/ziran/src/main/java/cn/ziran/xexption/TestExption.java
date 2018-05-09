package cn.ziran.xexption;

public class TestExption {
	public static void main(String[] args) throws Exception {
		// 异常层层上抛
		x1();
		System.out.println("---- success ----");
	}

	public static void x1() throws Exception {
		System.out.println("----x1 begin----");

		int i = 1;
		if (i == 0)
			throw new Exception("throw x1 exception..");

		System.out.println("----x1 end----");
		x2();
	}

	public static void x2() throws Exception {
		System.out.println("----x2 begin----");

		int i = 1;
		if (i == 1)
			throw new Exception("throw x2 exception..");

		System.out.println("----x2 end----");
		x3();
	}

	public static void x3() throws Exception {
		System.out.println("----x3 begin----");
		int i = 0;
		if (i == 0)
			throw new Exception("throw x3 exception..");
		System.out.println("----x4 end----");

	}
}
