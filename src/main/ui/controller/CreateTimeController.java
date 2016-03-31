package main.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import main.business.impl.repository.RepositoryServiceImpl;
import main.business.service.RepositoryService;
import main.vo.CreatedTimeStatisticsVO;

public class CreateTimeController implements Initializable {
	@FXML
	private LineChart<String, Integer> lineChart;
	@FXML
	private RepositoryService service;
	@FXML
	private NumberAxis yAxis;
	@FXML
	private AnchorPane pane;
	private XYChart.Series<String, Integer> series;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		series = new XYChart.Series<>();
		series.setName("time");
		service = RepositoryServiceImpl.getInstance();
		CreatedTimeStatisticsVO vo = service.getCreatedTimeStatistics();
		int[] nums = vo.getNum();
		String[] year = vo.getTimes();
		for (int i = 0; i < nums.length; i++) {
			series.getData().add(new XYChart.Data<String, Integer>(year[i], 0));
		}
		Timeline tl = new Timeline();
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				int i = 0;
				for (Data<String, Integer> data : series.getData()) {
					data.setYValue(nums[i]);
					i++;
				}
			}
		}));
		yAxis.setAnimated(false);
		tl.play();
		lineChart.getData().add(series);
		setupHover();
	}

	private void setupHover() {
		Label label = new Label();
		label.setTextFill(Color.DARKORANGE);
		label.setStyle("-fx-font: 24 arial;");
		
	    for (final XYChart.Data<String, Integer> dt : series.getData()) {
	        final Node n = dt.getNode();
	        n.setEffect(null);
	        n.setOnMouseEntered(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent e) {
	            	label.setLayoutX(n.getLayoutX() + 66);
	            	label.setTranslateY(n.getLayoutY());
	            	label.setText(String.valueOf(dt.getYValue()));
	            }
	        });
	    }
	    pane.getChildren().add(label);
	}
}
