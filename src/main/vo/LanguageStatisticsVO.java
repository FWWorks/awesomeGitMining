package main.vo;

import main.dao.entity.Statistics;

public class LanguageStatisticsVO {
	private int[] languageNum;
	private String[] languageType = Statistics.getInstance().getLanguage();

	public int[] getLanguageNum() {
		return languageNum;
	}

	public void setLanguageNum(int[] languageNum) {
		this.languageNum = languageNum;
	}

	public String[] getLanguageType() {
		return languageType;
	}

	public void setLanguageType(String[] languageType) {
		this.languageType = languageType;
	}

}
