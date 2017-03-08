
package strategydesignpattern;

public class DuckSimmulator {
    public static void main(String[] args) {

            //create duck
            MallardDuck	mallard = new MallardDuck();
            //call perform quack method
            mallard.performQuack();
            // display details about duck
            mallard.display();
            
            RubberDuck	rubberDuckie = new RubberDuck();
            rubberDuckie.performQuack();
            rubberDuckie.display();
            
            DecoyDuck	decoy = new DecoyDuck();
            decoy.performQuack();
            decoy.display();
            
            ModelDuck	model = new ModelDuck();
            model.performFly();	
            model.display();
            
            //create new behaviour
            FlyBehavior flyBehavior = new FlyNoWay();
            //assign this behaviour to model duck
            model.setFlyBehavior(flyBehavior);
            //performfly method on model duck
            model.performFly();
    }
    
}
