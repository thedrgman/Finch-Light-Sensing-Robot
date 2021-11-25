package Code;

import com.birdbraintechnologies.Finch;
import java.awt.Color;

/**
 * Created by: David Gameiro
 * Date: June 2020
 */

public class LightSensing1 {
    public static void main(final String[] args)
      {
      Finch myFinch = new Finch();
      
      int i = 1;
      // Helps to stop the loop at the right moment.
      
      while(i != 10){
      //This creates a loop until the i variable is equal to 10
        
        int leftLight = myFinch.getLeftLightSensor();
        int rightLight = myFinch.getRightLightSensor();
        double temp1 = myFinch.getTemperature();
        boolean rightSide = myFinch.isObstacleRightSide();
        boolean leftSide = myFinch.isObstacleLeftSide();
        //Gathering all of the necessary information from the sensors and new information every pass through of the loop
        
        System.out.println("RightLight = " + rightLight);
        System.out.println("Left Light = " + leftLight);
        //Display the light sensor readings for both sensors.
        
        if((rightLight - leftLight) > 60){
            myFinch.setLED(Color.yellow);
            myFinch.setWheelVelocities(100,50, 500);
            //When the right light sensor is greater than the left of more than 60 it will turn yellow and turn right.
        }
        else if((leftLight - rightLight) > 60){
            myFinch.setLED(Color.green);
            myFinch.setWheelVelocities(50,100, 500);
            //When the left light sensor is greater than the right of more than 60 it will turn green and turn left.
        }
        else if(((leftLight - rightLight) < 60) || ((rightLight - leftLight) < 60)){
            myFinch.setLED(Color.blue);
            myFinch.sleep(15000);
            //When the difference between the two sensors is less than 60 it will turn blue and sleep for 5 seconds.
            double temp2 = myFinch.getTemperature();
            //It will gather another temperature reading.
            System.out.println("Temp1 = " + temp1);
            System.out.println("Temp2 = " + temp2);
            //Display the temperature readings for both variables.
            if((temp1 - temp2) > 3){
                myFinch.setLED(255, 0, 255);
                myFinch.setWheelVelocities(100,100, 500);
                //Here it will compare the two readings and if the first one is greater than 7 than it will turn purple and go forward.
            }
        }
        
        //Obstacle avoidance.
        if(rightSide){
            myFinch.setLED(Color.red);
            myFinch.setWheelVelocities(-100,-100, 1000);
            myFinch.setWheelVelocities(10, 100, 500);
            //If the right sensor is triggered than it will turn red, backup, then turn left.
        }
        else if(leftSide){
            myFinch.setLED(255, 100, 255);
            myFinch.setWheelVelocities(-100,-100, 500);
            myFinch.setWheelVelocities(100, 10, 500);
            //If the left sensor is triggered than it will turn pink, backup, then turn right.
        }
        
        if(leftLight < 25){
            if(rightLight < 25){
                myFinch.setLED(255, 255, 255);
                myFinch.sleep(5000);
                //If light readings is less than 25 for both sensors than it will turn white and sleep for 5 seconds.
                if(leftLight < 25){
                    if (rightLight < 25) {
                        i = 10;
                        //After 5 seconds and the light situation has not improved so then it will change the i variable to 10 to end the loop.
                    }
                }
            }
        }
        
      }
      
      
      myFinch.quit();
      System.exit(0);
      }
}
