package com.com.oracle.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NullTest {

	public static void main(String args[]) {
		A a = new A();
		B b = new B();
		C c = new C();
		D d = new D();
		List<D> ds = new ArrayList<D>();
		ds.add(d);
		c.setD(ds);
		List<C> cs = new ArrayList<C>();
		cs.add(c);
		b.setC(cs);
		a.setB(b);
//		System.out.println(a.getB().getC().getD().getName());
		if(Optional.of(a).map(A::getB).map(B::getC)
				.filter(x -> ! x.isEmpty()).map(x -> x.get(0)).map(C::getD)
				.filter(x -> ! x.isEmpty()).map(x -> x.get(0)).isPresent()) {
			System.out.println(a.getB().getC().get(0).getD().get(0).getName());
		} else {
			System.out.println("Value not present");
		}
		if(a != null && 
			a.getB() != null && 
			a.getB().getC() != null && 
			a.getB().getC().get(0) != null &&
			a.getB().getC().get(0).getD() != null && 
			a.getB().getC().get(0).getD().get(0) != null && 
			a.getB().getC().get(0).getD().get(0).getName() != null) {
			System.out.println(a.getB().getC().get(0).getD().get(0).getName());
		} else {
			System.out.println("Value not present");
		}
	}

}

class A {
	private B b;

	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}
}

class B {
	private List<C> c;

	public List<C> getC() {
		return c;
	}

	public void setC(List<C> c) {
		this.c = c;
	}
}

class C {
	private List<D> d;

	public List<D> getD() {
		return d;
	}

	public void setD(List<D> d) {
		this.d = d;
	}
}

class D {
	private String name = "D";

	public String getName() {
		return name;
	}

}
