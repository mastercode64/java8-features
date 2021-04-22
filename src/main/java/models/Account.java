package models;

import java.time.LocalDate;

public class Account {

    private String name;
    private LocalDate birthDate;
    private Long currentBalance;

    public Account(String name, LocalDate birthDate, Long currentBalance) {
        this.name = name;
        this.birthDate = birthDate;
        this.currentBalance = currentBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Long getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Long currentBalance) {
        this.currentBalance = currentBalance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", currentBalance=" + currentBalance +
                '}';
    }
}
