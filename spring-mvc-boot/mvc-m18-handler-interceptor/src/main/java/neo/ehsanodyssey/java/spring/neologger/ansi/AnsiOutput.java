package neo.ehsanodyssey.java.spring.neologger.ansi;

import java.util.List;

public abstract class AnsiOutput {

    private static final String ENCODE_START = "\033[";

    private static final String ENCODE_END = "m";

    private static final String RESET = AnsiElement.NORMAL.toString() + AnsiElement.FG.toString() + AnsiElement.DEFAULT.toString();

    public static String toString(String in, List<AnsiElement> elements) {
        StringBuilder sb = new StringBuilder();
        buildEnabled(sb, in, elements);
        return sb.toString();
    }

    private static void buildEnabled(StringBuilder sb, String in, List<AnsiElement> elements) {
        boolean writingAnsi = false;
        boolean containsEncoding = false;
        if (elements != null && !elements.isEmpty()) {
            for (AnsiElement element : elements) {
                containsEncoding = true;
                if (!writingAnsi) {
                    sb.append(ENCODE_START);
                    writingAnsi = true;
                }
                if (element == AnsiElement.BG || element == AnsiElement.BRIGHT_BG) {
                    sb.append(ENCODE_END).append(ENCODE_START);
                }
                sb.append(element);
            }
        }
        if (containsEncoding) {
            sb.append(ENCODE_END);
        }
        sb.append(in);
        if (containsEncoding) {
            sb.append(ENCODE_START);
            sb.append(RESET);
            sb.append(ENCODE_END);
        }
    }
}
