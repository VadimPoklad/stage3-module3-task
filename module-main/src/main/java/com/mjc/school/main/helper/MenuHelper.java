package com.mjc.school.main.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import static com.mjc.school.main.helper.Constant.*;
import static com.mjc.school.main.helper.Operations.*;


@Component
public class MenuHelper {
    private final ObjectMapper mapper = new ObjectMapper();
    private final Map<String, Function<Scanner, Command>> operations;
    private final PrintStream printStream;

    public MenuHelper() {
        operations = new HashMap<>();

        operations.put(String.valueOf(GET_ALL_NEWS.getOperationNumber()), this::getNews);
        operations.put(String.valueOf(GET_NEWS_BY_ID.getOperationNumber()), this::getNewsById);
        operations.put(String.valueOf(GET_NEWS_BY_AUTHOR_NAME.getOperationNumber()), this::getNewsByAuthorName);
        operations.put(String.valueOf(GET_NEWS_BY_TITLE.getOperationNumber()), this::getNewsByTitle);
        operations.put(String.valueOf(GET_NEWS_BY_CONTENT.getOperationNumber()), this::getNewsByContent);
        operations.put(String.valueOf(GET_NEWS_BY_TAG_IDS.getOperationNumber()), this::getNewsByTagIds);
        operations.put(String.valueOf(GET_NEWS_BY_TAG_NAMES.getOperationNumber()), this::getNewsByTagNames);

        operations.put(String.valueOf(CREATE_NEWS.getOperationNumber()), this::createNews);
        operations.put(String.valueOf(UPDATE_NEWS.getOperationNumber()), this::updateNews);
        operations.put(String.valueOf(REMOVE_NEWS_BY_ID.getOperationNumber()), this::deleteNews);

        operations.put(String.valueOf(GET_ALL_AUTHORS.getOperationNumber()), this::getAuthors);
        operations.put(String.valueOf(GET_AUTHOR_BY_ID.getOperationNumber()), this::getAuthorById);
        operations.put(String.valueOf(CREATE_AUTHOR.getOperationNumber()), this::createAuthor);
        operations.put(String.valueOf(UPDATE_AUTHOR.getOperationNumber()), this::updateAuthor);
        operations.put(String.valueOf(REMOVE_AUTHOR_BY_ID.getOperationNumber()), this::deleteAuthor);

        operations.put(String.valueOf(GET_ALL_TAGS.getOperationNumber()), this::getTags);
        operations.put(String.valueOf(GET_TAG_BY_ID.getOperationNumber()), this::getTagById);
        operations.put(String.valueOf(CREATE_TAG.getOperationNumber()), this::createTag);
        operations.put(String.valueOf(UPDATE_TAG.getOperationNumber()), this::updateTag);
        operations.put(String.valueOf(REMOVE_TAG_BY_ID.getOperationNumber()), this::deleteTag);

        this.printStream = System.out;
    }

    public void printMainMenu() {
        printStream.println(NUMBER_OPERATION_ENTER);
        for (Operations operation : Operations.values()) {
            printStream.println(operation.getOperationWithNumber());
        }
    }

    public Command getCommand(String key, Scanner keyboard) {
        return operations.getOrDefault(key, this::getCommandNotFound).apply(keyboard);
    }

    private Command getCommandNotFound(Scanner keyboard) {
        return Command.NOT_FOUND;
    }

    private Command getNews(Scanner keyboard) {
        printStream.println(GET_ALL_NEWS.getOperation());
        return Command.GET_NEWS;
    }

    private Command getNewsById(Scanner keyboard) {
        printStream.println(GET_NEWS_BY_ID.getOperation());
        printStream.println(NEWS_ID_ENTER);
        return new Command(
                GET_NEWS_BY_ID.getOperationNumber(),
                Map.of("id", String.valueOf(getKeyboardNumber(NEWS_ID, keyboard))),
                null);
    }

    private Command getNewsByAuthorName(Scanner keyboard) {
        Command command = null;
        try {
            printStream.println(GET_NEWS_BY_AUTHOR_NAME.getOperation());
            printStream.println(AUTHOR_NAME_ENTER);
            command = new Command(
                    GET_NEWS_BY_AUTHOR_NAME.getOperationNumber(),
                    Map.of("name", String.valueOf(keyboard.nextLine())),
                    null);
        } catch (Exception ex) {
            printStream.println(ex.getMessage());
        }
        return command;
    }

