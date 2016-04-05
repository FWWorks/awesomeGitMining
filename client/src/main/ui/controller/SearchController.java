package main.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.business.impl.repository.RepositoryServiceImpl;
import main.business.service.RepositoryService;
import main.ui.MainUI;
import main.vo.RepositoryVO;

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
	private int pageNums;
	private int projectPage;

	public static SearchController getInstance() {
		return instance;
	}

	public SearchController() {
		repositoryService = RepositoryServiceImpl.getInstance();
	}

	private void initProject(List<RepositoryVO> list) {
		box = new VBox();
		for (int i = 0; i < 10; i++) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainUI.class.getResource("config/Ui_SingleReposView.fxml"));
			try {
				AnchorPane single = (AnchorPane) loader.load();
				SingleRepositoryController controller = loader.getController();
				if (i < list.size()) {
					RepositoryVO vo = list.get(i);
					controller.setVO(vo);
					box.getChildren().add(single);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		projectPane.setContent(box);
		box = null;
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
	}

	public void setSearchID(String id) {
		this.id = id;
		repositoryVO = repositoryService.searchRepository(id, 0);
		pageNums = repositoryService.getSearchPageNums(id);
		if (pageNums < 1) {
			pageNums = 1;
		}
		page.setText("1 / " + pageNums);
		initProject(repositoryVO);
	}
}