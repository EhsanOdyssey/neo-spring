package neo.ehsanodyssey.java.spring.neologger;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.pattern.CompositeConverter;
import neo.ehsanodyssey.java.spring.neologger.ansi.AnsiElement;
import neo.ehsanodyssey.java.spring.neologger.ansi.AnsiOutput;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NeoConverter extends CompositeConverter<ILoggingEvent> {
    private static final Map<String, AnsiElement> ANSI_COLORS;
    private static final String LEVEL_COLORS = "LEVEL_COLORS";
    private static Map<Integer, List<AnsiElement>> levelColorsMap = new ConcurrentHashMap<>();
    private static Map<String, List<AnsiElement>> colorsMap = new ConcurrentHashMap<>();
    private static final String LEVEL_PATTERN_STR = "((error|warn|info|debug|trace)\\[%s%s])"; // Level pattern
    private static final String FG_PATTERN_STR = "(default|((bold|underline)_){0,2}(bright_)?(black|red|green|yellow|blue|magenta|cyan|white))"; // Foreground pattern
    private static final String BG_PATTERN_STR = "(@(default|(bright_)?(black|red|green|yellow|blue|magenta|cyan|white)))?"; // Background pattern
    private static final Pattern LEVEL_PATTERN = Pattern.compile(String.format(LEVEL_PATTERN_STR, FG_PATTERN_STR, BG_PATTERN_STR));
    private static final String OPTION_PATTERN_STR = "(%s%s)";
    private static final Pattern OPTION_PATTERN = Pattern.compile(String.format(OPTION_PATTERN_STR, FG_PATTERN_STR, BG_PATTERN_STR));

    static {
        Map<String, AnsiElement> colors = new HashMap<>();
        colors.put("black", AnsiElement.BLACK);
        colors.put("red", AnsiElement.RED);
        colors.put("green", AnsiElement.GREEN);
        colors.put("yellow", AnsiElement.YELLOW);
        colors.put("blue", AnsiElement.BLUE);
        colors.put("magenta", AnsiElement.MAGENTA);
        colors.put("cyan", AnsiElement.CYAN);
        colors.put("white", AnsiElement.WHITE);
        colors.put("default", AnsiElement.DEFAULT);
        ANSI_COLORS = Collections.unmodifiableMap(colors);
    }

    @Override
    public void setContext(Context context) {
        super.setContext(context);
        String firstOption = getFirstOption();
        if (isNotEmpty(firstOption) && !firstOption.equalsIgnoreCase("level")) {
            parseColorOption(firstOption);
        } else if (levelColorsMap.isEmpty()) {
            parseLevelOption(context);
        }
    }

    private static void parseLevelOption(Context context) {
        String customLevelColors = context.getProperty(LEVEL_COLORS);
        if (isNotEmpty(customLevelColors)) {
            setCustomLevelColors(customLevelColors);
        }
        levelColorsMap.putIfAbsent(Level.ERROR_INTEGER, Arrays.asList(AnsiElement.BOLD, AnsiElement.FG, AnsiElement.RED));
        levelColorsMap.putIfAbsent(Level.WARN_INTEGER, Arrays.asList(AnsiElement.BRIGHT_FG, AnsiElement.YELLOW));
        levelColorsMap.putIfAbsent(Level.INFO_INTEGER, Arrays.asList(AnsiElement.BRIGHT_FG, AnsiElement.GREEN));
        levelColorsMap.putIfAbsent(Level.DEBUG_INTEGER, Arrays.asList(AnsiElement.FG, AnsiElement.MAGENTA));
        levelColorsMap.putIfAbsent(Level.TRACE_INTEGER, Arrays.asList(AnsiElement.FG, AnsiElement.BLUE));
    }

    private static void setCustomLevelColors(String customLevelColors) {
        Matcher matcher = LEVEL_PATTERN.matcher(customLevelColors.toLowerCase());
        while (matcher.find()) {
            String[] levelColors = matcher.group(1).split("\\[");
            Level level = Level.toLevel(levelColors[0]);
            String colorStr = levelColors[1].split("]")[0];
            String[] colors = colorStr.toLowerCase().split("@", -1);
            List<AnsiElement> colorElements = new ArrayList<>();
            if(colors.length == 1) {
                parseColors(colors[0], null, colorElements);
            } else if (colors.length == 2) {
                parseColors(colors[0], colors[1], colorElements);
            }
            if (!colorElements.isEmpty()) {
                levelColorsMap.put(level.toInteger(), colorElements);
            }
        }
    }

    private static void parseColorOption(String option) {
            List<AnsiElement> colorElements = new ArrayList<>();
            Matcher matcher = OPTION_PATTERN.matcher(option.toLowerCase());
            while (matcher.find()) {
                String[] colors = matcher.group(1).toLowerCase().split("@", -1);
                if (colors.length == 1) {
                    parseColors(colors[0], null, colorElements);
                } else if (colors.length == 2) {
                    parseColors(colors[0], colors[1], colorElements);
                }
            }
            if (colorElements.isEmpty()) {
                colorElements.add(AnsiElement.DEFAULT);
            }
            colorsMap.putIfAbsent(option, colorElements);
    }

    private static void parseColors(final String foregroundColor, final String backgroundColor, List<AnsiElement> colorElements) {
        if (isNotEmpty(foregroundColor)) {
            String foreground = foregroundColor.replaceAll("_","");
            if (foreground.contains("bold")) {
                foreground = foreground.replace("bold", "");
                colorElements.add(AnsiElement.BOLD);
            }
            if (foreground.contains("underline")) {
                foreground = foreground.replace("underline", "");
                colorElements.add(AnsiElement.UNDERLINE);
            }
            if (foreground.contains("bright")) {
                foreground = foreground.replace("bright", "");
                colorElements.add(AnsiElement.BRIGHT_FG);
            } else {
                colorElements.add(AnsiElement.FG);
            }
            colorElements.add(ANSI_COLORS.getOrDefault(foreground, AnsiElement.DEFAULT));
        }
        if (isNotEmpty(backgroundColor)) {
            String background = backgroundColor.replaceAll("_","");
            if (background.contains("bright")) {
                background = background.replace("bright", "");
                colorElements.add(AnsiElement.BRIGHT_BG);
            } else {
                colorElements.add(AnsiElement.BG);
            }
            colorElements.add(ANSI_COLORS.getOrDefault(background, AnsiElement.DEFAULT));
        }
    }

    @Override
    protected String transform(ILoggingEvent event, String in) {
        String firstOption = getFirstOption();
        if (isNotEmpty(firstOption) && !firstOption.equalsIgnoreCase("level")) {
            return toAnsiString(in, colorsMap.get(firstOption));
        }
        return toAnsiString(in, levelColorsMap.get(event.getLevel().toInteger()));
    }

    private static boolean isNotEmpty(String str) {
        return str != null && str.length() > 0;
    }

    private String toAnsiString(String in, List<AnsiElement> elements) {
        return AnsiOutput.toString(in, elements);
    }

}
