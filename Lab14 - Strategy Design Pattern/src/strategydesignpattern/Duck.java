package strategydesignpattern;
public abstract class Duck {
        //Create an instance variable quackBehaviour in the Duck class. 
	FlyBehavior flyBehavior;
        QuackBehavior quackBehavior;
	
	public Duck() {
	}
        //change behaviour to duck
	public void setFlyBehavior(FlyBehavior fb) {
            flyBehavior = fb;
	}
        //function which is overwritten in particular ducks
	abstract void display();
        
	public void performFly() {
            flyBehavior.fly();
	}

	public void swim() {
		System.out.println("All ducks float, even decoys!");
	}
        //change quack behaviour
        public void setQuackBeviour(QuackBehavior qb) {
            System.out.println("setQuackBehaviour to " + qb );
        }
        public void performQuack(){
            quackBehavior.quack();
        }

}
