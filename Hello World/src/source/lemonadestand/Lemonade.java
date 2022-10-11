package source.lemonadestand;

public class Lemonade {

	Water water;
	Sugar sugar = new Sugar(2, "cups");
	LemonJuice lemonJuice = new LemonJuice(5, "ounces");
	
	public Lemonade(Water water, Sugar sugar, LemonJuice lemonJuice) {
		super();
		this.water = water;
		this.sugar = sugar;
		this.lemonJuice = lemonJuice;
	}
	
//	public Lemonade(LemonJuice lemonJuice, Water water ) {
//		this.lemonJuice = lemonJuice;
//		this.water = water;
//		sugar = null; //or leave this out
//	}
	
}
