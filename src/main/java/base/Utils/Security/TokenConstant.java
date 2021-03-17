package base.Utils.Security;

public enum TokenConstant {
	
	EMPTY_TOKEN_TENANT_FIELD("");
	
	private String value;

	TokenConstant(String value){
		this.value=value;
	}
	
	public String getValue(){
		return this.value;
	}
}
