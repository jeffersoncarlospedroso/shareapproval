package br.com.dev2j.shareapproval.api.utils;

public enum Roles {

	ADMIN("ROLE_ADMIN"),
	APPROVAL("ROLE_APPROVAL"),
	UPLOADER("ROLE_UPLOADER");
	
	private String desc;
	
	private Roles(String desc) {
		this.desc = desc;
	}
	
	public String getDesc() {
        return desc;
    }

    public static Roles toEnum(String desc) {
        for (Roles r : Roles.values()) {
            if (r.getDesc().equals(desc)) {
                return r;
            }
        }
        return null;
    }
}
