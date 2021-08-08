package modele;

public class Matiere {
	private String code;
	private String libelle;
	private float coeificient;
	private int type;
	private float moyenne;
	public float getMoyenne() {
		return moyenne;
	}
	public Matiere(){
		
	}
	public Matiere(String code, String libelle, float coeificient, int type, float moye) {
		super();
		this.moyenne=moye;
		this.code = code;
		this.libelle = libelle;
		this.coeificient = coeificient;
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public String getLibelle() {
		return libelle;
	}
	public float getCoeificient() {
		return coeificient;
	}
	public int getType() {
		return type;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public void setCoeificient(float coeificient) {
		this.coeificient = coeificient;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setMoyenne(float moyenne) {
		this.moyenne = moyenne;
	}
	
	
	
	

}
