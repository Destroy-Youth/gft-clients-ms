package dev.gft.example.clients.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ClientDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4503663735692705974L;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String sex;
    private List<AccountDTO> accounts;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDTO> accounts) {
        this.accounts = accounts;
    }

}
