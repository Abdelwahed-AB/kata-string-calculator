package fr.norsys.stringcalculator;

public class DelimeterCleanUpResult {
    private String delimeter;
    private int removedCharsLen;

    public DelimeterCleanUpResult() {}

    public DelimeterCleanUpResult(String delimeter, int removedCharsLen) {
        this.delimeter = delimeter;
        this.removedCharsLen = removedCharsLen;
    }

    public String getDelimeter() {
        return delimeter;
    }

    public void setDelimeter(String delimeter) {
        this.delimeter = delimeter;
    }

    public int getRemovedCharsLen() {
        return removedCharsLen;
    }

    public void setRemovedCharsLen(int removedCharsLen) {
        this.removedCharsLen = removedCharsLen;
    }
}
