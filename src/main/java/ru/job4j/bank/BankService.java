package ru.job4j.bank;

import java.util.*;

/**
 * Класс описывает
 * главный сервис банка
 * @author Nike Zelensky
 * @version 1.0
 */
public class BankService {
    /**
     * поле содержит всех пользователей системы с привязанными к ним счетами
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * метод должен добавить пользователя в систему
     * @param user - добавляемый пользователь
     */
    public void addUser(User user) {
        users.put(user, new ArrayList<Account>());
    }

    /**
     * метод должен добавить новый счет к пользователю
     * @param passport - номер паспорта, тип String
     * @param account - банковскй счет, тип Account
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null && !users.get(user).contains(account)) {
            users.get(user).add(account);
        }
    }

    /**
     * метод ищет пользователя по номеру паспорта
     * @param passport - номер паспорта, тип String
     * @return - если пользователь с номером паспорта найден, то возращается объект типа User.
     *  Иначе результат будет равен null.
     */
    public User findByPassport(String passport) {
        return users.keySet().stream()
                .filter((str) -> passport.equals(str.getPassport()))
                .findFirst()
                .orElse(null);
    }

    /**
     * метод ищет счет пользователя по реквизитам
     * @param passport - номер паспорта
     * @param requisite - реквизиты счёта
     * @return - возращает счёт, если есть указанный пользователь и счёт с заданными реквизитами.
     * Иначе результат равен null
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user == null) {
            return null;
        }
        return users.get(user).stream()
                    .filter(a -> a.getRequisite().equals(requisite))
                    .findFirst()
                    .orElse(null);
    }

    /**
     * метод предназначен для перечисления денег с одного счёта на другой счёт.
     * @param srcPassport - номер паспорта пользователя отправляющего деньги
     * @param srcRequisite - реквизиты счёта с которого снимают деньги
     * @param destPassport - номер паспорта пользователя получающего деньги
     * @param destRequisite - реквизиты счёта на который переводят деньги
     * @param amount - переводимая сумма
     * @return - Если счёт не найден или не хватает денег на счёте srcAccount
     * (с которого переводят), то метод должен вернуть false. Иначе true.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount != null && destAccount != null && srcAccount.getBalance() >= amount) {
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            destAccount.setBalance(destAccount.getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }
}
