package net.kaeff.zazu.model;

import org.joda.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(suppressConstructorProperties = true)
public class TimeLog {
    public enum Type {
        MORNING("Arbeitsbeginn", "Wann hast Du mit der Arbeit begonnen?"),
        EVENING("Feierabend", "Wann hast du Feierabend gemacht?")
        ;

        private String humanQuestion;
        private String humanNoun;

        Type(String humanNoun, String humanQuestion) {
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

    LocalDateTime dateTime;
    Type type;
}
