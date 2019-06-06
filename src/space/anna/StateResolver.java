package space.anna;

import java.util.HashMap;
import java.util.Map;

public class StateResolver {
    private final static Map<String, String> states;

    static {
        states = new HashMap<>();

        states.put("0", "Налогоплательщик зарегистрирован в ЕГРН и имел статус действующего в указанную дату");
        states.put("1", "Налогоплательщик зарегистрирован в ЕГРН, но не имел статус действующего в указанную дату");
        states.put("2", "Налогоплательщик зарегистрирован в ЕГРН");
        states.put("3", "Налогоплательщик с указанным ИНН зарегистрирован в ЕГРН, КПП не соответствует ИНН или не указан");
        states.put("4", "Налогоплательщик с указанным ИНН не зарегистрирован в ЕГРН");
        states.put("5", "Некорректный ИНН");
        states.put("6", "Недопустимое количество символов ИНН");
        states.put("7", "Недопустимое количество символов КПП");
        states.put("8", "Недопустимые символы в ИНН");
        states.put("9", "Недопустимые символы в КПП");
        states.put("10", "КПП не должен использоваться при проверке ИП");
        states.put("11", "некорректный формат даты");
        states.put("12", "некорректная дата (ранее 01.01.1991 или позднее текущей даты)");
    }

    public String resolve(String state) {
        return String.format("%s - %s", state, states.get(state));
    }
}
