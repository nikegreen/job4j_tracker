package ru.job4j.ex;

public class FindEl {
    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        int rsl = -1;
        for (int i = 0; i < value.length; i++) {
            if (value[i] != null && value[i].equals(key)) {
                return i;
            }
        }
        throw new ElementNotFoundException("Element Not Found Exception");
    }

    public static void main(String[] args) {
        try {
            String[] array = new String[] {
                   "dgdh", "gfjhk", "kfkks", "kjjhkjghg"
            };
            int index = FindEl.indexOf(array, "key");
        } catch (ElementNotFoundException exception) {
            exception.printStackTrace();
        }
    }
}