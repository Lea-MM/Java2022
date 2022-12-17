package exercise1;

import java.sql.Date;
import java.time.LocalDate;

public class TableViewData {
	
	private int playerId;
	private String gameTitle;
	private LocalDate datePlayed;
	private int score;
	
	public TableViewData(int playerId, String gameTitle, Date date, int score) {
		super();
		this.playerId = playerId;
		this.gameTitle = gameTitle;
		this.datePlayed = date.toLocalDate();
		this.score = score;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public String getGameTitle() {
		return gameTitle;
	}
	public void setGameTitle(String gameTitle) {
		this.gameTitle = gameTitle;
	}
	public LocalDate getDatePlayed() {
		return datePlayed;
	}
	public void setDatePlayed(Date datePlayed) {
		this.datePlayed = datePlayed.toLocalDate();
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
}
