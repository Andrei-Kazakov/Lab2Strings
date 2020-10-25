package domain;

import data.Symbol;
import data.Line;
import data.Word;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextReaderHelper implements ITextReaderHelper {

    // +XXX(XX)XXX-XX-XX
    // +111 (202) 555-0125
    Pattern patternPhone = Pattern.compile("^+375 (17/29/33/44).00-9]{3}-[0-9]{2}-[0-9]{2}$");
    Pattern patternEmail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$");


    // Cp1251 utf-8
    @Override
    public String getText(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "Cp1251"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    //region  text
    @Override
    public List<Line> getListTexts(String text) {
        List<Line> result = new ArrayList<>();


        // Разбивка на предложения
        // Нужно найти нормальное разделение предложение
        String[] textArray = text.split("\n");
        List<String> filterList = Arrays.stream(textArray)
                .filter(s -> !s.trim().isEmpty())
                .collect(Collectors.toList());

        filterList
                .forEach(currentText -> {
                    // Погугли
                    // С этим погуглить и разобраться
                    Matcher matcherPhone = patternPhone.matcher(currentText);
                    Matcher matcherEmail = patternEmail.matcher(currentText);

                    while(matcherPhone.find()) {
                        System.out.println("phone = " + matcherPhone.group(2));
                    }

                    while(matcherEmail.find()) {
                        System.out.println("mail = " + matcherEmail.group(2));
                    }
                    // end region

                    // Разбивка на массив слов
                    String[] words = currentText.split(" ");
                    List<Word> listWords = new ArrayList<>();
                    Arrays.stream(words)
                            .forEach(word -> {
                                List<Symbol> listSymbol = new ArrayList<>();
                                // Разбивка слова на массив символов
                                char[] listChar = word.toCharArray();
                                for (char c : listChar) {
                                    listSymbol.add(new Symbol(c));
                                }

                                listWords.add(new Word(listSymbol));
                            });
                    result.add(new Line(listWords));
                });

        return result;
    }
}
