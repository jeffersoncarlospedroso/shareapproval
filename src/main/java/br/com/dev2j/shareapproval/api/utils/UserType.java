package br.com.dev2j.shareapproval.api.utils;

public enum UserType {

	ADMIN(1, "ADMIN"),
	APPROVAL(2, "APPROVAL"),
	UPLOADER(3, "UPLOADER");


    private int id;
    private String nome;

    UserType(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static UserType toEnum(int id) {
        for(UserType t : UserType.values()) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }
}
