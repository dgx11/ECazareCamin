public class DetaliiStudent {
    private String userId;
    private String nume;
    private String prenume;
    private String facultate;
    private String anFacultate;
    private String specializare;
    private String CNP;
    private String domiciliu;
    private String tipDeStudii;


    public DetaliiStudent(String userId, String nume, String prenume, String facultate, String anFacultate,
                          String specializare, String CNP, String domiciliu, String tipDeStudii) {
        this.userId = userId;
        this.nume = nume;
        this.prenume = prenume;
        this.facultate = facultate;
        this.anFacultate = anFacultate;
        this.specializare = specializare;
        this.CNP = CNP;
        this.domiciliu = domiciliu;
        this.tipDeStudii = tipDeStudii;
    }

    // Getters for the private fields
    public String getUserId() {
        return userId;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getFacultate() {
        return facultate;
    }

    public String getAnFacultate() {
        return anFacultate;
    }

    public String getSpecializare() {
        return specializare;
    }

    public String getCNP() {
        return CNP;
    }

    public String getDomiciliu() {
        return domiciliu;
    }

    public String getTipDeStudii() {
        return tipDeStudii;
    }

    @Override
    public String toString() {
        return nume;
    }
}
