package BFS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GraphFX extends Application {

	private HBox hbox = null; // the hbox of the scene
	private int width = 15; // a default values of the mineGrid
	private int heigth = 15; // *
	private Graph g;// = new Graph(heigth, width, 0, 0, 8, 8); // *
	private MyController controller = null;
	public static Button[][] b;
	private GridPane grid = new GridPane();
	private Stage stage;
	private boolean firstClick = false;
	private int buttonSize = 40;
	private boolean bfsIsRunning = false;
	private boolean endBFS = true;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		try {
			FXMLLoader loader = new FXMLLoader(); // loading the scene and controller
			loader.setLocation(getClass().getResource("graphFX.fxml"));
			this.hbox = loader.load();
			controller = loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene s = new Scene(hbox); // creating the scene
		primaryStage.setTitle("BFS Generator");
		primaryStage.setScene(s);
		primaryStage.show();
		stage = primaryStage; // saving the stage for size editing
		setGrid(grid); // creating a grid for default play
		stage.setWidth(heigth * buttonSize + 190);
		stage.setHeight(Math.max(width * buttonSize + 70, 181));
		controller.stack.getChildren().add(grid);
		s.addEventFilter(MouseEvent.DRAG_DETECTED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				s.startFullDrag();
			}
		});
		controller.reset.setOnAction(new EventHandler<ActionEvent>() { // when reset is clicked, create an eventhandler

			@Override
			public void handle(ActionEvent event) {
				endBFS = true;
				try {
					heigth = !controller.heigthT.getText().equals("") ? Integer.parseInt(controller.widthT.getText())
							: 0;
					width = !controller.heigthT.getText().equals("") ? Integer.parseInt(controller.heigthT.getText())
							: 0;
					buttonSize = !controller.sizeTf.getText().equals("") ? Integer.parseInt(controller.sizeTf.getText())
							: 0;
					buttonSize *= 10;
				} catch (Exception e) { // catching exception for invalid input
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Invalid Input");
					alert.setHeaderText(null);
					alert.setContentText("Input that was typed is invalid, please try again");
					alert.show();
					return;
				}
				if ((heigth < 2 || width < 2 || buttonSize < 10 || buttonSize > 50)) { // handling invalid
					// input
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Invalid Input");
					alert.setHeaderText(null);
					alert.setContentText("Input that was typed is invalid, please try again");
					alert.show();
					return;
				}
				controller.stack.getChildren().remove(grid); // if input is valid, remove grid and create a new one
				grid = new GridPane();
				grid.setPadding(new Insets(buttonSize, buttonSize, buttonSize, buttonSize));
				setGrid(grid);
				controller.stack.getChildren().add(grid);
				stage.setWidth(heigth * buttonSize + 190);
				stage.setHeight(Math.max(width * buttonSize + 70, 181));
			}
		});

		controller.creation_button.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (controller.random_button.isSelected()) {
					controller.random_button.setSelected(false);
				}
				if (!controller.creation_button.isSelected()) {
					controller.creation_button.setSelected(true);
				}
			}
		});

		controller.random_button.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (controller.creation_button.isSelected()) {
					controller.creation_button.setSelected(false);
				}
				if (!controller.random_button.isSelected()) {
					controller.random_button.setSelected(true);
				}
			}
		});
	}

	private void setGrid(GridPane g2) { // setting the new grid here
		g2.setPadding(new Insets(buttonSize, buttonSize, buttonSize, buttonSize));
		g2.setAlignment(Pos.CENTER);
		b = new Button[heigth][width];
		// randMines(); // adding random mines to the grid
		for (int i = 0; i < heigth; i++) { // creating buttons in the grid
			for (int j = 0; j < width; j++) {
				b[i][j] = new Button();
				b[i][j].setPrefSize(buttonSize, buttonSize);
				b[i][j].setMinSize(buttonSize, buttonSize);
				b[i][j].setMaxSize(buttonSize, buttonSize);
				b[i][j].setFont(new Font("Ariel", 15));
				g2.add(b[i][j], i, j);
				b[i][j].setStyle("-fx-background-radius: 0;");
				b[i][j].setOnMouseClicked(new ClickedButton(i, j)); // creating eventhandlers for each button
				b[i][j].setOnMouseDragEntered(new ClickeAndHovereddButton(i, j));
			}
		}
		g2.setMinSize((double) (buttonSize * (heigth) + 20), (double) (buttonSize * (width) + 20));
		g2.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
		g = new Graph(heigth, width);
	}

	public class ClickeAndHovereddButton implements EventHandler<MouseEvent> {

		private int x;
		private int y;

		public ClickeAndHovereddButton(int x, int y) { // constractor with the coordinates of the button
			this.x = x;
			this.y = y;
		}

		@Override
		public void handle(MouseEvent event) {
			if (!endBFS)
				return;
			if (controller.creation_button.isSelected()) {
				if (event.getButton() == MouseButton.SECONDARY) {
					if (g.getGraph().get(x).get(y).getPathFlag() == 0) {
						g.removeNode(x, y);
						g.getGraph().get(x).get(y).setPathFlag(1);
						g.getGraph().get(x).get(y).setValue("O");
						String bstyle = String.format("-fx-text-fill: #%s;-fx-fill: #%s;-fx-background-color: #%s;",
								"FFFFFF", "FFFFFF", "000000");
						b[x][y].setStyle(bstyle);
						event.consume();
					}
				}
			}
		}

	}

	public class ClickedButton implements EventHandler<MouseEvent> { // when a button is clicked:

		private int x;
		private int y;

		public ClickedButton(int x, int y) { // constractor with the coordinates of the button
			this.x = x;
			this.y = y;
		}

		@Override
		public void handle(MouseEvent event) {
			if (!endBFS)
				return;
			if (event.getButton() == MouseButton.PRIMARY && !bfsIsRunning) {
				if (firstClick) {
					firstClick = false;
					Graph.setEndNode(g.getGraph().get(x).get(y));
					String bstyle = String.format("-fx-text-fill: #%s;-fx-fill: #%s;-fx-background-color: #%s;",
							"FF0000", "FF0000", "FF0000");
					b[x][y].setStyle(bstyle);
					if (controller.random_button.isSelected()) {
						g.ranDeadNodes();
						g.ranDeadNodes();
					}
					BFS_Alg.BFS(g);
					BFS();
				} else {
					firstClick = true;
					Graph.setStartNode(g.getGraph().get(x).get(y));
					String bstyle = String.format("-fx-text-fill: #%s;-fx-fill: #%s;-fx-background-color: #%s;",
							"FF0000", "FF0000", "FF0000");
					b[x][y].setStyle(bstyle);
				}
			}
		}

	}

	public static String convertToHexa(String hexString) {

		hexString = hexString.toUpperCase();
		switch (hexString.length()) {

		case 0:
			return "345200";
		case 1:
			return "34520" + hexString;
		case 2:
			return "3452" + hexString;
		case 3:
			return "345" + hexString;
		case 4:
			return "34" + hexString;
		case 5:
			return "3" + hexString;
		case 6:
			return hexString;
		default:
			return "FFFFFF";

		}
	}

	Queue<Node> queue;
	Integer counter;
	Timer timer;

	public void BFS() {
		endBFS = false;
		if (Graph.getEndNode() == Graph.getStartNode()) {
			g.outputBFS();
			endBFS = true;
			return;
		}
		bfsIsRunning = true;
		timer = new Timer(true);
		for (ArrayList<Node> nodeList : g.getGraph()) {
			for (Node node : nodeList) {
				if (node.getValue().equals("O")) {
					String bstyle = String.format("-fx-text-fill: #%s;-fx-fill: #%s;-fx-background-color: #%s;",
							"FFFFFF", "FFFFFF", "000000");
					b[node.getX()][node.getY()].setStyle(bstyle);
				}
			}
		}
		Node s = Graph.getStartNode();
		queue = new LinkedList<Node>();
		queue.add(s);
		s.setVisited(true);
		ArrayList<Node> round = new ArrayList<Node>();
		counter = 1;
		s.setValue(counter.toString());
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				if (!queue.isEmpty()) {
					while (!queue.isEmpty())
						round.add((Node) queue.remove());
					for (Node node : round) {
						for (Node node2 : g.returnNeig(node)) {
							if (!node2.isVisited()) {
								queue.add(node2);
								node2.setValue(counter.toString());
								node2.setVisited(true);
								node2.setPredececor(node);
								if (node2.getType() == NodeType.END) {
									g.outputBFS();
									timer.cancel();
									bfsIsRunning = false;
									endBFS = true;
								}
							}

						}
						int i, j;
						i = node.getX();
						j = node.getY();
						if (g.getGraph().get(i).get(j).getValue().equals("X")) {
							String bstyle = "-fx-text-fill: #FF0000;-fx-fill: #FFFF00;-fx-background-color: #FF0000;";
							b[i][j].setStyle(bstyle);
						} else if (g.getGraph().get(i).get(j).getType() != NodeType.START
								&& g.getGraph().get(i).get(j).getType() != NodeType.END) {
							String bstyle = String.format("-fx-text-fill: #%s;-fx-fill: #%s;-fx-background-color: #%s;",
									"3452EB", "3452EB", "3452EB");
							b[i][j].setStyle(bstyle);

						}
					}
					counter++;
				} else {
					timer.cancel();
					bfsIsRunning = false;
					endBFS = true;
				}
			}
		};
		timer.scheduleAtFixedRate(timerTask, 0, buttonSize * 10);
	}
}