    private Command getNewsByTagNames(Scanner keyboard) {
        printStream.println(GET_NEWS_BY_TAG_NAMES.getOperation());
        printStream.println(NEWS_TAG_NAMES_ENTER);
        return new Command(
                GET_NEWS_BY_TAG_NAMES.getOperationNumber(),
                Map.of("tagNames", keyboard.nextLine()),
                null);
    }

    private Command getNewsByTagIds(Scanner keyboard) {
        printStream.println(GET_NEWS_BY_TAG_IDS.getOperation());
        printStream.println(NEWS_TAG_IDS_ENTER);
        return new Command(
                GET_NEWS_BY_TAG_IDS.getOperationNumber(),
                Map.of("tagIds", keyboard.nextLine()),
                null);
    }

    private Command getNewsByContent(Scanner keyboard) {
        printStream.println(GET_NEWS_BY_CONTENT.getOperation());
        printStream.println(NEWS_CONTENT_ENTER);
        return new Command(
                GET_NEWS_BY_CONTENT.getOperationNumber(),
                Map.of("content", keyboard.nextLine()),
                null);
    }

    private Command getNewsByTitle(Scanner keyboard) {
        printStream.println(GET_NEWS_BY_TITLE.getOperation());
        printStream.println(NEWS_TITLE_ENTER);
        return new Command(
                GET_NEWS_BY_TITLE.getOperationNumber(),
                Map.of("title", keyboard.nextLine()),
                null);
    }

