package domain;

import data.Line;

import java.util.List;

public interface ITextReaderHelper {

    String getText(String fileName);
    List<Line> getListTexts(String text);
}
