package neo.ehsanodyssey.java.spring.neologger.ansi;

public interface AnsiElement {

    AnsiElement NORMAL =        new DefaultAnsiElement("0;");
    AnsiElement BOLD =          new DefaultAnsiElement("1;");
    AnsiElement UNDERLINE =     new DefaultAnsiElement("4;");

    AnsiElement FG =            new DefaultAnsiElement("3");
    AnsiElement BG =            new DefaultAnsiElement("4");
    AnsiElement BRIGHT_FG =     new DefaultAnsiElement("9");
    AnsiElement BRIGHT_BG =     new DefaultAnsiElement("10");

    AnsiElement BLACK =         new DefaultAnsiElement("0");
    AnsiElement RED =           new DefaultAnsiElement("1");
    AnsiElement GREEN =         new DefaultAnsiElement("2");
    AnsiElement YELLOW =        new DefaultAnsiElement("3");
    AnsiElement BLUE =          new DefaultAnsiElement("4");
    AnsiElement MAGENTA =       new DefaultAnsiElement("5");
    AnsiElement CYAN =          new DefaultAnsiElement("6");
    AnsiElement WHITE =         new DefaultAnsiElement("7");
    AnsiElement DEFAULT =       new DefaultAnsiElement("9");

    /**
     * @return the ANSI escape code
     */
    @Override
    public String toString();

    /**
     * Internal default {@link AnsiElement} implementation.
     */
    static class DefaultAnsiElement implements AnsiElement {

        private final String code;

        public DefaultAnsiElement(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return this.code;
        }

    }

}
