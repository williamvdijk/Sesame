package model.kaarten;

public class Kelk implements Schat{
	private int waarde = 2;
	private boolean gepakt;

	@Override
	public void setGepakt(boolean gepakt) {
		this.gepakt = gepakt;
	}

	@Override
	public boolean isGepakt() {
		return gepakt;
	}

	@Override
	public void toonKaart(String icoon) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getWaarde() {
		return waarde;
	}

	@Override
	public void setSpeler(String speler) {
		Speler.naam = speler;

	}

	@Override
	public String getSpeler() {
		return Speler.naam;
	}

}
