package pl.kafarsoon.craftymarkdown.feature.lotto.dto;

public enum LottoGame {
    LOTTO("Lotto"),
    LOTTO_PLUS("LottoPlus"),
    SUPER_CHANCE("Superszansa"),
    JOKER("Joker"),
    MINI("Mini"),
    EURO_JACKPOT("EJ");

    private final String game;

    LottoGame(String game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return game;
    }
}
