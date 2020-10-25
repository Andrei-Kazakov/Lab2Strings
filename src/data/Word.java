package data;

import java.util.List;

public class Word {
    private final List<Symbol> listSymbol;

    public Word(List<Symbol> listSymbol) {
        this.listSymbol = listSymbol;
    }

    public List<Symbol> getListSymbol() {
        return listSymbol;
    }

    public String getWord() {
        StringBuilder sb = new StringBuilder();
        listSymbol.forEach(symbol -> sb.append(symbol.getSymbol()));
        return sb.toString();
    }
}
