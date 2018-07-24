package test;

public class Goblin {
	
	private final String name;  // required
	private final String color; // required
	private final int size;		// not required
	private final byte iq; 		// not required
	
	private Goblin(GoblinBuilder builder) {
		name=builder.name;
		color=builder.color;
		size=builder.size;
		iq=builder.iq;
	}
	
	public static class GoblinBuilder{
		private final String name;
		private final String color;
		private int size;		
		private byte iq; 		
		
		public GoblinBuilder(String name, String color) {
			this.name=name;
			this.color=color;
		}
		
		public GoblinBuilder withSize(int size){
			this.size=size;
			return this;
		}
		
		public GoblinBuilder withIq(byte iq){
			this.iq=iq;
			return this;
		}
		
		public Goblin build(){
			return new Goblin(this);
		}
	}

	public String getName() { return name; }

	public String getColor() { return color;}

	public int getSize() { return size;}

	public byte getIq() { return iq;}


}
