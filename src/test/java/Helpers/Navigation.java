package Helpers;

public enum Navigation {
    FIRST("Lorem ipsum dolor sit amet"),
    SECOND("Ut enim ad minim veniam, quis nostrud exercitation"),
    THIRD("Excepteur sint occaecat cupidatat non proident");

    private final String text;

    Navigation(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}