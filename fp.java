// fp.java

public class fp
{
	// Be sure to put your name on this next line...
	public String myName()
	{
		return "Neil Patrick Reyes";
	}

	public void normalize(FPNumber result){
		do{
			long newFrac;
			newFrac = result.f() >> 1;
			result.setF(newFrac);
			int newExp;
			newExp = result.e();
			newExp++;
			result.setE(newExp);
		}while(result.f() >= 67108864);
	}

	public void shrinkF(FPNumber result){

		//max 27 bit value is 134217727
		if(result.f() > 134217727){
			do{

				result.setF((result.f()>>1));

			}while(result.f() > 134217727);
		}
	}

	public int add(int a, int b)
	{
		int diff;

		FPNumber fa = new FPNumber(a);
		FPNumber fb = new FPNumber(b);
		FPNumber result = new FPNumber(0);

		if(fb.isNaN() || fa.isNaN()){
			if(fb.isNaN()){
				result.setE(fb.e());
				result.setF(fb.f());
				result.setS(fb.s());
				return result.asInt();
			}else{
				result.setE(fa.e());
				result.setF(fa.f());
				result.setS(fa.s());
				return result.asInt();
			}
		}else if(fa.isZero() || fb.isZero()){	
			if(fa.isZero()){
				result.setE(fb.e());
				result.setF(fb.f());
				result.setS(fb.s());
				return result.asInt();
			}else{
				result.setE(fa.e());
				result.setF(fa.f());
				result.setS(fa.s());
				return result.asInt();
			}
		}else if(fb.isInfinity() && fa.isInfinity()){
			if(fb.s() == fa.s()){
				result.setE(fb.e());
				result.setF(fb.f());
				result.setS(fb.s());
				return result.asInt();
			}else{
				result.setE(fa.e());
				result.setF(fa.f());
				result.setS(fa.s());
				return result.asInt();
			}
		}else if(fb.isInfinity() || fa.isInfinity()){
			if(fb.isInfinity()){
				result.setE(fb.e());
				result.setF(fb.f());
				result.setS(fb.s());
				return result.asInt();
			}else{
				result.setE(fa.e());
				result.setF(fa.f());
				result.setS(fa.s());
				return result.asInt();
			}
		}else if(fa.e() > fb.e()){//a > b exponent
			diff = fa.e() - fb.e();
			fb.setF((fb.f()>>diff));
			
			if(fa.s() == -1 && fb.s()== -1){
				result.setS(-1);
				result.setE(fa.e());
				result.setF((fa.f()+fb.f()));

			}else if(fa.s() == -1 || fb.s()== -1){

				result.setS(fa.s());
				result.setE(fa.e());
				result.setF((fa.f()-fb.f()));


			}else{
				result.setS(1);
				result.setE(fa.e());
				result.setF((fa.f()+fb.f()));
			}

		}else if(fa.e() < fb.e()){ // b > a exponent
			diff = fb.e() - fa.e();
			fa.setF((fa.f()>>diff));

			if(fa.s() == -1 && fb.s()== -1){

				result.setS(-1);
				result.setE(fb.e());
				result.setF((fa.f()+fb.f()));

			}else if(fa.s() == -1 || fb.s() == -1){
				
				result.setS(fb.s());
				result.setE(fb.e());
				result.setF((fb.f()-fa.f()));

			}else{
				result.setS(1);
				result.setE(fb.e());
				result.setF((fa.f()+fb.f()));
			}
		}else{ // b = a exponent
			
			if(fa.f() > fb.f()){
				if(fa.s() == -1 && fb.s()== -1){

					result.setS(-1);
					result.setE(fa.e());
					result.setF((fa.f()+fb.f()));
	
				}else if(fa.s() == -1 || fb.s()== -1){
	
					result.setS(fa.s());
					result.setE(fa.e());
					result.setF((fa.f()-fb.f()));
	
	
				}else{

					result.setS(1);
					result.setE(fa.e());
					result.setF((fa.f()+fb.f()));
				}
			}else{
				if(fa.s() == -1 && fb.s()== -1){

					result.setS(-1);
					result.setE(fb.e());
					result.setF((fa.f()+fb.f()));
	
				}else if(fa.s() == -1 || fb.s() == -1){
					
					result.setS(fb.s());
					result.setE(fb.e());
					result.setF((fb.f()-fa.f()));
	
				}else{
					result.setS(1);
					result.setE(fb.e());
				}
			}
		}

		if(result.f() >= 67108864){
			normalize(result);
		}

		// Put your code in here!

		//  System.out.println("Sign A is " + fa.s());
		//  System.out.println("Exponent A is " + fa.e());
		//  System.out.println("Mantissa A is " + fa.f());
		//  System.out.println("Sign B is " + fb.s());
		//  System.out.println("Exponent B is " + fb.e());
		//  System.out.println("Mantissa B is " + fb.f());
		//  System.out.println("Sign total is " + result.s());
		//  System.out.println("Exponent total is " + result.e());
		//  System.out.println("Mantissa total is " + result.f());

		return result.asInt();
	}

