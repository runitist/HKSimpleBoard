package com.hk.itedu.VO;

public class S_Board_VO {

	private int Board_No;
	private String Board_Title;
	private String Board_Content;
	private String Regdate;
	private int Cnt;

	public int getBoard_No() {
		return Board_No;
	}

	public void setBoard_No(int board_No) {
		Board_No = board_No;
	}

	public String getBoard_Title() {
		return Board_Title;
	}

	public void setBoard_Title(String board_Title) {
		Board_Title = board_Title;
	}

	public String getBoard_Content() {
		return Board_Content;
	}

	public void setBoard_Content(String board_Content) {
		Board_Content = board_Content;
	}

	public String getRegdate() {
		return Regdate;
	}

	public void setRegdate(String regdate) {
		Regdate = regdate;
	}

	public int getCnt() {
		return Cnt;
	}

	public void setCnt(int cnt) {
		Cnt = cnt;
	}

}
