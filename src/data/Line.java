package data;

import java.util.List;

public class Line {
    private final List<Word> listWords;

    public Line(List<Word> listWords) {
        this.listWords = listWords;
    }

    public List<Word> getListWords() {
        return listWords;
    }

    public String getText() {
        StringBuilder sb = new StringBuilder();
        listWords.forEach(word -> sb.append(word.getWord()).append(" "));
        return sb.toString().trim();
    }
}
