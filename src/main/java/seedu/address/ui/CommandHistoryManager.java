package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;

public class CommandHistoryManager {
    private final List<String> history = new ArrayList<>();
    private int index = 0;
    private String tempInput = null;

    public void add(String command) {
        history.add(command);
        index = history.size();
        tempInput = null;
    }

    public String getPrevious(String currentInput) {
        if (tempInput == null) {
            tempInput = currentInput;
        }
        if (index > 0) {
            index--;
            return history.get(index);
        }
        return null;
    }

    public String getNext() {
        if (index < history.size() - 1) {
            index++;
            return history.get(index);
        } else if (index == history.size() - 1) {
            index++;
            return tempInput != null ? tempInput : "";
        }
        return "";
    }
}
