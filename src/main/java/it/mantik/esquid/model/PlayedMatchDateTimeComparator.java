package it.mantik.esquid.model;

import java.util.Comparator;

public class PlayedMatchDateTimeComparator implements Comparator<PlayedMatch> {

	@Override
	public int compare(PlayedMatch pm1, PlayedMatch pm2) {
		return pm1.getDateTime().compareTo(pm2.getDateTime());
	}

}
