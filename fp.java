// fp.java

public class fp
{
	// Be sure to put your name on this next line...
	public String myName()
	{
		return "Neil Patrick Reyes";
	}

	public int add(int a, int b)
	{
		FPNumber fa = new FPNumber(a);
		FPNumber fb = new FPNumber(b);
		FPNumber result = new FPNumber(0);

		if(fb.isNaN() || fa.isNaN()){
			if(fb.isNaN()){
				result.setE(fb.e());
				result.setF(fb.f());
				result.setS(fb.s());
			}else{
				result.setE(fa.e());
				result.setF(fa.f());
				result.setS(fa.s());
			}
		}else if(fa.isZero() || fb.isZero()){	
			if(fa.isZero()){
				result.setE(fb.e());
				result.setF(fb.f());
				result.setS(fb.s());
			}else{
				result.setE(fa.e());
				result.setF(fa.f());
				result.setS(fa.s());
			}
		}else if(fb.isInfinity() && fa.isInfinity()){
			if(fb.s() == fa.s()){
				result.setE(fb.e());
				result.setF(fb.f());
				result.setS(fb.s());
			}else{
				result.setE(fa.e());
				result.setF(fa.f());
				result.setS(fa.s());
			}
		}else if(fb.isInfinity() || fa.isInfinity()){
			if(fb.isInfinity()){
				result.setE(fb.e());
				result.setF(fb.f());
				result.setS(fb.s());
			}else{
				result.setE(fa.e());
				result.setF(fa.f());
				result.setS(fa.s());
			}
		}else if(fa.e() > fb.e()){//a > b exponent
			

		}else if(fa.e() < fb.e()){ // b > a exponent

		}else{ // b = a exponent
			if(fa.f() > fb.f()){

			}else{

			}
		}
		// Put your code in here!

		System.out.println("Sign A is " + fa.s());
		System.out.println("Exponent A is " + fa.e());
		System.out.println("Mantissa A is " + fa.f());
		System.out.println("Sign B is " + fb.s());
		System.out.println("Exponent B is " + fb.e());
		System.out.println("Mantissa B is " + fb.f());

		return result.asInt();
	}

	public int mul(int a, int b)
	{
		FPNumber fa = new FPNumber(a);
		FPNumber fb = new FPNumber(b);
		FPNumber result = new FPNumber(0);

		// Put your code in here!

		return result.asInt();
	}

	// Here is some test code that one student had written...
	public static void main(String[] args)
	{
		int v24_25	= 0x41C20000; // 24.25
		int v_1875	= 0xBE400000; // -0.1875
		int v5		= 0xC0A00000; // -5.0

		fp m = new fp();

		System.out.println(Float.intBitsToFloat(m.add(v24_25, v_1875)) + " should be 24.0625");
		System.out.println(Float.intBitsToFloat(m.add(v24_25, v5)) + " should be 19.25");
		System.out.println(Float.intBitsToFloat(m.add(v_1875, v5)) + " should be -5.1875");

		System.out.println(Float.intBitsToFloat(m.mul(v24_25, v_1875)) + " should be -4.546875");
		System.out.println(Float.intBitsToFloat(m.mul(v24_25, v5)) + " should be -121.25");
		System.out.println(Float.intBitsToFloat(m.mul(v_1875, v5)) + " should be 0.9375");
	}
}
