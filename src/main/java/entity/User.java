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

    public User(String nickname, String nome, String cognome, String email, String password, Gender gender) {
        this.nickname = nickname;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "nome: " + this.nome + "\ncognome: " + this.cognome;
    }
}




