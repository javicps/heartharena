package com.lolquizz.heartharena.logic;

import com.lolquizz.heartharena.logic.Card.Rarity;

public class RandomGenerator {

	public static double THRESSHOLD_RARE = 14.28;
	public static double THRESSHOLD_EPIC = 2.01;
	public static double THRESSHOLD_LEGENDARY = 0.28;

	public Rarity getRandomRarity() {
		double seed = getRandomNumberPercent();
		if (seed <= THRESSHOLD_LEGENDARY) {
			return Rarity.LEGENDARY;
		} else if (seed <= THRESSHOLD_EPIC) {
			return Rarity.EPIC;
		} else if (seed <= THRESSHOLD_RARE) {
			return Rarity.RARE;
		}
		return Rarity.COMMON;
	}

	public double getRandomNumber() {
		return Math.random();
	}

	public double getRandomNumberPercent() {
		return getRandomNumber() * 100;
	}

}
