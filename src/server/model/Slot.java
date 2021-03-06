package server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Slot implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<SymboolEnum> symbolen;
	private int positie;
	private boolean positieJuist = false;

	public Slot(int geestkaartSymboolId) {
		symbolen = new ArrayList<SymboolEnum>(Arrays.asList(SymboolEnum.values()));
		Collections.shuffle(symbolen);
		checkPositie(geestkaartSymboolId);
	}

	public List<SymboolEnum> getSymbolen() {
		return symbolen;
	}

	public SymboolEnum getSymbool() {
		return symbolen.get(positie);
	}

	public int getPositie() {
		return this.positie;
	}

	public void setPositie(int positie) {
		this.positie = positie;
	}

	public void checkPositie(int geestkaartSymboolId) {
		if(getSymbool().getId() == geestkaartSymboolId) {
			this.positieJuist = true;
		} else if(getSymbool().getId() != geestkaartSymboolId){
			this.positieJuist = false;
		}
	}

	public boolean isPositieJuist() {
		return this.positieJuist;
	}

}