	public int mul(int a, int b)
	{
		FPNumber fa = new FPNumber(a);
		FPNumber fb = new FPNumber(b);
		FPNumber result = new FPNumber(0);

		System.out.println("Exponent A is " + fa.e());
		System.out.println("Exponent B is " + fb.e());

		System.out.println("Mantissa A is " + fa.f());
		System.out.println("Mantissa B is " + fb.f());

		long rFrac;

		// Put your code in here!
		if(fa.s()==fb.s()){ 	//XOR for sign
			result.setS(1);
		}else{
			result.setS(-1);
		}

		int exp = fa.e() + fb.e() - 127;
		result.setE(exp);
		System.out.println("Exponent total is " + result.e());

		if(fa.isNaN() || fb.isNaN()){// result is NaN

			result.setS(1);
			result.setE(225);
			result.setF(1);
			return result.asInt();

		}else if((fa.isInfinity() && fb.isZero()) || (fa.isZero() && fb.isInfinity())){//result is NaN

			result.setS(1);
			result.setE(225);
			result.setF(1);
			return result.asInt();

		}else if(fa.isZero() || fb.isZero()){//result is zero

			result.setE(0);
			result.setF(0);
			return result.asInt();

		}else if(fa.isInfinity() || fb.isInfinity()){//result is infinity

			result.setE(225);
			result.setF(0);
			return result.asInt();

		}else if(result.e() < 0){ //underflow => result is zero

			result.setE(0);
			result.setF(0);
			return result.asInt();

		}else if(result.e() > 254){ //overflow => result is infinity

			result.setE(255);
			result.setF(0);
			return result.asInt();

		}else{

			rFrac = fa.f() * fb.f();
			result.setF(rFrac);

			System.out.println("Mantissa mult is " + result.f());
			
		}
		
		shrinkF(result);
		System.out.println("Mantissa shrink is " + result.f());
		result.setF((result.f()>>1));
		System.out.println("Mantissa normalization is " + result.f());
		exp = result.e() + 1;
		result.setE(exp);
		System.out.println("Exponent normalization is " + result.e());


		//check for under/over flow again
		if(result.e() < 0){ //underflow => result is zero

			result.setE(0);
			result.setF(0);
			return result.asInt();

		}else if(result.e() > 254){ //overflow => result is infinity

			result.setE(255);
			result.setF(0);
			return result.asInt();

		}

		return result.asInt();
	}

	// Here is some test code that one student had written...
	public static void main(String[] args)
	{
		int v24_25	= 0x41C20000; // 24.25
		int v_1875	= 0xBE400000; // -0.1875
		int v5		= 0xC0A00000; // -5.0
		int v9_0625 = 0x3F680000; //0.90625 (0x3F680000)
		int v12_312 = 0xC1450000; //-12.3125 (0xC1450000) 
		int v25_25 	= 0x41CA0000; //  25.25 0x41ca0000

		fp m = new fp();

		System.out.println(Float.intBitsToFloat(m.add(v24_25, v_1875)) + " should be 24.0625");
		System.out.println(Float.intBitsToFloat(m.add(v24_25, v5)) + " should be 19.25");
		System.out.println(Float.intBitsToFloat(m.add(v_1875, v5)) + " should be -5.1875");
		System.out.println(Float.intBitsToFloat(m.add(v9_0625, v12_312)) + " should be -11.40625");
		System.out.println(Float.intBitsToFloat(m.add(v25_25, v24_25)) + " should be 49.50");

		System.out.println(Float.intBitsToFloat(m.mul(v24_25, v_1875)) + " should be -4.546875");
		System.out.println(Float.intBitsToFloat(m.mul(v24_25, v5)) + " should be -121.25");
		System.out.println(Float.intBitsToFloat(m.mul(v_1875, v5)) + " should be 0.9375");
	}
}
