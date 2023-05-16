package modelos;

public class Usuario {

    private PersonaEmpresa personaEmpresa;
    private TutorEmpresarial tutorEmpresarial;

    public PersonaEmpresa getPersonaEmpresa() {
        return personaEmpresa;
    }

    public void setPersonaEmpresa(PersonaEmpresa personaEmpresa) {
        this.personaEmpresa = personaEmpresa;
    }

    public TutorEmpresarial getTutorEmpresarial(){return tutorEmpresarial;}

    public void setTutorEmpresarial(TutorEmpresarial tutorEmpresarial) {
        this.tutorEmpresarial = tutorEmpresarial;
    }
}
