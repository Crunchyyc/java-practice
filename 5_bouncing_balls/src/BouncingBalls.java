import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BouncingBalls extends Application {
    private static final int BALL_RADIUS = 15;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int MAX_BALLS = 30;
    private List<Ball> balls = new ArrayList<>();
    private Random random = new Random();

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, WIDTH, HEIGHT);

        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (balls.size() < MAX_BALLS) {
                Ball ball = new Ball(event.getX(), event.getY(), BALL_RADIUS,
                        Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble()));
                balls.add(ball);
                pane.getChildren().add(ball);
            }
        });

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(20), e -> moveBalls()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

        primaryStage.setTitle("Bouncing Balls");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void moveBalls() {
        for (Ball ball : balls) {
            if (ball.getCenterX() < ball.getRadius() || ball.getCenterX() > WIDTH - ball.getRadius()) {
                ball.dx *= -1; // Change ball move direction
            }
            if (ball.getCenterY() < ball.getRadius() || ball.getCenterY() > HEIGHT - ball.getRadius()) {
                ball.dy *= -1; // Change ball move direction
            }

            // Update ball position
            ball.setCenterX(ball.dx + ball.getCenterX());
            ball.setCenterY(ball.dy + ball.getCenterY());
        }
    }

    class Ball extends Circle {
        private double speed = 2 + 8 * Math.random(); // Random speed between 2 and 5
        private double direction = 2 * Math.PI * Math.random(); // Random direction
        private double dx = speed * Math.cos(direction); // Speed in x direction
        private double dy = speed * Math.sin(direction); // Speed in y direction

        Ball(double x, double y, double radius, Color color) {
            super(x, y, radius);
            setFill(color); // Set ball color
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
