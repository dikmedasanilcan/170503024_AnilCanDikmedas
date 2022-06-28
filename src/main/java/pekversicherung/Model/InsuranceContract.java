package pekversicherung.model;

import java.util.Date;

public class InsuranceContract {

    enum ContractStatus{
        Pending,
        Approved,
        Rejected
    }

    public enum PersonType{
        Kunde("Kunde"),
        Mitarbeiter("Mitarbeiter"),
        VerwaltungsPersonal("VerwaltungsPersonal");

        private String label;
        
        PersonType(String label){
            this.label = label;
        }
        
        public String toString(){
            return this.label;
        }
    }

    public String vertrag_id;
    public String versicherungstyp_id;
    public String versicherungstyp_name;
    public Person person;
    public PersonType person_typ;
    public Date startDatum;
    public Date endDatum;
    public ContractStatus status = null;

    public InsuranceContract(String vertrag_id, String versicherungstyp_id, String versicherungstyp_name, Person person, PersonType person_typ, Date startDatum, Date endDatum, ContractStatus status) {
        this.vertrag_id = vertrag_id;
        this.versicherungstyp_id = versicherungstyp_id;
        this.versicherungstyp_name = versicherungstyp_name;
        this.person = person;
        this.person_typ = person_typ;
        this.startDatum = startDatum;
        this.endDatum = endDatum;
        this.status = status;
    }

    public String getVertrag_id() {
        return vertrag_id;
    }

    public void setVertrag_id(String vertrag_id) {
        this.vertrag_id = vertrag_id;
    }

    public String getVersicherungstyp_id() {
        return versicherungstyp_id;
    }

    public void setVersicherungstyp_id(String versicherungstyp_id) {
        this.versicherungstyp_id = versicherungstyp_id;
    }

    public String getVersicherungstyp_name() {
        return versicherungstyp_name;
    }

    public void setVersicherungstyp_name(String versicherungstyp_name) {
        this.versicherungstyp_name = versicherungstyp_name;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public PersonType getPerson_typ() {
        return person_typ;
    }

    public String getPerson_typName(){
        return person_typ.toString();
    }

    public void setPerson_typ(PersonType person_typ) {
        this.person_typ = person_typ;
    }

    public String getPersonFirstName()
    {
        return person.getFirstName();
    }

    public String getPersonLastName()
    {
        return person.getLastName();
    }

    public Date getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(Date startDatum) {
        this.startDatum = startDatum;
    }

    public Date getEndDatum() {
        return endDatum;
    }

    public void setEndDatum(Date endDatum) {
        this.endDatum = endDatum;
    }

    public ContractStatus getStatus() {
        return status;
    }

    public void setStatus(ContractStatus status) {
        this.status = status;
    }
}