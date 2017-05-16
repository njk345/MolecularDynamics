package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.animation.AnimationTimer;

import java.awt.*;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        double sw = screen.getWidth();
        double sh = screen.getHeight();
        Group root = new Group();
        primaryStage.setTitle("MD Simulator");
        Scene scene = new Scene(root, sw, sh);
        primaryStage.setScene(scene);
        primaryStage.show();

        StackPane stack = new StackPane(new Atom(1, 100, 100), new Text("H"));
        root.getChildren().add(stack);


        AnimationTimer loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                stack.setTranslateX(50);
                stack.setTranslateY(50);

            }
        };
        loop.start();
    }
    public static void main(String[] args) {
        launch(args);
    }
}