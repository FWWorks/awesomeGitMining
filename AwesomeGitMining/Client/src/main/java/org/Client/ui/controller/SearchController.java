package org.Client.ui.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.Client.business.impl.repository.RepositoryServiceImpl;
import org.Client.business.service.RepositoryService;
import org.Client.ui.MainUI;
import org.Client.ui.utility.BackHandler;
import org.Client.ui.utility.BackObject;
import org.Client.ui.utility.BackType;
import org.Client.ui.utility.SkinConfig;
import org.Common.vo.RepositoryVO;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class SearchController implements Initializable {

	private static SearchController instance;
	private RepositoryService repositoryService;
	private List<RepositoryVO> repositoryVO;
	private String id;
	private VBox box = new VBox();
	@FXML
	private ScrollPane projectPane;
	@FXML
	private Label page;
	@FXML
	private AnchorPane pane;
	private int pageNums;
	private int projectPage;
	private final String configPath = "file:src/main/java/org/Client/ui/config/";
	
	public static SearchController getInstance() {
		return instance;
	}

	public SearchController() {
		repositoryService = RepositoryServiceImpl.getInstance();
	}

	public void initProject(List<RepositoryVO> list) {
		box = new VBox();
		for (int i = 0; i < 10; i++) {
			FXMLLoader loader = new FXMLLoader();
			try {
				loader.setLocation(new URL(configPath + (SkinConfig.getInstance().getFxmlResoursePath("singleReposView"))));
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
			try {
				AnchorPane single = (AnchorPane) loader.load();
				SingleRepositoryController controller = loader.getController();
				if (i < list.size()) {
					RepositoryVO vo = list.get(i);
					controller.setVO(vo);
					BackHandler.getInstance().setRepoBack(new BackObject(BackType.SEARCH_REPO, id, projectPage));
					box.getChildren().add(single);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		projectPane.setContent(box);
		box = null;
	}

	public void setPage(int num) {
		projectPage = num;
		page.setText(projectPage + 1 + " / " + pageNums);
	}

	@FXML
	public void handleProjectPre() {
		projectPage--;
		if (projectPage >= 0) {
			repositoryVO = repositoryService.searchRepository(id, projectPage);
			initProject(repositoryVO);
		} else {
			projectPage++;
		}
		page.setText(projectPage + 1 + " / " + pageNums);
	}

	@FXML
	public void handleProjectNext() {
		projectPage++;
		repositoryVO = repositoryService.searchRepository(id, projectPage);
		if (repositoryVO.size() > 0) {
			initProject(repositoryVO);
		} else {
			projectPage--;
		}
		page.setText(projectPage + 1 + " / " + pageNums);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		instance = this;
		projectPane.setHbarPolicy(ScrollBarPolicy.NEVER);
	}

	public void setSearchID(String id) {
		this.id = id;
		repositoryVO = repositoryService.searchRepository(id, 0);
		pageNums = repositoryService.getSearchPageNums(id);
		if (repositoryVO.size() == 0) {
			Label infoLabel = new Label();
			infoLabel.setLayoutX(200);
			infoLabel.setLayoutY(200);
			infoLabel.setGraphic(new ImageView(new Image(MainUI.class.getResourceAsStream("style/404.png"))));
			pane.getChildren().add(infoLabel);
		} else {
			if (pageNums < 1) {
				pageNums = 1;
			}
			page.setText("1 / " + pageNums);
			initProject(repositoryVO);
		}
	}

	public void changeStyle() {
		initProject(repositoryVO);
	}
}
