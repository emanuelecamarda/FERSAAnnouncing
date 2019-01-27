package entity;

import java.util.List;

public class User {
    private String nickname;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private Gender gender;
    private List<Research> favorite;

    public User(String nickname, String nome, String cognome, String email, String password) {
        this.nickname = nickname;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        if (gender == 'M' || gender == 'm') {
            this.gender = Gender.male;
        }
        if (gender == 'F' || gender == 'f') {
            this.gender = Gender.female;
        }
    }

    public List<Research> getFavorite() {
        return favorite;
    }

    public void setFavorite(List<Research> favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return "nome: " + this.nome + "\ncognome: " + this.cognome;
    }
}