    private Command createNews(Scanner keyboard) {
        Command command = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                printStream.println(CREATE_NEWS.getOperation());
                printStream.println(NEWS_TITLE_ENTER);
                String title = keyboard.nextLine();
                printStream.println(NEWS_CONTENT_ENTER);
                String content = keyboard.nextLine();
                printStream.println(AUTHOR_ID_ENTER);
                long authorId = getKeyboardNumber(AUTHOR_ID, keyboard);
                printStream.println(NEWS_TAG_IDS_ENTER);
                String tagIds = keyboard.nextLine();

                Map<String, String> body =
                        Map.of("title", title,
                                "content", content,
                                "authorId", Long.toString(authorId),
                                "tagIds", tagIds);

                command =
                        new Command(CREATE_NEWS.getOperationNumber(), null, mapper.writeValueAsString(body));
                isValid = true;
            } catch (Exception ex) {
                printStream.println(ex.getMessage());
            }
        }

        return command;
    }

    private Command updateNews(Scanner keyboard) {
        Command command = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                printStream.println(UPDATE_NEWS.getOperation());
                printStream.println(NEWS_ID_ENTER);
                long newsId = getKeyboardNumber(NEWS_ID, keyboard);
                printStream.println(NEWS_TITLE_ENTER);
                String title = keyboard.nextLine();
                printStream.println(NEWS_CONTENT_ENTER);
                String content = keyboard.nextLine();
                printStream.println(AUTHOR_ID_ENTER);
                long authorId = getKeyboardNumber(AUTHOR_ID, keyboard);
                printStream.println(NEWS_TAG_IDS_ENTER);
                String tagIds = keyboard.nextLine();

                Map<String, String> body =
                        Map.of(
                                "id",
                                Long.toString(newsId),
                                "title",
                                title,
                                "content",
                                content,
                                "authorId",
                                Long.toString(authorId),
                                "tagIds", tagIds);

                command =
                        new Command(UPDATE_NEWS.getOperationNumber(), null, mapper.writeValueAsString(body));
                isValid = true;
            } catch (Exception ex) {
                printStream.println(ex.getMessage());
            }
        }

        return command;
    }

    private Command deleteNews(Scanner keyboard) {
        printStream.println(REMOVE_NEWS_BY_ID.getOperation());
        printStream.println(NEWS_ID_ENTER);
        return new Command(
                REMOVE_NEWS_BY_ID.getOperationNumber(),
                Map.of("id", Long.toString(getKeyboardNumber(NEWS_ID, keyboard))),
                null);
    }

    private Command getAuthors(Scanner keyboard) {
        printStream.println(GET_ALL_AUTHORS.getOperation());
        return Command.GET_AUTHORS;
    }

    private Command getAuthorById(Scanner keyboard) {
        printStream.println(GET_AUTHOR_BY_ID.getOperation());
        printStream.println(AUTHOR_ID_ENTER);
        return new Command(
                GET_AUTHOR_BY_ID.getOperationNumber(),
                Map.of("id", String.valueOf(getKeyboardNumber(AUTHOR_ID, keyboard))),
                null);
    }

    private Command createAuthor(Scanner keyboard) {
        Command command = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                printStream.println(CREATE_AUTHOR.getOperation());
                printStream.println(AUTHOR_NAME_ENTER);
                String name = keyboard.nextLine();

                Map<String, String> body = Map.of("name", name);
                command =
                        new Command(CREATE_AUTHOR.getOperationNumber(), null, mapper.writeValueAsString(body));
                isValid = true;
            } catch (Exception ex) {
                printStream.println(ex.getMessage());
            }
        }

        return command;
    }

    private Command updateAuthor(Scanner keyboard) {
        Command command = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                printStream.println(UPDATE_AUTHOR.getOperation());
                printStream.println(AUTHOR_ID_ENTER);
                long authorId = getKeyboardNumber(AUTHOR_ID, keyboard);
                printStream.println(AUTHOR_NAME_ENTER);
                String name = keyboard.nextLine();

                Map<String, String> body = Map.of("id", Long.toString(authorId), "name", name);
                command =
                        new Command(UPDATE_AUTHOR.getOperationNumber(), null, mapper.writeValueAsString(body));
                isValid = true;
            } catch (Exception ex) {
                printStream.println(ex.getMessage());
            }
        }

        return command;
    }

    private Command deleteAuthor(Scanner keyboard) {
        printStream.println(REMOVE_AUTHOR_BY_ID.getOperation());
        printStream.println(AUTHOR_ID_ENTER);
        return new Command(
                REMOVE_AUTHOR_BY_ID.getOperationNumber(),
                Map.of("id", Long.toString(getKeyboardNumber(AUTHOR_ID, keyboard))),
                null);
    }

    private Command getTags(Scanner keyboard) {
        printStream.println(GET_ALL_TAGS.getOperation());
        return Command.GET_TAGS;
    }

    private Command getTagById(Scanner keyboard) {
        printStream.println(GET_TAG_BY_ID.getOperation());
        printStream.println(TAG_ID_ENTER);
        return new Command(
                GET_TAG_BY_ID.getOperationNumber(),
                Map.of("id", String.valueOf(getKeyboardNumber(TAG_ID, keyboard))),
                null);
    }

    private Command updateTag(Scanner keyboard) {
        Command command = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                printStream.println(UPDATE_TAG.getOperation());
                printStream.println(TAG_ID_ENTER);
                long tagId = getKeyboardNumber(TAG_ID, keyboard);
                printStream.println(TAG_NAME_ENTER);
                String name = keyboard.nextLine();

                Map<String, String> body = Map.of("id", Long.toString(tagId), "name", name);
                command = new Command(UPDATE_TAG.getOperationNumber(),
                        null,
                        mapper.writeValueAsString(body));
                isValid = true;
            } catch (Exception ex) {
                printStream.println(ex.getMessage());
            }
        }

        return command;
    }


    private Command createTag(Scanner keyboard) {
        Command command = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                printStream.println(CREATE_TAG.getOperation());
                printStream.println(TAG_NAME_ENTER);
                String name = keyboard.nextLine();

                Map<String, String> body = Map.of("name", name);
                command =
                        new Command(CREATE_TAG.getOperationNumber(), null, mapper.writeValueAsString(body));
                isValid = true;
            } catch (Exception ex) {
                printStream.println(ex.getMessage());
            }
        }

        return command;
    }

    private Command deleteTag(Scanner keyboard) {
        printStream.println(REMOVE_TAG_BY_ID.getOperation());
        printStream.println(TAG_ID_ENTER);
        return new Command(
                REMOVE_TAG_BY_ID.getOperationNumber(),
                Map.of("id", Long.toString(getKeyboardNumber(TAG_ID, keyboard))),
                null);
    }

    private long getKeyboardNumber(String params, Scanner keyboard) {
        long enter;
        try {
            enter = keyboard.nextLong();
            keyboard.nextLine();
        } catch (Exception ex) {
            keyboard.nextLine();
            throw new RuntimeException(String.format("%s should be number", params));
        }
        return enter;
    }
}
