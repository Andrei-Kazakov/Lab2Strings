package domain;

import data.Line;
import data.Word;

import java.util.List;

/**
 * Создать программу обработки текста учебника по программированию с использованием классов (по необходимости) для представления:
 * символа, слова, предложения, знака препинания и др.
 * Во всех задачах с формированием текста заменять табуляции и последовательности пробелов одним пробелом.
 * Программа должна обрабатывать адреса электронной почты и номера телефонов в формате +XXX(XX)XXX-XX-XX как отдельные слова.
 * Найти такое слово в первом предложении, которого нет ни в одном из остальных предложений.
 */
public class TextReader {

    public static final int NUMBER_LINE = 0;

    public static void main(String[] args) {
        String fileName = "Эккель Брюс. Философия java - royallib.com.txt";
        ITextReaderHelper helper = new TextReaderHelper();
        String text = helper.getText(fileName).replaceAll("\t", " ").trim();
        List<Line> lines = helper.getListTexts(text);

        //номер строчки в тексте
        if (lines.size() > NUMBER_LINE) {
            Line first = lines.get(NUMBER_LINE);
            System.out.println(first.getText());

            // Мы берем список слов которые нужно искать
            for (Word searchWord : first.getListWords()) {
                String result = null;

                // Массив предложений всего текста
                for (Line currentLine : lines) {
                    // Чтобы пропустить одинаковое предложение
                    if (!removePunct(currentLine.getText()).equals(removePunct(first.getText()))) {
                        // Разбиваем на массив слов
                        for (Word currentWord : currentLine.getListWords()) {
                            // searchWord - слово которое ищем
                            // currentWord - текущее слово в тексте
                            boolean isSome = checkSomeWords(
                                    currentWord.getWord(),
                                    searchWord.getWord()
                            );

                            // нашли нужное слово в предложении
                            if (isSome) {
                                result = searchWord.getWord();
                                break;
                            }
                        }

                        // Дальше по тексту искать не надо
                        if (result != null) {
                            break;
                        }
                    }
                }

                // Слово не было найдено
                if (result == null) {
                    System.out.println(removePunct(searchWord.getWord()));
                }
            }
        } else {
            throw new IllegalStateException("Not valid text");
        }
    }

    // Проверяет одинаковые слова
    static boolean checkSomeWords(String first, String second) {
        return removePunct(first).equals(removePunct(second));
    }

    // Удаляет пунктуацию
    public static String removePunct(String str) {
        StringBuilder result = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // Только буквы, цифры или пробелы
            boolean isCheck = Character.isAlphabetic(c) || Character.isDigit(c) || Character.isSpaceChar(c);
            if (isCheck) {
                result.append(c);
            }
        }
        return result.toString();
    }
}
