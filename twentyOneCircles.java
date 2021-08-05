import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javax.swing.*;
import java.util.Random;

public class twentyCircles extends Application {
    Circle[] circles = new Circle[20]; //the circles used to store instance of circles
    
    public static void main(String[] args) {launch(args);}

    public void start(Stage primaryStage) {

        Random random = new Random();
        //setting the size of the frame
        int frame_wid = 600;
        int frame_hgt = 600;
        
        for (int i = 0; i < circles.length; i++) {
            //generating a value between 10 and 50 for radius
            int radius = random.nextInt(41) + 10;
            //generating a pair of x,y within the frame
            int x_pt = random.nextInt(frame_wid - radius*2) + radius;
            int y_pt = random.nextInt(frame_hgt - radius*2) + radius;

            //creating Circle object
            Circle circle = new Circle();
            circle.setCenterX(x_pt);
            circle.setCenterY(y_pt);
            circle.setRadius(radius);
            circles[i] = circle;
        }
        ifOverlap();

        Pane root = new Pane();
        root.getChildren().addAll(circles);
        Scene scene = new Scene(root, frame_wid, frame_hgt);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Circle Overlap");
        primaryStage.show();
    }
    public void ifOverlap(){
        //looping through the previous circles to see if they are overlapping
        for (int i = 0; i < circles.length; i++) {

            double x1 = circles[i].getCenterX(); //get the location and radius for pt1
            double y1 = circles[i].getCenterY();
            double radius_1=circles[i].getRadius();
            boolean isOverlap = false;//used to exam whether overlapped

            for(int j =0; j<circles.length;j++) {

                if (i != j) {
                    double x2 = circles[j].getCenterX();//get the location and radius for pt2
                    double y2 = circles[j].getCenterY();
                    double radius_2 = circles[j].getRadius();

                    double dx = x2 - x1;          //Calculate distance between two circles
                    double dy = y2 - y1;
                    double distance = Math.sqrt((dx * dx) + (dy * dy));
                    //checking if distance<radius1+radius2
                    if (distance <= (radius_1 + radius_2)) {
                        //initiate transclucent blue color
                        Paint c = new Color(0, 0, 1.0, 0.3);
                        //changing the color of other circle
                        isOverlap=true;

                        circles[i].setFill(c);
                        circles[j].setFill(c);
                    }

                }
            }
            if(!isOverlap) {
                circles[i].setFill(Color.BLACK);
            }
        }
    }

}