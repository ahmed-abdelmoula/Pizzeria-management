package modele;

public class NoteCour {

	String codeM;	
	float noteds;
	float noteExamin;
	float autrenote;
	float noteTp;
	
	
	
	
	public float getNoteTp() {
		return noteTp;
	}
	public void setNoteTp(float noteTp) {
		this.noteTp = noteTp;
	}
	public String getCodeM() {
		return codeM;
	}
	public void setCodeM(String codeM) {
		this.codeM = codeM;
	}
	public float getNoteds() {
		return noteds;
	}
	public void setNoteds(float noteds) {
		this.noteds = noteds;
	}
	public float getNoteExamin() {
		return noteExamin;
	}
	public void setNoteExamin(float noteExamin) {
		this.noteExamin = noteExamin;
	}
	public float getAutrenote() {
		return autrenote;
	}
	public void setAutrenote(float autrenote) {
		this.autrenote = autrenote;
	}
	public NoteCour(String codeM, float noteds, float noteExamin, float autrenote, float notetp2) {
		super();
		this.noteTp=notetp2;
		this.codeM = codeM;
		this.noteds = noteds;
		this.noteExamin = noteExamin;
		this.autrenote = autrenote;
	}
	
	

}
