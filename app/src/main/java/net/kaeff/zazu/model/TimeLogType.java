package net.kaeff.zazu.model;

public enum TimeLogType {
    MORNING("Arbeitsbeginn", "Wann hast Du mit der Arbeit begonnen?"),
    EVENING("Feierabend", "Wann hast du Feierabend gemacht?");

    private String humanQuestion;
    private String humanNoun;

    TimeLogType(String humanNoun, String humanQuestion) {
        this.humanNoun = humanNoun;
        this.humanQuestion = humanQuestion;
    }

    public String asHumanNoun() {
        return humanNoun;
    }

    public String asHumanQuestion() {
        return humanQuestion;
    }
}
