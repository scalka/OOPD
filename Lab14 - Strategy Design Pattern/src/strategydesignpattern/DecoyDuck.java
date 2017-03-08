package strategydesignpattern;

public class DecoyDuck extends Duck {
	public DecoyDuck() {
                // duck which implements fly and quack behavior
		flyBehavior = new FlyNoWay();
                quackBehavior = new Squeak();
	}
	public void display() {
		System.out.println("I'm a duck Decoy");
	}
}
